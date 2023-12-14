package com.yash.yotaapi.serviceimpl;

import com.yash.yotaapi.domain.*;
import com.yash.yotaapi.repository.*;
import com.yash.yotaapi.service.UserAuthService;
import com.yash.yotaapi.util.EncryptionUtilService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAuthServiceImpl implements UserAuthService {

	@Autowired
	private YotaUserRepository yotaUserRepository;

	@Autowired
	private UserRoleRepository userRoleRepository;

	@Autowired
	private TrainingRepository trainingRepository; // Assuming the repository name is 'TrainingRepository'

	@Autowired
	private EncryptionUtilService encryptionUtilService;

	@Override
	public void registerUser(YotaUser yotaUser) {
		yotaUser.setPassword(encryptionUtilService.getEncryptedString(yotaUser.getPassword()));

		Optional<UserRole> role = userRoleRepository.findById(2L); // Default RoleId as Trainer

		if (role.isPresent()) {
			yotaUser.setRole(role.get());
		}

		Optional<Training> training = trainingRepository.findById(1L); // Default training id

		if (training.isPresent()) {
			yotaUser.setTraining(training.get());
		}

		yotaUserRepository.save(yotaUser);
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