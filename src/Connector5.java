import java.sql.*;
import java.util.Scanner;

public class Connector5 {
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

    public void addTicket(Ticket ticket) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите ID клиента\n");
        ticket.setId_client(sc.nextInt());
        System.out.print("Введите номер рейса \n");
        ticket.setId_number_flights(sc.nextInt());
        System.out.println("Дату Получения Билета (Пример: 2022-08-29 00:00:00)");
        ticket.setTime_take(Timestamp.valueOf(sc.next() + " " + sc.next()));
        String SQL = "INSERT INTO ticket (id_client,id_number_flights,time_take)" +
                "VALUES (?,?,?)";
        try (Connection conn = connection();
             PreparedStatement stmt = conn.prepareStatement(SQL)) {
            stmt.setInt(1, ticket.getId_client());
            stmt.setInt(2, ticket.getId_number_flights());
            stmt.setTimestamp(3, ticket.getTime_take());
            stmt.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteTicket(Ticket ticket) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите номер билета для удаления");
        ticket.setNum_ticket(sc.nextInt());
        String SQL = "DELETE FROM ticket WHERE num_ticket=?;";
        try (Connection conn = connection();
             PreparedStatement stmt = conn.prepareStatement(SQL)) {
            stmt.setInt(1, ticket.getNum_ticket());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void updateTicket(Ticket ticket) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите ID клиента\n");
        ticket.setId_client(sc.nextInt());
        System.out.print("Введите номер рейса \n");
        ticket.setId_number_flights(sc.nextInt());
        System.out.println("Дату Получения Билета (Пример: 2022-08-29 00:00:00)");
        ticket.setTime_take(Timestamp.valueOf(sc.next() + " " + sc.next()));
        System.out.println("Введите номер билета который хотите изменить");
        ticket.setNum_ticket(sc.nextInt());

        String SQL = "UPDATE ticket set   id_client = ?, id_number_flights = ? , time_take = ?, where num_ticket = ?;";
        try (Connection conn = connection();
             PreparedStatement stmt = conn.prepareStatement(SQL)) {
            stmt.setInt(1, ticket.getId_client());
            stmt.setInt(2, ticket.getId_number_flights());
            stmt.setTimestamp(3, ticket.getTime_take());
            stmt.setInt(4, ticket.getNum_ticket());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void searchTicket(Client client) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите номер Паспорта для поиска");
        client.setId_pass(sc.next());
        try {
            String sql = "select t.num_ticket, co.name_country, c.id_pass, c.fio  from ticket t\n" +
                    "inner join client c on c.id = t.id_client\n" +
                    "inner join country co on co.id = c.id_country\n" +
                    "where c.id_pass = ?";
            PreparedStatement statement = connection().prepareStatement(sql);
            statement.setString(1, client.getId_pass());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                System.out.println( "ФИО: " + rs.getString("fio") +
                        "\n" +"Номер Билета: " + rs.getInt("num_ticket") +
                        "\n" + "Страна : " + rs.getString("name_country") +
                        " \n" + "Номер Паспорта : " + rs.getString("id_pass") +
                        "\n");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage() + " \nОшибка");
        }
    }

    public void allTicket() {
        try {
            String sql = "select t.num_ticket, co.name_country, c.id_pass  from ticket t\n" +
                    "inner join client c on c.id = t.id_client\n" +
                    "inner join country co on co.id = c.id_country ";
            PreparedStatement statement = connection().prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                System.out.println( "ФИО: " + rs.getString("fio") +
                        "\n" +"Номер Билета: " + rs.getInt("num_ticket") +
                        "\n" + "Страна : " + rs.getString("name_country") +
                        " \n" + "Номер Паспорта : " + rs.getString("id_pass") +
                        "\n");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage() + " \nОшибка");
        }
    }

    public void filterNumFlightTicket() {
        try {
            String sql = "select t.num_ticket, co.name_country, c.id_pass, c.fio  from ticket t\n" +
                    "inner join client c on c.id = t.id_client\n" +
                    "inner join country co on co.id = c.id_country\n" +
                    "order by t.id_number_flights asc ;";
            PreparedStatement statement = connection().prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                System.out.println( "ФИО: " + rs.getString("fio") +
                        "\n" +"Номер Билета: " + rs.getInt("num_ticket") +
                        "\n" + "Страна : " + rs.getString("name_country") +
                        " \n" + "Номер Паспорта : " + rs.getString("id_pass") +
                        "\n");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage() + " \nОшибка");
        }
    }

    public void filterNumPassTicket() {
        try {
            String sql = "select t.num_ticket, co.name_country, c.id_pass, c.fio  from ticket t\n" +
                    "inner join client c on c.id = t.id_client\n" +
                    "inner join country co on co.id = c.id_country\n" +
                    "order by c.id_pass asc ;";
            PreparedStatement statement = connection().prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                System.out.println( "ФИО: " + rs.getString("fio") +
                        "\n" +"Номер Билета: " + rs.getInt("num_ticket") +
                        "\n" + "Страна : " + rs.getString("name_country") +
                        " \n" + "Номер Паспорта : " + rs.getString("id_pass") +
                        "\n");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage() + " \nОшибка");
        }
    }

    public void filterFullTicket() {
        try {
            String sql = "select t.num_ticket, co.name_country, c.id_pass, c.fio  from ticket t\n" +
                    "inner join client c on c.id = t.id_client\n" +
                    "inner join country co on co.id = c.id_country\n" +
                    "order by c.id_pass asc , t.id_number_flights asc ;";
            PreparedStatement statement = connection().prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                System.out.println( "ФИО: " + rs.getString("fio") +
                        "\n" +"Номер Билета: " + rs.getInt("num_ticket") +
                        "\n" + "Страна : " + rs.getString("name_country") +
                        " \n" + "Номер Паспорта : " + rs.getString("id_pass") +
                        "\n");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage() + " \nОшибка");
        }
    }
}
