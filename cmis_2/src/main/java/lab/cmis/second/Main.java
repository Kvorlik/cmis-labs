package lab.cmis.second;

import lab.cmis.second.register.LFSRegister;
import lab.cmis.second.register.variant.FirstVariant;
import lab.cmis.second.register.variant.Variant;

public class Main {
    public static void main(String[] args) {
        Variant variant = new FirstVariant();
        LFSRegister register = new LFSRegister(variant);
        StringBuilder out = new StringBuilder();

        register.generateArray();
        int period = register.findPeriod();
        int evens = register.countEvenAndOdd().getKey();
        int odds = register.countEvenAndOdd().getValue();
        int zeroes = register.countOfZeroesAndOnes().getKey();
        int ones = register.countOfZeroesAndOnes().getValue();
        out.append("Linear Feedback Shift Register").append("\n")
                .append("Generating array...").append("\n")
                .append(register.toString()).append("\n")
                .append("Generator period length in bits: ").append(period).append("\n")
                .append("The number of even and odd numbers in one period with a single-byte representation: ")
                .append(evens).append(" and ").append(odds).append("\n")
                .append("The number of zeroes and ones in one period with a single-byte representation: ")
                .append(zeroes).append(" and ").append(ones);
        System.out.println(out.toString());
    }
}
