package com.trilhas.gabaritaAi.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Table(name ="pessoa")
@Entity(name = "pessoa")

public class Pessoa {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotNull
	private String nome;
	@Email
	private String email;
	private String telefone;
	private String cpf;
	private Sexo sexo;
	private Date dataNascimento;
	private String arquivoIdentidade;
	
	@ManyToMany
	@JoinTable(
	    name = "pessoa_trilha",
	    joinColumns = @JoinColumn(name = "pessoa_id"),
	    inverseJoinColumns = @JoinColumn(name = "trilha_id")
	)
	private List<Trilha> trilhas = new ArrayList<>();
	
	
    public Sexo getSexo() {
		return sexo;
	}
	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getArquivoIdentidade() {
		return arquivoIdentidade;
	}
	public void setArquivoIdentidade(String arquivoIdentidade) {
		this.arquivoIdentidade = arquivoIdentidade;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public List<Trilha> getTrilhas() {
		return trilhas;
	}
	public void setTrilhas(List<Trilha> trilhas) {
		this.trilhas = trilhas;
	}


	

}

