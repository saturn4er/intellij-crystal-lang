package net.kenro.ji.jin.crystal.util;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.ExecutionHelper;
import com.intellij.execution.ExecutionModes;
import com.intellij.execution.RunContentExecutor;
import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.execution.configurations.ParametersList;
import com.intellij.execution.configurations.PtyCommandLine;
import com.intellij.execution.process.*;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.progress.Task;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.projectRoots.Sdk;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.util.Disposer;
import com.intellij.openapi.util.Ref;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.vfs.CharsetToolkit;
import com.intellij.util.Consumer;
import com.intellij.util.EnvironmentUtil;
import com.intellij.util.ObjectUtils;
import com.intellij.util.containers.ContainerUtil;
import net.kenro.ji.jin.crystal.sdk.CrystalSDKUtil;
import net.kenro.ji.jin.crystal.sdk.CrystalSdkService;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

public class CrystalExecutor {
    private static final Logger LOGGER = Logger.getInstance(CrystalExecutor.class);
    @NotNull
    private final Map<String, String> myExtraEnvironment = ContainerUtil.newHashMap();
    @NotNull
    private final ParametersList myParameterList = new ParametersList();
    @NotNull
    private final ProcessOutput myProcessOutput = new ProcessOutput();
    @NotNull
    private final Project myProject;
    @Nullable
    private final String myFile;
    @Nullable
    private String myCrystalRoot;
    @Nullable
    private String myCrystalPath;
    @Nullable
    private String myEnvPath;
    @Nullable
    private String myWorkDirectory;
    private boolean myShowOutputOnError;
    private boolean myShowNotificationsOnError;
    private boolean myShowNotificationsOnSuccess;
    private boolean myShowCrystalEnvVariables = true;
    private GeneralCommandLine.ParentEnvironmentType myParentEnvironmentType = GeneralCommandLine.ParentEnvironmentType.CONSOLE;
    private boolean myPtyDisabled;
    @Nullable
    private String myExePath;
    @Nullable
    private String myPresentableName;
    private OSProcessHandler myProcessHandler;
    private final Collection<ProcessListener> myProcessListeners = ContainerUtil.newArrayList();

    private CrystalExecutor(@NotNull Project project, @Nullable String file) {
        myProject = project;
        myFile = file;
    }


    @NotNull
    public static CrystalExecutor file(Project project, @NotNull String file) {
        Collection<Module> modules = CrystalSDKUtil.getCrystalModules(project);
        Sdk sdk = ProjectRootManager.getInstance(project).getProjectSdk();

        return new CrystalExecutor(project, file)
                .withCrystalRoot(sdk.getHomePath())
                .withCrystalPath(CrystalSDKUtil.retrieveCrystalPath(project, null));
    }

    @NotNull
    public CrystalExecutor withPresentableName(@Nullable String presentableName) {
        myPresentableName = presentableName;
        return this;
    }

    @NotNull
    public CrystalExecutor withExePath(@Nullable String exePath) {
        myExePath = exePath;
        return this;
    }

    @NotNull
    public CrystalExecutor withWorkDirectory(@Nullable String workDirectory) {
        myWorkDirectory = workDirectory;
        return this;
    }

    @NotNull
    public CrystalExecutor withCrystalRoot(@Nullable String goRoot) {
        myCrystalRoot = goRoot;
        return this;
    }

    @NotNull
    public CrystalExecutor withCrystalPath(@Nullable String goPath) {
        myCrystalPath = goPath;
        return this;
    }

    @NotNull
    public CrystalExecutor withEnvPath(@Nullable String envPath) {
        myEnvPath = envPath;
        return this;
    }


    public CrystalExecutor withProcessListener(@NotNull ProcessListener listener) {
        myProcessListeners.add(listener);
        return this;
    }

    @NotNull
    public CrystalExecutor withExtraEnvironment(@NotNull Map<String, String> environment) {
        myExtraEnvironment.putAll(environment);
        return this;
    }

    @NotNull
    public CrystalExecutor withPassParentEnvironment(boolean passParentEnvironment) {
        myParentEnvironmentType = passParentEnvironment ? GeneralCommandLine.ParentEnvironmentType.CONSOLE
                : GeneralCommandLine.ParentEnvironmentType.NONE;
        return this;
    }

