package com.projeto.desafiobackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projeto.desafiobackend.model.Telefones;

@Repository
public interface TelefonesRepository extends JpaRepository<Telefones, Long> {

}
