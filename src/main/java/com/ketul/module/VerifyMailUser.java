package com.ketul.module;

public class VerifyMailUser {
	
	private String toMail;
	private int otp;
	
	public VerifyMailUser() {
		super();
	}

	public VerifyMailUser(String toMail, int otp) {
		super();
		this.toMail = toMail;
		this.otp = otp;
	}

	public String getToMail() {
		return toMail;
	}

	public void setToMail(String toMail) {
		this.toMail = toMail;
	}

	public int getOtp() {
		return otp;
	}

	public void setOtp(int otp) {
		this.otp = otp;
	}

	@Override
	public String toString() {
		return "VerifyMailUser [toMail=" + toMail + ", otp=" + otp + "]";
	}
}
