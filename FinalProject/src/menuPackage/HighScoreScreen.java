package menuPackage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

//Implementing high score screen
public class HighScoreScreen extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ImageIcon highScores;
	private ImageIcon highScoreBackground;
	private ImageIcon backButton;
	private ImageIcon playerOne;
	private ImageIcon playerTwo;
	private ImageIcon playerThree;
	private ImageIcon playerFour;
	
	
	public FileReader fileReader = new FileReader();
	ArrayList<String> highScoreList = new ArrayList<String>();
	
	//Rendering the high score screen 
	public void render(Graphics g) {		
		highScoreBackground = new ImageIcon("assets/images/menuBackground.png");
		highScoreBackground.paintIcon(this, g, 0, 0);	
		
		highScores = new ImageIcon("assets/textImages/Leaderboard.png");
		highScores.paintIcon(this, g, 330, 50);
		
		playerOne = new ImageIcon("assets/textImages/Rank1.png");
		playerOne.paintIcon(this, g, 250, 200);
		
		playerTwo = new ImageIcon("assets/textImages/Rank2.png");
		playerTwo.paintIcon(this, g, 250, 260);
		
		playerThree = new ImageIcon("assets/textImages/Rank3.png");
		playerThree.paintIcon(this, g, 250, 320);
		
		playerFour = new ImageIcon("assets/textImages/Rank4.png");
		playerFour.paintIcon(this, g, 250, 380);
		
		
		//Setting font for top score number values
		g.setColor(Color.WHITE);
		g.setFont(new Font("Digiface", Font.BOLD, 30));
		fileReader.openFile();
		highScoreList = fileReader.readFile();
		
	//Displaying high scores that have been read from the FileReader class 	
		g.drawString(highScoreList.get(0), 600, 245);
		g.drawString(highScoreList.get(1), 600, 310);
		g.drawString(highScoreList.get(2), 600, 370);
		g.drawString(highScoreList.get(3), 600, 430);
		
		backButton = new ImageIcon("assets/textImages/backButton.png");
		backButton.paintIcon(this, g, 446, 600);
		
		// Dispose rendered screen
		g.dispose();
	}
}
