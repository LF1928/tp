package seedu.address.commons.util;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import seedu.address.commons.exceptions.HashingFunctionException;

public class HasherUtil {
    private static final int ITERATIONS = 65536;
    private static final int KEY_LENGTH = 256;

    public static String hashPassword(String password, String salt) {
        try {
            KeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), ITERATIONS, KEY_LENGTH);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            byte[] hash = factory.generateSecret(spec).getEncoded();
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new HashingFunctionException("Hashing function failed");
        }

    }
}
