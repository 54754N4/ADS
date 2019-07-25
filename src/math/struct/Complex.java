package math.struct;

public class Complex extends Number {
	private static final long serialVersionUID = -5840674781963782453L;
	private double x;
	private double y;
	
	public static void main(String[] args) {	//debug
		Complex c = new Complex(0,1);
		System.out.println(c);
	}
	
	/* Used to create a copy of a complex object */
	public Complex(Complex c) {
		x = c.x;
		y = c.y;
	}
	
	public Complex(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	/* Creates real numbers */
	public Complex(double x) {
		this(x,0);
	}
	
	/* Creates complex 0 */
	public Complex() {
		this(0,0);
	}
	
	//>	Accessors
	public double real() {
		return x;
	}
	
	public double imaginary() {
		return y;
	}
	
	//>	Elementary Operations
	//>>	Equality
	public boolean equals(Complex c) {
		return (x==c.x && y==c.y);
	}
		
	public Complex conjugate() {
		return new Complex(x,-y);
	}
	
	public Complex plus(double d) {
		return plus(new Complex(d));
	}
	
	public Complex plus(Complex c) {
		return new Complex(x+c.x,y+c.y);
	}
	
	public Complex minus(Complex c) {
		return new Complex(x-c.x,y-c.y);
	}
	
	public Complex minus(double d) {
		return minus(new Complex(d));
	}
	
	public Complex times(double d) {
		return new Complex(x*d,y*d);
	}
	

	public Complex times(Complex c) {
		return new Complex(x * c.x - y * c.y, x * c.y + y * c.x);
	}
	
	public Complex inversed() throws InverseZeroComplexException {
		if (equals(new Complex()))
			throw new InverseZeroComplexException();
		return new Complex(x/(x*x+y*y),-y/(x*x+y*y));
	}
	
	public Complex dividedBy(Complex c) throws InverseZeroComplexException {
		return times(c.inversed());
	}
	
	public double modulus() {
		return Math.sqrt(x*x+y*y);
	}
	
	public double phase() throws PhaseZeroComplexException{
		if (x>0) 
			return Math.atan(y/x);
		if (x<0 && y>=0) 
			return Math.atan(y/x)+Math.PI;
		if (x<0 && y<0)
			return Math.atan(y/x)-Math.PI;
		if (x==0 && y>0)
			return Math.PI/2;
		if (x==0 && y<0)
			return -Math.PI/2;
		//else 
		throw new PhaseZeroComplexException();
	}
	
	//>	Conversions
	public static Complex i2c(int i) {
		return new Complex(i,0);
	}
	
	public double[][] matrixify() {
		return new double[][]{
			{x,-y},
			{y,x}
		};
	}
	
	//>	Complex Transformations
	//>>	Translation
	public Complex translation(double dx) {
		double nX = x + dx;
		double nY = y;
		return new Complex(nX,nY);
	}
	
	//>>	Rotation
	/**
	 * Returns the complex image of a rotation of angle theta.
	 * @param theta	- the angle
	 * @return	complex image
	 */
	public Complex rotation(double theta) {
		return rotation(theta,0,0);
	}
	
	/**
	 * Returns the complex image of a rotation characterized by origin (oX,oY) and angle theta.
	 * @param theta	- the angle
	 * @param oX	- abscissa of origin
	 * @param oY	- ordinate of origin
	 * @return	complex image
	 */
	public Complex rotation(double theta, double oX, double oY) {
		double nX = oX + Math.cos(theta)*(x-oX) + Math.sin(theta)*(oY-y);
		double nY = oY + Math.cos(theta)*(y-oY) + Math.sin(theta)*(x-oX);
		return new Complex(nX,nY);
	}
	
	//>>	Homothety
	/**
	 * Returns the complex image of a homethetic transformation
	 * characterized by origin (oX,oY) and ratio k.
	 * @param k		- ratio
	 * @param oX	- abscissa of origin
	 * @param oY	- ordinate of origin
	 * @return	complex image
	 */
	public Complex homothety(double k, double oX, double oY) {
		double nX = oX + k*(x-oX);
		double nY = oY + k*(y-oY);
		return new Complex(nX,nY);
	}
	
	//>>	Direct Similarities
	public Complex similarity(double k, double theta, double oX, double oY) {
		Complex a = new Complex(k*Math.cos(theta), k*Math.sin(theta)),		// a = k*[cos(t)+i*sin(t)]
				w = new Complex(oX,oY), 									// origin w
				b = a.times(-1).plus(1).times(w);							// b = (1-a)*w
		return a.times(this).plus(b);										// returns a.z + b
	}
	
	/**
	 * Returns z' such as z' = a*z + b with (a,b) belonging to the complex space and a!=0
	 */
	public Complex similarity(Complex a, Complex b) {
		// with ratio k = |a|
		// and angle theta = arg(a)
		// and origin exists iff a!=1 with origin = b/(1-a) 
		return times(a).plus(b);
	}
	
	public String toString() {
		String result = "";
		if (x != 0) {
			if (x < 0) result += "-";
			result += (x < 0) ? -x : x;
		} if (y != 0) {
			if (!result.equals("")) {	// if there's a real part
				if (y > 0) result += " + ";
				else result += " - ";
			} if (y != 1 && y != -1) result += (y < 0) ? -y : y;
			result += "i";
		} if (result.equals("")) result = "0";
		return result;
	}
	
	class PhaseZeroComplexException extends Exception {
		private static final long serialVersionUID = 2320628836150673767L;

		public PhaseZeroComplexException() {
			super("The phase of the complex number z=0 cannot be calculated.");
		}
	}
	
	class InverseZeroComplexException extends ArithmeticException {
		private static final long serialVersionUID = -5405434972993050733L;
		
		public InverseZeroComplexException() {
			super("Cannot inverse the complex (0,0).");
		}
	}

	@Override
	public double doubleValue() {
		return x;
	}

	@Override
	public float floatValue() {
		return (float) x;
	}

	@Override
	public int intValue() {
		return (int) x;
	}

	@Override
	public long longValue() {
		return (long) x;
	}
}
