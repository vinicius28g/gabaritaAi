package com.trilhas.gabaritaAi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trilhas.gabaritaAi.models.Endereco;
import com.trilhas.gabaritaAi.models.Pessoa;

public interface EnderecoRepository extends JpaRepository<Endereco, Long>{
	
	List<Endereco> findByPessoa(Pessoa pessoa);


}
