// Daniel Fijalka
// OCCC - Fall 2024
// Advanced Java
// Unit 5 Homework
// CurrencyConverter Widget

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;

public class DF_CurrencyConverter extends JPanel implements ActionListener {
  // Define font sizes
  Font appFontLarge = new Font("Arial", Font.PLAIN, 24);
  Font appFontSmall = new Font("Arial", Font.PLAIN, 18);

  // Buttons
  JButton btnCalculate = new JButton("Calculate!");
  JButton btnClear = new JButton("Clear");

  // Combo boxes
  JComboBox<String> fromMenu;
  JComboBox<String> toMenu;

  // Lists to be used in combo boxes
  String [] fromList;
  String [] toList;

  // List to hold exchange rate amounts
  double [][] rateList = new double[][] { {1.00, 0.92, 152.95, 0.77, 1.52}, {1.09, 1.00, 166.43, 0.84, 1.66}, {0.0065, 0.0060, 1.00, 0.0051, 0.0099}, {1.29, 1.19, 197.84, 1.00, 1.97}, {0.66, 0.60, 100.55, 0.51, 1.00} };

  // Frame components
  JTextField enterField;
  JLabel resultLabel;

  // Constructor
  public DF_CurrencyConverter() {
    this.setLayout(new GridLayout(3,1));

    // Create top panel
    JPanel topPanel = new JPanel();
    topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

    JLabel fromLabel = new JLabel("From");
    fromList = new String[] { "US Dollar (USD)", "Euro (EUR)", "Japanese yen (JPY)", "Pound sterling (GBP)", "Australian dollar (AUD)" };
    fromMenu = new JComboBox<String>(fromList);
    fromLabel.setFont(appFontSmall);
    fromMenu.setFont(appFontSmall);

    JLabel toLabel = new JLabel("to");
    toList = new String[] { "US Dollar (USD)", "Euro (EUR)", "Japanese yen (JPY)", "Pound sterling (GBP)", "Australian dollar (AUD)" };
    toMenu = new JComboBox<String>(toList);
    toLabel.setFont(appFontSmall);
    toMenu.setFont(appFontSmall);

    topPanel.add(fromLabel);
    topPanel.add(fromMenu);
    topPanel.add(toLabel);
    topPanel.add(toMenu);     

    // Create middle panel
    JPanel middlePanel = new JPanel();
    middlePanel.setLayout(new FlowLayout(FlowLayout.CENTER));

    JLabel enterLabel = new JLabel("Enter amount to convert:");
    enterLabel.setFont(appFontSmall);
    enterField = new JTextField(10);
    enterField.setFont(appFontSmall);
    btnCalculate.setFont(appFontSmall);
    btnClear.setFont(appFontSmall);

    middlePanel.add(enterLabel);
    middlePanel.add(enterField); 
    middlePanel.add(btnCalculate);
    middlePanel.add(btnClear);

    // Create bottom panel
    JPanel bottomPanel = new JPanel();
    bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

    resultLabel = new JLabel();
    resultLabel.setFont(appFontLarge);
    bottomPanel.add(resultLabel);

    // Add panels to frame
    add(topPanel);
    add(middlePanel);
    add(bottomPanel);

    // Add action listeners
    btnCalculate.addActionListener(this);
    btnClear.addActionListener(this);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == btnCalculate) {
      try {
        double userAmount = Double.parseDouble(enterField.getText());
 
        int fromIdx = fromMenu.getSelectedIndex();
        int toIdx = toMenu.getSelectedIndex();

        double result = userAmount * rateList[fromIdx][toIdx];
        resultLabel.setText("Result: " + String.format("%.2f", result));
      }
      catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Error with calculation!", "Error", JOptionPane.ERROR_MESSAGE);
        System.out.println(ex.toString());
      }
    }
    if (e.getSource() == btnClear) {
      enterField.setText("");
      resultLabel.setText("");
    }
  }
}