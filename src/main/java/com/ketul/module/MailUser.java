package com.ketul.module;

public class MailUser {

	private String toMail;

	public MailUser() {
		super();
	}

	public MailUser(String toMail) {
		super();
		this.toMail = toMail;
	}

	public String getToMail() {
		return toMail;
	}

	public void setToMail(String toMail) {
		this.toMail = toMail;
	}

	@Override
	public String toString() {
		return "MailUser [toMail=" + toMail + "]";
	}

	
}
