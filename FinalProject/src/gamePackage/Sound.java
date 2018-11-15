package gamePackage;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.URI;

import javax.swing.JPanel;

public class Sound extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private AudioClip clip;
	
	//Finding the designated file name
	public Sound(String filename){
		//Trying to find music file and converting it.
		try{
			//Using Uniform Resources Identifier 
			//Defining app root directory
			File appDir = new File(System.getProperty("user.dir"));
			//Path to sound file
			URI uri = new URI(appDir.toURI() + filename);
			//Creating found sound file into an object
			clip = Applet.newAudioClip(uri.toURL());
		}catch(Exception e){
			System.out.println(e);
			e.printStackTrace();
		}
	}
	
	// Playing the sound and using thread to output sounds concurrently
	public void play(){
		try{
			new Thread(){
				public void run(){
					clip.play();
				}
			}
			.start();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}	
}
