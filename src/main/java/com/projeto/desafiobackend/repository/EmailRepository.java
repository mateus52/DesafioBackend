package com.projeto.desafiobackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projeto.desafiobackend.model.Email;

@Repository
public interface EmailRepository extends JpaRepository<Email, Long> {

}
