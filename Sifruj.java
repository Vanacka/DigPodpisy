import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Scanner;

public class Sifruj {
    public static void main(String[] args) throws NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, IOException, InvalidKeySpecException {
        Scanner sc = new Scanner(System.in);
        String vstup = sc.nextLine();


        //prectu binarni soubor verejneho klice, ktery ulozim do pole
        //a potom z pole ulozim klic do specialni promenne pro klic
        byte [] publicKlic = Files.readAllBytes(Paths.get("verejnyKlic.dat"));
        X509EncodedKeySpec bobPubKeySpec = new X509EncodedKeySpec(publicKlic);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(bobPubKeySpec);

        //zavolam konstruktor cipher a reknu mu ze bude pouzivat RSA k sifrovani/ desifrovani
        Cipher cipher = Cipher.getInstance("RSA");
        //nastavim cipher ze bude sifrovat pomoci klice key
        cipher.init(Cipher.PUBLIC_KEY, publicKey);

        //zasifruji zpravu ze vstupu a ulozim do pole
        byte[] sifra = cipher.doFinal(vstup.getBytes());

        Path path = Paths.get("message.dat");
        Files.write(path, sifra);
    }
}
