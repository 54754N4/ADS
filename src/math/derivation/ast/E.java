package math.derivation.ast;

public class E extends Constant {

	public E() {
		super(""+Math.E);
	}
	
	@Override
	public Double eval(Double x) {
		return Math.E;
	}
	
}