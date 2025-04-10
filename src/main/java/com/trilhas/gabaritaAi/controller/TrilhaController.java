package com.trilhas.gabaritaAi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.trilhas.gabaritaAi.dto.EnderecoRetornoDto;
import com.trilhas.gabaritaAi.dto.PessoaRetornoDto;
import com.trilhas.gabaritaAi.dto.RetornoDados;
import com.trilhas.gabaritaAi.models.Endereco;
import com.trilhas.gabaritaAi.models.Pessoa;
import com.trilhas.gabaritaAi.models.Trilha;
import com.trilhas.gabaritaAi.models.Usuario;
import com.trilhas.gabaritaAi.repository.EnderecoRepository;
import com.trilhas.gabaritaAi.repository.PessoaRepository;
import com.trilhas.gabaritaAi.repository.TrilhaRepositry;

@Controller
@RequestMapping("/trilha")
public class TrilhaController {
	@Autowired
	PessoaRepository pessoaRepository;
	@Autowired
	EnderecoRepository enderecoRepository;
	@Autowired
	TrilhaRepositry trilhaRepositry;
	
	
	@GetMapping("/getdados")
	public ResponseEntity<?> getDados(@AuthenticationPrincipal Usuario usuario){
		try {
			Pessoa pessoa = pessoaRepository.findById(usuario.getPessoa().getId()).orElseThrow();
			Endereco enderecoPrinciapal = enderecoRepository.findByPessoa(pessoa).get(0);
			// como atualemnte a aplicacao so permite um cadastro por inscrição é pego a primeira ocorrencia mas é uma funcionalidade que deve ser melhorada posteriomente
			Trilha trilha = pessoa.getTrilhas().get(0);
			PessoaRetornoDto pessoaRetorno =  new PessoaRetornoDto(pessoa.getNome(), pessoa.getDataNascimento(), pessoa.getCpf(), pessoa.getSexo().getCodigo(), pessoa.getEmail(), pessoa.getTelefone());
			EnderecoRetornoDto endereco = new EnderecoRetornoDto(enderecoPrinciapal.getCep(), enderecoPrinciapal.getRua(), enderecoPrinciapal.getNumero(), enderecoPrinciapal.getCidade(), enderecoPrinciapal.getEstado());
			RetornoDados retornoDados = new RetornoDados(trilha.getId(), pessoaRetorno, endereco);
		
			return new ResponseEntity<>(retornoDados, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(new Error("erro ao processa os dados"), HttpStatus.CONFLICT);
		}
	}
	

}
