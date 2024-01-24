package com.yash.yotaapi.security;
 
import com.yash.yotaapi.domain.YotaUser;
import com.yash.yotaapi.repository.YotaUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
 
import java.util.Collections;
 
@Service
public class YotaUserDetailsService implements UserDetailsService {
 
	@Autowired
	YotaUserRepository yotaUserRepository;
 
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		YotaUser yotaUser = yotaUserRepository.findByUsername(username);
		if (yotaUser == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
 
		GrantedAuthority authority = new SimpleGrantedAuthority(yotaUser.getRole().getDescription());
		return org.springframework.security.core.userdetails.User.withUsername(yotaUser.getUsername())
				.password(yotaUser.getPassword()).authorities(Collections.singletonList(authority))
				.accountExpired(false).accountLocked(false).credentialsExpired(false).disabled(false).build();
	}
 
}