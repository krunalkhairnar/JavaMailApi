package com.csi.model;

import lombok.Data;

@Data
public class MailData {

	private  String from;
	private String to;
	private String cc;
	private String subject;
	private String textBody;
	private String attachmentPath;
	
}
