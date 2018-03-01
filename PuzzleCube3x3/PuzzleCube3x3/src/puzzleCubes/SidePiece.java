package puzzleCubes;

public class SidePiece extends Piece {
	
	private char side;
	
	public SidePiece() {
		super(' ',0);
		setSide(' ');
	}
	public SidePiece(char f, char s) {
		super(f,0);
		setSide(s);
	}

	public SidePiece(SidePiece piece) {
		this(piece.getFront(),piece.getSide());
		
	}
	public char getSide() {
		return side;
	}

	public void setSide(char side) {
		this.side = side;
	}
	
	public SidePiece rotateSide() {
		SidePiece tempPiece =null;
		
		
		
		return tempPiece;
	}
	
	
	public SidePiece translateSide() {
		SidePiece temp=new SidePiece(this.getFront(), this.getSide());
		
		temp.setFrontColor(getSide());
		temp.setSide(getFront());
		
		return temp;
	}
	public String toString() {
		return (super.toString() + side);
	}
}
