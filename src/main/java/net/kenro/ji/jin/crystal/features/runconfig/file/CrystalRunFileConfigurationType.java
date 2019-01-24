package net.kenro.ji.jin.crystal.features.runconfig.file;

import com.intellij.execution.configurations.ConfigurationTypeBase;
import com.intellij.openapi.extensions.Extensions;
import net.kenro.ji.jin.crystal.features.runconfig.CrystalRunFileConfigurationFactory;
import net.kenro.ji.jin.crystal.icons.CrystalIcons;
import org.jetbrains.annotations.NotNull;


public class CrystalRunFileConfigurationType extends ConfigurationTypeBase {
    public CrystalRunFileConfigurationType() {
        super("CrystalRunFileConfiguration", "Crystal Single File", "Crystal single run file configuration", CrystalIcons.APPLICATION_RUN);
        addFactory(new CrystalRunFileConfigurationFactory(this));
    }

    @NotNull
    public static CrystalRunFileConfigurationType getInstance() {
        return Extensions.findExtension(CONFIGURATION_TYPE_EP, CrystalRunFileConfigurationType.class);
    }
}