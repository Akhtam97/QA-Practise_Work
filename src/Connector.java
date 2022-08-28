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
        System.out.print("Введите модель самолета (Пример: ИЛ-2, BF-109, boeing-747)\n");
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

    public void deleteFlights(Flights flights) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите id рейса который хотите удалить");
        flights.setId(sc.nextInt());
        String SQL = "DELETE FROM flights WHERE id=?;";
        try (Connection conn = connection();
             PreparedStatement stmt = conn.prepareStatement(SQL)) {
            stmt.setInt(1, flights.getId());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateFlights(Flights flights) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Начнем изменение внесите новые Данные");
        System.out.print("Введите модель самолета (Пример: ИЛ-2, BF-109, boeing-747) \n");
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
        System.out.println("Введите ID рейса который хотите изменить");
        flights.setId(sc.nextInt());

        String SQL = "UPDATE Flights set   model_airplane = ?, id_from_airport = ? , id_in_airport = ?, time_fly = ? ," +
                "places = ?, number = ?, departure = ? where id = ?;";
        try (Connection conn = connection();
             PreparedStatement stmt = conn.prepareStatement(SQL)) {
            stmt.setString(1, flights.getModel_airplane());
            stmt.setInt(2, flights.getId_from_airport());
            stmt.setInt(3, flights.getId_in_airport());
            stmt.setString(4, flights.getTime_fly());
            stmt.setInt(5, flights.getPlaces());
            stmt.setString(6, flights.getNumber());
            stmt.setTimestamp(7, flights.getDeparture());
            stmt.setInt(8, flights.getId());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void searchFlights(Flights flights) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите id рейса для поиска");
        flights.setId(sc.nextInt());
        try {
            String sql = "SELECT model_airplane,\n" +
                    "       a.name_airport,\n" +
                    "       a2.name_airport,\n" +
                    "       places,\n" +
                    "       number,\n" +
                    "       departure,\n" +
                    "       time_fly\n" +
                    "from flights\n" +
                    "         inner join airport a on flights.id_from_airport  =  a.id\n" +
                    "         inner join airport a2 on  flights.id_in_airport = a2.id\n" +
                    "where flights.id = ?";
            PreparedStatement statement = connection().prepareStatement(sql);
            statement.setInt(1, flights.getId());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                System.out.println("Модель Самолета : " + rs.getString("model_airplane") +
                        " \n" + "Аэропорт Вылета : " + rs.getString("name_airport") +
                        " \n" + "Аэропорт Прилета : " + rs.getString("name_airport") +
                        " \n" + "Посадочных мест : " + rs.getInt("places") +
                        " \n" + "Номер рейса : " + rs.getString("number") +
                        " \n" + "Время Вылета : " + rs.getTimestamp("departure") +
                        " \n" + "Время полета : " + rs.getString("time_fly"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void allFlights(Flights flights) {
        try {
            String sql = "SELECT flights.id, model_airplane,\n" +
                    "       a.name_airport,\n" +
                    "       a2.name_airport,\n" +
                    "       places,\n" +
                    "       number,\n" +
                    "       departure,\n" +
                    "       time_fly\n" +
                    "from flights\n" +
                    "         inner join airport a on flights.id_from_airport  =  a.id\n" +
                    "         inner join airport a2 on  flights.id_in_airport = a2.id\n;";
            PreparedStatement statement = connection().prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                System.out.println("ID : " + rs.getInt("id") +
                        " \n" + "Модель Самолета : " + rs.getString("model_airplane") +
                        " \n" + "Аэропорт Вылета : " + rs.getString("name_airport") +
                        " \n" + "Аэропорт Прилета : " + rs.getString("name_airport") +
                        " \n" + "Посадочных мест : " + rs.getInt("places") +
                        " \n" + "Номер рейса : " + rs.getString("number") +
                        " \n" + "Время Вылета : " + rs.getTimestamp("departure") +
                        " \n" + "Время полета : " + rs.getString("time_fly") +
                        " \n");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage() + " \nОшибка");
        }
    }
}
