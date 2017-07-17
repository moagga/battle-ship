package com.magg.battleship.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.magg.battleship.model.weapon.Missile;
import com.magg.battleship.model.weapon.Weapon;

/**
 * Model representation of a player in BattleShip game.
 * 
 * @author Mohit Aggarwal
 */
public class Player {

    /** Player name **/
    private final String name;

    /** Queue of weapons that the player own **/
    private final Queue<Weapon> weapons;

    /** list of ships that the player own **/
    private final List<Ship> ships;

    /**
     * Creates a new Player.
     * 
     * @param name Name of the player.
     */
    public Player(String name) {
        this.name = name;
        this.weapons = new LinkedList<>();
        this.ships = new ArrayList<>();
    }

    /**
     * Add weapons to the player.
     * 
     * @param targets Weapons target string in form the format A1 B2 C3
     * @return number of weapons added.
     * @throws IllegalArgumentException if targets string is null or empty
     */
    public int addWeapons(String targets) {
        if (targets == null || targets.isEmpty()) {
            throw new IllegalArgumentException("Invalid targets provided");
        }

        String[] items = targets.split("\\s");
        for (String target : items) {
            Position position = Position.create(target);
            Weapon w = new Missile(position);
            weapons.add(w);
        }
        return weapons.size();
    }

    /**
     * Add a ship to the player.
     */
    public void addShips(Ship ship) {
        this.ships.add(ship);
    }

    /**
     * Returns the next weapon for the player.
     * 
     * @return weapon or null if all weapons are exhausted.
     */
    public Weapon nextWeapon() {
        return weapons.poll();
    }

    /**
     * Return the players name.
     * 
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the ships assigned to a player.
     * 
     * @return
     */
    public List<Ship> getShips() {
        return Collections.unmodifiableList(ships);
    }

    /**
     * @param active
     * @return
     */
    public boolean areAllShipsDestroyed() {
        boolean result = ships.stream().anyMatch((ship) -> {
            return !ship.isDestroyed();
        });
        return !result;
    }

}
