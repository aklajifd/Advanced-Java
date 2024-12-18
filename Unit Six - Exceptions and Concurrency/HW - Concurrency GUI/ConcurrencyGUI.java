// Daniel Fijalka
// OCCC - Fall 2024
// Advanced Java
// Unit 6 Homework
// Concurrency GUI

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;
import java.util.concurrent.Semaphore;

public class ConcurrencyGUI extends JFrame implements ActionListener {
  public static final int WIDTH = 600;
  public static final int HEIGHT = 600;

  static int theBuffer;
  static Semaphore s = new Semaphore(1);
  Producer [] p;
  Consumer [] c;

  // Fields for user input and display
  static JTextField numProducers;
  static JTextField numConsumers;
  static JTextField avgProduced;
  static JTextField avgConsumed;
  static JTextField currentState;

  // Labels for the text fields
  JLabel labelNumProducers;
  JLabel labelNumConsumers;
  JLabel labelAvgProduced;
  JLabel labelAvgConsumed;
  JLabel labelCurrentState;

  // Buttons
  JButton buttonStart = new JButton("Start");
 
  // Area to display console messages
  static JTextArea consoleText = new JTextArea(20, 40);

  public static void main(String [] args) {
    ConcurrencyGUI cg = new ConcurrencyGUI();
  }

