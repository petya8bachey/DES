import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.io.File;
import java.util.Base64;
import java.util.Scanner;

import static javax.crypto.Cipher.DECRYPT_MODE;
import static javax.crypto.Cipher.ENCRYPT_MODE;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(new File("text.txt"));
        String text = scanner.nextLine();
        System.out.println(text);

        KeyGenerator keyGen = KeyGenerator.getInstance("DES");
        keyGen.init(56);
        SecretKey key = keyGen.generateKey();
        //Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");

        cipher.init(ENCRYPT_MODE, key);
        byte[] cText = cipher.doFinal(text.getBytes());
        System.out.println(Base64.getEncoder().encodeToString(cText));

        byte iv[] = cipher.getIV();
        IvParameterSpec dps = new IvParameterSpec(iv);
        cipher.init(DECRYPT_MODE, key, dps);
        //cipher.init(DECRYPT_MODE, key);
        byte[] dText = cipher.doFinal(cText);
        System.out.println(new String(dText));
    }
}