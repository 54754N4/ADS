package math.struct.generic;

import math.algebra.BooleanAlgebra;
import math.algebra.ComplexAlgebra;
import math.algebra.DoubleAlgebra;
import math.algebra.FloatAlgebra;
import math.algebra.IntegerAlgebra;
import math.algebra.LongAlgebra;
import math.algebra.StringAlgebra;
import math.contract.FieldAlgebra;
import math.lambda.ElementUpdateVisitor;
import math.lambda.ElementVisitor;
import math.lambda.Indexed2DUpdateVisitor;
import math.lambda.Indexed2DVisitor;

public class Matrix<K> {
	private int rows;
	private int cols;
	private K[][] model;
	private FieldAlgebra<K> algebra;
	
	public static void main(String[] args) throws MatricesNotMultipliableException, NotSquareMatrixException {
		{	// Anonymous block #1 - test complex matrices
			Matrix<Complex> m = Matrix.getComplex();
			System.out.println(m.det());
			m.forEachVisitIndexed((i,j, elem) -> System.out.println(String.format("(%d,%d) -> %s", i,j,elem)));
			System.out.println(m.forEachApply((elem) -> elem.conjugate()));	// complex
			System.out.println(m);
		}
		{	// Test matrices of doubles
			Matrix<Double> m = Matrix.doubleIdentity(4);
			System.out.println(m.det());
			System.out.println(m.forEachApply((elem) -> -elem));	// doubles
			System.out.println(m);
		}
		{ 	// Test ints
			Matrix<Integer> m = Matrix.ofIntegers(4, 4);
			m.forEachApplySelfIndexed((i,j, elem) -> i+j);
			System.out.println(m);
		}
		{ 	// Idk yet why anyone would wanna use this..
			Matrix<Boolean> m = Matrix.ofBooleans(4, 4);
			m.forEachVisitIndexed((i,j, elem) -> System.out.println(String.format("(%d,%d) -> %s", i,j,elem)));
			System.out.println(m);
		}		
	}
	
	@SuppressWarnings("unchecked")	// since each algebra know exactly how to initialise the cells
	public Matrix(int m, int n, FieldAlgebra<K> algebra) {
		this.algebra = algebra;
		setRows(m);
		setCols(n);
		model = (K[][]) new Object[m][n];
		for (int i=0; i<m; i++) 
			for (int j=0; j<n; j++)
				model[i][j] = algebra.additiveIdentity();	// usually 0
	}
	
	public Matrix(K[][] matrix, FieldAlgebra<K> algebra) {
		this.algebra = algebra;
		setRows(matrix.length);
		setCols(matrix[0].length);
		model = matrix;
	}

	//Getters and accessors
	public int getRows() {
		return rows;
	}

	public void setRows(int m) {
		rows = m;
	}

	public int getCols() {
		return cols;
	}

	public void setCols(int n) {
		cols = n;
	}
	
	public K[][] getMatrix() {
		return model;
	}

	public void setMatrix(K[][] matrix) {
		model = matrix;
	}
	
	public K set(int i, int j, K value) {
		return model[i][j] = value;
	}
	
	public K get(int i, int j) {
		return model[i][j];
	}
	
	//Conditions used a lot
	private static boolean notSameSize(Matrix<?> a, Matrix<?> b) {
		return a.rows!=b.rows && a.cols!=b.cols;
	}
	
	private static boolean matricesMultipliable(Matrix<?> a, Matrix<?> b) {
		return a.cols==b.rows;
	}
	
	//Elementary Operations
	public Matrix<K> plus(Matrix<K> matrix) throws MatricesNotSameSizeException {
		if (notSameSize(this,matrix))
			throw new MatricesNotSameSizeException();
		Matrix<K> result = new Matrix<>(matrix.rows, matrix.cols, algebra);
		for (int i=0; i<matrix.rows; i++) 
			for (int j=0; j<matrix.cols; j++) 
				result.set(i, j, algebra.add(model[i][j], matrix.model[i][j]));
		return result;
	}
	
	public Matrix<K> times(int lambda) {
		Matrix<K> result = new Matrix<>(rows, cols, algebra);
		for (int i=0; i<rows; i++) 
			for (int j=0; j<cols; j++) 
				result.set(i, j, algebra.multiply(model[i][j], lambda));
		return result;
	}
	
	public Matrix<K> opposite() {
		return this.times(-1);						
	}
	
	public Matrix<K> minus(Matrix<K> matrix) throws MatricesNotSameSizeException {
		if (notSameSize(this,matrix))
			throw new MatricesNotSameSizeException();
		return this.plus(matrix.opposite());
	}
	
