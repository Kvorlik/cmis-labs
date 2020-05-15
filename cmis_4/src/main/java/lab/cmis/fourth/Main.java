package lab.cmis.fourth;

import lab.cmis.fourth.rsa.RSA;
import lab.cmis.fourth.rsa.utils.Pair;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int e, d;
        RSA rsa = new RSA();
        List<Pair<Integer, Integer>> keys = rsa.generateKeys();
        Scanner in = new Scanner(System.in);

        int number = 0;
        for (Pair<Integer, Integer> pair : keys) {
            number++;
            System.out.println(String.format(
                    "%d: e = %d; d = %d",
                    number, pair.getKey(), pair.getValue()
            ));
        }

        System.out.println("Choose any pair of keys:");
        int choice = 0;
        choice = in.nextInt();
        switch (choice) {
            case 1:
                e = keys.get(0).getKey();
                d = keys.get(0).getValue();
                break;
            case 2:
                e = keys.get(1).getKey();
                d = keys.get(1).getValue();
                break;
            case 3:
            default:
                e = keys.get(2).getKey();
                d = keys.get(2).getValue();
        }

        System.out.println("Enter message to encrypt: ");
        String message = in.next();
        List<Integer> encrypted = rsa.encrypt(message, e);

        System.out.print("Encrypted message: ");
        for (int i : encrypted) {
            System.out.print(String.format("%d ", i));
        }

        String decrypted = rsa.decrypt(encrypted, d);
        System.out.println(String.format("\nDecrypted message: %s", decrypted));
    }
}
