package com.finpay.common.utils;

import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class PemUtils {

    private static String readPem(String path) throws Exception {
        try (InputStream inputStream = new ClassPathResource(path).getInputStream()) {
            String pem = new String(inputStream.readAllBytes());
            return pem.replaceAll("-----\\w+ PRIVATE KEY-----", "")
                    .replaceAll("-----\\w+ PUBLIC KEY-----", "")
                    .replaceAll("\\s", "");
        }
    }

    public static RSAPrivateKey loadPrivateKey(String path) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(readPem(path));
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        return (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(keySpec);
    }

    public static RSAPublicKey loadPublicKey(String path) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(readPem(path));
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        return (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(keySpec);
    }
}
