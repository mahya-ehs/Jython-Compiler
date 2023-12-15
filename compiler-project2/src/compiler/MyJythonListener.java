package compiler;

import compiler.ErrorHandle.ErrorHandling;
import compiler.SymbolTable.BaseAttribute;
import compiler.SymbolTable.Keyword;
import compiler.SymbolTable.ScopeType;
import compiler.SymbolTable.SymbolTable;
import gen.JythonListener;
import gen.JythonParser.*;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.*;

import static compiler.SymbolTable.KeywordConstants.*;

public class MyJythonListener implements JythonListener {

    private static final String program = "program";
    private int parameterIndex = 0;

    private boolean inMethod = false;
    private Stack<SymbolTable> symbolTablesStack = new Stack<>();
    private ArrayList<SymbolTable> symbolTableArrayList = new ArrayList<>();

    private ArrayList<String> definedClasses = new ArrayList<>();
    private ErrorHandling errorHandling;
    private Map<String, Method_callContext> methodCalls = new HashMap<>();
    private Map<String, String> variables = new HashMap<>();

    @Override
    public void enterProgram(ProgramContext ctx) {
        startNewScope(program, ctx.start.getLine());
        symbolTablesStack.peek().setScopeType(ScopeType.Global);
        errorHandling = new ErrorHandling();
    }

    @Override
    public void exitProgram(ProgramContext ctx) {
        endScope();
        printAllSymbolTables();

        errorHandling.setSymbolTableArrayList(symbolTableArrayList);

        errorHandling.checkForError102_104();
        errorHandling.checkForError105();
        errorHandling.checkForError106(variables);
        errorHandling.checkForError220(methodCalls);

        errorHandling.printAllErrors();
    }

    @Override
    public void enterImport_class(Import_classContext ctx) {

        String name = ctx.CLASSNAME().getText();
        String key = getKey(Keyword.Import, name);
        BaseAttribute value = BaseAttribute.importAttribute(name);
        addToCurrentScope(key, value);

        definedClasses.add(value.getOriginalType());
    }

    @Override
    public void exitImport_class(Import_classContext ctx) {
    }

    @Override
    public void enterClassDef(ClassDefContext ctx) {
        String name = ctx.class_name.getText();
        String key = getKey(Keyword.Class, name);
        BaseAttribute value = BaseAttribute.classAttribute(name, ctx.parent_name());
        value.setLine(ctx.start.getLine());
        value.setColumn(ctx.stop.getCharPositionInLine());
        addToCurrentScope(key, value);
        startNewScope(name, ctx.start.getLine());
        symbolTablesStack.peek().setScopeType(ScopeType.Class);

        definedClasses.add(value.getOriginalType());
    }

    @Override
    public void exitClassDef(ClassDefContext ctx) {
        endScope();
    }

    @Override
    public void enterClass_body(Class_bodyContext ctx) {
    }

    @Override
    public void exitClass_body(Class_bodyContext ctx) {
    }

    @Override
    public void enterVarDec(VarDecContext ctx) {
        String name = ctx.varBase().word().getText();
        String key = getKey(Keyword.Field, name); // classField is also a field (key == field)
        String type;
        BaseAttribute value;

        if (ctx.varBase().CLASSNAME() != null) {
            //ClassField
            type = ctx.varBase().CLASSNAME().getText();
            boolean isDefined = checkIfDefined(type);
            value = BaseAttribute.classFieldAttribute(name, type, isDefined, inMethod);

        } else {
            // primitive
            type = ctx.varBase().TYPE().getText();
            value = BaseAttribute.fieldAttribute(name, type, true, inMethod);
        }
        value.setLine(ctx.start.getLine());
        value.setColumn(ctx.stop.getCharPositionInLine());
        addToCurrentScope(key, value);
    }

    @Override
    public void exitVarDec(VarDecContext ctx) {

    }

