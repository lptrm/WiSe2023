public class Dameproblem {
  public static void main(String[] args) {
    int n = Integer.parseInt(args[0]);
    if (n <= 0) {
      System.out.println("Der Parameter sollte positiv sein!");
      return;
    }
    Schachbrett s = new Schachbrett(n);
    System.out.println("Die Loesungen des " + n + "-Dameproblems sind:");
    long cnt = 0;
    // Zaehler fuer Loesungen
    int i = 0;
    // Index der aktuellen Spalte
    while (i >= 0) {
      if (i == n) {
        cnt++;
        // Loesung! Zaehle Anzahl mit
        System.out.println(s);
        // gib Loesung aus
        i--;
        // zum Finden weiterer Loesungen
      }
      if (s.rueckeDame(i)) i++;
      // Naechste Position in Spalte i
      else i--;
      // Backtrack !!
    }
    System.out.println("Es gab " + cnt + " Loesungen des " + n + "-Dameproblems.");
  }

  static class Schachbrett {
    private final int[] a;
    // das Array fuer die Damen
    private final boolean[] u;
    private final boolean[] d;
    private final boolean[] z;

    // Arrays fuer die Diagonalen und Zeilen
    Schachbrett(int n) {
      // Konstruktor
      a = new int[n];
      u = new boolean[2 * n - 1];
      // aufsteigende Diagonale (up)
      d = new boolean[2 * n - 1];
      // absteigende Diagonale (down)
      z = new boolean[n];
      // Zeilen
      for (int i = 0; i < a.length; i++) {
        a[i] = -1;
        z[i] = true;
        // Zeilen & Spalten sind frei
      }
      for (int i = 0; i < u.length; i++) u[i] = d[i] = true;
      // Diagonalen sind frei
    }

    public String toString() {
      // erzeugt druckbare Darstellung
      String s = "";
      for (int i = 0; i < a.length; i++) s += a[i] + " ";
      return s;
    }

    boolean rueckeDame(int i) {
      // versuche Dame von Spalte i
      int j = a[i];
      // in naechste Zeile zu setzen
      if (j >= 0) aendereBelegung(i, j); // befreie Feld (i,j)
      while (++j < a.length) {
        if (unbedroht(i, j)) {
          aendereBelegung(i, j);
          // besetze Feld
          return true;
          // Versuch erfolgreich
        }
      }
      //
      return false;
      // oder nicht erfolgreich
    }

    private void aendereBelegung(int i, int j) {
      // belege oder befreie
      u[i + j] = d[i - j + a.length - 1] = z[j] = a[i] == j; // Feld (i,j)
      a[i] = z[j] ? -1 : j;
    }

    boolean unbedroht(int i, int j) { // testet, ob Feld (i,j) unbedroht ist
      return u[i + j] && d[i - j + a.length - 1] && z[j];
    }
  }
}
