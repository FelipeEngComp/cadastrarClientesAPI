package com.felipe.uolChallenge.exceptions;

public class ClienteNaoEncontradoException extends RuntimeException{
	public ClienteNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public ClienteNaoEncontradoException(String mensagem, Throwable causa) {
		super(mensagem,causa);
	}
}
