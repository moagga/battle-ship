package com.magg.battleship.model;

import org.junit.Test;

import com.magg.battleship.model.weapon.Missile;

import junit.framework.Assert;

public class ShipTest {

	@Test
	public void testCanBeTargetted(){
		Position p = Position.create("C3");
		Ship ship = new Ship(ShipType.P, 2, 1, p);

		boolean result = ship.canBeTargetted(new Missile(p));
		Assert.assertTrue(result);
		
		Position right = Position.create(p, 1, 0);
		result = ship.canBeTargetted(new Missile(right));
		Assert.assertTrue(result);

		Position down = Position.create(p, 0, 1);
		result = ship.canBeTargetted(new Missile(down));
		Assert.assertFalse(result);
	}

	@Test
	public void testrRehittingOfPartialDestructedTarget(){
		Position p = Position.create("C3");
		Ship ship = new Ship(ShipType.Q, 1, 1, p);

		//First hit
		boolean damaged = ship.damage(new Missile(p));
		Assert.assertTrue(damaged);
		Assert.assertFalse(ship.isDestroyed());

		//Second hit
		damaged = ship.damage(new Missile(p));
		Assert.assertTrue(damaged);
		Assert.assertTrue(ship.isDestroyed());

	}
	
	@Test
	public void testRehittingDestructedTarget(){
		Position p = Position.create("C3");
		Ship ship = new Ship(ShipType.P, 1, 1, p);

		//First hit
		boolean damaged = ship.damage(new Missile(p));
		Assert.assertTrue(damaged);
		Assert.assertTrue(ship.isDestroyed());

		//Second hit (will return false as target is already destroyed)
		damaged = ship.damage(new Missile(p));
		Assert.assertFalse(damaged);
	}

	@Test
	public void testFullDestruction(){
		Position p = Position.create("C3");
		Ship ship = new Ship(ShipType.P, 2, 2, p);

		//First hit
		boolean damaged = ship.damage(new Missile(p));
		Assert.assertTrue(damaged);
		Assert.assertFalse(ship.isDestroyed());

		//Second hit
		Position right = Position.create(p, 1, 0);
		damaged = ship.damage(new Missile(right));
		Assert.assertTrue(damaged);
		Assert.assertFalse(ship.isDestroyed());

		//Third hit
		Position down = Position.create(p, 0, 1);
		damaged = ship.damage(new Missile(down));
		Assert.assertTrue(damaged);
		Assert.assertFalse(ship.isDestroyed());

		//Fourth hit
		Position bottomRight = Position.create(p, 1, 1);
		damaged = ship.damage(new Missile(bottomRight));
		Assert.assertTrue(damaged);
		Assert.assertTrue(ship.isDestroyed());

	}

}
