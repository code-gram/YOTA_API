package com.yash.yotaapi.serviceimpl;

import com.yash.yotaapi.domain.YotaUser;
import com.yash.yotaapi.repository.YotaUserRepository;
import com.yash.yotaapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

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

    /**
     * Finds the user based on the name of user (not the username, just the name)
     *
     * @param name String name, based on which the user will be searched
     * @return Yota User entity object if user found
     * @author yash.raj
     * @see YotaUser Entity class
     * @see YotaUserRepository Repository class
     */
    @Override
    public YotaUser getUserByName(String name) {
        return this.yotaUserRepository.getUserByName(name);
    }

}
