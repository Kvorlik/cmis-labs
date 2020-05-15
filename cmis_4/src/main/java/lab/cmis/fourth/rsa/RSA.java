package lab.cmis.fourth.rsa;

import lab.cmis.fourth.rsa.dictionary.Dictionary;
import lab.cmis.fourth.rsa.dictionary.RussianDictionary;
import lab.cmis.fourth.rsa.utils.Pair;
import lab.cmis.fourth.rsa.variant.FirstVariant;
import lab.cmis.fourth.rsa.variant.Variant;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RSA {
    private final Map<Character, Integer> russianLetters;
    private final int p;
    private final int q;
    private final int n;

    public RSA() {
        Variant variant = new FirstVariant();
        Dictionary rd = new RussianDictionary();
        russianLetters = rd.getDictionary();
        p = variant.getP();
        q = variant.getQ();
        n = p * q;
    }

    public List<Pair<Integer, Integer>> generateKeys() {
        List<Pair<Integer, Integer>> keys = new ArrayList<>();
        int ed = (p - 1) * (q - 1) + 1;
        while (true) {
            for (int i = 2; i < ed; i++) {
                if (ed % i == 0) {
                    int d = ed / i;
                    if (isMutuallySimple(i, d)) {
                        keys.add(new Pair<>(i, d));
                    }
                    if (keys.size() == 3) {
                        return keys;
                    }
                }
            }
        }
    }

    public List<Integer> encrypt(final String s, final int e) {
        List<Integer> target = toNumberBlocks(s);
        List<Integer> encrypted = new ArrayList<>();
        for (int i : target) {
            BigInteger bigI = BigInteger.valueOf(i);
            BigInteger bigE = BigInteger.valueOf(e);
            BigInteger bigN = BigInteger.valueOf(n);
            int num = bigI.modPow(bigE, bigN).intValue();
            encrypted.add(num);
        }
        return encrypted;
    }

    public String decrypt(final List<Integer> target, final int d) {
        StringBuilder decrypted = new StringBuilder();
        for (int i : target) {
            BigInteger bigI = BigInteger.valueOf(i);
            BigInteger bigD = BigInteger.valueOf(d);
            BigInteger bigN = BigInteger.valueOf(n);
            int num = bigI.modPow(bigD, bigN).intValue();
            decrypted.append(num);
        }
        return toLetters(decrypted.toString());
    }

    private boolean isMutuallySimple(final int a, final int b) {
        return BigInteger.valueOf(a).gcd(BigInteger.valueOf(b)).intValue() == 1;
    }

    private String toNumbers(final String s) {
        String target = s.toUpperCase();
        StringBuilder transformed = new StringBuilder();
        for (char c : target.toCharArray()) {
            transformed.append(russianLetters.get(c));
        }
        return transformed.toString();
    }

    private String toLetters(final String s) {
        StringBuilder transformed = new StringBuilder();
        Set<Map.Entry<Character, Integer>> entrySet = russianLetters.entrySet();
        for (int i = 0; i <= s.length() - 2; i += 2) {
            int num = Integer.parseInt(s.substring(i, i + 2));
            for (Map.Entry<Character, Integer> pair : entrySet) {
                if (pair.getValue() == num) {
                    transformed.append(pair.getKey());
                }
            }
        }
        return transformed.toString();
    }

    private List<Integer> toNumberBlocks(final String s) {
        List<Integer> blocks = new ArrayList<>();
        String target = toNumbers(s);
        int previousValue = 0;
        String candidate = "";
        for (int i = 3; i < target.length(); i++) {
            if (Integer.parseInt(target.substring(previousValue, i + 1)) <= n) {
                candidate = target.substring(previousValue, i + 1);
            } else {
                blocks.add(Integer.parseInt(candidate));
                previousValue = i;
            }
        }
        blocks.add(Integer.parseInt(candidate));
        return blocks;
    }
}
