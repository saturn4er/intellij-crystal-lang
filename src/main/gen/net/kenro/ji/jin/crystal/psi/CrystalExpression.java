// This is a generated file. Not intended for manual editing.
package net.kenro.ji.jin.crystal.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface CrystalExpression extends PsiElement {

  @Nullable
  CrystalAliasExpression getAliasExpression();

  @NotNull
  List<CrystalAssoc> getAssocList();

  @NotNull
  List<CrystalAssocArray> getAssocArrayList();

  @Nullable
  CrystalBlockVariable getBlockVariable();

  @Nullable
  CrystalCall getCall();

  @NotNull
  List<CrystalCommand> getCommandList();

  @NotNull
  List<CrystalExpression> getExpressionList();

  @Nullable
  CrystalFname getFname();

  @NotNull
  List<CrystalFunction> getFunctionList();

  @Nullable
  CrystalLhs getLhs();

  @NotNull
  List<CrystalLiteral> getLiteralList();

  @NotNull
  List<CrystalOpAsgn> getOpAsgnList();

  @Nullable
  CrystalRequireStatement getRequireStatement();

  @NotNull
  List<CrystalTuple> getTupleList();

  @NotNull
  List<CrystalVariable> getVariableList();

  @NotNull
  List<CrystalWhenArgs> getWhenArgsList();

}
