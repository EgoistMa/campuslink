package com.shiropure.campuslink.services;

import com.shiropure.campuslink.utils.DateTool;
import com.shiropure.campuslink.entity.Log;
import com.shiropure.campuslink.entity.LogLevel;
import com.shiropure.campuslink.entity.PassResetToken;
import com.shiropure.campuslink.repository.LogRepository;
import com.shiropure.campuslink.repository.PassResetTokenRepository;
import com.shiropure.campuslink.utils.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    LogRepository logRepo;

    @Autowired
    PassResetTokenRepository passResetTokenRepo;

    public String generateActivatedEmail(String userName) {
        return "Dear " + userName + ",\n\n" +
                "Congratulations! Your Campus-link account has been successfully Registered.\n\n" +
                "You can now start exploring our comprehensive campus tools. If you have any questions or need assistance, please don't hesitate to reach out to our support team.\n\n" +
                "We're thrilled to have you as part of our community and look forward to serving you.\n\n" +
                "Best wishes,\n" +
                "The Campuslink Team";
    }
    //todo rewrite this
    public String generatePasswordResetEmail(String userName, String resetLink) {
        return "Dear " + userName + ",\n\n" +
                "We received a request to reset the password for your Campuslink account.\n\n" +
                "To reset your password, please click the link below:\n" +
                "http://campuslink-front.shiropure.com/resetpassword?uid=" + resetLink + "\n\n" +
                "(Note: This link will be available for 3 hours.)\n" +
                "If you did not request to reset your password or believe this is an error, please ignore this email and contact our support team for assistance.\n\n" +
                "Stay secure and always ensure you use strong passwords for your accounts.\n\n" +
                "Thank you for using Campuslink,\n" +
                "The Campuslink Team";
    }

    public void sendRegistrySuccessfullyEmail(String targetEmail, UUID uuid, String username, String ip){
        Log log = new Log(LogLevel.INFO,uuid,"sending Registry Successfully email","",new Date(),ip);
        logRepo.save(log);
        String subject = "Campuslink Account has been Registered";
        String content = generateActivatedEmail(username);
        sendEmail(targetEmail,subject,content);
    }

    public void sendRestPassEmail(String targetEmail,UUID uuid,String username,String ip){
        Log log = new Log(LogLevel.INFO,uuid,"sending reset password email","",new Date(),ip);
        logRepo.save(log);
        String subject = "Campuslink Password Reset Instructions";
        String token = TokenGenerator.generatorToken(uuid);
        PassResetToken prt = new PassResetToken(token, DateTool.nextThreeHours(new Date()));
        passResetTokenRepo.save(prt);
        String content = generatePasswordResetEmail(username,token);
        sendEmail(targetEmail,subject,content);
    }

    public void sendEmail(String to, String subject, String content){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("campuslink5619@outlook.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        mailSender.send(message);
    }
    public static void sendNoticeEmail(String to, String subject, String content){
        sendNoticeEmail(to, subject,content);
    }

//    private String generateEventReminderEmail(Event event, String username){
//        StringBuilder sb = new StringBuilder();
//
//        sb.append("Hello, ").append(username).append("\n\n");
//        sb.append("This is a reminder for your next event:\n");
//        sb.append("Title: ").append(event.getSUMMARY()).append("\n");
//        sb.append("Start Time: ").append(event.getHumanReadableDTSTART()).append("\n");
//        sb.append("End Time: ").append(event.getHumanReadableDTEND()).append("\n");
//        sb.append("Location: ").append(event.getLocation()).append("\n");
//        sb.append("Description: ").append(event.getDescription()).append("\n");
//        sb.append("\nBest Regards,\n");
//        sb.append("Campuslink Team");
//
//        return sb.toString();
//    }

//    public void sendEventReminder(Event event, String targetEmail, UUID uuid, String username, String ip){
//        Log log = new Log(LogLevel.INFO, uuid, "sending event reminder for user: " + username, "", new Date(), ip);
//        logRepo.save(log);
//        String subject = "Event Reminder: " + event.getSUMMARY();
//        String content = generateEventReminderEmail(event, username);
//        sendEmail(targetEmail, subject, content);
//    }
}

