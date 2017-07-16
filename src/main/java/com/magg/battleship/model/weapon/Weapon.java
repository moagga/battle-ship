package com.magg.battleship.model.weapon;

import com.magg.battleship.model.Position;

public abstract class Weapon {

	private Position target;
	
	public Weapon(Position target) {
		this.target = target;
	}
	
	public abstract int firePower();
	
	public Position getTarget() {
		return target;
	}
	
}
