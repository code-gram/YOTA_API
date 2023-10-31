package com.yash.yotaapi.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class EncryptionUtilService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    public String getEncryptedString(String input) {
        return passwordEncoder.encode(input);
    }
}
