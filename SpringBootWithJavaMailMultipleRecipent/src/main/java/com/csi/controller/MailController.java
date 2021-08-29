package com.csi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.csi.model.MailData;
import com.csi.service.MailService;

@RestController
@RequestMapping("/mail")
public class MailController {

	@Autowired
	MailService service;
	
	@GetMapping("/")
	public String welcome()
	{
		return "Welcome To mail Delivery System.";
	}
	
	@PostMapping("/sendmail")
	public void sendMail(@RequestBody MailData mailData)
	{
		service.sendMail(mailData);
	}
}
