package menuPackage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import gamePackage.Sound;
import gamePackage.GameBall;
import gamePackage.WallGenerator;
import playerPackage.PlayerFour;
import playerPackage.PlayerOne;
import playerPackage.PlayerThree;
import playerPackage.PlayerTwo;

public class GamePlayScreen extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//An active player count increments when a new player is created and decrements when a player is dead
	public int playerActiveCount = 0;
	
	//Images to show up when the game is over
	private ImageIcon gameOvertitle;
	private ImageIcon gameOverPlayer;
	private ImageIcon gameOverRestart;
	private ImageIcon gameOverWinner;
	
	//Images to show for countdown 
	private ImageIcon gameBackground;
	private ImageIcon countDownTitle;
	private ImageIcon countDownNumber;
	
	//Image to show when the game is paused
	private ImageIcon paused;

	public Sound boingSound = new Sound("assets/sounds/boingnoise.wav");
	//public static BackgroundMusic backgroundMusic = new BackgroundMusic("Resources/8BIT.wav");
	public Sound kingDead = new Sound("assets/sounds/KingDead2.wav");
	public Sound paddleCollision = new Sound("assets/sounds/PaddleCollision2.wav");
	public Sound backGroundMusic = new Sound("assets/sounds/8BIT.wav");
	
	public static String BACKGROUND, SUCCESS;
	
	//enum states for GAMESTATES
	public enum GAMESTATE{
		START,
		PLAYING,
		PAUSED,
		END
	};
	
	public enum PLAYER{
		NULL,
		PLAYERONE,
		PLAYERTWO,
		PLAYERTHREE,
		PLAYERFOUR
	};
	
	//Direction of ball in X and Y Directions
	public enum Direction{
		XDir,
		YDir
	};
	
	//Initializing Game Play components of the game
	public PLAYER winner = PLAYER.NULL;
	public PLAYER lastTouchPlayer = PLAYER.NULL;
	public GAMESTATE currentState;
	
	public WallGenerator player1Wall;
	public WallGenerator player2Wall;
	public WallGenerator player3Wall;
	public WallGenerator player4Wall;
	
	public GameBall firstGameBall;
	public GameBall player1GameBall;
	public GameBall player2GameBall;
	public GameBall player3GameBall;
	public GameBall player4GameBall;
	public GameBall extraBall;
	public GameBall extraBall2;
	
	public boolean player1GameBallActive = false;
	public boolean player2GameBallActive = false;
	public boolean player3GameBallActive = false;
	public boolean player4GameBallActive = false;
	public boolean extraBallActive = false;
	public boolean extraBallActive2 = false;
	
	public PlayerOne playerOne;
	public PlayerTwo playerTwo;
	public PlayerThree playerThree;
	public PlayerFour playerFour;
	
	public int mostWallsRemaining = 0;
	
	private int aiTimer = 0;
	
	private Timer timer;
	private int delay = 2;
	
	// Initalising the Level time of each game
	public int gameTime = 120; //120 seconds = 2 minutes
	public int startTime = 4;
	public int countDown = 0;
	private long start = 0;
	
	// Creating game screen
	public GamePlayScreen(){
		currentState = GAMESTATE.START;
		makeWallobjects();
		initialisePlayers();
		firstGameBall = new GameBall(502, 374, 0);
		timer = new Timer(delay, this);
	}
	
	// Creating walls for players by calling wallGenerator function
	public void makeWallobjects(){
		player1Wall = new WallGenerator();
		player2Wall = new WallGenerator();
		player3Wall = new WallGenerator();
		player4Wall = new WallGenerator();
	}
	
	// Initalising players
	public void initialisePlayers(){
		if (Gameplay.multiplayerState == Gameplay.MULTIPLAYER.SINGLEPLAYER){
			playerOne = new PlayerOne(false, this);
			playerTwo = new PlayerTwo(true, this);
			playerThree = new PlayerThree(true, this);
			playerFour = new PlayerFour(true, this);
		}
		else if (Gameplay.multiplayerState == Gameplay.MULTIPLAYER.MULTIPLAYERTWO){
			playerOne = new PlayerOne(false, this);
			playerTwo = new PlayerTwo(false, this);
			playerThree = new PlayerThree(true, this);
			playerFour = new PlayerFour(true, this);
		}
		else if (Gameplay.multiplayerState == Gameplay.MULTIPLAYER.MULTIPLAYERTHREE){
			playerOne = new PlayerOne(false, this);
			playerTwo = new PlayerTwo(false, this);
			playerThree = new PlayerThree(false, this);
			playerFour = new PlayerFour(true, this);
		}
		else if (Gameplay.multiplayerState == Gameplay.MULTIPLAYER.MULTIPLAYERFOUR){
			playerOne = new PlayerOne(false, this);
			playerTwo = new PlayerTwo(false, this);
			playerThree = new PlayerThree(false, this);
			playerFour = new PlayerFour(false, this);
		}
		else if (Gameplay.multiplayerState == Gameplay.MULTIPLAYER.DEMO){
			playerOne = new PlayerOne(true, this);
			playerTwo = new PlayerTwo(true, this);
			playerThree = new PlayerThree(true, this);
			playerFour = new PlayerFour(true, this);
		}
	}
	
	// Decrementing game timer per second
	public void updateGameTime() {
	    if ((System.currentTimeMillis() - start >= 1000) && (currentState == GAMESTATE.PLAYING)) { 
	    	gameTime -= 1; //Deduct 1 second, every second.
	        start = System.currentTimeMillis();
	    }
	}
	
	// Decrementing game timer per second
	public void updateStartTime() {
	    if ((System.currentTimeMillis() - start >= 1000) && (currentState == GAMESTATE.START)) { 
	    	startTime -= 1; //Deduct 1 second, every second.
	        start = System.currentTimeMillis();
	    }
	}
	
	//Rendering graphics of gameplay screen
	public void render(Graphics g) {
		// IU background
		g.setColor(Color.gray);
		g.fillRect(0, 0, 1024, 768);
		
		// Field background
		gameBackground = new ImageIcon("assets/images/gameBorder2.png");
		gameBackground.paintIcon(this, g, 0, 0);
		
		// Draw map
		player1Wall.drawPlayer1Wall((Graphics2D)g);
		player2Wall.drawPlayer2Wall((Graphics2D)g);
		player3Wall.drawPlayer3Wall((Graphics2D)g);
		player4Wall.drawPlayer4Wall((Graphics2D)g);
		
		// Draw Players
		playerOne.render(g);
		playerTwo.render(g);
		playerThree.render(g);
		playerFour.render(g);
		
		// Drawing the ball based on whether a player has died or if the extra ball key has been pressed
		firstGameBall.render(g);
		if(player1GameBallActive == true){
			player1GameBall.render(g);
		}
		if(player2GameBallActive == true){
			player2GameBall.render(g);
		}
		if(player3GameBallActive == true){
			player3GameBall.render(g);
		}
		if(player4GameBallActive == true){
			player4GameBall.render(g);
		}
		if(extraBallActive == true){
			extraBall.render(g);
		}
		if(extraBallActive2 == true){
			extraBall2.render(g);
		}
		
		if(currentState == GAMESTATE.START){			
			//Starting the countdown timer
			updateStartTime();
			
			//When the countdown reaches 0 the game is started and the game state is changed to playing
			if(startTime == 0){
				currentState = GAMESTATE.PLAYING;
				timer.start();
				firstGameBall.play();
				playBallsIfActive();
			}else{
			//Generating countdown title and countdown numbers	
				countDownTitle = new ImageIcon("assets/textImages/countDownTitle.png");
				countDownTitle.paintIcon(this, g, 175, 280);
				
				if(startTime==1){
					countDownNumber = new ImageIcon("assets/textImages/countDown1.png");
					countDownNumber.paintIcon(this, g, 467, 380);
				}
				else if(startTime==2){
					countDownNumber = new ImageIcon("assets/textImages/countDown2.png");
					countDownNumber.paintIcon(this, g, 467, 380);
				}
				else if(startTime==3){
					countDownNumber = new ImageIcon("assets/textImages/countDown3.png");
					countDownNumber.paintIcon(this, g, 467, 380);
				}
			}
		}
		//Creating screen for when the game is paused
		if(currentState == GAMESTATE.PAUSED){
			// IU background
			Color c = new Color(0f,0f,0f,.6f);
			g.setColor(c);
			g.fillRect(0, 0, 1024, 768);
			
			paused = new ImageIcon("assets/textImages/paused.png");
			paused.paintIcon(this, g, 376, 380);
		}
		
		//Font for the 120 second timer
		// Constantly decrementing when timer is not 0
		Fonts.addFont(new Fonts());
		if(gameTime > 0){
			// Timer
			updateGameTime();
			g.setColor(Color.white);
			g.setFont(new Font("Digiface", Font.BOLD, 50));
			g.drawString(""+gameTime, 25, 403);
		}else{
			currentState = GAMESTATE.END;
			g.setColor(Color.white);
			g.setFont(new Font("Digiface", Font.PLAIN, 50));
			g.drawString("0", 25, 403);
		}
		
		// Detecting when either 3 players are dead or timer has reached 0, Game ends
		if(playerActiveCount == 1 || gameTime == 0){
			currentState = GAMESTATE.END;
			gameEndScreen(g);
		}
		
		// Dispose rendered screen
		g.dispose();
	}

	// Creating the end screen of the game
	private void gameEndScreen(Graphics g){
		firstGameBall.stop();
		stopBallsIfActive();
		
		gameOvertitle = new ImageIcon("assets/textImages/titleGameOver.png");
		gameOvertitle.paintIcon(this, g, 244, 150);
		
		gameOverWinner = new ImageIcon("assets/textImages/winner.png");
		gameOverWinner.paintIcon(this, g, 366, 320);
		
		//Determining winner when there is only one survivor remaining
		if (gameTime > 0){
			winner = determineSurviver();
		}
		//Determining winner when the game has been played for two minutes
		else if (gameTime == 0){
			winner = winnerAtTimeZero();
		}
		//Printing the winner player on the end screen
		if (winner == PLAYER.PLAYERONE){
			gameOverPlayer = new ImageIcon("assets/textImages/playerOne.png");
		}
		else if (winner == PLAYER.PLAYERTWO){
			gameOverPlayer = new ImageIcon("assets/textImages/playerTwo.png");
		}
		else if (winner == PLAYER.PLAYERTHREE){
			gameOverPlayer = new ImageIcon("assets/textImages/playerThree.png");
		}
		else if (winner == PLAYER.PLAYERFOUR){
			gameOverPlayer = new ImageIcon("assets/textImages/playerFour.png");
		}
		
		gameOverPlayer.paintIcon(this, g, 347, 370);
		gameOverRestart = new ImageIcon("assets/textImages/enterToRestart.png");
		gameOverRestart.paintIcon(this, g, 218, 500);
	}
	
	// Determine the winner by finding the last surviver
	public PLAYER determineSurviver(){
		if(playerOne.lifeStatus()){
			winner = PLAYER.PLAYERONE;
		}
		else if(playerTwo.lifeStatus()){
			winner = PLAYER.PLAYERTWO;
		}
		else if(playerThree.lifeStatus()){
			winner = PLAYER.PLAYERTHREE;
		}
		else if(playerFour.lifeStatus()){
			winner = PLAYER.PLAYERFOUR;
		}
		
		return winner;
	}
	
	// Decide on the winner of the game when the timer reaches 0.
	// The winner is chosen based on the number of walls remaining.
	public PLAYER winnerAtTimeZero(){
		mostWallsRemaining = playerOne.brickCount;
		winner = PLAYER.PLAYERONE;
		
		if (playerTwo.brickCount > mostWallsRemaining){
			mostWallsRemaining = playerTwo.brickCount;
			winner = PLAYER.PLAYERTWO;
		}
		if (playerThree.brickCount > mostWallsRemaining){
			mostWallsRemaining = playerThree.brickCount;
			winner = PLAYER.PLAYERTHREE;
		}
		if (playerFour.brickCount > mostWallsRemaining){
			mostWallsRemaining = playerFour.brickCount;
			winner = PLAYER.PLAYERFOUR;
		}
		
		return winner;
	}

	//Checking if the key has been pressed
	public void gamekeyPressed(KeyEvent e) {
		// Enables player controls when the gamestate is PLAYING only.
		if(currentState == GAMESTATE.PLAYING){
			playerOne.keyControl(e);
			playerTwo.keyControl(e);
			playerThree.keyControl(e);
			playerFour.keyControl(e);
		}
		
		// Pause and unpause the game by pressing the P Key
		if(e.getKeyCode() == KeyEvent.VK_P){
			if(currentState == GAMESTATE.PLAYING){
				currentState = GAMESTATE.PAUSED;
				timer.stop();
			}
			else if(currentState == GAMESTATE.PAUSED){
				currentState = GAMESTATE.PLAYING;
				timer.start();
				firstGameBall.play();
				playBallsIfActive();
			}
		}
		
		//Generating an extra game ball by pressing "1"
		if(e.getKeyCode() == KeyEvent.VK_1){
			if(currentState == GAMESTATE.PLAYING){
				extraBallActive = true;
				extraBall = new GameBall(502, 374, 0);
				extraBall.play();
			}
		}
		//Generating another extra game ball by pressing "2"
		if(e.getKeyCode() == KeyEvent.VK_2){
			if(currentState == GAMESTATE.PLAYING){
				extraBallActive2 = true;
				extraBall2 = new GameBall(502, 374, 0);
				extraBall2.play();
			}
		}
		
		// Return to the menu screen by pressing ESC when the game has paused or ended, all components are set to 0
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
			if(currentState == GAMESTATE.PAUSED || currentState == GAMESTATE.END){
				Gameplay.currentState = Gameplay.STATE.MENU;
				currentState = GAMESTATE.START;
				resetGame();
				extraBall = null;
				extraBall2 = null;
				playerOne = null;
				playerTwo = null;
				playerThree = null;
				playerFour = null;
				gameOvertitle = null;
				gameOverPlayer = null;
				gameOverRestart = null;
				gameOverWinner = null;
				gameBackground = null;
				countDownTitle = null;
				countDownNumber = null;
				player1Wall = null;
				player2Wall = null;
				player3Wall = null;
				player4Wall = null;
			}
		}
		
		// Reset the game by pressing ENTER when the game has ended
		if(e.getKeyCode() == KeyEvent.VK_ENTER){
			if(currentState == GAMESTATE.END){
				resetGame();
			}
		}
		
		// Make the game time 0 to end the game instantly
		if(e.getKeyCode() == KeyEvent.VK_PAGE_DOWN){
			gameTime = 0;
		}
	}
	
	//Resetting the game 
	private void resetGame(){
		currentState = GAMESTATE.START;
		gameTime = 120;
		startTime = 3;
		playerActiveCount = 0;
		
		firstGameBall.reset();
		resetBallsIfActive();
		
		playerOne.reset();
		playerTwo.reset();
		playerThree.reset();
		playerFour.reset();
		
		player1Wall = new WallGenerator();
		player2Wall = new WallGenerator();
		player3Wall = new WallGenerator();
		player4Wall = new WallGenerator();
	}

	//Allowing the balls to run when the status of the specific ball becomes active
	private void playBallsIfActive(){
		if(player1GameBallActive == true){
			player1GameBall.play();
		}
		if(player2GameBallActive == true){
			player2GameBall.play();
		}
		if(player3GameBallActive == true){
			player3GameBall.play();
		}
		if(player4GameBallActive == true){
			player4GameBall.play();
		}
		if(extraBallActive == true){
			extraBall.play();
		}
		if(extraBallActive2 == true){
			extraBall2.play();
		}
	}
	
	//Called to stop the balls at the end of the game
	private void stopBallsIfActive(){
		if(player1GameBallActive == true){
			player1GameBall.stop();
		}
		if(player2GameBallActive == true){
			player2GameBall.stop();
		}
		if(player3GameBallActive == true){
			player3GameBall.stop();
		}
		if(player4GameBallActive == true){
			player4GameBall.stop();
		}
		if (extraBallActive == true){
			extraBall.stop();
		}
		if(extraBallActive2 == true){
			extraBall2.stop();
		}
	}
	
	//Calculating all directions and collision calculations
	private void ballCalculationIfBallsIfActive(){
		if(player1GameBallActive == true){
			ballCalculation(player1GameBall);
		}
		if(player2GameBallActive == true){
			ballCalculation(player2GameBall);
		}
		if(player3GameBallActive == true){
			ballCalculation(player3GameBall);
		}
		if(player4GameBallActive == true){
			ballCalculation(player4GameBall);
		}
		if (extraBallActive == true){
			ballCalculation(extraBall);
		}
		if(extraBallActive2 == true){
			ballCalculation(extraBall2);
		}
	}
	
	//Resetting the balls on the screen
	private void resetBallsIfActive(){
		if(player1GameBallActive == true){
			player1GameBallActive = false;
			player1GameBall.reset();
		}
		if(player2GameBallActive == true){
			player2GameBallActive = false;
			player2GameBall.reset();
		}
		if(player3GameBallActive == true){
			player3GameBallActive = false;
			player4GameBall.reset();
		}
		if(player4GameBallActive == true){
			player4GameBallActive = false;
			player4GameBall.reset();
		}
		if (extraBallActive == true){
			extraBallActive = false;
			extraBall.reset();
		}
		if (extraBallActive2 == true){
			extraBallActive2 = false;
			extraBall2.reset();
		}
	}
	
	//Detecting intersection of ball and player walls, when ball hits player wall it is reflected.
	@Override
	public void actionPerformed(ActionEvent e) {
		if(currentState == GAMESTATE.PLAYING){
			timer.start();
			ballCalculation(firstGameBall);
			ballCalculationIfBallsIfActive();
		}
	}
	
	public void ballCalculation(GameBall ball){
		aiTimer++;
		//Continue moving ball and ball reflect
		changeDirIfCollision(ball);
		ball.moveBall();
		
		if(aiTimer == 40){
			playerOne.AIcontrol(ball);
			playerTwo.AIcontrol(ball);
			playerThree.AIcontrol(ball);
			playerFour.AIcontrol(ball);
			aiTimer = 0;
		}
	}
	
	//Change the direction of the ball when there is a collision with any walls,
	//paddles or kings of any player.
	public boolean changeDirIfCollision(GameBall ball){
		//The object is in front of the rendered image, this is to make sure the ball does
		//not get rendered inside a paddle or wall.
		Rectangle ballObject = new Rectangle(ball.ballposX, 
				ball.ballposY, 25, 25);
		
		if(playerOne.lifeStatus()){
			if(!playerOne.playerTurned && ballObject.intersects(new Rectangle(playerOne.playerXPos, playerOne.playerYPos, 60, 5))){
				return ball.reflectBallOffPaddle(PLAYER.PLAYERONE, Direction.YDir, this);
			}
			else if(playerOne.playerTurned && ballObject.intersects(new Rectangle(playerOne.playerXPos+30, playerOne.playerYPos, 5, 60))){
				return ball.reflectBallOffPaddle(PLAYER.PLAYERONE, Direction.XDir, this);
			}
		}
		if(playerTwo.lifeStatus()){
			if(playerTwo.playerTurned && ballObject.intersects(new Rectangle(playerTwo.playerXPos, playerTwo.playerYPos, 60, 5))){
				return ball.reflectBallOffPaddle(PLAYER.PLAYERTWO, Direction.YDir, this);
			}
			if(!playerTwo.playerTurned && ballObject.intersects(new Rectangle(playerTwo.playerXPos, playerTwo.playerYPos, 5, 60))){
				return ball.reflectBallOffPaddle(PLAYER.PLAYERTWO, Direction.XDir, this);
			}
		}
		if(playerThree.lifeStatus()){
			if(playerThree.playerTurned && ballObject.intersects(new Rectangle(playerThree.playerXPos, playerThree.playerYPos+25, 60, 5))){
				return ball.reflectBallOffPaddle(PLAYER.PLAYERTHREE, Direction.YDir, this);
				
			}
			else if(!playerThree.playerTurned && ballObject.intersects(new Rectangle(playerThree.playerXPos, playerThree.playerYPos, 5, 60))){
				return ball.reflectBallOffPaddle(PLAYER.PLAYERTHREE, Direction.XDir, this);
			}
		}
		if(playerFour.lifeStatus()){
			if(!playerFour.playerTurned && ballObject.intersects(new Rectangle(playerFour.playerXPos, playerFour.playerYPos+25, 60, 5))){
				return ball.reflectBallOffPaddle(PLAYER.PLAYERFOUR, Direction.YDir, this);
			}
			else if(playerFour.playerTurned && ballObject.intersects(new Rectangle(playerFour.playerXPos+25, playerFour.playerYPos, 5, 60))){
				return ball.reflectBallOffPaddle(PLAYER.PLAYERFOUR, Direction.XDir, this);
			}
		}
		
		//Detecting when ball has hit king. If king is hit with the ball the specified player is dead and death count incremented
		playerOne.checkBallIntersectKing(ballObject,new Rectangle(playerOne.playerKingX, playerOne.playerKingY, 90, 90), ball, this);
		playerTwo.checkBallIntersectKing(ballObject,new Rectangle(playerTwo.playerKingX, playerTwo.playerKingY, 90, 90), ball, this);
		playerThree.checkBallIntersectKing(ballObject,new Rectangle(playerThree.playerKingX, playerThree.playerKingY, 90, 90), ball, this);
		playerFour.checkBallIntersectKing(ballObject,new Rectangle(playerFour.playerKingX, playerFour.playerKingY, 90, 90), ball, this);
		
		//Checking destroyed bricks for player one
		for(int i = 0; i<player1Wall.player1wall.length;i++){
			for(int j = 0; j<player1Wall.player1wall[0].length;j++){
				if(player1Wall.player1wall[i][j] > 0){
					
					Rectangle player1Brick = new Rectangle(j * player1Wall.brickWidth + 128, i * player1Wall.brickHeight + 523,
							player1Wall.brickWidth, player1Wall.brickHeight);
					
					// Check for intersections
					if(ballObject.intersects(player1Brick)){
						player1Wall.setBrickValue(1, 0, i, j);
						boingSound.play();
						scoreAwarder(PLAYER.PLAYERONE);
						playerOne.brickCount--;
						
						if(ball.ballposX + 24 <= player1Brick.x || ball.ballposX + 1 >= player1Brick.x + player1Brick.width){
							ball.ballXdir = -ball.ballXdir;
						} else {
							ball.ballYdir = -ball.ballYdir;
						}
						return true;
					}
				}
			}
		}
		//Checking destroyed bricks for player two
		for(int i = 0; i<player2Wall.player2wall.length;i++){
			for(int j = 0; j<player2Wall.player2wall[0].length;j++){
				if(player2Wall.player2wall[i][j] > 0){
					
					Rectangle playerTwoBrick = new Rectangle(j * player2Wall.brickWidth + 656, i * player2Wall.brickHeight + 523,
							player2Wall.brickWidth, player2Wall.brickHeight);
					
					// Check for intersections
					if(ballObject.intersects(playerTwoBrick)){
						player2Wall.setBrickValue(2, 0, i, j);
						boingSound.play();
						scoreAwarder(PLAYER.PLAYERTWO);
						playerTwo.brickCount--;
						
						if(ball.ballposX + 24 <= playerTwoBrick.x || ball.ballposX + 1 >= playerTwoBrick.x + playerTwoBrick.width){
							ball.ballXdir = -ball.ballXdir;
						} else {
							ball.ballYdir = -ball.ballYdir;
						}
						return true;
					}
				}
			}
		}
		//Checking destroyed bricks for player three
		for(int i = 0; i<player3Wall.player3wall.length;i++){
			for(int j = 0; j<player3Wall.player3wall[0].length;j++){
				if(player3Wall.player3wall[i][j] > 0){
					
					Rectangle player3Brick = new Rectangle(j * player3Wall.brickWidth + 656, i * player3Wall.brickHeight,
							player3Wall.brickWidth, player3Wall.brickHeight);
					
					// Check for intersections
					if(ballObject.intersects(player3Brick)){
						player3Wall.setBrickValue(3, 0, i, j);
						boingSound.play();
						scoreAwarder(PLAYER.PLAYERTHREE);
						playerThree.brickCount--;
						
						if(ball.ballposX + 24 <= player3Brick.x || ball.ballposX + 1 >= player3Brick.x + player3Brick.width){
							ball.ballXdir = -ball.ballXdir;
						} else {
							ball.ballYdir = -ball.ballYdir;
						}
						return true;
					}
				}
			}
		}
		//Checking destroyed bricks for player four
		for(int i = 0; i<player4Wall.player4wall.length;i++){
			for(int j = 0; j<player4Wall.player4wall[0].length;j++){
				if(player4Wall.player4wall[i][j] > 0){
					
					Rectangle player4Brick = new Rectangle(j * player4Wall.brickWidth + 128, i * player4Wall.brickHeight,
							player4Wall.brickWidth, player4Wall.brickHeight);
					
					// Check for intersections
					if(ballObject.intersects(player4Brick)){
						player4Wall.setBrickValue(4, 0, i, j);
						boingSound.play();
						scoreAwarder(PLAYER.PLAYERFOUR);
						playerFour.brickCount--;
						
						if(ball.ballposX + 24 <= player4Brick.x || ball.ballposX + 1 >= player4Brick.x + player4Brick.width){
							ball.ballXdir = -ball.ballXdir;
						} else {
							ball.ballYdir = -ball.ballYdir;
						}
						return true;
					}
				}
			}
		}
		ball.reflectBallOffWall();
		return false;
	}
	
	//When a wall is destroyed the player who hit it last get a point, unless the wall is their owns.
	public void scoreAwarder(PLAYER excludePlayer){
		if (lastTouchPlayer == PLAYER.PLAYERONE && lastTouchPlayer != excludePlayer){
			playerOne.score += 5;
		}
		else if (lastTouchPlayer == PLAYER.PLAYERTWO && lastTouchPlayer != excludePlayer){
			playerTwo.score += 5;
		}
		else if (lastTouchPlayer == PLAYER.PLAYERTHREE && lastTouchPlayer != excludePlayer){
			playerThree.score += 5;
		}
		else if (lastTouchPlayer == PLAYER.PLAYERFOUR && lastTouchPlayer != excludePlayer){
			playerFour.score += 5;
		}
	}
}

