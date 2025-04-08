import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class GuiApp extends JFrame {
  
  /*
   * Simulation attributes
   * DO NOT EDIT
   */
  
	private Airport airport = new Airport();
	private Terminal termA = new Terminal("TERMINAL A");
	private Terminal termB = new Terminal("TERMINAL B");
	private Terminal termC = new Terminal("TERMINAL C");
	private Gate[] gatesA = new Gate[3];
	private Gate[] gatesB = new Gate[7];
	private Gate[] gatesC = new Gate[5];
  
  private ScreenDialog airportScreen1Dialog = new ScreenDialog(this, "AIRPORT (1)", 0);
  private ScreenDialog airportScreen2Dialog = new ScreenDialog(this, "AIRPORT (2)", 0);
  private ScreenDialog termAScreen1Dialog = new ScreenDialog(this, "TERMINAL A (1)", 0);
  private ScreenDialog termAScreen2Dialog = new ScreenDialog(this, "TERMINAL A (2)", 0);
  private ScreenDialog termAScreen3Dialog = new ScreenDialog(this, "TERMINAL A (3)", 0);
  private ScreenDialog termBScreen1Dialog = new ScreenDialog(this, "TERMINAL B (1)", 0);
  private ScreenDialog termBScreen2Dialog = new ScreenDialog(this, "TERMINAL B (2)", 0);
  private ScreenDialog termBScreen3Dialog = new ScreenDialog(this, "TERMINAL B (3)", 0);
  private ScreenDialog termCScreen1Dialog = new ScreenDialog(this, "TERMINAL C (1)", 0);
  private ScreenDialog termCScreen2Dialog = new ScreenDialog(this, "TERMINAL C (2)", 0);
  private ScreenDialog termCScreen3Dialog = new ScreenDialog(this, "TERMINAL C (3)", 0);
  private ScreenDialog[] gatesADialogs = new ScreenDialog[3];
  private ScreenDialog[] gatesBDialogs = new ScreenDialog[7];
  private ScreenDialog[] gatesCDialogs = new ScreenDialog[5];
  
  /*
   * GUI attributes
   */
  
  public static final int WIDTH = 400;
  public static final int HEIGHT = 4 * ScreenDialog.HEIGHT;
  private static final int LINE_COUNT = 10;
  private static final int LINE_SIZE = 30; // in characters
  
  private static final String addFlightText = "Add Flight";
  private static final String delayFlightText = "Delay Flight";
  private static final String quitText = "Quit";
  // TODO: Add attributes for other operations
  
  private JButton addButton, delayButton, quitButton;
  // TODO: Add attributes for other operations
  
  private JTextArea displayArea;
  private JScrollPane scrollPane;
  private Container contentPane;
  
  /**
   * Creates the observers and attach them.
   * DO NOT EDIT
   */
  public void createObservers() {
    
    // Airport screens
    
    AirportScreen airportScreen1 = new AirportScreen(airport, "AIRPORT (1)", airportScreen1Dialog);
    AirportScreen airportScreen2 = new AirportScreen(airport, "AIRPORT (2)", airportScreen2Dialog);
    airport.attach(airportScreen1);
    airport.attach(airportScreen2);
    
    // Terminal screens (three each)
    
    TerminalScreen termAScreen1 = new TerminalScreen(termA, "TERMINAL A (1)", termAScreen1Dialog);
    TerminalScreen termAScreen2 = new TerminalScreen(termA, "TERMINAL A (2)", termAScreen2Dialog);
    TerminalScreen termAScreen3 = new TerminalScreen(termA, "TERMINAL A (3)", termAScreen3Dialog);
    termA.attach(termAScreen1);
    termA.attach(termAScreen2);
    termA.attach(termAScreen3);
    
    TerminalScreen termBScreen1 = new TerminalScreen(termB, "TERMINAL B (1)", termBScreen1Dialog);
    TerminalScreen termBScreen2 = new TerminalScreen(termB, "TERMINAL B (2)", termBScreen2Dialog);
    TerminalScreen termBScreen3 = new TerminalScreen(termB, "TERMINAL B (3)", termBScreen3Dialog);
    termB.attach(termBScreen1);
    termB.attach(termBScreen2);
    termB.attach(termBScreen3);
    
    TerminalScreen termCScreen1 = new TerminalScreen(termC, "TERMINAL C (1)", termCScreen1Dialog);
    TerminalScreen termCScreen2 = new TerminalScreen(termC, "TERMINAL C (2)", termCScreen2Dialog);
    TerminalScreen termCScreen3 = new TerminalScreen(termC, "TERMINAL C (3)", termCScreen3Dialog);
    termC.attach(termCScreen1);
    termC.attach(termCScreen2);
    termC.attach(termCScreen3);
    
    // Gates and gate screens
    
    // Terminal A
    for(int i = 0; i < gatesA.length; ++i) {
      gatesA[i] = new Gate("A-" + (i + 1));
      gatesA[i].attach(new GateScreen(gatesA[i], gatesADialogs[i]));
    }
    
    // Terminal B
    for(int i = 0; i < gatesB.length; ++i) {
      gatesB[i] = new Gate("B-" + (i + 1));
      gatesB[i].attach(new GateScreen(gatesB[i], gatesBDialogs[i]));
    }
    
    // Terminal C
    for(int i = 0; i < gatesC.length; ++i) {
      gatesC[i] = new Gate("C-" + (i + 1));
      gatesC[i].attach(new GateScreen(gatesC[i], gatesCDialogs[i]));
    }
  }
  
  /**
   * Creates the objects for the airport screens in a user-friendly layout
   */
  public void createScreenDialogs() {
    int colNumber, rowNumber = 0;
    
    colNumber = 0;
    airportScreen1Dialog.setVisible(true);
    airportScreen1Dialog.setLocation(WIDTH + colNumber++ * ScreenDialog.WIDTH, rowNumber * ScreenDialog.HEIGHT);
    airportScreen2Dialog.setVisible(true);
    airportScreen2Dialog.setLocation(WIDTH + colNumber++ * ScreenDialog.WIDTH, rowNumber * ScreenDialog.HEIGHT);

    ++rowNumber;
    colNumber = 0;
    termAScreen1Dialog.setVisible(true);
    termAScreen1Dialog.setLocation(WIDTH + colNumber++ * ScreenDialog.WIDTH, rowNumber * ScreenDialog.HEIGHT);
    termAScreen2Dialog.setVisible(true);
    termAScreen2Dialog.setLocation(WIDTH + colNumber++ * ScreenDialog.WIDTH, rowNumber * ScreenDialog.HEIGHT);
    termAScreen3Dialog.setVisible(true);
    termAScreen3Dialog.setLocation(WIDTH + colNumber++ * ScreenDialog.WIDTH, rowNumber * ScreenDialog.HEIGHT);
    
    ++rowNumber;
    colNumber = 0;
    termBScreen1Dialog.setVisible(true);
    termBScreen1Dialog.setLocation(WIDTH + colNumber++ * ScreenDialog.WIDTH, rowNumber * ScreenDialog.HEIGHT);
    termBScreen2Dialog.setVisible(true);
    termBScreen2Dialog.setLocation(WIDTH + colNumber++ * ScreenDialog.WIDTH, rowNumber * ScreenDialog.HEIGHT);
    termBScreen3Dialog.setVisible(true);
    termBScreen3Dialog.setLocation(WIDTH + colNumber++ * ScreenDialog.WIDTH, rowNumber * ScreenDialog.HEIGHT);
    
    ++rowNumber;
    colNumber = 0;
    termCScreen1Dialog.setVisible(true);
    termCScreen1Dialog.setLocation(WIDTH + colNumber++ * ScreenDialog.WIDTH, rowNumber * ScreenDialog.HEIGHT);
    termCScreen2Dialog.setVisible(true);
    termCScreen2Dialog.setLocation(WIDTH + colNumber++ * ScreenDialog.WIDTH, rowNumber * ScreenDialog.HEIGHT);
    termCScreen3Dialog.setVisible(true);
    termCScreen3Dialog.setLocation(WIDTH + colNumber++ * ScreenDialog.WIDTH, rowNumber * ScreenDialog.HEIGHT);

    ++rowNumber;
    for (int i = 0; i < gatesADialogs.length; ++i) {
      gatesADialogs[i] = new ScreenDialog(this, "GATE A-" + (i + 1), 0);
      gatesADialogs[i].setVisible(true);
      gatesADialogs[i].setLocation(i * ScreenDialog.WIDTH, rowNumber * ScreenDialog.HEIGHT);
    }

    ++rowNumber;
    int maxRowLength = 4; // Layout on two rows
    for (int i = 0; i < gatesBDialogs.length; ++i) {
      gatesBDialogs[i] = new ScreenDialog(this, "GATE B-" + (i + 1), 0);
      gatesBDialogs[i].setVisible(true);      
      gatesBDialogs[i].setLocation((i % (maxRowLength + 1)) * ScreenDialog.WIDTH, rowNumber * ScreenDialog.HEIGHT);
      if (i == maxRowLength) ++rowNumber;
    }

    ++rowNumber;
    for (int i = 0; i < gatesCDialogs.length; ++i) {
      gatesCDialogs[i] = new ScreenDialog(this, "GATE C-" + (i + 1), 0);
      gatesCDialogs[i].setVisible(true);
      gatesCDialogs[i].setLocation(i * ScreenDialog.WIDTH, rowNumber * ScreenDialog.HEIGHT);
    }
  }
  
  /**
   * Initializes the GUI components
   */
  public void initComponents() {
    addButton = new JButton(addFlightText);
    delayButton = new JButton(delayFlightText);
    quitButton = new JButton(quitText);
    // TODO: Add button instanciations for other operations
    
    // Initialize display area
    displayArea = new JTextArea(LINE_COUNT, LINE_SIZE);
    displayArea.setEditable(false);
    scrollPane = new JScrollPane(displayArea,
                                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
  }
  
  public void initLayout() {
    contentPane = getContentPane();
    contentPane.setLayout(new FlowLayout());
  }
  
  public void initContentPane() {
    contentPane.add(addButton);
    contentPane.add(delayButton);
    // TODO: Add other buttons
    contentPane.add(quitButton);
    
    contentPane.add(displayArea);
  }
  
  public void initListeners() {
    
    addButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        displayArea.append("Add Flight Option");
        System.out.println("Add Flight Option");
        AddFlightDialog addFlightDialog = new AddFlightDialog(GuiApp.this);
        addFlightDialog.setVisible(true);
      }
    });
    
    quitButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        displayArea.append("Quit");
        System.out.println("Quit");
        
        // Yes/no pop-up
        int choice = JOptionPane.showConfirmDialog(
          null,
          "Do you want to quit?",
          "Quit",
          JOptionPane.YES_NO_OPTION,
          JOptionPane.QUESTION_MESSAGE);
        
        if (choice == JOptionPane.YES_OPTION) {
          System.exit(0);
        }
      }
    });
    
  }
  
  public void init() {
    initComponents();
    initLayout();
    initContentPane();
    initListeners();
  }
  
  public GuiApp(String windowTitle) {
    super(windowTitle);
    
    frameInit();
    init();
    createScreenDialogs();
    createObservers();
    
    // Add listener to quit
    this.addWindowListener(
      new WindowAdapter() {
        public void windowClosing(WindowEvent e) {
          System.exit(0);
        }
      });
    
    pack(); // Start to change frame dimensions
    setVisible(true);
    setSize(WIDTH, HEIGHT);
    setResizable(true);
    validate(); // Update and validation
  }
  
  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        new GuiApp("Airport Simulation");
      }
    });
  }

}
