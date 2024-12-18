// Daniel Fijalka
// OCCC - Fall 2024
// Advanced Java
// Unit 5 Homework
// PersonGUI Application

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;
import java.util.Vector;
import java.io.*;

public class PersonGUI extends JFrame implements ActionListener {

  // Menu items
  JMenuItem newMenu_newPerson;
  JMenuItem newMenu_newRegisteredPerson;
  JMenuItem newMenu_newOCCCPerson;
  JMenuItem fileMenu_open;
  JMenuItem fileMenu_save;
  JMenuItem fileMenu_saveAs;
  JMenuItem fileMenu_exit;
  JMenuItem helpMenu_about;

  // Combo box to display Persons
  JComboBox<String> personMenu;

  // List to store Persons
  Vector<Person> personList = new Vector<>();

  // Fields to display Person info
  JTextField firstName;
  JTextField lastName;
  JTextField govID;
  JTextField studID;

  // Labels for the text fields
  JLabel labelFirstName;
  JLabel labelLastName;
  JLabel labelGovID;
  JLabel labelStudID;

  // Buttons
  JButton buttonSave;
  JButton buttonEdit;
  JButton buttonDelete;
  JButton buttonCancel;

  boolean editFlag = false;
  int editIdx = 0;

  public static void main (String [] args) {
    PersonGUI pg = new PersonGUI();
  }

