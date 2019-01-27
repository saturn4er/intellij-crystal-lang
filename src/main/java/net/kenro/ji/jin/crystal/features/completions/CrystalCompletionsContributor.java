package net.kenro.ji.jin.crystal.features.completions;


import com.intellij.codeInsight.completion.CompletionContributor;
import com.intellij.codeInsight.completion.CompletionType;
import com.intellij.patterns.PsiElementPattern;
import com.intellij.psi.PsiElement;
import net.kenro.ji.jin.crystal.psi.CrystalElementTypes;
import net.kenro.ji.jin.crystal.psi.CrystalRequirePath;
import net.kenro.ji.jin.crystal.psi.CrystalRequireStatement;

import static com.intellij.patterns.PlatformPatterns.psiElement;


public class CrystalCompletionsContributor extends CompletionContributor {
    public CrystalCompletionsContributor() {
        extend(CompletionType.BASIC, importString(), new CrystalImportPathsCompletionProvider());
    }

    private static PsiElementPattern.Capture<PsiElement> importString() {
        return psiElement().withElementType(CrystalElementTypes.STRING_LITERAL).withParent(CrystalRequirePath.class)
                .withSuperParent(2, CrystalRequireStatement.class);
    }
}
