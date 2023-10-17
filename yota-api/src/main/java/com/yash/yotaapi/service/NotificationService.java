package com.yash.yotaapi.service;

import java.util.List;

import com.yash.yotaapi.domain.Notification;

	/**
	* NotificationService will be performing business logic related to Notification
	*/
public interface NotificationService {
	
	/** saveNotification method will save the notification 
	 * @return notification object
	 */
	public Notification saveNotification(Notification notification);
	
	/**
	 * fetchNotificationByEmail method will fetch the notifications associated with the loggedin user using email.
	 * @return List of notification
	 */
	public List<Notification> fetchNotificationByEmail(String email); 
	
	/**
	 * this method will update the notification status field to read of the user using the email
	 * @return nothing
	 */
	public void updateNotificationStatus(String email);

	/**
	 * getAllNotification method will fetch the notifications associated with users.
	 * @return List of notification
	 */
	public List<Notification> getAllNotification();
}
