package com.yash.yotaapi.service;

import com.yash.yotaapi.domain.YotaUser;

import java.util.List;

public interface UserService {

    public List<YotaUser> findByRoleId(Long roleId);

    /**
     * Finds the user based on the name of user (not the username, just the name)
     *
     * @param name String name, based on which the user will be searched
     * @return Yota User entity object if user found
     * @author yash.raj
     * @see YotaUser Entity class
     * @see com.yash.yotaapi.repository.YotaUserRepository Repository class
     */
    YotaUser getUserByName(String name);

}
