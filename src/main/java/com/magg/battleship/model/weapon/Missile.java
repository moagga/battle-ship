package com.magg.battleship.model.weapon;

import com.magg.battleship.model.Position;

public class Missile extends Weapon {

	private static final int MISSILE_FIREPOWER = 1;

	public Missile(Position target) {
		super(target);
	}
	
	@Override
	public int firePower() {
		return MISSILE_FIREPOWER;
	}

}
