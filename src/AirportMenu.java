import java.util.Scanner;

public class AirportMenu {
    public static void start() {
        Scanner sc = new Scanner(System.in);
        Connector3 connector3 = new Connector3();
        Airport airport = new Airport();
        while (true) {
            int answer;
            System.out.println("Меню Аэропортов: ");
            System.out.println("Добавление Аэропорта - 1");
            System.out.println("Удаление Аэропорта - 2");
            System.out.println("Информация об Аэропортах - 3");
            System.out.println("Поиск Аэропорта - 4");
            System.out.println("Выход 0");
            answer = sc.nextInt();
            if (answer == 1) {
                connector3.addAirport(airport);
                System.out.println("Добавлено");
            } else if (answer == 2) {
                connector3.deleteAirport(airport);
                System.out.println("Удалено");
            } else if (answer == 3) {
                connector3.allAirport(airport);
                System.out.println("Вот весь список");
            } else if (answer == 4) {
                connector3.searchAirport(airport);

            } else if (answer == 0) {
                return;
            }else {
                System.out.println("Ошибка");
            }
        }
    }
}
