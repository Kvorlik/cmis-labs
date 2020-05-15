package lab.cmis.seventh.analyser;

public class RsaAnalyser {
    public int[] factorize(final int n) {
        int a, b;
        a = (int) Math.ceil(Math.sqrt(n));
        if (a * a == n) {
            return new int[] {a, a};
        }
        while (true) {
            int temp = a * a - n;
            b = (int) Math.sqrt(temp);
            if (b * b == temp) {
                break;
            }
            a++;
        }
        return new int[] {a - b, a + b};
    }
    
    public int getD(final int e, final int p, final int q) {
        int mod = (p - 1) * (q - 1);
        int temp = 1 + mod;
        while (temp % e != 0) {
            temp += mod;
        }
        return (temp / e) % ((p - 1) * (q - 1));
    }
}
