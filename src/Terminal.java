import java.util.List;
import java.util.ArrayList;

public class Terminal extends Subject {
    private List<Flight> flights = new ArrayList<>();
    private String name;

    public Terminal(String name) {
        this.name = name;
    }

    @Override
    public void addFlight(Flight flight) {
        flights.add(flight);
        notifyObservers();
    }

    @Override
    public void removeFlight(Flight flight) {
        flights.remove(flight);
        notifyObservers();
    }

    @Override
    public List<Flight> getFlights() {
        return flights;
    }

    public String getName() {
        return name;
    }
}