package lab.cmis.sixth;

import lab.cmis.sixth.elgammalsignature.ElGammalDigitalSignature;
import lab.cmis.sixth.variant.FirstVariant;
import lab.cmis.sixth.variant.Variant;

import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {
        Variant variant = new FirstVariant();
        ElGammalDigitalSignature signature = new ElGammalDigitalSignature();
        String m = "I am signed message.";
        String m1 = "I am not signed!";
        BigInteger p = variant.getP();
        BigInteger g = variant.getG();
        BigInteger x = variant.getX();
        BigInteger gx = variant.getGx();
        BigInteger y = g.pow(x.intValue()).divideAndRemainder(p)[1];
        BigInteger h = signature.getMessageHash(m, gx);

        int k = 0;
        int i = 2;
        while (true) {
            if (signature.isMutuallySimple(variant.F(i), p.intValue() - 1) &&
                    signature.inverse(variant.F(i) % p.intValue(), p.longValue() - 1) != 0) {
                k = variant.F(i) % p.intValue();
                break;
            } else {
                i++;
            }
        }
        int invertedK = signature.inverse(k, p.longValue() - 1);

        BigInteger r = g.pow(k).divideAndRemainder(p)[1];
        BigInteger u = (h.subtract(x.multiply(r))).divideAndRemainder(p.subtract(BigInteger.ONE))[1];
        BigInteger s = BigInteger.valueOf(invertedK).multiply(u).divideAndRemainder(p.subtract(BigInteger.ONE))[1];
        while (s.compareTo(BigInteger.ZERO) < 0) {
            s = s.add(p.subtract(BigInteger.ONE));
        }

        System.out.println(String.format("Signed text: %s; r = %d; s = %d\n", m, r, s));

        System.out.println(String.format("Text: %s", m));
        if (signature.isSignCorrect(m, r, s, p, g, y, gx)) {
            System.out.println("Signature is correct.\n");
        } else {
            System.out.println("Signature is incorrect!\n");
        }

        System.out.println(String.format("Text: %s", m1));
        if (signature.isSignCorrect(m1, r, s, p, g, y, gx)) {
            System.out.println("Signature is correct.");
        } else {
            System.out.println("Signature is incorrect!");
        }
    }
}
