// Daniel Fijalka
// OCCC - Fall 2024
// Advanced Java
// Unit 8 Homework
// Final Project - PersonGUI

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
  JTextField dob;
  JTextField govID;
  JTextField studID;

  // Labels for the text fields
  JLabel labelFirstName;
  JLabel labelLastName;
  JLabel labelDOB;
  JLabel labelGovID;
  JLabel labelStudID;

  // Buttons
  JButton buttonAdd;
  JButton buttonEdit;
  JButton buttonDelete;
  JButton buttonCancel;

  // Global variables, some used to test state
  File selectedFile;
  boolean editFlag = false;
  boolean changedData = false;
  boolean fileNameSet = false;
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
        // Check changedData flag, true signals user has made some changes
        if (changedData) {
          int userChoice = JOptionPane.showConfirmDialog(PersonGUI.this, "Save first?", "Save before exit", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
          if (userChoice == JOptionPane.YES_OPTION) {
            saveBehavior();  // Save
	    exitPersonGUI();
          }
          if (userChoice == JOptionPane.NO_OPTION) {
	    exitPersonGUI();  // Exit without saving
          }
        }
        else {
	  exitPersonGUI();
        }
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
    // Set a prototype display value to limit size of displayed combo box items
    personMenu.setPrototypeDisplayValue("FirstName LastName DOB GovID StudID");  

    // Combo box goes inside a panel with FlowLayout
    JPanel comboBoxPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    comboBoxPanel.add(personMenu);
 
    // Another panel for the labels and fields with GridLayout
    JPanel fieldsPanel = new JPanel(new GridLayout(5, 2));

    // Create labels and text fields
    JPanel firstNamePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    labelFirstName = new JLabel("First Name:");
    firstName = new JTextField(10);
    firstNamePanel.add(labelFirstName);
    firstNamePanel.add(firstName);

    JPanel lastNamePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    labelLastName = new JLabel("Last Name:");
    lastName = new JTextField(10);
    lastNamePanel.add(labelLastName);
    lastNamePanel.add(lastName);
   
    JPanel dobPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    labelDOB = new JLabel("Date of birth (MM/DD/YYYY):");
    dob = new JTextField(10);
    dobPanel.add(labelDOB);
    dobPanel.add(dob);

    JPanel govIDPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    labelGovID = new JLabel("Gov ID:");
    govID = new JTextField(10);
    govIDPanel.add(labelGovID);
    govIDPanel.add(govID);

    JPanel studIDPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    labelStudID = new JLabel("Stud ID:");
    studID = new JTextField(10);
    studIDPanel.add(labelStudID);
    studIDPanel.add(studID);

    // Add to fieldsPanel
    fieldsPanel.add(firstNamePanel);
    fieldsPanel.add(lastNamePanel);
    fieldsPanel.add(dobPanel);
    fieldsPanel.add(govIDPanel);
    fieldsPanel.add(studIDPanel);
  
    // Add fields and comboBox panels to bottom layer
    mainPanel.add(comboBoxPanel, BorderLayout.WEST);
    mainPanel.add(fieldsPanel, BorderLayout.EAST);

    // Button panel
    JPanel buttonPanel = new JPanel(new FlowLayout());
    buttonAdd = new JButton("Add");
    buttonEdit = new JButton("Edit");
    buttonDelete = new JButton("Delete");
    buttonCancel = new JButton("Cancel");
 
    // Add buttons to buttonPanel
    buttonPanel.add(buttonAdd);
    buttonPanel.add(buttonEdit);
    buttonPanel.add(buttonDelete);
    buttonPanel.add(buttonCancel);

    // Add buttonPanel to mainPanel and position mainPanel
    mainPanel.add(buttonPanel, BorderLayout.SOUTH);
    getContentPane().add(mainPanel, BorderLayout.CENTER);  

    // Add action listeners to buttons
    buttonAdd.addActionListener(this);
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
    dob.setText("");
    govID.setText("");
    studID.setText("");
  }

  // Method to adjust components when adding new Person
  public void newPersonAdjustComponents() {
    fileMenu_save.setEnabled(false);
    fileMenu_saveAs.setEnabled(false);
    buttonAdd.setEnabled(true);
    buttonEdit.setEnabled(false); 
    buttonDelete.setEnabled(false);
    personMenu.setEnabled(false);  
    clearFields();  

    // Enable text fields
    firstName.setEditable(true);
    lastName.setEditable(true);
    dob.setEditable(true);
    studID.setEditable(true);
    govID.setEditable(true);
  }

  // Method to 'reset' component settings
  public void resetComponents() {
      personMenu.setEnabled(true);
      firstName.setEditable(false);
      lastName.setEditable(false);
      dob.setEditable(false);
      studID.setEditable(false);
      govID.setEditable(false);
      buttonAdd.setEnabled(false);
      buttonEdit.setEnabled(true);
      buttonDelete.setEnabled(true);
      fileMenu_save.setEnabled(true);
      fileMenu_saveAs.setEnabled(true);
      clearFields();
  }

  // Method that saves current Persons
  public void saveBehavior() {
    if (fileNameSet) {  // Flag for checking if user has already saved data to file, if true overwrite with current Persons
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
    else {
      saveAsBehavior();  // User has not saved any data yet, use the SaveAs feature for user to set file name and save 
    }
  }

  // Method called from user saving for the first time or any "Save As" clicks from file menu
  public void saveAsBehavior() {
    JFileChooser fileChooser = new JFileChooser();
    int returnVal = fileChooser.showSaveDialog(this);

    if (returnVal == JFileChooser.APPROVE_OPTION) {
      selectedFile = fileChooser.getSelectedFile();
      try {
        FileOutputStream fout = new FileOutputStream(selectedFile);
        ObjectOutputStream oout = new ObjectOutputStream(fout);

        for (int i = 0; i < personList.size(); i++) {
          oout.writeObject(personList.get(i));
        }
        fileNameSet = true;
      }
      catch (Exception ex) {
        System.out.println(ex.toString());  // Display message to user via command prompt or popup if error encountered
        JOptionPane.showMessageDialog(this, "Error saving file!", "Error", JOptionPane.ERROR_MESSAGE);  
      }
    }
  }
 
  @Override
  public void actionPerformed(ActionEvent e){
    if ( e.getSource() == fileMenu_open ) {
      // Implement JFileChooser, info found in Java API
      JFileChooser fileChooser = new JFileChooser();
      int returnVal = fileChooser.showOpenDialog(this);

      if (returnVal == JFileChooser.APPROVE_OPTION) {
        selectedFile = fileChooser.getSelectedFile();
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
          fileNameSet = true;
        }
        catch (Exception ex) {
          System.out.println(ex.toString());  // Display message to user via command prompt or popup if error encountered
          JOptionPane.showMessageDialog(this, "Error loading file!", "Error", JOptionPane.ERROR_MESSAGE);  
        }
      }
    }

    if ( e.getSource() == buttonAdd ) {
      // Check if user has entered first name, last name, or DOB
      if (firstName.getText().isEmpty() || lastName.getText().isEmpty() || dob.getText().isEmpty()) { 
        JOptionPane.showMessageDialog(this, "Unable to add, missing first name, last name, or date of birth!", "Error", JOptionPane.ERROR_MESSAGE);
        return;
      }

      // Get values stored in fields 
      String fName = firstName.getText();
      String lName = lastName.getText();
      String dateOfBirth = dob.getText();
      String governmentID = govID.getText();
      String studentID = studID.getText();
      
      try {
        // Obtain day, month, year to create OCCCDate object
        int indexOfFirstSlash = dateOfBirth.indexOf('/');
        int indexOfSecondSlash = dateOfBirth.indexOf('/', indexOfFirstSlash + 1);
         
        // Signal user if DOB was entered without slashes
        if (indexOfFirstSlash == -1 || indexOfSecondSlash == -1) {
          JOptionPane.showMessageDialog(this, "Please enter date of birth with format MM/DD/YYYY.", "Error", JOptionPane.ERROR_MESSAGE);
          return;
        }

        int month = Integer.parseInt(dateOfBirth.substring(0, indexOfFirstSlash));
        int day = Integer.parseInt(dateOfBirth.substring(indexOfFirstSlash + 1, indexOfSecondSlash));
        int year = Integer.parseInt(dateOfBirth.substring(indexOfSecondSlash + 1));
        OCCCDate d = new OCCCDate(day, month, year);
        d.setDayName(OCCCDate.HIDE_DAY_NAME);

        // Logic if user clicks 'Add' after editing a Person
        if (editFlag) {
          Person selectedPerson = personList.get(editIdx);
          selectedPerson.setFirstName(fName);
          selectedPerson.setLastName(lName);
          JOptionPane.showMessageDialog(this, "Edits successfully saved!");
          changedData = true;
        }
        else {  // Add was clicked after adding new Person info
          Person newPerson = null; 

          // Check what info is present in boxes to determine the type of new Person added
          if (!govID.isEditable() && !studID.isEditable()) {
            newPerson = new Person(fName, lName, d); 
          }
          else if (!studID.isEditable()) {
            if (govID.getText().isEmpty()) { 
              JOptionPane.showMessageDialog(this, "Unable to add, missing Gov ID!", "Error", JOptionPane.ERROR_MESSAGE);
              return;
            }
            else {
              newPerson = new RegisteredPerson(fName, lName, d, governmentID);
            }
          }
          else {
            if (govID.getText().isEmpty() || studID.getText().isEmpty()) { 
              JOptionPane.showMessageDialog(this, "Unable to add, missing Gov ID or Stud ID!", "Error", JOptionPane.ERROR_MESSAGE);
              return;
            }
            else {
              newPerson = new OCCCPerson(fName, lName, d, governmentID, studentID);
            }
          }

          // Add new Person to list and udpate dropdown
          personList.add(newPerson);
          personMenu.addItem(newPerson.toString());
          JOptionPane.showMessageDialog(this, "New " + newPerson.getClass().getName() + " successfully added!");
          changedData = true;
        }

        // Reset components
        resetComponents();
        updatePersonMenu();
        clearFields();
      }
      catch (InvalidOCCCDateException id) {
        JOptionPane.showMessageDialog(this, id.getMessage(), "Invalid Date!", JOptionPane.ERROR_MESSAGE);
        // Clear date field so user can try again
        dob.setText("");
        dateOfBirth = "";
      }
    }
 
    if ( e.getSource() == fileMenu_save ) {
      saveBehavior();
    } 

    if ( e.getSource() == fileMenu_saveAs ){
      saveAsBehavior();
    }

    if ( e.getSource() == fileMenu_exit ){
      exitPersonGUI();
    }

    if ( e.getSource() == newMenu_newPerson ) {
      newPersonAdjustComponents();
      firstName.setEditable(true); 
      lastName.setEditable(true);
      dob.setEditable(true);
      govID.setEditable(false);  // No govID or studID for Person
      studID.setEditable(false);
    }

    if ( e.getSource() == newMenu_newRegisteredPerson ) {
      newPersonAdjustComponents();
      firstName.setEditable(true); 
      lastName.setEditable(true);
      dob.setEditable(true);
      govID.setEditable(true);
      studID.setEditable(false);  // No studID for RegisteredPerson
    }

    if ( e.getSource() == newMenu_newOCCCPerson ) {
      newPersonAdjustComponents();
      firstName.setEditable(true); 
      lastName.setEditable(true);
      dob.setEditable(true);
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

        changedData = true;
 
        // Set component display for editing
        personMenu.setEnabled(false);
        firstName.setEditable(true);
        lastName.setEditable(true);
        dob.setEditable(false);
        buttonAdd.setEnabled(true);  
        fileMenu_save.setEnabled(false);
        fileMenu_saveAs.setEnabled(false);
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
        changedData = true;
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
        dob.setText(selectedPerson.getDOB().toString());
        
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
         + "based upon the type of Person selected. The date of birth must be entered in MM/DD/YYYY format.\n- Select \"File\" -> \"Open\" to open and load a list of Persons "
         + "into the application.\n- Select \"File\" -> \"Save\" to save the current list of Persons, if no saves have been completed the user is prompted with a file chooser"
         + " to save.\n- Select \"File\" -> \"Save As\" to write the current list of Persons to a file.\n- To edit a Person, select a name from the dropdown, "
         + "click \"Edit\" and make the necessary changes, then click \"Save\". \n    *Note: Date of birth, govID, and studID values cannot be edited once set.");
    }
  }
}