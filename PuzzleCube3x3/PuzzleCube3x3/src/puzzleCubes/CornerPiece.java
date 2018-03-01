package puzzleCubes;

public class CornerPiece extends Piece {
	
	private char top, side;
	
	public CornerPiece() {
		super(' ',0);
		setTop(' ');
		setSide(' ');
	}
	
	/**point of reference is the the piece in the top left corner facing front
	 * 
	 * @param f - char facing front
	 * @param t - char facing top
	 * @param s - car facing left
	 */
	public CornerPiece(char f, char t, char s) {
		super(f,0);
		setTop(t);
		setSide(s);
	}
	
	public CornerPiece(CornerPiece piece) {
		this(piece.getFront(),piece.getTop(),piece.getSide());
		
	}

	public char getTop() {
		return top;
	}

	public void setTop(char t) {
		this.top = t;
	}

	public char getSide() {
		return side;
	}

	public void setSide(char s) {
		this.side = s;
	}

	
	/**
	 * rotate corner ccw point of reference is piece as top left corner
	 * @return temp Piece representing the next rotation of the corner piece
	 */
	public CornerPiece translateCornerPiece() {
		
		CornerPiece tempPiece= new CornerPiece();
		tempPiece.setFrontColor(getTop());
		tempPiece.setTop(getSide());
		tempPiece.setSide(getFront());
		return tempPiece;
		
	}
	
	public String toString() {
		return (super.toString() + top + side);
	}
}
