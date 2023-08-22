package com.yash.yotaapi.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;

import org.springframework.beans.factory.annotation.Value;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

	/**
	the notification model will work as a object of notifications. The notification object
	is created when the test is assigned to the user 
	*/

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Notification {
	
    /**ID is a primary key and it is generated automatically. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String email;
	
	private String message;
	
	/**
	 * notification created date and it is will be automatically generated at the
	 * time of record creation
	 */
	private Date date;
	
	/**Status field shows the status of notification and its default value is unread*/
	private String status ="unread";
	
	/**
	 * This method will be called before the entity is inserted (persisted) into the
	 * database.
	 */
	@PrePersist
	public void onDate() {
		this.date = new Date();
	}
}
