package com.felipe.uolChallenge.exceptions;

import java.io.IOException;

public class InformacoesSobreCadastranteNaoEncontradoException extends IOException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InformacoesSobreCadastranteNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public InformacoesSobreCadastranteNaoEncontradoException(String mensagem, Throwable causa) {
		super(mensagem,causa);
	}

}
