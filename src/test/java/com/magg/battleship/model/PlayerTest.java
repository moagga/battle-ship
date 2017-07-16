package com.magg.battleship.model;

import org.junit.Test;

import junit.framework.Assert;

public class PlayerTest {

	@Test
	public void testWeaponSequence(){
		Player p = new Player("Arnold");
		p.addWeapons("A1 B1 A2 B2");
		
		Assert.assertEquals(Position.create("A1"), p.nextWeapon().getTarget());
		Assert.assertEquals(Position.create("B1"), p.nextWeapon().getTarget());
		Assert.assertEquals(Position.create("A2"), p.nextWeapon().getTarget());
		Assert.assertEquals(Position.create("B2"), p.nextWeapon().getTarget());
		
		//Return null after weapons are finished
		Assert.assertNull(p.nextWeapon());
	}
}
