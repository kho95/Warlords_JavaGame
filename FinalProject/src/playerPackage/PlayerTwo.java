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

public class PlayerTwo extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean playerGrab = false;
	public boolean playerTurned = true;
	public boolean playerDead = false;
	public boolean playerWon = false;
	
	private ImageIcon p2Paddle;
	private ImageIcon playerKing;
	
	public int score = 0;
	public int playerPosInd = 22;
	public int brickCount = 43;
	
	// List of X axis position Player 2 can go
	private int [] playerXPosList = {
		600, 600, 600, 600, 600, 600, 600,
		600, 600, 600, 600, 600, 600, 600, 600, 600, 600,
		600, 600, 600, 600, 610, 620, 630, 640, 650, 660, 
		670, 680, 690, 700, 710, 720, 730, 740, 750, 760, 
		770, 780, 790, 800, 810, 820, 830
	};
	// List of Y axis position Player 2 can go
	private int [] playerYPosList = {
		680, 670, 660, 650, 640, 630, 620, 
		610, 600, 590, 580, 570, 560, 550, 540, 530, 520, 
		510, 500, 490, 480, 470, 470, 470, 470, 470, 470, 
		470, 470, 470, 470, 470, 470, 470, 470, 470, 470,
		470, 470, 470, 470, 470, 470, 470
	};
		
	public int playerXPos = playerXPosList[playerPosInd];
	public int playerYPos = playerYPosList[playerPosInd];
	public int playerKingX = 800;
	public int playerKingY = 655;
	
	public boolean aiEnabled;
	
	//Incrementing the number of active players when a player is initalized 
	public PlayerTwo(boolean includeAI, GamePlayScreen game){
		game.playerActiveCount++;
		if(includeAI){
			aiEnabled = true;
		}else{
			aiEnabled = false;
		}
	}
	
	//Detecting if player two has won or not
	public boolean hasWon(){
		if (playerWon == false){
			return false;
		}else{
			return true;
		}
	}
	
	//Setting X position of the paddle
	public void setXPos(int XPos, boolean manual){
		if(manual == true){
			playerXPos = XPos;
		}else{

			playerXPos = playerXPosList[XPos];
		}
	}
	//Setting Y position of the paddle
	public void setYPos(int YPos, boolean manual){
		if(manual == true){
			playerYPos = YPos;
		}else{
			playerYPos = playerYPosList[YPos];
		}
	}
	
	// Renders Player 2
	public void render(Graphics g){
		// Player2 scores
		g.setColor(Color.white);
		g.setFont(new Font("serif", Font.BOLD, 20));
		g.drawString("PLAYER 2", 905, 700);
		g.setColor(Color.white);
		g.setFont(new Font("serif", Font.BOLD, 20));
		g.drawString(""+score, 950, 720);
		
		if(!playerDead){
			if(playerPosInd>21){
				playerTurned = true;
			}else{
				playerTurned = false;
			}
			
			if(playerTurned && playerGrab){
				p2Paddle = new ImageIcon("assets/images/p2padTC.png");
				p2Paddle.paintIcon(this, g, playerXPos, playerYPos);
			}
			else if(playerTurned && !playerGrab){
				p2Paddle = new ImageIcon("assets/images/p2padTO.png");
				p2Paddle.paintIcon(this, g, playerXPos, playerYPos);
			}
			else if(!playerTurned && playerGrab){
				p2Paddle = new ImageIcon("assets/images/p2padC.png");
				p2Paddle.paintIcon(this, g, playerXPos, playerYPos);
			}
			else if(!playerTurned && !playerGrab){
				p2Paddle = new ImageIcon("assets/images/p2padO.png");
				p2Paddle.paintIcon(this, g, playerXPos, playerYPos);
			}
			
			//Player 2 King
			playerKing = new ImageIcon("assets/images/Player2King.png");
			playerKing.paintIcon(this, g, playerKingX, playerKingY);
		}
	}

	// Function to tell see if Player 2 is still alive
	public boolean lifeStatus(){
		if (!playerDead){
			return true;
		}else{
			return false;
		}
	}
	
	// Function to indicate player 2 has Died
	public boolean isDead(boolean status){
		if(status == true){
			playerDead = true;
		}else{
			playerDead = false;
		}
		return playerDead;
	}

	// Function reset the position and the life status of player 2
	public void reset() {
		isDead(false);
		
		score = 0;
		brickCount = 48;
		
		playerPosInd = 23;
		playerXPos = playerXPosList[playerPosInd];
		playerYPos = playerYPosList[playerPosInd];
	}
	
	public void AIcontrol(GameBall ball){
		if (aiEnabled){
			// LEFT
			if ((ball.ballposX < 600)&&(ball.ballposY > 440)){
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
					if(playerPosInd > 43){
						playerPosInd = 43;
					}
					playerXPos = playerXPosList[playerPosInd];
					playerYPos = playerYPosList[playerPosInd];
				}
			}
			// TOP
			else if ((ball.ballposX > 600)&&(ball.ballposY < 440)){
				if (ball.ballposX > playerXPos){
					playerPosInd++;
					if(playerPosInd > 43){
						playerPosInd = 43;
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
			else if ((ball.ballposX < 600) && (ball.ballposY < 440)){
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
	
	// Key controls for player 2
	public void keyControl(KeyEvent e){
		if(aiEnabled == false){
			if(e.getKeyCode() == KeyEvent.VK_W){
				if(playerGrab == true){
					playerGrab = false;
				}else{
					playerGrab = true;
				}
			}
			if(e.getKeyCode() == KeyEvent.VK_D){
				playerPosInd = playerPosInd+3;
				if(playerPosInd > 43){
					playerPosInd = 43;
				}
				
				playerXPos = playerXPosList[playerPosInd];
				playerYPos = playerYPosList[playerPosInd];
			}
			if(e.getKeyCode() == KeyEvent.VK_A){
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
			game.player2GameBallActive = true;
			game.player2GameBall = new GameBall(796, 668, 2);
			game.player2GameBall.play();
		}
	}
}
