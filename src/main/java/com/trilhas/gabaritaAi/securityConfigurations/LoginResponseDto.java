package com.trilhas.gabaritaAi.securityConfigurations;

import com.trilhas.gabaritaAi.models.UserRole;

public record LoginResponseDto(long id,String login,String token, UserRole role) {


	public long id() {
		return id;
	}

	public String login() {
		return login;
	}

	public String token() {
		return token;
	}

	public UserRole role() {
		return role;
	}
	
	

}
