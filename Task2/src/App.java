import java.util.Scanner;

public class App {
    private static final Scanner scanner = new Scanner(System.in);
    
    private static final Dictionary latin = new AppDictionary(
        "Латинский", new LatinValidator(), new FileRepository("latin.txt")
    );
    private static final Dictionary digit = new AppDictionary(
        "Цифровой", new DigitValidator(), new FileRepository("digit.txt")
    );

    private static Dictionary current = null;

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n1. Все записи | 2. Латинский | 3. Цифровой | 0. Выход");
            String choice = scanner.nextLine();
            if (choice.equals("0")) break;
            if (choice.equals("1")) { showAll(); continue; }
            
            current = choice.equals("2") ? latin : digit;
            work();
        }
    }

    private static void showAll() {
        latin.getAll().forEach((k, v) -> System.out.println("[LAT] " + k + ": " + v));
        digit.getAll().forEach((k, v) -> System.out.println("[DIG] " + k + ": " + v));
    }

    private static void work() {
        while (true) {
            System.out.println("\nСловарь: " + current.getName());
            System.out.println("1. Найти | 2. Добавить | 3. Удалить | 0. Назад");
            String act = scanner.nextLine();
            if (act.equals("0")) break;

            if (act.equals("1")) {
                System.out.print("Ключ: ");
                String r = current.find(scanner.nextLine());
                System.out.println(r != null ? "Найдено: " + r : "Нет данных");
            } else if (act.equals("2")) {
                System.out.print("Ключ: "); String k = scanner.nextLine();
                System.out.print("Значение: "); String v = scanner.nextLine();
                current.add(k, v);
            } else if (act.equals("3")) {
                System.out.print("Ключ для удаления: ");
                current.delete(scanner.nextLine());
                System.out.println("Удалено.");
            }
        }
    }
}