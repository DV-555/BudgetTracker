import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
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

    }
  }
}
