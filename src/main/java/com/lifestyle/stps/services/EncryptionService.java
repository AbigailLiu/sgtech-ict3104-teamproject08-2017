package com.lifestyle.stps.services;

/**
 * Created by User 1 on 26/9/2017.
 */
public interface EncryptionService {
    String encryptString(String input);
    boolean checkPassword(String plainPassword, String encryptedPassword);
}
