import java.util.Scanner;

public class FlightMenu {
    public static void start() {
        Scanner sc = new Scanner(System.in);
        Connector connector = new Connector();
        Flights flights = new Flights();
        while (true){
            int answer;
            System.out.println("Меню Рейсов: ");
            System.out.println("Добавление Рейсов - 1");
            System.out.println("Удаление Рейсов - 2");
            System.out.println("Изменения Рейсов - 3");
            System.out.println("Поиск Рейса - 4");
            System.out.println("Вывод Полного списка Рейсов -5");
            System.out.println("Выход 0");
            answer = sc.nextInt();
            if (answer == 1){
                connector.addFlights(flights);
            }
        }
    }
}