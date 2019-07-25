package math.derivation.ast;

public class Constant implements Expression {
	private String c;
	
	public Constant(String c) {
		this.c = c;
	}
	
	@Override
	public Double eval(Double x) {
		return Double.parseDouble(c);
	}

	@Override
	public Expression derivative() {
		return new Constant("0");
	}

	public boolean equals(Constant constant) {
		return c.equals(constant.c);
	}
	
	public String toString() {
		return c;
	}
}
