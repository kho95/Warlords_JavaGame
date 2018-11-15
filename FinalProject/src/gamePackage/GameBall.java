package gamePackage;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import menuPackage.GamePlayScreen;
import menuPackage.GamePlayScreen.Direction;
import menuPackage.GamePlayScreen.PLAYER;

public class GameBall extends JPanel{
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	private ImageIcon ballImage;
	
	//Initalising ball position
	public int ballposX = 502;
	public int ballposY = 374;
	
	//Initalising ball direction 
	public int ballXdir = 0;
	public int ballYdir = 0;
	
	// Randomise the direction of the ball
	public int [] dirList = {-1,1};
	Random rand = new Random();
	public int startXdir = dirList[rand.nextInt(2)];
	public int startYdir = dirList[rand.nextInt(2)];
	
	// Temporary ball direction used for to save the direction of the ball
	// when the game has paused.
	public int tempBallXdir = 0;
	public int tempBallYdir = 0;
	
	public boolean player1GameBallActive = false;
	public boolean player2GameBallActive = false;
	public boolean player3GameBallActive = false;
	public boolean player4GameBallActive = false;
	public boolean extraBallActive = false;
	public boolean extraBall2Active = false;
	
	public int playerNumber;
	
	public GameBall(int xPos, int yPos,int ballNumber){
		ballposX = xPos;
		ballposY = yPos;
		playerNumber = ballNumber;
	}
	
	// Reset ball position to original position
	public void reset(){
		ballposX = 512;
		ballposY = 384;
		ballXdir = startXdir;
		ballYdir = startYdir;
	}
	
	// Start the moving ball
	public void play(){
		if (tempBallXdir == 0){
			ballXdir = startXdir;
			ballYdir = startYdir;
		}else{
			ballXdir = tempBallXdir;
			ballYdir = tempBallYdir;
		}
	}
	
	// Stop the ball and save the direction of the ball
	public void stop(){
		tempBallXdir = ballXdir;
		tempBallYdir = ballYdir;
		ballXdir = 0;
		ballYdir = 0;
	}
	
	// Incrementation of ball movement along screen in both X direction and Y direction
	public void moveBall(){
		ballposX += ballXdir;
		ballposY += ballYdir;
	}
	
	// Rendering ball image from assets files
	public void render(Graphics g){
		if (playerNumber == 0){
			ballImage = new ImageIcon("assets/images/ball.png");
			ballImage.paintIcon(this, g, ballposX, ballposY);
		}
		else if (playerNumber == 1){
			ballImage = new ImageIcon("assets/images/player1ball.png");
			ballImage.paintIcon(this, g, ballposX, ballposY);
		}
		else if (playerNumber == 2){
			ballImage = new ImageIcon("assets/images/player2ball.png");
			ballImage.paintIcon(this, g, ballposX, ballposY);
		}
		else if (playerNumber == 3){
			ballImage = new ImageIcon("assets/images/player3ball.png");
			ballImage.paintIcon(this, g, ballposX, ballposY);
		}
		else if (playerNumber == 4){
			ballImage = new ImageIcon("assets/images/player4ball.png");
			ballImage.paintIcon(this, g, ballposX, ballposY);
		}
	}

	// Reflect the ball direction when it hits a paddle, play ball hit sound and save who hit
	// the ball last.
	public boolean reflectBallOffPaddle(PLAYER lastTouchPlayer, Direction direction, GamePlayScreen game){
		if (direction == Direction.XDir){
			game.paddleCollision.play();
			ballXdir = -ballXdir;
			game.lastTouchPlayer = lastTouchPlayer;
			return true;
		}
		else if (direction == Direction.YDir){
			game.paddleCollision.play();
			ballYdir = -ballYdir;
			game.lastTouchPlayer = lastTouchPlayer;
			return true;
		}
		return false;
	}
	
	// Making ball reflect off game maximum boundaries
	public void reflectBallOffWall(){
		if(ballposX < 128) {
			ballXdir = -ballXdir;
		}
		if(ballposY < 2) {
			ballYdir = -ballYdir;
		}
		if(ballposX > 870) {
			ballXdir = -ballXdir;
		}
		if(ballposY > 720) {
			ballYdir = -ballYdir;
		}
	}
}