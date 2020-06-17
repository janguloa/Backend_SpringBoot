package com.servicio;

import com.modelo.NotificationEmail;

public interface MailService {
	
	void sendMail(NotificationEmail notificationEmail);
}