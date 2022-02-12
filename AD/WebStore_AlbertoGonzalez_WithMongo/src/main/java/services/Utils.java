package services;

import java.security.SecureRandom;
import java.util.Base64;

public class Utils {

    public String churro() {
        SecureRandom sr = new SecureRandom();
        byte[] bits = new byte[5];
        sr.nextBytes(bits);
        return Base64.getUrlEncoder().encodeToString(bits);
    }
}
