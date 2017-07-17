package com.magg.battleship.provider;

import java.util.List;

/**
 * Input Data provider to setup the game.
 * 
 * @author Mohit Aggarwal
 */
public interface InputProvider {

    /**
     * Returns the battleship size in the format 5 E
     * 
     * @return
     */
    String battleShipSize();

    /**
     * Return the first player weapon sequence in the format A1 B2 B3
     * 
     * @return
     */
    String firstPlayerWeaponSequence();

    /**
     * Return the second player weapon sequence in the format A1 B2 B3
     * 
     * @return
     */
    String secondPlayerWeaponSequence();

    /**
     * Returns the ship data in chunk of 4 lines for each ship. Each chunk (4 lines) contains ship type, ship dimensions, player 1 location, player 2 location.
     * 
     * @return
     */
    List<List<String>> shipsData();
}
