package sample;

import src.Observer;
import src.Subject;
import src.Gate;
import src.Flight;

public class GateScreen extends Observer {
    private ScreenDialog screen;
    
    public GateScreen(Subject gate, ScreenDialog screen) {
        this.subject = gate;
        this.screen = screen;
    }
    
    @Override
    public void update() {
        displayFlights(subject.getFlights(), screen);
    }
} 