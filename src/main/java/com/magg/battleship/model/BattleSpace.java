package com.magg.battleship.model;

import java.util.ArrayList;
import java.util.List;

import com.magg.battleship.model.weapon.Weapon;

/**
 * Model representation of Battle space.
 * 
 * @author Mohit Aggarwal
 *
 */
public class BattleSpace {

	private int width;
	private int height;
	
	/** List of ships in the battle space **/
	private List<Ship> ships;
	
	/**
	 * Creates a new battle space.
	 * 
	 * @param size Size of battle ground in 5 E format.
	 * @throws IllegalArgumentException if size is null, empty or incorrectly formatted
	 */
	public BattleSpace(String size) {
		if (size == null || size.isEmpty()){
			throw new IllegalArgumentException("Null or empty size not allowed");
		}

		if (size.length() != 3){
			throw new IllegalArgumentException("Incorrect size format.");
		}

		String[] parts = size.split("\\s");
		width = Integer.parseInt(parts[0]);
		if (width < 1 && width > 9){
			throw new IllegalArgumentException("Width must be between 1-9");
		}

		height = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(parts[1]) + 1;
		if (height < 1 && height > 26){
			throw new IllegalArgumentException("Width must be between 1-26");
		}
		
		this.ships = new ArrayList<>();
	}
	
	/**
	 * Add a ship to the battle space
	 * @param ship
	 */
	public void addShip(Ship ship){
		this.ships.add(ship);
	}
	
	/**
	 * 
	 * @param weapon
	 * @param passive
	 * @return
	 */
	public boolean handleWeapon(Weapon weapon, Player passive){
		Ship targetShip = ships.stream().filter((ship) -> {
			return ship.getShipOwner().equals(passive) && !ship.isDestroyed() && ship.canBeTargetted(weapon);
		}).findFirst().orElse(null);
	
		if (targetShip == null){
			return false;
		}
		
		return targetShip.damage(weapon);
	}
	
	/**
	 * 
	 * @param active
	 * @return
	 */
	public boolean assessOppositionDamage(Player active){
		boolean result = ships.stream().anyMatch((ship) -> {
			return ship.getShipOwner().equals(active) && !ship.isDestroyed();
		});
		return result;
	}
	
	/**
	 * Returns the width of battle space.
	 * @return
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * Returns the height of battle space.
	 * @return
	 */
	public int getHeight() {
		return height;
	}
}
