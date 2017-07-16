package com.magg.battleship;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.magg.battleship.model.Player;

/**
 * Main class 
 * 
 * @author Mohit Aggarwal
 *
 */
public class BattleShip {
	
	private static final String PATH = "/Users/magg/Documents/Projects/battle-ship/src/main/resources/sample-run.txt";
	
    public static void main( String[] args) {
    	try {
        	String filePath = PATH;
//        	String filePath = args[0];
        	if (filePath == null || filePath.isEmpty()){
        		throw new IllegalArgumentException("Input file path missing");
        	}
        	List<String> inputs = Files.readAllLines(Paths.get(filePath));
			
			Game game = new Game();
			game.setup(inputs);
			Player winner = game.play();
			if (winner != null){
				System.out.println("Winner:" + winner.getName());
			} else {
				System.out.println("Peace declared");
			}
			
		} catch (Exception e) {
			//Print to console. Use logging 
			e.printStackTrace();
		}
    	
    	
    }
}
