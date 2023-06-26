package com.jpa.s.cotroller;

import com.jpa.s.entity.MyOTP;

public interface EmailService {
	public void sendTextMail(MyOTP myotpe);
}