    @NotNull
    public CrystalExecutor withParameterString(@NotNull String parameterString) {
        myParameterList.addParametersString(parameterString);
        return this;
    }

    @NotNull
    public CrystalExecutor withParameters(@NotNull String... parameters) {
        myParameterList.addAll(parameters);
        return this;
    }

    public CrystalExecutor showCrystalEnvVariables(boolean show) {
        myShowCrystalEnvVariables = show;
        return this;
    }

    @NotNull
    public CrystalExecutor showOutputOnError() {
        myShowOutputOnError = true;
        return this;
    }

    @NotNull
    public CrystalExecutor disablePty() {
        myPtyDisabled = true;
        return this;
    }

    @NotNull
    public CrystalExecutor showNotifications(boolean onError, boolean onSuccess) {
        myShowNotificationsOnError = onError;
        myShowNotificationsOnSuccess = onSuccess;
        return this;
    }

    public boolean execute() {
        Logger.getInstance(getClass()).assertTrue(!ApplicationManager.getApplication().isDispatchThread(),
                "It's bad idea to run external tool on EDT");
        Logger.getInstance(getClass()).assertTrue(myProcessHandler == null, "Process has already run with this executor instance");
        Ref<Boolean> result = Ref.create(false);
        GeneralCommandLine commandLine = null;
        try {
            commandLine = createCommandLine();
            GeneralCommandLine finalCommandLine = commandLine;
            myProcessHandler = new KillableColoredProcessHandler(finalCommandLine, true) {
                @Override
                public void startNotify() {
                    if (myShowCrystalEnvVariables) {
//                        CrystalRunUtil.printCrystalEnvVariables(finalCommandLine, this);
                    }
                    super.startNotify();
                }
            };
            CrystalHistoryProcessListener historyProcessListener = new CrystalHistoryProcessListener();
            myProcessHandler.addProcessListener(historyProcessListener);
            for (ProcessListener listener : myProcessListeners) {
                myProcessHandler.addProcessListener(listener);
            }

            CapturingProcessAdapter processAdapter = new CapturingProcessAdapter(myProcessOutput) {
                @Override
                public void processTerminated(@NotNull ProcessEvent event) {
                    super.processTerminated(event);
                    boolean success = event.getExitCode() == 0 && myProcessOutput.getStderr().isEmpty();
                    boolean nothingToShow = myProcessOutput.getStdout().isEmpty() && myProcessOutput.getStderr().isEmpty();
                    boolean cancelledByUser = (event.getExitCode() == -1 || event.getExitCode() == 2) && nothingToShow;
                    result.set(success);
                    if (success) {
                        if (myShowNotificationsOnSuccess) {
                            showNotification("Finished successfully", NotificationType.INFORMATION);
                        }
                    } else if (cancelledByUser) {
                        if (myShowNotificationsOnError) {
                            showNotification("Interrupted", NotificationType.WARNING);
                        }
                    } else if (myShowOutputOnError) {
                        ApplicationManager.getApplication().invokeLater(() -> showOutput(myProcessHandler, historyProcessListener));
                    }
                }
            };

            myProcessHandler.addProcessListener(processAdapter);
            myProcessHandler.startNotify();
            ExecutionModes.SameThreadMode sameThreadMode = new ExecutionModes.SameThreadMode(getPresentableName());
            ExecutionHelper.executeExternalProcess(myProject, myProcessHandler, sameThreadMode, commandLine);

            LOGGER.debug("Finished `" + getPresentableName() + "` with result: " + result.get());
            return result.get();
        } catch (ExecutionException e) {
            if (myShowOutputOnError) {
                ExecutionHelper.showErrors(myProject, Collections.singletonList(e), getPresentableName(), null);
            }
            if (myShowNotificationsOnError) {
                showNotification(StringUtil.notNullize(e.getMessage(), "Unknown error, see logs for details"), NotificationType.ERROR);
            }
            String commandLineInfo = commandLine != null ? commandLine.getCommandLineString() : "not constructed";
            LOGGER.debug("Finished `" + getPresentableName() + "` with an exception. Commandline: " + commandLineInfo, e);
            return false;
        }
    }

