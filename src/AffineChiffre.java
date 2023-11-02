import java.util.Stack;

public class AffineChiffre {
  public static void main(String[] args) {
    int[] message = {0, 19, 19, 0, 2, 10};
    System.out.println("The Message is: ");
    for (int value : message) {
      char character = (char) (value + 'A');
      System.out.print(character);
    }
    System.out.println();
    System.out.println("This is encoded: ");
    int[] cypher = new int[6];
    for (int i = 0; i < 6; i++) {
      cypher[i] = encode(message[i], 9, 13);
      char character = (char) (cypher[i] + 'A');
      System.out.print(character);
    }
    System.out.println();
    System.out.println("Check if decoding is correct...");

    for (int i = 0; i < 6; i++) {
      if (decode(cypher[i], 3, 13) != message[i])
        throw new RuntimeException("Decoded Cypher doesn't match with the message");
    }
    System.out.println("Success!");

    Stack<Integer> stack = new Stack<>();
    for (int i = 0; i <= 26; i++) {

      if (gcd(i, 26) == 1) stack.push(i);
    }
    System.out.println("Keyspace size is: " + stack.size());
    System.out.println("The Keys are: ");
    while (!stack.empty()) {
      System.out.print(stack.pop() + " ");
    }
    System.out.println();
  }

  public static int gcd(int a, int b) {
    return b == 0 ? a : gcd(b, a % b);
  }

  public static int encode(int x, int a, int b) {
    return (a * x + b) % 26;
  }

  public static int decode(int y, int aInv, int b) {
    if (y - b >= 0) return (aInv * (y - b)) % 26;
    else return ((aInv * (y - b)) % 26) + 26;
  }
}
