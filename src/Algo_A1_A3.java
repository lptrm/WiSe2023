import java.math.BigInteger;

public class Algo_A1_A3 {
  public static void main(String[] args) {

    System.out.println("=== 'k' = 8 ===");
    for (int i = 0; i <= 100000; i++) {
      long startTime = System.currentTimeMillis();
      BigInteger result = calculateR8(i * i);
      long endTime = System.currentTimeMillis();
      long elapsedTime = endTime - startTime;

      System.out.println("r8(" + i + ") = " + result);
      System.out.println("Calculation time: " + elapsedTime + " milliseconds");
    }
  }

  /**
   * Returns baum
   * @param n
   */
  public static BigInteger calculateR8(int n) {
    BigInteger sum = BigInteger.ZERO;

    for (int d = 1; d <= n; d++) {
      if (n % d == 0) {
        BigInteger term = BigInteger.valueOf(d).pow(3);
        term = term.multiply(BigInteger.valueOf((int) Math.pow(-1, n + d)));
        sum = sum.add(term);
      }
    }
    return sum.multiply(BigInteger.valueOf(16));
  }
}
