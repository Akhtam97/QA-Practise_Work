import java.sql.*;
import java.util.Scanner;

public class Connector {
    private static final String url = "jdbc:postgresql://localhost:5432/qa_work_2";
    private static final String user = "postgres";
    private static final String password = "16091997";
    private static final String driver = "org.postgresql.Driver";

    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password); //DriverManager.getConnection(url, user, password)
            System.out.println("Connected to the PostgresSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void addFlights(Flights flights) {
        Scanner sc = new Scanner(System.in);
        String SQL = "INSERT INTO flights (model_airplane, departure, id_from_airport , id_in_airport, time_fly ,places,number)" +
                "VALUES (?,?,?,?,?,?,?)";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(SQL)) {
            stmt.setString(1, flights.getModel_airplane());
            stmt.setString(2, flights.getDeparture());
            stmt.setInt(3, flights.getId_from_airport());
            stmt.setInt(4, flights.getId_in_airport());
            stmt.setString(5, flights.getTime_fly());
            stmt.setInt(6, flights.getPlaces());
            stmt.setString(7, flights.getNumber());
            stmt.execute();
        } catch (SQLException e) {
            System.out.println("Создал");
        }
        System.out.println("Введите модель самолета");
        flights.setModel_airplane(sc.next());
        System.out.println("Введите время вылета (Пример: 2022-08-29 00:00");
        flights.setDeparture(sc.nextLine());
        System.out.println("Введите id Аэропорта откуда планируется вылет");
        flights.setId_from_airport(sc.nextInt());
        System.out.println("Введите id Аэропорта куда планируется вылет");
        flights.setId_in_airport(sc.nextInt());
        System.out.println("Введите время полета (Пример: 01:30)");
        flights.setTime_fly(sc.next());
        System.out.println("Введите номер рейса (Пример: JET3256)");
        flights.setTime_fly(sc.next());
    }
}
