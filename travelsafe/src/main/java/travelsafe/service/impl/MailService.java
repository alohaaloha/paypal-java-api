package travelsafe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * Created by Dorian on 1/4/2017.
 */
@Service
public class MailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public MailService(){

    }

    public void sendMail(String to, String content){
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom("travelsafe.insurances@gmail.com");
        mail.setTo(to);
        mail.setSubject("Travelsafe insurances");
        mail.setText(content);
        javaMailSender.send(mail);
    }

}
