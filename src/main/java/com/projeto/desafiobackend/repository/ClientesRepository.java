package com.projeto.desafiobackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projeto.desafiobackend.model.Clientes;


@Repository
public interface ClientesRepository extends JpaRepository<Clientes, Long> {

	Clientes  findByNomeUsuario(String nomeUsuario);
	boolean existsByCpf(String cpf);
    boolean existsByNomeUsuario(String nomeUsuario);
}
