package com.yash.yotaapi.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yash.yotaapi.domain.User;
import com.yash.yotaapi.repository.UserRepository;
import com.yash.yotaapi.service.UserService;


@Service
public class UserServiceImpl implements UserService
{
	@Autowired
	UserService userService;
	
	@Autowired
	UserRepository userRepo;

	@Override
	public List<User> getAllUser() 
	{
		return userRepo.findAll();
	}

	@Override
	public Optional<User> getUserById(int id) 
	{
		return userRepo.findById(id);
	}

	@Override
	public User saveUser(User user) 
	{
		return userRepo.save(user);
	}

	@Override
	public User updateUser(User user) 
	{
		return userRepo.save(user);
	}

	@Override
	public void deleteUser(Integer uId) 
	{
		userRepo.deleteById(uId);;
	}

}
