package menuPackage;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.util.ArrayList;

public class Fonts {
	// Create a list just incase more than one new font needs to be added
	private static ArrayList<Fonts> fontList = new ArrayList<Fonts>();
	
	public Fonts(){
		registerFont();
	}
	
	private void registerFont(){
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		
		try{
			// Add the font into the run time of the game
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("assets/fonts/Digiface Regular.ttf")));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void addFont(Fonts font){
		fontList.add(font);
	}
}
