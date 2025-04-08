import java.io.Serializable;

public class Flight implements Serializable {
  
    private static final long serialVersionUID = 1L;

    /**
     * Constant string for the ON TIME status
     */
    private String company;
    private int flightNumber;
    private String destination;
    private int departureTime;
    private String gate;
    private String status;

    public Flight(String company, int flightNumber, String destination, int departureTime, String gate, String status) {
        this.company = company;
        this.flightNumber = flightNumber;
        this.destination = destination;
        this.departureTime = departureTime;
        this.gate = gate;
        this.status = status;
    }

    public String getCompany() { return company; }
    public int getFlightNumber() { return flightNumber; }
    public String getDestination() { return destination; }
    public int getDepartureTime() { return departureTime; }
    public String getGate() { return gate; }
    public String getStatus() { return status; }

    public void setDestination(String destination) { this.destination = destination; }
    public void setDepartureTime(int departureTime) { this.departureTime = departureTime; }
    public void setGate(String gate) { this.gate = gate; }
    public void setStatus(String status) { this.status = status; }

    public String toString() {
        return company + flightNumber + " " + destination + " " + departureTime + " " + gate + " " + status;
    }
    public static final String ONTIME = "ON TIME";
    public static final String CANCELLED = "CANCELLED";
    public static final String BOARDING = "BOARDING";
    public static final String DELAYED = "DELAYED";
}
  