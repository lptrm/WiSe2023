package cryptographics.aes_problems;
/*
 * For the AES algorithm, some computations are done by Galois Fields (GF).
 * With the following problems, we practice some basic computations.
 * Compute the multiplication and addition table for the prime field GF(7). A mul-
 * tiplication table is a square (here: 7 × 7) table which has as its rows and columns all
 * field elements. Its entries are the products of the field element at the corresponding
 * row and column. Note that the table is symmetric along the diagonal. The addition
 * table is completely analogous but contains the sums of field elements as entries
 */
public class Problem_4_2 {
  int[][] multiplicationTable = new int[7][7];
  int[][] additionTable = new int[7][7];
  public Problem_4_2() {
    for (int i = 0; i < 7; i++) {
      for (int j = 0; j < 7; j++) {
        multiplicationTable[i][j] = (i * j) % 7;
        additionTable[i][j] = (i + j) % 7;
      }
    }
  }
  public static void printTable(int[][] table) {
    for (int[] row : table) {
      for (int value : row) {
        System.out.print(value + " ");
      }
      System.out.println();
    }
  }
  public static void main(String[] args){
    Problem_4_2 problem = new Problem_4_2();
    System.out.println("Multiplication Table:");
    printTable(problem.multiplicationTable);
    System.out.println("Addition Table:");
    printTable(problem.additionTable);
  }
}
