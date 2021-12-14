package com.ketul.service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

@Service
public class MailOtpService {
	
	@Autowired
	private JavaMailSender mailSender;

	private static final Integer EXPIRE_MINS = 5;

	private LoadingCache<String, Integer> otpCache;

	public MailOtpService() {
		super();

		otpCache = CacheBuilder.newBuilder().expireAfterWrite(EXPIRE_MINS, TimeUnit.MINUTES)
				.build(new CacheLoader<String, Integer>() {
					public Integer load(String key) {
						return 0;
					}
				});
	}

	public int generateOTP(String key) {

		Random random = new Random();
		int otp = 100000 + random.nextInt(900000);
		otpCache.put(key, otp);
		return otp;
	}

	public int getOtp(String key) {
		try {
			return otpCache.get(key);
		} catch (Exception e) {
			return 0;
		}
	}

	public void clearOTP(String key) {
		otpCache.invalidate(key);
	}

	public void sendSimpleMail(String toMail) {

		SimpleMailMessage mailMessage = new SimpleMailMessage();

		mailMessage.setFrom("<Enter Your Mail whose sent from>");
		mailMessage.setTo(toMail);
		mailMessage.setText(String.valueOf(generateOTP(toMail)));
		mailMessage.setSubject("OTP");

		mailSender.send(mailMessage);
	}
}
