package lab.cmis.sixth.elgammalsignature;

import java.math.BigInteger;

public class ElGammalDigitalSignature {
    public boolean isSignCorrect(
            final String m, final BigInteger r, final BigInteger s,
            final BigInteger p, final BigInteger g, final BigInteger y, final BigInteger gx
    ) {
        BigInteger h = getMessageHash(m, gx);
        BigInteger left = y.modPow(r, p).multiply(r.modPow(s, p).divideAndRemainder(p)[1]);
        BigInteger right = g.modPow(h, p);
        return left.equals(right);
    }

    public BigInteger getMessageHash(final String message, final BigInteger gx) {
        BigInteger msg = BigInteger.ZERO;
        char[] messageChars = message.toCharArray();
        for (char c : messageChars) {
            if (msg.equals(BigInteger.ZERO)) {
                msg = BigInteger.valueOf(c);
            } else {
                msg = (msg.shiftLeft(binaryLength(c))).or(BigInteger.valueOf(c));
            }
        }
        return crc(msg, gx);
    }

    public boolean isMutuallySimple(final long a, final long b) {
        return BigInteger.valueOf(a).gcd(BigInteger.valueOf(b)).intValue() == 1;
    }

    public int inverse(long a, long m) {
        if (!isMutuallySimple(a, m)) {
            return 0;
        }
        for (int x = 1; x <= m; x++) {
            if (a * x % m == 1) {
                return x;
            }
        }
        return 0;
    }

    private BigInteger crc(final BigInteger message, final BigInteger gx) {
        BigInteger result = message;
        if (result.compareTo(gx) < 0) {
            return result;
        }
        if (result.equals(gx)) {
            return BigInteger.ZERO;
        }
        result = result.shiftLeft(gx.bitLength() - 1);
        result = result.xor(gx.shiftLeft(result.bitLength() - gx.bitLength()));
        while (result.bitLength() >= gx.bitLength()) {
            result = result.xor(gx.shiftLeft(result.bitLength() - gx.bitLength()));
        }
        return result;
    }

    private int binaryLength(final long number) {
        int i = 0;
        long num = Long.parseLong(String.valueOf(number));
        if (num == 1) {
            return 1;
        }
        while (num >= 1)
        {
            if (num % 2 == 1)
            {
                num -= 1;
            }
            num /= 2;
            i++;
        }
        return i;
    }
}
