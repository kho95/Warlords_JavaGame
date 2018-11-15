package menuPackage;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReader {
	
	private Scanner x;
	public String a;
	public String b;
	public String c;
	public String d;
	
	
	//Opening the file
	public void openFile(){
		try{
			x = new Scanner(new File("assets/Highscore.txt"));			
		}
		catch(Exception e){
			System.out.println("could not find file");
		}
	}
	//Reading the contents of the file to render onto the high score screen
	public ArrayList<String> readFile(){
		ArrayList<String> highScoreList = new ArrayList<String>();
		//Reading through each line of the text file (four lines)
			a = x.next();
			b = x.next();
			c = x.next();
			d = x.next();
		//Adding each of the score values in the text file to the highScoreList Array List
		highScoreList.add(a);
		highScoreList.add(b);
		highScoreList.add(c);
		highScoreList.add(d);
		return highScoreList;
	}
	//Closing the file
	public void closeFile(){
		x.close();
	}
}
