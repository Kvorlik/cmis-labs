package lab.cmis.sixth.variant;

import java.math.BigInteger;

public interface Variant {
    BigInteger getP();
    BigInteger getG();
    BigInteger getX();
    BigInteger getGx();
    int F(int x);
}
