package com.sendcertifications.services;

import com.sendcertifications.domain.Member;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagService {


    /**
     * This method find the tags and change for the correct information
     * @param plainText
     * @return
     */
    public String changeTags(String plainText, String name, String country, String institute, String email, String subscribe){


        plainText = plainText.replace("{{País}}",country);
        plainText =  plainText.replace("{{Nome}}",name);
        plainText =  plainText.replace("{{Instituição}}",institute);
        plainText =  plainText.replace("{{Email}}",email);
        plainText =  plainText.replace("{{Inscrição}}",subscribe);

        return plainText;
    }

    /**
     *this method preapplies the strings, handling the tags
     * @param plainText
     * @param members
     * @return
     */
    public List<Member> prepareCertificates(String plainText, String members){

        List<Member> memberList = new ArrayList<Member>();

        String result = null;

       final int colName = 0, colCountry = 1, colInstitute = 2, colEmail = 3, colSubscribe = 4;

        String lines[] = members.split("\n");

        String columns[] =  null;

        Member memberAux;
        String certificateText = null;
        String certificateContent = null;
        for (int i =1; i < lines.length; i++){
            System.out.println(lines[i]);
            memberAux = new Member();
            columns = lines[i].split("\t");

            memberAux.setName(columns[colName]);
            memberAux.setCountry(columns[colCountry]);
            memberAux.setInstituite(columns[colInstitute]);
            memberAux.setEmail(columns[colEmail]);
            memberAux.setSubscribe(columns[colSubscribe]);

            certificateText = changeTags(plainText, columns[colName],columns[colCountry],  columns[colInstitute]
                    ,  columns[colEmail],  columns[colSubscribe]);
            certificateContent = generateHtml(certificateText);
            memberAux.setCertificate(certificateContent);

            memberList.add(memberAux);
        }

        return memberList;
    }

    /**
     * this method generates the email template
     * @param certificateText
     * @return
     */
    public String generateHtml(String certificateText){

        String html = "<div style=\" width: 210mm;\n" +
                "    height: 297mm; background: url(cid:bgImage) no-repeat center ;\">\n" +
                "\n" +
                "    <div style=\"padding-top: 17%;\n" +
                "    padding-bottom: 15%;\n" +
                "    padding-left: 15%;\n" +
                "    padding-right: 15%;\">\n" +
                "\n" +
                "        <h2 style=\"text-align: center;\">Certificado</h2>\n" +
                "        <p>"+certificateText+"</p>\n" +
                "\n" +
                "    </div>\n" +
                "</div>";


        return html;
    }




}
