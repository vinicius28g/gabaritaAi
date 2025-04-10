package com.trilhas.gabaritaAi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.trilhas.gabaritaAi.models.Pessoa;
import com.trilhas.gabaritaAi.models.UserRole;
import com.trilhas.gabaritaAi.models.Usuario;



public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
UserDetails findByLogin(String login);
	
	List<Usuario>findAllByRole(UserRole role);
	
	Usuario findByPessoa(Pessoa pessoa);


}
