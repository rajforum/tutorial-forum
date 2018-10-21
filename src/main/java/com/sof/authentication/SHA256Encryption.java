package com.sof.authentication;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class SHA256Encryption {
	
	//inputString: username-mobile-currtimemillis
	public String createAuthToken(String inputString) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte [] hashData = digest.digest(inputString.getBytes(StandardCharsets.UTF_8));
		return Base64.getEncoder().encodeToString(hashData);
	}

}
