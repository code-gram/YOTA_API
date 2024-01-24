package com.yash.yotaapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.yash.yotaapi.domain.Notification;
import com.yash.yotaapi.service.NotificationService;
import java.security.Principal;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * NotificationController will interact with service layer to perform operations
 * related to Notification
 */
@CrossOrigin("*")
@Tag(name = "Notification Controller", description = "Controller of Notification")
@RequestMapping("/notifications")
@RestController
public class NotificationController {

	/**
	 * notificationService is a service layer having business logic used to interact
	 * with Controller
	 */
	@Autowired
	private NotificationService notificationService;

	/**
	 * this method is used to save notification
	 */
	@PostMapping("/")
	public ResponseEntity<Notification> addNotification(@RequestBody Notification notification, Principal principal) {
		// Access authenticated user's details (username)
		String username = principal.getName();
		// Add your logic here

		return ResponseEntity.status(HttpStatus.OK).body(notificationService.saveNotification(notification));
	}

	/**
	 * this method will fetch all the notifications of a particular user using email
	 */
	@GetMapping("/{email}")
	public ResponseEntity<?> getAllNotificationByEmail(@PathVariable("email") String email, Principal principal) {
		// Access authenticated user's details (username)
		String username = principal.getName();
		// Add your logic here

		List<Notification> fetchNotificationByEmail = notificationService.fetchNotificationByEmail(email);
		return ResponseEntity.ok().body(fetchNotificationByEmail);
	}

	/**
	 * this method will fetch all the notifications of users
	 */
	@GetMapping("/")
	public ResponseEntity<?> getAllNotification(Principal principal) {
		// Access authenticated user's details (username)
		String username = principal.getName();
		// Add your logic here

		List<Notification> fetchNotificationByEmail = notificationService.getAllNotification();
		return ResponseEntity.ok().body(fetchNotificationByEmail);
	}

	/**
	 * this method will update the notification status to read
	 */
	@PutMapping("/{email}")
	public void removeNotificationById(@PathVariable("email") String email, Principal principal) {
		// Access authenticated user's details (username)
		String username = principal.getName();
		// Add your logic here

		try {
			notificationService.updateNotificationStatus(email);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
}