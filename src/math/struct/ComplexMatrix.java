package math.struct;

import math.lambda.Indexed2DVisitor;
import math.lambda.ElementUpdateVisitor;
import math.lambda.ElementVisitor;

public class ComplexMatrix {
	private int rows;
	private int cols;
	private Complex[][] model;
	
	public ComplexMatrix(int m, int n) {
		this.setRows(m);
		this.setCols(n);
		model = new Complex[m][n];
	}
	
	public ComplexMatrix(Complex[][] matrix) {
		setRows(matrix.length);
		setCols(matrix[0].length);
		model = matrix;
	}
	
	public ComplexMatrix(){
		this(new Complex[][]{{new Complex()}});
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
	
	public Complex[][] getMatrix() {
		return model;
	}

	public void setMatrix(Complex[][] matrix) {
		this.model = matrix;
	}
	
	//Conditions used a lot
	private static boolean notSameSize(ComplexMatrix a, ComplexMatrix b) {
		return a.rows!=b.rows && a.cols!=b.cols;
	}
	
	private static boolean matricesMultipliable(ComplexMatrix a, ComplexMatrix b) {
		return a.cols==b.rows;
	}
	
	//Elementary Operations
	public ComplexMatrix plus(ComplexMatrix matrix) throws MatricesNotSameSizeException {
		if (notSameSize(this,matrix))
			throw new MatricesNotSameSizeException();
		Complex[][] result = new Complex[matrix.rows][matrix.cols];
		for (int i=0; i<matrix.rows; i++) 
			for (int j=0; j<matrix.cols; j++) 
				result[i][j] = this.model[i][j].plus(matrix.model[i][j]);
		return new ComplexMatrix(result);
	}
	
	public ComplexMatrix times(int lambda) {
		Complex[][] result = this.model;
		for (int i=0; i<rows; i++) 
			for (int j=0; j<cols; j++) 
				result[i][j] = model[i][j].times(lambda);
		return new ComplexMatrix(result);
	}
	
	public ComplexMatrix timesHadamard(ComplexMatrix matrix) throws MatricesNotSameSizeException {
		if (notSameSize(this, matrix))
			throw new MatricesNotSameSizeException();
		Complex[][] result = new Complex[rows][cols];
		for (int i=0; i<rows; i++)
			for (int j=0; j<cols; j++)
				result[i][j] = model[i][j].times(matrix.model[i][j]);
		return new ComplexMatrix(result);
	}
	
	public ComplexMatrix timesSchur(ComplexMatrix matrix) throws MatricesNotSameSizeException {
		return timesHadamard(matrix);
	}
	
	public ComplexMatrix opposite() {
		return this.times(-1);						
	}
	
	public ComplexMatrix minus(ComplexMatrix matrix) throws MatricesNotSameSizeException {
		if (notSameSize(this,matrix))
			throw new MatricesNotSameSizeException();
		return this.plus(matrix.opposite());
	}
	
	public ComplexMatrix times(ComplexMatrix matrix) throws MatricesNotMultipliableException {
		if (!matricesMultipliable(this, matrix))
			throw new MatricesNotMultipliableException();
		Complex[][] result = new Complex[rows][matrix.cols];
		for (int i=0; i<rows; i++) 
			for (int j=0; j<matrix.cols; j++) {
				Complex sum = new Complex();
				for (int k=0; k<matrix.rows; k++)
					sum = sum.plus(model[i][k].times(matrix.model[k][j]));
				result[i][j] = sum;
			}
		return new ComplexMatrix(result);
	} 
	
	public Complex det() throws NotSquareMatrixException {
		if (getRows() != getCols())
			throw new NotSquareMatrixException();
		return det(this);
	}
	
	public Complex trace() throws NotSquareMatrixException {
		if (rows!=cols) 
			throw new NotSquareMatrixException();
		Complex trace = new Complex();
		for (int i=0; i<rows; i++)
			trace = trace.plus(model[i][i]);
		return trace;		
	}
	
	public ComplexMatrix transpose() {
		Complex[][] result = new Complex[cols][rows];
		for (int row=0; row<rows; row++)
			for (int col=0; col<cols; col++)
				result[col][row] = model[row][col];
		return new ComplexMatrix(result);
	}
	
	/**
	 * Recursively calculate the matrix's determinant :
	 * - Stop Condition	| square matrix size 2 
	 * - Heredity 		| use formula : det(A) = Σ(σ*pivot*det(minor))
	 * Hypothesis: matrix is a Square Matrix.
	 * @param matrix
	 * @return complex determinant
	 */
	private static Complex det(ComplexMatrix matrix) {
		if (matrix.getRows()==2) {	//if square matrix size 2
			Complex a = matrix.model[0][0],
					b = matrix.model[0][1],
					c = matrix.model[1][0],
					d = matrix.model[1][1];
			return (a.times(d)).minus(b.times(c));
		}
		Complex determinant = new Complex();
		for (int i=0; i<matrix.getRows(); i++) 
			/* we'll use the first column as pivot, and the following formula : det(A) = Σ(σ*pivot*det(minor))
			 * with σ the sign deduced by (-1)^i,
			 * i representing the current pivot,
			 * pivot being the i'th coefficient from the first column,
			 * minor being the minor matrix after removing the pivot cell.
			 */
			determinant = determinant.plus(matrix.model[i][0].times(Math.pow(-1, i)).times(det(matrix.minor(i, 0))));
		return determinant;
	}
	
	private ComplexMatrix minor(int row, int column) {
		ComplexMatrix minor = new ComplexMatrix(rows-1, cols-1);	//because we're deleting 1 row + 1 column
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
	
	public ComplexMatrix forEachVisit(ElementVisitor<Complex> action) {
		for (int i=0; i<rows; i++) 
			for (int j=0; j<cols; j++)
				action.visit(model[i][j]);
		return this;
	}
	
	public ComplexMatrix forEachVisitIndexed(Indexed2DVisitor<Complex> indexedAction) {
		for (int i=0; i<rows; i++)
			for (int j=0; j<cols; j++) 
				indexedAction.visit(i, j, model[i][j]);
		return this;
	}
	
	public ComplexMatrix forEachApply(ElementUpdateVisitor<Complex> action) {
		ComplexMatrix applied = new ComplexMatrix(rows, cols);
		for (int i=0; i<applied.rows; i++)
			for (int j=0; j<applied.cols; j++) 
				applied.model[i][j] = action.visit(model[i][j]);
		return applied;	// return the new result matrix
	}
	
	public ComplexMatrix forEachApplySelf(ElementUpdateVisitor<Complex> action) {
		for (int i=0; i<rows; i++)
			for (int j=0; j<cols; j++) 
				model[i][j] = action.visit(model[i][j]);
		return this;
	}
	
	public static ComplexMatrix get() {
		return new ComplexMatrix(
			new Complex[][] {
				{ new Complex(1),new Complex(2),new Complex(3) },
				{ new Complex(4),new Complex(5),new Complex(6) },
				{ new Complex(7),new Complex(8),new Complex(9) }
			}
		);
	}

	public static void main(String[] args) throws MatricesNotMultipliableException, NotSquareMatrixException {
		ComplexMatrix m = new ComplexMatrix();
		/* Empty matrix : m = new Matrix(3,3); */
		Complex[][] mModel = new Complex[][] {
			{ new Complex(1),new Complex(2,2),new Complex(3,3) },
			{ new Complex(4,4),new Complex(5,5),new Complex(6,6) },
			{ new Complex(7,7),new Complex(8,8),new Complex(9,9) }
		};	
		m = new ComplexMatrix(mModel);
		// Verification Purposes : det(m) = 0 and if u replace first complex 1+i to 1 det(m) = -6
		System.out.println(m.det());
//		System.out.println(m.forEachVisit((elem) -> System.err.println(elem)));
//		System.out.println(m.forEachApply((elem) -> elem.times(new Complex(-1))));
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