    @Override
    public void enterArrayDec(ArrayDecContext ctx) {
        String name = ctx.word().getText();
        String key = getKey(Keyword.Field, name);  // ClassArrayField is also a field (key == field)
        String type;
        BaseAttribute value;

        if (ctx.CLASSNAME() != null) {
            // classArrayField
            type = ctx.CLASSNAME().getText();
            boolean isDefined = checkIfDefined(type);
            value = BaseAttribute.classArrayFieldAttribute(name, type, isDefined, inMethod);

        } else {
            type = ctx.TYPE().getText();
            value = BaseAttribute.baseClassArrayFieldAttribute(name, type, type,true, inMethod);
        }
        value.setLine(ctx.start.getLine());
        value.setColumn(ctx.stop.getCharPositionInLine());
        addToCurrentScope(key, value);
    }

    @Override
    public void exitArrayDec(ArrayDecContext ctx) {

    }

    @Override
    public void enterMethodDec(MethodDecContext ctx) {
        String name = ctx.word().getText();
        List<ParameterContext> parameter = ctx.parameter();
        String key = getKey(Keyword.Method, name);
        BaseAttribute value;
        inMethod = true;

        if (ctx.return_type == null) {
            // void
            value = BaseAttribute.voidMethodAttribute(name, parameter);

        } else if (ctx.CLASSNAME() != null) {
            // object return type
            value = BaseAttribute.objectMethodAttribute(name, ctx.CLASSNAME().getText(), parameter);
            boolean isDefined = checkIfDefined(ctx.CLASSNAME().getText());
            value.setDefined(isDefined);
        } else {
            // primitive return type
            value = BaseAttribute.primitiveMethodAttribute(name, ctx.TYPE().getText(), parameter);
        }
        value.setLine(ctx.start.getLine());
        value.setColumn(ctx.stop.getCharPositionInLine());
        addToCurrentScope(key, value);
        startNewScope(name, ctx.start.getLine());
        symbolTablesStack.peek().setScopeType(ScopeType.Method);
    }

    @Override
    public void exitMethodDec(MethodDecContext ctx) {
        inMethod = false;
        endScope();
    }

    @Override
    public void enterConstructor(ConstructorContext ctx) {

        String name = ctx.name.getText();
        String key = getKey(Keyword.Constructor, name);

        BaseAttribute value = BaseAttribute.constructorAttribute(name, ctx.parameter());

        value.setLine(ctx.start.getLine());
        value.setColumn(ctx.stop.getCharPositionInLine());
        boolean isDefined = checkIfDefined(ctx.CLASSNAME().getText());
        value.setDefined(isDefined);

        addToCurrentScope(key, value);
        startNewScope(name, ctx.start.getLine());
        symbolTablesStack.peek().setScopeType(ScopeType.Method);

    }

    @Override
    public void exitConstructor(ConstructorContext ctx) {
        endScope();
    }

    @Override
    public void enterParameter(ParameterContext ctx) {
        List<VarBaseContext> varBases = ctx.varBase();
        for (VarBaseContext varBase : varBases) {
            parameterIndex++;
            String name = varBase.word().getText();
            String key = getKey(Keyword.Field, name);
            BaseAttribute value;

            if (varBase.CLASSNAME() != null) {
                boolean isDefined = checkIfDefined(varBase.CLASSNAME().getText());
                value = BaseAttribute.classFieldParameterAttribute(name, varBase.CLASSNAME().getText(),
                        isDefined, parameterIndex);
            } else {
                value = BaseAttribute.classFieldParameterAttribute(name, varBase.TYPE().getText(),
                        true, parameterIndex);
            }

            value.setLine(ctx.start.getLine());
            value.setColumn(ctx.stop.getCharPositionInLine());
            addToCurrentScope(key, value);
        }
    }

    @Override
    public void exitParameter(ParameterContext ctx) {
        parameterIndex = 0;
    }

    @Override
    public void enterStatement(StatementContext ctx) {
    }

    @Override
    public void exitStatement(StatementContext ctx) {

    }

    // TODO : return type in grammar is weird!! check it out again
    @Override
    public void enterReturn_statement(Return_statementContext ctx) {
        int line = ctx.start.getLine();
        int column = ctx.start.getCharPositionInLine();
        SymbolTable currentTable = symbolTablesStack.peek();
        SymbolTable parentTable = getCertainTable(currentTable.getParentScopeNumber());
        errorHandling.checkForError210(currentTable, parentTable, ctx.exp().getText(), line, column);
    }

    @Override
    public void exitReturn_statement(Return_statementContext ctx) {

    }

