package lab.cmis.fifth.crc;

import java.util.*;

public class CRC {
    public int transform(final int message, final int gx) {
        int result = message;
        if (result < gx) {
            return result;
        }
        if (result == gx) {
            return 0;
        }
        result = result << binaryLength(gx) - 1;
        result = result ^ (gx << (binaryLength(result) - binaryLength(gx)));
        while (binaryLength(result) >= binaryLength(gx)) result = result ^ (gx << (binaryLength(result) - binaryLength(gx)));
        return result;
    }

    public List<Integer> findCollisions(final List<Integer> hashes) {
        List<Integer> collisions = new ArrayList<>();
        Set<Integer> set = new HashSet<>(collisions);

        if(set.size() == hashes.size()){
            return collisions;
        }

        for (int i = 0; i < hashes.size(); i++) {
            Integer a = hashes.get(i);
            for (int j = 0; j < hashes.size(); j++) {
                if (i != j) {
                    Integer b = hashes.get(j);
                    if (!collisions.contains(a) && a.equals(b)) {
                        collisions.add(i);
                    }
                }
            }
        }
        return collisions;
    }

    private int binaryLength(final int number) {
        int i = 0;
        int num = number;
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
