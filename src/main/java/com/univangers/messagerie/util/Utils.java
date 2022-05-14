/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univangers.messagerie.util;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import org.apache.commons.validator.routines.EmailValidator;

/**
 *
 * @author etud
 */
public class Utils {

    /**
     *
     * @param email
     * @return
     */
    public static boolean isValideEmail(String email) {
        EmailValidator validator = EmailValidator.getInstance();
        return validator.isValid(email);
    }

    public static boolean isValidInternetAddress(InternetAddress internetAddress) {
        boolean result = true;
        try {
            internetAddress.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }

}
