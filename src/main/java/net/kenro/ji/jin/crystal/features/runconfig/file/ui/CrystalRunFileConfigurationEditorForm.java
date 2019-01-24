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

import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import net.kenro.ji.jin.crystal.features.runconfig.file.CrystalRunFileConfiguration;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class CrystalRunFileConfigurationEditorForm extends SettingsEditor<CrystalRunFileConfiguration> {
  private JPanel myComponent;
  private TextFieldWithBrowseButton myFileField;
  private CrystalCommonSettingsPanel myCommonSettingsPanel;

  public CrystalRunFileConfigurationEditorForm(@NotNull Project project) {
    myCommonSettingsPanel.init(project);
//    CrystalRunUtil.installCrystalWithMainFileChooser(project, myFileField);
  }

  @Override
  protected void resetEditorFrom(CrystalRunFileConfiguration configuration) {
    myFileField.setText(configuration.getFile());
    myCommonSettingsPanel.resetEditorFrom(configuration);
  }

  @Override
  protected void applyEditorTo(CrystalRunFileConfiguration configuration) throws ConfigurationException {
    configuration.setFile(myFileField.getText());
    myCommonSettingsPanel.applyEditorTo(configuration);
  }

  @NotNull
  @Override
  protected JComponent createEditor() {
    return myComponent;
  }
}
