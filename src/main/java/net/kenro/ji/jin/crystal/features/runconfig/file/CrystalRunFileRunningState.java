package net.kenro.ji.jin.crystal.features.runconfig.file;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.CommandLineState;
import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.execution.process.KillableColoredProcessHandler;
import com.intellij.execution.process.ProcessHandler;
import com.intellij.execution.process.ProcessTerminatedListener;
import com.intellij.execution.runners.ExecutionEnvironment;
import net.kenro.ji.jin.crystal.util.CrystalExecutor;
import org.jetbrains.annotations.NotNull;

public class CrystalRunFileRunningState extends CommandLineState {
    private CrystalRunFileConfiguration myConfiguration;

    CrystalRunFileRunningState(@NotNull ExecutionEnvironment env, @NotNull String file, CrystalRunFileConfiguration configuration) {
        super(env);
        myConfiguration = configuration;
    }

    private CrystalExecutor patchExecutor(@NotNull CrystalExecutor executor) throws ExecutionException {
        return executor
                .withParameters("run")
                .withParameterString(myConfiguration.getCrystalParams())
                .withParameters(myConfiguration.getFile());
    }

    @NotNull
    @Override
    protected ProcessHandler startProcess() throws ExecutionException {
        CrystalExecutor executor = patchExecutor(createCommonExecutor());
        GeneralCommandLine commandLine = executor.withParameterString(myConfiguration.getParams()).createCommandLine();
        KillableColoredProcessHandler handler = new KillableColoredProcessHandler(commandLine, true);
        ProcessTerminatedListener.attach(handler);
        return handler;
    }

    @NotNull
    public CrystalExecutor createCommonExecutor() {
        return CrystalExecutor.file(myConfiguration.getProject(), myConfiguration.getFile()).withWorkDirectory(myConfiguration.getWorkingDirectory())
                .withExtraEnvironment(myConfiguration.getCustomEnvironment())
                .withPassParentEnvironment(myConfiguration.isPassParentEnvironment());
    }

}
