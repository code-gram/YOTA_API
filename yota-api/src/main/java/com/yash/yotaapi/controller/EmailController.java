package com.yash.yotaapi.controller;

import com.yash.yotaapi.domain.MailRequest;
import com.yash.yotaapi.domain.MailResponse;
import com.yash.yotaapi.service.EmailSenderService;
import com.yash.yotaapi.util.EmailUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/emails")
public class EmailController {

    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    private EmailUtility service;

    @PostMapping("/send-email")
    public ResponseEntity<MailResponse> sendEmail(@RequestBody MailRequest request) {

        MailResponse response = emailSenderService.sendEmailsAsync(request);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/trf/approve/send-email")
    public ResponseEntity<MailResponse> sendTrfApprovalEmail(@RequestBody MailRequest request) {
        MailResponse response = emailSenderService.sendTrfApprovalMail(request);
        return ResponseEntity.ok(response);
    }

}
