package com.magg.battleship.game;

import java.util.List;

import com.magg.battleship.model.BattleSpace;
import com.magg.battleship.model.Player;
import com.magg.battleship.model.Position;
import com.magg.battleship.model.Ship;
import com.magg.battleship.model.ShipType;
import com.magg.battleship.model.weapon.Weapon;
import com.magg.battleship.provider.InputProvider;

/**
 * This class represents instances of battleship game.
 * 
 * @author Mohit Aggarwal
 */
public class Game {

    private BattleSpace battleShip;

    private Player active;
    private Player passive;

    private int maxHits;

    /**
     * Setup the game in terms of battlespace, players, ships & weapons.
     * 
     * @param provider
     */
    public void setup(InputProvider provider) {
        //Create battleship
        battleShip = new BattleSpace(provider.battleShipSize());

        Player first = new Player("Player 1");
        Player second = new Player("Player 2");

        //Assign weapons
        int weaponsAdded = second.addWeapons(provider.secondPlayerWeaponSequence());
        maxHits += weaponsAdded;

        weaponsAdded = first.addWeapons(provider.firstPlayerWeaponSequence());
        maxHits += weaponsAdded;

        //Create ships
        List<List<String>> ships = provider.shipsData();
        for (List<String> shipInfo : ships) {
            ShipType type = ShipType.valueOf(shipInfo.get(0));
            String[] dimensions = shipInfo.get(1).split("\\s");
            int width = Integer.parseInt(dimensions[0]);
            if (width < 1 || width > battleShip.getWidth()) {
                throw new IllegalArgumentException("Width of ship out of bound.");
            }
            int height = Integer.parseInt(dimensions[1]);
            if (height < 1 || height > battleShip.getHeight()) {
                throw new IllegalArgumentException("Height of ship out of bound.");
            }

            String player1Loc = shipInfo.get(2);
            Position shipPosition = convert(player1Loc);
            Ship ship = new Ship(type, width, height, shipPosition);
            first.addShips(ship);

            String player2Loc = shipInfo.get(3);
            shipPosition = convert(player2Loc);
            ship = new Ship(type, width, height, shipPosition);
            second.addShips(ship);
        }

        active = first;
        passive = second;
    }

    /**
     * Executes the playing sequnce of the game.
     * 
     * @return a winner (if any), null otherwise.
     */
    public Player play() {
        Player winner = null;

        int index = 0;
        while (index < maxHits) {
            Weapon w = active.nextWeapon();
            //If no weapon, player has run out of weapons
            if (w == null) {
                swapActivePossive();
                continue;
            }

            boolean success = battleShip.handleWeapon(w, passive);
            index++;

            System.out.println(active.getName() + ":" + w.getTarget() + ":" + success);

            if (success) {
                boolean oppositionShipsCompletelyDestroyed = passive.areAllShipsDestroyed();
                //if all opponents ships are destroyed, declare winner
                if (oppositionShipsCompletelyDestroyed) {
                    winner = active;
                    break;
                }
            } else {
                swapActivePossive();
            }
        }

        return winner;
    }

    private Position convert(String location) {
        Position shipPosition = Position.create(location);
        if (shipPosition.x() < 1 || shipPosition.x() > battleShip.getWidth()) {
            throw new IllegalArgumentException("x-coordinate of the ship out of bound.");
        }

        if (shipPosition.y() < 1 || shipPosition.y() > battleShip.getHeight()) {
            throw new IllegalArgumentException("y-coordinate of the ship out of bound.");
        }
        return shipPosition;
    }

    private void swapActivePossive() {
        Player temp = active;
        active = passive;
        passive = temp;
    }

}
