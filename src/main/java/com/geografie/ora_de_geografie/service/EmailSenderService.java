package com.geografie.ora_de_geografie.service;

import org.springframework.mail.SimpleMailMessage;

public interface EmailSenderService {
    void sendEmail(SimpleMailMessage simpleMailMessage);
}
