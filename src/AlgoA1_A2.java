import java.util.HashMap;
import java.util.Map;

public class AlgoA1_A2 {
    private static Map<String, Long> cache = new HashMap<>();

    // Count the ways to represent pm as the sum of squares of k integers, using a cache
    public static long countWaysToRepresent(long pm, int n, int k) {
        // Generate a unique key for the cache using the values of pm and k
        String key = pm + "-" + k;
        if (cache.containsKey(key)) {
            // If the result is in the cache, return it
            return cache.get(key);
        }
        // If the result is not in the cache, compute it and store it in the cache
        long result = countWays(pm, n, k);
        cache.put(key, result);
        return result;
    }

    // Recursive function to count the ways to represent pm as the sum of squares of k integers
    private static long countWays(long pm, int n, int k) {
        // Base cases:
        if (pm == 0 && k == 0) {
            // If pm is zero and k is zero, there is one valid representation
            return 1;
        }
        if (k == 0 || n == 0) {
            // If k is zero or n is zero, there are no valid representations
            return 0;
        }

        // Initialize the totalWays counter to zero
        long totalWays = 0;

        // Calculate the result for k-1 if we exclude the current square
        long r2 = countWaysToRepresent(pm, n, k - 1);
        totalWays += r2;

        // Consider each integer from n down to 1
        for (int i = n; i > 0; i--) {
            // Calculate the new pmn after subtracting the square of the current integer
            long pmn = pm - (long) i * i;
            if (pmn < 0) {
                // If pmn is negative, skip it as it's not a valid option
                continue;
            }
            // Calculate the result for k-1 with the updated pmn
            long r = countWaysToRepresent(pmn, n, k - 1);
            // Add the result for pmn with the current integer squared to the totalWays
            totalWays += 2 * r;
        }

        return totalWays;
    }

    // Count the ways to represent n^2 as the sum of squares of 8 integers
    public static long countRepresentations(int n) {
        if (n == 0) {
            // Special case: If n is zero, there is one valid representation
            return 1;
        }
        // Calculate pm as n^2 and use countWaysToRepresent to find the result
        long pm = (long) n * n;
        return countWaysToRepresent(pm, n, 8);
    }

    public static void main(String[] args) {
        for (int n = 0; n <= 50; n++) {
            long startTime = System.nanoTime();
            long result = countRepresentations(n);
            long endTime = System.nanoTime();
            long duration = (endTime - startTime) / 1000000; // Convert to milliseconds
            System.out.println("Total Permutations for " + n + ": " + result);
            System.out.println("Time taken: " + duration + " ms");
        }
    }
}
