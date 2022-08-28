import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;

public class Flights {
    private int id;
    private String model_airplane;
    private Timestamp departure;
    private int id_from_airport;
    private int id_in_airport;
    private String time_fly;
    private int places;
    private String number;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel_airplane() {
        return model_airplane;
    }

    public void setModel_airplane(String model_airplane) {
        this.model_airplane = model_airplane;
    }

    public Timestamp getDeparture() {
        return departure;
    }

    public void setDeparture(Timestamp departure) {
        this.departure = departure;
    }

    public int getId_from_airport() {
        return id_from_airport;
    }

    public void setId_from_airport(int id_from_airport) {
        this.id_from_airport = id_from_airport;
    }

    public int getId_in_airport() {
        return id_in_airport;
    }

    public void setId_in_airport(int id_in_airport) {
        this.id_in_airport = id_in_airport;
    }

    public String getTime_fly() {
        return time_fly;
    }

    public void setTime_fly(String time_fly) {
        this.time_fly = time_fly;
    }

    public int getPlaces() {
        return places;
    }

    public void setPlaces(int places) {
        this.places = places;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
