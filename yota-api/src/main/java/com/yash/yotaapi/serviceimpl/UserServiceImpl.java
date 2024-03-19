package com.yash.yotaapi.serviceimpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yash.yotaapi.domain.YotaUser;
import com.yash.yotaapi.repository.YotaUserRepository;
import com.yash.yotaapi.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	/**
	 * @author pragati.paliwal
	 * @param getting name of user against role.
	 */
	@Autowired
	private YotaUserRepository yotaUserRepository;
	@Override
	public List<YotaUser> findByRoleId(Long roleId) {
	return yotaUserRepository.findByRoleId(roleId);
		
	}

}
