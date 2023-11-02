package cryptographics.aes_problems;
/*
 * Generate the multiplication table for the extension field GF(23 ) for the case
 * that the irreducible polynomial is P(x) = x 3 + x + 1. The multiplication table is in
 * this case a 8 × 8 table. (Remark: You can do this manually or write a program for
 * it.)
 */
public class Problem_4_3 {
  int[][] multiplicationTable = new int[8][8];
  int irreduciblePolynomial = 0b000001011;

  public Problem_4_3() {
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        multiplicationTable[i][j] = multiply(i, j);
      }
    }
  }

  public int multiply(int a, int b) {
    int result = 0; // Initialize resulting bits
    while (b != 0) {
      if ((b & 1) != 0) { // if the least significant bit is set
        result ^= a; // add the current a to the result, because we have to multiply a by 1
      }
      a <<= 1; // shift a to the left by 1, because we have to multiply a by x
      if ((a & 0b1000) != 0) { // if a doesnt fit into the field anymore
        a ^= irreduciblePolynomial; // reduce a by the irreducible polynomial
      }
      b >>= 1;  // shift b to the right by 1, because we have to divide b by x
    }
    return result;
  }

  public static void printTablePolynomial(int[][] table) {
    for (int[] row : table) {
      for (int value : row) {
        boolean printed = false;

        for (int i = 2; i >= 0; i--) {
          int term = (value >> i) & 1;
          if (term == 1) {
            if (printed) {
              System.out.print(" + ");
            }
            if (i == 0) {
              System.out.print("1");
            } else {
              System.out.print("x^" + i);
            }
            printed = true;
          }
        }

        if (!printed) {
          System.out.print("0");
        }
        System.out.print(" ");
      }
      System.out.println();
    }
    System.out.println();
  }

  public static void main(String[] args) {
    Problem_4_3 problem = new Problem_4_3();
    System.out.println("Multiplication Table:");
    Problem_4_2.printTable(problem.multiplicationTable);
    System.out.println();
    printTablePolynomial(problem.multiplicationTable);
  }
}
