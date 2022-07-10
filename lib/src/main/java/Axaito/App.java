// Import package, predefined and external library
package Axaito;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class App {
	
	
	public static void main(String [] args) throws URISyntaxException, UnsupportedAudioFileException, IOException, LineUnavailableException {
		Configuration config = new Configuration(); // Declare Configuration object class
		config.setSampleRate(16000); // set audio sample rate for speech recognizer to 16kHz
		
		config.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us"); // set default acoustic model path to English-US language
		config.setDictionaryPath("file:3864.dic"); // set dictionary path file
		config.setLanguageModelPath("file:3864.lm"); // set language model path file

		System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
		Voice voice = VoiceManager.getInstance().getVoice("kevin16"); // Declare and initialize Voice text-to-speech object class with Kevin voice (16kHz) 
		if (voice != null) {
            voice.allocate(); //Allocating Voice
        }
     
        voice.setRate(160); //Setting the rate of the voice
        voice.setPitch(150); //Setting the Pitch of the voice
        voice.setVolume(2); //Setting the volume of the voice
  
		
		GUI gui = new GUI(); // Declare graphical user interface GUI object class
				
		
		// Declare, open and reduce volume for Google sound effect audio wav file
		File track_2 = new File("Ok Google Sound Effect.wav"); 
		AudioInputStream auStream_track2 = AudioSystem.getAudioInputStream(track_2);
		Clip clip_track2 = AudioSystem.getClip();
		clip_track2.open(auStream_track2);
		FloatControl gainControlVol2 = (FloatControl) clip_track2.getControl(FloatControl.Type.MASTER_GAIN);
		gainControlVol2.setValue(-20.0f); // Reduce volume gain by 20 decibels (dB)
		
		// Declare, open and reduce volume for Rick Astley - Never Gonna Give You Up audio wav file
		File track_3 = new File("Rick Astley - Never Gonna Give You Up (Official Music Video).wav"); 
		AudioInputStream auStream_track3 = AudioSystem.getAudioInputStream(track_3);
		Clip clip_track3 = AudioSystem.getClip();
		clip_track3.open(auStream_track3);
		FloatControl gainControlVol3 = (FloatControl) clip_track3.getControl(FloatControl.Type.MASTER_GAIN);
		gainControlVol3.setValue(-20.0f); // Reduce volume gain by 20 decibels (dB)
		
		// Declare, open and reduce volume for The Avengers soundtrack audio wav file
		File track_4 = new File("The Avengers Soundtrack - The Avengers.wav"); 
		AudioInputStream auStream_track4 = AudioSystem.getAudioInputStream(track_4);
		Clip clip_track4 = AudioSystem.getClip();
		clip_track4.open(auStream_track4);
		FloatControl gainControlVol4 = (FloatControl) clip_track4.getControl(FloatControl.Type.MASTER_GAIN);
		gainControlVol4.setValue(-20.0f); // Reduce volume gain by 20 decibels (dB)
		
		// Declare, open and reduce volume for One Republic - Stop and Stare audio wav file
		File track_5 = new File("One Republic Lyrics - Stop and Stare.wav"); 
		AudioInputStream auStream_track5 = AudioSystem.getAudioInputStream(track_5);
		Clip clip_track5 = AudioSystem.getClip();
		clip_track5.open(auStream_track5);
		FloatControl gainControlVol5 = (FloatControl) clip_track5.getControl(FloatControl.Type.MASTER_GAIN);
		gainControlVol5.setValue(-20.0f); // Reduce volume gain by 20 decibels (dB)		
		
		try {
			LiveSpeechRecognizer speech = new LiveSpeechRecognizer(config); // Declare LiveSpeechRecognizer object class from Sphinx4
			speech.startRecognition(true); // Call start recognition method, enabling microphone
			Desktop desk = Desktop.getDesktop(); // Declare Desktop object class to open browser
      
			SpeechResult speechResult = null;
			
		
			while((speechResult = speech.getResult()) != null) { // Check if there is speech of words detected from user during recognition
				
				clip_track2.setMicrosecondPosition(0); //Reset sound clip duration
				clip_track2.start(); // Start Google sound effect audio for each recognition
				String voiceCommand = speechResult.getHypothesis();	// Assign speech recognition result into voiceCommand
				System.out.println("Voice command is " + voiceCommand);	// Output voiceCommand in console
				
				if(voiceCommand.equalsIgnoreCase("Open Chrome")) { // Check if voiceCommand is equal to "OPEN CHROME"
					voice.speak(voiceCommand); // Start speak using Text-to-Speech voice with voiceCommand
					gui.setLabel(voiceCommand); // Replace taskLabel from GUI to voiceCommand label
					Runtime.getRuntime().exec("cmd.exe /c start chrome");
				} else if(voiceCommand.equalsIgnoreCase("Close Chrome")) { // Check if voiceCommand is equal to "CLOSE CHROME"
					voice.speak(voiceCommand);
					gui.setLabel(voiceCommand); // Replace taskLabel from GUI to voiceCommand label
					Runtime.getRuntime().exec("taskkill /F /IM chrome* /T");
				} else if(voiceCommand.equalsIgnoreCase("Open Photoshop")) { // Check if voiceCommand is equal to "OPEN PHOTOSHOP"
					voice.speak(voiceCommand);
					gui.setLabel(voiceCommand); // Replace taskLabel from GUI to voiceCommand label
					Runtime.getRuntime().exec("cmd.exe /c start photoshop");
				} else if(voiceCommand.equalsIgnoreCase("Close Photoshop")) { // Check if voiceCommand is equal to "CLOSE PHOTOSHOP"
					voice.speak(voiceCommand);
					gui.setLabel(voiceCommand); // Replace taskLabel from GUI to voiceCommand label
					Runtime.getRuntime().exec("taskkill /F /IM photoshop* /T");
				} else if(voiceCommand.equalsIgnoreCase("Open Illustrator")) { // Check if voiceCommand is equal to "OPEN ILLUSTRATOR"
					voice.speak(voiceCommand);
					gui.setLabel(voiceCommand); // Replace taskLabel from GUI to voiceCommand label
					Runtime.getRuntime().exec("cmd.exe /c start illustrator");
				} else if(voiceCommand.equalsIgnoreCase("Close Illustrator")) { // Check if voiceCommand is equal to "CLOSE ILLUSTRATOR"
					voice.speak(voiceCommand);
					gui.setLabel(voiceCommand); // Replace taskLabel from GUI to voiceCommand label
					Runtime.getRuntime().exec("taskkill /F /IM illustrator* /T"); 
				} else if(voiceCommand.equalsIgnoreCase("Open Youtube")) { // Check if voiceCommand is equal to "OPEN YOUTUBE"
					voice.speak(voiceCommand);
					gui.setLabel(voiceCommand); // Replace taskLabel from GUI to voiceCommand label
					URI uri = new URI("https://www.youtube.com/");
					desk.browse(uri); // Open default dekstop browser with the URL
				} else if(voiceCommand.equalsIgnoreCase("Open Facebook")) { // Check if voiceCommand is equal to "OPEN FACEBOOK"
					voice.speak(voiceCommand);
					gui.setLabel(voiceCommand); // Replace taskLabel from GUI to voiceCommand label
					URI uri = new URI("https://www.facebook.com/");
					desk.browse(uri); // Open default dekstop browser with the URL
				} else if(voiceCommand.equalsIgnoreCase("Open Gmail")) { // Check if voiceCommand is equal to "OPEN GMAIL"
					voice.speak(voiceCommand);
					gui.setLabel(voiceCommand); // Replace taskLabel from GUI to voiceCommand label
					URI uri = new URI("https://www.gmail.com/");
					desk.browse(uri); // Open default dekstop browser with the URL
				} else if(voiceCommand.equalsIgnoreCase("Open Github")) { // Check if voiceCommand is equal to "OPEN GITHUB"
					voice.speak(voiceCommand);
					gui.setLabel(voiceCommand); // Replace taskLabel from GUI to voiceCommand label
					URI uri = new URI("https://www.github.com/");
					desk.browse(uri); // Open default dekstop browser with the URL
				} else if(voiceCommand.contains("SEARCH")) { // Check if voiceCommand has SEARCH keyword in the string
					voice.speak(voiceCommand);
					gui.setLabel(voiceCommand); // Replace taskLabel from GUI to voiceCommand label
					String searchContent = voiceCommand.substring(voiceCommand.indexOf("SEARCH")); // Trim any string before "SEARCH"
					if(searchContent.length() >= 7) { // Check if searchContent length of string is more than or equal to 7, need to have keyword after SEARCH
						System.out.println("Search is " + searchContent.substring(7)); // Display search content result in console
						URI uri = new URI(String.format("https://www.google.com/search?q=%s", searchContent.substring(7).replaceAll(" ", "+"))); // If search content keywords has more than one words, then include "+" symbol after each word in URL
						desk.browse(uri); // Open default dekstop browser with the URL
					}					
				} else if(voiceCommand.equalsIgnoreCase("Play pop song")) {
					voice.speak(voiceCommand);
					gui.setLabel(voiceCommand); // Replace taskLabel from GUI to voiceCommand label
					
					// Stop audio track 4, 5 and 6 and start track 3
					clip_track4.stop();
					clip_track5.stop();
					clip_track3.setMicrosecondPosition(0); //Reset sound clip duration
					clip_track3.start();
				} else if(voiceCommand.equalsIgnoreCase("Play superhero song")) {
					voice.speak(voiceCommand);
					gui.setLabel(voiceCommand); // Replace taskLabel from GUI to voiceCommand label
					
					// Stop audio track 3, 5 and 6 and start track 4
					clip_track3.stop();
					clip_track5.stop();
					clip_track4.setMicrosecondPosition(0); //Reset sound clip duration
					clip_track4.start();
				} else if(voiceCommand.equalsIgnoreCase("Play stop and stare")) {
					voice.speak(voiceCommand);
					gui.setLabel(voiceCommand); // Replace taskLabel from GUI to voiceCommand label
					
					// Stop audio track 3, 4 and 6 and start track 5
					clip_track3.stop();
					clip_track4.stop();
					clip_track5.setMicrosecondPosition(0); //Reset sound clip duration
					clip_track5.start();
				} else if(voiceCommand.equalsIgnoreCase("Stop song")) {
					voice.speak(voiceCommand);
					gui.setLabel(voiceCommand); // Replace taskLabel from GUI to voiceCommand label
					
					// Stop all audio track
					clip_track3.stop();
					clip_track4.stop();
					clip_track5.stop();
				} 
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}