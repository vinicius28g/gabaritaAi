package com.trilhas.gabaritaAi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.trilhas.gabaritaAi.dto.ErroRetorno;
import com.trilhas.gabaritaAi.models.Endereco;
import com.trilhas.gabaritaAi.models.Pessoa;
import com.trilhas.gabaritaAi.models.Trilha;
import com.trilhas.gabaritaAi.models.UserRole;
import com.trilhas.gabaritaAi.models.Usuario;
import com.trilhas.gabaritaAi.repository.EnderecoRepository;
import com.trilhas.gabaritaAi.repository.PessoaRepository;
import com.trilhas.gabaritaAi.repository.TrilhaRepositry;
import com.trilhas.gabaritaAi.repository.UsuarioRepository;
import com.trilhas.gabaritaAi.securityConfigurations.ResgistroDto;

@Service
public class UsuarioService {
	@Autowired
	private PessoaRepository pessoaRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private TrilhaRepositry trilhaRepositry;
	

	public ResponseEntity<?> registro(ResgistroDto registro, UserRole role) {
		Pessoa pessoa = null;
		if (this.usuarioRepository.findByLogin(registro.login()) != null)
			return new ResponseEntity<>(new ErroRetorno("login já existente"), HttpStatus.CONFLICT);
		
		if(registro.id()!=null && registro.id()>0) {
			
		}
		
		String encryptePassword = new BCryptPasswordEncoder().encode(registro.pass());
		
		
//		pessoa = pessoaRepository.findByNome(registro.pessoa().getNome());
//		if(pessoa == null) {
//			pessoa = new Pessoa();
//			pessoa = registro.pessoa();	
//		}
		Endereco endereco = new Endereco();
		pessoa = new Pessoa();
		pessoa = registro.pessoa();	
		
		if(registro.trilhaId()!= null && registro.trilhaId()>0) {
			Trilha tirlha = trilhaRepositry.findById(registro.trilhaId()).get();
			pessoa.getTrilhas().add(tirlha);
		}else {
			return new ResponseEntity<>(new ErroRetorno("Selecione uma trilha"), HttpStatus.CONFLICT);
		}

		
		endereco = registro.endereco();
		var usuario = new Usuario(registro.login(), encryptePassword, role, pessoa);
		endereco.setPessoa(pessoa);
		pessoaRepository.save(pessoa);
		enderecoRepository.save(endereco);
		usuarioRepository.save(usuario);

		return new ResponseEntity<>(usuario, HttpStatus.CREATED);
	}
	
	public ResponseEntity<?> editar(ResgistroDto registro, UserRole role) {
		
		Optional<Usuario> usuarioOptional = usuarioRepository.findById(registro.id());
		
		Usuario usuario = usuarioOptional.get();
		
		Usuario usuCompare = (Usuario) usuarioRepository.findByLogin(registro.login());

		if (usuCompare != null && usuCompare != usuario ) {
			return new ResponseEntity<>(new ErroRetorno("login já existente"), HttpStatus.CONFLICT);
		}
		
		
		String encryptePassword = new BCryptPasswordEncoder().encode(registro.pass());
		Pessoa pessoa =registro.pessoa() ;
		
		pessoa.setId(usuario.getPessoa().getId());
		usuario.setLogin(registro.login());
		usuario.setPass(encryptePassword);
		usuario.setRole(role);
		usuario.setPessoa(pessoa);
		
		pessoaRepository.save(pessoa);
		usuarioRepository.save(usuario);

		return new ResponseEntity<>(usuario, HttpStatus.OK);
	}

}
