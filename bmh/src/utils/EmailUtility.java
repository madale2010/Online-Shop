package utils;


import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import utils.Constants.Emails;
 
/**
 * A utility class for sending e-mail messages
 * @author www.codejava.net
 *
 */
public class EmailUtility {
	/**
	 * Send email with system properties for internal java calls that need to send emails
	 * @param toAddress
	 * @param subject
	 * @param message
	 */
    public static void sendEmail(String toAddress, String subject, String message){
 
        // sets SMTP server properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", Constants.EMAIL_HOST);
        properties.put("mail.smtp.port", Constants.EMAIL_PORT);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
 
        // creates a new session with an authenticator
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(Constants.USER_EMAIL, Constants.USER_PASSWORD);
            }
        };
 
        Session session = Session.getInstance(properties, auth);
 
        // creates a new e-mail message
        Message msg = new MimeMessage(session);
        try {
			InternetAddress fromAddress = new InternetAddress(Constants.USER_EMAIL, Emails.EMAIL_TO_NAME);
	        msg.setFrom(fromAddress);
	        InternetAddress emailToAddress=new InternetAddress(toAddress, Emails.EMAIL_FROM_NAME);
	        InternetAddress[] toAddresses = { emailToAddress };
	        msg.setRecipients(Message.RecipientType.TO, toAddresses);
	        msg.setSubject(subject);
	        msg.setSentDate(new Date());
	        msg.setText(message);
	        Transport.send(msg);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	/**
	 * Send email with auth
	 * @param host
	 * @param port
	 * @param userName
	 * @param password
	 * @param toAddress
	 * @param subject
	 * @param message
	 */
    public static void sendEmail(String host, String port,final String userName, final String password, String toAddress, String subject, String message){
 
        // sets SMTP server properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
 
        // creates a new session with an authenticator
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        };
 
        Session session = Session.getInstance(properties, auth);
 
        // creates a new e-mail message
        Message msg = new MimeMessage(session);
        try {
			InternetAddress fromAddress = new InternetAddress(userName, Emails.EMAIL_TO_NAME);
	        msg.setFrom(fromAddress);
	        InternetAddress emailToAddress=new InternetAddress(toAddress, Emails.EMAIL_FROM_NAME);
	        InternetAddress[] toAddresses = { emailToAddress };
	        msg.setRecipients(Message.RecipientType.TO, toAddresses);
	        msg.setSubject(subject);
	        msg.setSentDate(new Date());
	        msg.setText(message);
	        // sends the e-mail
	        Transport.send(msg);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
    /**
     * Utility method to send simple HTML email
     * @param session
     * @param toEmail
     * @param fromEmail 
     * @param subject
     * @param body
     */
    public static void sendSimpleEmail(Session session, String toEmail, String fromEmail, String subject, String body){
        try
        {
          MimeMessage msg = new MimeMessage(session);
          //set message headers
          msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
          msg.addHeader("format", "flowed");
          msg.addHeader("Content-Transfer-Encoding", "8bit");
 
          msg.setFrom(new InternetAddress(fromEmail, "Blue Mountain Holly"));
          
          msg.setReplyTo(InternetAddress.parse(fromEmail, false));
 
          msg.setSubject(subject, "UTF-8");
 
          msg.setText(body, "UTF-8");
 
          msg.setSentDate(new Date());
 
          msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
          System.out.println("Message is ready");
          Transport.send(msg);  
 
          System.out.println("EMail Sent Successfully!!");
        }
        catch (Exception e) {
          e.printStackTrace();
        }
    }

}
