package com.felipe.uolChallenge.resources;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.felipe.uolChallenge.application.Aplicacao;
import com.felipe.uolChallenge.domain.Cliente;
import com.felipe.uolChallenge.domain.DadosDoCadastrante;
import com.felipe.uolChallenge.services.ClientesService;

@RestController
@RequestMapping("/clientes")
public class ClientesResources {
	
	@Autowired
	ClientesService clientesService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Cliente>> listar(){
		return ResponseEntity.status(HttpStatus.OK).body(clientesService.listar());
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<Cliente>buscar(@PathVariable("id") Long id){
		Cliente cliente = clientesService.buscar(id);
		//cache para diminuir o tempo de processamento, tendo em vista que essa requisicao
		//sera bastante utilizada...
		CacheControl cacheControl = CacheControl.maxAge(20, TimeUnit.SECONDS);
		return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl).body(cliente);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> salvar(@Valid @RequestBody Cliente cliente){
		cliente = clientesService.salvar(cliente);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(cliente.getId()).toUri(); 
		salvarCadastrante(cliente);
		return ResponseEntity.created(uri).build();
	}
	
	public ResponseEntity<Void> salvarCadastrante(@Valid @RequestBody Cliente cliente){
		DadosDoCadastrante dadosDoCadastrante = clientesService.salvarCadastrante(cliente);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(dadosDoCadastrante.getId()).toUri(); 
		
		return ResponseEntity.created(uri).build();
	}
	
	
	@RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletar(@PathVariable("id") Long id){
		clientesService.deletar(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Cliente> atualizar(@RequestBody Cliente autor, @PathVariable("id") Long autorId){
		autor.setId(autorId);
		clientesService.atualizar(autor);
		return ResponseEntity.noContent().build();
	}
	
	
}
