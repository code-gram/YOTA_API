package com.yash.yotaapi.springSecurity;

import lombok.Data;

@Data
public class JwtAuthRequest 
{
     private String username;  //email
     
     private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
     
     
}
