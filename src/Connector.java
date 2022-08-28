import java.sql.*;
import java.util.Scanner;

public class Connector {
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

    public void addFlights(Flights flights) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите модель самолета \n");
        flights.setModel_airplane(sc.next());
        System.out.print("Введите id Аэропорта откуда планируется вылет \n");
        flights.setId_from_airport(sc.nextInt());
        System.out.print("Введите id Аэропорта куда планируется вылет \n");
        flights.setId_in_airport(sc.nextInt());
        System.out.print("Введите время полета (Пример: 01:30) \n");
        flights.setTime_fly(sc.next());
        System.out.println("Введите количество мест");
        flights.setPlaces(sc.nextInt());
        System.out.print("Введите номер рейса (Пример: JET3256) \n");
        flights.setNumber(sc.next());
        System.out.print("Введите время вылета (Пример: 2022-08-29 00:00:00)");
        flights.setDeparture(Timestamp.valueOf(sc.next() + " " + sc.next()));
        String SQL = "INSERT INTO flights (model_airplane, id_from_airport , id_in_airport, time_fly ,places, number, departure)" +
                "VALUES (?,?,?,?,?,?,?)";
        try (Connection conn = connection();
             PreparedStatement stmt = conn.prepareStatement(SQL)) {
            stmt.setString(1, flights.getModel_airplane());
            stmt.setInt(2, flights.getId_from_airport());
            stmt.setInt(3, flights.getId_in_airport());
            stmt.setString(4, flights.getTime_fly());
            stmt.setInt(5, flights.getPlaces());
            stmt.setString(6, flights.getNumber());
            stmt.setTimestamp(7, flights.getDeparture());
            stmt.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteFlights(Flights flights){

    }
}
