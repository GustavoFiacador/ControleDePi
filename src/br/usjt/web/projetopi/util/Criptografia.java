package br.usjt.web.projetopi.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Criptografia {
	
	public String convertToMD5(String input) throws Exception {
	    String md5 = null;
	    if (null == input)
	        return null;
	    try {
	        // Create MessageDigest object for MD5
	        MessageDigest digest = MessageDigest.getInstance("MD5");
	        // Update input string in message digest
	        digest.update(input.getBytes(), 0, input.length());
	        // Converts message digest value in base 16 (hex)
	        md5 = new BigInteger(1, digest.digest()).toString(16);
	    } catch (NoSuchAlgorithmException e) {
	        e.printStackTrace();
	    }
	    return md5;
	}
	
	public boolean matching(String orig, String compare){
	    String md5 = null;
	    try{
	        MessageDigest md = MessageDigest.getInstance("MD5");
	        md.update(compare.getBytes());
	        byte[] digest = md.digest();	        
	        md5 = new BigInteger(1, digest).toString(16);
	        return md5.equals(orig);

	    } catch (NoSuchAlgorithmException e) {
	        e.printStackTrace();
	    	return false;
	    }
	}
}
