// John Goulden
// CardLayout demo
// with student JPanel widgets

// Original version OCCC Spring 2015
// This     version OCCC Spring 2021

// CardLayout example based on code found at
// http://www.java-tips.org/java-se-tips/java.awt/how-to-use-awt-cardlayout.html

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.util.Vector;

public class CardLayoutDemo extends JFrame {
    
    private int currentCard = 1;
    private JPanel cardPanel;
    private CardLayout cl;
    
    public CardLayoutDemo() {
        
        setTitle("Card Layout Example - Student JPanel Widgets");
        setSize(600, 600);
        cardPanel = new JPanel();
        cl = new CardLayout();
        cardPanel.setLayout(cl);

/////////////////////////////////////////////////////

        Vector<JPanel> jp  = new Vector<JPanel>();

////////////////////////////////////////////////////

        // SAMPLE

        // jp.add(new JDG_MusicPlayer());
        // jp.add(new JDG_EmployeeHourlyPayDemo());
        // jp.add(new JDG_ISSFinder());
        // jp.add(new JDG_GoogleMapsDemo());

///////////////////////////////////////////////////////

        // ADD WIDGETS HERE
        jp.add(new DF_CurrencyConverter());
        jp.add(new DF_ScreenSaver());
        jp.add(new DF_ColorChooser());
        jp.add(new DF_MusicPlayer());

///////////////////////////////////////////////////////

        for(int i = 0; i < jp.size(); i++){
           cardPanel.add(jp.get(i), (i+1)+"");
        }
 
//////////////////////////////////////////////////////

        JPanel buttonPanel = new JPanel();
        JButton firstBtn = new JButton("First");
        JButton nextBtn = new JButton("Next");
        JButton previousBtn = new JButton("Previous");
        JButton lastBtn = new JButton("Last");
        buttonPanel.add(firstBtn);
        buttonPanel.add(previousBtn);
        buttonPanel.add(nextBtn);
        buttonPanel.add(lastBtn);
        
        firstBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                cl.first(cardPanel);
                currentCard = 1;
            }
        });
        
        lastBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                cl.last(cardPanel);
                currentCard = jp.size();
            }
        });
        
        nextBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (currentCard < jp.size()) {
                    currentCard += 1;
                    cl.show(cardPanel, "" + (currentCard));
                }
            }
        });
        
        previousBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (currentCard > 1) {
                    currentCard -= 1;
                    cl.show(cardPanel, "" + (currentCard));
                }
            }
        });
        
        getContentPane().add(cardPanel, BorderLayout.NORTH);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        CardLayoutDemo cl = new CardLayoutDemo();
        cl.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cl.setVisible(true);
    }
}