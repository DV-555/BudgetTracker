public class Income {

  private static double salary;
  private static double otherIncome;

  public Income(double salary, double otherIncome) {
    this.salary = salary;
    this.otherIncome = otherIncome;
  }

  public static double getSalary() {
    return salary;
  }

  public static double getOtherIncome() {
    return otherIncome;
  }

  @Override
  public String toString() {
    return "Income{" + "salary=" + salary + ", otherIncome=" + otherIncome + '}';
  }

  public static double fullIncome() {
    double sum = getSalary() + getOtherIncome();
    return sum;
  }


}
