public class AirportScreen extends Observer {
    private String label;

    public AirportScreen(Subject airport, String label) {
        this.subject = airport;
        this.label = label;
    }

    @Override
    public void update() {
        System.out.println(label);
        for (Flight f : subject.getFlights()) {
            System.out.println(f);
        }
    }
}