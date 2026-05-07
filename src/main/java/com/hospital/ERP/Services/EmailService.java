package com.hospital.ERP.Services;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendInvoiceEmail(String toEmail, byte[] pdf, Long billId) {

        try {
            MimeMessage message = mailSender.createMimeMessage();

            MimeMessageHelper helper =
                    new MimeMessageHelper(message, true);

            helper.setTo(toEmail);
            helper.setSubject("Payment Successful - Invoice");

            helper.setText(
                    "Dear Patient,\n\n" +
                            "Your payment has been successfully completed.\n\n" +
                            "Please find the attached invoice.\n\n" +
                            "Thank you,\nHospital Team"
            );


            helper.addAttachment(
                    "invoice_" + billId + ".pdf",
                    new ByteArrayResource(pdf)
            );

            mailSender.send(message);

        } catch (Exception e) {
            throw new RuntimeException("Email sending failed");
        }
    }
}