package com.bookstore.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class PasswordUtil {

	private PasswordUtil() {
	}

	public static String encryptPassword(String password) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			byte[] pwd = md.digest(password.getBytes());
			return Base64.getEncoder().encodeToString(pwd);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return null;
	}
}
