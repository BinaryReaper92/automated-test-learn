package utilities;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class EncrypterDecrypter {
    private static final String KEY = "What?";

    public static String encrypt(String input) {
        byte[] inputBytes = input.getBytes(StandardCharsets.UTF_8);
        byte[] keyBytes = KEY.getBytes(StandardCharsets.UTF_8);
        byte[] encryptedBytes = new byte[inputBytes.length];

        for (int i = 0; i < inputBytes.length; i++) {
            encryptedBytes[i] = (byte) (inputBytes[i] ^ keyBytes[i % keyBytes.length]);
        }

        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String decrypt(String input) {
        byte[] inputBytes = Base64.getDecoder().decode(input);
        byte[] keyBytes = KEY.getBytes(StandardCharsets.UTF_8);
        byte[] decryptedBytes = new byte[inputBytes.length];

        for (int i = 0; i < inputBytes.length; i++) {
            decryptedBytes[i] = (byte) (inputBytes[i] ^ keyBytes[i % keyBytes.length]);
        }

        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }
    public static void main(String[] args) {

    }
}
