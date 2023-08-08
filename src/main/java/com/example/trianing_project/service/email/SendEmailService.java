package com.example.trianing_project.service.email;

import com.example.trianing_project.service.dto.EmployeeDTO;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class SendEmailService {

    private final JavaMailSender javaMailSender;

    public SendEmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendMail(EmployeeDTO employeeDTO) throws MessagingException {
        String subject = "Your account was create success fully!";
        String toEmail = employeeDTO.getEmail();
        String body = "<i>welcome: </i>" + employeeDTO.getFirstName() + " " + employeeDTO.getLastName() + " to company " + "<br>" + "<b>password: </b>" + employeeDTO.getPassword() + "<br>" + "<p>let's  good experience</p>";
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message);
        messageHelper.setFrom("minhkhiet1415@gmail.com");
        messageHelper.setSubject(subject);
        messageHelper.setTo(toEmail);
        boolean html = true;
        messageHelper.setText(body, html);
        javaMailSender.send(message);
    }

    public void sendMailChangePassword(EmployeeDTO employeeDTO) throws MessagingException {
        String subject = "Change password success fully!";
        String toEmail = employeeDTO.getEmail();
        String body = "<i>welcome: </i>" + employeeDTO.getFirstName() + " " + employeeDTO.getLastName() + "<br>" + "<b>new password is : </b>" + employeeDTO.getPassword() + "<br>" + "<p>let's  good experience</p>";
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message);
        messageHelper.setFrom("minhkhiet1415@gmail.com");
        messageHelper.setSubject(subject);
        messageHelper.setTo(toEmail);
        boolean html = true;
        messageHelper.setText(body, html);
        javaMailSender.send(message);
    }
}
