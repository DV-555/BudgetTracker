public class FixedExpenses {

  private final double appartmentRent;
  private final double electricityCosts;
  private final double heatingCosts;
  private final double internetCosts;

  public FixedExpenses(double appartmentRent, double electricityCosts, double heatingCosts,
      double internetCosts) {
    this.appartmentRent = appartmentRent;
    this.electricityCosts = electricityCosts;
    this.heatingCosts = heatingCosts;
    this.internetCosts = internetCosts;
  }

  public double getAppartmentRent() {
    return appartmentRent;
  }

  public double getElectricityCosts() {
    return electricityCosts;
  }

  public double getHeatingCosts() {
    return heatingCosts;
  }

  public double getInternetCosts() {
    return internetCosts;
  }

  @Override
  public String toString() {
    return "FixedExpenses{" +
        "appartmentRent=" + appartmentRent +
        ", electricityCosts=" + electricityCosts +
        ", heatingCosts=" + heatingCosts +
        ", internetCosts=" + internetCosts +
        '}';
  }
}
