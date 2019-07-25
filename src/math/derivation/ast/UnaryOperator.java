package math.derivation.ast;

public abstract class UnaryOperator implements Expression {
	public final Expression expression;
	
	public UnaryOperator(Expression expression) {
		this.expression = expression;
	}
}
