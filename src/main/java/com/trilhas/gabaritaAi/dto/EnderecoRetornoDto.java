package com.trilhas.gabaritaAi.dto;

public record EnderecoRetornoDto(String cep, String rua, String nuemro, String cidade,String estado) {

	public String cep() {
		return cep;
	}

	public String rua() {
		return rua;
	}

	public String nuemro() {
		return nuemro;
	}

	public String cidade() {
		return cidade;
	}

	public String estado() {
		return estado;
	}

}
