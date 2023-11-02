import java.util.HashMap;
import java.util.Map;
import java.math.BigInteger;

public class AlgoA1_B2 {
    private static Map<String, BigInteger> cache = new HashMap<>();
    private static int[] permutation = new int[8];

    public static BigInteger countWaysToRepresent(BigInteger pm, int n, int k, int depth) {
        String key = pm + "-" + k;
        if (cache.containsKey(key)) {
            return cache.get(key);
        }
        BigInteger result = countWays(pm, n, k, depth);
        cache.put(key, result);
        return result;
    }

    private static BigInteger countWays(BigInteger pm, int n, int k, int depth) {
        if (pm.equals(BigInteger.ZERO) && k == 0) {
            if (depth == 8) {
                printPermutation();
            }






            return BigInteger.ONE;
        }
        if (k == 0 || n == 0) {
            return BigInteger.ZERO;
        }
        BigInteger totalWays = BigInteger.ZERO;
        BigInteger r2 = countWaysToRepresent(pm, n, k - 1, depth);
        totalWays = totalWays.add(r2);
        for (int i = n; i > 0; i--) {
            BigInteger pmn = pm.subtract(BigInteger.valueOf(i).multiply(BigInteger.valueOf(i)));
            if (pmn.compareTo(BigInteger.ZERO) < 0) {
                continue;
            }
            permutation[depth] = i;
            BigInteger r = countWaysToRepresent(pmn, n, k - 1, depth + 1);
            totalWays = totalWays.add(r.multiply(BigInteger.valueOf(2)));
        }
        return totalWays;
    }

    private static void printPermutation() {
        for (int i = 0; i < 8; i++) {
            System.out.print(permutation[i] + " ");
        }
        System.out.println();
    }

    public static BigInteger countRepresentations(int n) {
        if (n == 0) {
            return BigInteger.ONE;
        }
        BigInteger pm = BigInteger.valueOf(n).multiply(BigInteger.valueOf(n));
        return countWaysToRepresent(pm, n, 8, 0);
    }

    public static void main(String[] args) {
        int n = 4; // Change n to your desired value
        BigInteger result = countRepresentations(n);
        System.out.println("Total Permutations: " + result);
    }
}
