package com.yash.yotaapi.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yash.yotaapi.domain.Notification;
import com.yash.yotaapi.repository.NotificationRepository;
import com.yash.yotaapi.service.NotificationService;

	/**
	this is service layer class for Notification to write business logic
	*/
@Service
public class NotificationServiceImpl implements NotificationService{

	/**
	 * NotificationRepository is used to interact service layer with repository layer.
	 */
	@Autowired
	private NotificationRepository notificationRepository;
	
	/**
	 * This method is for fetch Notification of the particular user from DB using user email 
	 */
	@Override
	public List<Notification> fetchNotificationByEmail(String email) {
		return this.notificationRepository.findByEmail(email);
	}
	
	

	/**
	 * This method is for save Notification to DB through repository layer
	 */
	@Override
	public Notification saveNotification(Notification notification) {
		return notificationRepository.save(notification);
	}

	/**
	 * this method will update the Notification status field to read
	 */
	@Override
	public void updateNotificationStatus(String email) {
	  this.notificationRepository.changeStatusToRead(email);
	}

	/**
	 * This method is for fetch all Notification which assigned by trainer
	 */
	@Override
	public List<Notification> getAllNotification() {
		// TODO Auto-generated method stub
		return this.notificationRepository.findAll();
	}

}
