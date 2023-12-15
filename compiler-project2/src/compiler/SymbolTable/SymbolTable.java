package compiler.SymbolTable;

import java.util.*;

import static compiler.Colors.BLUE_BRIGHT;
import static compiler.Colors.RESET;
import static compiler.SymbolTable.KeywordConstants.KEY_KEYWORD;
import static compiler.SymbolTable.KeywordConstants.VALUE_KEYWORD;


public class SymbolTable {
    private String name;
    private int scopeNumber;
    private Map<String, BaseAttribute> table;
    // To keep keys in the order
    private Queue<String> keys;
    private int parentScopeNumber = 0;
    private ScopeType scopeType;

    public SymbolTable(String name, int scopeNumber) {
        this.name = name;
        this.scopeNumber = scopeNumber;
        this.table = new HashMap<>();
        this.keys = new LinkedList<>();
    }

    public void insert(String idefname, BaseAttribute attribute) {
        table.put(idefname, attribute);
        keys.add(idefname);
    }

    public BaseAttribute lookup(String idefname) {
        for (Map.Entry<String , BaseAttribute> entry : table.entrySet()) {
            if (entry.getKey().equals(idefname) && !entry.getValue().isRepeated())
                return table.get(idefname);
        }
        return null;
    }

    public String toString() {
        return "------------- " + name + " : " + scopeNumber + " -------------\n" +
                printItems() +
                "-----------------------------------------\n";
    }

    public String printItems() {
        String itemsStr = "";
        while (!keys.isEmpty()) {
            String entry = keys.poll();
            itemsStr += KEY_KEYWORD + BLUE_BRIGHT + entry + RESET + " | " + VALUE_KEYWORD + table.get(entry).toString()
                    + "\n";
        }
        return itemsStr;
    }

    public int getScopeNumber() {
        return scopeNumber;
    }

    public Map<String, BaseAttribute> getTable() {
        return table;
    }

    public int getParentScopeNumber() {
        return parentScopeNumber;
    }

    public void setParentScopeNumber(int parentScopeNumber) {
        this.parentScopeNumber = parentScopeNumber;
    }

    public String getName() {
        return name;
    }

    public ScopeType getScopeType() {
        return scopeType;
    }

    public void setScopeType(ScopeType scopeType) {
        this.scopeType = scopeType;
    }
}
