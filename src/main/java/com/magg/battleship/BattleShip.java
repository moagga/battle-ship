package com.magg.battleship;

import java.nio.file.Paths;

import com.magg.battleship.game.Game;
import com.magg.battleship.model.Player;
import com.magg.battleship.provider.FileInputProvider;
import com.magg.battleship.provider.InputProvider;

/**
 * Main class
 * 
 * @author Mohit Aggarwal
 */
public class BattleShip {


    public static void main(String[] args) {
        try {
            String filePath = null;
            if (args.length > 0 && args[0] != null && !args[0].isEmpty()) {
                filePath = args[0];
            } else {
                //defaulting
                filePath = Paths.get(Thread.currentThread().getContextClassLoader().getResource("sample-run.txt").toURI()).toString();
            }
            System.out.println(filePath);

            InputProvider provider = new FileInputProvider(filePath);

            Game game = new Game();
            game.setup(provider);
            Player winner = game.play();

            if (winner != null) {
                System.out.println("Winner:" + winner.getName());
            } else {
                System.out.println("Peace declared");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
