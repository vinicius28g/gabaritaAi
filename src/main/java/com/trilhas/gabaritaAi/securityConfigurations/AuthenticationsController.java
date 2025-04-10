package com.trilhas.gabaritaAi.securityConfigurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trilhas.gabaritaAi.models.Pessoa;
import com.trilhas.gabaritaAi.models.UserRole;
import com.trilhas.gabaritaAi.models.Usuario;
import com.trilhas.gabaritaAi.repository.PessoaRepository;
import com.trilhas.gabaritaAi.repository.UsuarioRepository;
import com.trilhas.gabaritaAi.service.UsuarioService;
import com.trilhas.gabaritaAi.dto.ErroRetorno;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationsController {
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	PessoaRepository pessoaRepository;
	
	@Autowired
	TokenService tokenService;
	@Autowired
	UsuarioService usuarioService;
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody @Valid AuthenticationDto authenticationDto){
		  try {
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(authenticationDto.login(),
                		authenticationDto.pass());
		
		Authentication authenticate = this.authenticationManager
                .authenticate(usernamePasswordAuthenticationToken);
//		var userNamePassword = new UsernamePasswordAuthenticationToken(authenticationDto.login(), authenticationDto.pass());
//		var auth = this.authenticationManager.authenticate(userNamePassword);
		
		var usuario = (Usuario) authenticate.getPrincipal();
		
		var token = tokenService.generateToken(usuario);
		
		var loginResponse = new LoginResponseDto(usuario.getId(), usuario.getLogin(), token,usuario.getRole());
		
		return new ResponseEntity<>(loginResponse, HttpStatus.OK);
		  }catch (org.springframework.security.core.AuthenticationException e) {
		        return new ResponseEntity<>(new ErroRetorno("Usuario ou senha invalido"), HttpStatus.UNAUTHORIZED);
		}
	}
	
	@PostMapping("/registro/Participante")
	public ResponseEntity<?> registroAluno(@RequestBody @Valid ResgistroDto registroUsuario){
		ResponseEntity<?> response = usuarioService.registro(registroUsuario, UserRole.Participante);
		return response;
	}
	
	
	@PostMapping("/registro/adm")
	public ResponseEntity<?> registroAdm(@RequestBody @Valid ResgistroDto registroUsuario){
		ResponseEntity<?> response = usuarioService.registro(registroUsuario, UserRole.ADMIN);
		return response;
	}
	
	@PostMapping("/registro/all")
	public ResponseEntity<?> registroAll(@RequestBody @Valid ResgistroDto registroUsuario){
		if (this.usuarioRepository.findByLogin(registroUsuario.login()) != null)
			return new ResponseEntity<>(new ErroRetorno("login já existente"), HttpStatus.CONFLICT);
		
		UserRole userRole = UserRole.getTipo(registroUsuario.role());
		String encryptePassword = new BCryptPasswordEncoder().encode(registroUsuario.pass());
		Pessoa pessoa = new Pessoa();

		pessoa = registroUsuario.pessoa();
		var usuario = new Usuario(registroUsuario.login(), encryptePassword, userRole, pessoa);
		
		pessoaRepository.save(pessoa);
		usuarioRepository.save(usuario);

		return new ResponseEntity<>(usuario, HttpStatus.CREATED);
		
	}
}
