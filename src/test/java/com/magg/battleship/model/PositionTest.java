package com.magg.battleship.model;

import org.junit.Test;

import junit.framework.Assert;

/**
 * Test cases for {@link Position}
 * 
 * @author Mohit Aggarwal
 *
 */
public class PositionTest {

	@Test(expected = IllegalArgumentException.class)
	public void testNullLocation(){
		Position.create(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testEmptyLocation(){
		Position.create("");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testIncorrectFormat(){
		Position.create("A 3");
	}

	@Test
	public void testXandY(){
		Position p = Position.create("C4");
		Assert.assertEquals(4, p.x());
		Assert.assertEquals(3, p.y());
	}

	@Test
	public void testReference(){
		Position ref = Position.create("C4");
		Position p = Position.create(ref, 1, 3);
		Assert.assertEquals(5, p.x());
		Assert.assertEquals(6, p.y());
	}

	@Test
	public void testEquals(){
		Position p1 = Position.create("C4");
		Position p2 = Position.create("C4");
		Position p3 = Position.create("C5");
		Position p4 = Position.create("B4");
		Assert.assertTrue(p1.equals(p2));
		Assert.assertFalse(p1.equals(p3));
		Assert.assertFalse(p1.equals(p4));
	}

	@Test
	public void testHashCode(){
		Position p1 = Position.create("C4");
		Position p2 = Position.create("C4");
		Position p3 = Position.create("C5");
		Assert.assertTrue(p1.hashCode() == p2.hashCode());
		Assert.assertFalse(p1.hashCode() == p3.hashCode());
	}

}
