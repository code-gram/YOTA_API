package com.yash.yotaapi.services.impls;

import com.yash.yotaapi.constants.AppConstants;
import com.yash.yotaapi.constants.UserAccountStatusTypes;
import com.yash.yotaapi.constants.UserRoleTypes;
import com.yash.yotaapi.dto.PageResponseDto;
import com.yash.yotaapi.dto.PasswordDto;
import com.yash.yotaapi.dto.UserProfileDto;
import com.yash.yotaapi.dto.UserRoleDto;
import com.yash.yotaapi.dto.YotaUserDto;
import com.yash.yotaapi.entity.YotaUser;
import com.yash.yotaapi.exceptions.ApplicationException;
import com.yash.yotaapi.exceptions.PasswordMismatchException;
import com.yash.yotaapi.repositories.YotaUserRepository;
import com.yash.yotaapi.services.IServices.IUserRoleService;
import com.yash.yotaapi.services.IServices.IYOTAUserService;
import com.yash.yotaapi.util.PaginationUtility;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Project Name - YOTASecurityPOC
 * <p>
 * IDE Used - IntelliJ IDEA
 *
 * @author - yashr
 * @since - 02-04-2024
 */
@Service
public class YOTAUserServiceImpl implements IYOTAUserService {

	@Autowired
	private YotaUserRepository userRepository;

	@Autowired
	private IUserRoleService userRoleService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
	public String createNewUser(YotaUserDto userDto) {
		YotaUser user = null;
		String message = null;
		if (ObjectUtils.isNotEmpty(userDto)) {
			if (validateEmail(userDto.getEmailAdd())) {
				if (validatePassword(userDto)) {
					user = this.userRepository.getUserByEmail(userDto.getEmailAdd());
					if (ObjectUtils.isEmpty(user)) {
						// user do not exist, new user will be created
						if (StringUtils.equals(userDto.getPassword(), userDto.getConfirmPassword())) {

							if (ObjectUtils.isNotEmpty(userDto.getEmpId())) {
								if (String.valueOf(userDto.getEmpId()).length() == 6) {
									UserRoleDto userRoleDto = this.userRoleService
											.getUserRoleByRoleName(UserRoleTypes.ROLE_ASSOCIATE.toString());

									userDto.setUserRole(userRoleDto);

									user = this.modelMapper.map(userDto, YotaUser.class);

									user.setPassword(this.passwordEncoder.encode(userDto.getPassword()));
									user.setAccountStatus(UserAccountStatusTypes.PENDING);

									// reassigned with the new created data
									user = this.userRepository.save(user);

									if (ObjectUtils.isNotEmpty(user))
										message = AppConstants.NEW_USER_REGISTRATION_SUCCESS_MESSAGE;
									else
										message = "YOTA User creation failed";

								} else {
									throw new ApplicationException("Employee ID must be a 6-digit integer.");
								}
							} else
								throw new ApplicationException(
										"Employee Id is empty or null, please enter valid employee id");
						} else
							throw new ApplicationException("Password did not matched, please try again");
					} else
						throw new ApplicationException("User already exists with this email address");
				} else
					throw new ApplicationException("Password is empty or null, please enter valid password.");
			} else
				throw new ApplicationException("Email must contain @ and end with @yash.com.");
		} else
			throw new ApplicationException("Invalid user details");
		return message;
	}

	@Override
	public YotaUserDto getUserByEmailAdd(String emailAdd) {
		YotaUserDto userDto = null;
		YotaUser user = null;

		if (StringUtils.isNotEmpty(emailAdd)) {
			user = this.userRepository.getUserByEmail(emailAdd);

			userDto = this.modelMapper.map(user, YotaUserDto.class);
		} else {
			throw new ApplicationException("Email add is empty");
		}
		return userDto;
	}

	@Override
	public List<YotaUserDto> getAllTrainers() {
		List<YotaUser> allTrainers = this.userRepository.findAllUsersByRole(UserRoleTypes.ROLE_TRAINER.toString());
		if (!allTrainers.isEmpty()) {
			return allTrainers.stream().map(yur -> this.modelMapper.map(yur, YotaUserDto.class))
					.collect(Collectors.toList());
		} else
			throw new ApplicationException("No Trainers found !");
	}

	@Override
	public List<YotaUserDto> getAllAssociates() {
		List<YotaUser> allTrainers = this.userRepository.findAllUsersByRole(UserRoleTypes.ROLE_ASSOCIATE.toString());
		if (!allTrainers.isEmpty()) {
			return allTrainers.stream().map(yur -> this.modelMapper.map(yur, YotaUserDto.class))
					.collect(Collectors.toList());
		} else
			throw new ApplicationException("No Associates found !");
	}

