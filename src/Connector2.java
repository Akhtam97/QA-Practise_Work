import java.sql.*;
import java.util.Scanner;

public class Connector2 {
    private final String url = "jdbc:postgresql://localhost:5432/qa_work_2";
    private final String user = "postgres";
    private final String password = "16091997";

    public Connection connection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void addCountry(Country country) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите код страны (Примеры: KGZ, RUS, UZB)\n");
        country.setCode_country(sc.next());
        System.out.print("Введите полное название страны \n");
        country.setName_country(sc.next());
        String SQL = "INSERT INTO country (code_country , name_country)" +
                "VALUES (?,?)";
        try (Connection conn = connection();
             PreparedStatement stmt = conn.prepareStatement(SQL)) {
            stmt.setString(1, country.getCode_country());
            stmt.setString(2, country.getName_country());
            stmt.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteCountry(Country country) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Вы хотите удалить страну по ID - 1 или по Коду - 2");
        int answer = sc.nextInt();
        if (answer == 1) {
            System.out.println("Введите id страны которго хотите удалить");
            country.setId(sc.nextInt());
            String SQL = "DELETE FROM country WHERE id=?;";
            try (Connection conn = connection();
                 PreparedStatement stmt = conn.prepareStatement(SQL)) {
                stmt.setInt(1, country.getId());
                stmt.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (answer == 2) {
            System.out.println("Введите Код страны которого хотите удалить");
            country.setCode_country(sc.next());
            String SQL = "DELETE FROM country WHERE code_country = ? ;";
            try (Connection conn = connection();
                 PreparedStatement stmt = conn.prepareStatement(SQL)) {
                stmt.setString(1, country.getCode_country());
                stmt.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateCountry(Country country) {
        try {
            String sql = "SELECT * from country;";
            PreparedStatement statement = connection().prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                System.out.println("ID : " + rs.getInt("id") +
                        " \n" + "Код Страны : " + rs.getString("code_country") +
                        " \n" + "Страна : " + rs.getString("name_country") +
                        " \n");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage() + " \nОшибка");
        }

        Scanner sc = new Scanner(System.in);
        System.out.println("Начнем изменение внесите новые Данные");
        System.out.print("Введите Код Страны \n");
        country.setCode_country(sc.next());
        System.out.print("Введите Название Страны \n");
        country.setName_country(sc.next());
        System.out.println("Введите ID Cтраны которого хотите изменить");
        country.setId(sc.nextInt());

        String SQL = "UPDATE country set   code_country = ?, name_country = ? where id = ?;";
        try (Connection conn = connection();
             PreparedStatement stmt = conn.prepareStatement(SQL)) {
            stmt.setString(1, country.getCode_country());
            stmt.setString(2, country.getName_country());
            stmt.setInt(3, country.getId());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void infoCountry(Country country) {
        Scanner sc = new Scanner(System.in);
        int a;
        System.out.println("Поиск по id - 1 , поиск по коду - 2");
        a = sc.nextInt();
        if (a == 1) {
            System.out.println("Введите id страны");
            country.setId(sc.nextInt());
            String sql = "SELECT * from country where id = ? ;";
            try {
                PreparedStatement statement = connection().prepareStatement(sql);
                statement.setInt(1, country.getId());
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    System.out.println("ID : " + rs.getInt("id") +
                            " \n" + "Код Страны : " + rs.getString("code_country") +
                            " \n" + "Страна : " + rs.getString("name_country") +
                            " \n");
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage() + " \nОшибка");
            }
        } else if (a == 2) {
            System.out.println("Введите код страны");
            country.setCode_country(sc.next());
            String sql = "SELECT * from country where code_country = ? ;";
            try {
                PreparedStatement statement = connection().prepareStatement(sql);
                statement.setString(1, country.getCode_country());
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    System.out.println("ID : " + rs.getInt("id") +
                            " \n" + "Код Страны : " + rs.getString("code_country") +
                            " \n" + "Страна : " + rs.getString("name_country") +
                            " \n");
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage() + " \nОшибка");
            }
        }
    }

    public void allCountry(Country country) {
        try {
            String sql = "SELECT * from country;";
            PreparedStatement statement = connection().prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                System.out.println("ID : " + rs.getInt("id") +
                        " \n" + "Код Страны : " + rs.getString("code_country") +
                        " \n" + "Страна : " + rs.getString("name_country") +
                        " \n");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage() + " \nОшибка");
        }
    }

    public void searchCountry(Country country) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите id страны");
        country.setId(sc.nextInt());
        String sql = "SELECT * from country where id = ? ;";
        try {
            PreparedStatement statement = connection().prepareStatement(sql);
            statement.setInt(1, country.getId());
            ResultSet rs = statement.executeQuery();
            if (rs.next()){
                System.out.println("ID : " + rs.getInt("id") +
                        " \n" + "Код Страны : " + rs.getString("code_country") +
                        " \n" + "Страна : " + rs.getString("name_country") +
                        " \n");
            }else {
                System.out.println("Нет такой страны в списке");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage() + " \nОшибка");
        }
    }
}