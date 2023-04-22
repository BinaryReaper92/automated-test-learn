package utilities;
import io.cucumber.java.Scenario;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class EmailUtil {
    private static Properties getEmailProperties() {
        Properties properties = new Properties();
        try (InputStream input = EmailUtil.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input != null) {
                properties.load(input);
            } else {
                throw new IOException("Cannot find config.properties");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    public static void sendEmailWithAttachment(String subject, String body, File attachment) throws MessagingException {
        Properties properties = getEmailProperties();
        String from = properties.getProperty("from.email");
        String password = properties.getProperty("from.password");
        String to = properties.getProperty("to.email");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(subject);

        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setText(body);

        MimeBodyPart attachmentBodyPart = new MimeBodyPart();
        attachmentBodyPart.setDataHandler(new DataHandler(new FileDataSource(attachment)));
        attachmentBodyPart.setFileName(attachment.getName());

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        multipart.addBodyPart(attachmentBodyPart);

        message.setContent(multipart);

        Transport.send(message);
    }

    public static void sendReportEmail(Scenario scenario, File reportFile) {
        String subject = "Cucumber Test Report - " + scenario.getName();
        String body = "Please find the attached Cucumber test report.";

        try {
            sendEmailWithAttachment(subject, body, reportFile);
            System.out.println("Email sent successfully!");
        } catch (MessagingException e) {
            System.err.println("Error sending email: " + e.getMessage());
        }
    }
}
