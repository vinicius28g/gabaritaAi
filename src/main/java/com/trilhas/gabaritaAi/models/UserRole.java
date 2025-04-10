package com.trilhas.gabaritaAi.models;


public enum UserRole {
	ADMIN("Admin", 1),
	Participante("Participante", 2);
	
	private String nome;
	private int codigo;
	
	UserRole(String nome, int codigo) {
		this.nome = nome;
		this.codigo = codigo;
	}

	public String getNome() {
		return this.nome;
	}
	
	public int getCodigo() {
		return this.codigo;
	}
	
	 public static UserRole getTipo(int codigo) {
	    	for (UserRole tipo : UserRole.values()) {
	            if (tipo.codigo == codigo) {
	                return tipo;
	            }
	        }
	        throw new IllegalArgumentException("Número invalido");
	    	
	    }

}
