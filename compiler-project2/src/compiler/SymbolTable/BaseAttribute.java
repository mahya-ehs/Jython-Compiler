package compiler.SymbolTable;

import gen.JythonParser.*;

import java.util.*;

import static compiler.Colors.*;
import static compiler.SymbolTable.KeywordConstants.*;

public class BaseAttribute {

    private int line;
    private int column;
    private boolean repeated;

    private Keyword keyword;
    private String name;
    private String originalType = null;
    private String classType = null;
    private String returnType = null;
    private int index = -1;
    boolean isDefined = true;

    List<ParameterContext> parameterList = null;
    List<VarBaseContext> varBaseList = null;
    List<Parent_nameContext> parentList = null;

    String valueString = "";

    public BaseAttribute(Keyword keyword, String name) {
        this.keyword = keyword;
        this.name = name;
    }

    /**
     * @param name name of imported class
     * @Import
     */
    public static BaseAttribute importAttribute(String name) {
        BaseAttribute attr = new BaseAttribute(Keyword.Import, name);
        attr.setOriginalType(name);
        return attr;
    }

    /**
     * @param name       name of class
     * @param parentList list of classes which are thi class parents
     * @Class
     */
    public static BaseAttribute classAttribute(String name, List<Parent_nameContext> parentList) {
        BaseAttribute attr = new BaseAttribute(Keyword.Class, name);
        attr.parentList = parentList;
        attr.valueString = constructParent(parentList);
        attr.setOriginalType(name);
        return attr;
    }


    /**
     * @param name      name of Field which is an instance of an Object
     * @param classType is the Object
     * @param isDefined if this variable is defined
     * @ClassField
     */
    public static BaseAttribute classFieldAttribute(String name, String classType, boolean isDefined, boolean inMethod) {
        String classClassType = constructClassType(classType);
        return baseFieldAttribute(Keyword.ClassField, name, classClassType, isDefined, classType, inMethod);
    }


    /**
     * @param name      name of Field which is an instance of an Object
     * @param classType is the Object
     * @param isDefined if this variable is defined
     * @ClassArrayField
     */
    public static BaseAttribute classArrayFieldAttribute(String name, String classType, boolean isDefined, boolean inMethod) {
        String arrayClassType = constructClassType(classType);
        return baseClassArrayFieldAttribute(name, arrayClassType, classType, isDefined, inMethod);
    }

    public static BaseAttribute baseClassArrayFieldAttribute(String name, String classType, String originalType,
                                                             boolean isDefined, boolean inMethod) {
        return baseFieldAttribute(Keyword.ClassArrayField, name, classType, isDefined, originalType, inMethod);
    }


    /**
     * @param name      name of Field which is an instance of an Object
     * @param classType is the Object
     * @param isDefined if this variable is defined
     * @Field
     */
    public static BaseAttribute fieldAttribute(String name, String classType, boolean isDefined, boolean inMethod) {
        return baseFieldAttribute(Keyword.Field, name, classType, isDefined, classType, inMethod);
    }


    /**
     * @param name      name of Field which is an instance of an Object
     * @param classType is the Object
     * @param isDefined if this variable is defined
     * @param index     index of this parameter in parameter list
     * @Parameter
     */
    public static BaseAttribute classFieldParameterAttribute(String name, String classType, boolean isDefined, int index) {
        String classClassType = constructClassType(classType);
        return baseParameterAttribute(name, classClassType, classType, isDefined, index);
    }

    public static BaseAttribute baseParameterAttribute(String name, String classType, String originalType,
                                                       boolean isDefined, int index) {
        BaseAttribute attr = baseFieldAttribute(Keyword.Parameter, name, classType, isDefined, classType, false);
        attr.index = index;
        attr.valueString += constructIndex(index);
        attr.setOriginalType(originalType);
        return attr;
    }

    public static BaseAttribute baseFieldAttribute(Keyword keyword, String name, String classType, boolean isDefined,
                                                   String originalType, boolean inMethod) {

        if (inMethod) {
            keyword = Keyword.MethodField;
        }

        BaseAttribute attr = new BaseAttribute(keyword, name);

        attr.classType = classType;
        attr.isDefined = isDefined;

        attr.valueString = " (" + TYPE_KEYWORD + GREEN_BRIGHT + classType + RESET + ", " + constructIsDefined(isDefined) + ")";
        attr.setOriginalType(originalType);
        return attr;
    }


    /**
     * @param name       name of function
     * @param returnType type of returning value of function
     * @param parameters arguments
     * @Method
     */
    public static BaseAttribute objectMethodAttribute(String name, String returnType, List<ParameterContext> parameters) {
        String objectReturnType = constructClassType(returnType);
        return baseMethodAttribute(name, objectReturnType, parameters, returnType);
    }

    public static BaseAttribute primitiveMethodAttribute(String name, String returnType, List<ParameterContext> parameters) {
        return baseMethodAttribute(name, returnType, parameters, returnType);
    }

    public static BaseAttribute voidMethodAttribute(String name, List<ParameterContext> parameters) {
        String voidReturnType = bracket(VOID_KEYWORD);
        return baseMethodAttribute(name, voidReturnType, parameters, "void");
    }

