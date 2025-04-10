package com.trilhas.gabaritaAi.models;

public enum Sexo {
	Masculino("Masculino", 1),
	Feminino("Feminino",2),
	Outro("Outro", 3);
	
	private String nome;
	private int codigo;
	
	Sexo(String nome, int codigo){
		this.nome = nome;
		this.codigo = codigo;
	}
	

	 public String getNome() {
		return nome;
	}



	public int getCodigo() {
		return codigo;
	}


	


	public static Sexo getTipo(int codigo) {
		    	for (Sexo sexo : Sexo.values()) {
		            if (sexo.codigo == codigo) {
		                return sexo;
		            }
		        }
		        throw new IllegalArgumentException("Número invalido");
		    	
		    }
	
	
}
