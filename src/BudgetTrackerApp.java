import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class BudgetTrackerApp {

  private static final String FILE_PATH = "res/budget.txt";
  private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    boolean runner = true;
    while (runner) {
      Date current = new Date();
      System.out.println("\n" + "\n" + "... Сегодня " + current + " ...");
      System.out.println("---------------------------------------------");
      System.out.println("               Budget Tracker                ");
      System.out.println("---------------------------------------------"+"\n");

      System.out.println("1. Добавить расходы");
      System.out.println("2. Удалить расходы");
      System.out.println("3. Вывести статистику на экран");
      System.out.println("4. Выход"+"\n");
      System.out.println("Выберите действие:" + "\n");
      int choice = scanner.nextInt();
      scanner.nextLine(); // Считываем перевод строки после ввода числа

      switch (choice) {
        case 1:
          addExpense(scanner);
          break;
        case 2:
          deleteExpense(scanner);
          break;
        case 3:
          printStatistics(scanner);
          break;
        case 4:
          System.out.println("Выход из программы ");
          runner = false;
          break;
        default:
          System.out.println("Не верный ввод. Попробуйте ещё раз.");
      }
    }
  }
  // Объект Scanner, для считывания ввода пользователя с консоли. Затем в бесконечном цикле
  // выводится меню с возможными действиями: добавить расход, удалить расход,
  // вывести статистику или выйти из программы. В зависимости от выбора пользователя
  // вызывается соответствующий метод.

  private static void addExpense(Scanner scanner) {
    System.out.println("Введите дату расходов в формате dd.mm.yyyy :");
    String dateInput = scanner.nextLine();
    LocalDate expenseDate = LocalDate.parse(dateInput, DATE_FORMATTER);

    System.out.println("Введите категорию расхода: "
        + "Food, Car, Housekeeping, Health, Clothes, Fun :");
    String category = scanner.nextLine();

    System.out.println("Введите сумму расхода:");
    double amount = scanner.nextDouble();
    scanner.nextLine(); // Считываем перевод строки после ввода числа

    String expense = expenseDate.format(DATE_FORMATTER) + "," + category + "," + amount;

    try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
      writer.write(expense);
      writer.newLine();
      System.out.println("Расход успешно добавлен.");
    } catch (IOException e) {
      System.out.println("Ошибка при записи в файл: " + e.getMessage());
    }
  }
//Метод addExpense предназначен для добавления нового расхода.
// Он запрашивает у пользователя дату расхода, категорию и сумму.
// Затем формируется строка expense, содержащая дату, категорию и сумму,
// разделенные запятыми. Далее эта строка записывается в файл budget.txt
// с помощью объекта BufferedWriter.

  private static void deleteExpense(Scanner scanner) {
    System.out.println("Введите дату расходов, которую хотите удалить, в формате dd.mm.yyyy :");
    String dateInput = scanner.nextLine();
    LocalDate expenseDate = LocalDate.parse(dateInput, DATE_FORMATTER);

    System.out.println("Введите категорию расходов : "
        + "Food, Car, Housekeeping, Health, Clothes, Fun "
        + "из которой хотите удалить:");
    String category = scanner.nextLine();

    try {
      List<String> expenses = Files.readAllLines(Path.of(FILE_PATH));
      List<String> updatedExpenses = new ArrayList<>();

      for (String expense : expenses) {
        String[] parts = expense.split(",");
        LocalDate currentExpenseDate = LocalDate.parse(parts[0], DATE_FORMATTER);
        String currentCategory = parts[1];

        if (!currentExpenseDate.equals(expenseDate) || !currentCategory.equals(category)) {
          updatedExpenses.add(expense);
        }
      }
      Files.write(Path.of(FILE_PATH), updatedExpenses);
      System.out.println("Расход успешно удален.");
    } catch (IOException e) {
      System.out.println("Ошибка при чтении/записи файла: " + e.getMessage());
    }
  }
//Метод deleteExpense предназначен для удаления расходов. Он запрашивает у пользователя
// дату расходов и категорию для удаления. Затем он читает все расходы из файла
// budget.txt и фильтрует их, исключая расходы с указанной датой и категорией.
// Обновленный список расходов записывается обратно в файл.

private static void printStatistics(Scanner scanner) {
  System.out.println("Выберите период:");
  System.out.println("1. За сегодняшний день");
  System.out.println("2. За прошедшую неделю");
  System.out.println("3. За прошедший месяц");
  System.out.println("4. За все время");

  int choice = scanner.nextInt();
  scanner.nextLine(); // Перевод строки после ввода числа

  LocalDate startDate;
  LocalDate endDate = LocalDate.now();

  switch (choice) {
    case 1:
      startDate = endDate;
      break;
    case 2:
      startDate = endDate.minusWeeks(1);
      break;
    case 3:
      startDate = endDate.minusMonths(1);
      break;
    case 4:
      startDate = null; // Если startDate равно null, то учитываются все записи
      break;
    default:
      System.out.println("Неверный выбор. Вывод статистики отменен.");
      return;
  }

  try {
    List<String> expenses = Files.readAllLines(Path.of(FILE_PATH));

    Map<String, Double> categoryExpenses = new HashMap<>();
    Map<String, List<String>> categoryDates = new HashMap<>();

    for (String expense : expenses) {
      String[] parts = expense.split(",");
      LocalDate expenseDate = LocalDate.parse(parts[0], DATE_FORMATTER);
      String category = parts[1];
      double amount = Double.parseDouble(parts[2]);

      if ((startDate == null || expenseDate.isEqual(startDate) || expenseDate.isAfter(startDate))
          && (expenseDate.isEqual(endDate) || expenseDate.isBefore(endDate))) {
        categoryExpenses.put(category, categoryExpenses.getOrDefault(category, 0.0) + amount);

        String formattedDate = expenseDate.format(DATE_FORMATTER);
        String expenseDetails = "Дата: " + formattedDate + ", Сумма: " + amount;
        List<String> categoryExpensesList = categoryDates.getOrDefault(category, new ArrayList<>());
        categoryExpensesList.add(expenseDetails);
        categoryDates.put(category, categoryExpensesList);
      }
    }
    System.out.println("\n" + "Статистика расходов по категориям:");
    for (Map.Entry<String, Double> entry : categoryExpenses.entrySet()) {
      String category = entry.getKey();
      double totalAmount = entry.getValue();
      System.out.println("- - - - - - - - - - - - - - - - - - - - - - -");
      System.out.println("Категория: " + category + ", Сумма: " + totalAmount);
      System.out.println("- - - - - - - - - - - - - - - - - - - - - - -");

      List<String> expensesList = categoryDates.get(category);
      if (expensesList != null) {
        System.out.println("Расходы по категории " + category + ":");
        for (String expenseDetails : expensesList) {
          System.out.println(expenseDetails);
        }
      }
    }

    double totalAmount = categoryExpenses.values().stream()
        .mapToDouble(Double::doubleValue)
        .sum();
    System.out.println("- - - - - - - - - - - - - - - - - - - - - - -");
    System.out.println("Общая сумма расходов: " + totalAmount);
  } catch (IOException e) {
    System.out.println("Ошибка при чтении файла: " + e.getMessage());
  }
}

//Метод printStatistics выводит статистику расходов по категориям.
// Сначала он запрашивает у пользователя период, за который нужно вывести статистику.
// Затем он читает все расходы из файла budget.txt и фильтрует их, используя выбранный период.
// Далее происходит подсчет сумм расходов по категориям и формирование списка расходов
// по категориям с указанием даты и суммы. Наконец, выводится статистика по категориям,
// расходы по каждой категории и общая сумма расходов.
}
