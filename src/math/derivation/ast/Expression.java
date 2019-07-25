package math.derivation.ast;

public interface Expression {
	Double eval(Double x);
	Expression derivative();
}
