package menuPackage;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class MultiplayerScreenRender extends JPanel{
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	
	//Declearing positions for navigating through Multiplayer menu screen
	public int [] pointerYPosList = {326, 396, 466, 626};
	public int [] pointerXPosList = {347, 327, 343, 413};
	public int pointerPosInd = 0;
	public int pointerXPos = pointerXPosList[pointerPosInd];
	public int pointerYPos = pointerYPosList[pointerPosInd];
	
	//Declaring Images to be used for rendering of multiplayer screen
	private ImageIcon menuBackground;
	private ImageIcon multiplayerTitle;
	private ImageIcon menuPointer;
	private ImageIcon twoPlayer;
	private ImageIcon threePlayer;
	private ImageIcon fourPlayer;
	private ImageIcon backButton;
	
	//Navigating up through the multiplayer menu 
	public void movePointerUp(){
		pointerPosInd--;
		if (pointerPosInd <= 0){
			pointerPosInd = 0;
		}
		pointerXPos = pointerXPosList[pointerPosInd];
		pointerYPos = pointerYPosList[pointerPosInd];
	}
	//Navigating down through the multiplayer menu
	public void movePointerDown(){
		pointerPosInd++;
		if (pointerPosInd >= 3){
			pointerPosInd = 3;
		}
		pointerXPos = pointerXPosList[pointerPosInd];
		pointerYPos = pointerYPosList[pointerPosInd];
	}
	
	//Rendering Multiplayer screen
	public void render(Graphics g) {
		menuBackground = new ImageIcon("assets/images/menuBackground.png");
		menuBackground.paintIcon(this, g, 0, 0);
		
		multiplayerTitle = new ImageIcon("assets/textImages/titleMultiplayer.png");
		multiplayerTitle.paintIcon(this, g, 372, 140);
		
		menuPointer = new ImageIcon("assets/images/ball.png");
		menuPointer.paintIcon(this, g, pointerXPos, pointerYPos);
		
		twoPlayer = new ImageIcon("assets/textImages/TwoPlayerLogo.png");
		twoPlayer.paintIcon(this, g, 380, 300);
		
		threePlayer = new ImageIcon("assets/textImages/ThreePlayerLogo.png");
		threePlayer.paintIcon(this, g, 360, 370);
		
		fourPlayer = new ImageIcon("assets/textImages/FourPlayerLogo.png");
		fourPlayer.paintIcon(this, g, 376, 440);
		
		backButton = new ImageIcon("assets/textImages/backButton.png");
		backButton.paintIcon(this, g, 446, 600);
	}

}
