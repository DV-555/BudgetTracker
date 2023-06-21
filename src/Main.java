import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

  final static Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) {
    System.out.println("************ BUDGET TRACKER *************");
    System.out.println();

    boolean running = true;
    while (running) {
      System.out.println("1. Проверка бюджета ");
      System.out.println("2. Внести доходы ");
      System.out.println("3. Внести затраты ");
      System.out.println("4. Выход из программы");
      System.out.println("Выберите действие: " );
      int choice = scanner.nextInt();
      try {
        switch (choice) {
          case 1:
            BudgeTracker.budgetCheck();
            break;
          case 2:
            BudgeTracker.addIncome();
            break;
          case 3:
            BudgeTracker.addExpense();
            break;
          case 4:
            System.out.print("Выход из программы");
            running = false;
            break;
          default:
            System.out.println("Неверный ввод. Попробуйте ещё раз:");
        }
      } catch (InputMismatchException e) {
        System.out.println("ошибка при вводе ");
      }
    }
  }
}