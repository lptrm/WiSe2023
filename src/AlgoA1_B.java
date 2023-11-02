import java.util.*;

public class AlgoA1_B {

  public static void printList(List<Integer> list) {
    for (int num : list) {
      System.out.print(num + " ");
    }
    System.out.println();
  }

  public static void generatePermutations(List<Integer> list, Set<List<Integer>> result) {
    int n = list.size();
    Collections.sort(list); // Sortiere die Eingangsliste am Anfang, um in aufsteigender Reihenfolge zu
                            // beginnen
    while (true) {
      result.add(new ArrayList<>(list));

      // Finde das größte Index i, für das list[i] < list[i+1]
      int i = n - 2;
      while (i >= 0 && list.get(i) >= list.get(i + 1)) {
        i--;
      }

      // Wenn i < 0, sind alle Permutationen erzeugt
      if (i < 0) {
        break;
      }

      // Finde das kleinste Index j, für das list[j] > list[i]
      int j = n - 1;
      while (list.get(j) <= list.get(i)) {
        j--;
      }

      // Vertausche list[i] und list[j]
      Collections.swap(list, i, j);

      // Kehre die Reihenfolge von i+1 bis zum Ende der Liste um
      Collections.reverse(list.subList(i + 1, n));
    }
  }

  public static void generateNegations(List<Integer> input, Set<List<Integer>> result, int index) {
    if (index == input.size()) {
      while (input.size() < 8) {
        input.add(0);
      }
      result.add(new ArrayList<>(input));
      return;
    }
    if (input.get(index) != 0) {
      input.set(index, -input.get(index));
      generateNegations(input, result, index + 1);
      input.set(index, -input.get(index));
    }
    generateNegations(input, result, index + 1);
  }

  public static void main(String[] args) {

    int target = 10;
    int digits = 8;

    List<Integer> current = new ArrayList<>();
    HashSet<List<Integer>> combinations = new HashSet<>();
    TreeSet<List<Integer>> res = new TreeSet<>(new Comparator<List<Integer>>() {
      @Override
      public int compare(List<Integer> list1, List<Integer> list2) {
        // Compare lists element by element
        for (int i = 0; i < list1.size(); i++) {
          int cmp = Integer.compare(list1.get(i), list2.get(i));
          if (cmp != 0) {
            return cmp;
          }
        }
        return 0; // Lists are equal
      }
    });

    long startTime = System.nanoTime();

    AlgoA1_A.findSquareCombinations(target * target, current, 0, 1, combinations, digits);

    HashSet<List<Integer>> negatedCombinations = new HashSet<>();

    for (List<Integer> combination : combinations) {
      generateNegations(combination, negatedCombinations, 0);
    }

    for (List<Integer> list : negatedCombinations) {
      generatePermutations(list, res);
    }

    long endTime = System.nanoTime();
    long duration = (endTime - startTime) / 1000;

    for (List<Integer> list : res) {
      printList(list);
    }

    System.out.println("Total Permutations " + res.size());
    System.out.println("Took: " + duration + " mcs");
  }
}
