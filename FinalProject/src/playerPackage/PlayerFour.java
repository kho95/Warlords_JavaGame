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

public class PlayerFour extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean playerGrab = false;
	public boolean playerTurned = true;
	public boolean playerDead = false;
	public boolean playerWon = false;
	
	private ImageIcon p4Paddle;
	private ImageIcon playerKing;
	
	public int score = 0;
	private int playerPosInd = 22;
	public int brickCount = 48;
	
	// List of X axis position Player 4 can go
	private int [] playerXPosList = {
		130, 140, 150, 160, 170, 180, 190, 200, 210, 220, 
		230, 240, 250, 260, 270, 280, 290, 300, 310, 320,
		330, 340, 350, 360, 370, 380, 390, 390, 390, 390, 
		390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 
		390, 390, 390, 390, 390, 390, 390, 390, 390, 390,
		390
	};
	// List of Y axis position Player 4 can go
	private int [] playerYPosList = {
		270, 270, 270, 270, 270, 270, 270, 270, 270, 270,
		270, 270, 270, 270, 270, 270, 270, 270, 270, 270,
		270, 270, 270, 270, 270, 270, 260, 250, 240, 230,
		220, 210, 200, 190, 180, 170, 160, 150, 140, 130,
		120, 110, 90, 80, 70, 60, 50, 40, 30, 20,
		10
	};
		
	public int playerXPos = playerXPosList[playerPosInd];
	public int playerYPos = playerYPosList[playerPosInd];
	public int playerKingX = 136;
	public int playerKingY = 9;
	
	public boolean aiEnabled;
	
	//Incrementing the number of active players when a player is initalized 
	public PlayerFour(boolean includeAI , GamePlayScreen game){
		game.playerActiveCount++;
		if(includeAI){
			aiEnabled = true;
		}else{
			aiEnabled = false;
		}
	}
	
	//Detecting if player four has won or not
	public boolean hasWon(){
		if (playerWon == false){
			return false;
		}else{
			return true;
		}
	}
		
	// Renders Player 4
	public void render(Graphics g){
		// Player4 scores
		g.setColor(Color.white);
		g.setFont(new Font("serif", Font.BOLD, 20));
		g.drawString("PLAYER 4", 6, 175);
		g.setFont(new Font("serif", Font.BOLD, 20));
		g.drawString(""+score, 54, 195);
		
		if(!playerDead){
			// Player 4 paddle
			if(playerPosInd>25){
				playerTurned = true;
			}else{
				playerTurned = false;
			}
			
			if(playerTurned && playerGrab){
				p4Paddle = new ImageIcon("assets/images/p4padTC.png");
				p4Paddle.paintIcon(this, g, playerXPos, playerYPos);
			}
			else if(playerTurned && !playerGrab){
				p4Paddle = new ImageIcon("assets/images/p4padTO.png");
				p4Paddle.paintIcon(this, g, playerXPos, playerYPos);
			}
			else if(!playerTurned && playerGrab){
				p4Paddle = new ImageIcon("assets/images/p4padC.png");
				p4Paddle.paintIcon(this, g, playerXPos, playerYPos);
			}
			else if(!playerTurned && !playerGrab){
				p4Paddle = new ImageIcon("assets/images/p4padO.png");
				p4Paddle.paintIcon(this, g, playerXPos, playerYPos);
			}
			
			//Setting King image at designated position 
			playerKing = new ImageIcon("assets/images/Player4King.png");
			playerKing.paintIcon(this, g, playerKingX, playerKingY);
		}
	}
	
	// Function to tell see if Player 4 is still alive
	public boolean lifeStatus(){
		if (!playerDead){
			return true;
		}else{
			return false;
		}
	}
	
	// Function to indicate player 4 has Died
	public boolean isDead(boolean status){
		if(status == true){
			playerDead = true;
		}else{
			playerDead = false;
		}
		return playerDead;
	}

	// Function reset the position and the life status of player 4
	public void reset() {
		isDead(false);
		playerWon = false;
		
		score = 0;
		brickCount = 48;
		
		playerPosInd = 22;
		playerXPos = playerXPosList[playerPosInd];
		playerYPos = playerYPosList[playerPosInd];
	}
	
	public void AIcontrol(GameBall ball){
		if(aiEnabled){
			// RIGHT
			if ((ball.ballposX > 330)&&(ball.ballposY < 270)){
				if (ball.ballposY > playerYPos){
					playerPosInd--;
					if(playerPosInd < 0){
						playerPosInd = 0;
					}
					playerXPos = playerXPosList[playerPosInd];
					playerYPos = playerYPosList[playerPosInd];
				}
				else if (ball.ballposY < playerYPos){
					playerPosInd++;
					if(playerPosInd > 50){
						playerPosInd = 48;
					}
					playerXPos = playerXPosList[playerPosInd];
					playerYPos = playerYPosList[playerPosInd];
				}
			}
			// BOTTOM
			else if ((ball.ballposX < 330)&&(ball.ballposY > 270)){
				if (ball.ballposX > playerXPos){
					playerPosInd++;
					if(playerPosInd > 50){
						playerPosInd = 50;
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
			else if ((ball.ballposX < 330) && (ball.ballposY > 270)){
				if (playerPosInd>22){
					playerPosInd--;
					playerXPos = playerXPosList[playerPosInd];
					playerYPos = playerYPosList[playerPosInd];
				}
				else if (playerPosInd<22){
					playerPosInd++;
					playerXPos = playerXPosList[playerPosInd];
					playerYPos = playerYPosList[playerPosInd];
				}
			}
		}
	}
	
	// Key controls for player 4
	public void keyControl(KeyEvent e){
		if(e.getKeyCode() == KeyEvent.VK_Y){
			if(playerGrab == true){
				playerGrab = false;
			}else{
				playerGrab = true;
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_J){
			playerPosInd = playerPosInd+3;
			if(playerPosInd > 50){
				playerPosInd = 50;
			}
			
			playerXPos = playerXPosList[playerPosInd];
			playerYPos = playerYPosList[playerPosInd];
		}
		if(e.getKeyCode() == KeyEvent.VK_G){
			playerPosInd = playerPosInd-3;
			if(playerPosInd < 0){
				playerPosInd = 0;
			}
			
			playerXPos = playerXPosList[playerPosInd];
			playerYPos = playerYPosList[playerPosInd];
		}
	}
	
	//Checking if the ball has intersected with the player's king
	public void checkBallIntersectKing(Rectangle ballObject, Rectangle kingObject, GameBall ball, GamePlayScreen game) {
		if(lifeStatus() && ballObject.intersects(kingObject)){
			ball.ballYdir = -ball.ballYdir;
			game.playerActiveCount--;
			isDead(true);
			game.kingDead.play();
			game.player4GameBallActive = true;
			game.player4GameBall = new GameBall(148, 100, 4);
			game.player4GameBall.play();
		}
	}
}
