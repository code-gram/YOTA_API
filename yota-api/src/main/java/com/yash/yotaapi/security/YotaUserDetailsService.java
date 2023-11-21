package com.yash.yotaapi.security;

import com.yash.yotaapi.domain.UserRole;
import com.yash.yotaapi.domain.YotaUser;
import com.yash.yotaapi.repository.YotaUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class YotaUserDetailsService implements UserDetailsService {

    @Autowired
    YotaUserRepository yotaUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        YotaUser yotaUser = yotaUserRepository.findByUsername(username);
        SimpleGrantedAuthority yotauserauthorities = new SimpleGrantedAuthority(yotaUser.getRole().getDescription());
        return new User(yotaUser.getUsername(), yotaUser.getPassword(), Collections.singleton(yotauserauthorities));
    }
}
