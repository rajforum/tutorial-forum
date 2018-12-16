package com.sof.authentication;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class SHA256Encryption {
	private static final String salt = "hiSalt";
		
	public static String getSalt() {
		return salt;
	}


	//inputString: username-mobile-currtimemillis for authtoken
	public static String generateHash(String input) {
		try {
			MessageDigest sha = MessageDigest.getInstance("SHA-256");
			byte[] hashedBytes = sha.digest(input.getBytes(StandardCharsets.UTF_8));
			return Base64.getEncoder().encodeToString(hashedBytes);
		}catch(NoSuchAlgorithmException ex) {
			ex.printStackTrace();
		}
		return null;
	}
}
