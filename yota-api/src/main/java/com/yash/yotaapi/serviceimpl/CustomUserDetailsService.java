package com.yash.yotaapi.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.yash.yotaapi.domain.User;
import com.yash.yotaapi.repository.UserRepository;


@Service
public class CustomUserDetailsService implements UserDetailsService
{

	@Autowired
	UserRepository userRepo;
	 
	@Override
	public UserDetails loadUserByUsername(String userName)
	{
		//User user = this.userRepo.findByEmail(userName).orElseThrow(() -> new ResourceNotFoundException("user","email : "+userName, 0));
    	User user = this.userRepo.findByEmail(userName).get();
    	    
	    return user;
	}

}
