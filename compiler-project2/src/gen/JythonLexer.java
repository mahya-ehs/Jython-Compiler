package gen;// Generated from H:/sem 6 1400/compiler-project2/grammar\Jython.g4 by ANTLR 4.9.1
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class JythonLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, T__33=34, T__34=35, T__35=36, T__36=37, T__37=38, 
		T__38=39, T__39=40, TYPE=41, INT=42, FLOATING_POINT=43, STRING=44, BOOL=45, 
		CLASSNAME=46, ID=47, LETTER=48, INTEGER=49, FLOAT=50, DIGIT=51, OPEN_COMMENT=52, 
		CLOSE_COMMENT=53, COMMENT=54, ONE_LINE_COMMENT=55, WHITESPACE=56;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "T__16", 
			"T__17", "T__18", "T__19", "T__20", "T__21", "T__22", "T__23", "T__24", 
			"T__25", "T__26", "T__27", "T__28", "T__29", "T__30", "T__31", "T__32", 
			"T__33", "T__34", "T__35", "T__36", "T__37", "T__38", "T__39", "TYPE", 
			"INT", "FLOATING_POINT", "STRING", "BOOL", "CLASSNAME", "ID", "LETTER", 
			"INTEGER", "FLOAT", "DIGIT", "OPEN_COMMENT", "CLOSE_COMMENT", "COMMENT", 
			"ONE_LINE_COMMENT", "WHITESPACE"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'import'", "'class'", "'('", "','", "')'", "'{'", "'}'", "'['", 
			"']'", "'def'", "'void'", "'return'", "'or'", "'and'", "'if'", "'while'", 
			"'elif'", "'else'", "'print'", "'for'", "'in'", "'range'", "'.'", "'='", 
			"'none'", "'+'", "'-'", "'*'", "'/'", "'%'", "'<'", "'>'", "'<='", "'>='", 
			"'=='", "'!='", "'+='", "'-='", "'*='", "'/='", null, "'int'", "'float'", 
			"'string'", "'bool'", null, null, null, null, null, null, "'#*'", "'*#'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, "TYPE", "INT", "FLOATING_POINT", "STRING", 
			"BOOL", "CLASSNAME", "ID", "LETTER", "INTEGER", "FLOAT", "DIGIT", "OPEN_COMMENT", 
			"CLOSE_COMMENT", "COMMENT", "ONE_LINE_COMMENT", "WHITESPACE"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public JythonLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Jython.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2:\u015e\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\3\2\3\2\3\2\3\2\3\2\3\2"+
		"\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3"+
		"\t\3\t\3\n\3\n\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r"+
		"\3\r\3\r\3\r\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\21\3"+
		"\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3"+
		"\23\3\24\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3"+
		"\27\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3\31\3\31\3\32\3\32\3\32\3\32\3"+
		"\32\3\33\3\33\3\34\3\34\3\35\3\35\3\36\3\36\3\37\3\37\3 \3 \3!\3!\3\""+
		"\3\"\3\"\3#\3#\3#\3$\3$\3$\3%\3%\3%\3&\3&\3&\3\'\3\'\3\'\3(\3(\3(\3)\3"+
		")\3)\3*\3*\3*\3*\5*\u00ff\n*\3+\3+\3+\3+\3,\3,\3,\3,\3,\3,\3-\3-\3-\3"+
		"-\3-\3-\3-\3.\3.\3.\3.\3.\3/\3/\3/\7/\u011a\n/\f/\16/\u011d\13/\3\60\3"+
		"\60\7\60\u0121\n\60\f\60\16\60\u0124\13\60\3\61\3\61\3\62\6\62\u0129\n"+
		"\62\r\62\16\62\u012a\3\63\6\63\u012e\n\63\r\63\16\63\u012f\3\63\3\63\6"+
		"\63\u0134\n\63\r\63\16\63\u0135\3\64\3\64\3\65\3\65\3\65\3\66\3\66\3\66"+
		"\3\67\3\67\3\67\7\67\u0143\n\67\f\67\16\67\u0146\13\67\3\67\3\67\3\67"+
		"\3\67\38\38\78\u014e\n8\f8\168\u0151\138\38\58\u0154\n8\38\38\39\69\u0159"+
		"\n9\r9\169\u015a\39\39\3\u0144\2:\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23"+
		"\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31"+
		"\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S+U,W-Y.[/]\60"+
		"_\61a\62c\63e\64g\65i\66k\67m8o9q:\3\2\t\3\2C\\\3\2c|\5\2C\\aac|\4\2C"+
		"\\c|\3\2\62;\3\2\f\f\5\2\13\f\16\17\"\"\2\u016b\2\3\3\2\2\2\2\5\3\2\2"+
		"\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21"+
		"\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2"+
		"\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3"+
		"\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3"+
		"\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3"+
		"\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2"+
		"\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2"+
		"Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3"+
		"\2\2\2\2g\3\2\2\2\2i\3\2\2\2\2k\3\2\2\2\2m\3\2\2\2\2o\3\2\2\2\2q\3\2\2"+
		"\2\3s\3\2\2\2\5z\3\2\2\2\7\u0080\3\2\2\2\t\u0082\3\2\2\2\13\u0084\3\2"+
		"\2\2\r\u0086\3\2\2\2\17\u0088\3\2\2\2\21\u008a\3\2\2\2\23\u008c\3\2\2"+
		"\2\25\u008e\3\2\2\2\27\u0092\3\2\2\2\31\u0097\3\2\2\2\33\u009e\3\2\2\2"+
		"\35\u00a1\3\2\2\2\37\u00a5\3\2\2\2!\u00a8\3\2\2\2#\u00ae\3\2\2\2%\u00b3"+
		"\3\2\2\2\'\u00b8\3\2\2\2)\u00be\3\2\2\2+\u00c2\3\2\2\2-\u00c5\3\2\2\2"+
		"/\u00cb\3\2\2\2\61\u00cd\3\2\2\2\63\u00cf\3\2\2\2\65\u00d4\3\2\2\2\67"+
		"\u00d6\3\2\2\29\u00d8\3\2\2\2;\u00da\3\2\2\2=\u00dc\3\2\2\2?\u00de\3\2"+
		"\2\2A\u00e0\3\2\2\2C\u00e2\3\2\2\2E\u00e5\3\2\2\2G\u00e8\3\2\2\2I\u00eb"+
		"\3\2\2\2K\u00ee\3\2\2\2M\u00f1\3\2\2\2O\u00f4\3\2\2\2Q\u00f7\3\2\2\2S"+
		"\u00fe\3\2\2\2U\u0100\3\2\2\2W\u0104\3\2\2\2Y\u010a\3\2\2\2[\u0111\3\2"+
		"\2\2]\u0116\3\2\2\2_\u011e\3\2\2\2a\u0125\3\2\2\2c\u0128\3\2\2\2e\u012d"+
		"\3\2\2\2g\u0137\3\2\2\2i\u0139\3\2\2\2k\u013c\3\2\2\2m\u013f\3\2\2\2o"+
		"\u014b\3\2\2\2q\u0158\3\2\2\2st\7k\2\2tu\7o\2\2uv\7r\2\2vw\7q\2\2wx\7"+
		"t\2\2xy\7v\2\2y\4\3\2\2\2z{\7e\2\2{|\7n\2\2|}\7c\2\2}~\7u\2\2~\177\7u"+
		"\2\2\177\6\3\2\2\2\u0080\u0081\7*\2\2\u0081\b\3\2\2\2\u0082\u0083\7.\2"+
		"\2\u0083\n\3\2\2\2\u0084\u0085\7+\2\2\u0085\f\3\2\2\2\u0086\u0087\7}\2"+
		"\2\u0087\16\3\2\2\2\u0088\u0089\7\177\2\2\u0089\20\3\2\2\2\u008a\u008b"+
		"\7]\2\2\u008b\22\3\2\2\2\u008c\u008d\7_\2\2\u008d\24\3\2\2\2\u008e\u008f"+
		"\7f\2\2\u008f\u0090\7g\2\2\u0090\u0091\7h\2\2\u0091\26\3\2\2\2\u0092\u0093"+
		"\7x\2\2\u0093\u0094\7q\2\2\u0094\u0095\7k\2\2\u0095\u0096\7f\2\2\u0096"+
		"\30\3\2\2\2\u0097\u0098\7t\2\2\u0098\u0099\7g\2\2\u0099\u009a\7v\2\2\u009a"+
		"\u009b\7w\2\2\u009b\u009c\7t\2\2\u009c\u009d\7p\2\2\u009d\32\3\2\2\2\u009e"+
		"\u009f\7q\2\2\u009f\u00a0\7t\2\2\u00a0\34\3\2\2\2\u00a1\u00a2\7c\2\2\u00a2"+
		"\u00a3\7p\2\2\u00a3\u00a4\7f\2\2\u00a4\36\3\2\2\2\u00a5\u00a6\7k\2\2\u00a6"+
		"\u00a7\7h\2\2\u00a7 \3\2\2\2\u00a8\u00a9\7y\2\2\u00a9\u00aa\7j\2\2\u00aa"+
		"\u00ab\7k\2\2\u00ab\u00ac\7n\2\2\u00ac\u00ad\7g\2\2\u00ad\"\3\2\2\2\u00ae"+
		"\u00af\7g\2\2\u00af\u00b0\7n\2\2\u00b0\u00b1\7k\2\2\u00b1\u00b2\7h\2\2"+
		"\u00b2$\3\2\2\2\u00b3\u00b4\7g\2\2\u00b4\u00b5\7n\2\2\u00b5\u00b6\7u\2"+
		"\2\u00b6\u00b7\7g\2\2\u00b7&\3\2\2\2\u00b8\u00b9\7r\2\2\u00b9\u00ba\7"+
		"t\2\2\u00ba\u00bb\7k\2\2\u00bb\u00bc\7p\2\2\u00bc\u00bd\7v\2\2\u00bd("+
		"\3\2\2\2\u00be\u00bf\7h\2\2\u00bf\u00c0\7q\2\2\u00c0\u00c1\7t\2\2\u00c1"+
		"*\3\2\2\2\u00c2\u00c3\7k\2\2\u00c3\u00c4\7p\2\2\u00c4,\3\2\2\2\u00c5\u00c6"+
		"\7t\2\2\u00c6\u00c7\7c\2\2\u00c7\u00c8\7p\2\2\u00c8\u00c9\7i\2\2\u00c9"+
		"\u00ca\7g\2\2\u00ca.\3\2\2\2\u00cb\u00cc\7\60\2\2\u00cc\60\3\2\2\2\u00cd"+
		"\u00ce\7?\2\2\u00ce\62\3\2\2\2\u00cf\u00d0\7p\2\2\u00d0\u00d1\7q\2\2\u00d1"+
		"\u00d2\7p\2\2\u00d2\u00d3\7g\2\2\u00d3\64\3\2\2\2\u00d4\u00d5\7-\2\2\u00d5"+
		"\66\3\2\2\2\u00d6\u00d7\7/\2\2\u00d78\3\2\2\2\u00d8\u00d9\7,\2\2\u00d9"+
		":\3\2\2\2\u00da\u00db\7\61\2\2\u00db<\3\2\2\2\u00dc\u00dd\7\'\2\2\u00dd"+
		">\3\2\2\2\u00de\u00df\7>\2\2\u00df@\3\2\2\2\u00e0\u00e1\7@\2\2\u00e1B"+
		"\3\2\2\2\u00e2\u00e3\7>\2\2\u00e3\u00e4\7?\2\2\u00e4D\3\2\2\2\u00e5\u00e6"+
		"\7@\2\2\u00e6\u00e7\7?\2\2\u00e7F\3\2\2\2\u00e8\u00e9\7?\2\2\u00e9\u00ea"+
		"\7?\2\2\u00eaH\3\2\2\2\u00eb\u00ec\7#\2\2\u00ec\u00ed\7?\2\2\u00edJ\3"+
		"\2\2\2\u00ee\u00ef\7-\2\2\u00ef\u00f0\7?\2\2\u00f0L\3\2\2\2\u00f1\u00f2"+
		"\7/\2\2\u00f2\u00f3\7?\2\2\u00f3N\3\2\2\2\u00f4\u00f5\7,\2\2\u00f5\u00f6"+
		"\7?\2\2\u00f6P\3\2\2\2\u00f7\u00f8\7\61\2\2\u00f8\u00f9\7?\2\2\u00f9R"+
		"\3\2\2\2\u00fa\u00ff\5U+\2\u00fb\u00ff\5W,\2\u00fc\u00ff\5Y-\2\u00fd\u00ff"+
		"\5[.\2\u00fe\u00fa\3\2\2\2\u00fe\u00fb\3\2\2\2\u00fe\u00fc\3\2\2\2\u00fe"+
		"\u00fd\3\2\2\2\u00ffT\3\2\2\2\u0100\u0101\7k\2\2\u0101\u0102\7p\2\2\u0102"+
		"\u0103\7v\2\2\u0103V\3\2\2\2\u0104\u0105\7h\2\2\u0105\u0106\7n\2\2\u0106"+
		"\u0107\7q\2\2\u0107\u0108\7c\2\2\u0108\u0109\7v\2\2\u0109X\3\2\2\2\u010a"+
		"\u010b\7u\2\2\u010b\u010c\7v\2\2\u010c\u010d\7t\2\2\u010d\u010e\7k\2\2"+
		"\u010e\u010f\7p\2\2\u010f\u0110\7i\2\2\u0110Z\3\2\2\2\u0111\u0112\7d\2"+
		"\2\u0112\u0113\7q\2\2\u0113\u0114\7q\2\2\u0114\u0115\7n\2\2\u0115\\\3"+
		"\2\2\2\u0116\u011b\t\2\2\2\u0117\u011a\5a\61\2\u0118\u011a\5g\64\2\u0119"+
		"\u0117\3\2\2\2\u0119\u0118\3\2\2\2\u011a\u011d\3\2\2\2\u011b\u0119\3\2"+
		"\2\2\u011b\u011c\3\2\2\2\u011c^\3\2\2\2\u011d\u011b\3\2\2\2\u011e\u0122"+
		"\t\3\2\2\u011f\u0121\t\4\2\2\u0120\u011f\3\2\2\2\u0121\u0124\3\2\2\2\u0122"+
		"\u0120\3\2\2\2\u0122\u0123\3\2\2\2\u0123`\3\2\2\2\u0124\u0122\3\2\2\2"+
		"\u0125\u0126\t\5\2\2\u0126b\3\2\2\2\u0127\u0129\5g\64\2\u0128\u0127\3"+
		"\2\2\2\u0129\u012a\3\2\2\2\u012a\u0128\3\2\2\2\u012a\u012b\3\2\2\2\u012b"+
		"d\3\2\2\2\u012c\u012e\5g\64\2\u012d\u012c\3\2\2\2\u012e\u012f\3\2\2\2"+
		"\u012f\u012d\3\2\2\2\u012f\u0130\3\2\2\2\u0130\u0131\3\2\2\2\u0131\u0133"+
		"\7\60\2\2\u0132\u0134\5g\64\2\u0133\u0132\3\2\2\2\u0134\u0135\3\2\2\2"+
		"\u0135\u0133\3\2\2\2\u0135\u0136\3\2\2\2\u0136f\3\2\2\2\u0137\u0138\t"+
		"\6\2\2\u0138h\3\2\2\2\u0139\u013a\7%\2\2\u013a\u013b\7,\2\2\u013bj\3\2"+
		"\2\2\u013c\u013d\7,\2\2\u013d\u013e\7%\2\2\u013el\3\2\2\2\u013f\u0144"+
		"\5i\65\2\u0140\u0143\5m\67\2\u0141\u0143\13\2\2\2\u0142\u0140\3\2\2\2"+
		"\u0142\u0141\3\2\2\2\u0143\u0146\3\2\2\2\u0144\u0145\3\2\2\2\u0144\u0142"+
		"\3\2\2\2\u0145\u0147\3\2\2\2\u0146\u0144\3\2\2\2\u0147\u0148\5k\66\2\u0148"+
		"\u0149\3\2\2\2\u0149\u014a\b\67\2\2\u014an\3\2\2\2\u014b\u014f\7%\2\2"+
		"\u014c\u014e\n\7\2\2\u014d\u014c\3\2\2\2\u014e\u0151\3\2\2\2\u014f\u014d"+
		"\3\2\2\2\u014f\u0150\3\2\2\2\u0150\u0153\3\2\2\2\u0151\u014f\3\2\2\2\u0152"+
		"\u0154\7\f\2\2\u0153\u0152\3\2\2\2\u0153\u0154\3\2\2\2\u0154\u0155\3\2"+
		"\2\2\u0155\u0156\b8\2\2\u0156p\3\2\2\2\u0157\u0159\t\b\2\2\u0158\u0157"+
		"\3\2\2\2\u0159\u015a\3\2\2\2\u015a\u0158\3\2\2\2\u015a\u015b\3\2\2\2\u015b"+
		"\u015c\3\2\2\2\u015c\u015d\b9\2\2\u015dr\3\2\2\2\17\2\u00fe\u0119\u011b"+
		"\u0122\u012a\u012f\u0135\u0142\u0144\u014f\u0153\u015a\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}