  // Constructor
  public PersonGUI() {
    super("PersonGUI Application");
    setSize(600, 600);
    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

    addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        exitPersonGUI();
      }
    });

    // Create menu bar
    JMenuBar bar = new JMenuBar();

    // Create file menu
    JMenu fileMenu = new JMenu("File");
    fileMenu.setMnemonic(KeyEvent.VK_F);  // hot key 'F'
 
    // Set open option
    fileMenu_open = new JMenuItem("Open...");
    fileMenu_open.addActionListener(this);
    fileMenu_open.setMnemonic(KeyEvent.VK_O);  // hot key 'O'

    // Set save option
    fileMenu_save = new JMenuItem("Save");
    fileMenu_save.addActionListener(this);
    fileMenu_save.setMnemonic(KeyEvent.VK_V);  // hot key 'V'

    // Set save as option
    fileMenu_saveAs = new JMenuItem("Save As...");
    fileMenu_saveAs.addActionListener(this);
    fileMenu_saveAs.setMnemonic(KeyEvent.VK_S);  // hot key 'S'

    // Set exit option
    fileMenu_exit = new JMenuItem("Exit");
    fileMenu_exit.addActionListener(this);
    fileMenu_exit.setMnemonic(KeyEvent.VK_X);  // hot key 'X'

    // Add options to fileMenu
    fileMenu.add(fileMenu_open);
    fileMenu.add(fileMenu_save);
    fileMenu.add(fileMenu_saveAs);
    fileMenu.addSeparator();
    fileMenu.add(fileMenu_exit);

    // Add fileMenu to bar
    bar.add(fileMenu);
    setJMenuBar(bar);

    // Create new menu
    JMenu newMenu = new JMenu("New");
    newMenu.setMnemonic(KeyEvent.VK_N);  // hot key 'N'

    // Set Person option
    newMenu_newPerson = new JMenuItem("Person");
    newMenu_newPerson.addActionListener(this);
    newMenu_newPerson.setMnemonic(KeyEvent.VK_P);  // hot key 'P'

    // Set RegisteredPerson option
    newMenu_newRegisteredPerson = new JMenuItem("RegisteredPerson");
    newMenu_newRegisteredPerson.addActionListener(this);
    newMenu_newRegisteredPerson.setMnemonic(KeyEvent.VK_R);  // hot key 'R'

    // Set OCCCPerson option
    newMenu_newOCCCPerson = new JMenuItem("OCCCPerson");
    newMenu_newOCCCPerson.addActionListener(this);
    newMenu_newOCCCPerson.setMnemonic(KeyEvent.VK_C);  // hot key 'C'

    // Add options to newMenu
    newMenu.add(newMenu_newPerson);
    newMenu.add(newMenu_newRegisteredPerson);
    newMenu.add(newMenu_newOCCCPerson);

    // Add newMenu to bar
    bar.add(newMenu);

    // Create help menu
    JMenu helpMenu = new JMenu("Help");
    helpMenu.setMnemonic(KeyEvent.VK_H);  // hot key 'H'

    // Set about option
    helpMenu_about = new JMenuItem("About");
    helpMenu_about.addActionListener(this);
    helpMenu_about.setMnemonic(KeyEvent.VK_A);  // hot key 'A'
   
    // Add about option to helpMenu
    helpMenu.add(helpMenu_about);

    bar.add(Box.createHorizontalGlue());
    bar.add(helpMenu);

    // Panel that holds dropdown list, labels, and fields
    JPanel mainPanel = new JPanel(new BorderLayout());
 
    // Define the dropdown list
    personMenu = new JComboBox<String>(new String [] {"Select a Person"});

    // Combo box goes inside a panel with FlowLayout
    JPanel comboBoxPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    comboBoxPanel.add(personMenu);
 
    // Another panel for the labels and fields with GridLayout
    JPanel fieldsPanel = new JPanel(new GridLayout(5, 2));

    // Create labels and text fields
    JPanel firstNamePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    labelFirstName = new JLabel("First Name:");
    firstName = new JTextField(20);
    firstNamePanel.add(labelFirstName);
    firstNamePanel.add(firstName);

    JPanel lastNamePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    labelLastName = new JLabel("Last Name:");
    lastName = new JTextField(20);
    lastNamePanel.add(labelLastName);
    lastNamePanel.add(lastName);

    JPanel govIDPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    labelGovID = new JLabel("Gov ID:");
    govID = new JTextField(20);
    govIDPanel.add(labelGovID);
    govIDPanel.add(govID);

    JPanel studIDPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    labelStudID = new JLabel("Stud ID:");
    studID = new JTextField(20);
    studIDPanel.add(labelStudID);
    studIDPanel.add(studID);

    // Add to fieldsPanel
    fieldsPanel.add(firstNamePanel);
    fieldsPanel.add(lastNamePanel);
    fieldsPanel.add(govIDPanel);
    fieldsPanel.add(studIDPanel);
  
    // Add fields and comboBox panels to bottom layer
    mainPanel.add(comboBoxPanel, BorderLayout.WEST);
    mainPanel.add(fieldsPanel, BorderLayout.EAST);

    // Button panel
    JPanel buttonPanel = new JPanel(new FlowLayout());
    buttonSave = new JButton("Save");
    buttonEdit = new JButton("Edit");
    buttonDelete = new JButton("Delete");
    buttonCancel = new JButton("Cancel");
 
    // Add buttons to buttonPanel
    buttonPanel.add(buttonSave);
    buttonPanel.add(buttonEdit);
    buttonPanel.add(buttonDelete);
    buttonPanel.add(buttonCancel);

    // Add buttonPanel to mainPanel and position mainPanel
    mainPanel.add(buttonPanel, BorderLayout.SOUTH);
    getContentPane().add(mainPanel, BorderLayout.CENTER);  

    // Add action listeners to buttons
    buttonSave.addActionListener(this);
    buttonEdit.addActionListener(this);
    buttonDelete.addActionListener(this);
    buttonCancel.addActionListener(this);
    personMenu.addActionListener(this);
    
    // Set components 
    resetComponents();
    setVisible(true);
  }

  // Method to exit the GUI
  public void exitPersonGUI() {
    dispose();
    System.exit(0);
  }

  // Method to update the dropdown
  public void updatePersonMenu() {
    personMenu.removeAllItems();
    personMenu.addItem("Select a Person");
    for(Person p: personList){
      personMenu.addItem(p.toString());
    }
  }

  // Method to "clear" all text fields
  public void clearFields() {
    firstName.setText("");
    lastName.setText("");
    govID.setText("");
    studID.setText("");
  }

  // Method to adjust components when adding new Person
  public void newPersonAdjustComponents() {
    buttonSave.setEnabled(true);
    buttonEdit.setEnabled(false); 
    buttonDelete.setEnabled(false);
    fileMenu_save.setEnabled(true);
    personMenu.setEnabled(false);  
    clearFields();  

    // Enable text fields
    firstName.setEditable(true);
    lastName.setEditable(true);
    studID.setEditable(true);
    govID.setEditable(true);
  }

  // Method to 'reset' component settings
  public void resetComponents() {
      personMenu.setEnabled(true);
      firstName.setEditable(false);
      lastName.setEditable(false);
      studID.setEditable(false);
      govID.setEditable(false);
      buttonSave.setEnabled(false);
      buttonEdit.setEnabled(true);
      buttonDelete.setEnabled(true);
      fileMenu_save.setEnabled(false);
      clearFields();
  }
 
  @Override
  public void actionPerformed(ActionEvent e){
    if ( e.getSource() == fileMenu_open ) {
      // Implement JFileChooser, info found in Java API
      JFileChooser fileChooser = new JFileChooser();
      int returnVal = fileChooser.showOpenDialog(this);

      if (returnVal == JFileChooser.APPROVE_OPTION) {
        File selectedFile = fileChooser.getSelectedFile();
        try {
          FileInputStream fin = new FileInputStream(selectedFile);
          ObjectInputStream oin = new ObjectInputStream(fin);
          Object o;

          personList.clear();  // Empty the personList to load file values

          while(true) {
            try {
              o = oin.readObject();
              if (o.getClass().equals(OCCCPerson.class)) {
                personList.add((OCCCPerson) o);
              }
              else if (o.getClass().equals(RegisteredPerson.class)) {
                personList.add((RegisteredPerson) o);
              }
              else {
                personList.add((Person) o);
              }
            } catch (EOFException eof) {
                break;  // Read until end of file is thrown, then break
            }
          }
          updatePersonMenu();  // Load the personMenu
        }
        catch (Exception ex) {
          System.out.println(ex.toString());  // Display message to user via command prompt or popup if error encountered
          JOptionPane.showMessageDialog(this, "Error loading file!", "Error", JOptionPane.ERROR_MESSAGE);  
        }
      }
    }

    if ( e.getSource() == buttonSave || e.getSource() == fileMenu_save ) {
      // Get values stored in fields 
      String fName = firstName.getText();
      String lName = lastName.getText();
      String governmentID = govID.getText();
      String studentID = studID.getText();

      // Logic if user clicks 'Save' after editing a Person
      if (editFlag) {
        Person selectedPerson = personList.get(editIdx);
        selectedPerson.setFirstName(fName);
        selectedPerson.setLastName(lName);
        JOptionPane.showMessageDialog(this, "Edits successfully saved!");
      }
      else {  // Save was clicked after adding new Person info
        Person newPerson; 

        // Check what info is present in boxes to determine the type of new Person added
        if (govID.getText().equals("") && studID.getText().equals("")) {
          newPerson = new Person(fName, lName); 
        }
        else if (studID.getText().equals("")) {
          newPerson = new RegisteredPerson(fName, lName, governmentID);
        }
        else {
          newPerson = new OCCCPerson(fName, lName, governmentID, studentID);
        }

        // Add new Person to list and udpate dropdown
        personList.add(newPerson);
        personMenu.addItem(newPerson.toString());
        JOptionPane.showMessageDialog(this, "New " + newPerson.getClass().getName() + " successfully added!");
      }
      // Reset components
      resetComponents();
      updatePersonMenu();
      clearFields();
    }

    if ( e.getSource() == fileMenu_saveAs ){
      JFileChooser fileChooser = new JFileChooser();
      int returnVal = fileChooser.showSaveDialog(this);

      if (returnVal == JFileChooser.APPROVE_OPTION) {
        File selectedFile = fileChooser.getSelectedFile();
        try {
          FileOutputStream fout = new FileOutputStream(selectedFile);
          ObjectOutputStream oout = new ObjectOutputStream(fout);

          for (int i = 0; i < personList.size(); i++) {
            oout.writeObject(personList.get(i));
          }
        }
        catch (Exception ex) {
          System.out.println(ex.toString());  // Display message to user via command prompt or popup if error encountered
          JOptionPane.showMessageDialog(this, "Error saving file!", "Error", JOptionPane.ERROR_MESSAGE);  
        }
      }
    }

    if ( e.getSource() == fileMenu_exit ){
      exitPersonGUI();
    }

    if ( e.getSource() == newMenu_newPerson ) {
      newPersonAdjustComponents();
      firstName.setEditable(true); 
      lastName.setEditable(true);
      govID.setEditable(false);  // No govID or studID for Person
      studID.setEditable(false);
    }

    if ( e.getSource() == newMenu_newRegisteredPerson ) {
      newPersonAdjustComponents();
      firstName.setEditable(true); 
      lastName.setEditable(true);
      govID.setEditable(true);
      studID.setEditable(false);  // No studID for RegisteredPerson
    }

    if ( e.getSource() == newMenu_newOCCCPerson ) {
      newPersonAdjustComponents();
      firstName.setEditable(true); 
      lastName.setEditable(true);
      govID.setEditable(true);
      studID.setEditable(true);
    }

    if ( e.getSource() == buttonEdit ) {
      int i = personMenu.getSelectedIndex();
      if (i > 0) {
        editFlag = true;
        editIdx = i - 1;
        Person selectedPerson = personList.get(editIdx);

        // User should only be able to edit first and last name
        firstName.setText(selectedPerson.getFirstName());
        lastName.setText(selectedPerson.getLastName());

        if (selectedPerson instanceof RegisteredPerson) {
          RegisteredPerson rp = (RegisteredPerson) selectedPerson;
          govID.setText(rp.getGovernmentID());
          studID.setText("");
        }
        else if (selectedPerson instanceof OCCCPerson) {
          OCCCPerson occcp = (OCCCPerson) selectedPerson;
          govID.setText(occcp.getGovernmentID());
          studID.setText(occcp.getStudentID());
        }
        else {
          govID.setText("");
          studID.setText("");
        }
 
        // Set component display for editing
        personMenu.setEnabled(false);
        firstName.setEditable(true);
        lastName.setEditable(true);
        buttonSave.setEnabled(true);  
        fileMenu_save.setEnabled(true);
        buttonDelete.setEnabled(false); 
      }
      else {
        JOptionPane.showMessageDialog(this, "Please select a Person to edit.");
      }
    }

    if ( e.getSource() == buttonDelete ) {
      int i = personMenu.getSelectedIndex();
      if (i > 0) {
        personList.remove(i - 1);
        updatePersonMenu();
        clearFields();
      }
      else {
        JOptionPane.showMessageDialog(this, "Please select a Person to delete.");
      }
    }

    if ( e.getSource() == buttonCancel ) {
      resetComponents();
    }

    if ( e.getSource() == personMenu ) {
      // Logic to display Person data if selected in the dropdown
      int i = personMenu.getSelectedIndex();
      if (i > 0) {
        Person selectedPerson = personList.get(i - 1);

        firstName.setText(selectedPerson.getFirstName());
        lastName.setText(selectedPerson.getLastName());
        
        if (selectedPerson instanceof OCCCPerson) {
          OCCCPerson occcp = (OCCCPerson) selectedPerson;
          govID.setText(occcp.getGovernmentID());
          studID.setText(occcp.getStudentID());
        }
        else if (selectedPerson instanceof RegisteredPerson) {
          RegisteredPerson rp = (RegisteredPerson) selectedPerson;
          govID.setText(rp.getGovernmentID());
          studID.setText("");
        }
        else {
          govID.setText("");
          studID.setText("");
        }
      }
      else {
        clearFields();
      }    
    }

    if ( e.getSource() == helpMenu_about ) {  // Display info section to user
      JOptionPane.showMessageDialog(this, "- Use the \"New\" menu to create a new instance of Person. The text boxes will adjust accordingly "
         + "based upon the type of Person selected.\n- Select \"File\" -> \"Open\" to open and load a list of Persons into the application.\n"
         + "- Select \"File\" -> \"Save As\" to write the current list of Persons to a file.\n- To edit a Person, select a name from the dropdown, "
         + "click \"Edit\" and make the necessary changes, then click \"Save\". \n    *Note: govID and studID values cannot be edited once set.");
    }
  }
}