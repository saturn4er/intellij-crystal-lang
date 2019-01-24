/*
 * Copyright 2013-2016 Sergey Ignatov, Alexander Zolotov, Florin Patan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.kenro.ji.jin.crystal.sdk;

import com.intellij.execution.configurations.PathEnvironmentVariableUtil;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.util.SystemInfo;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.vfs.*;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.util.CachedValueProvider;
import com.intellij.psi.util.CachedValuesManager;
import com.intellij.util.Function;
import com.intellij.util.ObjectUtils;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.util.text.VersionComparatorUtil;
import net.kenro.ji.jin.crystal.util.CrystalConstants;
import net.kenro.ji.jin.crystal.util.CrystalEnvironmentUtil;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.intellij.util.containers.ContainerUtil.newLinkedHashSet;

public class CrystalSDKUtil {
    private static final Pattern CRYSTAL_VERSION_PATTERN = Pattern.compile("^# ([\\d]+\\.[\\d]+\\.[\\d]) ");
    private static final Key<String> ZVERSION_DATA_KEY = Key.create("CRYSTAL_ZVERSION_KEY");

    private CrystalSDKUtil() {
    }

    @Nullable
    public static VirtualFile getSdkSrcDir(@NotNull Project project, @Nullable Module module) {
        if (module != null) {
            return CachedValuesManager.getManager(project).getCachedValue(module, () -> {
                CrystalSdkService sdkService = CrystalSdkService.getInstance(module.getProject());
                return CachedValueProvider.Result.create(getInnerSdkSrcDir(sdkService, module), sdkService);
            });
        }
        return CachedValuesManager.getManager(project).getCachedValue(project, () -> {
            CrystalSdkService sdkService = CrystalSdkService.getInstance(project);
            return CachedValueProvider.Result.create(getInnerSdkSrcDir(sdkService, null), sdkService);
        });
    }

    @Nullable
    private static VirtualFile getInnerSdkSrcDir(@NotNull CrystalSdkService sdkService, @Nullable Module module) {
        String sdkHomePath = sdkService.getSdkHomePath(module);
        String sdkVersionString = sdkService.getSdkVersion(module);
        return sdkHomePath != null && sdkVersionString != null ? getSdkSrcDir(sdkHomePath, sdkVersionString) : null;
    }

    @Nullable
    private static VirtualFile getSdkSrcDir(@NotNull String sdkPath, @NotNull String sdkVersion) {
        String srcPath = getSrcLocation(sdkVersion);
        VirtualFile file = VirtualFileManager.getInstance().findFileByUrl(VfsUtilCore.pathToUrl(FileUtil.join(sdkPath, srcPath)));
        return file != null && file.isDirectory() ? file : null;
    }


    @Nullable
    public static VirtualFile findExecutableInCrystalPath(@NotNull String executableName, @NotNull Project project, @Nullable Module module) {
        executableName = CrystalEnvironmentUtil.getBinaryFileNameForPath(executableName);
        Collection<VirtualFile> roots = getCrystalPathRoots(project, module);
        for (VirtualFile file : roots) {
            VirtualFile child = VfsUtil.findRelativeFile(file, "bin", executableName);
            if (child != null) return child;
        }
        File fromPath = PathEnvironmentVariableUtil.findInPath(executableName);
        return fromPath != null ? VfsUtil.findFileByIoFile(fromPath, true) : null;
    }


    @NotNull
    private static Collection<VirtualFile> getCrystalPathRoots(@NotNull Project project, @Nullable Module module) {
        Collection<VirtualFile> roots = ContainerUtil.newArrayList();
//        if (CrystalApplicationLibrariesService.getInstance().isUseCrystalPathFromSystemEnvironment()) {
//            roots.addAll(getCrystalPathsRootsFromEnvironment());
//        }
//        roots.addAll(module != null ? CrystalLibrariesService.getUserDefinedLibraries(module) : CrystalLibrariesService.getUserDefinedLibraries(project));
        return roots;
    }


    @NotNull
    private static List<VirtualFile> getInnerCrystalPathSources(@NotNull Project project, @Nullable Module module) {
        return ContainerUtil.mapNotNull(getCrystalPathRoots(project, module), new RetrieveSubDirectoryOrSelfFunction("src"));
    }

    @NotNull
    private static Collection<VirtualFile> getCrystalPathBins(@NotNull Project project, @Nullable Module module) {
        Collection<VirtualFile> result = newLinkedHashSet(ContainerUtil.mapNotNull(getCrystalPathRoots(project, module),
                new RetrieveSubDirectoryOrSelfFunction("bin")));
        String executableCrystalPath = CrystalSdkService.getInstance(project).getCrystalExecutablePath(module);
        if (executableCrystalPath != null) {
            VirtualFile executable = VirtualFileManager.getInstance().findFileByUrl(VfsUtilCore.pathToUrl(executableCrystalPath));
            if (executable != null) ContainerUtil.addIfNotNull(result, executable.getParent());
        }
        return result;
    }


    @NotNull
    public static String retrieveCrystalPath(@NotNull Project project, @Nullable Module module) {
        return StringUtil.join(ContainerUtil.map(getCrystalPathRoots(project, module), VirtualFile::getPath), File.pathSeparator);
    }

    @NotNull
    public static String retrieveEnvironmentPathForCrystal(@NotNull Project project, @Nullable Module module) {
        return StringUtil.join(ContainerUtil.map(getCrystalPathBins(project, module), VirtualFile::getPath), File.pathSeparator);
    }


    @NotNull
    private static String getSrcLocation(@NotNull String version) {
        if (ApplicationManager.getApplication().isUnitTestMode()) {
            return "src/pkg";
        }
        if (version.startsWith("devel")) {
            return "src";
        }
        if (version.length() > 2 && StringUtil.parseDouble(version.substring(0, 3), 1.4) < 1.4) {
            return "src/pkg";
        }
        return "src";
    }

    public static int compareVersions(@NotNull String lhs, @NotNull String rhs) {
        return VersionComparatorUtil.compare(lhs, rhs);
    }

    @Nullable
    @Contract("null, _ -> null")
    public static String getImportPath(@Nullable PsiDirectory psiDirectory, boolean withVendoring) {
        if (psiDirectory == null) {
            return null;
        }
        return CachedValuesManager.getCachedValue(psiDirectory, withVendoring ? new CachedVendoredImportPathProvider(psiDirectory)
                : new CachedImportPathProviderImpl(psiDirectory));
    }


    @Nullable
    public static String getRelativePathToRoots(@NotNull VirtualFile file, @NotNull Collection<VirtualFile> sourceRoots) {
        for (VirtualFile root : sourceRoots) {
            String relativePath = VfsUtilCore.getRelativePath(file, root, '/');
            if (StringUtil.isNotEmpty(relativePath)) {
                return relativePath;
            }
        }
        return null;
    }


    @Nullable
    public static String parseCrystalVersion(@NotNull String text) {
        Matcher matcher = CRYSTAL_VERSION_PATTERN.matcher(text);
        if (matcher.find()) {
            return matcher.group(1);
        }

        return null;
    }

    @Nullable
    public static String retrieveCrystalVersion(@NotNull String sdkPath) {
        try {
            VirtualFile sdkRoot = VirtualFileManager.getInstance().findFileByUrl(VfsUtilCore.pathToUrl(sdkPath));
            if (sdkRoot != null) {
                String cachedVersion = sdkRoot.getUserData(ZVERSION_DATA_KEY);
                if (cachedVersion != null) {
                    return !cachedVersion.isEmpty() ? cachedVersion : null;
                }
                VirtualFile crystalChangelog = sdkRoot.findFileByRelativePath(CrystalConstants.CHANGELOG_FILE);

                if (crystalChangelog != null) {
                    String text = VfsUtilCore.loadText(crystalChangelog);
                    String version = parseCrystalVersion(text);
                    if (version == null) {
                        CrystalSdkService.LOG.debug("Cannot retrieve go version from zVersion file: " + text);
                    }
                    sdkRoot.putUserData(ZVERSION_DATA_KEY, StringUtil.notNullize(version));
                    return version;
                } else {
                    CrystalSdkService.LOG.debug("Cannot find go version file in sdk path: " + sdkPath);
                }
            }
        } catch (IOException e) {
            CrystalSdkService.LOG.debug("Cannot retrieve crystal version from sdk path: " + sdkPath, e);
        }
        return null;
    }


    @NotNull
    public static Collection<VirtualFile> getSdkDirectoriesToAttach(@NotNull String sdkPath, @NotNull String versionString) {
        // scr is enough at the moment, possible process binaries from pkg
        return ContainerUtil.createMaybeSingletonList(getSdkSrcDir(sdkPath, versionString));
    }


    @NotNull
    public static Collection<Module> getCrystalModules(@NotNull Project project) {
        if (project.isDefault()) return Collections.emptyList();
        CrystalSdkService sdkService = CrystalSdkService.getInstance(project);
        Module[] modules = ModuleManager.getInstance(project).getModules();
        return ContainerUtil.filter(modules, sdkService::isCrystalModule);
    }


    @Nullable
    public static VirtualFile findParentDirectory(@Nullable VirtualFile file, @NotNull Set<VirtualFile> sourceRoots, @NotNull String name) {
        if (file == null) {
            return null;
        }
        VirtualFile currentFile = file.isDirectory() ? file : file.getParent();
        while (currentFile != null && !sourceRoots.contains(currentFile)) {
            if (currentFile.isDirectory() && name.equals(currentFile.getName())) {
                return currentFile;
            }
            currentFile = currentFile.getParent();
        }
        return null;
    }

    private static boolean isUnreachablePackage(@NotNull String unreachableDirectoryName,
                                                @NotNull VirtualFile targetDirectory,
                                                @NotNull VirtualFile referenceContextFile,
                                                @NotNull Set<VirtualFile> sourceRoots) {
        VirtualFile directory = findParentDirectory(targetDirectory, sourceRoots, unreachableDirectoryName);
        VirtualFile parent = directory != null ? directory.getParent() : null;
        return directory != null && !VfsUtilCore.isAncestor(parent, referenceContextFile, false);
    }

    private static class RetrieveSubDirectoryOrSelfFunction implements Function<VirtualFile, VirtualFile> {
        @NotNull
        private final String mySubdirName;

        public RetrieveSubDirectoryOrSelfFunction(@NotNull String subdirName) {
            mySubdirName = subdirName;
        }

        @Override
        public VirtualFile fun(VirtualFile file) {
            return file == null || FileUtil.namesEqual(mySubdirName, file.getName()) ? file : file.findChild(mySubdirName);
        }
    }

    private abstract static class CachedImportPathProvider implements CachedValueProvider<String> {
        private final PsiDirectory myPsiDirectory;
        private final boolean myWithVendoring;

        public CachedImportPathProvider(@NotNull PsiDirectory psiDirectory, boolean vendoring) {
            myPsiDirectory = psiDirectory;
            myWithVendoring = vendoring;
        }

        @Nullable
        @Override
        public Result<String> compute() {
//            String path = getPathRelativeToSdkAndLibrariesAndVendor(myPsiDirectory);
//            Module module = ModuleUtilCore.findModuleForPsiElement(myPsiDirectory);
            return new Result<String>(null);
        }
    }

    private static class CachedImportPathProviderImpl extends CachedImportPathProvider {
        public CachedImportPathProviderImpl(@NotNull PsiDirectory psiDirectory) {
            super(psiDirectory, false);
        }
    }

    private static class CachedVendoredImportPathProvider extends CachedImportPathProvider {
        public CachedVendoredImportPathProvider(@NotNull PsiDirectory psiDirectory) {
            super(psiDirectory, true);
        }
    }

    @NotNull
    public static String adjustSdkPath(@NotNull String path) {
        return path;
    }
    @Nullable
    public static VirtualFile suggestSdkDirectory() {
        if (SystemInfo.isWindows) {
            return ObjectUtils.chooseNotNull(LocalFileSystem.getInstance().findFileByPath("C:\\Crystal"),
                    LocalFileSystem.getInstance().findFileByPath("C:\\cygwin"));
        }
        if (SystemInfo.isMac || SystemInfo.isLinux) {
            VirtualFile usrLocal = LocalFileSystem.getInstance().findFileByPath("/usr/local/crystal");
            if (usrLocal != null) return usrLocal;
        }
        if (SystemInfo.isMac) {
            String macPorts = "/opt/local/lib/crystal";
            String homeBrew = "/usr/local/Cellar/crystal";
            File file = FileUtil.findFirstThatExist(macPorts, homeBrew);
            if (file != null) {
                return LocalFileSystem.getInstance().findFileByIoFile(file);
            }
        }
        return null;
    }
}
