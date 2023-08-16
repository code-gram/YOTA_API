package com.yash.yotaapi.springSecurity;

import lombok.Data;

@Data
public class JWTAuthResponse 
{
    private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
    
    
}
