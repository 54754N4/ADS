package math.derivation.interpreter;

import java.awt.Dimension;

import math.derivation.ast.Abs;
import math.derivation.ast.Addition;
import math.derivation.ast.Arccos;
import math.derivation.ast.Arcsin;
import math.derivation.ast.Arctan;
import math.derivation.ast.Constant;
import math.derivation.ast.Cos;
import math.derivation.ast.Division;
import math.derivation.ast.E;
import math.derivation.ast.Exp;
import math.derivation.ast.Expression;
import math.derivation.ast.ExpressionTree;
import math.derivation.ast.Ln;
import math.derivation.ast.Log;
import math.derivation.ast.Negation;
import math.derivation.ast.PI;
import math.derivation.ast.Power;
import math.derivation.ast.Product;
import math.derivation.ast.Sin;
import math.derivation.ast.SquareRoot;
import math.derivation.ast.Substraction;
import math.derivation.ast.Tan;
import math.derivation.ast.UnaryOperator;
import math.derivation.ast.Variable;
import struct.tree.generic.wysiwyg.TreeView;

/**
 *  The math grammar was defined with left-associativity and operator precedence in mind, as follows :
expression: binary_additive
binary_additive: binary_multiplicative [(+|-) binary_additive]
binary_multiplicative: binary_exponent [(*|/) binary_multiplicative]
binary_exponent: unary [^ binary_exponent]
unary: |expression| 
    | [-] atom
atom: constant
    | function
    | ( expression )
constant: scalar
	| variable
    | PI
    | E
scalar: number [. number]
	
number: [0-9]+
variable: [a-zA-Z]+[a-zA-Z0-9]*
function: (ACOS|ASIN|ATAN|COS|SIN|TAN|LOG|LN|SQRT) ( expression )

 * 
 */
public class Parser {
	private Lexer lexer;
	private Token current;
	
	public static void main(String[] args) throws ParsingException {
		Parser parser = new Parser(new Lexer("2*x+4*x"));
		Expression exp = parser.parse();
		ExpressionTree tree = ExpressionTree.from(exp);
		TreeView.displayValues(new Dimension(2000,1000), tree);
//		tree.simplify();
//		TreeView.displayValues(new Dimension(600,1000), tree);
	}
	
	public Parser(Lexer lexer) {
		this.lexer = lexer;
		current = lexer.nextToken();
	}
	
	public void consume(Type type) throws ParsingException {
		if (current.getType().equals(type)) 
			current = lexer.nextToken();
		else throw new ParsingException("Expected "+type);
	}
	
	private boolean isConstant() {
		return (current.getType().equals(Type.PI) 
			|| current.getType().equals(Type.NUMBER)
			|| current.getType().equals(Type.E)
			|| current.getType().equals(Type.VAR));
	}
	
	private boolean isFunction() {
		switch (current.getType()) {
			case ACOS: case ASIN: case ATAN: 
			case COS: case SIN: case TAN: 
			case E: case LN: case LOG:
			case BAR: case SQRT: 
				return true;
			default: 
				return false;
		}
	}
	
	private Type consumeIfFunction() throws ParsingException {
		Type type = current.getType();
		switch (current.getType()) {
			case ACOS: case ASIN: case ATAN: 
			case COS: case SIN: case TAN: 
			case LN: case LOG:case SQRT: 
				break;	// is valid
			default: throw new ParsingException("Expected function name");			
		}
		consume(current.getType());
		return type;
	}
	
	private boolean isAdditive() {
		switch (current.getType()) {
			case PLUS: case MINUS: return true;
			default: return false;
		}
	}
	
	private boolean isMultiplicative() {
		switch (current.getType()) {
			case TIMES: case DIVIDE: return true;
			default: return false;
		}
	}
	
	// Production Rules
	
	private UnaryOperator function() throws ParsingException {
		Type type = consumeIfFunction();
		consume(Type.LPAREN);
		Expression exp = expression();
		consume(Type.RPAREN);
		switch (type) {
			case ACOS: return new Arccos(exp);
			case ASIN: return new Arcsin(exp);
			case ATAN: return new Arctan(exp);
			case COS: return new Cos(exp);
			case SIN: return new Sin(exp);
			case TAN: return new Tan(exp);
			case LN: return new Ln(exp);
			case LOG: return new Log(exp);
			case SQRT: return new SquareRoot(exp);
			default: throw new ParsingException("Expected function name");
		}
	}
	
	private Variable variable() throws ParsingException {
		Variable v = new Variable(current.getValue());
		consume(Type.VAR);
		return v;
	}
	
	private Constant scalar() throws ParsingException {
		String scalar = current.getValue();
		consume(Type.NUMBER);
		if (current.getType().equals(Type.DOT)) {
			scalar += ".";
			consume(Type.DOT);
			scalar += current.getValue();
			consume(Type.NUMBER);
		}
		return new Constant(scalar);
	}
	
	private Expression constant() throws ParsingException {
		if (current.getType().equals(Type.NUMBER))  
			return scalar();
		else if (current.getType().equals(Type.VAR)) 
			return variable();
		else if (current.getType().equals(Type.PI)) {
			consume(Type.PI);
			return new PI();
		} else if (current.getType().equals(Type.E)) {
			consume(Type.E);
			return new E();
		} throw new ParsingException("Expected a constant");
	}
	
	private Expression atom() throws ParsingException {
		if (isConstant()) return constant();
		else if (isFunction()) return function();
		else if (current.getType().equals(Type.LPAREN)) {
			consume(Type.LPAREN);
			Expression exp = expression();
			consume(Type.RPAREN);
			return exp;
		}
		throw new ParsingException("Invalid atom starting token type : "+current.getType());
	}
	
	private Expression unary() throws ParsingException {
		if (current.getType().equals(Type.BAR)) {
			consume(Type.BAR);
			Expression exp = expression();
			consume(Type.BAR);
			return new Abs(exp);
		} else if (current.getType().equals(Type.MINUS)) {
			consume(Type.MINUS);
			Expression exp = atom();
			return new Negation(exp);
		} return atom();
	}
	
	private Expression binary_exponent() throws ParsingException {
		Expression exp = unary();
		if (current.getType().equals(Type.POWER)) {
			consume(Type.POWER);
			if (exp instanceof E) 
				exp = new Exp(binary_exponent());
			else 
				exp = new Power(exp, binary_exponent());
		}
		return exp;
	}
	
	private Expression binary_multiplicative() throws ParsingException {
		Expression exp = binary_exponent();
		if (isMultiplicative()) {
			if (current.getType().equals(Type.TIMES)) {
				consume(Type.TIMES);
				exp = new Product(exp, binary_multiplicative());
			} else {	// division
				consume(Type.DIVIDE);
				exp = new Division(exp, binary_multiplicative());
			}
		}
		return exp;
	}
	
	private Expression binary_additive() throws ParsingException {
		Expression exp = binary_multiplicative();
		if (isAdditive()) {
			if (current.getType().equals(Type.PLUS)) {
				consume(Type.PLUS);
				exp = new Addition(exp, binary_additive());
			} else {	// substraction
				consume(Type.MINUS);
				exp = new Substraction(exp, binary_additive());
			}
		}
		return exp;
	}
	
	private Expression expression() throws ParsingException {
		return binary_additive();
	}

	public Expression parse() throws ParsingException {
		return expression();
	}
}

class ParsingException extends Exception {
	private static final long serialVersionUID = -7037267993327981069L;
	
	public ParsingException(String string) {
		super(string);
	}
}