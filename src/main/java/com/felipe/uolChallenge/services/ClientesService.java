package com.felipe.uolChallenge.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.felipe.uolChallenge.domain.Cliente;
import com.felipe.uolChallenge.exceptions.ClienteNaoEncontradoException;
import com.felipe.uolChallenge.repository.ClientesRepository;

/*
 *author Felipe Ribeiro Rosa Alves
 * 
 * 
 * */


@Service
public class ClientesService {
	@Autowired
	ClientesRepository clientesRepository;
	
	public List<Cliente> listar() {
		return clientesRepository.findAll();
	}
	
	public Cliente buscar(Long clienteId) {
		Optional<Cliente> cliente = clientesRepository.findById(clienteId);
		
		if (cliente.hashCode() == 0) {
			throw new ClienteNaoEncontradoException("Não foi possível encontrar esse clinte");
		}	
		
		return cliente.get();
	}
	
	public Cliente salvar(Cliente cliente){
		return clientesRepository.save(cliente);
	}
	
	public void deletar(Long clienteId) {
		verificaExistencia(clienteId);
		clientesRepository.deleteById(clienteId);;
	}
	
	public void atualizar(Cliente cliente) {
		verificaExistencia(cliente.getId());
		salvar(cliente);
	}

	private void verificaExistencia(Long clienteId) {
		buscar(clienteId);
	}
	
	
}
