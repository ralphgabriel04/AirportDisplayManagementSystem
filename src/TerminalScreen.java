public class TerminalScreen extends Observer {
    private String label;

    public TerminalScreen(Subject terminal, String label) {
        this.subject = terminal;
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