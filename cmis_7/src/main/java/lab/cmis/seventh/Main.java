package lab.cmis.seventh;

import lab.cmis.seventh.analyser.RsaAnalyser;
import lab.cmis.seventh.variant.FirstVariant;
import lab.cmis.seventh.variant.Variant;

import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {
        Variant variant = new FirstVariant();
        RsaAnalyser analyser = new RsaAnalyser();

        int[] pq = analyser.factorize(variant.getN());
        int d = analyser.getD(variant.getE(), pq[0], pq[1]);
        System.out.println(String.format("Private key is: %d", d));

        int m = BigInteger.valueOf(variant.getC())
                .modPow(BigInteger.valueOf(d), BigInteger.valueOf(variant.getN()))
                .intValue();
        System.out.println(String.format("Decrypted message: %c", (char) m));
    }
}
