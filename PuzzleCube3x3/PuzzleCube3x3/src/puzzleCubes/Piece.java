package puzzleCubes;

class Piece {
	
private char frontColor;
private int lastRotation;

	public Piece() {
		frontColor=0;
		lastRotation=0;
	}

	public Piece(char color, int orient) {
		setFrontColor(color);
		
		lastRotation=0;
	}
	public void setFrontColor(char color) {
		this.frontColor=color;
	}
	
	public char getFront() {
		return frontColor;
	}
	
	public String toString() {
		return Character.toString(frontColor);
	}
	
	public int getLastRotation() {
		return lastRotation;
	}

	public void setLastRotation(int rotation) {
		this.lastRotation=rotation;
	}
}
