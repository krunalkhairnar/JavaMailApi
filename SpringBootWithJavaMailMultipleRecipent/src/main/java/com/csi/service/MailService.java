package com.csi.service;

import java.io.File;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import com.csi.model.MailData;

@Service
public class MailService {

	@ResponseBody
	public String sendMail(MailData mailData) {
		
		String host="smtp.gmail.com";
		
		Properties properties = System.getProperties();
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");
		
		Session session = Session.getInstance(properties, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				
				return new PasswordAuthentication("krunal.khairnar95@gmail.com", "7350069515");
			}
		 
		});
		
		session.setDebug(true);
		
		MimeMessage mimeMessage = new MimeMessage(session);
		MimeMultipart mimeMultipart = new MimeMultipart();
		MimeBodyPart textbody = new MimeBodyPart();
		MimeBodyPart filebody = new MimeBodyPart();
		
		String[] recipents = mailData.getTo().split(",");
		String[] allcc = mailData.getCc().split(",");
		
		try {
			mimeMessage.setFrom(mailData.getFrom());
			mimeMessage.setSubject(mailData.getSubject());
			for(String to : recipents)
			{
				mimeMessage.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
			}
			
			for(String cc : allcc)
			{
				mimeMessage.addRecipient(Message.RecipientType.CC,new InternetAddress(cc));
			}
			
			if(!(mailData.getAttachmentPath().isEmpty()))
			{
				File file = new File(mailData.getAttachmentPath());
				filebody.attachFile(file);
				mimeMultipart.addBodyPart(filebody);
			}
			textbody.setText(mailData.getTextBody());
			mimeMultipart.addBodyPart(textbody);
			mimeMessage.setContent(mimeMultipart);
			
			Transport.send(mimeMessage);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Mail deliverd";	
	}

}
























