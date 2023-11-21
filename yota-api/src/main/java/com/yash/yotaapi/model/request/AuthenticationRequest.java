package com.yash.yotaapi.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@AllArgsConstructor
public class AuthenticationRequest {
    private String username;
    private String password;
}
