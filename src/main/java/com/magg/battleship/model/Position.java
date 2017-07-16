package com.magg.battleship.model;

/**
 *  This class converts excel like positions (A2, B3, etc) to X,Y co-ordinates.
 * 
 * @author Mohit Aggarwal
 *
 */
public final class Position {
	
	private static final String REFERNCE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	public static final Position create(String location){
		if (location == null || location.isEmpty()){
			throw new IllegalArgumentException("Null or empty location not allowed");
		}
		
		if (location.length() != 2){
			throw new IllegalArgumentException("Incorrect location format.");
		}
		
		char vertical = location.charAt(0);
		int y = REFERNCE.indexOf(vertical) + 1;

		char horizontal = location.charAt(1);
		int x = Character.getNumericValue(horizontal);
		
		return new Position(x,y);
	}
	
	public static final Position create(Position reference, int horizontalOffset, int verticalOffset){
		Position p = new Position(reference);
		p.x += horizontalOffset;
		p.y += verticalOffset;
		
		return p;
	}

	private int x;
	private int y;
	
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Position(Position p) {
		this.x = p.x;
		this.y = p.y;
	}

	public int x() {
		return x;
	}
	
	public int y() {
		return y;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Position other = (Position) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Position [x=" + x + ", y=" + y + "]";
	}
}
