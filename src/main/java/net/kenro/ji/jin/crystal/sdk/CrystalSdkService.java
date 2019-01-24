package net.kenro.ji.jin.crystal.sdk;

import com.intellij.execution.configurations.PathEnvironmentVariableUtil;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Disposer;
import com.intellij.openapi.util.SimpleModificationTracker;
import com.intellij.openapi.util.SystemInfo;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.vfs.VfsUtilCore;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.util.containers.ContainerUtil;
import net.kenro.ji.jin.crystal.util.CrystalConstants;
import net.kenro.ji.jin.crystal.util.CrystalEnvironmentUtil;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.TestOnly;

import java.io.File;
import java.util.Set;

public abstract class CrystalSdkService extends SimpleModificationTracker {
    public static final Logger LOG = Logger.getInstance(CrystalSdkService.class);
    private static final Set<String> FEDORA_SUBDIRECTORIES = ContainerUtil.newHashSet("linux_amd64", "linux_386", "linux_arm");
    private static String ourTestSdkVersion;

    @NotNull
    protected final Project myProject;

    protected CrystalSdkService(@NotNull Project project) {
        myProject = project;
    }

    public static CrystalSdkService getInstance(@NotNull Project project) {
        return ServiceManager.getService(project, CrystalSdkService.class);
    }

    @Nullable
    public abstract String getSdkHomePath(@Nullable Module module);

    @NotNull
    public static String libraryRootToSdkPath(@NotNull VirtualFile root) {
        return VfsUtilCore.urlToPath(StringUtil.trimEnd(StringUtil.trimEnd(StringUtil.trimEnd(root.getUrl(), "src/pkg"), "src"), "/"));
    }

    @Nullable
    public String getSdkVersion(@Nullable Module module) {
        return ourTestSdkVersion;
    }


    public abstract void chooseAndSetSdk(@Nullable Module module);

    /**
     * Use this method in order to check whether the method is appropriate for providing Crystal-specific code insight
     */
    @Contract("null -> false")
    public boolean isCrystalModule(@Nullable Module module) {
        return true;
//        return module != null && !module.isDisposed();
    }

    @Nullable
    public Configurable createSdkConfigurable() {
        return null;
    }

    @Nullable
    public String getCrystalExecutablePath(@Nullable Module module) {
        return getCrystalExecutablePath(getSdkHomePath(module));
    }

    public static String getCrystalExecutablePath(@Nullable String sdkHomePath) {
        if (sdkHomePath != null) {
            File binDirectory = new File(sdkHomePath, "bin");
            if (!binDirectory.exists() && SystemInfo.isLinux) {
                LOG.debug(sdkHomePath + "/bin doesn't exist, checking linux-specific paths");
                // failed to define executable path in old linux and old Crystal
                File CrystalFromPath = PathEnvironmentVariableUtil.findInPath(CrystalConstants.EXECUTABLE_NAME);
                if (CrystalFromPath != null && CrystalFromPath.exists()) {
                    LOG.debug("Crystal executable found at " + CrystalFromPath.getAbsolutePath());
                    return CrystalFromPath.getAbsolutePath();
                }
            }

            String executableName = CrystalEnvironmentUtil.getBinaryFileNameForPath(CrystalConstants.EXECUTABLE_NAME);
            String executable = FileUtil.join(sdkHomePath, "bin", executableName);

            if (!new File(executable).exists() && SystemInfo.isLinux) {
                LOG.debug(executable + " doesn't exists. Looking for binaries in fedora-specific directories");
                // fedora
                for (String directory : FEDORA_SUBDIRECTORIES) {
                    File file = new File(binDirectory, directory);
                    if (file.exists() && file.isDirectory()) {
                        LOG.debug("Crystal executable found at " + file.getAbsolutePath());
                        return FileUtil.join(file.getAbsolutePath(), executableName);
                    }
                }
            }
            LOG.debug("Crystal executable found at " + executable);
            return executable;
        }
        return null;
    }

    @TestOnly
    public static void setTestingSdkVersion(@Nullable String version, @NotNull Disposable disposable) {
        ourTestSdkVersion = version;
        Disposer.register(disposable, () -> {
            //noinspection AssignmentToStaticFieldFromInstanceMethod
            ourTestSdkVersion = null;
        });
    }
}
