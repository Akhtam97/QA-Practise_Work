import java.util.Scanner;

public class ClientMenu {
    public static void start() {
        Scanner sc = new Scanner(System.in);
        Connector4 connector4 = new Connector4();
        Client client = new Client();
        while (true) {
            int answer;
            System.out.println("Меню Клиентов: ");
            System.out.println("Добавление Клиента - 1");
            System.out.println("Удаление Клиента - 2");
            System.out.println("Изменения Клиента - 3");
            System.out.println("Поиск Клиента - 4");
            System.out.println("Вывод Полного списка Клиентов -5");
            System.out.println("Выход 0");
            answer = sc.nextInt();
            if (answer == 1) {
                connector4.addClient(client);
                System.out.println("Добавлено");
            }else if (answer == 2){
                connector4.deleteClient(client);
                System.out.println("Удалено");
            } else if (answer == 3) {
                connector4.updateClient(client);
                System.out.println("Изменено");
            }else if (answer == 4){
                connector4.searchClient(client);
            } else if (answer == 5) {
                connector4.allClient(client);
                System.out.println("Вот весь список");
            }else if (answer==0){
                return;
            }else {
                System.out.println("Ошибка");
            }
        }
    }
}
