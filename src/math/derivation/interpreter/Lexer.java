package math.derivation.interpreter;

import java.util.HashMap;
import java.util.Map;

public class Lexer {
	private boolean finished;
	private String input;
	private int i;
	private char current;
	private static final Map<String, Token> RESERVED_WORDS = new HashMap<>();
	
	public static void main(String[] args) throws InterruptedException {
		String input = "x*2";
		Lexer lexer = new Lexer(input);
		Token token;
		while ((token = lexer.nextToken()).getType() != Type.EOF) {
			System.out.println(token);
			Thread.sleep(1000);
		}
	}
	
	static {
		RESERVED_WORDS.put("pi", new Token(Type.PI));
		RESERVED_WORDS.put("e", new Token(Type.E));
		RESERVED_WORDS.put("acos", new Token(Type.ACOS));
		RESERVED_WORDS.put("asin", new Token(Type.ASIN));
		RESERVED_WORDS.put("atan", new Token(Type.ATAN));
		RESERVED_WORDS.put("cos", new Token(Type.COS));
		RESERVED_WORDS.put("sin", new Token(Type.SIN));
		RESERVED_WORDS.put("tan", new Token(Type.TAN));
		RESERVED_WORDS.put("log", new Token(Type.LOG));
		RESERVED_WORDS.put("ln", new Token(Type.LN));
		RESERVED_WORDS.put("sqrt", new Token(Type.SQRT));
	}
	
	public Lexer(String input) {
		this.input = input;
		finished = false;
		i = 0;
		current = input.charAt(i);
	}
	
	private void advance() {
		if (i < input.length() - 1 && !finished) current = input.charAt(++i);
		else finished = true;	// EOF
	}
	
	private char peek() {
		if (i+1<input.length())
			return input.charAt(i+1);
		return '\0';
	}
	
//	private String peek(int n) {
//		return input.substring(i, i+n);
//	}
	
	private boolean isDigit(char c) {
		return '0' <= c && c <= '9';
	}
	
	private boolean isAlpha(char c) {
		return ('a' <= c && c <= 'z') 
			|| ('A' <= c && c <= 'Z') 
			|| c == '_';
	}
	
	private boolean isAlphanumeral(char c) {
		return isAlpha(c) || isDigit(c);
	}
	
	private boolean isDigit() {
		return isDigit(current);
	}
	
	private boolean isAlpha() {
		return isAlpha(current);
	}
	
	private Token number() {
		StringBuilder sb = new StringBuilder();
		char next;
		do {
			sb.append(current);
			next = peek();
			advance();
		} while (isDigit(next));
		return new Token(Type.NUMBER, sb.toString());
	}
	
	private Token word() {
		StringBuilder sb = new StringBuilder();
		char next;
		do {
			sb.append(current);
			next = peek();
			advance();
		} while (isAlphanumeral(next));
		String string = sb.toString();
		if (RESERVED_WORDS.containsKey(string.toLowerCase()))
			return RESERVED_WORDS.get(string.toLowerCase());
		return new Token(Type.VAR, string);
	}
	
	public Token nextToken() {
		if (finished)
			return new Token(Type.EOF);
		switch (current) {
			case '(':
				advance();
				return new Token(Type.LPAREN);
			case ')':
				advance();
				return new Token(Type.RPAREN);
			case '+':
				advance();
				return new Token(Type.PLUS);
			case '-':
				advance();
				return new Token(Type.MINUS);
			case '*':
				advance();
				return new Token(Type.TIMES);
			case '/':
				advance();
				return new Token(Type.DIVIDE);
			case '^':
				advance();
				return new Token(Type.POWER);
			case '|':
				advance();
				return new Token(Type.BAR);
			case '.':
				advance();
				return new Token(Type.DOT);
			default: 
				if (isDigit()) 
					return number();
				else if (isAlpha()) 
					return word();
				return null; // unreachable
		}
	}
}
