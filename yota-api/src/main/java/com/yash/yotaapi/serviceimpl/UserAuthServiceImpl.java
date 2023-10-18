package com.yash.yotaapi.serviceimpl;

import com.yash.yotaapi.domain.Batch;
import com.yash.yotaapi.domain.UserRole;
import com.yash.yotaapi.domain.YotaUser;
import com.yash.yotaapi.repository.BatchRepository;
import com.yash.yotaapi.repository.UserRoleRepository;
import com.yash.yotaapi.repository.YotaUserRepository;
import com.yash.yotaapi.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.OptionalInt;

@Service
public class UserAuthServiceImpl implements UserAuthService {

    @Autowired
    private YotaUserRepository yotaUserRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private BatchRepository batchRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public void registerUser(YotaUser yotaUser) {
        yotaUser.setPassword(passwordEncoder.encode(yotaUser.getPassword()));
        Optional<UserRole> role = userRoleRepository.findById(3l); //Added default batch id.
        if(role.isPresent()){
            yotaUser.setRole(role.get());
        }
        Optional<Batch> batch = batchRepository.findById(1l);//Added default batch id.
        if(batch.isPresent()){
            yotaUser.setBatch(batch.get());
        }
        yotaUserRepository.save(yotaUser);
    }
}
