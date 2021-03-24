package com.koreait.baraON.command.member;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Sha256 {

	public static String sha256(String message) {
		
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-256");
			md.update(message.getBytes());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return bytesToHex(md.digest());
		
	}
	
	public static String bytesToHex(byte[] bytes) {
		
		StringBuffer sb = new StringBuffer();
		for ( byte b : bytes ) {
			sb.append(String.format("%02x", b));
		}
		return sb.toString();
	
	}

}