	@Override
	public List<YotaUserDto> getAllPendingUsers() {
		List<YotaUser> pendingUsers = this.userRepository.getAllPendingUsers();
		return pendingUsers.stream().map(penUser -> this.modelMapper.map(penUser, YotaUserDto.class))
				.collect(Collectors.toList());
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
	public Boolean approvePendingUser(String emailAdd) {
		if (StringUtils.isNotEmpty(emailAdd)) {
			Integer status = this.userRepository.approvePendingUser(emailAdd);
			if (status == 1)
				return true;
			else
				throw new ApplicationException("User not approved");
		} else {
			throw new ApplicationException("Email address is empty.");
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
	public Boolean declinePendingUser(String emailAdd, String reason) {
		if (StringUtils.isNotEmpty(emailAdd)) {
			Integer status = this.userRepository.declinePendingUser(emailAdd, reason);
			if (status == 1)
				return true;
			else
				throw new ApplicationException("Failed while declining user account");
		} else {
			throw new ApplicationException("Email address is empty.");
		}
	}

	@Override
	public PageResponseDto getAllAssociatesByStatus(String status, Integer pageNumber, Integer pageSize) {
		List<YotaUser> user = null;
		if (StringUtils.isNotEmpty(status)) {
			UserAccountStatusTypes userAccountStatusTypes = UserAccountStatusTypes.valueOf(status);
			user = this.userRepository.getAllUserByStatus(userAccountStatusTypes);
			if (CollectionUtils.isEmpty(user)) {
				throw new ApplicationException("No associates found with the provided status : " + status);
			}
			try {
				List<YotaUserDto> yotaUserDto = user.stream().filter(
						users -> users.getUserRole().getRoleTypes().equals(UserRoleTypes.ROLE_ASSOCIATE.toString()))
						.map(users -> modelMapper.map(users, YotaUserDto.class)).collect(Collectors.toList());
				// adding paginate method logic
				Page<YotaUserDto> paginate = PaginationUtility.paginate(yotaUserDto, pageNumber, pageSize);
				PageResponseDto dto1 = new PageResponseDto();
				dto1.setContent(paginate.getContent());
				dto1.setPageNumber(paginate.getNumber() + 1);
				dto1.setPageSize(paginate.getSize());
				dto1.setTotalPages(paginate.getTotalPages());
				return dto1;
			} catch (IllegalArgumentException e) {
				throw new ApplicationException(e.getMessage());
			}
		} else {
			throw new ApplicationException("Status is empty");
		}

	}

	public String resetPassword(PasswordDto passwordDto) {
		String message = null;
		YotaUser user = userRepository.getUserByEmail(passwordDto.getEmailAdd());
		if (user == null) {
			throw new ApplicationException("User not found with email: " + passwordDto.getEmailAdd());
		}
		if (!passwordDto.getNewPassword().equals(passwordDto.getConfirmPassword())) {
			throw new PasswordMismatchException("NewPassword do not match with ConfirmPassword");
		}
		if (!this.passwordEncoder.matches(passwordDto.getCurrentPassword(), user.getPassword())) {
			throw new PasswordMismatchException("Current Password is incorrect");
		}
		if (this.passwordEncoder.matches(passwordDto.getNewPassword(), user.getPassword())) {
			throw new PasswordMismatchException("Password is already exist");
		}
		user.setPassword(this.passwordEncoder.encode(passwordDto.getNewPassword()));
		userRepository.save(user);
		message = "Password Change Successfully";
		return message;
	}

	public UserProfileDto getUserByEmailAddress(String emailAdd) {

		UserProfileDto userDto = null;
		YotaUser user = null;

		if (StringUtils.isNotEmpty(emailAdd)) {
			user = this.userRepository.getUserByEmail(emailAdd);

			userDto = this.modelMapper.map(user, UserProfileDto.class);
		} else {
			throw new ApplicationException("Email add is empty");
		}
		return userDto;

	}

	@Override
	public List<YotaUserDto> getAllRejectedAssociatesByStatus(String status) {
		List<YotaUserDto> yotaUserDto = null;
		List<YotaUser> user = null;
		if (StringUtils.isNotEmpty(status)) {
			UserAccountStatusTypes userAccountStatusTypes = UserAccountStatusTypes.valueOf(status);
			user = this.userRepository.getAllRejectedAssociatesByStatus(userAccountStatusTypes);
			if (CollectionUtils.isEmpty(user)) {
				throw new ApplicationException("No associates found with the provided status : " + status);
			}

			yotaUserDto = user.stream()
					.filter(users -> users.getUserRole().getRoleTypes().equals(UserRoleTypes.ROLE_ASSOCIATE.toString()))
					.map(users -> modelMapper.map(users, YotaUserDto.class)).collect(Collectors.toList());
		} else {
			throw new ApplicationException("Status is empty");
		}
		return yotaUserDto;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
	public Boolean pendingDeclinedAssociate(String emailAdd) {
		if (StringUtils.isNotEmpty(emailAdd)) {
			Integer status = this.userRepository.pendingDeclinedAssociate(emailAdd);
			if (status == 1)
				return true;
			else
				throw new ApplicationException("User not Pending");
		} else {
			throw new ApplicationException("Email address is empty.");
		}
	}

	private boolean validateEmail(String email) {
		if (email == null || email.isEmpty()) {
			return false;
		}
		Pattern regexPattern = Pattern.compile("^[^@\\s]+@yash\\.com$");
		Matcher regMatcher = regexPattern.matcher(email);
		return regMatcher.matches();
	}

	private boolean validatePassword(YotaUserDto userDto) {

		if (userDto.getPassword() == null || userDto.getPassword().isEmpty()) {
			return false;
		}

		if (userDto.getConfirmPassword() == null || userDto.getConfirmPassword().isEmpty()) {
			return false;
		}
		return true;
	}
}
