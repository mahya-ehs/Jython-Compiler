package gen;// Generated from H:/sem 6 1400/compiler-project2/grammar\Jython.g4 by ANTLR 4.9.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link JythonParser}.
 */
public interface JythonListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link JythonParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(JythonParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link JythonParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(JythonParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link JythonParser#import_class}.
	 * @param ctx the parse tree
	 */
	void enterImport_class(JythonParser.Import_classContext ctx);
	/**
	 * Exit a parse tree produced by {@link JythonParser#import_class}.
	 * @param ctx the parse tree
	 */
	void exitImport_class(JythonParser.Import_classContext ctx);
	/**
	 * Enter a parse tree produced by {@link JythonParser#classDef}.
	 * @param ctx the parse tree
	 */
	void enterClassDef(JythonParser.ClassDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link JythonParser#classDef}.
	 * @param ctx the parse tree
	 */
	void exitClassDef(JythonParser.ClassDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link JythonParser#class_body}.
	 * @param ctx the parse tree
	 */
	void enterClass_body(JythonParser.Class_bodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link JythonParser#class_body}.
	 * @param ctx the parse tree
	 */
	void exitClass_body(JythonParser.Class_bodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link JythonParser#varDec}.
	 * @param ctx the parse tree
	 */
	void enterVarDec(JythonParser.VarDecContext ctx);
	/**
	 * Exit a parse tree produced by {@link JythonParser#varDec}.
	 * @param ctx the parse tree
	 */
	void exitVarDec(JythonParser.VarDecContext ctx);
	/**
	 * Enter a parse tree produced by {@link JythonParser#arrayDec}.
	 * @param ctx the parse tree
	 */
	void enterArrayDec(JythonParser.ArrayDecContext ctx);
	/**
	 * Exit a parse tree produced by {@link JythonParser#arrayDec}.
	 * @param ctx the parse tree
	 */
	void exitArrayDec(JythonParser.ArrayDecContext ctx);
	/**
	 * Enter a parse tree produced by {@link JythonParser#methodDec}.
	 * @param ctx the parse tree
	 */
	void enterMethodDec(JythonParser.MethodDecContext ctx);
	/**
	 * Exit a parse tree produced by {@link JythonParser#methodDec}.
	 * @param ctx the parse tree
	 */
	void exitMethodDec(JythonParser.MethodDecContext ctx);
	/**
	 * Enter a parse tree produced by {@link JythonParser#constructor}.
	 * @param ctx the parse tree
	 */
	void enterConstructor(JythonParser.ConstructorContext ctx);
	/**
	 * Exit a parse tree produced by {@link JythonParser#constructor}.
	 * @param ctx the parse tree
	 */
	void exitConstructor(JythonParser.ConstructorContext ctx);
	/**
	 * Enter a parse tree produced by {@link JythonParser#parameter}.
	 * @param ctx the parse tree
	 */
	void enterParameter(JythonParser.ParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link JythonParser#parameter}.
	 * @param ctx the parse tree
	 */
	void exitParameter(JythonParser.ParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link JythonParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(JythonParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link JythonParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(JythonParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link JythonParser#return_statement}.
	 * @param ctx the parse tree
	 */
	void enterReturn_statement(JythonParser.Return_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link JythonParser#return_statement}.
	 * @param ctx the parse tree
	 */
	void exitReturn_statement(JythonParser.Return_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link JythonParser#condition_list}.
	 * @param ctx the parse tree
	 */
	void enterCondition_list(JythonParser.Condition_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link JythonParser#condition_list}.
	 * @param ctx the parse tree
	 */
	void exitCondition_list(JythonParser.Condition_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link JythonParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterCondition(JythonParser.ConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link JythonParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitCondition(JythonParser.ConditionContext ctx);
	/**
	 * Enter a parse tree produced by {@link JythonParser#if_statement}.
	 * @param ctx the parse tree
	 */
	void enterIf_statement(JythonParser.If_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link JythonParser#if_statement}.
	 * @param ctx the parse tree
	 */
	void exitIf_statement(JythonParser.If_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link JythonParser#while_statement}.
	 * @param ctx the parse tree
	 */
	void enterWhile_statement(JythonParser.While_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link JythonParser#while_statement}.
	 * @param ctx the parse tree
	 */
	void exitWhile_statement(JythonParser.While_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link JythonParser#if_else_statement}.
	 * @param ctx the parse tree
	 */
	void enterIf_else_statement(JythonParser.If_else_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link JythonParser#if_else_statement}.
	 * @param ctx the parse tree
	 */
	void exitIf_else_statement(JythonParser.If_else_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link JythonParser#print_statement}.
	 * @param ctx the parse tree
	 */
	void enterPrint_statement(JythonParser.Print_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link JythonParser#print_statement}.
	 * @param ctx the parse tree
	 */
	void exitPrint_statement(JythonParser.Print_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link JythonParser#for_statement}.
	 * @param ctx the parse tree
	 */
	void enterFor_statement(JythonParser.For_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link JythonParser#for_statement}.
	 * @param ctx the parse tree
	 */
	void exitFor_statement(JythonParser.For_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link JythonParser#method_call}.
	 * @param ctx the parse tree
	 */
	void enterMethod_call(JythonParser.Method_callContext ctx);
	/**
	 * Exit a parse tree produced by {@link JythonParser#method_call}.
	 * @param ctx the parse tree
	 */
	void exitMethod_call(JythonParser.Method_callContext ctx);
	/**
	 * Enter a parse tree produced by {@link JythonParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignment(JythonParser.AssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link JythonParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignment(JythonParser.AssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link JythonParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterExp(JythonParser.ExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link JythonParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitExp(JythonParser.ExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link JythonParser#prefixexp}.
	 * @param ctx the parse tree
	 */
	void enterPrefixexp(JythonParser.PrefixexpContext ctx);
	/**
	 * Exit a parse tree produced by {@link JythonParser#prefixexp}.
	 * @param ctx the parse tree
	 */
	void exitPrefixexp(JythonParser.PrefixexpContext ctx);
	/**
	 * Enter a parse tree produced by {@link JythonParser#args}.
	 * @param ctx the parse tree
	 */
	void enterArgs(JythonParser.ArgsContext ctx);
	/**
	 * Exit a parse tree produced by {@link JythonParser#args}.
	 * @param ctx the parse tree
	 */
	void exitArgs(JythonParser.ArgsContext ctx);
	/**
	 * Enter a parse tree produced by {@link JythonParser#explist}.
	 * @param ctx the parse tree
	 */
	void enterExplist(JythonParser.ExplistContext ctx);
	/**
	 * Exit a parse tree produced by {@link JythonParser#explist}.
	 * @param ctx the parse tree
	 */
	void exitExplist(JythonParser.ExplistContext ctx);
	/**
	 * Enter a parse tree produced by {@link JythonParser#arithmetic_operator}.
	 * @param ctx the parse tree
	 */
	void enterArithmetic_operator(JythonParser.Arithmetic_operatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link JythonParser#arithmetic_operator}.
	 * @param ctx the parse tree
	 */
	void exitArithmetic_operator(JythonParser.Arithmetic_operatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link JythonParser#relational_operators}.
	 * @param ctx the parse tree
	 */
	void enterRelational_operators(JythonParser.Relational_operatorsContext ctx);
	/**
	 * Exit a parse tree produced by {@link JythonParser#relational_operators}.
	 * @param ctx the parse tree
	 */
	void exitRelational_operators(JythonParser.Relational_operatorsContext ctx);
	/**
	 * Enter a parse tree produced by {@link JythonParser#assignment_operators}.
	 * @param ctx the parse tree
	 */
	void enterAssignment_operators(JythonParser.Assignment_operatorsContext ctx);
	/**
	 * Exit a parse tree produced by {@link JythonParser#assignment_operators}.
	 * @param ctx the parse tree
	 */
	void exitAssignment_operators(JythonParser.Assignment_operatorsContext ctx);
	/**
	 * Enter a parse tree produced by {@link JythonParser#parent_name}.
	 * @param ctx the parse tree
	 */
	void enterParent_name(JythonParser.Parent_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link JythonParser#parent_name}.
	 * @param ctx the parse tree
	 */
	void exitParent_name(JythonParser.Parent_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link JythonParser#varBase}.
	 * @param ctx the parse tree
	 */
	void enterVarBase(JythonParser.VarBaseContext ctx);
	/**
	 * Exit a parse tree produced by {@link JythonParser#varBase}.
	 * @param ctx the parse tree
	 */
	void exitVarBase(JythonParser.VarBaseContext ctx);
	/**
	 * Enter a parse tree produced by {@link JythonParser#word}.
	 * @param ctx the parse tree
	 */
	void enterWord(JythonParser.WordContext ctx);
	/**
	 * Exit a parse tree produced by {@link JythonParser#word}.
	 * @param ctx the parse tree
	 */
	void exitWord(JythonParser.WordContext ctx);
}