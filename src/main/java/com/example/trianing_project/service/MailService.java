package com.example.trianing_project.service;

import com.example.trianing_project.service.dto.DataMailDTO;

import javax.mail.MessagingException;

public interface MailService {
    void sendHtmlMail(DataMailDTO dataMail, String templateName) throws MessagingException;
}