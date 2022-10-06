package com.europeandynamics.utils;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class PassBasedEncryption {

	private static final Random random = new SecureRandom();
	private static final String characters = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	private static final int iterations = 10000;
	private static final int keylength = 256;

	public static String getSaltvalue(int length) {
		StringBuilder finalval = new StringBuilder(length);

		for (int i = 0; i < length; i++) {
			finalval.append(characters.charAt(random.nextInt(characters.length())));
		}

		return new String(finalval);
	}

	public static byte[] hash(char[] password, byte[] salt) {
		PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, keylength);
		Arrays.fill(password, Character.MIN_VALUE);
		try {
			SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			return skf.generateSecret(spec).getEncoded();
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			throw new AssertionError("Error while hashing a password: " + e.getMessage(), e);
		} finally {
			spec.clearPassword();
		}
	}

	public static String generateSecurePassword(String password, String salt) {
		String finalval = null;

		byte[] securePassword = hash(password.toCharArray(), salt.getBytes());

		finalval = Base64.getEncoder().encodeToString(securePassword);

		return finalval;
	}

	public static boolean verifyUserPassword(String providedPassword, String securedPassword, String salt) {
		boolean finalval = false;

		String newSecurePassword = generateSecurePassword(providedPassword, salt);

		finalval = newSecurePassword.equalsIgnoreCase(securedPassword);

		return finalval;

	}
	
public static String passEncryptionGenerator(String password) {
		
        String saltvalue = PassBasedEncryption.getSaltvalue(30);
        
        String encryptedpassword = generateSecurePassword(password, saltvalue);
        
        System.out.println("Plain text password = " + password);  
        System.out.println("Secure password = " + encryptedpassword);  
        System.out.println("Salt value = " + saltvalue);  
          
        Boolean status = PassBasedEncryption.verifyUserPassword(password,encryptedpassword,saltvalue);  
        if(status==true)  
            System.out.println("Password Matched!!");  
        else  
            System.out.println("Password Mismatched");
        
        return encryptedpassword;
        

	}
}
