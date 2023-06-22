import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

public class BudgetTrackerApp {

  private static final String FILE_PATH = "res/budget.txt";
  private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    boolean runner = true;
    while (runner) {
      Date current = new Date();
      System.out.println("\n" + "... Сегодня " + current + " ...");
      System.out.println("---------------------------------------------");
      System.out.println("               Budget Tracker                ");
      System.out.println("---------------------------------------------");

      System.out.println("1. Добавить расходы");
      System.out.println("2. Удалить расходы");
      System.out.println("3. Вывести статистику на экран");
      System.out.println("4. Выход");
      System.out.println("Выберите действие:" + "\n");
      int choice = scanner.nextInt();
      scanner.nextLine(); // Считываем перевод строки после ввода числа

      switch (choice) {
        case 1:
          //addExpense(scanner);
          break;
        case 2:
          // deleteExpense();
          break;
        case 3:
          // printStatistics();
          break;
        case 4:
          System.out.println("Выход из программы ");
          runner = false;
          break;
        default:
          System.out.println("Не верный ввод. Попробуйте снова.");
      }
    }
  }
  // Объект Scanner, для считывания ввода пользователя с консоли. Затем в бесконечном цикле
  // выводится меню с возможными действиями: добавить расход, удалить расход,
  // вывести статистику или выйти из программы. В зависимости от выбора пользователя
  // вызывается соответствующий метод.

}
