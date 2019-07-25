package math.struct.generic;

import java.util.Random;

import math.algebra.AlphanumeralAlgebra;
import math.struct.generic.Matrix.MatricesNotMultipliableException;
import math.struct.generic.Matrix.NotSquareMatrixException;

public class TestMatrix {
	public static void main(String[] args) throws NotSquareMatrixException, MatricesNotMultipliableException {
		// Test complex matrices
		Matrix<Complex> m = Matrix.getComplex();
		System.out.println(m.det());
		m.forEachVisitIndexed((i,j, elem) -> System.out.println(String.format("(%d,%d) -> %s", i,j,elem)));
		System.out.println(m.forEachApply((elem) -> elem.conjugate()));	// complex
		System.out.println(m);
		
		// Test matrices of doubles
		Matrix<Double> m1 = Matrix.doubleIdentity(4);
		System.out.println(m1.det());
		System.out.println(m1.forEachApply((elem) -> -elem));	// doubles
		System.out.println(m1);
		
		// Test ints
		Matrix<Integer> m2 = Matrix.ofIntegers(4, 4);
		m2.forEachApplySelfIndexed((i,j, elem) -> i+j);
		System.out.println(m2);
		
		// Idk yet why anyone would wanna use the ones below ..
		Matrix<Boolean> ibool = Matrix.booleanIdentity(4);
		System.out.println(ibool);
		System.out.println(ibool.times(ibool).equals(ibool));	// verify identity
		Matrix<Boolean> m3 = Matrix.ofBooleans(4, 4);
		m3.forEachApplySelf((bool) -> new Random().nextBoolean());	// random bool matrix
		System.out.println(m3);
		System.out.println(!m3.times(m3).equals(m3));
		System.out.println(m3.times(Matrix.booleanIdentity(4)).equals(m3));

		// Test matrix of strings
		Matrix<String> istring = Matrix.stringIdentity(4);
		System.out.println(istring);
		System.out.println(istring.times(istring).equals(istring));
		Matrix<String> m4 = Matrix.ofStrings(4, 4);
		m4.forEachApplySelfIndexed((i,j, elem) -> (i==j)?""+AlphanumeralAlgebra.get(i+j):"");
		System.out.println(m4);
		System.out.println(m4.times(Matrix.stringIdentity(4)));
		System.out.println(m4.times(Matrix.stringIdentity(4)).equals(m4));
		
		// Test char matrices
		Matrix<Character> cidentity = Matrix.characterIdentity(3);
		System.out.println(cidentity);
		System.out.println(cidentity.times(cidentity));
		System.out.println(cidentity.times(cidentity).equals(cidentity));	// true so identity is correct
		Matrix<Character> m5 = Matrix.ofCharacters(3, 3);
		m5.forEachApplySelfIndexed((i,j,c) -> (char)('a'+new Random().nextInt('z'-'a')));
		System.out.println(m5);
		System.out.println(m5.times(cidentity));
		System.out.println(m5.times(cidentity).equals(m5));
		
		// Test char matrices
		Matrix<Character> aidentity = Matrix.alphanumeralIdentity(3);
		System.out.println(aidentity);
		System.out.println(aidentity.times(aidentity));
		System.out.println(aidentity.times(aidentity).equals(aidentity));	// true so identity is correct
		Matrix<Character> m6 = Matrix.ofCharacters(3, 3);
		m6.forEachApplySelfIndexed((i,j,c) -> (char)('a'+new Random().nextInt('z'-'a')));
		System.out.println(m6);
		System.out.println(m6.times(aidentity));
		System.out.println(m6.times(aidentity).equals(m6));
	}
}
