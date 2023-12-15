package compiler.ErrorHandle;

import compiler.MyJythonListener;
import compiler.SymbolTable.BaseAttribute;
import compiler.SymbolTable.Keyword;
import compiler.SymbolTable.ScopeType;
import compiler.SymbolTable.SymbolTable;
import gen.JythonParser;

import java.util.*;

/**
 * @author Mahya Ehsanimehr on 7/1/2022
 * @project compiler-project2
 **/
public class ErrorHandling {
    private ArrayList<SymbolTable> symbolTableArrayList;
    private ArrayList<Error> allErrors;

    public ErrorHandling() {
        this.allErrors = new ArrayList<>();
    }

    public void printAllErrors() {
        Collections.sort(allErrors, Comparator.comparing(e -> e.getLine()));
        for (Error e : allErrors) {
            System.out.println(e.toString());
        }
    }

    // repeated declaration of method and field
    public void checkForError102_104() {
        for (SymbolTable symbolTable : symbolTableArrayList) {
            Map<String, BaseAttribute> tableMap = symbolTable.getTable();
            for (Map.Entry<String, BaseAttribute> entry : tableMap.entrySet()) {
                if (entry.getValue().isRepeated()) {
                    String type = "";
                    String errMessage = "";
                    int lineOfError = entry.getValue().getLine();
                    int colOfError = entry.getValue().getColumn();

                    if (entry.getValue().getKeyword() == Keyword.Method) {
                        errMessage = " method " + entry.getValue().getName() + " has been defined already";
                        type = "102";
                    } else if (entry.getValue().getKeyword() == Keyword.Field ||
                            entry.getValue().getKeyword() == Keyword.MethodField ||
                            entry.getValue().getKeyword() == Keyword.ClassArrayField ||
                            entry.getValue().getKeyword() == Keyword.ClassField ) {
                        errMessage = " field " + entry.getValue().getName() + " has been defined already";
                        type = "104";
                    }
                    Error error = new Error(type, errMessage, lineOfError, colOfError);
                    allErrors.add(error);
                }
            }
        }
    }

    // using an undefined class
    public void checkForError105() {
        for (SymbolTable symbolTable : symbolTableArrayList) {
            Map<String, BaseAttribute> tableMap = symbolTable.getTable();
            for (Map.Entry<String, BaseAttribute> entry : tableMap.entrySet()) {
                if (!entry.getValue().isDefined()) {
                    int lineOfError = entry.getValue().getLine();
                    int colOfError = entry.getValue().getColumn();
                    String errMessage = "cannot find class " + entry.getValue().getOriginalType();

                    Error error = new Error("105", errMessage, lineOfError, colOfError);
                    allErrors.add(error);
                }
            }
        }
    }

    // using an undefined variable
    public void checkForError106(Map<String, String> variables) {
        for (Map.Entry<String, String> entry : variables.entrySet()) {
            String[] str = entry.getKey().split("_");
            if (!checkVariableDefinition(str[0], entry.getValue())) {
                String errMessage = "Can not find Variable " + str[0];
                int lineOfError = Integer.valueOf(str[1]);
                int colOfError = Integer.valueOf(str[2]);
                Error error = new Error("106", errMessage, lineOfError, colOfError);
                allErrors.add(error);
            }
        }

    }

    // return type mismatch
    public void checkForError210(SymbolTable currentTable, SymbolTable parentTable, String returnValue,
                                 int line, int column) {
        // getting the return value type
        String returnType = "";
        BaseAttribute fieldValue = currentTable.lookup("Field_" + returnValue);
        if (fieldValue != null)
            returnType = fieldValue.getOriginalType();
        else {
            String message = "Can not find Variable " + returnValue;
            Error error = new Error("106", message, line, column);
            allErrors.add(error);
        }
        // getting the method return type
        String methodName = currentTable.getName();
        String methodReturnType = "";
        BaseAttribute methodValue = parentTable.lookup("Method_" + methodName);
        if (methodValue != null)
            methodReturnType = methodValue.getOriginalType();
        else {
            String message = "Can not find method " + methodName;
            Error error = new Error("107", message, line, column);
            allErrors.add(error);
        }
        // comparing these two
        if (!returnType.equals(methodReturnType)) {
            String message = "";
            if (methodReturnType.equals("void"))
                message = "This method doesn't return anything";
            else
                message = "ReturnType of this method must be " + methodReturnType;
            Error error = new Error("210", message, line, column);
            allErrors.add(error);
        }

    }

