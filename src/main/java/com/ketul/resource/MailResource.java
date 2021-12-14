package com.ketul.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ketul.module.MailUser;
import com.ketul.module.VerifyMailUser;
import com.ketul.service.MailOtpService;

@RestController
public class MailResource {
	
	@Autowired
	private MailOtpService mailOtpService;
	
	@PostMapping("/send")
	public void sendSms(@RequestBody MailUser mailUser) {
		mailOtpService.sendSimpleMail(mailUser.getToMail());
	}
	
	@PostMapping("/verify")
	public String varifyOtp(@RequestBody VerifyMailUser varifyMailUser) {
		Integer generatedOtp = mailOtpService.getOtp(varifyMailUser.getToMail());
		
		if(varifyMailUser.getOtp() == generatedOtp) {
			mailOtpService.clearOTP(varifyMailUser.getToMail());
			return "Correct Otp";
		}
		
		return "Not Correct OTP";
	}
}
