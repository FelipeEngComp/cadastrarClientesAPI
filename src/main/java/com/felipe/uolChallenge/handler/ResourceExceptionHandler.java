package com.felipe.uolChallenge.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.felipe.uolChallenge.domain.DetalhesErro;
import com.felipe.uolChallenge.exceptions.ClienteNaoEncontradoException;
import com.felipe.uolChallenge.exceptions.InformacoesSobreCadastranteNaoEncontradoException;



public class ResourceExceptionHandler {
	@ExceptionHandler(ClienteNaoEncontradoException.class)
	public ResponseEntity<DetalhesErro> handlerLivroNaoEncontradoException
									(ClienteNaoEncontradoException e, HttpServletRequest request) {
		
		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(404l);
		erro.setTitulo("O livro não pôde ser encontrado");
		erro.setMensagemDesenvolvedor("http://erros.socialbooks.com/404");
		erro.setTimestamp(System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
	
	@ExceptionHandler(InformacoesSobreCadastranteNaoEncontradoException.class)
	public ResponseEntity<DetalhesErro> handlerInformacoesSobreCadastranteNaoEncontradoException
									(InformacoesSobreCadastranteNaoEncontradoException e, HttpServletRequest request) {
		
		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(404l);
		erro.setTitulo("As informações sobre a cidade e clima do cadastrante não foram encontradas, porém o processo irá continuar.");
		erro.setMensagemDesenvolvedor("http://erros.socialbooks.com/404");
		erro.setTimestamp(System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
}
