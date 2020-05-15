package lab.cmis.second.register;

import lab.cmis.second.register.variant.Variant;
import lab.cmis.second.utils.Pair;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

public class LFSRegister {
    private final Variant variant;
    private List<Byte> byteArray;

    public LFSRegister(final Variant variant) {
        this.variant = variant;
    }

    public void generateArray() {
        byteArray = new ArrayList<>();
        byteArray.add(variant.getInitial());
        byte current_byte = variant.F(variant.getInitial());
        while (current_byte != variant.getInitial()) {
            byteArray.add(current_byte);
            current_byte = variant.F(current_byte);
        }
    }

    public Pair<Integer, Integer> countEvenAndOdd() {
        int countOfEven = 0;
        int countOfOdd = 0;
        for (Byte b : byteArray) {
            if (b % 2 == 0) {
                countOfEven++;
            } else {
                countOfOdd++;
            }
        }
        return new Pair<>(countOfEven, countOfOdd);
    }

    public Pair<Integer, Integer> countOfZeroesAndOnes() {
        int countOfZeroes = 0;
        int countOfOnes = 0;
        byte[] bytes = unwrapByteArray();
        BitSet bits = BitSet.valueOf(bytes);
        for (int i = 0; i < bits.length(); i++) {
            if (bits.get(i)) {
                countOfOnes++;
            } else {
                countOfZeroes++;
            }
        }
        return new Pair<>(countOfZeroes, countOfOnes);
    }

    public int findPeriod() {
        return byteArray.size() * 8;
    }

    private byte[] unwrapByteArray() {
        byte[] unwrapped = new byte[byteArray.size()];
        for (int i = 0; i < byteArray.size(); i++) {
            unwrapped[i] = byteArray.get(i);
        }
        return unwrapped;
    }

    public String toString() {
        StringBuilder out = new StringBuilder();
        for (Byte b : byteArray) {
            out.append(Byte.toUnsignedInt(b)).append(" ");
        }
        return out.toString();
    }
}
