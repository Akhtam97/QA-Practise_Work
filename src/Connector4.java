import java.sql.*;
import java.util.Scanner;

public class Connector4 {
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

    public void addClient(Client client) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите ИНН\n");
        client.setInn(sc.nextInt());
        System.out.print("Введите Id паспорта (Пример: FA77777) \n");
        client.setId_pass(sc.next());
        System.out.println("ФИО (Пример: Халилов Ахтам Ахмаджанович)");
        client.setFio(sc.next() + " " + sc.next() + " " + sc.next());
        System.out.println("Введите ваш пол");
        client.setGender(sc.next());
        System.out.println("Введите ID страны");
        client.setId_country(sc.nextInt());
        String SQL = "INSERT INTO client (inn,id_pass,fio, gender, id_country)" +
                "VALUES (?,?,?,?,?)";
        try (Connection conn = connection();
             PreparedStatement stmt = conn.prepareStatement(SQL)) {
            stmt.setInt(1, client.getInn());
            stmt.setString(2, client.getId_pass());
            stmt.setString(3, client.getFio());
            stmt.setString(4, client.getGender());
            stmt.setInt(5, client.getId_country());
            stmt.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteClient(Client client) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите номер паспорта для удаления");
        client.setId_pass(sc.next());
        String SQL = "DELETE FROM client WHERE id_pass=?;";
        try (Connection conn = connection();
             PreparedStatement stmt = conn.prepareStatement(SQL)) {
            stmt.setString(1, client.getId_pass());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void updateClient(Client client) {
        Scanner sc = new Scanner(System.in);
        String a;
        System.out.println("Начнем изменение внесите новые Данные");
        System.out.print("Введите инн\n");
        client.setInn(sc.nextInt());
        System.out.print("Введите номер паспорта \n");
        client.setId_pass(sc.next());
        System.out.print("Введите ФИО (Пример: Халилов Ахтам Ахмаджанович) \n");
        client.setFio(sc.next() + " " + sc.next() + " " + sc.next());
        System.out.print("Введите Пол \n");
        client.setGender(sc.next());
        System.out.println("Введите ID страны");
        client.setId_country(sc.nextInt());
        System.out.println("Введите Номер паспорта которого хотите изменить");
        a = sc.next();

        String SQL = "UPDATE client set   inn = ?, id_pass = ? , fio = ?, gender = ? ," +
                "id_country = ? where id_pass = ?;";
        try (Connection conn = connection();
             PreparedStatement stmt = conn.prepareStatement(SQL)) {
            stmt.setInt(1, client.getInn());
            stmt.setString(2, client.getId_pass());
            stmt.setString(3, client.getFio());
            stmt.setString(4, client.getGender());
            stmt.setInt(5, client.getId_country());
            stmt.setString(6, a);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void searchClient(Client client) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите номер паспорта для поиска");
        client.setId_pass(sc.next());
        try {
            String sql = "select client.id,inn,id_pass,fio,gender, c.name_country\n" +
                    "from client\n" +
                    "inner join country c on c.id = client.id_country " +
                    "where id_pass = ?";
            PreparedStatement statement = connection().prepareStatement(sql);
            statement.setString(1, client.getId_pass());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                System.out.println("ID" + rs.getInt("ID") +
                        "\n" + "ИНН : " + rs.getString("INN") +
                        " \n" + "Номер Паспорта : " + rs.getString("id_pass") +
                        " \n" + "ФИО : " + rs.getString("fio") +
                        " \n" + "Пол: " + rs.getString("gender") +
                        " \n" + "Страна: " + rs.getString("name_country") +
                        "\n");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage() + " \nОшибка");
        }
    }

    public void allClient(Client client) {
        try {
            String sql = "select client.id,inn,id_pass,fio,gender, c.name_country\n" +
                    "from client\n" +
                    "inner join country c on c.id = client.id_country ";
            PreparedStatement statement = connection().prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                System.out.println("ID" + rs.getInt("ID") +
                        "\n" + "ИНН : " + rs.getString("INN") +
                        " \n" + "Номер Паспорта : " + rs.getString("id_pass") +
                        " \n" + "ФИО : " + rs.getString("fio") +
                        " \n" + "Пол: " + rs.getString("gender") +
                        " \n" + "Страна: " + rs.getString("name_country") +
                        "\n");
            }
            else {
                System.out.println("Ошибка попробуйте заного");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage() + " \nОшибка");
        }
    }
}
