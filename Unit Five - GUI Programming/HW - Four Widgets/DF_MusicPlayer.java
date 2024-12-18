// Daniel Fijalka
// OCCC - Fall 2024
// Advanced Java
// Unit 5 Homework
// MusicPlayer Widget

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;
import java.util.Vector;
import java.io.*;
import javax.sound.sampled.*;

public class DF_MusicPlayer extends JPanel implements ActionListener {
  // Font settings
  Font appFont = new Font("Palatino", Font.PLAIN, 18);

  // Buttons
  JButton btnPlayPause = new JButton("Play");
  JButton btnPrevious = new JButton("Previous");
  JButton btnNext = new JButton("Next");

  // Image icon
  ImageIcon cover;

  // List indexes
  int imageIdx = 0;
  int audioIdx = 0;

  // Used to hold the current position of the audio data
  long timeStamp = 0;

  // Flag for playing the music
  boolean audioPlay = false;

  // Objects used in audio methods
  AudioInputStream aStream;
  Clip audioClip;

  // Label for image
  JLabel imageLabel = new JLabel();

  // Image paths
  String imagePath1 = "DF_Media/tyler-van-der-hoeven-_ok8uVzL2gI-unsplash.jpg";
  String imagePath2 = "DF_Media/spacex-PIOgkhaF3WA-unsplash.jpg";
  String imagePath3 = "DF_Media/sebastian-unrau-sp-p7uuT0tw-unsplash.jpg";

  // Container to hold images
  String[] imageList = new String[]{ imagePath1, imagePath2, imagePath3 };

  // Container to hold audio files
  Vector<File> audioList = new Vector<>();

  // Constructor
  public DF_MusicPlayer() {
    setLayout(new BorderLayout());

    // Create panel for cover image
    JPanel coverPanel = new JPanel();
    coverPanel.setLayout(new BorderLayout());
    coverPanel.add(imageLabel, BorderLayout.CENTER);
    imageLabel.setIcon(new ImageIcon(imageList[imageIdx]));

    // Create button panel
    JPanel btnPanel = new JPanel();
    btnPanel.setLayout(new BorderLayout());
    JPanel innerBtnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

    // Add buttons to panel
    btnPrevious.setFont(appFont);
    btnNext.setFont(appFont);
    btnPlayPause.setFont(appFont);
    innerBtnPanel.add(btnPrevious);   
    innerBtnPanel.add(btnPlayPause);   
    innerBtnPanel.add(btnNext);
    btnPanel.add(innerBtnPanel, BorderLayout.CENTER);  

    // Add panels to frame
    add(coverPanel, BorderLayout.NORTH);
    add(btnPanel, BorderLayout.SOUTH);

    // Load audio from the Media folder into audioList
    try {
      File dir = new File("DF_Media");  // Media folder
      File[] audioFiles = dir.listFiles();  // Use listFiles method to get array of pathnames
      
      for (File f : audioFiles) {
        if (f.getPath().contains(".wav")) {  // Only add the audio files
          audioList.add(f);  // Add audio file to audioList
        }
      }  
    }
    catch (Exception ex) {
      JOptionPane.showMessageDialog(this, "Error retrieving media!");
      System.out.println(ex.toString());
    } 

    // Add action listeners
    btnPrevious.addActionListener(this);
    btnPlayPause.addActionListener(this);
    btnNext.addActionListener(this);
  }

  // Method to play the audio
  public void playAudio() {
    try {
      // Set the audioClip if null or closed 
      if (audioClip == null || !audioClip.isOpen()) {
        aStream = AudioSystem.getAudioInputStream(audioList.get(audioIdx));
        audioClip = AudioSystem.getClip();
        audioClip.open(aStream);
      }

      // Set timeStamp
      audioClip.setMicrosecondPosition(timeStamp);
      audioClip.start();
      btnPlayPause.setText("Pause");  // Switch button text to say "Pause"
      audioPlay = true;  // Set flag to true, music is playing
    }
    catch (Exception exc) {
      JOptionPane.showMessageDialog(this, "Error playing media!");
      System.out.println(exc.toString());
    }
  }
 
  // Method to pause the audio
  public void pauseAudio() {
    if (audioPlay) {  // Check the flag
      timeStamp = audioClip.getMicrosecondPosition();  // Update timestamp
      audioClip.stop();
      btnPlayPause.setText("Play");  // Switch button text to say "Play"
      audioPlay = false;  // Set flag to false, music is stopped
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    int vSize = audioList.size();
    if (e.getSource() == btnPrevious && vSize > 0) {
      if (imageIdx > 0 && audioIdx > 0) {
        audioClip.close();  // Close any open audioClip
        imageIdx--; 
        audioIdx--;
        timeStamp = 0;  // Reset timeStamp
        imageLabel.setIcon(new ImageIcon(imageList[imageIdx]));  // Update image
        playAudio();  // Play previous file in audioList
      }
    }
    if (e.getSource() == btnNext && vSize > 0) {
      if ((imageIdx < vSize - 1) && (audioIdx < vSize - 1)) {
        audioClip.close();  // Close any open audioClip
        imageIdx++; 
        audioIdx++;
        timeStamp = 0;  // Reset timeStamp
        imageLabel.setIcon(new ImageIcon(imageList[imageIdx]));  // Update image
        playAudio();  // Play next file in audioList
      }
    }
    if (e.getSource() == btnPlayPause && vSize > 0) {
      if (audioPlay) {  // Pause if flag is true
        pauseAudio();
      }
      else {
        playAudio();
      }
    }
  }
}