  // Constructor
  public ConcurrencyGUI() {
    super("Concurrency GUI");
    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    setSize(WIDTH, HEIGHT);
    setResizable(true);

    addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        exitPersonGUI();
      }
    });

    JPanel mainPanel = new JPanel(new BorderLayout());

    // Top panel to hold labels, text fields, and button
    JPanel topPanel = new JPanel(new GridLayout(6, 2));

    // Create labels and text fields
    JPanel numProducerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    labelNumProducers = new JLabel("Number of Producers:");
    numProducers = new JTextField(10);
    numProducerPanel.add(labelNumProducers);
    numProducerPanel.add(numProducers);

    JPanel numConsumerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    labelNumConsumers = new JLabel("Number of Consumers:");
    numConsumers = new JTextField(10);
    numConsumerPanel.add(labelNumConsumers);
    numConsumerPanel.add(numConsumers);

    JPanel avgProducerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    labelAvgProduced = new JLabel("Average value produced:");
    avgProduced = new JTextField(10);
    avgProducerPanel.add(labelAvgProduced);
    avgProducerPanel.add(avgProduced);

    JPanel avgConsumerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    labelAvgConsumed = new JLabel("Average value consumed:");
    avgConsumed = new JTextField(10);
    avgConsumerPanel.add(labelAvgConsumed);
    avgConsumerPanel.add(avgConsumed);

    JPanel buttonStartPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    buttonStartPanel.add(buttonStart);

    JPanel currentStatePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    labelCurrentState = new JLabel("Current state:");
    currentState = new JTextField(10);
    currentStatePanel.add(labelCurrentState);
    currentStatePanel.add(currentState);

    // Add to the topPanel
    topPanel.add(numProducerPanel);
    topPanel.add(numConsumerPanel);
    topPanel.add(avgProducerPanel);
    topPanel.add(avgConsumerPanel);
    topPanel.add(buttonStartPanel);
    topPanel.add(currentStatePanel);

    // Bottom panel to hold text pane for console messages
    JPanel bottomPanel = new JPanel();
    bottomPanel.add(consoleText);

    // Add top and bottom panels to mainPanel, position mainPanel
    mainPanel.add(topPanel, BorderLayout.NORTH);
    mainPanel.add(bottomPanel, BorderLayout.SOUTH);
    getContentPane().add(mainPanel, BorderLayout.CENTER); 

    // Add action listener to button
    buttonStart.addActionListener(this);
    setVisible(true);
  }

  // From ProducerConsumer demo
  public static void mySleep(){
    // this function puts the thread "to sleep" for a while,
    // to simulate time spent processing 

    try{
      Thread.sleep((int)(Math.random()*1000));
    }
    catch(InterruptedException e){
      // do nothing
    }
  } // close sleep method

  // Method to exit the GUI
  public void exitPersonGUI() {
    dispose();
    System.exit(0);
  }
    
  @Override
  public void actionPerformed(ActionEvent e) {
    if ( e.getSource() == buttonStart ) {
      try {
        // Get user input from text fields
        int numberOfProducers = Integer.parseInt(numProducers.getText());
        int numberOfConsumers = Integer.parseInt(numConsumers.getText());
        int avgValProducers = Integer.parseInt(avgProduced.getText());
        int avgValConsumers = Integer.parseInt(avgConsumed.getText());

        p = new Producer[numberOfProducers];
        c = new Consumer[numberOfConsumers];

        // Create new Producers and Consumers 
        for (int i = 0; i < numberOfProducers; i++) {
          p[i] = new Producer(i, avgValProducers);
          p[i].start();
        }
        for (int i = 0; i < numberOfConsumers; i++) {
          c[i] = new Consumer(i, avgValConsumers);
          c[i].start();
        }
        System.out.println("Starting the program with " + numberOfProducers + " Producers and " + numberOfConsumers + " Consumers.");
        consoleText.append("Starting the program with " + numberOfProducers + " Producers and " + numberOfConsumers + " Consumers.\n");
      }
      catch (Exception ex) {
        System.out.println(ex.toString());  // Display message to user if error encountered
        JOptionPane.showMessageDialog(this, "Error!", "Error", JOptionPane.ERROR_MESSAGE);  
      }
    }
  }

  private static class Producer extends Thread{
    int i;
    int avg;

    public Producer(int i, int avg) {
      super();
      this.i = i;
      this.avg = avg;
    }

    public void run() {
      while(true) {
        mySleep();
        System.out.println("Producer " + i + ": attempting to acquire");
        consoleText.append("Producer " + i + ": attempting to acquire\n");
        try {
          s.acquire();
          System.out.println("Producer " + i + ": resource acquired!");
          consoleText.append("Producer " + i + ": resource acquired!\n");
          mySleep();
          System.out.println("Producer " + i + ": theBuffer (pre)  is " + theBuffer);
          consoleText.append("Producer " + i + ": theBuffer (pre)  is " + theBuffer + "\n");
          currentState.setText(String.valueOf(theBuffer));  // display current state of shared resource (an integer)
          theBuffer += (avg - 3) + (int)(Math.random()*(avg + 1));  // get random value from average range 
          System.out.println("Producer " + i + ": theBuffer (post) is " + theBuffer);
          consoleText.append("Producer " + i + ": theBuffer (post) is " + theBuffer + "\n");
          currentState.setText(String.valueOf(theBuffer));  // display current state of shared resource (an integer)
          System.out.println("Producer " + i + ": resource released");
          consoleText.append("Producer " + i + ": resource released\n");
          s.release();
        }
        catch(InterruptedException e){}
      }   
    }
  }

  private static class Consumer extends Thread {
    int i;
    int avg;

    public Consumer(int i, int avg) {
      super();
      this.i = i;
      this.avg = avg;
    }

    public void run() {
      while(true) {
        mySleep();
        System.out.println("Consumer " + i + ": attempting to acquire");
        consoleText.append("Consumer " + i + ": attempting to acquire\n");
        try {
          s.acquire();
          System.out.println("Consumer " + i + ": resource acquired!");
          consoleText.append("Consumer " + i + ": resource acquired!\n");
          mySleep();
          System.out.println("Consumer " + i + ": theBuffer is " + theBuffer);
          consoleText.append("Consumer " + i + ": theBuffer is " + theBuffer + "\n");
          int need = (avg - 3) + (int)(1 + Math.random()*(avg + 1));  // get random value from average range 
          System.out.println("Consumer " + i + ": my need is " + need);
          consoleText.append("Consumer " + i + ": my need is " + need + "\n");
          if (theBuffer >= need) { 
            theBuffer -= need;
            currentState.setText(String.valueOf(theBuffer));  // display current state of shared resource (an integer)
            System.out.println("Consumer " + i + ": got what I needed!");
            consoleText.append("Consumer " + i + ": got what I needed!\n");
            System.out.println("Consumer " + i + ": theBuffer is now " + theBuffer);
            consoleText.append("Consumer " + i + ": theBuffer is now " + theBuffer + "\n");
          }
          else {
            System.out.println("Consumer " + i + ": resource unavailable");
            consoleText.append("Consumer " + i + ": resource unavailable\n");
          }
          System.out.println("Consumer " + i + ": resource released");
          consoleText.append("Consumer " + i + ": resource released\n");
          s.release();
        }
        catch(InterruptedException e){}
      }
    }
  } 
}