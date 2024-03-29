package com.yash.yotaapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yash.yotaapi.domain.MailRequest;
import com.yash.yotaapi.domain.MailResponse;
import com.yash.yotaapi.service.EmailSenderService;
import com.yash.yotaapi.util.EmailUtility;

@CrossOrigin
@RestController
public class EmailController {

	@Autowired
	private EmailSenderService emailSenderService;

	@Autowired
	private EmailUtility service;

	@PostMapping("/sendingEmail")
	public ResponseEntity<MailResponse> sendEmail(@RequestBody MailRequest request) {

		MailResponse response = emailSenderService.sendEmailsAsync(request);

		return ResponseEntity.ok(response);
	}

}
