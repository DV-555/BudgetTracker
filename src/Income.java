public class Income {
  private final double salary;
  private final double otherIncome;
  public Income(double salary, double otherIncome) {
    this.salary = salary;
    this.otherIncome = otherIncome;
  }
  public double getSalary() {
    return salary;
  }
  public double getOtherIncome() {
    return otherIncome;
  }
  @Override
  public String toString() {
    return "Income{" + "salary=" + salary + ", otherIncome=" + otherIncome + '}';
  }
}
