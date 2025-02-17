package com.yash.yotaapi.controllers;

import com.yash.yotaapi.dto.PageResponseDto;
import com.yash.yotaapi.dto.YotaUserDto;
import com.yash.yotaapi.services.IServices.IYOTAUserService;
import com.yash.yotaapi.validators.IsTechnicalManager;
import com.yash.yotaapi.validators.IsTechnicalManagerOrTrainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * Project Name - YOTASecurityPOC
 * <p>
 * IDE Used - IntelliJ IDEA
 *
 * @author - yashr
 * @since - 02-04-2024
 */
@RestController
@RequestMapping("/users")
public class UsersController {

	@Autowired
	private IYOTAUserService userService;

	@GetMapping("/get/all-trainers")
	@IsTechnicalManager
	public ResponseEntity<List<YotaUserDto>> getAllTrainers() {
		List<YotaUserDto> allTrainers = this.userService.getAllTrainers();
		return new ResponseEntity<>(allTrainers, HttpStatus.OK);
	}

	@GetMapping("/get/all-pending/associates")
	@IsTechnicalManager
	public ResponseEntity<List<YotaUserDto>> getAllPendingAssociates() {
		List<YotaUserDto> allPendingUsers = this.userService.getAllPendingUsers();
		return new ResponseEntity<>(allPendingUsers, HttpStatus.OK);
	}

	@GetMapping("/get/all-associates")
	@IsTechnicalManagerOrTrainer
	public ResponseEntity<List<YotaUserDto>> getAllAssociates() {
		List<YotaUserDto> allStudents = this.userService.getAllAssociates();
		return new ResponseEntity<>(allStudents, HttpStatus.OK);
	}

	@IsTechnicalManager
	@PutMapping("/approve/associate")
	public ResponseEntity<Boolean> approvePendingUser(@RequestParam("email") String emailAdd) {
		Boolean status = this.userService.approvePendingUser(emailAdd);
		return new ResponseEntity<>(status, HttpStatus.OK);
	}

	@IsTechnicalManager
	@PutMapping("/decline/associate")
	public ResponseEntity<Boolean> declinePendingUser(@RequestParam("email") String emailAdd,
			@RequestParam("reason") String reason) {
		Boolean status = this.userService.declinePendingUser(emailAdd, reason);
		return new ResponseEntity<>(status, HttpStatus.OK);
	}

	// Get Associates by status
	@GetMapping("/all-associates-status")
	@IsTechnicalManagerOrTrainer
	public ResponseEntity<PageResponseDto> getAllAssociatesByStatus(@RequestParam("status") String status,
			@RequestParam(value = "pageNumber") Integer pageNumber,
			@RequestParam(value = "pageSize") Integer pageSize) {
		PageResponseDto allAssociatesByStatus = this.userService.getAllAssociatesByStatus(status, pageNumber, pageSize);
		return new ResponseEntity<>(allAssociatesByStatus, HttpStatus.OK);
	}

	// Get ALl Rejected Associates
	@GetMapping("/all-rejected-associates-status")
	@IsTechnicalManagerOrTrainer
	public ResponseEntity<List<YotaUserDto>> getAllRejectedAssociatesByStatus(@RequestParam("status") String status) {
		List<YotaUserDto> allRejectedAssociatesByStatus = this.userService.getAllRejectedAssociatesByStatus(status);
		return new ResponseEntity<>(allRejectedAssociatesByStatus, HttpStatus.OK);
	}

	// Pending Associates
	@IsTechnicalManager
	@PutMapping("/pending/associate")
	public ResponseEntity<Boolean> pendingDeclinedAssociate(@RequestParam("email") String emailAdd) {
		Boolean status = this.userService.pendingDeclinedAssociate(emailAdd);
		return new ResponseEntity<>(status, HttpStatus.OK);
	}

}
