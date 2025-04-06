# 🛫 Airport Display Management System | Système de Gestion d'Affichage Aéroportuaire

## 📌 Description [EN/FR]
A real-time flight information display system implementing the Observer pattern in Java. / Un système d'affichage d'informations de vol en temps réel implémentant le pattern Observer en Java.

✅ Real-time flight status updates / Mises à jour en temps réel
✅ Multi-level display management / Gestion d'affichage multi-niveaux
✅ Observer pattern implementation / Implémentation du pattern Observer
✅ Terminal and gate management / Gestion des terminaux et des portes

## 🛠️ Technologies Used | Technologies Utilisées
- Java
- Observer Design Pattern
- Console-based UI / Interface console

## 📂 Project Structure | Structure du Projet
```
/src
├── Flight.java           # Flight data model / Modèle de données des vols
├── Subject.java          # Abstract observable class / Classe observable abstraite
├── Observer.java         # Abstract observer class / Classe observateur abstraite
├── Airport.java         # Main flight repository / Dépôt principal des vols
├── Terminal.java        # Terminal management / Gestion des terminaux
├── Gate.java            # Gate operations / Opérations des portes
├── AirportScreen.java   # Airport display / Affichage aéroport
├── TerminalScreen.java  # Terminal display / Affichage terminal
├── GateScreen.java      # Gate display / Affichage porte
└── ConsoleApp.java      # Main application / Application principale
```

## 🚀 Features | Fonctionnalités

### Flight Management | Gestion des Vols
- ✈️ Add/Remove flights | Ajout/Suppression de vols
- ⏰ Flight delay handling | Gestion des retards
- 🚪 Gate changes | Changements de porte
- ❌ Flight cancellations | Annulations de vols
- 🎫 Boarding notifications | Notifications d'embarquement

### Display System | Système d'Affichage
- 🖥️ Multi-level display synchronization | Synchronisation multi-écrans
- 🔄 Real-time updates | Mises à jour en temps réel
- 📊 Hierarchical information flow | Flux d'information hiérarchique

## 📈 Example Usage | Exemple d'Utilisation
```java
// Creating and managing a flight
Flight flight = new Flight("AA123", 123, "Paris", 1430, "B-3", Flight.ONTIME);
airport.addFlight(flight);

// Updating flight status
flight.setStatus(Flight.DELAYED);
airport.notifyObservers();  // Updates all displays automatically
```

## 🎯 Core Components | Composants Principaux

### 1️⃣ Flight Class | Classe Vol
Immutable representation of flight information including:
- Flight number | Numéro de vol
- Destination
- Gate | Porte
- Status | Statut
- Departure time | Heure de départ

### 2️⃣ Subject Classes | Classes Sujets
- Airport: Main flight repository | Dépôt principal des vols
- Terminal: Terminal-level management | Gestion niveau terminal
- Gate: Individual gate operations | Opérations des portes

### 3️⃣ Observer Classes | Classes Observateurs
Screen implementations for different display levels:
- AirportScreen: Airport-wide displays
- TerminalScreen: Terminal-specific displays
- GateScreen: Gate-level displays

## 🔄 Observer Pattern Implementation | Implémentation du Pattern Observer
- Subject maintains list of observers | Le sujet maintient une liste d'observateurs
- Automatic notification system | Système de notification automatique
- Hierarchical update propagation | Propagation hiérarchique des mises à jour

## 🎯 Future Improvements | Améliorations Futures
- ✅ Graphical User Interface | Interface graphique
- ✅ Database Integration | Intégration base de données
- ✅ Flight Schedule Management | Gestion des horaires de vol
- ✅ Multiple Airport Support | Support multi-aéroports

## 🧪 Testing | Tests
The system includes comprehensive testing through the ConsoleApp class, validating:
- Flight management operations | Opérations de gestion des vols
- Display synchronization | Synchronisation des affichages
- Error handling | Gestion des erreurs

## 📜 License | Licence
This project is open for educational purposes. / Ce projet est ouvert à des fins éducatives.

## 🤝 Contributing | Contribution
Contributions welcome! Feel free to submit issues or pull requests. / Les contributions sont bienvenues !
