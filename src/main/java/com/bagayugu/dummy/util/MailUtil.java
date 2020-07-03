package com.bagayugu.dummy.util;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.context.annotation.PropertySource;

import com.bagayugu.dummy.message.MailDto;

@PropertySource("classpath:mail.properties")
public class MailUtil {
	private static final String SMTP_AUTH_FLAG = "true";
	private static final String SMTP_TLS_FLAG = "true";
	private static final String SMTP_HOST = "smtp.gmail.com";
	private static final String SMTP_PORT = "587";
	private static final String CONTENT_TYPE = "text/html";
	
	public static void sendEmailNotif(MailDto request) {
		try {
			Properties props = new Properties();
		    props.put("mail.smtp.auth", SMTP_AUTH_FLAG);
		    props.put("mail.smtp.starttls.enable", SMTP_TLS_FLAG);
		    props.put("mail.smtp.host", SMTP_HOST);
		    props.put("mail.smtp.port", SMTP_PORT);
			   
			Session session = Session.getInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(request.getFromUsername(), request.getFromPassword());
			    }
			});
			   
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(request.getFromUsername(), false));
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(request.getToUsername()));
			msg.setSubject(request.getSubject());
			msg.setContent(request.getMessage(), CONTENT_TYPE);
			msg.setSentDate(new Date());
			
			Transport.send(msg);
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
}