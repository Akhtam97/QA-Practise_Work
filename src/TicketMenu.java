import java.util.Scanner;

public class TicketMenu {
    public static void start() {
        Scanner sc = new Scanner(System.in);
        Connector5 connector5 = new Connector5();
        Ticket ticket = new Ticket();
        Client client = new Client();
        while (true) {
            int answer;
            System.out.println("Меню Билетов: ");
            System.out.println("Добавление Билета - 1");
            System.out.println("Удаление Билета - 2");
            System.out.println("Изменения Билета - 3");
            System.out.println("Поиск Билета - 4");
            System.out.println("Вывод Полного списка Билетов -5");
            System.out.println("Вывод Фильтр по Номеру рейса - 6");
            System.out.println("Вывод Фильтр по Номеру Паспорта - 7");
            System.out.println("Вывод по 6 - 7 вместе");
            System.out.println("Выход 0");
            answer = sc.nextInt();
            if (answer == 1) {
                connector5.addTicket(ticket);
                System.out.println("Добавлено");
            } else if (answer == 2) {
                connector5.deleteTicket(ticket);
                System.out.println("Удалено");
            } else if (answer == 3) {
                connector5.updateTicket(ticket);
                System.out.println("Изменено");
            }else if (answer == 4){
                connector5.searchTicket(client);
            } else if (answer == 5) {
                connector5.allTicket();
                System.out.println("Вот весь список");
            }else if (answer == 6){
                connector5.filterNumFlightTicket();
            } else if (answer ==7){
                connector5.filterNumPassTicket();
            }else if (answer == 8){
                connector5.filterFullTicket();
            } else if (answer==0){
                return;
            } else {
                System.out.println("Ошибка");
            }
        }
    }
}
