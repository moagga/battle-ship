package com.magg.battleship.model;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.magg.battleship.model.weapon.Missile;

/**
 * Test cases for {@link BattleSpace} class.
 * 
 * @author Mohit Aggarwal
 */
public class BattleSpaceTest {

    private BattleSpace battleSpace;
    private Player passive;
    private Ship shipOne;
    private Ship shipTwo;

    @Before
    public void setup() {
        battleSpace = new BattleSpace("3 C");

        passive = new Player("Arnold");
        shipOne = new Ship(ShipType.P, 1, 1, Position.create("A1"));
        passive.addShips(shipOne);

        shipTwo = new Ship(ShipType.P, 1, 1, Position.create("C3"));
        passive.addShips(shipTwo);
    }

    @Test
    public void testHandleWeaponWithIncorrectTarget() {
        boolean result = battleSpace.handleWeapon(new Missile(Position.create("A2")), passive);
        Assert.assertFalse(result);
        Assert.assertFalse(shipOne.isDestroyed());
    }

    @Test
    public void testHandleWeaponWhenShipIsAlreadyDestroyed() {
        //This will destroy the ship.
        battleSpace.handleWeapon(new Missile(Position.create("A1")), passive);
        Assert.assertTrue(shipOne.isDestroyed());

        //Second invocation should return false.
        boolean result = battleSpace.handleWeapon(new Missile(Position.create("A1")), passive);
        Assert.assertFalse(result);
    }

    @Test
    public void testHandleWeaponWhenShipBelongsToAnotherPlayer() {
        boolean result = battleSpace.handleWeapon(new Missile(Position.create("A1")), new Player("Rambo"));
        Assert.assertFalse(result);
        Assert.assertFalse(shipOne.isDestroyed());
    }

}