    public void executeWithProgress(boolean modal) {
        //noinspection unchecked
        executeWithProgress(modal, Consumer.EMPTY_CONSUMER);
    }

    public void executeWithProgress(boolean modal, @NotNull Consumer<Boolean> consumer) {
        ProgressManager.getInstance().run(new Task.Backgroundable(myProject, getPresentableName(), true) {
            private boolean doNotStart;

            @Override
            public void onCancel() {
                doNotStart = true;
                ProcessHandler handler = getProcessHandler();
                if (handler != null) {
                    handler.destroyProcess();
                }
            }

            @Override
            public boolean shouldStartInBackground() {
                return !modal;
            }

            @Override
            public boolean isConditionalModal() {
                return modal;
            }

            @Override
            public void run(@NotNull ProgressIndicator indicator) {
                if (doNotStart || myProject == null || myProject.isDisposed()) {
                    return;
                }
                indicator.setIndeterminate(true);
                consumer.consume(execute());
            }
        });
    }

    @Nullable
    public ProcessHandler getProcessHandler() {
        return myProcessHandler;
    }

    private void showNotification(@NotNull String message, NotificationType type) {
        ApplicationManager.getApplication().invokeLater(() -> {
            String title = getPresentableName();
            Notifications.Bus.notify(CrystalConstants.GO_EXECUTION_NOTIFICATION_GROUP.createNotification(title, message, type, null), myProject);
        });
    }

    private void showOutput(@NotNull OSProcessHandler originalHandler, @NotNull CrystalHistoryProcessListener historyProcessListener) {
        if (myShowOutputOnError) {
            BaseOSProcessHandler outputHandler = new KillableColoredProcessHandler(originalHandler.getProcess(), null);
            RunContentExecutor runContentExecutor = new RunContentExecutor(myProject, outputHandler)
                    .withTitle(getPresentableName())
                    .withActivateToolWindow(myShowOutputOnError);
//                    .withFilter(new CrystalConsoleFilter(myProject, file, myWorkDirectory != null ? VfsUtilCore.pathToUrl(myWorkDirectory) : null));
            Disposer.register(myProject, runContentExecutor);
            runContentExecutor.run();
            historyProcessListener.apply(outputHandler);
        }
        if (myShowNotificationsOnError) {
            showNotification("Failed to run", NotificationType.ERROR);
        }
    }

    @NotNull
    public GeneralCommandLine createCommandLine() throws ExecutionException {
        if (myCrystalRoot == null) {
            throw new ExecutionException("Sdk is not set or Sdk home path is empty for module");
        }

        GeneralCommandLine commandLine = !myPtyDisabled && PtyCommandLine.isEnabled() ? new PtyCommandLine() : new GeneralCommandLine();
        commandLine.setExePath(ObjectUtils.notNull(myExePath, CrystalSdkService.getCrystalExecutablePath(myCrystalRoot)));
        commandLine.getEnvironment().putAll(myExtraEnvironment);

        Collection<String> paths = ContainerUtil.newArrayList();
        ContainerUtil.addIfNotNull(paths, StringUtil.nullize(commandLine.getEnvironment().get(CrystalConstants.PATH), true));
        ContainerUtil.addIfNotNull(paths, StringUtil.nullize(EnvironmentUtil.getValue(CrystalConstants.PATH), true));
        ContainerUtil.addIfNotNull(paths, StringUtil.nullize(myEnvPath, true));
        commandLine.getEnvironment().put(CrystalConstants.PATH, StringUtil.join(paths, File.pathSeparator));

        commandLine.withWorkDirectory(myWorkDirectory);
        commandLine.addParameters(myParameterList.getList());
        commandLine.withParentEnvironmentType(myParentEnvironmentType);
        commandLine.withCharset(CharsetToolkit.UTF8_CHARSET);
        return commandLine;
    }

    @NotNull
    private String getPresentableName() {
        return ObjectUtils.notNull(myPresentableName, "go");
    }
}
