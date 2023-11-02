package devtools.tests;

public class SubClass extends SuperClass{
  int test3;
  SubClass(int test1, int test2, int test3){
    super(test1, test2);
    this.test3 = test3;
  }
  @Override
  public String toString() {
    return "SubClass{" +
            "test1=" + test1 +
            ", test2=" + test2 +
            ", test3=" + test3 +
            '}';
  }
}
