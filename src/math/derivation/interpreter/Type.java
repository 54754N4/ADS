package math.derivation.interpreter;

public enum Type {
	LPAREN("("), RPAREN(")"), 
	PLUS("+"), MINUS("-"), TIMES("*"), DIVIDE("/"), POWER("^"), 
	DOT("."), BAR("|"), 
	ACOS("arccos"), ASIN("arcsin"), ATAN("arctan"),
	COS("cos"), SIN("sin"), TAN("tan"),
	LOG("log"), LN("ln"), SQRT("sqrt"),
	PI("PI"), E("e"), 
	NUMBER("NUMBER"), VAR("VAR"),
	
	EOF("EOF");
	
	public final String string;
	
	private Type(String string) {
		this.string = string;
	}
	
	public String toString() {
		return string;
	}
}
