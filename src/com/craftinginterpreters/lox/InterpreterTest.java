package com.craftinginterpreters.lox;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class InterpreterTest {

	@Test
	public void testInterpretLiteralExpression() {
		Expr.Literal literal = new Expr.Literal(5.0);
		Interpreter interpreter = new Interpreter();

		Object result = null;
		try {
			result = TestReflectionHelper.invokePrivateMethod(interpreter, "evaluate", new Class<?>[] { Expr.class },
					new Object[] { literal });
		} catch (Exception e) {
			e.printStackTrace();
		}

		assertEquals(5.0, result);
	}

	@Test
	public void testInterpretBinaryExpression() throws Exception {
		Expr.Binary binary = new Expr.Binary(new Expr.Literal(3.0), new Token(TokenType.PLUS, "+", null, 1),
				new Expr.Literal(4.0));
		Interpreter interpreter = new Interpreter();

		Object result = TestReflectionHelper.invokePrivateMethod(interpreter, "evaluate", new Class<?>[] { Expr.class },
				new Object[] { binary });

		assertEquals(7.0, result);
	}
}