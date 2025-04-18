package com.trilhas.gabaritaAi.securityConfigurations;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.trilhas.gabaritaAi.models.Usuario;


@Service
public class TokenService {
	
	public String generateToken(Usuario usuario) {
		try {
			Algorithm algorithm = Algorithm.HMAC256("SECRETO");
			String token = JWT.create()
					.withIssuer("localySeg")
					.withSubject(usuario.getLogin())
					.withExpiresAt(genExpirationDate())
					.sign(algorithm);
			
			return token;
		} catch (JWTCreationException exception) {
			// pode fazer um json pra ficar bonito
			throw new RuntimeException("Erro ao gerar token" + exception);
		}
	}
	
	public String validateToken(String token) {
		try {
			Algorithm algorithm = Algorithm.HMAC256("SECRETO");
			 return JWT.require(algorithm)
					.withIssuer("localySeg")
					.build()
					.verify(token)
					.getSubject();
			
		} catch (JWTCreationException exception) {
			return "";
		}
	}
	
	public Instant genExpirationDate() {
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
	}
}
