import com.sun.source.tree.IfTree;

import java.util.Scanner;

public class CountryMenu {
    public static void start() {
        Scanner sc = new Scanner(System.in);
        Connector2 connector2 = new Connector2();
        Country country = new Country();
        while (true) {
            int answer;
            System.out.println("Меню Стран: ");
            System.out.println("Добавление Стран - 1");
            System.out.println("Удаление Стран - 2");
            System.out.println("Изменения Стран - 3");
            System.out.println("Поиск Страны - 4");
            System.out.println("Вывод Полного списка Стран -5");
            System.out.println("Поиск на существование  Стран - 6");
            System.out.println("Выход 0");
            answer = sc.nextInt();
            if (answer == 1) {
                connector2.addCountry(country);
                System.out.println("Добавлено");
            } else if (answer == 2) {
                connector2.deleteCountry(country);
                System.out.println("Удалено");
            } else if (answer == 3) {
                connector2.updateCountry(country);
                System.out.println("Изменено");
            } else if (answer == 4) {
                connector2.infoCountry(country);
            } else if (answer == 5) {
                connector2.allCountry(country);
                System.out.println("Вот весь список");
            } else if (answer == 6) {
                connector2.searchCountry(country);
            }else if (answer==0){
                return;
            }else {
                System.out.println("Ошибка");
            }
        }
    }
}

