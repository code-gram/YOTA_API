package com.yash.yotaapi.service;

import com.yash.yotaapi.domain.YotaUser;

public interface UserAuthService {

    String registerUser(YotaUser yotaUser);

    String findUserRoleByUserName(String username);

}
