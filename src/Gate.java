import java.util.List;
import java.util.ArrayList;

public class Gate extends Subject {
    private List<Flight> flights = new ArrayList<>();
    private String name;

    public Gate(String name) {
        this.name = name;
    }

    @Override
    public void addFlight(Flight flight) {
        flights.clear(); // une seule place par porte
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
