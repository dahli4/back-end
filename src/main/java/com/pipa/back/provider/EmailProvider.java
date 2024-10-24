package com.pipa.back.provider;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EmailProvider {
    private final JavaMailSender javaMailSender;

    private final String SUBJECT = "{인증메일입니다.}";

    public boolean sendCertificationMail(String userEmail, String certificationNumber) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);

            String htmlContent = this.getCertificationMessage(certificationNumber);

            messageHelper.setTo(userEmail);
            messageHelper.setSubject(SUBJECT);
            messageHelper.setText(htmlContent, true);

            javaMailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();

            return false;
        }
        return true;
    }

    private String getCertificationMessage(String certificationNumber) {
        String certificationMessage = "";
        certificationMessage += "<h1 style='text-align: center;'>[인증메일서비스]</h1>";
        certificationMessage += "<h3 style='text-align: center;'>인증 코드 : <strong style='font-size: 32px; letter-spacing: 8px;'>";
        certificationMessage += certificationNumber;
        certificationMessage += "</strong></h3>";

        return certificationMessage;
    }
}
