import java.sql.*;
import java.util.Scanner;

public class Connector3 {
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

    public void addAirport(Airport airport) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите название Аэропорта\n");
        airport.setName_airport(sc.next());
        System.out.print("Введите код аэропорта (Например: TAS) \n");
        airport.setCode_airport(sc.next());
        System.out.println("Введите город нахождения Аэропорта");
        airport.setCity(sc.next());
        System.out.println("Введите ID страны");
        airport.setId_country(sc.nextInt());
        String SQL = "INSERT INTO country (name_airport,code_airport,city, id_country)" +
                "VALUES (?,?,?,?)";
        try (Connection conn = connection();
             PreparedStatement stmt = conn.prepareStatement(SQL)) {
            stmt.setString(1, airport.getName_airport());
            stmt.setString(2, airport.getCode_airport());
            stmt.setString(3,airport.getCity());
            stmt.setInt(4,airport.getId_country());
            stmt.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteAirport(Airport airport){
        Scanner sc = new Scanner(System.in);
        System.out.println("Вы хотите удалить аэропорт по ID - 1 или по ID страны - 2");
        int answer = sc.nextInt();
        if (answer == 1) {
            System.out.println("Введите id аэропорта которго хотите удалить");
            airport.setId(sc.nextInt());
            String SQL = "DELETE FROM airport WHERE id=?;";
            try (Connection conn = connection();
                 PreparedStatement stmt = conn.prepareStatement(SQL)) {
                stmt.setInt(1, airport.getId());
                stmt.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (answer == 2) {
            System.out.println("Введите id страны для  удаления Аэропорта");
            airport.setId_country(sc.nextInt());
            String SQL = "DELETE FROM airport WHERE id_country = ? ;";
            try (Connection conn = connection();
                 PreparedStatement stmt = conn.prepareStatement(SQL)) {
                stmt.setInt(1, airport.getId_country());
                stmt.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void allAirport(Airport airport) {
        try {
            String sql = "SELECT name_airport,\n" +
                    "       code_airport,\n" +
                    "       city,\n" +
                    "       c.name_country\n" +
                    "from airport\n" +
                    "inner join country c on c.id = airport.id_country;";
            PreparedStatement statement = connection().prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                System.out.println("Название Аэропорта : " + rs.getString("name_airport") +
                        " \n" + "Код Аэропорта : " + rs.getString("code_airport") +
                        " \n" + "Город : " + rs.getString("city") +
                        " \n" + "Страна: " + rs.getString("name_country") +
                        "\n");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage() + " \nОшибка");
        }
    }

    public void searchAirport(Airport airport) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите код Аэропорта");
        airport.setCode_airport(sc.next());
        try {
            String sql = "SELECT name_airport,code_airport ,city ,c.name_country " +
                    "from airport " +
                    "inner join country c on c.id = airport.id_country " +
                    "where code_airport = ?;";
            PreparedStatement statement = connection().prepareStatement(sql);
            statement.setString(1,airport.getCode_airport());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                System.out.println("Название Аэропорта : " + rs.getString("name_airport") +
                        " \n" + "Код Аэропорта : " + rs.getString("code_airport") +
                        " \n" + "Город : " + rs.getString("city") +
                        " \n" + "Страна: " + rs.getString("name_country") +
                        "\n");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage() + " \nОшибка");
        }
    }
}
