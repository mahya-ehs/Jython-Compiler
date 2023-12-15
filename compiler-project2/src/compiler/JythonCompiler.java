package compiler;


import gen.JythonLexer;
import gen.JythonListener;
import gen.JythonParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import java.io.IOException;

public class JythonCompiler {
    public static void main(String[] args) throws IOException {
        CharStream program = CharStreams.fromFileName("F:\\university\\university projects\\CDProject_PhaseFinal_MahyaEhsanimehr_9812762327_BitaKarvizi_9812762338\\compiler-project2\\input.txt");
        JythonLexer lexer = new JythonLexer(program);
        TokenStream tokens = new CommonTokenStream(lexer);
        JythonParser parser = new JythonParser(tokens);
        parser.setBuildParseTree(true);
        ParseTree parseTree = parser.program();
        ParseTreeWalker walker = new ParseTreeWalker();
        JythonListener listener = new MyJythonListener();

        walker.walk(listener, parseTree);
    }
}
