// Daniel Fijalka
// OCCC - Fall 2024
// Advanced Java
// Unit 5 Homework
// ScreenSaver Widget

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.Vector;

public class DF_ScreenSaver extends JPanel implements ActionListener {
  // Container that will hold images
  Vector<ImageIcon> imageList = new Vector<>();

  // Buttons
  JButton btnPrevious;
  JButton btnNext;
  JButton btnSelect;

  int imageIdx = 0;

  // Label for image
  JLabel imageLabel = new JLabel();

  // Font settings
  Font appFont = new Font("Palatino", Font.PLAIN, 18);

  // Constructor
  public DF_ScreenSaver() {
    setLayout(new BorderLayout());

    // Add label to top
    JPanel topInfo = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JLabel lblInfo = new JLabel("Select one of the photos below for your new screensaver!");
    lblInfo.setFont(appFont);
    topInfo.add(lblInfo);
 
    // Add previous button and panels
    JPanel outerPrevious = new JPanel();
    outerPrevious.setLayout(new BorderLayout());
    JPanel innerPrevious = new JPanel(new FlowLayout(FlowLayout.CENTER));
    btnPrevious = new JButton("Previous");
    innerPrevious.add(btnPrevious);
    outerPrevious.add(innerPrevious, BorderLayout.CENTER);

    // Add next button and panels
    JPanel outerNext = new JPanel();
    outerNext.setLayout(new BorderLayout());
    JPanel innerNext = new JPanel(new FlowLayout(FlowLayout.CENTER));
    btnNext = new JButton("Next");
    innerNext.add(btnNext);
    outerNext.add(innerNext, BorderLayout.CENTER);

    // Add select button and panels
    JPanel outerSelect = new JPanel();
    outerSelect.setLayout(new BorderLayout());
    JPanel innerSelect = new JPanel(new FlowLayout(FlowLayout.CENTER));
    btnSelect = new JButton("Select");
    innerSelect.add(btnSelect);
    outerSelect.add(innerSelect, BorderLayout.CENTER);

    // Add to panel 
    add(topInfo, BorderLayout.NORTH);
    add(outerPrevious, BorderLayout.WEST);
    add(outerNext, BorderLayout.EAST);
    add(outerSelect, BorderLayout.SOUTH);

    // Load images from the Media folder into imageList
    try {
      File dir = new File("DF_Media");  // Media folder
      File[] mediaFiles = dir.listFiles();  // Use listFiles method to get array of pathnames
      
      for (File file : mediaFiles) {
        if (file.getPath().contains(".jpg")) {  // Only add image files
          imageList.add(new ImageIcon(file.getPath()));  // Add image from path to list
        }
      }

      // Check if list is empty, which means no files were loaded
      if (!imageList.isEmpty()) {
        // If not empty, set image to label
        imageLabel.setIcon(imageList.get(imageIdx));
        add(imageLabel, BorderLayout.CENTER);
      }
      else {
        // Inform user there was issue 
        JOptionPane.showMessageDialog(this, "Error retrieving media!");
        imageLabel.setText("Image files not found");
        add(imageLabel, BorderLayout.CENTER);
      }   
    }
    catch (Exception e) {
      System.out.println(e.toString());
    }

    // Add action listeners
    btnPrevious.addActionListener(this);
    btnNext.addActionListener(this);
    btnSelect.addActionListener(this);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    int vSize = imageList.size();
    if (e.getSource() == btnPrevious && vSize > 0) {
      if (imageIdx > 0) {
        imageIdx--;  // Decrement only when imageIdx greater than 0
        imageLabel.setIcon(imageList.get(imageIdx));  // Set image to label
      }
    }
    if (e.getSource() == btnNext && vSize > 0) {
      if (imageIdx < vSize - 1) {
        imageIdx++;  // Increment only when imageIdx not in last position
        imageLabel.setIcon(imageList.get(imageIdx));  // Set image to label
      }
    }
    if (e.getSource() == btnSelect && vSize > 0) {
      removeAll();  // From Container class, remove all components from container
      revalidate();  // Hierarchy has to be validated to reflect container changes
      imageLabel.setIcon(imageList.get(imageIdx));  // Set image to label
      add(imageLabel, BorderLayout.CENTER);
      repaint();  // Repaints the component
    }
  }
}