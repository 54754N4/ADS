package math.derivation.ast;

public abstract class BinaryOperator implements Expression {
	public final Expression left, right;
	
	public BinaryOperator(Expression left, Expression right) {
		this.left = left;
		this.right = right;
	}
	
	public String toString(String op) {
		return left + op + right;
	}
}
