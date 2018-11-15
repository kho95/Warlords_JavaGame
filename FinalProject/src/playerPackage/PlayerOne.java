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

public class PlayerOne extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean playerGrab = false;
	public boolean playerTurned = false;
	public boolean playerDead = false;
	public boolean playerWon = false;
	
	private ImageIcon p1Paddle;
	private ImageIcon playerKing;
	
	public int score = 0;
	public static int playerPosInd = 22;
	public int brickCount = 47;
	
	// List of X axis position Player 1 can go
	private int [] playerXPosList = {
		130, 140, 150, 160, 170, 180, 190, 200, 210, 220, 
		230, 240, 250, 260, 270, 280, 290, 300, 310, 320,
		330, 340, 350, 360, 370, 380, 390, 390, 390, 390,
		390, 390, 390, 390, 390, 390, 390, 390, 390, 390,
		390, 390, 390, 390, 390, 390, 390
	};
	// List of Y axis position Player 1 can go
	private int [] playerYPosList = {
		470, 470, 470, 470, 470, 470, 470, 470, 470, 470,
		470, 470, 470, 470, 470, 470, 470, 470, 470, 470,
		470, 470, 470, 470, 470, 470, 480, 490, 500, 510,
		520, 530, 540, 550, 560, 570, 580, 590, 600, 610, 
		620, 630, 640 ,650, 660, 670, 680
	};
	
	public int playerXPos = playerXPosList[playerPosInd];
	public int playerYPos = playerYPosList[playerPosInd];
	public int playerKingX = 145;
	public int playerKingY = 665;
	
	public boolean aiEnabled;
	
	//Incrementing the number of active players when a player is initalized 
	public PlayerOne(boolean includeAI, GamePlayScreen game){
		game.playerActiveCount++;
		if(includeAI){
			aiEnabled = true;
		}else{
			aiEnabled = false;
		}
	}
	
	//Detecting if player one has won or not
	public boolean hasWon(){
		if (playerWon == false){
			return false;
		}else{
			return true;
		}
	}

	// Renders Player 1
	public void render(Graphics g){
		// Player1 scores
		g.setColor(Color.white);
		g.setFont(new Font("serif", Font.BOLD, 20));
		g.drawString("PLAYER 1", 6, 700);
		g.setColor(Color.white);
		g.setFont(new Font("serif", Font.BOLD, 20));
		g.drawString(""+score, 54, 720);
		
		if(!playerDead){
			if(playerPosInd>24){
				playerTurned = true;
			}else{
				playerTurned = false;
			}
			
			if(playerTurned && playerGrab){
				p1Paddle = new ImageIcon("assets/images/p1padTC.png");
				p1Paddle.paintIcon(this, g, playerXPos, playerYPos);
			}
			else if(playerTurned && !playerGrab){
				p1Paddle = new ImageIcon("assets/images/p1padTO.png");
				p1Paddle.paintIcon(this, g, playerXPos, playerYPos);
			}
			else if(!playerTurned && playerGrab){
				p1Paddle = new ImageIcon("assets/images/p1padC.png");
				p1Paddle.paintIcon(this, g, playerXPos, playerYPos);
			}
			else if(!playerTurned && !playerGrab){
				p1Paddle = new ImageIcon("assets/images/p1padO.png");
				p1Paddle.paintIcon(this, g, playerXPos, playerYPos);
			}
			
			//Player 1 King
			playerKing = new ImageIcon("assets/images/Player1King.png");
			playerKing.paintIcon(this, g, playerKingX-10, playerKingY-10);
		}
	}
	
	// Function to tell see if Player 1 is still alive
	public boolean lifeStatus(){
		if (!playerDead){
			return true;
		}else{
			return false;
		}
	}
	
	// Function to indicate player 1 has Died
	public boolean isDead(boolean status){
		if(status == true){
			playerDead = true;
		}else{
			playerDead = false;
		}
		return playerDead;
	}
	
	// Function reset the position and the life status of player 1
	public void reset(){
		playerWon = false;
		isDead(false);
		
		score = 0;
		brickCount = 48;
		
		playerPosInd = 22;
		playerXPos = playerXPosList[playerPosInd];
		playerYPos = playerYPosList[playerPosInd];
	}
	
	public void AIcontrol(GameBall ball){
		if (aiEnabled){
			// RIGHT
			if ((ball.ballposX > 390)&&(ball.ballposY > 440)){
				if (ball.ballposY > playerYPos){
					playerPosInd++;
					if(playerPosInd > 46){
						playerPosInd = 46;
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
			// TOP
			else if ((ball.ballposX < 390)&&(ball.ballposY < 440)){
				if (ball.ballposX > playerXPos){
					playerPosInd++;
					if(playerPosInd > 46){
						playerPosInd = 46;
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
			else if ((ball.ballposX > 390) && (ball.ballposY < 440)){
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
	
	// Key controls for player 1
	public void keyControl(KeyEvent e){
		if(aiEnabled == false){
			if(e.getKeyCode() == KeyEvent.VK_UP){
				if(playerGrab == true){
					playerGrab = false;
				}else{
					playerGrab = true;
				}
			}
			if(e.getKeyCode() == KeyEvent.VK_RIGHT){
				playerPosInd = playerPosInd+3;
				if(playerPosInd > 46){
					playerPosInd = 46;
				}
				playerXPos = playerXPosList[playerPosInd];
				playerYPos = playerYPosList[playerPosInd];
			}
			if(e.getKeyCode() == KeyEvent.VK_LEFT){
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
			game.player1GameBallActive = true;
			game.player1GameBall = new GameBall(160, 700, 1);
			game.player1GameBall.play();
		}
	}
}
