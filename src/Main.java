import java.sql.Time;
import java.sql.Timestamp;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Главное Меню");
            System.out.println("Для работы с Рейсами - 1");
            System.out.println("Работа со Странами - 2");
            System.out.println("Работа с Аэропортами -3");
            System.out.println("Работа с Клиентами -4");
            System.out.println("Работа с Билетами -5");
            System.out.println("Выход - 0");
            int answer = sc.nextInt();
            if (answer == 1) {
                FlightMenu.start();
            }else if   (answer ==2){
                CountryMenu.start();
            }else if (answer == 3){
                AirportMenu.start();
            } else if (answer == 4) {
                ClientMenu.start();
            }else if (answer == 5){
                TicketMenu.start();
            }else if (answer ==0){
                System.exit(1);
            }else {
                System.out.println("Ошибка попробуйте еще");
            }
        }
    }
}
