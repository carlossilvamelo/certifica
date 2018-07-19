package com.sendcertifications;

import com.sendcertifications.controllers.IndexController;
import com.sendcertifications.domain.Member;
import com.sendcertifications.services.TagService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.logging.Logger;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SendcertificationsApplicationTests {
    static final Logger LOG = Logger.getLogger(SendcertificationsApplicationTests.class.getName());

    @Autowired
    private TagService tagService;

    @Test
    public void contextLoads() {

    }


    @Test
    public void prepareCertificates() {

        List<Member> memberList = tagService.prepareCertificates("Certifico que o aluno {{Nome}} da instituição {{Instituição}} compareceu ao congresso em Fortaleza.","Nome\tPaís\tInstituição\tEmail\tInscrição\n" +
                "Abdellatif Bouazza\tBrasil\tNão Respondeu\tcarlossilvameloo@gmail.com\tPendente\n");
        for (Member member: memberList) {
            LOG.info(member.getCertificate());
        }


    }

}
