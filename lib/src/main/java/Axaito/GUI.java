// Import package from Axaito and built-in libraries 
package Axaito;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import javax.sound.sampled.*;


public class GUI {
	JFrame myWindow = new JFrame("Axaito - AI Personal Assistant");
	JLabel axaitoLabel = new JLabel("Axaito - AI Personal Assistant", SwingConstants.CENTER);
    JLabel taskLabel = new JLabel("How may i help you?", SwingConstants.CENTER); // Set default task label to "How may i help you?" label, position lebel to center
    JPanel topPanel = new JPanel(); // Declare top panel section GUI JPanel for Axaito.AI logo
    JPanel middlePanel = new JPanel(); // Declare middle panel section GUI JPanel for application name
	JPanel bottomPanel = new JPanel(); // Declare bottom panel section GUI JPanel for task label
	ImageIcon axaitoImage = new ImageIcon("Axaito.AI Logo-01.png");
	ImageIcon axaitoIcon = new ImageIcon("axaito_icon.png");
	JLabel imgLabel = new JLabel(axaitoImage);
	
    
	
	public GUI() throws UnsupportedAudioFileException, IOException, LineUnavailableException {	
		// Declare file and import audio track 1
	    File track_1 = new File("Hello sir, how may I help you_.wav"); 
		
	    // Open audio clip for track 1 and lower volume gain by 20dB
		AudioInputStream auStream_track1 = AudioSystem.getAudioInputStream(track_1);
		Clip clip_track1 = AudioSystem.getClip();
		clip_track1.open(auStream_track1);
		FloatControl gainControlVol1 = (FloatControl) clip_track1.getControl(FloatControl.Type.MASTER_GAIN);
		gainControlVol1.setValue(-15.0f);
		
		
		topPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 10)); // set spacing for top, left, bottom and right without border for top panel.
		topPanel.setLayout(new GridLayout(1, 1)); // set grid layout with 1 row and 1 column
		topPanel.setBackground(Color.orange); // set orange background color for top panel

		middlePanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 10));
		middlePanel.setLayout(new GridLayout(1, 1));
		middlePanel.setBackground(Color.black);
					
		bottomPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 50, 10));
		bottomPanel.setLayout(new GridLayout(1, 1));
		bottomPanel.setBackground(Color.white); // set white background color for bottom panel
		
		axaitoLabel.setFont(new Font("Roboto", Font.BOLD, 30)); // set font type for axaito label to "Roboto" with bold and size 30
		axaitoLabel.setForeground(Color.white); // set font color to white
		taskLabel.setFont(new Font("Roboto", Font.BOLD, 59));
		taskLabel.setForeground(Color.black); // set font color to black
		
		topPanel.add(imgLabel); // add image to top panel
		middlePanel.add(axaitoLabel); // add axaito label for middle panel
	    bottomPanel.add(taskLabel); // add task label to bottom panel
	    clip_track1.start(); // start clip audio track 1 after task label loaded
	    
	    myWindow.add(topPanel, BorderLayout.NORTH); // Set position to top panel in frame at the very top
	    myWindow.add(middlePanel, BorderLayout.CENTER);  // Set position to middle panel in frame at the center
	    myWindow.add(bottomPanel, BorderLayout.PAGE_END); // Set position to bottom panel in frame at the very bottom
	    myWindow.setSize(1000, 700); // Set default frame size once opened
	    myWindow.setIconImage(axaitoIcon.getImage()); // Set icon image for application
	    myWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    myWindow.setVisible(true); // Display frame
	}
	
	// Method to set label default with voice command
	public void setLabel(String voiceCommand) {
		this.taskLabel.setText(voiceCommand);
	}
}