    // method call errors
    public void checkForError220(Map<String , JythonParser.Method_callContext> methodCallList) {
        for (Map.Entry<String , JythonParser.Method_callContext> methodCall : methodCallList.entrySet()) {
            JythonParser.Method_callContext ctx = methodCall.getValue();
            int scopeNumber = Integer.valueOf(methodCall.getKey());
            int line = ctx.start.getLine();
            int column = ctx.start.getCharPositionInLine();
            String methodName = ctx.word().getText();
            List<JythonParser.ExpContext> arguments = new ArrayList<>();
            if (ctx.args().explist() != null)
               arguments = ctx.args().explist().exp();

            SymbolTable currentTable = getCertainSymbolTable(scopeNumber);
            SymbolTable classTable = getCertainScope(ScopeType.Class);

            BaseAttribute methodValue = classTable.lookup("Method_" + methodName);
            if (methodValue == null) {
                String message = "Can not find method " + methodName;
                Error error = new Error("107", message, line, column);
                allErrors.add(error);
            }
            else {
                List<JythonParser.ParameterContext> methodParameterList = methodValue.getParameterList();
                if (arguments.isEmpty()) {
                    if (!methodParameterList.isEmpty()) {
                        String message = "Mismatch arguments; the number of arguments is incompatible";
                        Error error = new Error("220", message, line, column);
                        allErrors.add(error);
                    }
                }
                else {
                    ArrayList<String> argTypes = new ArrayList<>();
                    for (int i = 0; i < arguments.size(); i++) {
                        String field = arguments.get(i).getText();
                        BaseAttribute fieldValue = currentTable.lookup("Field_" + field);
                        if (fieldValue == null) {
                            String message = "Can not find Variable " + field;
                            Error error = new Error("106", message, line, column);
                            allErrors.add(error);
                        } else {
                            String fieldType = fieldValue.getOriginalType();
                            argTypes.add(fieldType);
                        }
                    }
                    if (methodParameterList.isEmpty()) {
                        String message = "Mismatch arguments; the number of arguments is incompatible";
                        Error error = new Error("220", message, line, column);
                        allErrors.add(error);
                    }
                    else {
                        if (methodParameterList.get(0).varBase().size() != argTypes.size()) {
                            String message = "Mismatch arguments; the number of arguments is incompatible";
                            Error error = new Error("220", message, line, column);
                            allErrors.add(error);
                        }
                        else {
                            for (int j = 0; j < methodParameterList.get(0).varBase().size(); j++) {
                                if (!methodParameterList.get(0).varBase(j).name.getText().equals(argTypes.get(j))) {
                                    String message = "Mismatch arguments; " +
                                            methodParameterList.get(0).varBase(j).name.getText() +
                                            " is expected";
                                    Error error = new Error("220", message, line, column);
                                    allErrors.add(error);
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    // operational errors
    private void checkForError230() {

    }

    private SymbolTable getCertainSymbolTable(int scopeNumber) {
        for (SymbolTable symbolTable : symbolTableArrayList) {
            if (symbolTable.getScopeNumber() == scopeNumber)
                return symbolTable;
        }
        return null;
    }

    private SymbolTable getCertainScope(ScopeType scopeType) {
        for (SymbolTable symbolTable : symbolTableArrayList) {
            if (symbolTable.getScopeType().equals(scopeType))
                return symbolTable;
        }
        return null;
    }

    private boolean checkVariableDefinition(String key, String scopeNumber) {
        int scopeNum = Integer.valueOf(scopeNumber);
        while (scopeNum >= 1) {
            SymbolTable st = getCertainSymbolTable(scopeNum);
            BaseAttribute ba = st.lookup("Field_"+key);
            //if can't find var in current scope.. look up in higher scopes
            if (ba == null) {
                scopeNum = st.getParentScopeNumber();
            }
            else{
                return true;
            }
        }
        return false;
    }
    // ------getter and setter-------


    public ArrayList<SymbolTable> getSymbolTableArrayList() {
        return symbolTableArrayList;
    }

    public void setSymbolTableArrayList(ArrayList<SymbolTable> symbolTableArrayList) {
        this.symbolTableArrayList = symbolTableArrayList;
    }

    public ArrayList<Error> getAllErrors() {
        return allErrors;
    }

    public void setAllErrors(ArrayList<Error> allErrors) {
        this.allErrors = allErrors;
    }

}
