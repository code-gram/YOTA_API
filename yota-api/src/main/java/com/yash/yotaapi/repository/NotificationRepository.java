package com.yash.yotaapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.yash.yotaapi.domain.Notification;

	/**
	 * NotificationRepository will perform db operations on Notifications
	*/
@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer>{

	/**
	 * this method will find the notifications from the database according to the
	 * email of the loggedin user and return list of notifications
	 */
	public List<Notification> findByEmail(String email);
	
	
	/**
	 * this method will update the notification status field to read
	 * once the notification is seen by the user
	 */
	@Transactional
	@Modifying
	@Query(value="update notification set status='read' where email=:e",nativeQuery = true)
	public void changeStatusToRead(@Param("e") String email);
	
}
