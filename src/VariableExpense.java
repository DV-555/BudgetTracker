import java.time.LocalDateTime;
import java.util.Scanner;

public class VariableExpense {
  private final double meal;
  private final double car;
  private final double pet;
  private final double freeTimeRelax;

  public VariableExpense(double meal, double car, double pet, double freeTimeRelax) {
    this.meal = meal;
    this.car = car;
    this.pet = pet;
    this.freeTimeRelax = freeTimeRelax;
  }

  public double getMeal() {
    return meal;
  }

  public double getCar() {
    return car;
  }

  public double getPet() {
    return pet;
  }

  public double getFreeTimeRelax() {
    return freeTimeRelax;
  }

  @Override
  public String toString() {
    return "VariableExpense{" +
        "meal=" + meal +
        ", car=" + car +
        ", pet=" + pet +
        ", freeTimeRelax=" + freeTimeRelax +
        '}';
  }
}
