package com.magg.battleship.model;

import com.magg.battleship.model.weapon.Weapon;

/**
 * Model representation of Battle space.
 * 
 * @author Mohit Aggarwal
 */
public class BattleSpace {

    private final int width;
    private final int height;

    /**
     * Creates a new battle space.
     * 
     * @param size Size of battle ground in 5 E format.
     * @throws IllegalArgumentException if size is null, empty or incorrectly formatted
     */
    public BattleSpace(String size) {
        if (size == null || size.isEmpty()) {
            throw new IllegalArgumentException("Null or empty size not allowed");
        }

        if (size.length() != 3) {
            throw new IllegalArgumentException("Incorrect size format.");
        }

        String[] parts = size.split("\\s");
        width = Integer.parseInt(parts[0]);
        if (width < 1 && width > 9) {
            throw new IllegalArgumentException("Width must be between 1-9");
        }

        height = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(parts[1]) + 1;
        if (height < 1 && height > 26) {
            throw new IllegalArgumentException("Width must be between 1-26");
        }
    }

    /**
     * Checks & damage a player's ship if applicable.
     * 
     * This method finds a target ship of the player based on following criteria:
     * 
     * <li>Ship is not fully destroyed
     * <li>Weapon's target is one the position occupied by ship.
     * 
     * 
     * @param weapon Weapon to target a ship
     * @param passive Player whos ships will be targetted
     * @return true if weapon is able to damage a ship, false otherwise.
     */
    public boolean handleWeapon(Weapon weapon, Player passive) {
        Ship targetShip = passive.getShips().stream().filter((ship) -> {
            return !ship.isDestroyed() && ship.canBeTargetted(weapon);
        }).findFirst().orElse(null);

        if (targetShip == null) {
            return false;
        }

        return targetShip.damage(weapon);
    }

    /**
     * Returns the width of battle space.
     * 
     * @return
     */
    public int getWidth() {
        return width;
    }

    /**
     * Returns the height of battle space.
     * 
     * @return
     */
    public int getHeight() {
        return height;
    }
}
