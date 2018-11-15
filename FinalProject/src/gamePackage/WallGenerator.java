package gamePackage;

import java.awt.Graphics2D;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class WallGenerator extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Initalise Player Values
	public int player1wall[][];
	public int player2wall[][];
	public int player3wall[][];
	public int player4wall[][];
	
	// Initalise wall parameters
	public static int wallRow = 8;
	public static int wallCol = 8;
	private ImageIcon p1Brick;
	private ImageIcon p2Brick;
	private ImageIcon p3Brick;
	private ImageIcon p4Brick;
	public int brickWidth = 240/wallCol;
	public int brickHeight = 240/wallRow;
	
	// Using for loop to generate walls for Players 1-4, 
	public WallGenerator(){
		player1wall = new int[wallRow][wallCol];
		for(int i=0; i<player1wall.length; i++){
			for(int j=0; j<player1wall[0].length; j++){
				if(!(i>3 && j<4)){
					player1wall[i][j] = 1;
				}
			}
		}
		player2wall = new int[wallRow][wallCol];
		for(int i=0; i<player2wall.length; i++){
			for(int j=0; j<player2wall[0].length; j++){
				if(!(i>3 && j>3)){
					player2wall[i][j] = 1;
				}
			}
		}
		player3wall = new int[wallRow][wallCol];
		for(int i=0; i<player3wall.length; i++){
			for(int j=0; j<player3wall[0].length; j++){
				if(!(i<4 && j>3)){
					player3wall[i][j] = 1;
				}
			}
		}
		player4wall = new int[wallRow][wallCol];
		for(int i=0; i<player4wall.length; i++){
			for(int j=0; j<player4wall[0].length; j++){
				if(!(i<4 && j<4)){
					player4wall[i][j] = 1;
				}
			}
		}
	}
	
	//Drawing the walls for player 1 that we have found image walls for
	public void drawPlayer1Wall(Graphics2D g){
		for(int i=0; i<player1wall.length; i++){
			for(int j=0; j<player1wall[0].length; j++){
				if(player1wall[i][j] > 0){
					if((i==0 && j<7) || (i==1 && j<6) || (i==2 && j<5) || (i==3 && j<4)){
						p1Brick = new ImageIcon("assets/images/player1BrickN.png");
						p1Brick.paintIcon(this, g, j * brickWidth + 128, i * brickHeight + 523);
					}
					if((i>0 && j==7) || (i>1 && j==6) || (i>2 && j==5) || (i>3 && j==4)){
						p1Brick = new ImageIcon("assets/images/player1BrickE.png");
						p1Brick.paintIcon(this, g, j * brickWidth + 128, i * brickHeight + 523);
					}
					if((i==0 && j==7) || (i==1 && j==6) || (i==2 && j==5) || (i==3 && j==4)){
						p1Brick = new ImageIcon("assets/images/player1BrickM.png");
						p1Brick.paintIcon(this, g, j * brickWidth + 128, i * brickHeight + 523);
					}
				}
			}
		}
	}
	//Drawing the walls for player 2 that we have found image walls for
	public void drawPlayer2Wall(Graphics2D g){
		for(int i=0; i<player2wall.length; i++){
			for(int j=0; j<player2wall[0].length; j++){
				if(player2wall[i][j] > 0){
					if((i==0 && j>0) || (i==1 && j>1) || (i==2 && j>2) || (i==3 && j>3)){
						p2Brick = new ImageIcon("assets/images/player2BrickN.png");
						p2Brick.paintIcon(this, g, j * brickWidth + 656, i * brickHeight + 523);
					}
					if((i>0 && j==0) || (i>1 && j==1) || (i>2 && j==2) || (i>3 && j==3)){
						p2Brick = new ImageIcon("assets/images/player2BrickW.png");
						p2Brick.paintIcon(this, g, j * brickWidth + 656, i * brickHeight + 523);
					}
					if(i == j){
						p2Brick = new ImageIcon("assets/images/player2BrickM.png");
						p2Brick.paintIcon(this, g, j * brickWidth + 656, i * brickHeight + 523);
					}
				}
			}
		}
	}
	//Drawing the walls for player 3 
	public void drawPlayer3Wall(Graphics2D g){
		for(int i=0; i<player3wall.length; i++){
			for(int j=0; j<player3wall[0].length; j++){
				if(player3wall[i][j] > 0){
					if((i<7 && j==0) || (i<6 && j==1) || (i<5 && j==2) || (i<4 && j==3)){
						p3Brick = new ImageIcon("assets/images/player3BrickW.png");
						p3Brick.paintIcon(this, g, j * brickWidth + 656, i * brickHeight);
					}
					if((i==7 && j>0) || (i==6 && j>1) || (i==5 && j>2) || (i==4 && j>3)){
						p3Brick = new ImageIcon("assets/images/player3BrickS.png");
						p3Brick.paintIcon(this, g, j * brickWidth + 656, i * brickHeight);
					}
					if((i==7 && j==0) || (i==6 && j==1) || (i==5 && j==2) || (i==4 && j==3)){
						p3Brick = new ImageIcon("assets/images/player3BrickM.png");
						p3Brick.paintIcon(this, g, j * brickWidth + 656, i * brickHeight);
					}
				}
			}
		}
	}
	//Drawing the walls for player 4 
	public void drawPlayer4Wall(Graphics2D g){
		for(int i=0; i<player4wall.length; i++){
			for(int j=0; j<player4wall[0].length; j++){
				if(player4wall[i][j] > 0){
					if((i==7 && j<7) || (i==6 && j<6) || (i==5 && j<5) || (i==4 && j<4)){
						p4Brick = new ImageIcon("assets/images/player4BrickS.png");
						p4Brick.paintIcon(this, g, j * brickWidth + 128, i * brickHeight);
					}
					if((i<7 && j==7) || (i<6 && j==6) || (i<5 && j==5) || (i<4 && j==4)){
						p4Brick = new ImageIcon("assets/images/player4BrickE.png");
						p4Brick.paintIcon(this, g, j * brickWidth + 128, i * brickHeight);
					}
					if(i == j){
						p4Brick = new ImageIcon("assets/images/player4BrickM.png");
						p4Brick.paintIcon(this, g, j * brickWidth + 128, i * brickHeight);
					}
				}
			}
		}
	}
	
	//Setting value for bricks so they can disappear later when hit by ball
	public void setBrickValue(int playerNum, int value, int row, int col){
		if(playerNum==1){
			player1wall[row][col] = value;
		}
		else if(playerNum==2){
			player2wall[row][col] = value;
		}
		else if(playerNum==3){
			player3wall[row][col] = value;
		}
		else if(playerNum==4){
			player4wall[row][col] = value;
		}
	}
}