	public Matrix<K> times(Matrix<K> matrix) throws MatricesNotMultipliableException {
		if (!matricesMultipliable(this, matrix))
			throw new MatricesNotMultipliableException();
		Matrix<K> result = new Matrix<>(rows, matrix.cols, algebra);
		for (int i=0; i<rows; i++) { 
			for (int j=0; j<matrix.cols; j++) {
				K sum = algebra.additiveIdentity();
				for (int k=0; k<matrix.rows; k++)
					sum = algebra.add(sum , algebra.multiply(model[i][k], matrix.model[k][j]));
				result.set(i, j, sum);
			}
		}
		return result;
	}
	
	public Matrix<K> timesHadamard(Matrix<K> matrix) throws MatricesNotSameSizeException {
		if (notSameSize(this, matrix))
			throw new MatricesNotSameSizeException();
		Matrix<K> result = new Matrix<>(rows, cols, algebra);
		for (int i=0; i<rows; i++)
			for (int j=0; j<cols; j++)
				result.set(i, j, algebra.multiply(model[i][j], matrix.model[i][j]));
		return result;
	}
	
	public Matrix<K> timesSchur(Matrix<K> matrix) throws MatricesNotSameSizeException {
		return timesHadamard(matrix);
	}
	
	public K trace() throws NotSquareMatrixException {
		if (rows!=cols) 
			throw new NotSquareMatrixException();
		K trace = algebra.additiveIdentity();
		for (int i=0; i<rows; i++)
			trace = algebra.add(trace, model[i][i]);
		return trace;		
	}
	
	public Matrix<K> transpose() {
		Matrix<K> result = new Matrix<>(rows, cols, algebra);
		for (int i=0; i<rows; i++)
			for (int j=0; j<cols; j++)
				result.set(j, i, model[i][j]);
		return result;
	}
	
	/**
	 * Recursively calculate the matrix's determinant :
	 * - Stop Condition	| square matrix size 2 
	 * - Heredity 		| use formula : det(A) = Σ(σ*pivot*det(minor))
	 * Hypothesis: matrix is a Square Matrix.
	 * @param matrix
	 * @return complex determinant
	 * @throws NotSquareMatrixException 
	 */
	public K det() throws NotSquareMatrixException {
		if (rows!=cols) throw new NotSquareMatrixException();
		else if (rows==2) {	//if square matrix size 2
			K a = model[0][0], b = model[0][1],
			  c = model[1][0], d = model[1][1];
			return algebra.substract(algebra.multiply(a, d), algebra.multiply(b, c));
		}
		K determinant = algebra.additiveIdentity();
		for (int i=0; i<rows; i++) 
			/* we'll use the first column as pivot, and the following formula : det(A) = Σ(σ*pivot*det(minor))
			 * with σ the sign deduced by (-1)^i,
			 * i representing the current pivot,
			 * pivot being the i'th coefficient from the first column,
			 * minor being the minor matrix after removing the pivot cell.
			 */
			determinant = algebra.add(determinant, algebra.multiply(algebra.multiply(model[i][0], Math.pow(-1, i)), minor(i, 0).det()));
		return determinant;
	}
	
	private Matrix<K> minor(int row, int column) {
		Matrix<K> minor = new Matrix<K>(rows-1, cols-1, algebra);	//because we're deleting 1 row + 1 column
		for (int i=0; i<getRows(); i++)
			for (int j=0; j<getCols(); j++)
				if (!(i==row || j==column))
					minor.model[(i>row) ? i-1 : i][(j>column) ? j-1 : j] = model[i][j];
		return minor;	
	}
	
	//Representation
	public String toString() {
		String representation = "";
		for (int i=0; i<rows; i++) {
			representation += "(";
			for (int j=0; j<cols; j++)
				representation += model[i][j]+((j!=cols-1)?"\t":"");
			representation += "\t)\n";
		}
		return representation;
	}
	
	// For-each loops
	public void forEachVisit(ElementVisitor<K> action) {
		for (int i=0; i<rows; i++) 
			for (int j=0; j<cols; j++)
				action.visit(model[i][j]);
	}
	
	public void forEachVisitIndexed(Indexed2DVisitor<K> indexedAction) {
		for (int i=0; i<rows; i++)
			for (int j=0; j<cols; j++) 
				indexedAction.visit(i, j, model[i][j]);
	}
	
	public Matrix<K> forEachApply(ElementUpdateVisitor<K> action) {
		Matrix<K> applied = new Matrix<>(rows, cols, algebra);
		for (int i=0; i<applied.rows; i++)
			for (int j=0; j<applied.cols; j++) 
				applied.model[i][j] = action.visit(model[i][j]);
		return applied;	// return the new result matrix
	}
	
	public Matrix<K> forEachApplyIndexed(Indexed2DUpdateVisitor<K> action) {
		Matrix<K> applied = new Matrix<>(rows, cols, algebra);
		for (int i=0; i<applied.rows; i++)
			for (int j=0; j<applied.cols; j++) 
				applied.model[i][j] = action.visit(i, j, model[i][j]);
		return applied;	// return the new result matrix
	}
	
