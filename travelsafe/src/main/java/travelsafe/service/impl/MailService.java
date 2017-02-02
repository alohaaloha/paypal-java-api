package travelsafe.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import travelsafe.model.*;
import travelsafe.service.pdf.PdfManagerService;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * Created by Dorian on 1/4/2017.
 */
@Service
public class MailService {

    private static final Logger LOG = LoggerFactory.getLogger(MailService.class);

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private PdfManagerService pdfManagerService;

    public MailService(){

    }

    public void sendMailWithAttachment(TravelInsurance travelInsurance){

        MimeMessage mail = javaMailSender.createMimeMessage();

        ParticipantInInsurance carrier = new ParticipantInInsurance();

        for(ParticipantInInsurance pii : travelInsurance.getParticipantInInsurances()){
            if(pii.getCarrier()){
                carrier = pii;
                break;
            }
        }

        LOG.debug("Sending mail to {}", carrier.getEmail());

        try {
            MimeMessageHelper mailHelper = new MimeMessageHelper(mail, true);

            mailHelper.setFrom("travelsafe.insurances@gmail.com");
            mailHelper.setTo(carrier.getEmail());
            mailHelper.setSubject("Travelsafe insurances");
            mailHelper.setText("Dear " + carrier.getName() + ",\n\nhere is your insurance purchase report.\n\nWish you a happy journey.\n\nTravelsafe management team");

            pdfManagerService.createPDF(travelInsurance);

            FileSystemResource file = new FileSystemResource("InsuranceReport"+travelInsurance.getId()+".pdf");
            mailHelper.addAttachment("InsuranceReport.pdf", file);

        } catch (MessagingException e) {
            e.printStackTrace();
        }

        javaMailSender.send(mail);

    }


}
