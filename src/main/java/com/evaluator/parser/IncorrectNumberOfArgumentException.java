package com.evaluator.parser;

/**
 * @author shrikrushna on 03/07/20
 */
public class IncorrectNumberOfArgumentException extends RuntimeException {

	private String token;

	public IncorrectNumberOfArgumentException(String token) {
		super("Incorrect number of arguments passed to operator/function " + token);
		this.token = token;
	}

	public String getToken() {
		return token;
	}
}