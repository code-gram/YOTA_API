package com.yash.yotaapi.model.response;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@Builder
public class AuthenticationResponse {
    private String authToken;
}
