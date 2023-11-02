package devtools.tests;

public class SuperClass {
  int test1;
  int test2;
  SuperClass(int test1, int test2){
    this.test1 = test1;
    this.test2 = test2;
    log();
  }
  private final void log(){
    System.out.println("SuperClass log called" + this);
  }

  @Override
  public String toString() {
    return "SuperClass{" +
            "test1=" + test1 +
            ", test2=" + test2 +
            '}';
  }
  public static void main(String[] args){
    SuperClass superClass = new SuperClass(1, 2);
    SubClass subClass = new SubClass(1, 2, 3);
    System.out.println(subClass);
  }
}
