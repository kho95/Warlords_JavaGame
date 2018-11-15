package menuPackage;
import java.awt.Color;

import javax.swing.JFrame;

public class Main {
	public static void main(String[] args){
		// Create window frame of the game
		JFrame gameFrame = new JFrame();
		Gameplay gameplay = new Gameplay();
		
		gameFrame.setBounds(10,10,1024,768);
		gameFrame.setBackground(Color.DARK_GRAY);
		gameFrame.setTitle("Warlords");
		gameFrame.setResizable(false);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.add(gameplay);
		gameFrame.setVisible(true);

		// Repaint the game at 60FPS.
		while(true){
			gameplay.repaint();
			try {
				Thread.sleep(1000/60);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}
}
