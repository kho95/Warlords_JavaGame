package menuPackage;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class MenuScreenRender extends JPanel{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public int [] pointerYPosList = {406, 476, 546, 616, 686};
	public int [] pointerXPosList = {320, 339, 347, 407, 423};
	public int pointerPosInd = 0;
	public int pointerXPos = pointerXPosList[pointerPosInd];
	public int pointerYPos = pointerYPosList[pointerPosInd];
	
	private ImageIcon menuBackground;
	private ImageIcon menuTitle;
	private ImageIcon menuPointer;
	private ImageIcon menuSinglePlayer;
	private ImageIcon menuMultiplayer;
	private ImageIcon menuHighScore;
	private ImageIcon menuDemo;
	private ImageIcon menuQuit;
	
	//Going upwards through the menu screen
	public void movePointerUp(){
		pointerPosInd--;
		if (pointerPosInd <= 0){
			pointerPosInd = 0;
		}
		pointerXPos = pointerXPosList[pointerPosInd];
		pointerYPos = pointerYPosList[pointerPosInd];
	}
	//Going downwards through the menu screen
	public void movePointerDown(){
		pointerPosInd++;
		if (pointerPosInd >= 4){
			pointerPosInd = 4;
		}
		pointerXPos = pointerXPosList[pointerPosInd];
		pointerYPos = pointerYPosList[pointerPosInd];
	}
	
	// Renders the menu screen with images
	public void render (Graphics g){
		menuBackground = new ImageIcon("assets/images/menuBackground.png");
		menuBackground.paintIcon(this, g, 0, 0);
		
		menuTitle = new ImageIcon("assets/textImages/titleWarlords.png");
		menuTitle.paintIcon(this, g, 166, 100);
		
		menuPointer = new ImageIcon("assets/images/ball.png");
		menuPointer.paintIcon(this, g, pointerXPos, pointerYPos);
		
		menuSinglePlayer = new ImageIcon("assets/textImages/titleSinglePlayer.png");
		menuSinglePlayer.paintIcon(this, g, 353, 380);
		
		menuMultiplayer = new ImageIcon("assets/textImages/titleMultiplayer.png");
		menuMultiplayer.paintIcon(this, g, 372, 450);
		
		menuHighScore = new ImageIcon("assets/textImages/titleHighScore.png");
		menuHighScore.paintIcon(this, g, 380, 520);
		
		menuDemo = new ImageIcon("assets/textImages/titleDemo.png");
		menuDemo.paintIcon(this, g, 440, 590);
		
		menuQuit = new ImageIcon("assets/textImages/titleQuit.png");
		menuQuit.paintIcon(this, g, 456, 660);
	}
}