package com.felipe.uolChallenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.felipe.uolChallenge.domain.Cliente;

public interface ClientesRepository extends JpaRepository<Cliente, Long>{

}
