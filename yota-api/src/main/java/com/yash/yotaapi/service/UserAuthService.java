package com.yash.yotaapi.service;

import java.util.List;

import com.yash.yotaapi.domain.YotaUser;

public interface UserAuthService {
    public void registerUser(YotaUser yotaUser);
    public String findUserRoleByUserName(String username);
   
}
