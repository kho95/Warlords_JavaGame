package menuPackage;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import menuPackage.Gameplay.MULTIPLAYER;
import menuPackage.Gameplay.STATE;

public class MouseMovement implements MouseListener {
	
	@Override
	public void mouseClicked(MouseEvent e) {}

	//Detecting mouse click by taking in x and y position of place clicked
	public void mousePressed(MouseEvent e){
			int xPoint = e.getX();
			int yPoint = e.getY();
			
		if (Gameplay.currentState == STATE.MENU){
			if ((xPoint >= 353 && xPoint <= 697) && (yPoint >= 380 && yPoint <= 460)){
				Gameplay.multiplayerState = MULTIPLAYER.SINGLEPLAYER;
				Gameplay.gamePlayScreen = new GamePlayScreen();
				Gameplay.currentState = STATE.GAMEPLAY;
			}
			//If multiplayer is clicked then will go to multiplayer menu screen
			else if ((xPoint >= 372 && xPoint <= 729) && (yPoint >= 450 && yPoint <= 530)){
				Gameplay.multiplayerScreen = new MultiplayerScreenRender();
				Gameplay.currentState = STATE.MULTIPLAYER;
				
				xPoint = 0;
				yPoint = 0;
			}
			//If Highscore is clicked then it will direct us to the highscore screen
			else if ((xPoint >= 380 && xPoint <= 641) && (yPoint >= 531 && yPoint <=590)){
				Gameplay.highScoreScreen = new HighScoreScreen();
				Gameplay.currentState = STATE.HIGHSCORE;
			}
			else if ((xPoint >= 440 && xPoint <= 510) && (yPoint >= 591 && yPoint <=650)){
				Gameplay.multiplayerState = MULTIPLAYER.DEMO;
				Gameplay.gamePlayScreen = new GamePlayScreen();
				Gameplay.currentState = STATE.GAMEPLAY;
			}
			else if ((xPoint >= 456 && xPoint <= 568) && (yPoint >= 660 && yPoint <= 700)){
				System.exit(1);		
			}
		}
		//Clicking within Multiplayer Screen
		if (Gameplay.currentState == STATE.MULTIPLAYER){
			//If two player mode is clicked
			if ((xPoint >= 350 && xPoint <= 677) && (yPoint >= 250 && yPoint <= 350)){
				Gameplay.multiplayerState = MULTIPLAYER.MULTIPLAYERTWO;
				Gameplay.gamePlayScreen = new GamePlayScreen();
				Gameplay.currentState = STATE.GAMEPLAY;
			//If three player mode is clicked
			}
			else if ((xPoint >= 330 && xPoint <= 798) && (yPoint >= 350 && yPoint <= 450)){
				Gameplay.multiplayerState = MULTIPLAYER.MULTIPLAYERTHREE;
				Gameplay.gamePlayScreen = new GamePlayScreen();
				Gameplay.currentState = STATE.GAMEPLAY;
			}
			//If four player mode is clicked
			else if ((xPoint >= 350 && xPoint <= 728) && (yPoint >= 450 && yPoint <= 550)){
				Gameplay.multiplayerState = MULTIPLAYER.MULTIPLAYERFOUR;
				Gameplay.gamePlayScreen = new GamePlayScreen();
				Gameplay.currentState = STATE.GAMEPLAY;
			}
		}
	}
	
	//Unused mouse functions
	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

}
