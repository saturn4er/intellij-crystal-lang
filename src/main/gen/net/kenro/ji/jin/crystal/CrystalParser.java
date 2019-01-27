// This is a generated file. Not intended for manual editing.
package net.kenro.ji.jin.crystal;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static net.kenro.ji.jin.crystal.psi.CrystalElementTypes.*;
import static com.intellij.lang.parser.GeneratedParserUtilBase.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class CrystalParser implements PsiParser, LightPsiParser {

  public ASTNode parse(IElementType root_, PsiBuilder builder_) {
    parseLight(root_, builder_);
    return builder_.getTreeBuilt();
  }

  public void parseLight(IElementType root_, PsiBuilder builder_) {
    boolean result_;
    builder_ = adapt_builder_(root_, builder_, this, null);
    Marker marker_ = enter_section_(builder_, 0, _COLLAPSE_, null);
    if (root_ == ALIAS_EXPRESSION) {
      result_ = aliasExpression(builder_, 0);
    }
    else if (root_ == ARG_DECL) {
      result_ = argDecl(builder_, 0);
    }
    else if (root_ == ARG_LIST) {
      result_ = argList(builder_, 0);
    }
    else if (root_ == ASSOC) {
      result_ = assoc(builder_, 0);
    }
    else if (root_ == ASSOC_ARRAY) {
      result_ = assocArray(builder_, 0);
    }
    else if (root_ == BLOCK_VARIABLE) {
      result_ = blockVariable(builder_, 0);
    }
    else if (root_ == CALL) {
      result_ = call(builder_, 0);
    }
    else if (root_ == COMMAND) {
      result_ = command(builder_, 0);
    }
    else if (root_ == EXPRESSION) {
      result_ = expression(builder_, 0);
    }
    else if (root_ == FNAME) {
      result_ = fname(builder_, 0);
    }
    else if (root_ == FUNCTION) {
      result_ = function(builder_, 0);
    }
    else if (root_ == GLOBAL) {
      result_ = global(builder_, 0);
    }
    else if (root_ == LHS) {
      result_ = lhs(builder_, 0);
    }
    else if (root_ == LITERAL) {
      result_ = literal(builder_, 0);
    }
    else if (root_ == MLHS) {
      result_ = mlhs(builder_, 0);
    }
    else if (root_ == MLHS_ITEM) {
      result_ = mlhsItem(builder_, 0);
    }
    else if (root_ == MRHS) {
      result_ = mrhs(builder_, 0);
    }
    else if (root_ == OP_ASGN) {
      result_ = opAsgn(builder_, 0);
    }
    else if (root_ == PROC_TYPE) {
      result_ = procType(builder_, 0);
    }
    else if (root_ == REQUIRE_PATH) {
      result_ = requirePath(builder_, 0);
    }
    else if (root_ == REQUIRE_STATEMENT) {
      result_ = requireStatement(builder_, 0);
    }
    else if (root_ == SINGLETON) {
      result_ = singleton(builder_, 0);
    }
    else if (root_ == STRING_LITERAL) {
      result_ = stringLiteral(builder_, 0);
    }
    else if (root_ == SYMBOL) {
      result_ = symbol(builder_, 0);
    }
    else if (root_ == TOUPLE_TYPE) {
      result_ = toupleType(builder_, 0);
    }
    else if (root_ == TUPLE) {
      result_ = tuple(builder_, 0);
    }
    else if (root_ == TYPEE) {
      result_ = typee(builder_, 0);
    }
    else if (root_ == VAR_NAME) {
      result_ = varName(builder_, 0);
    }
    else if (root_ == VARIABLE) {
      result_ = variable(builder_, 0);
    }
    else if (root_ == WHEN_ARGS) {
      result_ = whenArgs(builder_, 0);
    }
    else {
      result_ = parse_root_(root_, builder_, 0);
    }
    exit_section_(builder_, 0, marker_, root_, result_, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType root_, PsiBuilder builder_, int level_) {
    return crystalFile(builder_, level_ + 1);
  }

  /* ********************************************************** */
  // 'alias' IDENT EQUAL type
  public static boolean aliasExpression(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "aliasExpression")) return false;
    if (!nextTokenIs(builder_, ALIAS)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, ALIAS, IDENT, EQUAL, TYPE);
    exit_section_(builder_, marker_, ALIAS_EXPRESSION, result_);
    return result_;
  }

  /* ********************************************************** */
  // lhs '=' arg
  //   | lhs opAsgn arg
  //   | ('..' | '...' | '+'  | '-'  | '*'  | '/'  | '%'  | '**'  | '|'  | '^'
  //     | '&' | '<=>' | '>'  | '>=' | '<'  | '<=' | '==' | '===' | '!=' | '='
  //     | '!' | '~'   | '<<' |  '>>'| '&&' | '||') arg
  //   | 'defined?' arg
  //   | primaries
  static boolean arg(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "arg")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = arg_0(builder_, level_ + 1);
    if (!result_) result_ = arg_1(builder_, level_ + 1);
    if (!result_) result_ = arg_2(builder_, level_ + 1);
    if (!result_) result_ = arg_3(builder_, level_ + 1);
    if (!result_) result_ = primaries(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // lhs '=' arg
  private static boolean arg_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "arg_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = lhs(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, EQUAL);
    result_ = result_ && arg(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // lhs opAsgn arg
  private static boolean arg_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "arg_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = lhs(builder_, level_ + 1);
    result_ = result_ && opAsgn(builder_, level_ + 1);
    result_ = result_ && arg(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // ('..' | '...' | '+'  | '-'  | '*'  | '/'  | '%'  | '**'  | '|'  | '^'
  //     | '&' | '<=>' | '>'  | '>=' | '<'  | '<=' | '==' | '===' | '!=' | '='
  //     | '!' | '~'   | '<<' |  '>>'| '&&' | '||') arg
  private static boolean arg_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "arg_2")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = arg_2_0(builder_, level_ + 1);
    result_ = result_ && arg(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // '..' | '...' | '+'  | '-'  | '*'  | '/'  | '%'  | '**'  | '|'  | '^'
  //     | '&' | '<=>' | '>'  | '>=' | '<'  | '<=' | '==' | '===' | '!=' | '='
  //     | '!' | '~'   | '<<' |  '>>'| '&&' | '||'
  private static boolean arg_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "arg_2_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, DOT_DOT);
    if (!result_) result_ = consumeToken(builder_, DOT_DOT_DOT);
    if (!result_) result_ = consumeToken(builder_, PLUS);
    if (!result_) result_ = consumeToken(builder_, MINUS);
    if (!result_) result_ = consumeToken(builder_, TIMES);
    if (!result_) result_ = consumeToken(builder_, DIVIDENTE);
    if (!result_) result_ = consumeToken(builder_, "%");
    if (!result_) result_ = consumeToken(builder_, TIMES_TIMES);
    if (!result_) result_ = consumeToken(builder_, PIPE);
    if (!result_) result_ = consumeToken(builder_, CAROT);
    if (!result_) result_ = consumeToken(builder_, AND);
    if (!result_) result_ = consumeToken(builder_, LEFT_RIGHT_ARROW);
    if (!result_) result_ = consumeToken(builder_, GREATER);
    if (!result_) result_ = consumeToken(builder_, GREATER_EQUAL);
    if (!result_) result_ = consumeToken(builder_, LESS);
    if (!result_) result_ = consumeToken(builder_, LEFT_ARROW);
    if (!result_) result_ = consumeToken(builder_, EQUAL_EQUAL);
    if (!result_) result_ = consumeToken(builder_, EQUAL_EQUAL_EQUAL);
    if (!result_) result_ = consumeToken(builder_, NOT_EQUAL);
    if (!result_) result_ = consumeToken(builder_, EQUAL);
    if (!result_) result_ = consumeToken(builder_, NOT);
    if (!result_) result_ = consumeToken(builder_, TILDE);
    if (!result_) result_ = consumeToken(builder_, INSERT);
    if (!result_) result_ = consumeToken(builder_, GREATER_GREATER);
    if (!result_) result_ = consumeToken(builder_, AND_AND);
    if (!result_) result_ = consumeToken(builder_, OR_OR);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // 'defined?' arg
  private static boolean arg_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "arg_3")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, "defined?");
    result_ = result_ && arg(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // '(' argList ')' | argList
  public static boolean argDecl(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "argDecl")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, ARG_DECL, "<arg decl>");
    result_ = argDecl_0(builder_, level_ + 1);
    if (!result_) result_ = argList(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // '(' argList ')'
  private static boolean argDecl_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "argDecl_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, LEFT_PAREN);
    result_ = result_ && argList(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RIGHT_PAREN);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // IDENT (',' IDENT)* [',' '*' [IDENT]][',' '&' IDENT]
  // | '*' IDENT [',' '&' IDENT]
  // | [ '&' IDENT]
  public static boolean argList(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "argList")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, ARG_LIST, "<arg list>");
    result_ = argList_0(builder_, level_ + 1);
    if (!result_) result_ = argList_1(builder_, level_ + 1);
    if (!result_) result_ = argList_2(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // IDENT (',' IDENT)* [',' '*' [IDENT]][',' '&' IDENT]
  private static boolean argList_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "argList_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, IDENT);
    result_ = result_ && argList_0_1(builder_, level_ + 1);
    result_ = result_ && argList_0_2(builder_, level_ + 1);
    result_ = result_ && argList_0_3(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (',' IDENT)*
  private static boolean argList_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "argList_0_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!argList_0_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "argList_0_1", pos_)) break;
    }
    return true;
  }

  // ',' IDENT
  private static boolean argList_0_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "argList_0_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, COMMA, IDENT);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // [',' '*' [IDENT]]
  private static boolean argList_0_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "argList_0_2")) return false;
    argList_0_2_0(builder_, level_ + 1);
    return true;
  }

  // ',' '*' [IDENT]
  private static boolean argList_0_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "argList_0_2_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, COMMA, TIMES);
    result_ = result_ && argList_0_2_0_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // [IDENT]
  private static boolean argList_0_2_0_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "argList_0_2_0_2")) return false;
    consumeToken(builder_, IDENT);
    return true;
  }

  // [',' '&' IDENT]
  private static boolean argList_0_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "argList_0_3")) return false;
    parseTokens(builder_, 0, COMMA, AND, IDENT);
    return true;
  }

  // '*' IDENT [',' '&' IDENT]
  private static boolean argList_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "argList_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, TIMES, IDENT);
    result_ = result_ && argList_1_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // [',' '&' IDENT]
  private static boolean argList_1_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "argList_1_2")) return false;
    parseTokens(builder_, 0, COMMA, AND, IDENT);
    return true;
  }

  // [ '&' IDENT]
  private static boolean argList_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "argList_2")) return false;
    parseTokens(builder_, 0, AND, IDENT);
    return true;
  }

  /* ********************************************************** */
  // arg [',' args]
  static boolean args(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "args")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = arg(builder_, level_ + 1);
    result_ = result_ && args_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // [',' args]
  private static boolean args_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "args_1")) return false;
    args_1_0(builder_, level_ + 1);
    return true;
  }

  // ',' args
  private static boolean args_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "args_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COMMA);
    result_ = result_ && args(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // arg '=>' arg
  public static boolean assoc(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "assoc")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, ASSOC, "<assoc>");
    result_ = arg(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RIGHT_ARROW);
    result_ = result_ && arg(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // '{' assocs '}'
  public static boolean assocArray(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "assocArray")) return false;
    if (!nextTokenIs(builder_, LEFT_BRACE)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, LEFT_BRACE);
    result_ = result_ && assocs(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RIGHT_BRACE);
    exit_section_(builder_, marker_, ASSOC_ARRAY, result_);
    return result_;
  }

  /* ********************************************************** */
  // assocArray [',' assocArrays]
  static boolean assocArrays(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "assocArrays")) return false;
    if (!nextTokenIs(builder_, LEFT_BRACE)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = assocArray(builder_, level_ + 1);
    result_ = result_ && assocArrays_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // [',' assocArrays]
  private static boolean assocArrays_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "assocArrays_1")) return false;
    assocArrays_1_0(builder_, level_ + 1);
    return true;
  }

  // ',' assocArrays
  private static boolean assocArrays_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "assocArrays_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COMMA);
    result_ = result_ && assocArrays(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // assoc [',' assocs]
  static boolean assocs(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "assocs")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = assoc(builder_, level_ + 1);
    result_ = result_ && assocs_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // [',' assocs]
  private static boolean assocs_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "assocs_1")) return false;
    assocs_1_0(builder_, level_ + 1);
    return true;
  }

  // ',' assocs
  private static boolean assocs_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "assocs_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COMMA);
    result_ = result_ && assocs(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // lhs | mlhs
  public static boolean blockVariable(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "blockVariable")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, BLOCK_VARIABLE, "<block variable>");
    result_ = lhs(builder_, level_ + 1);
    if (!result_) result_ = mlhs(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // function | command
  public static boolean call(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "call")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, CALL, "<call>");
    result_ = function(builder_, level_ + 1);
    if (!result_) result_ = command(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // args
  //        | args [',' assocs] [',' '*' args] [',' '&' arg]
  //        | assocs [',' '*' args] [',' '&' arg]
  //        | '*' arg [',' '&' arg] | '&' arg
  //        | command
  static boolean callArgs(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "callArgs")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = args(builder_, level_ + 1);
    if (!result_) result_ = callArgs_1(builder_, level_ + 1);
    if (!result_) result_ = callArgs_2(builder_, level_ + 1);
    if (!result_) result_ = callArgs_3(builder_, level_ + 1);
    if (!result_) result_ = callArgs_4(builder_, level_ + 1);
    if (!result_) result_ = command(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // args [',' assocs] [',' '*' args] [',' '&' arg]
  private static boolean callArgs_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "callArgs_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = args(builder_, level_ + 1);
    result_ = result_ && callArgs_1_1(builder_, level_ + 1);
    result_ = result_ && callArgs_1_2(builder_, level_ + 1);
    result_ = result_ && callArgs_1_3(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // [',' assocs]
  private static boolean callArgs_1_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "callArgs_1_1")) return false;
    callArgs_1_1_0(builder_, level_ + 1);
    return true;
  }

  // ',' assocs
  private static boolean callArgs_1_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "callArgs_1_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COMMA);
    result_ = result_ && assocs(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // [',' '*' args]
  private static boolean callArgs_1_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "callArgs_1_2")) return false;
    callArgs_1_2_0(builder_, level_ + 1);
    return true;
  }

  // ',' '*' args
  private static boolean callArgs_1_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "callArgs_1_2_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, COMMA, TIMES);
    result_ = result_ && args(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // [',' '&' arg]
  private static boolean callArgs_1_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "callArgs_1_3")) return false;
    callArgs_1_3_0(builder_, level_ + 1);
    return true;
  }

  // ',' '&' arg
  private static boolean callArgs_1_3_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "callArgs_1_3_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, COMMA, AND);
    result_ = result_ && arg(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // assocs [',' '*' args] [',' '&' arg]
  private static boolean callArgs_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "callArgs_2")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = assocs(builder_, level_ + 1);
    result_ = result_ && callArgs_2_1(builder_, level_ + 1);
    result_ = result_ && callArgs_2_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // [',' '*' args]
  private static boolean callArgs_2_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "callArgs_2_1")) return false;
    callArgs_2_1_0(builder_, level_ + 1);
    return true;
  }

  // ',' '*' args
  private static boolean callArgs_2_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "callArgs_2_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, COMMA, TIMES);
    result_ = result_ && args(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // [',' '&' arg]
  private static boolean callArgs_2_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "callArgs_2_2")) return false;
    callArgs_2_2_0(builder_, level_ + 1);
    return true;
  }

  // ',' '&' arg
  private static boolean callArgs_2_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "callArgs_2_2_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, COMMA, AND);
    result_ = result_ && arg(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // '*' arg [',' '&' arg]
  private static boolean callArgs_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "callArgs_3")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, TIMES);
    result_ = result_ && arg(builder_, level_ + 1);
    result_ = result_ && callArgs_3_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // [',' '&' arg]
  private static boolean callArgs_3_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "callArgs_3_2")) return false;
    callArgs_3_2_0(builder_, level_ + 1);
    return true;
  }

  // ',' '&' arg
  private static boolean callArgs_3_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "callArgs_3_2_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, COMMA, AND);
    result_ = result_ && arg(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // '&' arg
  private static boolean callArgs_4(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "callArgs_4")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, AND);
    result_ = result_ && arg(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // operation callArgs
  //          | primaries ('.' | '::') command
  //          | 'super' callArgs
  //          | 'loop'
  public static boolean command(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "command")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _COLLAPSE_, COMMAND, "<command>");
    result_ = command_0(builder_, level_ + 1);
    if (!result_) result_ = command_1(builder_, level_ + 1);
    if (!result_) result_ = command_2(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, LOOP);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // operation callArgs
  private static boolean command_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "command_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = operation(builder_, level_ + 1);
    result_ = result_ && callArgs(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // primaries ('.' | '::') command
  private static boolean command_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "command_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = primaries(builder_, level_ + 1);
    result_ = result_ && command_1_1(builder_, level_ + 1);
    result_ = result_ && command(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // '.' | '::'
  private static boolean command_1_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "command_1_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, DOT);
    if (!result_) result_ = consumeToken(builder_, NAMESPACE);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // 'super' callArgs
  private static boolean command_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "command_2")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, SUPER);
    result_ = result_ && callArgs(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // statements | expressions
  static boolean compositeStatement(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "compositeStatement")) return false;
    boolean result_;
    result_ = consumeToken(builder_, STATEMENTS);
    if (!result_) result_ = expressions(builder_, level_ + 1);
    return result_;
  }

  /* ********************************************************** */
  // item_*
  static boolean crystalFile(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "crystalFile")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!item_(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "crystalFile", pos_)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // [call] 'do' ['|' [blockVariable] '|'] [compositeStatement] 'end'
  //     | 'undef' fname
  //     | aliasExpression
  //     | 'while' expression
  //     | 'unless' expression
  //     | 'until' expression
  //     | requireStatement
  //     | 'begin' '{' compositeStatement '}'
  //     | 'end' '{' compositeStatement '}'
  //     | lhs '=' expression
  //     | 'if' expression
  //     | 'return' callArgs
  //     | 'yield' [callArgs]
  //     | 'and' expression
  //     | 'or' expression
  //     | 'not' expression
  //     | command
  //     | '!' command
  //     | arg
  public static boolean expression(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "expression")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _COLLAPSE_, EXPRESSION, "<expression>");
    result_ = expression_0(builder_, level_ + 1);
    if (!result_) result_ = expression_1(builder_, level_ + 1);
    if (!result_) result_ = aliasExpression(builder_, level_ + 1);
    if (!result_) result_ = expression_3(builder_, level_ + 1);
    if (!result_) result_ = expression_4(builder_, level_ + 1);
    if (!result_) result_ = expression_5(builder_, level_ + 1);
    if (!result_) result_ = requireStatement(builder_, level_ + 1);
    if (!result_) result_ = expression_7(builder_, level_ + 1);
    if (!result_) result_ = expression_8(builder_, level_ + 1);
    if (!result_) result_ = expression_9(builder_, level_ + 1);
    if (!result_) result_ = expression_10(builder_, level_ + 1);
    if (!result_) result_ = expression_11(builder_, level_ + 1);
    if (!result_) result_ = expression_12(builder_, level_ + 1);
    if (!result_) result_ = expression_13(builder_, level_ + 1);
    if (!result_) result_ = expression_14(builder_, level_ + 1);
    if (!result_) result_ = expression_15(builder_, level_ + 1);
    if (!result_) result_ = command(builder_, level_ + 1);
    if (!result_) result_ = expression_17(builder_, level_ + 1);
    if (!result_) result_ = arg(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // [call] 'do' ['|' [blockVariable] '|'] [compositeStatement] 'end'
  private static boolean expression_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "expression_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = expression_0_0(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, DO);
    result_ = result_ && expression_0_2(builder_, level_ + 1);
    result_ = result_ && expression_0_3(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, END);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // [call]
  private static boolean expression_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "expression_0_0")) return false;
    call(builder_, level_ + 1);
    return true;
  }

  // ['|' [blockVariable] '|']
  private static boolean expression_0_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "expression_0_2")) return false;
    expression_0_2_0(builder_, level_ + 1);
    return true;
  }

  // '|' [blockVariable] '|'
  private static boolean expression_0_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "expression_0_2_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, PIPE);
    result_ = result_ && expression_0_2_0_1(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, PIPE);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // [blockVariable]
  private static boolean expression_0_2_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "expression_0_2_0_1")) return false;
    blockVariable(builder_, level_ + 1);
    return true;
  }

  // [compositeStatement]
  private static boolean expression_0_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "expression_0_3")) return false;
    compositeStatement(builder_, level_ + 1);
    return true;
  }

  // 'undef' fname
  private static boolean expression_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "expression_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, "undef");
    result_ = result_ && fname(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // 'while' expression
  private static boolean expression_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "expression_3")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, "while");
    result_ = result_ && expression(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // 'unless' expression
  private static boolean expression_4(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "expression_4")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, "unless");
    result_ = result_ && expression(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // 'until' expression
  private static boolean expression_5(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "expression_5")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, UNTIL);
    result_ = result_ && expression(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // 'begin' '{' compositeStatement '}'
  private static boolean expression_7(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "expression_7")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, BEGIN, LEFT_BRACE);
    result_ = result_ && compositeStatement(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RIGHT_BRACE);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // 'end' '{' compositeStatement '}'
  private static boolean expression_8(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "expression_8")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, END, LEFT_BRACE);
    result_ = result_ && compositeStatement(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RIGHT_BRACE);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // lhs '=' expression
  private static boolean expression_9(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "expression_9")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = lhs(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, EQUAL);
    result_ = result_ && expression(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // 'if' expression
  private static boolean expression_10(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "expression_10")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, IF);
    result_ = result_ && expression(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // 'return' callArgs
  private static boolean expression_11(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "expression_11")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, RETURN);
    result_ = result_ && callArgs(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // 'yield' [callArgs]
  private static boolean expression_12(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "expression_12")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, YIELD);
    result_ = result_ && expression_12_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // [callArgs]
  private static boolean expression_12_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "expression_12_1")) return false;
    callArgs(builder_, level_ + 1);
    return true;
  }

  // 'and' expression
  private static boolean expression_13(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "expression_13")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, "and");
    result_ = result_ && expression(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // 'or' expression
  private static boolean expression_14(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "expression_14")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, "or");
    result_ = result_ && expression(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // 'not' expression
  private static boolean expression_15(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "expression_15")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, "not");
    result_ = result_ && expression(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // '!' command
  private static boolean expression_17(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "expression_17")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, NOT);
    result_ = result_ && command(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // expression [expressions]
  static boolean expressions(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "expressions")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = expression(builder_, level_ + 1);
    result_ = result_ && expressions_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // [expressions]
  private static boolean expressions_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "expressions_1")) return false;
    expressions(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // IDENT | '..' | '|' | '^'|'&'| '<=>' | '==' | '===' | '=~'  | '>' | '>=' | '<' | '<=' | '+' | '-' | '*' | '/'
  //              | '%'| '**' | '<<' | '>>' | '~' | '+@' | '-@' | '[]' | '[]='
  public static boolean fname(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "fname")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, FNAME, "<fname>");
    result_ = consumeToken(builder_, IDENT);
    if (!result_) result_ = consumeToken(builder_, DOT_DOT);
    if (!result_) result_ = consumeToken(builder_, PIPE);
    if (!result_) result_ = consumeToken(builder_, CAROT);
    if (!result_) result_ = consumeToken(builder_, AND);
    if (!result_) result_ = consumeToken(builder_, LEFT_RIGHT_ARROW);
    if (!result_) result_ = consumeToken(builder_, EQUAL_EQUAL);
    if (!result_) result_ = consumeToken(builder_, EQUAL_EQUAL_EQUAL);
    if (!result_) result_ = consumeToken(builder_, APROX_ARROW);
    if (!result_) result_ = consumeToken(builder_, GREATER);
    if (!result_) result_ = consumeToken(builder_, GREATER_EQUAL);
    if (!result_) result_ = consumeToken(builder_, LESS);
    if (!result_) result_ = consumeToken(builder_, LEFT_ARROW);
    if (!result_) result_ = consumeToken(builder_, PLUS);
    if (!result_) result_ = consumeToken(builder_, MINUS);
    if (!result_) result_ = consumeToken(builder_, TIMES);
    if (!result_) result_ = consumeToken(builder_, DIVIDENTE);
    if (!result_) result_ = consumeToken(builder_, "%");
    if (!result_) result_ = consumeToken(builder_, TIMES_TIMES);
    if (!result_) result_ = consumeToken(builder_, INSERT);
    if (!result_) result_ = consumeToken(builder_, GREATER_GREATER);
    if (!result_) result_ = consumeToken(builder_, TILDE);
    if (!result_) result_ = consumeToken(builder_, "+@");
    if (!result_) result_ = consumeToken(builder_, "-@");
    if (!result_) result_ = consumeToken(builder_, ARRAY);
    if (!result_) result_ = consumeToken(builder_, ARRAY_EQUAL);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // operation ['(' [callArgs] ')']
  //          | 'super' ['(' [callArgs] ')']
  //          | 'for' blockVariable 'in' expression 'do' compositeStatement 'end'
  //          | 'begin' compositeStatement {'rescue' [args] do compositeStatement} [('else' | 'ensure') compositeStatement] 'end'
  //          | 'class' IDENT ['<' IDENT]  [compositeStatement] 'end'
  //          | 'module' IDENT [compositeStatement] 'end'
  //          | 'def' fname argDecl [compositeStatement] 'end'
  //          | 'def' singleton ('.' | '::') fname argDecl compositeStatement 'end'
  public static boolean function(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "function")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _COLLAPSE_, FUNCTION, "<function>");
    result_ = function_0(builder_, level_ + 1);
    if (!result_) result_ = function_1(builder_, level_ + 1);
    if (!result_) result_ = function_2(builder_, level_ + 1);
    if (!result_) result_ = function_3(builder_, level_ + 1);
    if (!result_) result_ = function_4(builder_, level_ + 1);
    if (!result_) result_ = function_5(builder_, level_ + 1);
    if (!result_) result_ = function_6(builder_, level_ + 1);
    if (!result_) result_ = function_7(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // operation ['(' [callArgs] ')']
  private static boolean function_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "function_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = operation(builder_, level_ + 1);
    result_ = result_ && function_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // ['(' [callArgs] ')']
  private static boolean function_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "function_0_1")) return false;
    function_0_1_0(builder_, level_ + 1);
    return true;
  }

  // '(' [callArgs] ')'
  private static boolean function_0_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "function_0_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, LEFT_PAREN);
    result_ = result_ && function_0_1_0_1(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RIGHT_PAREN);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // [callArgs]
  private static boolean function_0_1_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "function_0_1_0_1")) return false;
    callArgs(builder_, level_ + 1);
    return true;
  }

  // 'super' ['(' [callArgs] ')']
  private static boolean function_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "function_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, SUPER);
    result_ = result_ && function_1_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // ['(' [callArgs] ')']
  private static boolean function_1_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "function_1_1")) return false;
    function_1_1_0(builder_, level_ + 1);
    return true;
  }

  // '(' [callArgs] ')'
  private static boolean function_1_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "function_1_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, LEFT_PAREN);
    result_ = result_ && function_1_1_0_1(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RIGHT_PAREN);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // [callArgs]
  private static boolean function_1_1_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "function_1_1_0_1")) return false;
    callArgs(builder_, level_ + 1);
    return true;
  }

  // 'for' blockVariable 'in' expression 'do' compositeStatement 'end'
  private static boolean function_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "function_2")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, FOR);
    result_ = result_ && blockVariable(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, IN);
    result_ = result_ && expression(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, DO);
    result_ = result_ && compositeStatement(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, END);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // 'begin' compositeStatement {'rescue' [args] do compositeStatement} [('else' | 'ensure') compositeStatement] 'end'
  private static boolean function_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "function_3")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, BEGIN);
    result_ = result_ && compositeStatement(builder_, level_ + 1);
    result_ = result_ && function_3_2(builder_, level_ + 1);
    result_ = result_ && function_3_3(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, END);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // 'rescue' [args] do compositeStatement
  private static boolean function_3_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "function_3_2")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, RESCUE);
    result_ = result_ && function_3_2_1(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, DO);
    result_ = result_ && compositeStatement(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // [args]
  private static boolean function_3_2_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "function_3_2_1")) return false;
    args(builder_, level_ + 1);
    return true;
  }

  // [('else' | 'ensure') compositeStatement]
  private static boolean function_3_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "function_3_3")) return false;
    function_3_3_0(builder_, level_ + 1);
    return true;
  }

  // ('else' | 'ensure') compositeStatement
  private static boolean function_3_3_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "function_3_3_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = function_3_3_0_0(builder_, level_ + 1);
    result_ = result_ && compositeStatement(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // 'else' | 'ensure'
  private static boolean function_3_3_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "function_3_3_0_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, ELSE);
    if (!result_) result_ = consumeToken(builder_, ENSURE);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // 'class' IDENT ['<' IDENT]  [compositeStatement] 'end'
  private static boolean function_4(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "function_4")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, CLASS, IDENT);
    result_ = result_ && function_4_2(builder_, level_ + 1);
    result_ = result_ && function_4_3(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, END);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // ['<' IDENT]
  private static boolean function_4_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "function_4_2")) return false;
    parseTokens(builder_, 0, LESS, IDENT);
    return true;
  }

  // [compositeStatement]
  private static boolean function_4_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "function_4_3")) return false;
    compositeStatement(builder_, level_ + 1);
    return true;
  }

  // 'module' IDENT [compositeStatement] 'end'
  private static boolean function_5(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "function_5")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, MODULE, IDENT);
    result_ = result_ && function_5_2(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, END);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // [compositeStatement]
  private static boolean function_5_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "function_5_2")) return false;
    compositeStatement(builder_, level_ + 1);
    return true;
  }

  // 'def' fname argDecl [compositeStatement] 'end'
  private static boolean function_6(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "function_6")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, DEF);
    result_ = result_ && fname(builder_, level_ + 1);
    result_ = result_ && argDecl(builder_, level_ + 1);
    result_ = result_ && function_6_3(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, END);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // [compositeStatement]
  private static boolean function_6_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "function_6_3")) return false;
    compositeStatement(builder_, level_ + 1);
    return true;
  }

  // 'def' singleton ('.' | '::') fname argDecl compositeStatement 'end'
  private static boolean function_7(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "function_7")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, DEF);
    result_ = result_ && singleton(builder_, level_ + 1);
    result_ = result_ && function_7_2(builder_, level_ + 1);
    result_ = result_ && fname(builder_, level_ + 1);
    result_ = result_ && argDecl(builder_, level_ + 1);
    result_ = result_ && compositeStatement(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, END);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // '.' | '::'
  private static boolean function_7_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "function_7_2")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, DOT);
    if (!result_) result_ = consumeToken(builder_, NAMESPACE);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // '$' IDENT
  public static boolean global(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "global")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, GLOBAL, "<global>");
    result_ = consumeToken(builder_, "$");
    result_ = result_ && consumeToken(builder_, IDENT);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // statements | expressions
  static boolean item_(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "item_")) return false;
    boolean result_;
    result_ = consumeToken(builder_, STATEMENTS);
    if (!result_) result_ = expressions(builder_, level_ + 1);
    return result_;
  }

  /* ********************************************************** */
  // variable
  //    | primaries '[' [args] ']'
  //    | primaries '.' IDENT
  public static boolean lhs(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "lhs")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, LHS, "<lhs>");
    result_ = variable(builder_, level_ + 1);
    if (!result_) result_ = lhs_1(builder_, level_ + 1);
    if (!result_) result_ = lhs_2(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // primaries '[' [args] ']'
  private static boolean lhs_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "lhs_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = primaries(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, LEFT_BRACKET);
    result_ = result_ && lhs_1_2(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RIGHT_BRACKET);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // [args]
  private static boolean lhs_1_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "lhs_1_2")) return false;
    args(builder_, level_ + 1);
    return true;
  }

  // primaries '.' IDENT
  private static boolean lhs_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "lhs_2")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = primaries(builder_, level_ + 1);
    result_ = result_ && consumeTokens(builder_, 0, DOT, IDENT);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // NIL
  //                     | TRUE
  //                     | FALSE
  //                     | NUMBER_LITERAL
  //                     | CHAR_LITERAL
  //                     | STRING
  //                     | symbol
  public static boolean literal(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "literal")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, LITERAL, "<literal>");
    result_ = consumeToken(builder_, NIL);
    if (!result_) result_ = consumeToken(builder_, TRUE);
    if (!result_) result_ = consumeToken(builder_, FALSE);
    if (!result_) result_ = consumeToken(builder_, NUMBER_LITERAL);
    if (!result_) result_ = consumeToken(builder_, CHAR_LITERAL);
    if (!result_) result_ = consumeToken(builder_, STRING);
    if (!result_) result_ = symbol(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // mlhsItem ',' [mlhsItem (',' mlhsItem)*] [ '*' lhs] | '*' lhs
  public static boolean mlhs(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "mlhs")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, MLHS, "<mlhs>");
    result_ = mlhs_0(builder_, level_ + 1);
    if (!result_) result_ = mlhs_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // mlhsItem ',' [mlhsItem (',' mlhsItem)*] [ '*' lhs]
  private static boolean mlhs_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "mlhs_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = mlhsItem(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, COMMA);
    result_ = result_ && mlhs_0_2(builder_, level_ + 1);
    result_ = result_ && mlhs_0_3(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // [mlhsItem (',' mlhsItem)*]
  private static boolean mlhs_0_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "mlhs_0_2")) return false;
    mlhs_0_2_0(builder_, level_ + 1);
    return true;
  }

  // mlhsItem (',' mlhsItem)*
  private static boolean mlhs_0_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "mlhs_0_2_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = mlhsItem(builder_, level_ + 1);
    result_ = result_ && mlhs_0_2_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (',' mlhsItem)*
  private static boolean mlhs_0_2_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "mlhs_0_2_0_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!mlhs_0_2_0_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "mlhs_0_2_0_1", pos_)) break;
    }
    return true;
  }

  // ',' mlhsItem
  private static boolean mlhs_0_2_0_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "mlhs_0_2_0_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COMMA);
    result_ = result_ && mlhsItem(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // [ '*' lhs]
  private static boolean mlhs_0_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "mlhs_0_3")) return false;
    mlhs_0_3_0(builder_, level_ + 1);
    return true;
  }

  // '*' lhs
  private static boolean mlhs_0_3_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "mlhs_0_3_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, TIMES);
    result_ = result_ && lhs(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // '*' lhs
  private static boolean mlhs_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "mlhs_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, TIMES);
    result_ = result_ && lhs(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // lhs | '(' mlhs ')'
  public static boolean mlhsItem(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "mlhsItem")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, MLHS_ITEM, "<mlhs item>");
    result_ = lhs(builder_, level_ + 1);
    if (!result_) result_ = mlhsItem_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // '(' mlhs ')'
  private static boolean mlhsItem_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "mlhsItem_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, LEFT_PAREN);
    result_ = result_ && mlhs(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RIGHT_PAREN);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // args [',' '*' arg] | '*' arg
  public static boolean mrhs(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "mrhs")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, MRHS, "<mrhs>");
    result_ = mrhs_0(builder_, level_ + 1);
    if (!result_) result_ = mrhs_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // args [',' '*' arg]
  private static boolean mrhs_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "mrhs_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = args(builder_, level_ + 1);
    result_ = result_ && mrhs_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // [',' '*' arg]
  private static boolean mrhs_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "mrhs_0_1")) return false;
    mrhs_0_1_0(builder_, level_ + 1);
    return true;
  }

  // ',' '*' arg
  private static boolean mrhs_0_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "mrhs_0_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, COMMA, TIMES);
    result_ = result_ && arg(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // '*' arg
  private static boolean mrhs_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "mrhs_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, TIMES);
    result_ = result_ && arg(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // '+='|'-='|'*='|'/='|'%='|'**=' |'&='|'|='|'ˆ='|'<<='|'>>=' |'&&='|'||='
  public static boolean opAsgn(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "opAsgn")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, OP_ASGN, "<op asgn>");
    result_ = consumeToken(builder_, PLUS_EQUAL);
    if (!result_) result_ = consumeToken(builder_, MINUS_EQUAL);
    if (!result_) result_ = consumeToken(builder_, TIMES_EQUAL);
    if (!result_) result_ = consumeToken(builder_, DIVIDENTE_EQUAL);
    if (!result_) result_ = consumeToken(builder_, MODULO_EQUAL);
    if (!result_) result_ = consumeToken(builder_, TIMES_TIMES_EQUAL);
    if (!result_) result_ = consumeToken(builder_, AND_EQUAL);
    if (!result_) result_ = consumeToken(builder_, OR_EQUAL);
    if (!result_) result_ = consumeToken(builder_, "ˆ=");
    if (!result_) result_ = consumeToken(builder_, LEFT_LEFT_ARROW);
    if (!result_) result_ = consumeToken(builder_, GREATER_GREATER_EQUAL);
    if (!result_) result_ = consumeToken(builder_, AND_AND_EQUAL);
    if (!result_) result_ = consumeToken(builder_, OR_OR_EQUAL);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // (IDENT ['!' | '?']) | NEW | PRINT
  static boolean operation(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "operation")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = operation_0(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, NEW);
    if (!result_) result_ = consumeToken(builder_, PRINT);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // IDENT ['!' | '?']
  private static boolean operation_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "operation_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, IDENT);
    result_ = result_ && operation_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // ['!' | '?']
  private static boolean operation_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "operation_0_1")) return false;
    operation_0_1_0(builder_, level_ + 1);
    return true;
  }

  // '!' | '?'
  private static boolean operation_0_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "operation_0_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, NOT);
    if (!result_) result_ = consumeToken(builder_, QUESTION);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // primary [primaries]
  static boolean primaries(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "primaries")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = primary(builder_, level_ + 1);
    result_ = result_ && primaries_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // [primaries]
  private static boolean primaries_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "primaries_1")) return false;
    primaries(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // '(' compositeStatement ')'
  // | literal
  // | variable
  // | '::' IDENT
  // | '[' [args] ']' [QUESTION]
  // | '[' [args [',']] ']' [QUESTION]
  // | assocArrays
  // | tuples
  // | 'return' ['(' [callArgs] ')']
  // | 'yield' ['(' [callArgs] ')']
  // | 'defined?' '(' arg ')'
  // | ('.' | '::') operation ['(' [callArgs] ')']
  // | function
  // | function '{' ['|' [blockVariable] '|'] compositeStatement '}'
  // | 'if' expression 'then' compositeStatement ['elsif' expression 'then' compositeStatement] ['else' compositeStatement] 'end'
  // | 'unless' expression 'then' compositeStatement ['else' compositeStatement] 'end'
  // | 'while' expression 'do' compositeStatement 'end'
  // | 'until' expression 'do' compositeStatement 'end'
  // | ('case'| 'select') [compositeStatement] 'when' whenArgs 'then' compositeStatement ['when' whenArgs 'then' compositeStatement] ['else' compositeStatement] 'end'
  // | 'puts' args
  // | 'break'
  static boolean primary(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "primary")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = primary_0(builder_, level_ + 1);
    if (!result_) result_ = literal(builder_, level_ + 1);
    if (!result_) result_ = variable(builder_, level_ + 1);
    if (!result_) result_ = parseTokens(builder_, 0, NAMESPACE, IDENT);
    if (!result_) result_ = primary_4(builder_, level_ + 1);
    if (!result_) result_ = primary_5(builder_, level_ + 1);
    if (!result_) result_ = assocArrays(builder_, level_ + 1);
    if (!result_) result_ = tuples(builder_, level_ + 1);
    if (!result_) result_ = primary_8(builder_, level_ + 1);
    if (!result_) result_ = primary_9(builder_, level_ + 1);
    if (!result_) result_ = primary_10(builder_, level_ + 1);
    if (!result_) result_ = primary_11(builder_, level_ + 1);
    if (!result_) result_ = function(builder_, level_ + 1);
    if (!result_) result_ = primary_13(builder_, level_ + 1);
    if (!result_) result_ = primary_14(builder_, level_ + 1);
    if (!result_) result_ = primary_15(builder_, level_ + 1);
    if (!result_) result_ = primary_16(builder_, level_ + 1);
    if (!result_) result_ = primary_17(builder_, level_ + 1);
    if (!result_) result_ = primary_18(builder_, level_ + 1);
    if (!result_) result_ = primary_19(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, BREAK);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // '(' compositeStatement ')'
  private static boolean primary_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "primary_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, LEFT_PAREN);
    result_ = result_ && compositeStatement(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RIGHT_PAREN);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // '[' [args] ']' [QUESTION]
  private static boolean primary_4(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "primary_4")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, LEFT_BRACKET);
    result_ = result_ && primary_4_1(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RIGHT_BRACKET);
    result_ = result_ && primary_4_3(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // [args]
  private static boolean primary_4_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "primary_4_1")) return false;
    args(builder_, level_ + 1);
    return true;
  }

  // [QUESTION]
  private static boolean primary_4_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "primary_4_3")) return false;
    consumeToken(builder_, QUESTION);
    return true;
  }

  // '[' [args [',']] ']' [QUESTION]
  private static boolean primary_5(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "primary_5")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, LEFT_BRACKET);
    result_ = result_ && primary_5_1(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RIGHT_BRACKET);
    result_ = result_ && primary_5_3(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // [args [',']]
  private static boolean primary_5_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "primary_5_1")) return false;
    primary_5_1_0(builder_, level_ + 1);
    return true;
  }

  // args [',']
  private static boolean primary_5_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "primary_5_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = args(builder_, level_ + 1);
    result_ = result_ && primary_5_1_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // [',']
  private static boolean primary_5_1_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "primary_5_1_0_1")) return false;
    consumeToken(builder_, COMMA);
    return true;
  }

  // [QUESTION]
  private static boolean primary_5_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "primary_5_3")) return false;
    consumeToken(builder_, QUESTION);
    return true;
  }

  // 'return' ['(' [callArgs] ')']
  private static boolean primary_8(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "primary_8")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, RETURN);
    result_ = result_ && primary_8_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // ['(' [callArgs] ')']
  private static boolean primary_8_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "primary_8_1")) return false;
    primary_8_1_0(builder_, level_ + 1);
    return true;
  }

  // '(' [callArgs] ')'
  private static boolean primary_8_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "primary_8_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, LEFT_PAREN);
    result_ = result_ && primary_8_1_0_1(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RIGHT_PAREN);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // [callArgs]
  private static boolean primary_8_1_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "primary_8_1_0_1")) return false;
    callArgs(builder_, level_ + 1);
    return true;
  }

  // 'yield' ['(' [callArgs] ')']
  private static boolean primary_9(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "primary_9")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, YIELD);
    result_ = result_ && primary_9_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // ['(' [callArgs] ')']
  private static boolean primary_9_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "primary_9_1")) return false;
    primary_9_1_0(builder_, level_ + 1);
    return true;
  }

  // '(' [callArgs] ')'
  private static boolean primary_9_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "primary_9_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, LEFT_PAREN);
    result_ = result_ && primary_9_1_0_1(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RIGHT_PAREN);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // [callArgs]
  private static boolean primary_9_1_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "primary_9_1_0_1")) return false;
    callArgs(builder_, level_ + 1);
    return true;
  }

  // 'defined?' '(' arg ')'
  private static boolean primary_10(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "primary_10")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, "defined?");
    result_ = result_ && consumeToken(builder_, LEFT_PAREN);
    result_ = result_ && arg(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RIGHT_PAREN);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // ('.' | '::') operation ['(' [callArgs] ')']
  private static boolean primary_11(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "primary_11")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = primary_11_0(builder_, level_ + 1);
    result_ = result_ && operation(builder_, level_ + 1);
    result_ = result_ && primary_11_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // '.' | '::'
  private static boolean primary_11_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "primary_11_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, DOT);
    if (!result_) result_ = consumeToken(builder_, NAMESPACE);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // ['(' [callArgs] ')']
  private static boolean primary_11_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "primary_11_2")) return false;
    primary_11_2_0(builder_, level_ + 1);
    return true;
  }

  // '(' [callArgs] ')'
  private static boolean primary_11_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "primary_11_2_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, LEFT_PAREN);
    result_ = result_ && primary_11_2_0_1(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RIGHT_PAREN);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // [callArgs]
  private static boolean primary_11_2_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "primary_11_2_0_1")) return false;
    callArgs(builder_, level_ + 1);
    return true;
  }

  // function '{' ['|' [blockVariable] '|'] compositeStatement '}'
  private static boolean primary_13(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "primary_13")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = function(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, LEFT_BRACE);
    result_ = result_ && primary_13_2(builder_, level_ + 1);
    result_ = result_ && compositeStatement(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RIGHT_BRACE);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // ['|' [blockVariable] '|']
  private static boolean primary_13_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "primary_13_2")) return false;
    primary_13_2_0(builder_, level_ + 1);
    return true;
  }

  // '|' [blockVariable] '|'
  private static boolean primary_13_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "primary_13_2_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, PIPE);
    result_ = result_ && primary_13_2_0_1(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, PIPE);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // [blockVariable]
  private static boolean primary_13_2_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "primary_13_2_0_1")) return false;
    blockVariable(builder_, level_ + 1);
    return true;
  }

  // 'if' expression 'then' compositeStatement ['elsif' expression 'then' compositeStatement] ['else' compositeStatement] 'end'
  private static boolean primary_14(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "primary_14")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, IF);
    result_ = result_ && expression(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, THEN);
    result_ = result_ && compositeStatement(builder_, level_ + 1);
    result_ = result_ && primary_14_4(builder_, level_ + 1);
    result_ = result_ && primary_14_5(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, END);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // ['elsif' expression 'then' compositeStatement]
  private static boolean primary_14_4(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "primary_14_4")) return false;
    primary_14_4_0(builder_, level_ + 1);
    return true;
  }

  // 'elsif' expression 'then' compositeStatement
  private static boolean primary_14_4_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "primary_14_4_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, ELSIF);
    result_ = result_ && expression(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, THEN);
    result_ = result_ && compositeStatement(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // ['else' compositeStatement]
  private static boolean primary_14_5(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "primary_14_5")) return false;
    primary_14_5_0(builder_, level_ + 1);
    return true;
  }

  // 'else' compositeStatement
  private static boolean primary_14_5_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "primary_14_5_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, ELSE);
    result_ = result_ && compositeStatement(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // 'unless' expression 'then' compositeStatement ['else' compositeStatement] 'end'
  private static boolean primary_15(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "primary_15")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, "unless");
    result_ = result_ && expression(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, THEN);
    result_ = result_ && compositeStatement(builder_, level_ + 1);
    result_ = result_ && primary_15_4(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, END);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // ['else' compositeStatement]
  private static boolean primary_15_4(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "primary_15_4")) return false;
    primary_15_4_0(builder_, level_ + 1);
    return true;
  }

  // 'else' compositeStatement
  private static boolean primary_15_4_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "primary_15_4_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, ELSE);
    result_ = result_ && compositeStatement(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // 'while' expression 'do' compositeStatement 'end'
  private static boolean primary_16(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "primary_16")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, "while");
    result_ = result_ && expression(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, DO);
    result_ = result_ && compositeStatement(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, END);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // 'until' expression 'do' compositeStatement 'end'
  private static boolean primary_17(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "primary_17")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, UNTIL);
    result_ = result_ && expression(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, DO);
    result_ = result_ && compositeStatement(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, END);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // ('case'| 'select') [compositeStatement] 'when' whenArgs 'then' compositeStatement ['when' whenArgs 'then' compositeStatement] ['else' compositeStatement] 'end'
  private static boolean primary_18(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "primary_18")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = primary_18_0(builder_, level_ + 1);
    result_ = result_ && primary_18_1(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, WHEN);
    result_ = result_ && whenArgs(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, THEN);
    result_ = result_ && compositeStatement(builder_, level_ + 1);
    result_ = result_ && primary_18_6(builder_, level_ + 1);
    result_ = result_ && primary_18_7(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, END);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // 'case'| 'select'
  private static boolean primary_18_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "primary_18_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, CASE);
    if (!result_) result_ = consumeToken(builder_, SELECT);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // [compositeStatement]
  private static boolean primary_18_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "primary_18_1")) return false;
    compositeStatement(builder_, level_ + 1);
    return true;
  }

  // ['when' whenArgs 'then' compositeStatement]
  private static boolean primary_18_6(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "primary_18_6")) return false;
    primary_18_6_0(builder_, level_ + 1);
    return true;
  }

  // 'when' whenArgs 'then' compositeStatement
  private static boolean primary_18_6_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "primary_18_6_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, WHEN);
    result_ = result_ && whenArgs(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, THEN);
    result_ = result_ && compositeStatement(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // ['else' compositeStatement]
  private static boolean primary_18_7(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "primary_18_7")) return false;
    primary_18_7_0(builder_, level_ + 1);
    return true;
  }

  // 'else' compositeStatement
  private static boolean primary_18_7_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "primary_18_7_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, ELSE);
    result_ = result_ && compositeStatement(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // 'puts' args
  private static boolean primary_19(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "primary_19")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, PUTS);
    result_ = result_ && args(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // PROC LEFT_PAREN type type* RIGHT_PAREN
  public static boolean procType(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "procType")) return false;
    if (!nextTokenIs(builder_, PROC)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, PROC, LEFT_PAREN, TYPE);
    result_ = result_ && procType_3(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RIGHT_PAREN);
    exit_section_(builder_, marker_, PROC_TYPE, result_);
    return result_;
  }

  // type*
  private static boolean procType_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "procType_3")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!consumeToken(builder_, TYPE)) break;
      if (!empty_element_parsed_guard_(builder_, "procType_3", pos_)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // stringLiteral
  public static boolean requirePath(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "requirePath")) return false;
    if (!nextTokenIs(builder_, STRING)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = stringLiteral(builder_, level_ + 1);
    exit_section_(builder_, marker_, REQUIRE_PATH, result_);
    return result_;
  }

  /* ********************************************************** */
  // 'require' requirePath
  public static boolean requireStatement(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "requireStatement")) return false;
    if (!nextTokenIs(builder_, REQUIRE)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, REQUIRE);
    result_ = result_ && requirePath(builder_, level_ + 1);
    exit_section_(builder_, marker_, REQUIRE_STATEMENT, result_);
    return result_;
  }

  /* ********************************************************** */
  // variable | '(' expressions ')'
  public static boolean singleton(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "singleton")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, SINGLETON, "<singleton>");
    result_ = variable(builder_, level_ + 1);
    if (!result_) result_ = singleton_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // '(' expressions ')'
  private static boolean singleton_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "singleton_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, LEFT_PAREN);
    result_ = result_ && expressions(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RIGHT_PAREN);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // STRING
  public static boolean stringLiteral(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "stringLiteral")) return false;
    if (!nextTokenIs(builder_, STRING)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, STRING);
    exit_section_(builder_, marker_, STRING_LITERAL, result_);
    return result_;
  }

  /* ********************************************************** */
  // ':' (fname | varName)
  public static boolean symbol(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "symbol")) return false;
    if (!nextTokenIs(builder_, COLON)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COLON);
    result_ = result_ && symbol_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, SYMBOL, result_);
    return result_;
  }

  // fname | varName
  private static boolean symbol_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "symbol_1")) return false;
    boolean result_;
    result_ = fname(builder_, level_ + 1);
    if (!result_) result_ = varName(builder_, level_ + 1);
    return result_;
  }

  /* ********************************************************** */
  // LEFT_BRACE
  //     | 'NamedTuple' LEFT_PAREN (IDENT ':' type)(',' IDENT ':' type)* RIGHT_PAREN
  public static boolean toupleType(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "toupleType")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, TOUPLE_TYPE, "<touple type>");
    result_ = consumeToken(builder_, LEFT_BRACE);
    if (!result_) result_ = toupleType_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // 'NamedTuple' LEFT_PAREN (IDENT ':' type)(',' IDENT ':' type)* RIGHT_PAREN
  private static boolean toupleType_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "toupleType_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, "NamedTuple");
    result_ = result_ && consumeToken(builder_, LEFT_PAREN);
    result_ = result_ && toupleType_1_2(builder_, level_ + 1);
    result_ = result_ && toupleType_1_3(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RIGHT_PAREN);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // IDENT ':' type
  private static boolean toupleType_1_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "toupleType_1_2")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, IDENT, COLON, TYPE);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (',' IDENT ':' type)*
  private static boolean toupleType_1_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "toupleType_1_3")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!toupleType_1_3_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "toupleType_1_3", pos_)) break;
    }
    return true;
  }

  // ',' IDENT ':' type
  private static boolean toupleType_1_3_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "toupleType_1_3_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, COMMA, IDENT, COLON, TYPE);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // '{' args '}'
  public static boolean tuple(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "tuple")) return false;
    if (!nextTokenIs(builder_, LEFT_BRACE)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, LEFT_BRACE);
    result_ = result_ && args(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RIGHT_BRACE);
    exit_section_(builder_, marker_, TUPLE, result_);
    return result_;
  }

  /* ********************************************************** */
  // tuple [',' tuple]
  static boolean tuples(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "tuples")) return false;
    if (!nextTokenIs(builder_, LEFT_BRACE)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = tuple(builder_, level_ + 1);
    result_ = result_ && tuples_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // [',' tuple]
  private static boolean tuples_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "tuples_1")) return false;
    tuples_1_0(builder_, level_ + 1);
    return true;
  }

  // ',' tuple
  private static boolean tuples_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "tuples_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COMMA);
    result_ = result_ && tuple(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // procType                                            // Proc
  //     | toupleType
  //     | type ( COMMA type ) * ARRAY MINUS_GREATER type      // Proc Type->Type
  //     | type [LEFT_BRACKET (NUMBER_LITERAL) RIGHT_BRACKET]  // Array  Type[n]
  //     | type '.' CLASS
  //     | IDENT
  public static boolean typee(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "typee")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, TYPEE, "<typee>");
    result_ = procType(builder_, level_ + 1);
    if (!result_) result_ = toupleType(builder_, level_ + 1);
    if (!result_) result_ = typee_2(builder_, level_ + 1);
    if (!result_) result_ = typee_3(builder_, level_ + 1);
    if (!result_) result_ = parseTokens(builder_, 0, TYPE, DOT, CLASS);
    if (!result_) result_ = consumeToken(builder_, IDENT);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // type ( COMMA type ) * ARRAY MINUS_GREATER type
  private static boolean typee_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "typee_2")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, TYPE);
    result_ = result_ && typee_2_1(builder_, level_ + 1);
    result_ = result_ && consumeTokens(builder_, 0, ARRAY, MINUS_GREATER, TYPE);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // ( COMMA type ) *
  private static boolean typee_2_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "typee_2_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!typee_2_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "typee_2_1", pos_)) break;
    }
    return true;
  }

  // COMMA type
  private static boolean typee_2_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "typee_2_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, COMMA, TYPE);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // type [LEFT_BRACKET (NUMBER_LITERAL) RIGHT_BRACKET]
  private static boolean typee_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "typee_3")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, TYPE);
    result_ = result_ && typee_3_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // [LEFT_BRACKET (NUMBER_LITERAL) RIGHT_BRACKET]
  private static boolean typee_3_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "typee_3_1")) return false;
    typee_3_1_0(builder_, level_ + 1);
    return true;
  }

  // LEFT_BRACKET (NUMBER_LITERAL) RIGHT_BRACKET
  private static boolean typee_3_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "typee_3_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, LEFT_BRACKET);
    result_ = result_ && consumeToken(builder_, NUMBER_LITERAL);
    result_ = result_ && consumeToken(builder_, RIGHT_BRACKET);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // global | '@' IDENT | IDENT
  public static boolean varName(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "varName")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, VAR_NAME, "<var name>");
    result_ = global(builder_, level_ + 1);
    if (!result_) result_ = parseTokens(builder_, 0, AT, IDENT);
    if (!result_) result_ = consumeToken(builder_, IDENT);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // varName | 'nil' | 'self'
  public static boolean variable(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "variable")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, VARIABLE, "<variable>");
    result_ = varName(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, NIL);
    if (!result_) result_ = consumeToken(builder_, SELF);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // args [',' '*' arg] | '*' arg
  public static boolean whenArgs(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "whenArgs")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _COLLAPSE_, WHEN_ARGS, "<when args>");
    result_ = whenArgs_0(builder_, level_ + 1);
    if (!result_) result_ = whenArgs_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // args [',' '*' arg]
  private static boolean whenArgs_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "whenArgs_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = args(builder_, level_ + 1);
    result_ = result_ && whenArgs_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // [',' '*' arg]
  private static boolean whenArgs_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "whenArgs_0_1")) return false;
    whenArgs_0_1_0(builder_, level_ + 1);
    return true;
  }

  // ',' '*' arg
  private static boolean whenArgs_0_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "whenArgs_0_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, COMMA, TIMES);
    result_ = result_ && arg(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // '*' arg
  private static boolean whenArgs_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "whenArgs_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, TIMES);
    result_ = result_ && arg(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

}
