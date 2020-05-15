package lab.cmis.first;

import lab.cmis.first.generator.PRSGenerator;
import lab.cmis.first.generator.PRSGeneratorException;
import lab.cmis.first.generator.variant.FirstVariant;
import lab.cmis.first.generator.variant.Variant;
import lab.cmis.first.utils.Pair;

public class Main {
    public static void main(final String[] args) {
        Variant variant = new FirstVariant();
        PRSGenerator generator = new PRSGenerator(variant);
        StringBuilder out = new StringBuilder();
        out.append("Start value: ").append(generator.getStartValue()).append("\n");
        out.append("Generated values:\n");

        generator.generate();
        out.append(generator.toString()).append("\n");
        out.append("Period found: ").append(generator.getPeriod()).append("\n");
        out.append("Period byte length: ").append(generator.getPeriodByteLength()).append("\n\n");

        try {
            out.append("Counts of odd and even values in byte representation:\n");
            Pair<Integer, Integer> byteStatistic = generator.countByteStat();
            out.append("even: ").append(byteStatistic.getKey()).append("\n");
            out.append("odd: ").append(byteStatistic.getValue()).append("\n\n");

            out.append("Counts of zeroes and ones in bit representation:\n");
            Pair<Integer, Integer> bitStatistic = generator.countBitStat();
            out.append("zeroes: ").append(bitStatistic.getKey()).append("\n");
            out.append("ones: ").append(bitStatistic.getValue());
        } catch (PRSGeneratorException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(out.toString());
    }
}
