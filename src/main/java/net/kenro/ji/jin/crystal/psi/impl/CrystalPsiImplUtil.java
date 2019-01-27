package net.kenro.ji.jin.crystal.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.ElementManipulators;
import com.intellij.psi.impl.source.tree.LeafElement;
import com.intellij.psi.util.PsiTreeUtil;
import net.kenro.ji.jin.crystal.psi.CrystalRequirePath;
import net.kenro.ji.jin.crystal.psi.CrystalStringLiteral;
import net.kenro.ji.jin.crystal.util.CrystalStringLiteralEscaper;
import org.jetbrains.annotations.NotNull;

public class CrystalPsiImplUtil {
    @NotNull
    public static String getPath(@NotNull CrystalRequirePath o) {
        return "";
//        return o.getStringLiteral().getDecodedText();
    }

    @NotNull
    public static String getDecodedText(@NotNull CrystalStringLiteral o) {
        StringBuilder builder = new StringBuilder();
        TextRange range = ElementManipulators.getManipulator(o).getRangeInElement(o);
        o.createLiteralTextEscaper().decode(range, builder);
        return builder.toString();
    }

    @NotNull
    public static CrystalStringLiteralEscaper createLiteralTextEscaper(@NotNull CrystalStringLiteral o) {
        return new CrystalStringLiteralEscaper(o);
    }

    public static boolean isValidHost(@NotNull CrystalStringLiteral o) {
        return PsiTreeUtil.getParentOfType(o, CrystalRequirePath.class) == null;
    }

    @NotNull
    public static TextRange getPathTextRange(@NotNull CrystalRequirePath importString) {
        String text = importString.getText();
        return !text.isEmpty() && isQuote(text.charAt(0)) ? TextRange.create(1, text.length() - 1) : TextRange.EMPTY_RANGE;
    }

    private static boolean isQuote(char ch) {
        return ch == '"' || ch == '\'' || ch == '`';
    }

    @NotNull
    public static CrystalStringLiteralImpl updateText(@NotNull CrystalStringLiteral o, @NotNull String text) {
        if (text.length() > 2) {
            if (o.getText() != null) {
                StringBuilder outChars = new StringBuilder();
                CrystalStringLiteralEscaper.escapeString(text.substring(1, text.length() - 1), outChars);
                outChars.insert(0, '"');
                outChars.append('"');
                text = outChars.toString();
            }
        }

        ASTNode valueNode = o.getNode().getFirstChildNode();
        assert valueNode instanceof LeafElement;

        ((LeafElement)valueNode).replaceWithText(text);
        return (CrystalStringLiteralImpl)o;
    }
//

//    public static boolean isValidHost(@NotNull CrystalStringLiteral o) {
//        return PsiTreeUtil.getParentOfType(o, CrystalRequirePath.class) == null;
//    }
//
//    @NotNull
//    public static CrystalStringLiteralImpl updateText(@NotNull CrystalStringLiteral o, @NotNull String text) {
//        if (text.length() > 2) {
//            if (o.getString() != null) {
//                StringBuilder outChars = new StringBuilder();
//                CrystalStringLiteralEscaper.escapeString(text.substring(1, text.length() - 1), outChars);
//                outChars.insert(0, '"');
//                outChars.append('"');
//                text = outChars.toString();
//            }
//        }
//
//        ASTNode valueNode = o.getNode().getFirstChildNode();
//        assert valueNode instanceof LeafElement;
//
//        ((LeafElement) valueNode).replaceWithText(text);
//        return (CrystalStringLiteralImpl) o;
//    }

}