	public Matrix<K> forEachApplySelf(ElementUpdateVisitor<K> action) {
		for (int i=0; i<rows; i++)
			for (int j=0; j<cols; j++) 
				model[i][j] = action.visit(model[i][j]);
		return this;
	}
	
	public Matrix<K> forEachApplySelfIndexed(Indexed2DUpdateVisitor<K> action) {
		for (int i=0; i<rows; i++)
			for (int j=0; j<cols; j++) 
				model[i][j] = action.visit(i, j, model[i][j]);
		return this;
	}
	
	// Identity matrices
	public static <T> Matrix<T> identity(int degree, FieldAlgebra<T> algebra) {
		Matrix<T> identity = new Matrix<>(degree, degree, algebra);
		for (int i=0; i<degree; i++)
			identity.set(i, i, algebra.multiplicativeIdentity());
		return identity;
	}
	
	public static Matrix<Integer> intIdentity(int degree) {
		return identity(degree, Algebra.forIntegers());
	}
	
	public static Matrix<Long> longIdentity(int degree) {
		return identity(degree, Algebra.forLongs());
	}
	
	public static Matrix<Float> floatIdentity(int degree) {
		return identity(degree, Algebra.forFloats());
	}
	
	public static Matrix<Double> doubleIdentity(int degree) {
		return identity(degree, Algebra.forDoubles());
	}
	
	public static Matrix<Complex> complexIdentity(int degree) {
		return identity(degree, Algebra.forComplex());
	}
	
	// Primitive types matrices constructors
	public static Matrix<Integer> ofIntegers(int m, int n) {
		return new Matrix<Integer>(m, n, Algebra.forIntegers());
	}
	
	public static Matrix<Long> ofLongs(int m, int n) {
		return new Matrix<Long>(m, n, Algebra.forLongs());
	}
	
	public static Matrix<Float> ofFloats(int m, int n) {
		return new Matrix<Float>(m, n, Algebra.forFloats());
	}
	
	public static Matrix<Double> ofDoubles(int m, int n) {
		return new Matrix<Double>(m, n, Algebra.forDoubles());
	}
	
	public static Matrix<Complex> ofComplex(int m, int n) {
		return new Matrix<Complex>(m, n, Algebra.forComplex());
	}
	
	public static Matrix<Boolean> ofBooleans(int m, int n) {
		return new Matrix<Boolean>(m, n, Algebra.forBooleans());
	}
	
	public static Matrix<String> ofStrings(int m, int n) {
		return new Matrix<String>(m, n, Algebra.forStrings());
	}
	
	// Test + Debug matrices
	public static Matrix<Double> getDoubles() {
		return new Matrix<Double>(
			new Double[][] {
				{ 1d,2d,3d },
				{ 4d,5d,6d },
				{ 7d,8d,9d }
			}, Algebra.forDoubles());
	}
	
	public static Matrix<Complex> getComplex() {
		return new Matrix<Complex>(
			new Complex[][] {
				{ new Complex(1,1), new Complex(2,2), new Complex(3,3) },
				{ new Complex(4,4), new Complex(5,5), new Complex(6,6) },
				{ new Complex(7,7), new Complex(8,8), new Complex(9,9) }
			}, Algebra.forComplex());
	}
	
	/**
	 * Gives convenient access to primitive types algebras
	 * @author Satsana
	 */
	public static abstract class Algebra {
		public static IntegerAlgebra forIntegers() {
			return new IntegerAlgebra();
		}
		
		public static LongAlgebra forLongs() {
			return new LongAlgebra();
		}
		
		public static FloatAlgebra forFloats() {
			return new FloatAlgebra();
		}
		
		public static DoubleAlgebra forDoubles() {
			return new DoubleAlgebra();
		}
		
		public static ComplexAlgebra forComplex() {
			return new ComplexAlgebra();
		}

		//Trolling
		public static BooleanAlgebra forBooleans() {
			return new BooleanAlgebra();
		}
		
		public static StringAlgebra forStrings() {
			return new StringAlgebra();
		}
	}
	
	// Matrix related exceptions
	public static class NotSquareMatrixException extends Exception {
		private static final long serialVersionUID = -7200675495130696696L;

		public NotSquareMatrixException() {
			super("Matrix is not square.");
		}
	}
	
	public static class MatricesNotMultipliableException extends Exception {
		private static final long serialVersionUID = -1770879902979636021L;

		public MatricesNotMultipliableException() {
			super("You need A,B matrices that follow this rule ( A: m-by-n & B: n-by-p )");
		}

		public MatricesNotMultipliableException(String string) {
			super(string);
		}
	}
	
	public static class MatricesNotSameSizeException extends Exception {
		private static final long serialVersionUID = 8981285656443861687L;

		public MatricesNotSameSizeException() {
			super("The two matrices don't have the same size.");
		}
	}
}
