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

package net.kenro.ji.jin.crystal.actions.file;

import com.intellij.ide.actions.CreateFileFromTemplateAction;
import com.intellij.ide.actions.CreateFileFromTemplateDialog;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiDirectory;
import net.kenro.ji.jin.crystal.icons.CrystalIcons;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CrystalCreateFileAction extends CreateFileFromTemplateAction implements DumbAware {
    public static final String FILE_TEMPLATE = "Crystal File";

    private static final String NEW_CRYSTAL_FILE = "New CrystalIcons File";
    private static final String DEFAULT_CRYSTAL_TEMPLATE_PROPERTY = "DefaultCrystalTemplateProperty";

    public CrystalCreateFileAction() {
        super(NEW_CRYSTAL_FILE, "", CrystalIcons.ICON);
    }

    @Override
    protected void buildDialog(Project project, PsiDirectory directory, @NotNull CreateFileFromTemplateDialog.Builder builder) {
        builder.setTitle(NEW_CRYSTAL_FILE).addKind("Empty file", CrystalIcons.ICON, FILE_TEMPLATE);
    }

    @Nullable
    @Override
    protected String getDefaultTemplateProperty() {
        return DEFAULT_CRYSTAL_TEMPLATE_PROPERTY;
    }

    @NotNull
    @Override
    protected String getActionName(PsiDirectory directory, String newName, String templateName) {
        return NEW_CRYSTAL_FILE;
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof CrystalCreateFileAction;
    }
}