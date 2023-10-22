package com.shiropure.campuslink;

import org.junit.Test;

import java.security.SecureRandom;
import java.util.Base64;

public class KeyGenerator {
    @Test
    public void generateKey() {
        SecureRandom random = new SecureRandom();
        byte[] keyBytes = new byte[32]; // 32 bytes = 256 bits
        random.nextBytes(keyBytes);
        String encodedKey = Base64.getEncoder().encodeToString(keyBytes);
        System.out.println(encodedKey);
        assert encodedKey.length() == 44;
    }
}
