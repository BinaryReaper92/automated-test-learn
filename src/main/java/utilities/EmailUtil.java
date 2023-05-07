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
import java.util.Properties;

public class EmailUtil {
    private static boolean initialized;
    public static Properties EmailProperties;
    public static void init (){
        if (initialized)
            return;

        EmailProperties = new Properties();
        EmailProperties.setProperty("mail.smtp.host", ConfigReader.getMailSmtpHost());
        EmailProperties.setProperty("mail.smtp.port", ConfigReader.getMailSmtpPort());
        EmailProperties.setProperty("mail.smtp.auth", ConfigReader.getMailSmtpAuth());
        EmailProperties.setProperty("mail.smtp.starttls.enable", ConfigReader.getMailSmtpStarttls());

        initialized = true;
    }

    public static void sendEmailWithAttachment(String subject, String body, File attachment) throws MessagingException {
        String from = ConfigReader.getFromEmail();
        String password = ConfigReader.getFromPassword();
        String to = ConfigReader.getToEmail();

        Session session = Session.getInstance(EmailProperties, new javax.mail.Authenticator() {
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
        String body = "Please find the attached Cucumber test report. \n\n"+CucumberEventListener.EventMessages;

        try {
            sendEmailWithAttachment(subject, body, reportFile);
            System.out.println("Email sent successfully!");
        } catch (MessagingException e) {
            Log4j.error(e.getMessage());
            System.err.println("Error sending email: " + e.getMessage());
        }
    }
}
