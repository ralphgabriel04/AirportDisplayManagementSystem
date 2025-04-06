public class GateScreen extends Observer {
    private String label;

    public GateScreen(Subject gate) {
        this.subject = gate;
        this.label = ((Gate)gate).getName();
    }

    @Override
    public void update() {
        System.out.println("GATE " + label);
        for (Flight f : subject.getFlights()) {
            System.out.println(f);
        }
    }
}