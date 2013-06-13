package com.yunfeng.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

/**
 * 
 * @author Administrator
 * 
 */
public class Encrypt {

	public static String MD5(String text) {
		String md5 = "";
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] md5hash = new byte[32];
			md.update(text.getBytes(), 0, text.length());
			md5hash = md.digest();
			md5 = convertToHex(md5hash);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return md5;
	}

	public static String SHA1(String text) {
		String sha1 = "";
		try {
			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();
			crypt.update(text.getBytes());
			sha1 = convertToHex(crypt.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return sha1;
	}

	private static String convertToHex(final byte[] hash) {
		Formatter formatter = new Formatter();
		for (byte b : hash) {
			formatter.format("%02x", b);
		}
		String hexString = formatter.toString();
		formatter.close();
		return hexString;
	}
}