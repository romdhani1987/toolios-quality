package fr.romdhani.aymen.toolios.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash {
    private final static String SHA256 = "SHA-256";

    private Hash() {
        throw new IllegalAccessError("Utility class");
    }

    public static byte[] sha256(String data)  {
        try {
            MessageDigest md = MessageDigest.getInstance(SHA256);
            md.update(data.getBytes(StandardCharsets.UTF_8));
            return md.digest();
        } catch(NoSuchAlgorithmException e) {
            //should not happen, SHA256 is standard
            throw new RuntimeException(e);
        }
    }

    /**
     * This isn't good, we should use Base64 to encode binary as string.
     * We have to keep it for now to avoid breaking existing user passwords &amp; licences...
     *
     * @param data the byte array to encode
     * @return the encoded string
     */
    public static String asIsoString(byte[] data) {
        return new String(data, StandardCharsets.ISO_8859_1);
    }
}