package playerPackage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import gamePackage.GameBall;
import menuPackage.GamePlayScreen;

public class PlayerThree extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean playerGrab = false;
	public boolean playerTurned = true;
	public boolean playerDead = false;
	public boolean playerWon = false;
	
	private ImageIcon p3Paddle;
	private ImageIcon playerKing;
	
	public int score = 0;
	public int playerPosInd = 26;
	public int brickCount = 48;
	
	// List of X axis position Player 3 can go
	private int [] playerXPosList = {
		600, 600, 600, 600, 600, 600, 600, 600, 600, 600,
		600, 600, 600, 600, 600, 600, 600, 600, 600, 600, 
		600, 600, 600, 600, 600, 600, 610, 620, 630, 640, 
		650, 660, 670, 680, 690, 700, 710, 720, 730, 740, 
		750, 760, 770, 780, 790, 800, 810, 820, 830
	};
	// List of X axis position Player 3 can go
	private int [] playerYPosList = {
		10, 20, 30, 40, 50, 60, 70, 80, 90, 100,
		110, 120, 130, 140, 150, 160, 170, 180, 190, 200,
		210, 220, 230, 240, 250, 260, 270, 270, 270 ,270,
		270, 270, 270 ,270, 270, 270, 270, 270 ,270, 270,
		270, 270, 270 ,270, 270, 270, 270, 270, 270
	};
	
	public int playerXPos = playerXPosList[playerPosInd];
	public int playerYPos = playerYPosList[playerPosInd];
	public int playerKingX = 800;
	public int playerKingY = 9;
	
	public boolean aiEnabled;
	
	//Incrementing the number of active players when a player is initalized 
	public PlayerThree(boolean includeAI, GamePlayScreen game){
		game.playerActiveCount++;
		if(includeAI){
			aiEnabled = true;
		}else{
			aiEnabled = false;
		}
	}
	
	//Detecting if player three has won or not
	public boolean hasWon(){
		if (playerWon == false){
			return false;
		}else{
			return true;
		}
	}
	
	//Rendering the graphics of playerThree
	public void render(Graphics g){
		
		g.setColor(Color.white);
		g.setFont(new Font("serif", Font.BOLD, 20));
		g.drawString("PLAYER 3", 905, 175);
		g.setColor(Color.white);
		g.setFont(new Font("serif", Font.BOLD, 20));
		g.drawString(""+score, 950, 195);
		
		if(!playerDead){
			if(playerPosInd>25){
				playerTurned = true;
			}else{
				playerTurned = false;
			}
			
			if(!playerTurned && playerGrab){
				p3Paddle = new ImageIcon("assets/images/p3padTC.png");
				p3Paddle.paintIcon(this, g, playerXPos, playerYPos);
			}
			else if(!playerTurned && !playerGrab){
				p3Paddle = new ImageIcon("assets/images/p3padTO.png");
				p3Paddle.paintIcon(this, g, playerXPos, playerYPos);
			}
			else if(playerTurned && playerGrab){
				p3Paddle = new ImageIcon("assets/images/p3padC.png");
				p3Paddle.paintIcon(this, g, playerXPos, playerYPos);
			}
			else if(playerTurned && !playerGrab){
				p3Paddle = new ImageIcon("assets/images/p3padO.png");
				p3Paddle.paintIcon(this, g, playerXPos, playerYPos);
			}
			
			//Setting king image for player three
			playerKing = new ImageIcon("assets/images/Player3King.png");
			playerKing.paintIcon(this, g, playerKingX, playerKingY);
		}
	}
	
	//Checking if player 3 is dead or not
	public boolean lifeStatus(){
		if (!playerDead){
			return true;
		}else{
			return false;
		}
	}
	
	//Setting player three to either dead or alive depending on boolean returned from lifeStatus
	public boolean isDead(boolean status){
		if(status == true){
			playerDead = true;
		}else{
			playerDead = false;
		}
		return playerDead;
	}
	
	// Function reset the position and the life status of player 3
	public void reset() {
		isDead(false);
		playerWon = false;
		
		score = 0;
		brickCount = 48;
		
		playerPosInd = 26;
		playerXPos = playerXPosList[playerPosInd];
		playerYPos = playerYPosList[playerPosInd];
	}
	
	public void AIcontrol(GameBall ball){
		if (aiEnabled){
			// LEFT
			if ((ball.ballposX < 600)&&(ball.ballposY < 250)){
				if (ball.ballposY > playerYPos){
					playerPosInd++;
					if(playerPosInd > 48){
						playerPosInd = 48;
					}
					playerXPos = playerXPosList[playerPosInd];
					playerYPos = playerYPosList[playerPosInd];
				}
				else if (ball.ballposY < playerYPos){
					playerPosInd--;
					if(playerPosInd < 0){
						playerPosInd = 0;
					}
					playerXPos = playerXPosList[playerPosInd];
					playerYPos = playerYPosList[playerPosInd];
				}
			}
			// BOTTOM
			else if ((ball.ballposX > 600)&&(ball.ballposY > 250)){
				if (ball.ballposX > playerXPos){
					playerPosInd++;
					if(playerPosInd > 48){
						playerPosInd = 48;
					}
					playerXPos = playerXPosList[playerPosInd];
					playerYPos = playerYPosList[playerPosInd];
				}
				else if (ball.ballposX < playerXPos){
					playerPosInd--;
					if(playerPosInd < 0){
						playerPosInd = 0;
					}
					playerXPos = playerXPosList[playerPosInd];
					playerYPos = playerYPosList[playerPosInd];
				}
			}
			//OTHER
			else if ((ball.ballposX < 600) && (ball.ballposY > 250)){
				if (playerPosInd>25){
					playerPosInd--;
					playerXPos = playerXPosList[playerPosInd];
					playerYPos = playerYPosList[playerPosInd];
				}
				else if (playerPosInd<25){
					playerPosInd++;
					playerXPos = playerXPosList[playerPosInd];
					playerYPos = playerYPosList[playerPosInd];
				}
			}
		}
	}
	
	//Player three keys
	public void keyControl(KeyEvent e){
		if(aiEnabled == false){
			if(e.getKeyCode() == KeyEvent.VK_O){
				if(playerGrab == true){
					playerGrab = false;
				}else{
					playerGrab = true;
				}
			}
			if(e.getKeyCode() == KeyEvent.VK_SEMICOLON){
				playerPosInd = playerPosInd+3;
				if(playerPosInd > 48){
					playerPosInd = 48;
				}
				
				playerXPos = playerXPosList[playerPosInd];
				playerYPos = playerYPosList[playerPosInd];
			}
			if(e.getKeyCode() == KeyEvent.VK_K){
				playerPosInd = playerPosInd-3;
				if(playerPosInd < 0){
					playerPosInd = 0;
				}
				
				playerXPos = playerXPosList[playerPosInd];
				playerYPos = playerYPosList[playerPosInd];
			}
		}
	}
	//Checking if the ball has intersected with the player's king
	public void checkBallIntersectKing(Rectangle ballObject, Rectangle kingObject, GameBall ball, GamePlayScreen game) {
		if(lifeStatus() && ballObject.intersects(kingObject)){
			ball.ballYdir = -ball.ballYdir;
			game.playerActiveCount--;
			isDead(true);
			game.kingDead.play();
			game.player3GameBallActive = true;
			game.player3GameBall = new GameBall(796, 80, 3);
			game.player3GameBall.play();
		}
	}
}
