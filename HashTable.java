import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

public class HashTable {
    public static int mujHash(String str) {
        int out = 0;
        for(char ch: str.toCharArray()) out += (int)ch;
        return out %= 13;
    }

    public static void main(String[] args) {
        String tabulka[] = new String[13];
        Scanner sc = new Scanner(System.in);
        ArrayList<String> poleSlov= new ArrayList<String>();

        while(true) {
            String vstup = sc.next();
            int hash = mujHash(vstup);
            if (tabulka[hash] != null) {
                System.out.println("Blbecku uz jsi to psal " + vstup);
            }
            else {
                tabulka[hash] = vstup;
                //System.out.println("Zapsal jsem to tam " + vstup);
            }
        }
    }
}
