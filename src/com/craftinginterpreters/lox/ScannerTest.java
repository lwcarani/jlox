package com.craftinginterpreters.lox;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

public class ScannerTest {

	@Test
	public void testScanSingleToken() {
		Scanner scanner = new Scanner("+");
		List<Token> tokens = scanner.scanTokens();

		assertEquals(2, tokens.size()); // Plus token and EOF
		assertEquals(TokenType.PLUS, tokens.get(0).type);
		assertEquals(TokenType.EOF, tokens.get(1).type);
	}

	@Test
	public void testScanMultipleTokens() {
		Scanner scanner = new Scanner("var x = 10;");
		List<Token> tokens = scanner.scanTokens();

		assertEquals(6, tokens.size()); // var, x, =, 10, ;, EOF
		assertEquals(TokenType.VAR, tokens.get(0).type);
		assertEquals(TokenType.IDENTIFIER, tokens.get(1).type);
		assertEquals(TokenType.EQUAL, tokens.get(2).type);
		assertEquals(TokenType.NUMBER, tokens.get(3).type);
		assertEquals(10.0, tokens.get(3).literal);
		assertEquals(TokenType.SEMICOLON, tokens.get(4).type);
		assertEquals(TokenType.EOF, tokens.get(5).type);
	}

	// Add more test methods as needed
}
