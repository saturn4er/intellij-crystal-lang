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

package net.kenro.ji.jin.crystal.features.runconfig;

import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.ConfigurationType;
import com.intellij.execution.configurations.ConfigurationTypeBase;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.openapi.extensions.Extensions;
import com.intellij.openapi.project.Project;
import net.kenro.ji.jin.crystal.features.runconfig.file.CrystalRunFileConfiguration;
import net.kenro.ji.jin.crystal.features.runconfig.file.CrystalRunFileConfigurationType;
import net.kenro.ji.jin.crystal.util.CrystalConstants;
import org.jetbrains.annotations.NotNull;

public class CrystalRunFileConfigurationFactory extends ConfigurationFactory {
    public CrystalRunFileConfigurationFactory(ConfigurationType type) {
        super(type);
    }

    @Override
    @NotNull
    public RunConfiguration createTemplateConfiguration(@NotNull Project project) {
        CrystalRunFileConfigurationType instance = Extensions.findExtension(ConfigurationTypeBase.CONFIGURATION_TYPE_EP, CrystalRunFileConfigurationType.class);
        return new CrystalRunFileConfiguration(project, CrystalConstants.CRYSTAL, instance);
    }

}
