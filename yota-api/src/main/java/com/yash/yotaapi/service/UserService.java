package com.yash.yotaapi.service;

import java.util.List;
import com.yash.yotaapi.domain.User;
import java.util.Optional;

public interface UserService 
{
	List<User> getAllUser();

	Optional<User> getUserById(int id);

	User saveUser(User user);

	User updateUser(User user);

	void deleteUser(Integer uId);

}
