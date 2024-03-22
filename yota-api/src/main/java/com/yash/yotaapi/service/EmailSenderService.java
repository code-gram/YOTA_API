package com.yash.yotaapi.service;

import com.yash.yotaapi.domain.MailRequest;
import com.yash.yotaapi.domain.MailResponse;
import com.yash.yotaapi.util.EmailUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmailSenderService {

    @Autowired
    private EmailUtility emailUtility;

    public MailResponse sendEmailsAsync(MailRequest request) {
        List<String> recipients = request.getTo();
        String subject = request.getSubject();
        String name = request.getName();
        String body = request.getBody();
        String from = "invitetest125@gmail.com";

        StringBuffer str = new StringBuffer();
        List<MailResponse> rs = new ArrayList<MailResponse>();
        MailResponse mail = new MailResponse();
        // StringBuilder sb = new StringBuilder();
        List<String> list = new ArrayList<>();
        Thread thread = new Thread(() -> {

            for (String recipient : recipients) {
                String sendEmail = emailUtility.sendEmail(recipient, subject, name, from, body);
                list.add(sendEmail);
                //str.append(sendEmail);
                System.out.println(str);

            }

        });
        thread.setDaemon(true);

        thread.start();
        try {
            thread.join();
            mail.setEmailList(list);
            mail.setMsg("Email Send Successfully!");
            mail.setStatus(Boolean.TRUE);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return null;
        }

        // System.out.println(str + "***");
        //return rs;
        return mail;
    }

    public MailResponse sendTrfApprovalMail(MailRequest request) {
        List<String> recipients = request.getTo();
        String subject = request.getSubject();
        String body = request.getBody();
        String from = "invitetest125@gmail.com";

        MailResponse mail = new MailResponse();
        List<String> list = new ArrayList<>();
        Thread thread = new Thread(() -> {
            recipients.parallelStream().forEach(recipient -> {
                String sendEmail = emailUtility.sendTrfApprovalMail(recipient, subject, from, body);
                list.add(sendEmail);
            });
        });
        thread.setDaemon(true);

        thread.start();
        try {
            thread.join();
            mail.setEmailList(list);
            mail.setMsg("Email Send Successfully!");
            mail.setStatus(Boolean.TRUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return mail;
    }
}