    public static BaseAttribute baseMethodAttribute(String name, String returnType, List<ParameterContext> parameters,
                                                    String originalType) {
        BaseAttribute attr = new BaseAttribute(Keyword.Method, name);

        attr.returnType = returnType;
        attr.parameterList = parameters;
        attr.varBaseList = getAllVarBases(parameters);

        attr.valueString = constructReturnType(returnType);
        if (!parameters.isEmpty())
            attr.valueString += constructParameterList(parameters);

        attr.setOriginalType(originalType);

        return attr;
    }


    /**
     * @param name       name of constructor
     * @param parameters arguments
     * @Constructor
     */
    public static BaseAttribute constructorAttribute(String name, List<ParameterContext> parameters) {
        BaseAttribute attr = new BaseAttribute(Keyword.Constructor, name);

        attr.parameterList = parameters;
        attr.varBaseList = getAllVarBases(parameters);
        attr.originalType = name;

        if (!parameters.isEmpty())
            attr.valueString = constructParameterList(parameters);

        return attr;
    }

    @Override
    public String toString() {
        return constructName(keyword, name) + valueString;
    }

    /**
     * ===================================== Helper functions =============================
     */

    public static List<VarBaseContext> getAllVarBases(List<ParameterContext> parameters) {
        ArrayList<VarBaseContext> varBases = new ArrayList<>();

        for (ParameterContext parameter : parameters) {
            varBases.addAll(parameter.varBase());
        }

        return varBases;
    }

    public static String getParameterString(List<ParameterContext> parameters) {
        List<VarBaseContext> varBases = getAllVarBases(parameters);

        String parametersString = "";
        for (int i = 0; i < varBases.size(); i++) {

            String type = getVarBaseType(varBases.get(i));
            parametersString += arrayItem(TYPE_KEYWORD + type, INDEX_KEYWORD + (i + 1));

            if (i != varBases.size() - 1) {
                parametersString += ", ";
            }
        }
        return parametersString;
    }

    public static String getVarBaseType(VarBaseContext varBase) {
        String type;
        if (varBase.CLASSNAME() != null) {
            type = varBase.CLASSNAME().getText();
        } else {
            type = varBase.TYPE().getText();
        }
        return type;
    }

    public static String getParentString(List<Parent_nameContext> parents) {
        String parentString = "";
        for (int i = 0; i < parents.size(); i++) {
            parentString += parents.get(i).getText();
            if (i != parents.size() - 1) {
                parentString += ", ";
            }
        }
        return parentString;
    }

    public static String constructName(Keyword keyword, String name) {
        return YELLOW_BRIGHT + keyword.name() + RESET + parenthesis(NAME_KEYWORD + PURPLE_BRIGHT + name + RESET);
    }

    public static String constructClassType(String classType) {
        return bracket(CLASS_TYPE_KEYWORD + GREEN_BRIGHT + classType) + RESET;
    }

    public static String constructParameterList(List<ParameterContext> parameterList) {
        return CYAN_BRIGHT + bracket(PARAMETER_LIST_KEYWORD + getParameterString(parameterList)) + RESET;
    }

    public static String constructParent(List<Parent_nameContext> parentList) {
        return parenthesis(PARENT_KEYWORD + getParentString(parentList));
    }

    public static String constructReturnType(String returnType) {
        return GREEN_BRIGHT + parenthesis(RETURN_TYPE_KEYWORD + returnType) + RESET;
    }

    public static String constructIndex(int index) {
        return parenthesis(INDEX_KEYWORD + RED_BRIGHT + index + RESET);
    }

    public static String constructIsDefined(boolean isDefined) {
        return IS_DEFINED_KEYWORD + RED_BRIGHT + isDefined + RESET;
    }

    public static String parenthesis(String s) {
        return " (" + s + ")";
    }

    public static String bracket(String s) {
        return "[" + s + "]";
    }

    public static String arrayItem(String type, String index) {
        return "[" + type + "," + index + "]";
    }


    //------ getter setter ----------

    public Keyword getKeyword() {
        return keyword;
    }

    public String getName() {
        return name;
    }

    public String getClassType() {
        return classType;
    }

    public String getReturnType() {
        return returnType;
    }

    public int getIndex() {
        return index;
    }

    public List<ParameterContext> getParameterList() {
        return parameterList;
    }

    public List<VarBaseContext> getVarBaseList() {
        return varBaseList;
    }

    public List<Parent_nameContext> getParentList() {
        return parentList;
    }

    public boolean isDefined() {
        return isDefined;
    }

    public void setDefined(boolean defined) {
        isDefined = defined;
    }

    public String getValueString() {
        return valueString;
    }

    public String getOriginalType() {
        return originalType;
    }

    public void setOriginalType(String originalType) {
        this.originalType = originalType;
    }

    //-------- line and column------

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public boolean isRepeated() {
        return repeated;
    }

    public void setRepeated(boolean repeated) {
        this.repeated = repeated;
    }
}
