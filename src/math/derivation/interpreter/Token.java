package math.derivation.interpreter;

public class Token {
	private Type type;
	private String value;
	
	public Token(Type type) {
		this(type, type.toString());
	}
	
	public Token(Type type, String value) {
		this.type = type;
		this.value = value;
	}
	
	public Type getType() {
		return type;
	}
	
	public String getValue() {
		return value;
	}
	
	public String toString() {
		if (value.equals(type.toString())) 
			return String.format("TOKEN(%s)", type.name());
		return String.format("TOKEN(%s, %s)", type.name(), value);
	}
}
