package com.trilhas.gabaritaAi.dto;


public record RetornoDados(long trilhaId,PessoaRetornoDto pessoa, EnderecoRetornoDto emdereco) {

	public long trilhaId() {
		return trilhaId;
	}

	public PessoaRetornoDto pessoa() {
		return pessoa;
	}

	public EnderecoRetornoDto emdereco() {
		return emdereco;
	}

	
	
}
