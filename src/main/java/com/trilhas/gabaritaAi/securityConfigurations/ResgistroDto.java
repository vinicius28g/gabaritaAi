package com.trilhas.gabaritaAi.securityConfigurations;

import com.trilhas.gabaritaAi.models.Endereco;
import com.trilhas.gabaritaAi.models.Pessoa;

public record ResgistroDto(Long id ,String login, String pass, Pessoa pessoa, int role, Endereco endereco, Long trilhaId) {

	public Long id() {
		return id;
	}

	public String login() {
		return login;
	}

	public String pass() {
		return pass;
	}

	public Pessoa pessoa() {
		return pessoa;
	}

	public int role() {
		return role;
	}

	public Endereco endereco() {
		return endereco;
	}

	public Long trilhaId() {
		return trilhaId;
	}

	

//	public long cursoId() {
//		return cursoId;
//	}
	
	

}
