import java.math.BigInteger;
import java.util.*;

public class AlgoA1_A {
  private static final Map<BigInteger, BigInteger> factorialCache = new HashMap<>();

  public static BigInteger factorial(BigInteger n) {
    if (n.compareTo(BigInteger.ONE) <= 0) {
      return BigInteger.ONE;
    }

    if (factorialCache.containsKey(n)) {
      return factorialCache.get(n);
    }

    BigInteger result = n.multiply(factorial(n.subtract(BigInteger.ONE)));
    factorialCache.put(n, result);
    return result;
  }

  public static BigInteger countUniquePermutations(int maxDigits, List<Integer> combination) {
    int nonZeroCount = combination.size();
    int zeroCount = maxDigits - nonZeroCount;

    Set<Integer> uniqueValues = new HashSet<>(combination);
    BigInteger uniquePermutations;
    BigInteger denominator = BigInteger.ONE;
    BigInteger negatedPermutations = BigInteger.ONE;

    for (int value : uniqueValues) {
      long count = combination.stream().filter(x -> x == value).count();
      denominator = denominator.multiply(factorial(BigInteger.valueOf(count)));
      negatedPermutations = negatedPermutations.multiply(BigInteger.valueOf(2).pow((int) count));
    }

    BigInteger maxDigitsFactorial = factorial(BigInteger.valueOf(maxDigits));
    BigInteger zeroCountFactorial = factorial(BigInteger.valueOf(zeroCount));

    uniquePermutations = maxDigitsFactorial.divide(denominator.multiply(zeroCountFactorial));

    return negatedPermutations.multiply(uniquePermutations);
  }

  public static void findSquareCombinations(
      int target,
      List<Integer> current,
      int currentSum,
      int currentNum,
      Set<List<Integer>> combinations,
      int digits) {
    if (currentSum == target) {
      combinations.add(new ArrayList<>(current));
      return;
    }

    if (currentSum > target || currentNum * currentNum > target || current.size() >= digits) {
      return;
    }

    for (int i = currentNum; i * i <= target - currentSum; i++) {
      current.add(i);
      findSquareCombinations(target, current, currentSum + i * i, i, combinations, digits);
      current.remove(current.size() - 1);
    }
  }

  public static void test(int a) {
    int target = a;
    int digits = 8;

    List<Integer> current = new ArrayList<>();
    HashSet<List<Integer>> combinations = new HashSet<>();
    BigInteger permutations = BigInteger.ZERO;

    long startTime = System.nanoTime();
    findSquareCombinations(target * target, current, 0, 1, combinations, digits);

    for (List<Integer> combination : combinations) {
      BigInteger uniquePermutations = countUniquePermutations(digits, combination);
      permutations = permutations.add(uniquePermutations);
    }
    long endTime = System.nanoTime();
    long duration = (endTime - startTime) / 1000;

    System.out.println("Total Permutations " + permutations);
  }

  public static void main(String[] args) {
    for (int i = 0; i < 101; i++) {
      System.out.println("n = " + i);
      test(i);
    }
  }
}
