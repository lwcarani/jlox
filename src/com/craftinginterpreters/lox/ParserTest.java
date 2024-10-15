package com.craftinginterpreters.lox;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class ParserTest {

	@Test
	public void testExpression() throws Exception {
		List<Token> tokens = Arrays.asList(new Token(TokenType.NUMBER, "5", 5.0, 1),
				new Token(TokenType.PLUS, "+", null, 1), new Token(TokenType.NUMBER, "3", 3.0, 1),
				new Token(TokenType.EOF, "", null, 1));

		Parser parser = new Parser(tokens);
		Expr expr = (Expr) TestReflectionHelper.invokePrivateMethod(parser, "expression", new Class<?>[] {},
				new Object[] {});

		assertTrue(expr instanceof Expr.Binary);
		Expr.Binary binary = (Expr.Binary) expr;
		assertTrue(binary.left instanceof Expr.Literal);
		assertTrue(binary.right instanceof Expr.Literal);
		assertEquals(TokenType.PLUS, binary.operator.type);
	}

	@Test
	public void testParseVariableDeclaration() {
		List<Token> tokens = Arrays.asList(new Token(TokenType.VAR, "var", null, 1),
				new Token(TokenType.IDENTIFIER, "x", null, 1), new Token(TokenType.EQUAL, "=", null, 1),
				new Token(TokenType.NUMBER, "10", 10.0, 1), new Token(TokenType.SEMICOLON, ";", null, 1),
				new Token(TokenType.EOF, "", null, 1));

		Parser parser = new Parser(tokens);

		List<Stmt> statements = parser.parse();

		assertEquals(1, statements.size());
		assertTrue(statements.get(0) instanceof Stmt.Var);
		Stmt.Var varStmt = (Stmt.Var) statements.get(0);
		assertEquals("x", varStmt.name.lexeme);
		assertTrue(varStmt.initializer instanceof Expr.Literal);
	}
}