package com.yash.yotaapi.controller;

import com.yash.yotaapi.domain.YotaUser;
import com.yash.yotaapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    /**
     * @author pragati.paliwal
     * @param getting name of user against role.
     */
    @Autowired
    private UserService userService;

    @GetMapping("/trainer")
    public List<YotaUser> getTrainerList(Principal principal) {
        return userService.findByRoleId(2l);
    }

}
