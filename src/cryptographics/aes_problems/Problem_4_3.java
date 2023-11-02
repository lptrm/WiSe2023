package cryptographics.aes_problems;

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
public int multiply(int a, int b) {
    int result = 0;
    while (b != 0) {
        if ((b & 1) != 0) {
            result ^= a;
        }
        a <<= 1;
        if ((a & 0b1000) != 0) {
            a ^= irreduciblePolynomial;
        }
        b >>= 1;
    }
    return result;
}
  public static void main(String[] args){
    Problem_4_3 problem = new Problem_4_3();
    System.out.println("Multiplication Table:");
    Problem_4_2.printTable(problem.multiplicationTable);
    System.out.println();
    printTablePolynomial(problem.multiplicationTable);
  }
}
