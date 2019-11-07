package math.derivation.ast;

public class PI extends Constant {

	public PI() {
		super(""+Math.PI);
	}
	
	@Override
	public Double eval(Double x) {
		return Math.PI;
	}
	
}