package compiler.ErrorHandle;

import static compiler.Colors.*;

/**
 * @author Mahya Ehsanimehr on 7/1/2022
 * @project compiler-project2
 **/
public class Error {
    private String type;    // 102 104 105 106 107 210 220 230
    private int line;
    private int column;
    private String text;

    public Error(String type, String text, int line, int column) {
        this.type = type;
        this.line = line;
        this.column = column;
        this.text = text;
    }

    public String toString(){
        return RED_BOLD + "Error" + type + " : " +
                ANSI_YELLOW +"in line " + "[" + line + ":" + column + "] ," +
                text + RESET  ;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