    @Override
    public void enterCondition_list(Condition_listContext ctx) {

    }

    @Override
    public void exitCondition_list(Condition_listContext ctx) {

    }

    @Override
    public void enterCondition(ConditionContext ctx) {

    }

    @Override
    public void exitCondition(ConditionContext ctx) {

    }

    @Override
    public void enterIf_statement(If_statementContext ctx) {
        if (!isNestedScope(ctx.statement(), ctx.start.getLine())) {
            startNewScope(IF_KEYWORD, ctx.start.getLine());
        }
        symbolTablesStack.peek().setScopeType(ScopeType.Blocks);

    }

    @Override
    public void exitIf_statement(If_statementContext ctx) {
        endScope();
    }

    @Override
    public void enterWhile_statement(While_statementContext ctx) {
        if (!isNestedScope(ctx.statement(), ctx.start.getLine())) {
            startNewScope(WHILE_KEYWORD, ctx.start.getLine());
            symbolTablesStack.peek().setScopeType(ScopeType.Blocks);
        }
    }

    @Override
    public void exitWhile_statement(While_statementContext ctx) {
        endScope();
    }

    @Override
    public void enterIf_else_statement(If_else_statementContext ctx) {
        if (!isNestedScope(ctx.statement(), ctx.start.getLine())) {
            startNewScope(IF_ELSE_KEYWORD, ctx.start.getLine());
            symbolTablesStack.peek().setScopeType(ScopeType.Blocks);
        }
    }

    @Override
    public void exitIf_else_statement(If_else_statementContext ctx) {
        endScope();
    }

    @Override
    public void enterPrint_statement(Print_statementContext ctx) {

    }

    @Override
    public void exitPrint_statement(Print_statementContext ctx) {

    }

    @Override
    public void enterFor_statement(For_statementContext ctx) {
        if (!isNestedScope(ctx.statement(), ctx.start.getLine())) {
            startNewScope(FOR_KEYWORD, ctx.start.getLine());
            symbolTablesStack.peek().setScopeType(ScopeType.Blocks);
        }
    }

    @Override
    public void exitFor_statement(For_statementContext ctx) {
        endScope();
    }

    @Override
    public void enterMethod_call(Method_callContext ctx) {
        int scopeNumber = symbolTablesStack.peek().getScopeNumber();
        methodCalls.put(String.valueOf(scopeNumber), ctx);

    }

    @Override
    public void exitMethod_call(Method_callContext ctx) {

    }

    @Override
    public void enterAssignment(AssignmentContext ctx) {
        int scopeNumber = symbolTablesStack.peek().getScopeNumber();
        if (ctx.exp()!= null) {
            if (ctx.exp().prefixexp() != null && ctx.exp().prefixexp().getChildCount() == 1) {
                String key = ctx.exp().prefixexp().word().getText() + "_"  + ctx.start.getLine() + "_" +
                        ctx.start.getCharPositionInLine();
                variables.put(key, String.valueOf(scopeNumber));
            }
            if (ctx.exp().arithmetic_operator()!= null) {
                for (int i = 0; i < ctx.exp().children.size(); i++) {
                    if (!ctx.exp().children.get(i).getText().equals(ctx.exp().arithmetic_operator().getText())) {
                        String key = ctx.exp().children.get(i).getText() + "_"  + ctx.start.getLine() + "_" +
                                ctx.start.getCharPositionInLine();
                        variables.put(key, String.valueOf(scopeNumber));
                    }
                }
            }
        }
    }

    @Override
    public void exitAssignment(AssignmentContext ctx) {

    }

    @Override
    public void enterExp(ExpContext ctx) {
    }

    @Override
    public void exitExp(ExpContext ctx) {

    }

    @Override
    public void enterPrefixexp(PrefixexpContext ctx) {

    }

    @Override
    public void exitPrefixexp(PrefixexpContext ctx) {

    }

    @Override
    public void enterArgs(ArgsContext ctx) {

    }

    @Override
    public void exitArgs(ArgsContext ctx) {

    }

    @Override
    public void enterExplist(ExplistContext ctx) {

    }

    @Override
    public void exitExplist(ExplistContext ctx) {

    }

