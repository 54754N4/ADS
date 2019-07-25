package math.derivation.ast;

public class Variable implements Expression {
	private String name;
	
	public Variable() {
		this("x");
	}
	
	public Variable(String name) {
		this.name = name;
	}
	
	@Override
	public Double eval(Double x) {
		return x;
	}

	@Override
	public Expression derivative() {
		return new Constant("1");
	}
	
	public String toString() {
		return name;
	}

}
