package com.yash.yotaapi.util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.yash.yotaapi.domain.MailResponse;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@Service
public class EmailUtility {

	@Autowired
	private JavaMailSender sender;
	@Autowired
	private Configuration config;

	public String sendEmail(String recipient, String subject, String name, String from, String body) {
		String p = "Please check your network before proceed ";
		p = p.concat(body);
		Map<String, Object> model = new HashMap<>();
		model.put("link", "https://www.w3schools.com/quiztest/quiztest.asp?qtest=JAVA");
		model.put("subject", p); // request.getSubject()
		model.put("name", ""); // request.getName()
		MailResponse response = new MailResponse();
		MimeMessage message = sender.createMimeMessage();

		try {
			// mediatype
			MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
					StandardCharsets.UTF_8.name());

			// attachment

			helper.addAttachment("logo.png", new ClassPathResource("logo.png"));

			Template t = config.getTemplate("email_template.ftl");
			String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);
            helper.setTo(recipient);
			helper.setText(html, true);
			helper.setSubject(subject);
			helper.setFrom(from);
			sender.send(message);

			System.out.println("Mail send to "+recipient);
			// response.setMsg("mail sent to : " + (recipient));
			//\ response.setStatus(Boolean.TRUE);

			// System.out.println(response);
		} catch (MessagingException | IOException | TemplateException e) {
			response.setMsg("mail sending failure : " + e.getMessage());
			response.setStatus(Boolean.FALSE);
			e.printStackTrace();
		}
		return recipient;

	}

}
