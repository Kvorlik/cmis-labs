package lab.cmis.first.generator;

import lab.cmis.first.generator.variant.Variant;
import lab.cmis.first.utils.Pair;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class PRSGenerator {
    private final Variant variant;
    private final int startValue;
    private int previousValue;
    private int currentIndex = 1;
    private Integer period;
    private final List<Integer> list;

    public PRSGenerator(final Variant variant) {
        this.variant = variant;
        startValue = variant.getX0();
        previousValue = startValue;
        period = null;
        list = new ArrayList<>();
    }

    public void generate() {
        while (period == null) {
            int target = (variant.getA() * previousValue + variant.getC()) % variant.getM();
            if (target == startValue) {
                period = currentIndex;
            }
            list.add(target);
            currentIndex++;
            previousValue = target;
        }
    }

    public int getStartValue() {
        return startValue;
    }

    public Integer getPeriod() {
        return period;
    }

    public int getPeriodByteLength() {
        if (period != null) {
            return list.size() * 4;
        } else {
            return 0;
        }
    }

    public Pair<Integer, Integer> countByteStat() throws PRSGeneratorException {
        int even = 0;
        int odd = 0;
        if (period != null) {
            for (int i = 0; i < period; i++) {
                int element = list.get(i);
                byte[] fourBytes = ByteBuffer.allocate(4).putInt(element).array();
                for (int j = 0; j < 4; j++) {
                    byte singleByte = fourBytes[j];
                    if ((singleByte % 2) == 0) {
                        even++;
                    } else {
                        odd++;
                    }
                }
            }
            return new Pair<>(even, odd);
        } else {
            throw new PRSGeneratorException("Period still not found. Generate more values.");
        }
    }

    public Pair<Integer, Integer> countBitStat() throws PRSGeneratorException {
        int zeros = 0;
        int ones = 0;
        if (period != null) {
            for (int i = 0; i < period; i++) {
                int element = list.get(i);
                byte[] bytes = ByteBuffer.allocate(4).putInt(element).array();
                for (int j = 0; j < 4; j++) {
                    char[] bits = getBits(bytes[j]);
                    for (char bit : bits) {
                        if (bit == '0') {
                            zeros++;
                        } else {
                            ones++;
                        }
                    }
                }
            }
            return new Pair<>(zeros, ones);
        } else {
            throw new PRSGeneratorException("Period still not found. Generate more values.");
        }
    }

    private char[] getBits(final byte b) {
        final char[] bits = new char[8];
        int mask = 0x1;
        for (int j = 7; j >= 0; j--) {
            final int bitValue = b & mask;
            if (bitValue == 0) {
                bits[j] = '0';
            } else {
                bits[j] = '1';
            }
            mask <<= 1;
        }
        return bits;
    }

    public String toString() {
        return list.toString();
    }
}
