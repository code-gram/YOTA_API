package com.yash.yotaapi.service;

import java.util.List;

import com.yash.yotaapi.domain.YotaUser;

public interface UserService {
	
    public List<YotaUser> findByRoleId(Long roleId);

}
