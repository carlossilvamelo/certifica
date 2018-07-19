package com.sendcertifications.services;



import com.itextpdf.xmp.impl.Base64;
import com.sendcertifications.domain.Member;
import com.sendgrid.*;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

@Service
public class SendService {



    public void sendEmails(List<Member> certificates){
        for (Member member: certificates) {
            sendEmail(member);
        }
    }

    /**
     * this method sends the emails with use of sendgrid
     * @param member
     */
    public void sendEmail(Member member){


        Attachments attachmentsImage = new Attachments();
        attachmentsImage.setContent(Base64.encode(getClass().getResource("/static/img/bg1.jpg").getPath()));
        attachmentsImage.setDisposition("inline");
        attachmentsImage.setFilename("image.jpg");
        attachmentsImage.setContentId("bgImage");
        attachmentsImage.setType("image/jpge");

        Email from = new Email("congres@fortal.com");
        String subject = "Certificado de participação ["+member.getName().toLowerCase()+"]";
        Email to = new Email(member.getEmail());
        Content content = new Content("text/html", member.getCertificate());
        Mail mail = new Mail(from, subject, to, content);
        mail.addAttachments(attachmentsImage);

        SendGrid sg = new SendGrid("--");
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println("Response status: "+response.getStatusCode());
            System.out.println(response.getBody());
           // System.out.println(response.getHeaders());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
