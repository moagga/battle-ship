package com.magg.battleship.model;

import junit.framework.Assert;

import org.junit.Test;

import com.magg.battleship.model.weapon.Missile;

/**
 * Test cases for {@link Player} class.
 * 
 * @author Mohit Aggarwal
 */
public class PlayerTest {

    @Test
    public void testWeaponSequence() {
        Player p = new Player("Arnold");
        p.addWeapons("A1 B1 A2 B2");

        Assert.assertEquals(Position.create("A1"), p.nextWeapon().getTarget());
        Assert.assertEquals(Position.create("B1"), p.nextWeapon().getTarget());
        Assert.assertEquals(Position.create("A2"), p.nextWeapon().getTarget());
        Assert.assertEquals(Position.create("B2"), p.nextWeapon().getTarget());

        //Return null after weapons are finished
        Assert.assertNull(p.nextWeapon());
    }

    @Test
    public void testAreAllShipsDestroyed() {
        Player p = new Player("Arnold");
        Ship s1 = new Ship(ShipType.P, 1, 1, Position.create("A1"));
        p.addShips(s1);
        Ship s2 = new Ship(ShipType.P, 1, 1, Position.create("A2"));
        p.addShips(s2);

        //Destroy first ship 
        s1.damage(new Missile(Position.create("A1")));
        Assert.assertFalse(p.areAllShipsDestroyed());

        //Destroy second ship 
        s2.damage(new Missile(Position.create("A2")));
        Assert.assertTrue(p.areAllShipsDestroyed());

    }
}
