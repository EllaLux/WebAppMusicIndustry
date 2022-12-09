package org.larina.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

//класс для рассылки сообщений
@Service//спринг создаст класс автоматически
public class MailService{
    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String username;

    //кому, тема, тело письма
    public void send(String addressee, String theme, String message){
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom(username);
        mailMessage.setTo(addressee);
        mailMessage.setSubject(theme);
        mailMessage.setText(message);

        //отправляем сформированно сообщение
        mailSender.send(mailMessage);
    }

}
