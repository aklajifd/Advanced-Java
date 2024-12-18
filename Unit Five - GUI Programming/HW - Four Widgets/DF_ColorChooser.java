// Daniel Fijalka
// OCCC - Fall 2024
// Advanced Java
// Unit 5 Homework
// ColorChooser Widget

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.Vector;

public class DF_ColorChooser extends JPanel implements ActionListener, ChangeListener {
  // Container to store selected colors
  Vector<Color> colorList = new Vector<>();

  // Set panels
  JPanel topPanel = new JPanel();
  JPanel middlePanel = new JPanel();
  JPanel bottomPanel = new JPanel();

  // Set ColorChooser
  JColorChooser cc = new JColorChooser();

  // Buttons
  JButton bottomButton = new JButton("Done!");

  // Font settings
  Font appFontMedium = new Font("Arial", Font.PLAIN, 18);
  Font appFontSmall = new Font("Arial", Font.PLAIN, 14);

  // Constructor
  public DF_ColorChooser() { 
    this.setLayout(new BorderLayout());

    // Banner panel to go in topPanel
    JPanel bannerPanel = new JPanel();
    bannerPanel.setLayout(new BorderLayout());

    // New panel for each section of banner
    JPanel bannerUpperLabelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JLabel bannerUpperLabel = new JLabel("Welcome to the Color Chooser!");
    bannerUpperLabelPanel.add(bannerUpperLabel);

    JPanel bannerCenterLabelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JLabel bannerCenterLabel = new JLabel("Pick some of your favorite colors below.");
    bannerCenterLabelPanel.add(bannerCenterLabel);

    JPanel bannerLowerLabelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JLabel bannerLowerLabel = new JLabel("Click \"Done\" below to change the lower background color to a random color from your selections!");
    bannerLowerLabelPanel.add(bannerLowerLabel);

    // Set fonts
    bannerUpperLabel.setFont(appFontMedium);
    bannerCenterLabel.setFont(appFontMedium);
    bannerLowerLabel.setFont(appFontSmall);

    // Add labels to banner panel
    bannerPanel.add(bannerUpperLabelPanel, BorderLayout.NORTH);
    bannerPanel.add(bannerCenterLabelPanel, BorderLayout.CENTER);
    bannerPanel.add(bannerLowerLabelPanel, BorderLayout.SOUTH);

    // Add bannerPanel to topPanel
    topPanel.add(bannerPanel);

    // Add the ColorChooser to middlePanel
    middlePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
    middlePanel.add(cc);
    
    // Set layout of bottomPanel and add components
    bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
    JLabel bottomLabel = new JLabel("Click the button when finished:");
    bottomLabel.setFont(appFontMedium);
    bottomPanel.add(bottomLabel);
    bottomPanel.add(bottomButton);

    // Add panels to "bottom layer" layout
    add(topPanel, BorderLayout.NORTH);
    add(middlePanel, BorderLayout.CENTER);
    add(bottomPanel, BorderLayout.SOUTH);

    // Add change and action listeners
    cc.getSelectionModel().addChangeListener(this);
    bottomButton.addActionListener(this);
  }

  @Override
  public void stateChanged(ChangeEvent e) {
    Color selectedColor = cc.getColor();
    colorList.add(selectedColor);  // Add user's chosen color to list
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    int vSize = colorList.size();
    if (e.getSource() == bottomButton && vSize > 0) {
      int randomColorPosition = (int)(Math.random() * vSize);  // Get position of a random color
      Color randomColor = colorList.get(randomColorPosition);
      bottomPanel.setBackground(randomColor);  // Set background of bottom panel to the random color
    }
    else if (vSize == 0) {
      JOptionPane.showMessageDialog(this, "No colors selected! Please choose at least one color.");
    }
  }
}