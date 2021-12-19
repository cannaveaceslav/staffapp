package com.staffapp.backend.service.email;

import com.staffapp.backend.security.EmailSender;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@AllArgsConstructor
public class EmailService implements EmailSender {

  private final static Logger logger = LoggerFactory.getLogger(EmailService.class);
  private final JavaMailSender mailSender;

  @Override
  @Async
  public void send(String to, String email) {
    logger.info("Sending email");
    try {
      MimeMessage mimeMessage = mailSender.createMimeMessage();
      MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, "utf-8");
      messageHelper.setText(email,true);
      messageHelper.setTo(to);
      messageHelper.setSubject("confirm your email");
      messageHelper.setFrom("registration@staffapp.tz");
      mailSender.send(mimeMessage);
    } catch (MessagingException e) {
      logger.error("failed to send email");
      throw new IllegalStateException("failed to send email");
    }
  }
}
