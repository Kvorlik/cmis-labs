package lab.cmis.third;

import lab.cmis.third.xorcipher.XORCipher;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Enter filename to encode or decode");
            return;
        }

        XORCipher xorc = new XORCipher();
        xorc.encode(args[0]);
        System.out.println("Done");
    }
}
