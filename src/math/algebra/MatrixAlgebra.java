package math.algebra;

import math.contract.FieldAlgebra;
import math.struct.generic.Matrix;

public class MatrixAlgebra<K> implements FieldAlgebra<Matrix<K>> {
	public int rows, cols;
	private FieldAlgebra<K> algebra;
	
	public MatrixAlgebra(int rows, int cols, FieldAlgebra<K> algebra) {
		setRows(rows);
		setCols(cols);
		this.algebra = algebra;
	}
	
	public MatrixAlgebra<K> setRows(int rows) {
		this.rows = rows;
		return this;
	}
	
	public MatrixAlgebra<K> setCols(int cols) {
		this.cols = cols;
		return this;
	}
	
	@Override
	public Matrix<K> additiveIdentity() {
		return Matrix.of(rows, cols, algebra);
	}

	@Override
	public Matrix<K> multiplicativeIdentity() {
		return Matrix.identity(rows, cols, algebra);
	}

	@Override
	public Matrix<K> add(Matrix<K> a, Matrix<K> b) {
		return a.plus(b);
	}

	@Override
	public Matrix<K> substract(Matrix<K> a, Matrix<K> b) {
		return a.minus(b);
	}

	@Override
	public Matrix<K> multiply(Matrix<K> a, Matrix<K> b) {
		return a.times(b);
	}

	@Override
	public Matrix<K> multiply(Matrix<K> k, double lambda) {
		return k.times(lambda);
	}

	@Override
	public Matrix<K> inverse(Matrix<K> k) {
		return k.inverse();
	}

	@Override
	public Matrix<K> divide(Matrix<K> a, Matrix<K> b) {
		return a.times(b.inverse());
	}

}
