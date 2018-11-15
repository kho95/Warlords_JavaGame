package menuPackage;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import menuPackage.GamePlayScreen.GAMESTATE;


public class Gameplay extends JPanel implements KeyListener, ActionListener {

	private static final long serialVersionUID = 1L;
	
	//ENUM representations for game states
	public enum STATE{
		MENU,
		SINGLEPLAYER,
		TWOPLAYER,
		THREEPLAYER,
		FOURPLAYER,
		MULTIPLAYER,
		HIGHSCORE,
		GAMEPLAY,
	};
	
	//ENUM representing different multiplayer states with the MULTIPLAYER enums 
	public enum MULTIPLAYER{
		SINGLEPLAYER,
		MULTIPLAYERTWO,
		MULTIPLAYERTHREE,
		MULTIPLAYERFOUR, 
		DEMO
	}
	
	//Initalising different game menus
	public static HighScoreScreen highScoreScreen;
	public MenuScreenRender menuScreen;
	public static GamePlayScreen gamePlayScreen;
	public static MultiplayerScreenRender multiplayerScreen;

	
	//Making first screen to show up be the Menu screen
	public static STATE currentState = STATE.MENU;
	public static MULTIPLAYER multiplayerState = MULTIPLAYER.SINGLEPLAYER;
	

	public Gameplay(){
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		this.addMouseListener(new MouseMovement());

		menuScreen = new MenuScreenRender();
	}
	
	//Creating different screens based on the game state status represented by ENUM 
	public void paint(Graphics g){
		// If current game status is Menu, load menu screen
		if (currentState == STATE.MENU){
			menuScreen.render(g);
		}
		// If current game status is multiplayer, load multiplayer screen
		else if (currentState == STATE.MULTIPLAYER){
			multiplayerScreen.render(g);
		}
		// If current game status is highscore, load highscore screen
		else if (currentState == STATE.HIGHSCORE){
			highScoreScreen.render(g);
		}
		// If current game status is Gameplay, load the game
		else if (currentState == STATE.GAMEPLAY){
			gamePlayScreen.render(g);
		}
		// Dispose rendered screen
		g.dispose();
	}
	
	@Override
	//Unused Action Event
	public void actionPerformed(ActionEvent e) {}

	//Unused Key Event Function 
	@Override
	public void keyTyped(KeyEvent e) {}

	//Unused Key Event Function
	@Override
	public void keyPressed(KeyEvent e){}
	
	//Key Code for navigating through menu screen, changing game states accordingly to key pressed
	@Override
	public void keyReleased(KeyEvent e) {
		if (currentState == STATE.GAMEPLAY){
			gamePlayScreen.gamekeyPressed(e);
			
			if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
				if(gamePlayScreen.currentState == GAMESTATE.PAUSED || gamePlayScreen.currentState == GAMESTATE.END){
					currentState = STATE.MENU;
					gamePlayScreen = null;
				}
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_UP){
			if(currentState == STATE.MENU){
				menuScreen.movePointerUp();
			}
			else if(currentState == STATE.MULTIPLAYER){
				multiplayerScreen.movePointerUp();
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN){
			if(currentState == STATE.MENU){
				menuScreen.movePointerDown();
			}
			else if(currentState == STATE.MULTIPLAYER){
				multiplayerScreen.movePointerDown();
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_ENTER){
			if(currentState == STATE.MENU){
				if(menuScreen.pointerPosInd == 0){
					multiplayerState = MULTIPLAYER.SINGLEPLAYER;
					gamePlayScreen = new GamePlayScreen();
					currentState = STATE.GAMEPLAY;
				}
				else if(menuScreen.pointerPosInd == 1){
					multiplayerScreen = new MultiplayerScreenRender();
					currentState = STATE.MULTIPLAYER;
				}
				else if(menuScreen.pointerPosInd == 2){
					highScoreScreen = new HighScoreScreen();
					currentState = STATE.HIGHSCORE;
				}
				else if(menuScreen.pointerPosInd == 3){
					multiplayerState = MULTIPLAYER.DEMO;
					gamePlayScreen = new GamePlayScreen();
					currentState = STATE.GAMEPLAY;
				}
				else if(menuScreen.pointerPosInd == 4){
					System.exit(1);
				}
			}
			else if(currentState == STATE.MULTIPLAYER){
				if (multiplayerScreen.pointerPosInd == 0){
					multiplayerState = MULTIPLAYER.MULTIPLAYERTWO;
					gamePlayScreen = new GamePlayScreen();
					currentState = STATE.GAMEPLAY;
				}
				else if (multiplayerScreen.pointerPosInd == 1){
					multiplayerState = MULTIPLAYER.MULTIPLAYERTHREE;
					gamePlayScreen = new GamePlayScreen();
					currentState = STATE.GAMEPLAY;
				}
				else if (multiplayerScreen.pointerPosInd == 2){
					multiplayerState = MULTIPLAYER.MULTIPLAYERFOUR;
					gamePlayScreen = new GamePlayScreen();
					currentState = STATE.GAMEPLAY;
				}
				else if (multiplayerScreen.pointerPosInd == 3){
					currentState = STATE.MENU;
					multiplayerScreen = null;
				}
			}
			else if(currentState == STATE.HIGHSCORE){
				currentState = STATE.MENU;
				highScoreScreen = null;
			}
		}
		
	}

}