package net.kenro.ji.jin.crystal.features.completions;


import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.patterns.PsiElementPattern;
import com.intellij.psi.PsiElement;
import com.intellij.util.ProcessingContext;
import net.kenro.ji.jin.crystal.psi.CrystalElementTypes;
import net.kenro.ji.jin.crystal.psi.CrystalRequirePath;
import net.kenro.ji.jin.crystal.psi.CrystalRequireStatement;
import org.jetbrains.annotations.NotNull;

import static com.intellij.patterns.PlatformPatterns.psiElement;


public class RequireCompletionsContributor extends CompletionContributor {
    public RequireCompletionsContributor() {
        extend(CompletionType.BASIC,
                importString(),
                new CompletionProvider<CompletionParameters>() {
                    public void addCompletions(@NotNull CompletionParameters parameters,
                                               ProcessingContext context,
                                               @NotNull CompletionResultSet resultSet) {
                        resultSet.addElement(LookupElementBuilder.create("Hello"));
                    }
                }
        );
    }
    private static PsiElementPattern.Capture<PsiElement> importString() {
        return psiElement().withElementType(CrystalElementTypes.STRING_LITERAL).withParent(CrystalRequirePath.class)
                .withSuperParent(2, CrystalRequireStatement.class);
    }
}
