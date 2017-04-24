/**
 * 
 */
package com.algaworks.pedidovenda.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author adrianopatrick@gmail.com
 * @since 7 de jul de 2015
 */
public class Encripta {
	
	public static String encripta (String original) throws UnsupportedEncodingException {
		try {
			MessageDigest algorithm = MessageDigest.getInstance("SHA-512");
			byte messageDigest[] = algorithm.digest(original.getBytes("UTF-8"));
			 
			StringBuilder hexString = new StringBuilder();
			for (byte b : messageDigest) {
			  hexString.append(String.format("%02X", 0xFF & b));
			}
			String senha = hexString.toString();
			return senha;
		} catch (NoSuchAlgorithmException ns) {
			ns.printStackTrace();
			return null;
		}
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		System.out.println("Senha criptografada");
		System.out.println(Encripta.encripta("123456"));
	}
}
