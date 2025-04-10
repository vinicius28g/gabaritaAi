package com.trilhas.gabaritaAi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trilhas.gabaritaAi.models.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
	Pessoa findByNome(String nome);

}