    @Override
    public void enterArithmetic_operator(Arithmetic_operatorContext ctx) {

    }

    @Override
    public void exitArithmetic_operator(Arithmetic_operatorContext ctx) {

    }

    @Override
    public void enterRelational_operators(Relational_operatorsContext ctx) {

    }

    @Override
    public void exitRelational_operators(Relational_operatorsContext ctx) {

    }

    @Override
    public void enterAssignment_operators(Assignment_operatorsContext ctx) {

    }

    @Override
    public void exitAssignment_operators(Assignment_operatorsContext ctx) {

    }

    @Override
    public void enterParent_name(Parent_nameContext ctx) {

    }

    @Override
    public void exitParent_name(Parent_nameContext ctx) {

    }

    @Override
    public void enterVarBase(VarBaseContext ctx) {
    }

    @Override
    public void exitVarBase(VarBaseContext ctx) {

    }

    @Override
    public void visitTerminal(TerminalNode terminalNode) {

    }

    @Override
    public void visitErrorNode(ErrorNode errorNode) {

    }

    @Override
    public void enterEveryRule(ParserRuleContext parserRuleContext) {

    }

    @Override
    public void exitEveryRule(ParserRuleContext parserRuleContext) {

    }

    @Override
    public void enterWord(WordContext ctx) {

    }

    @Override
    public void exitWord(WordContext ctx) {

    }

    //============================== helper functions ==========================================

    private boolean isNestedScope(List<StatementContext> statements, int line) {
        if (!checkStatement(statements)) {
            startNewScope(NESTED_KEYWORD, line);
            return true;
        }
        return false;
    }

    private boolean checkStatement(List<StatementContext> statements) {

        for (int i = 0; i < statements.size(); i++) {
            StatementContext statement = statements.get(i);
            if (statement.if_statement() != null
                    || statement.if_else_statement() != null
                    || statement.while_statement() != null
                    || statement.for_statement() != null) {
                return true;
            }
        }
        return false;
    }

    private void startNewScope(String name, int scopeNumber) {
        SymbolTable symbolTable = new SymbolTable(name, scopeNumber);
        if (symbolTablesStack.size() >= 1) {
            int parentScopeNumber = symbolTablesStack.peek().getScopeNumber();
            symbolTable.setParentScopeNumber(parentScopeNumber);
        }
        symbolTablesStack.push(symbolTable);
    }

    private void addToCurrentScope(String key, BaseAttribute value) {
        SymbolTable current = symbolTablesStack.peek();
        String finalKey = checkIfRepeated(current, key, value);
        current.insert(finalKey, value);
    }

    private void endScope() {
        symbolTableArrayList.add(symbolTablesStack.pop());
    }

    private String getKey(Keyword keyword, String name) {
        return keyword.name() + "_" + name;
    }

    private void printAllSymbolTables() {
        while (!symbolTablesStack.isEmpty()) {
            symbolTableArrayList.add(symbolTablesStack.pop());
        }
        Collections.sort(symbolTableArrayList, Comparator.comparing(s -> s.getScopeNumber()));
        for (int i = 0; i < symbolTableArrayList.size(); i++) {
            String symbolTable = symbolTableArrayList.get(i).toString();
            System.out.println(symbolTable);
        }
    }

    private String checkIfRepeated(SymbolTable symbolTable, String key, BaseAttribute value) {
        int line = value.getLine();
        int column = value.getColumn();
        if (symbolTable.lookup(key) != null) {
            key += "_" + line + "_" + column;
            value.setRepeated(true);
        }
        return key;
    }

    private boolean checkIfDefined(String type) {
        return definedClasses.contains(type);
    }

    private SymbolTable getCertainTable(int scopeNumber) {
        Stack<SymbolTable> tempStack = new Stack<>();
        SymbolTable found = null;
        while (!symbolTablesStack.isEmpty()) {
            SymbolTable temp = symbolTablesStack.pop();
            if (temp.getScopeNumber() == scopeNumber) {
                found = temp;
                symbolTablesStack.push(temp);
                break;
            }
            tempStack.push(temp);
        }
        while (!tempStack.isEmpty())
            symbolTablesStack.push(tempStack.pop());

        return found;
    }
}
