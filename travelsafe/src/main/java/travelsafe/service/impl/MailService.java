package travelsafe.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * Created by Dorian on 1/4/2017.
 */
@Service
public class MailService {

    private static final Logger LOG = LoggerFactory.getLogger(MailService.class);

    @Autowired
    private JavaMailSender javaMailSender;

    public MailService(){

    }

    public void sendMail(String to, String content){

        LOG.debug("Sending mail to {} with message: {}",to,content);

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom("travelsafe.insurances@gmail.com");
        mail.setTo(to);
        mail.setSubject("Travelsafe insurances");
        mail.setText(content);
        javaMailSender.send(mail);
    }

}
