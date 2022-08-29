import java.sql.Timestamp;

public class Ticket {
    private int num_ticket;
    private int id_client;
    private int id_number_flights;
    private Timestamp time_take;

    public int getNum_ticket() {
        return num_ticket;
    }

    public void setNum_ticket(int num_ticket) {
        this.num_ticket = num_ticket;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public int getId_number_flights() {
        return id_number_flights;
    }

    public void setId_number_flights(int id_number_flights) {
        this.id_number_flights = id_number_flights;
    }

    public Timestamp getTime_take() {
        return time_take;
    }

    public void setTime_take(Timestamp time_take) {
        this.time_take = time_take;
    }
}
