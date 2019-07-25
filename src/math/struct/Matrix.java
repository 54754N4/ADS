package math.struct;

import math.lambda.Indexed2DVisitor;
import math.lambda.ElementUpdateVisitor;
import math.lambda.ElementVisitor;

public class Matrix {
	private int rows;
	private int cols;
	private double[][] model;
	
	public Matrix(int m, int n) {
		this.setRows(m);
		this.setCols(n);
		model = new double[m][n];
	}
	
	public Matrix(double[][] matrix) {
		setRows(matrix.length);
		setCols(matrix[0].length);
		model = matrix;
	}
	
	public Matrix(){
		this(new double[][]{{0d}});
	}

	//Getters and accessors
	public int getRows() {
		return rows;
	}

	public void setRows(int m) {
		this.rows = m;
	}

	public int getCols() {
		return cols;
	}

	public void setCols(int n) {
		this.cols = n;
	}
	
	public double[][] getMatrix() {
		return model;
	}

	public void setMatrix(double[][] matrix) {
		this.model = matrix;
	}
	
	//Conditions used a lot
	private static boolean notSameSize(Matrix a, Matrix b) {
		return a.rows!=b.rows && a.cols!=b.cols;
	}
	
	private static boolean matricesMultipliable(Matrix a, Matrix b) {
		return a.cols==b.rows;
	}
	
	//Elementary Operations
	public Matrix plus(Matrix matrix) throws MatricesNotSameSizeException {
		if (notSameSize(this,matrix))
			throw new MatricesNotSameSizeException();
		double[][] result = new double[matrix.rows][matrix.cols];
		for (int i=0; i<matrix.rows; i++) 
			for (int j=0; j<matrix.cols; j++) 
				result[i][j] = this.model[i][j] + matrix.model[i][j];
		return new Matrix(result);
	}
	
	public Matrix times(int lambda) {
		double[][] result = this.model;
		for (int i=0; i<rows; i++) 
			for (int j=0; j<cols; j++) 
				result[i][j] = model[i][j]*lambda;
		return new Matrix(result);
	}
	
	public Matrix opposite() {
		return this.times(-1);						
	}
	
	public Matrix minus(Matrix matrix) throws MatricesNotSameSizeException {
		if (notSameSize(this,matrix))
			throw new MatricesNotSameSizeException();
		return this.plus(matrix.opposite());
	}
	
	public Matrix times(Matrix matrix) throws MatricesNotMultipliableException {
		if (!matricesMultipliable(this, matrix))
			throw new MatricesNotMultipliableException();
		double[][] result = new double[rows][matrix.cols];
		for (int i=0; i<rows; i++) 
			for (int j=0; j<matrix.cols; j++) {
				double sum = 0;
				for (int k=0; k<matrix.rows; k++)
					sum = sum + (model[i][k] * matrix.model[k][j]);
				result[i][j] = sum;
			}
		return new Matrix(result);
	}
	
	public Matrix timesHadamard(Matrix matrix) throws MatricesNotSameSizeException {
		if (notSameSize(this, matrix))
			throw new MatricesNotSameSizeException();
		double[][] result = new double[rows][cols];
		for (int i=0; i<rows; i++)
			for (int j=0; j<cols; j++)
				result[i][j] = model[i][j] * matrix.model[i][j];
		return new Matrix(result);
	}
	
	public Matrix timesSchur(Matrix matrix) throws MatricesNotSameSizeException {
		return timesHadamard(matrix);
	}
	
	public double det() throws NotSquareMatrixException {
		if (getRows() != getCols())
			throw new NotSquareMatrixException();
		return det(this);
	}
	
	public double trace() throws NotSquareMatrixException {
		if (rows!=cols) 
			throw new NotSquareMatrixException();
		double trace = 0;
		for (int i=0; i<rows; i++)
			trace += model[i][i];
		return trace;		
	}
	
	public Matrix transpose() {
		double[][] result = new double[cols][rows];
		for (int row=0; row<rows; row++)
			for (int col=0; col<cols; col++)
				result[col][row] = model[row][col];
		return new Matrix(result);
	}
	
	/**
	 * Recursively calculate the matrix's determinant :
	 * - Stop Condition	| square matrix size 2 
	 * - Heredity 		| use formula : det(A) = Σ(σ*pivot*det(minor))
	 * Hypothesis: matrix is a Square Matrix.
	 * @param matrix
	 * @return complex determinant
	 */
	private static double det(Matrix matrix) {
		if (matrix.getRows()==2) {	//if square matrix size 2
			double a = matrix.model[0][0],
					b = matrix.model[0][1],
					c = matrix.model[1][0],
					d = matrix.model[1][1];
			return (a*d)-(b*c);
		}
		double determinant = 0;
		for (int i=0; i<matrix.getRows(); i++) 
			/* we'll use the first column as pivot, and the following formula : det(A) = Σ(σ*pivot*det(minor))
			 * with σ the sign deduced by (-1)^i,
			 * i representing the current pivot,
			 * pivot being the i'th coefficient from the first column,
			 * minor being the minor matrix after removing the pivot cell.
			 */
			determinant += matrix.model[i][0]*Math.pow(-1, i)*det(matrix.minor(i, 0));
		return determinant;
	}
	
	private Matrix minor(int row, int column) {
		Matrix minor = new Matrix(rows-1, cols-1);	//because we're deleting 1 row + 1 column
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
	
	public Matrix forEachVisit(ElementVisitor<Double> action) {
		for (int i=0; i<rows; i++) 
			for (int j=0; j<cols; j++)
				action.visit(model[i][j]);
		return this;
	}
	
	public Matrix forEachVisitIndexed(Indexed2DVisitor<Double> indexedAction) {
		for (int i=0; i<rows; i++)
			for (int j=0; j<cols; j++) 
				indexedAction.visit(i, j, model[i][j]);
		return this;
	}
	
	public Matrix forEachApply(ElementUpdateVisitor<Double> action) {
		Matrix applied = new Matrix(rows, cols);
		for (int i=0; i<applied.rows; i++)
			for (int j=0; j<applied.cols; j++) 
				applied.model[i][j] = action.visit(model[i][j]);
		return applied;	// return the new result matrix
	}
	
	public Matrix forEachApplySelf(ElementUpdateVisitor<Double> action) {
		for (int i=0; i<rows; i++)
			for (int j=0; j<cols; j++) 
				model[i][j] = action.visit(model[i][j]);
		return this;
	}
	
	public static Matrix get() {
		return new Matrix(
			new double[][] {
				{ 1,2,3 },
				{ 4,5,6 },
				{ 7,8,9 }
			}
		);
	}

	public static void main(String[] args) throws MatricesNotMultipliableException, NotSquareMatrixException {
		Matrix m = new Matrix();
		/* Empty matrix : m = new Matrix(3,3); */
		double[][] mModel = new double[][] {
			{ 1,2,3 },
			{ 4,5,6 },
			{ 7,8,9 }
		};	
		m = new Matrix(mModel);
		// Verification Purposes : det(m) = 0 and if u replace first complex 1+i to 1 det(m) = -6
//		System.out.println(m.det());
//		System.out.println(m.forEachVisit((elem) -> System.err.println(elem)));
//		System.out.println(m.forEachApply((elem) -> -elem));
		System.out.println(m);
	}
	
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
