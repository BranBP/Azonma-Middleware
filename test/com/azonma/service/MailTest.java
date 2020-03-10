package com.azonma.service;

import com.azonma.service.impl.MailServiceImpl;

public class MailTest { 
	 
	//Datos
	private String subject = "Test message from My Company Mail Service";
	private String message = "Hi, Yes you can!";
	private String[] to = new String [] {
			"topomusero@gmail.com",
			"mralejandro98@gmail.com",
			"dromaycobelas@gmail.com",
			"hector.ledo.doval@gmail.com",
			"joseantoniolp.teacher@gmail.com"
	};
		
	private MailService mailService = null;
	
	public MailTest() {
		mailService = new MailServiceImpl();
	}
		
	protected void testSendMail() {
		System.out.println("Testing email service...");
		try {
			mailService.sendMail(subject, message, to);
		} catch (Throwable t) {
	
			t.printStackTrace();
		}
	System.out.println("MailService test finished");
	}
	
	public static void main(String args[]) {
		MailTest test = new MailTest();
		test.testSendMail();	
	}
		
			
}		
