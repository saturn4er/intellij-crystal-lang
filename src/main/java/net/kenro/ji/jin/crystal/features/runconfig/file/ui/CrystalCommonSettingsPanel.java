/*
 * Copyright 2013-2015 Sergey Ignatov, Alexander Zolotov, Florin Patan
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

package net.kenro.ji.jin.crystal.features.runconfig.file.ui;

import com.intellij.application.options.ModulesComboBox;
import com.intellij.execution.configuration.EnvironmentVariablesTextFieldWithBrowseButton;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.ui.RawCommandLineEditor;
import net.kenro.ji.jin.crystal.features.runconfig.CrystalRunUtil;
import net.kenro.ji.jin.crystal.features.runconfig.file.CrystalRunFileConfiguration;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class CrystalCommonSettingsPanel extends JPanel {
    private RawCommandLineEditor myCrystalToolParamsField;
    private RawCommandLineEditor myParamsField;
    private TextFieldWithBrowseButton myWorkingDirectoryField;
    private EnvironmentVariablesTextFieldWithBrowseButton myEnvironmentField;
    private ModulesComboBox myModulesComboBox;
    @SuppressWarnings("unused")
    private JPanel myRoot;

    public void init(@NotNull Project project) {
        CrystalRunUtil.installFileChooser(project, myWorkingDirectoryField, true);
        myCrystalToolParamsField.setDialogCaption("Crystal tool arguments");
        myParamsField.setDialogCaption("Program arguments");
    }

    public void resetEditorFrom(@NotNull CrystalRunFileConfiguration configuration) {
        myCrystalToolParamsField.setText(configuration.getCrystalParams());
        myParamsField.setText(configuration.getParams());
        myWorkingDirectoryField.setText(configuration.getWorkingDirectory());
        myEnvironmentField.setEnvs(configuration.getCustomEnvironment());
        myEnvironmentField.setPassParentEnvs(configuration.isPassParentEnvironment());
    }

    public void applyEditorTo(@NotNull CrystalRunFileConfiguration configuration) {
        configuration.setCrystalParams(myCrystalToolParamsField.getText());
        configuration.setParams(myParamsField.getText());
        configuration.setWorkingDirectory(myWorkingDirectoryField.getText());
        configuration.setCustomEnvironment(myEnvironmentField.getEnvs());
        configuration.setPassParentEnvironment(myEnvironmentField.isPassParentEnvs());
    }

    @Nullable
    public Module getSelectedModule() {
        return myModulesComboBox.getSelectedModule();
    }
}
