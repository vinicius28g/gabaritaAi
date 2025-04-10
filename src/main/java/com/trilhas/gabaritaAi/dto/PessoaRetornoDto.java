package com.trilhas.gabaritaAi.dto;

import java.util.Date;

public record PessoaRetornoDto(String nome, Date dataNascimento, String cpf, int sexo, String email, String telefone) {

	public String nome() {
		return nome;
	}

	public Date dataNascimento() {
		return dataNascimento;
	}

	public String cpf() {
		return cpf;
	}

	public int sexo() {
		return sexo;
	}

	public String email() {
		return email;
	}

	public String telefone() {
		return telefone;
	}

}
