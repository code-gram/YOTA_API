package com.yash.yotaapi.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PasswordDto {

    private String emailAdd;

    private String currentPassword;

    private String newPassword;

    private String confirmPassword;
}
