package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncryption {
    public static String sha256(String msg) {
        String encMsg = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(msg.getBytes());
            byte[] digest = md.digest();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < digest.length; i++) {
                sb.append(Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1));
            }
            encMsg = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return encMsg;
    }
}