import java.util.Scanner;

public class ConsoleApp {
    private Scanner scan = new Scanner(System.in);
    private Airport airport = new Airport();
    private Terminal termA = new Terminal("A");
    private Terminal termB = new Terminal("B");
    private Terminal termC = new Terminal("C");
    private Gate[] gatesA = new Gate[3];  // 3 portes pour terminal A
    private Gate[] gatesB = new Gate[8];  // 8 portes pour terminal B (B-1 à B-7)
    private Gate[] gatesC = new Gate[5];  // 5 portes pour terminal C

    public void displayPrompt() {
        int option = -1;
        
        while (option != 0) {
            displayMenu();
            System.out.print("Select Option: ");
            
            try {
                option = Integer.parseInt(scan.nextLine());
                
                switch(option) {
                    case 1: addFlight(); break;
                    case 2: delayFlight(); break;
                    case 3: changeGate(); break;
                    case 4: cancelFlight(); break;
                    case 5: notifyBoarding(); break;
                    case 6: removeFlight(); break;
                    case 0: break;
                    default: System.out.println("Invalid option"); break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
        scan.close();
    }

    public void addFlight() {
        try {
            System.out.print("Company: ");
            String company = scan.nextLine();
            
            System.out.print("Flight Number: ");
            int flightNumber = Integer.parseInt(scan.nextLine());
            
            System.out.print("Destination: ");
            String destination = scan.nextLine();
            
            System.out.print("Departure Time (0000): ");
            int departureTime = Integer.parseInt(scan.nextLine());
            
            System.out.print("Terminal (A, B, C): ");
            String terminal = scan.nextLine();
            
            System.out.print("Gate Number: ");
            int gateNumber = Integer.parseInt(scan.nextLine());
            
            System.out.println("Status:");
            System.out.println("1 - " + Flight.ONTIME);
            System.out.println("2 - " + Flight.CANCELLED);
            System.out.println("3 - " + Flight.BOARDING);
            System.out.println("4 - " + Flight.DELAYED);
            
            int statusInt = Integer.parseInt(scan.nextLine());
            String statusStr = Flight.ONTIME;  // valeur par défaut
            
            switch(statusInt) {
                case 1: statusStr = Flight.ONTIME; break;
                case 2: statusStr = Flight.CANCELLED; break;
                case 3: statusStr = Flight.BOARDING; break;
                case 4: statusStr = Flight.DELAYED; break;
            }
            
            Flight flight = new Flight(company, flightNumber, destination,
                departureTime, terminal + "-" + gateNumber, statusStr);
            
            // Ajouter le vol dans l'ordre: aéroport, terminal, porte
            airport.addFlight(flight);
            
            switch(terminal) {
                case "A":
                    termA.addFlight(flight);
                    gatesA[gateNumber - 1].addFlight(flight);
                    break;
                case "B":
                    termB.addFlight(flight);
                    gatesB[gateNumber - 1].addFlight(flight);
                    break;
                case "C":
                    termC.addFlight(flight);
                    gatesC[gateNumber - 1].addFlight(flight);
                    break;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format. Please try again.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void delayFlight() {
        try {
            System.out.print("Company: ");
            String company = scan.nextLine();
            
            System.out.print("Flight Number: ");
            int flightNumber = Integer.parseInt(scan.nextLine());
            
            Flight targetFlight = findFlight(company, flightNumber);
            if (targetFlight != null) {
                targetFlight.setStatus(Flight.DELAYED);
                
                // Notify all relevant subjects
                airport.notifyObservers();
                
                String gate = targetFlight.getGate();
                String terminal = gate.substring(0, 1);
                int gateNumber = Integer.parseInt(gate.substring(2)) - 1;
                
                switch(terminal) {
                    case "A":
                        termA.notifyObservers();
                        gatesA[gateNumber].notifyObservers();
                        break;
                    case "B":
                        termB.notifyObservers();
                        gatesB[gateNumber].notifyObservers();
                        break;
                    case "C":
                        termC.notifyObservers();
                        gatesC[gateNumber].notifyObservers();
                        break;
                }
            } else {
                System.out.println("Flight not found");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format. Please try again.");
        }
    }

    public void changeGate() {
        try {
            System.out.print("Company: ");
            String company = scan.nextLine();
            
            System.out.print("Flight Number: ");
            int flightNumber = Integer.parseInt(scan.nextLine());
            
            System.out.print("Terminal (A, B, C): ");
            String newTerminal = scan.nextLine();
            
            System.out.print("Gate Number: ");
            int newGateNumber = Integer.parseInt(scan.nextLine());
            
            Flight targetFlight = findFlight(company, flightNumber);
            if (targetFlight != null) {
                // Vérifier si la nouvelle porte est disponible
                Gate newGate = null;
                switch(newTerminal) {
                    case "A": newGate = gatesA[newGateNumber - 1]; break;
                    case "B": newGate = gatesB[newGateNumber - 1]; break;
                    case "C": newGate = gatesC[newGateNumber - 1]; break;
                }
                
                if (!newGate.getFlights().isEmpty()) {
                    System.out.println("New gate " + newTerminal + "-" + newGateNumber + " is not available");
                    return;
                }
                
                // Récupérer les informations de l'ancienne porte
                String oldGate = targetFlight.getGate();
                String oldTerminal = oldGate.substring(0, 1);
                int oldGateNumber = Integer.parseInt(oldGate.substring(2)) - 1;
                
                // Supprimer de l'ancienne porte et terminal
                switch(oldTerminal) {
                    case "A": 
                        gatesA[oldGateNumber].removeFlight(targetFlight);
                        if (!oldTerminal.equals(newTerminal)) {
                            termA.removeFlight(targetFlight);
                        }
                        break;
                    case "B": 
                        gatesB[oldGateNumber].removeFlight(targetFlight);
                        if (!oldTerminal.equals(newTerminal)) {
                            termB.removeFlight(targetFlight);
                        }
                        break;
                    case "C": 
                        gatesC[oldGateNumber].removeFlight(targetFlight);
                        if (!oldTerminal.equals(newTerminal)) {
                            termC.removeFlight(targetFlight);
                        }
                        break;
                }
                
                // Mettre à jour la porte du vol
                targetFlight.setGate(newTerminal + "-" + newGateNumber);
                
                // Ajouter à la nouvelle porte et terminal
                switch(newTerminal) {
                    case "A":
                        if (!oldTerminal.equals(newTerminal)) {
                            termA.addFlight(targetFlight);
                        }
                        gatesA[newGateNumber - 1].addFlight(targetFlight);
                        break;
                    case "B":
                        if (!oldTerminal.equals(newTerminal)) {
                            termB.addFlight(targetFlight);
                        }
                        gatesB[newGateNumber - 1].addFlight(targetFlight);
                        break;
                    case "C":
                        if (!oldTerminal.equals(newTerminal)) {
                            termC.addFlight(targetFlight);
                        }
                        gatesC[newGateNumber - 1].addFlight(targetFlight);
                        break;
                }
                
                // Notifier dans l'ordre: ancienne porte
                switch(oldTerminal) {
                    case "A": gatesA[oldGateNumber].notifyObservers(); break;
                    case "B": gatesB[oldGateNumber].notifyObservers(); break;
                    case "C": gatesC[oldGateNumber].notifyObservers(); break;
                }
                
                // Notifier l'aéroport
                airport.notifyObservers();
                
                // Notifier le terminal
                switch(newTerminal) {
                    case "A": termA.notifyObservers(); break;
                    case "B": termB.notifyObservers(); break;
                    case "C": termC.notifyObservers(); break;
                }
                
                // Notifier la nouvelle porte
                switch(newTerminal) {
                    case "A": gatesA[newGateNumber - 1].notifyObservers(); break;
                    case "B": gatesB[newGateNumber - 1].notifyObservers(); break;
                    case "C": gatesC[newGateNumber - 1].notifyObservers(); break;
                }
                
            } else {
                System.out.println("Flight not found");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format. Please try again.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void cancelFlight() {
        try {
            System.out.print("Company: ");
            String company = scan.nextLine();
            
            System.out.print("Flight Number: ");
            int flightNumber = Integer.parseInt(scan.nextLine());
            
            Flight targetFlight = findFlight(company, flightNumber);
            if (targetFlight != null) {
                targetFlight.setStatus(Flight.CANCELLED);
                
                // Notifier tous les sujets concernés
                airport.notifyObservers();
                
                String gate = targetFlight.getGate();
                String terminal = gate.substring(0, 1);
                int gateNumber = Integer.parseInt(gate.substring(2)) - 1;
                
                switch(terminal) {
                    case "A":
                        termA.notifyObservers();
                        gatesA[gateNumber].notifyObservers();
                        break;
                    case "B":
                        termB.notifyObservers();
                        gatesB[gateNumber].notifyObservers();
                        break;
                    case "C":
                        termC.notifyObservers();
                        gatesC[gateNumber].notifyObservers();
                        break;
                }
            } else {
                System.out.println("Flight not found");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format. Please try again.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void notifyBoarding() {
        try {
            System.out.print("Company: ");
            String company = scan.nextLine();
            
            System.out.print("Flight Number: ");
            int flightNumber = Integer.parseInt(scan.nextLine());
            
            Flight targetFlight = findFlight(company, flightNumber);
            if (targetFlight != null) {
                targetFlight.setStatus(Flight.BOARDING);
                
                // Notifier tous les sujets concernés
                airport.notifyObservers();
                
                String gate = targetFlight.getGate();
                String terminal = gate.substring(0, 1);
                int gateNumber = Integer.parseInt(gate.substring(2)) - 1;
                
                switch(terminal) {
                    case "A":
                        termA.notifyObservers();
                        gatesA[gateNumber].notifyObservers();
                        break;
                    case "B":
                        termB.notifyObservers();
                        gatesB[gateNumber].notifyObservers();
                        break;
                    case "C":
                        termC.notifyObservers();
                        gatesC[gateNumber].notifyObservers();
                        break;
                }
            } else {
                System.out.println("Flight not found");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format. Please try again.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void removeFlight() {
        try {
            System.out.print("Company: ");
            String company = scan.nextLine();
            
            System.out.print("Flight Number: ");
            int flightNumber = Integer.parseInt(scan.nextLine());
            
            Flight targetFlight = findFlight(company, flightNumber);
            if (targetFlight != null) {
                // Récupérer les informations de la porte avant de supprimer le vol
                String gate = targetFlight.getGate();
                String terminal = gate.substring(0, 1);
                int gateNumber = Integer.parseInt(gate.substring(2)) - 1;
                
                // Supprimer le vol dans l'ordre: aéroport, terminal, porte
                airport.removeFlight(targetFlight);
                
                switch(terminal) {
                    case "A":
                        termA.removeFlight(targetFlight);
                        gatesA[gateNumber].removeFlight(targetFlight);
                        break;
                    case "B":
                        termB.removeFlight(targetFlight);
                        gatesB[gateNumber].removeFlight(targetFlight);
                        break;
                    case "C":
                        termC.removeFlight(targetFlight);
                        gatesC[gateNumber].removeFlight(targetFlight);
                        break;
                }
            } else {
                System.out.println("Flight not found");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format. Please try again.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void displayMenu() {
        System.out.println("********************");
        System.out.println("1 - Add Flight");
        System.out.println("2 - Delay Flight");
        System.out.println("3 - Change Gate");
        System.out.println("4 - Cancel Flight");
        System.out.println("5 - Notify Boarding");
        System.out.println("6 - Remove Flight");
        System.out.println("0 - Quit");
        System.out.println("********************");
    }

    private Flight findFlight(String company, int flightNumber) {
        for (Flight f : airport.getFlights()) {
            if (f.getCompany().equals(company) && f.getFlightNumber() == flightNumber) {
                return f;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        ConsoleApp app = new ConsoleApp();
        
        // Create and attach airport screens
        AirportScreen airportScreen1 = new AirportScreen(app.airport, "AIRPORT (1)");
        AirportScreen airportScreen2 = new AirportScreen(app.airport, "AIRPORT (2)");
        app.airport.attach(airportScreen1);
        app.airport.attach(airportScreen2);
        
        // Create and attach terminal screens
        TerminalScreen termAScreen1 = new TerminalScreen(app.termA, "TERMINAL A (1)");
        TerminalScreen termAScreen2 = new TerminalScreen(app.termA, "TERMINAL A (2)");
        TerminalScreen termAScreen3 = new TerminalScreen(app.termA, "TERMINAL A (3)");
        app.termA.attach(termAScreen1);
        app.termA.attach(termAScreen2);
        app.termA.attach(termAScreen3);
        
        TerminalScreen termBScreen1 = new TerminalScreen(app.termB, "TERMINAL B (1)");
        TerminalScreen termBScreen2 = new TerminalScreen(app.termB, "TERMINAL B (2)");
        TerminalScreen termBScreen3 = new TerminalScreen(app.termB, "TERMINAL B (3)");
        app.termB.attach(termBScreen1);
        app.termB.attach(termBScreen2);
        app.termB.attach(termBScreen3);
        
        TerminalScreen termCScreen1 = new TerminalScreen(app.termC, "TERMINAL C (1)");
        TerminalScreen termCScreen2 = new TerminalScreen(app.termC, "TERMINAL C (2)");
        TerminalScreen termCScreen3 = new TerminalScreen(app.termC, "TERMINAL C (3)");
        app.termC.attach(termCScreen1);
        app.termC.attach(termCScreen2);
        app.termC.attach(termCScreen3);
        
        // Initialize gates
        for(int i = 0; i < app.gatesA.length; i++) {
            app.gatesA[i] = new Gate("A-" + (i + 1));
            app.gatesA[i].attach(new GateScreen(app.gatesA[i]));
        }
        
        for(int i = 0; i < app.gatesB.length; i++) {
            app.gatesB[i] = new Gate("B-" + (i + 1));
            app.gatesB[i].attach(new GateScreen(app.gatesB[i]));
        }
        
        for(int i = 0; i < app.gatesC.length; i++) {
            app.gatesC[i] = new Gate("C-" + (i + 1));
            app.gatesC[i].attach(new GateScreen(app.gatesC[i]));
        }
        
        // Start application
        app.displayPrompt();
    }
}
