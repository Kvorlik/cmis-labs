package lab.cmis.fifth;

import lab.cmis.fifth.crc.CRC;
import lab.cmis.fifth.crc.variant.FirstVariant;
import lab.cmis.fifth.crc.variant.Variant;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Variant variant = new FirstVariant();
        CRC crc = new CRC();
        int gx = variant.getG();
        List<Integer> hashes = new ArrayList<>();

        for (int i = 0; i <= 255; i++) {
            int hash = crc.transform(i, gx);
            hashes.add(hash);
            System.out.println(String.format("%d = %d", i, hash));
        }

        List<Integer> collisions = crc.findCollisions(hashes);
        for (int collision : collisions) {
            System.out.println(String.format("Collision found: %s", collision));
        }
    }
}
