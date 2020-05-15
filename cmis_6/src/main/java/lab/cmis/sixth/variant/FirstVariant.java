package lab.cmis.sixth.variant;

import java.math.BigInteger;

public class FirstVariant implements Variant {
    @Override
    public BigInteger getP() {
        return new BigInteger("227");
    }

    @Override
    public BigInteger getG() {
        return new BigInteger("24");
    }

    @Override
    public BigInteger getX() {
        return new BigInteger("3");
    }

    /**
     * 1+x^1+x^2 = 111 = 7
     * @return BigInteger
     */
    @Override
    public BigInteger getGx() {
        return new BigInteger("7");
    }

    @Override
    public int F(final int x) {
        int ui = Integer.parseUnsignedInt(String.valueOf(x));
        int bit = ui ^ (ui >> 5) ^ (ui >> 7);
        return (ui >> 1) | (bit << 7);
    }
}
