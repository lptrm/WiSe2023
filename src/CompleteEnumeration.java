public class CompleteEnumeration {
  public static void main(String[] arg) {
    System.out.println("TSP");
    int[][] mat = {
      {0, 2, 2, 5, 4}, {3, 0, 5, 2, 2}, {6, 7, 0, 1, 6}, {3, 2, 7, 0, 3}, {5, 4, 3, 8, 0}
    }; // Matrix der Kosten
    int N = mat.length;
    // Zahl der Staedte
    Perm p = new Perm(N); // Liefert Permutationen von 0 .. N-1 mit 0 fix
    int cost = Integer.MAX_VALUE;
    int[] c, best = {};
    while ((c = p.getNext()) != null) { // Naechste Permutation
      int actcost = 0;
      for (int j = 0; j < N; j++) {
        actcost += mat[c[j]][c[(j + 1) % N]];
      }
      System.out.println(arrStr(c) + " --> " + actcost);
      if (actcost < cost) {
        cost = actcost;
        best = c.clone();
      }
    }
    System.out.println("Die geringsten Kosten " + cost + " verursacht " + arrStr(best));
  } // main

  private static String arrStr(int[] best) {
    StringBuilder s = new StringBuilder();
    for (int j : best) {
      s.append(j).append(" ");
    }
    return s.toString();
  }

  static class Perm extends Thread {
    // a Arbeitsarray
    private final int max;
    private int[] a;
    // maximaler Index
    private boolean mayread = false; // Kontrolle

    Perm(int n) {
      // Konstruktor
      a = new int[n];
      // Indices: 0 .. n-1
      max = n - 1;
      // Maximaler Index
      for (int i = 0; i <= max; ) a[i] = i++; // a fuellen
      this.start();
      // run-Methode beginnt zu laufen
    } // end Konstruktor

    public void run() { // die Arbeits-Methode
      perm(1);
      // a[0] bleibt fest; permutiere ab 1
      a = null;
      // Anzeige, dass fertig
      put();
      // ausliefern
    } // end run

    private void perm(int i) { // permutiere ab Index i
      if (i >= max) put(); // eine Permutation fertig
      else {
        for (int j = i; j <= max; j++) { // jedes nach Vorne
          swap(i, j);
          // vertauschen
          perm(i + 1);
          // und Rekursion
        }
        int h = a[i];
        // restauriere Array
        System.arraycopy(a, i + 1, a, i, max - i); // shift links
        a[max] = h;
      }
    } // end perm

    private void swap(int i, int j) {
      // tausche a[i] <-> a[j]
      if (i != j) {
        int h = a[i];
        a[i] = a[j];
        a[j] = h;
      }
    } // end swap

    synchronized int[] getNext() { // liefert naechste Permutation
      mayread = false;
      // a darf geaendert werden
      notify();
      // wecke anderen Thread
      try {
        while (!mayread) wait(); // non-busy waiting
      } catch (InterruptedException ignored) {
      }
      return a;
      // liefere Permutationsarray
    } // end getNext

    private synchronized void put() {
      // uebergebe array
      mayread = true;
      // a wird gelesen
      notify();
      // wecke anderen Thread
      try {
        if (a != null) while (mayread) wait();
        // non-busy waiting
      } catch (InterruptedException ignored) {
      }
    } // end put
  }
}
