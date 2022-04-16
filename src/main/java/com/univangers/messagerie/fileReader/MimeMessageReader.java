/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univangers.messagerie.fileReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.jsoup.Jsoup;

/**
 *
 * @author etud
 */
public class MimeMessageReader {

    public static MailObject readMessageFile(String filePath) throws FileNotFoundException, MessagingException, IOException {
        Properties props = new Properties();
        Session mailSession = Session.getDefaultInstance(props, null);
        InputStream inputStream = new FileInputStream(filePath);
        MimeMessage message = new MimeMessage(mailSession, inputStream);

        MailObject mailObject = new MailObject();

        mailObject.setFrom(getMailFromString(message.getFrom()[0].toString()));
        mailObject.setSentDate(message.getSentDate());
        mailObject.setReceivedDate(message.getReceivedDate());
        mailObject.setSubject(message.getSubject());
        mailObject.setContent(getTextFromMessage(message));

        //DESTINATAIRES
        Address[] toList = message.getRecipients(Message.RecipientType.TO);
        if (toList != null) {
            for (Address adr : message.getRecipients(Message.RecipientType.TO)) {
                String mailAdr = getMailFromString(adr.toString());
                mailObject.getTo().add(mailAdr);
            }
        }

        //DESTINATAIRES CC
        Address[] ccList = message.getRecipients(Message.RecipientType.CC);
        if (ccList != null) {
            for (Address adr : message.getRecipients(Message.RecipientType.CC)) {
                String mailAdr = getMailFromString(adr.toString());
                mailObject.getCc().add(mailAdr);
            }
        }
        //DESTINATAIRES BCC
        Address[] bccList = message.getRecipients(Message.RecipientType.BCC);
        if (bccList != null) {
            for (Address adr : message.getRecipients(Message.RecipientType.BCC)) {
                String mailAdr = getMailFromString(adr.toString());
                mailObject.getBcc().add(mailAdr);
            }
        }

        return mailObject;
    }

    private static String getTextFromMessage(Message message) throws MessagingException, IOException {
        String result = "";
        if (message.isMimeType("text/plain")) {
            result = message.getContent().toString();
        } else if (message.isMimeType("multipart/*")) {
            MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
            result = getTextFromMimeMultipart(mimeMultipart);
        }
        return result;
    }

    private static String getTextFromMimeMultipart(
            MimeMultipart mimeMultipart) throws MessagingException, IOException {
        String result = "";
        int count = mimeMultipart.getCount();
        for (int i = 0; i < count; i++) {
            BodyPart bodyPart = mimeMultipart.getBodyPart(i);
            if (bodyPart.isMimeType("text/plain")) {
                result = result + "\n" + bodyPart.getContent();
                break; // Sans le break le même texte apparît 3 fois ???
            } else if (bodyPart.isMimeType("text/html")) {
                String html = (String) bodyPart.getContent();
                result = result + "\n" + Jsoup.parse(html).text();
            } else if (bodyPart.getContent() instanceof MimeMultipart) {
                result = result + getTextFromMimeMultipart((MimeMultipart) bodyPart.getContent());
            }
        }
        return result;
    }

    private static String getMailFromString(String str) {
        Matcher m = Pattern.compile("[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+").matcher(str);
        String mail = null;
        if (m.find()) {
            mail = m.group();
        }
        return mail;
    }
}
