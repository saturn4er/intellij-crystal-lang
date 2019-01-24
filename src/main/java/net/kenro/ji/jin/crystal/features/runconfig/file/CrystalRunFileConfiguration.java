package net.kenro.ji.jin.crystal.features.runconfig.file;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.Executor;
import com.intellij.execution.configuration.EnvironmentVariablesComponent;
import com.intellij.execution.configurations.*;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.InvalidDataException;
import com.intellij.openapi.util.JDOMExternalizerUtil;
import com.intellij.openapi.util.WriteExternalException;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.vfs.VfsUtilCore;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileManager;
import com.intellij.util.containers.ContainerUtil;
import net.kenro.ji.jin.crystal.features.runconfig.file.ui.CrystalRunFileConfigurationEditorForm;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class CrystalRunFileConfiguration extends RunConfigurationBase {
    private static final String FILE = "myFile";
    private static final String WORKING_DIRECTORY_NAME = "working_directory";
    private static final String CRYSTAL_PARAMETERS_NAME = "crystal_parameters";
    private static final String PARAMETERS_NAME = "parameters";
    private static final String PASS_PARENT_ENV = "pass_parent_env";

    public void setFile(@NotNull String myFile) {
        this.myFile = myFile;
    }

    public void setWorkingDirectory(@NotNull String myWorkingDirectory) {
        this.myWorkingDirectory = myWorkingDirectory;
    }

    public void setCrystalParams(@NotNull String myCrystalParams) {
        this.myCrystalParams = myCrystalParams;
    }

    public void setParams(@NotNull String myParams) {
        this.myParams = myParams;
    }

    public void setPassParentEnvironment(boolean myPassParentEnvironment) {
        this.myPassParentEnvironment = myPassParentEnvironment;
    }

    public void setCustomEnvironment(Map<String, String> customEnvironment) {
        this.myCustomEnvironment = customEnvironment;
    }

    @NotNull
    private String myFile = "";
    @NotNull
    private String myWorkingDirectory = "";
    @NotNull
    private String myCrystalParams = "";

    @NotNull
    public String getFile() {
        return myFile;
    }

    @NotNull
    public String getWorkingDirectory() {
        return myWorkingDirectory;
    }

    @NotNull
    public String getCrystalParams() {
        return myCrystalParams;
    }

    @NotNull
    public String getParams() {
        return myParams;
    }

    @NotNull
    public Map<String, String> getCustomEnvironment() {
        return myCustomEnvironment;
    }

    public boolean isPassParentEnvironment() {
        return myPassParentEnvironment;
    }

    @NotNull
    private String myParams = "";
    @NotNull
    private Map<String, String> myCustomEnvironment = ContainerUtil.newHashMap();
    private boolean myPassParentEnvironment = true;

    public CrystalRunFileConfiguration(Project project, String name, @NotNull ConfigurationType configurationType) {
        super(project, configurationType.getConfigurationFactories()[0], name);
    }


    @NotNull
    @Override
    public SettingsEditor<? extends RunConfiguration> getConfigurationEditor() {
        return new CrystalRunFileConfigurationEditorForm(getProject());
    }

    @Override
    public void checkConfiguration() throws RuntimeConfigurationException {
//        super.checkBaseConfiguration();
//        super.checkFileConfiguration();
    }

    @Override
    public void readExternal(@NotNull Element element) throws InvalidDataException {
        super.readExternal(element);
        myFile = StringUtil.notNullize(JDOMExternalizerUtil.readCustomField(element, FILE));
        myCrystalParams = StringUtil.notNullize(JDOMExternalizerUtil.readCustomField(element, CRYSTAL_PARAMETERS_NAME));
        myParams = StringUtil.notNullize(JDOMExternalizerUtil.readCustomField(element, PARAMETERS_NAME));

        String workingDirectoryValue = JDOMExternalizerUtil.readCustomField(element, WORKING_DIRECTORY_NAME);
        if (workingDirectoryValue != null) {
            myWorkingDirectory = workingDirectoryValue;
        }
        EnvironmentVariablesComponent.readExternal(element, myCustomEnvironment);

        String passEnvValue = JDOMExternalizerUtil.readCustomField(element, PASS_PARENT_ENV);
        myPassParentEnvironment = passEnvValue == null || Boolean.valueOf(passEnvValue);
    }

    @Override
    public void writeExternal(@NotNull Element element) throws WriteExternalException {
        super.writeExternal(element);
        addNonEmptyElement(element, FILE, myFile);
        addNonEmptyElement(element, WORKING_DIRECTORY_NAME, myWorkingDirectory);
        addNonEmptyElement(element, CRYSTAL_PARAMETERS_NAME, myCrystalParams);
        addNonEmptyElement(element, PARAMETERS_NAME, myParams);
        if (!myCustomEnvironment.isEmpty()) {
            EnvironmentVariablesComponent.writeExternal(element, myCustomEnvironment);
        }
        if (!myPassParentEnvironment) {
            JDOMExternalizerUtil.writeCustomField(element, PASS_PARENT_ENV, "false");
        }
    }

    private void addNonEmptyElement(@NotNull Element element, @NotNull String attributeName, @Nullable String value) {
        if (StringUtil.isNotEmpty(value)) {
            JDOMExternalizerUtil.writeCustomField(element, attributeName, value);
        }
    }


    @Nullable
    protected VirtualFile findFile(@NotNull String filePath) {
        VirtualFile virtualFile = VirtualFileManager.getInstance().findFileByUrl(VfsUtilCore.pathToUrl(filePath));
        if (virtualFile == null) {
            String path = FileUtil.join(myWorkingDirectory, filePath);
            virtualFile = VirtualFileManager.getInstance().findFileByUrl(VfsUtilCore.pathToUrl(path));
        }
        return virtualFile;
    }

    @NotNull
    private CrystalRunFileRunningState newRunningState(@NotNull ExecutionEnvironment env, @NotNull String file) {
//        if (!"crystal".equals(PathUtil.getFileExtension(path))) {
//            VirtualFile f = LocalFileSystem.getInstance().refreshAndFindFileByPath(path);
//            if (f != null && f.getFileType() == ScratchFileType.INSTANCE) {
//                String suffixWithoutExt = "." + UUID.randomUUID().toString().substring(0, 4);
//                String suffix = suffixWithoutExt + ".cr";
//                String before = f.getName();
//                String beforeWithoutExt = FileUtil.getNameWithoutExtension(before);
//                ApplicationManager.getApplication().runWriteAction(new Runnable() {
//                    @Override
//                    public void run() {
//                        try {
//                            f.rename(this, before + suffix);
//                        } catch (IOException ignored) {
//                        }
//                    }
//                });
//                setFilePath(path + suffix);
//                setName(getName().replace(beforeWithoutExt, beforeWithoutExt + suffixWithoutExt));
//            }
//        }
        return new CrystalRunFileRunningState(env, file, this);
    }


    @Nullable
    @Override
    public RunProfileState getState(@NotNull Executor executor, @NotNull ExecutionEnvironment environment) throws ExecutionException {
        return newRunningState(environment, myFile);
    }
}
