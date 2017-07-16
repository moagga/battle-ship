package com.magg.battleship;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.magg.battleship.model.BattleSpace;
import com.magg.battleship.model.Player;
import com.magg.battleship.model.Position;
import com.magg.battleship.model.Ship;
import com.magg.battleship.model.ShipType;
import com.magg.battleship.model.weapon.Weapon;

public class Game {

	private Queue<Player> players;
	private BattleSpace battleShip;
	
	private Player active;
	private Player passive;
	
	private int maxHits;
	
	public void setup(List<String> inputs){
		//Create battleship
		String size = inputs.remove(0);
		battleShip = new BattleSpace(size);
		
		Player first = new Player("Player 1");
		Player second = new Player("Player 2");
		
		//Assign weapons
		String player2Seq = inputs.remove(inputs.size() - 1);
		int weaponsAdded = second.addWeapons(player2Seq);
		maxHits += weaponsAdded;
		
		String player1Seq = inputs.remove(inputs.size() - 1);
		weaponsAdded = first.addWeapons(player1Seq);
		maxHits += weaponsAdded;

		//Create ships
		List<List<String>> ships = toChunks(inputs, 4);
		for (List<String> shipInfo : ships) {
			ShipType type = ShipType.valueOf(shipInfo.get(0));
			String[] dimensions = shipInfo.get(1).split("\\s");
			int width = Integer.parseInt(dimensions[0]);
			if (width < 1 || width > battleShip.getWidth()){
				throw new IllegalArgumentException("Width of ship out of bound.");
			}
			int height = Integer.parseInt(dimensions[1]);
			if (height < 1 || height > battleShip.getHeight()){
				throw new IllegalArgumentException("Height of ship out of bound.");
			}

			String player1Loc = shipInfo.get(2);
			Position shipPosition = convert(player1Loc);
			Ship ship = new Ship(type, width, height, shipPosition);
			ship.setShipOwner(first);
			battleShip.addShip(ship);

			String player2Loc = shipInfo.get(3);
			shipPosition = convert(player2Loc);
			ship = new Ship(type, width, height, shipPosition);
			ship.setShipOwner(second);
			battleShip.addShip(ship);
		}
		
		players = new LinkedList<>();
		players.add(first);
		players.add(second);
		
		active = first;
		passive = second;
	}

	public Player play(){
		Player winner = null;
		
		int index = 0;
		while (index < maxHits){
			Weapon w = active.nextWeapon();
			if (w == null){
				swapActivePossive();
				continue;
			}

			boolean success = battleShip.handleWeapon(w, passive);
			index++;
			System.out.println(active.getName() + ":" + w.getTarget() + ":" + success);

			if (success){
				boolean anyOppositionShipLeft = battleShip.assessOppositionDamage(passive);
				//if all opponents ships are destroyed, declare winner
				if (!anyOppositionShipLeft){
					winner = active;
					break;
				}
			} else {
				swapActivePossive();
			}
		}
/*		
		while (!players.isEmpty()){
			Player activePlayer = players.poll();
			Weapon w = activePlayer.nextWeapon();
			// If no weapons are left for a player, he can't play further chances.
			if (w == null) {
				players.remove(activePlayer);
				continue;
			}

			boolean success = battleShip.handleWeapon(w, activePlayer);
			System.out.println(activePlayer.getName() + ":" + w.getTarget() + ":" + success);
			if (success){
				boolean anyOppositionShipLeft = battleShip.assessOppositionDamage(activePlayer);
				//if all opponents ships are destroyed, declare winner
				if (!anyOppositionShipLeft){
					winner = activePlayer;
					break;
				}
			} else {
				players.add(activePlayer);
			}
		}
*/		
		return winner;
	}
	
	private List<List<String>> toChunks(List<String> list, int size){
		List<List<String>> chunks = new ArrayList<>();
		int index = 1;
		List<String> chunk = new ArrayList<>();
		Iterator<String> it = list.iterator();
		while (it.hasNext()) {
			String line = it.next();
			chunk.add(line);
			if (index % size == 0){
				chunks.add(chunk);
				chunk = new ArrayList<>();
			}
			index++;
		}
		return chunks;
	}

	private Position convert(String location){
		Position shipPosition = Position.create(location);
		if (shipPosition.x() < 1 || shipPosition.x() > battleShip.getWidth()){
			throw new IllegalArgumentException("x-coordinate of the ship out of bound.");
		}
		
		if (shipPosition.y() < 1 || shipPosition.y() > battleShip.getHeight()){
			throw new IllegalArgumentException("y-coordinate of the ship out of bound.");
		}
		return shipPosition;
	}
	
	private void swapActivePossive(){
		Player temp = active;
		active = passive;
		passive = temp;
	}
	
}
