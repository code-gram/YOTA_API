package com.yash.yotaapi.serviceimpl;

import com.yash.yotaapi.domain.UserRole;
import com.yash.yotaapi.domain.YotaUser;
import com.yash.yotaapi.exception.ApplicationException;
import com.yash.yotaapi.repository.UserRoleRepository;
import com.yash.yotaapi.repository.YotaUserRepository;
import com.yash.yotaapi.service.UserAuthService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserAuthServiceImpl implements UserAuthService {

    @Autowired
    private YotaUserRepository yotaUserRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public String registerUser(YotaUser yotaUser) {
        YotaUser user = null; //if user not found, this object will be moved for Garbage collection, keep it null only
        String message = null;
        if (ObjectUtils.isNotEmpty(yotaUser)) {

            user = this
                    .yotaUserRepository
                    .getUserByEmail(yotaUser.getEmailId());

            if (ObjectUtils.isEmpty(user)) {
                //user do not exist, new user will be created

                Optional<UserRole> userRole = userRoleRepository.findById(2L);// Default RoleId as Trainer

                userRole.ifPresent(yotaUser :: setRole);
                yotaUser.setPassword(this.passwordEncoder.encode(yotaUser.getPassword()));

                //reassigned with the new created data
                yotaUser = this
                        .yotaUserRepository
                        .save(yotaUser);

                if (ObjectUtils.isNotEmpty(yotaUser))
                    message = "YOTA User created successfully";
                else
                    message = "YOTA User creation failed";
            } else {
                throw new ApplicationException("User already exists with this email address");
            }
        } else {
            throw new ApplicationException("Invalid user details");
        }
        return message;
    }

    @Override
    public String findUserRoleByUserName(String username) {
        String userRole = null;
        Optional<YotaUser> user = Optional.ofNullable(yotaUserRepository.findByUsername(username));

        if (user.isPresent()) {
            userRole = user.get().getRole() != null ? user.get().getRole().getDescription() : "NotAuthorized";
        }

        return userRole;
    }
}
