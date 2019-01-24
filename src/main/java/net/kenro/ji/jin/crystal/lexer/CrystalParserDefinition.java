package net.kenro.ji.jin.crystal.lexer;


import com.intellij.lang.ASTNode;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.FlexAdapter;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.IStubFileElementType;
import com.intellij.psi.tree.TokenSet;
import net.kenro.ji.jin.crystal.CrystalParser;
import net.kenro.ji.jin.crystal.file.CrystalFile;
import net.kenro.ji.jin.crystal.file.CrystalFileStubType;
import net.kenro.ji.jin.crystal.parser._CrystalLexer;
import net.kenro.ji.jin.crystal.psi.CrystalElementTypes;
import org.jetbrains.annotations.NotNull;

public class CrystalParserDefinition implements ParserDefinition {

    public static final TokenSet WHITE_SPACES = TokenSet.create(TokenType.WHITE_SPACE);
    public static final TokenSet COMMENTS = TokenSet.create(CrystalElementTypes.LINE_COMMENT);
    public static final IStubFileElementType FILE_ELEMENT_TYPE = CrystalFileStubType.INSTANCE;

    @NotNull
    @Override
    public Lexer createLexer(Project project) {
        return new FlexAdapter(new _CrystalLexer(null));
    }

    @NotNull
    public TokenSet getWhitespaceTokens() {
        return WHITE_SPACES;
    }

    @NotNull
    public TokenSet getCommentTokens() {
        return COMMENTS;
    }

    @NotNull
    public TokenSet getStringLiteralElements() {
        return TokenSet.EMPTY;
    }

    @NotNull
    public PsiParser createParser(final Project project) {
        return new CrystalParser();
    }

    @Override
    public IFileElementType getFileNodeType() {
        return FILE_ELEMENT_TYPE;
    }

    public PsiFile createFile(FileViewProvider viewProvider) {
        return new CrystalFile(viewProvider);
    }

//    public ParserDefinition.SpaceRequirements spaceExistenceTypeBetweenTokens(ASTNode left, ASTNode right) {
//        return ParserDefinition.SpaceRequirements.MAY;
//    }

    @NotNull
    public PsiElement createElement(ASTNode node) {
        return CrystalElementTypes.Factory.createElement(node);
    }

}
