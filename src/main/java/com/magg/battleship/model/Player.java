package com.magg.battleship.model;

import java.util.LinkedList;
import java.util.Queue;

import com.magg.battleship.model.weapon.Missile;
import com.magg.battleship.model.weapon.Weapon;

/**
 * Model representation of a player in BattleShip game.
 * 
 * 
 * @author Mohit Aggarwal
 *
 */
public class Player {

	/** Player name **/
	private String name;

	/** Queue of weapons that the player own **/
	private Queue<Weapon> weapons;
	
	/**
	 * Creates a new Player.
	 * 
	 * @param name Name of the player.
	 */
	public Player(String name) {
		this.name = name;
		this.weapons = new LinkedList<>();
	}
	
	/**
	 * Add weapons to the player.
	 * 
	 * @param targets Weapons target string in form the format A1 B2 C3
	 * @return number of weapons added.
	 * @throws IllegalArgumentException if targets string is null or empty
	 */
	public int addWeapons(String targets){
		if (targets == null || targets.isEmpty()){
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
	 * Returns the next weapon for the player.
	 * 
	 * @return weapon or null if all weapons are exhausted.
	 */
	public Weapon nextWeapon(){
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
	
}
