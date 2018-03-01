package puzzleCubes;

public class CenterPiece extends Piece {
	private CornerPiece topRight, topLeft, botRight, botLeft;
	private SidePiece topSide, rightSide, botSide, leftSide;
	private int orientation;
	
	public CenterPiece(char f) {
		super(f, 0);
		getFront();
	}

	public CenterPiece(CenterPiece piece) {
		this(piece.getFront());
	}
	public void rotateMiddlePiece(int rot) {		
		
		if(!(rot>1) || !(rot<-1)) {
					
				setLastRotation(rot);
				
				setOrientation(rot);
		}
		else {
			System.out.println("Please input rotation value of -3<=x<=3");
			
		}
	}

	public int getOrientation() {
		return orientation;
	}
	
	public void setOrientation(int rotation) {
					
			switch (rotation){					
				case 1:
					
					CornerPiece t = getTopRight();
					SidePiece s = getTopSide();
					
					setTopRight(getTopLeft(),rotation);
					setTopSide(getLeftSide(),rotation);
					setTopLeft(getBotLeft(),rotation);
					setLeftSide(getBotSide(),rotation);
					setBotLeft(getBotRight(),rotation);
					setBotSide(getRightSide(),rotation);
					setRightSide(s,rotation);
					setBotRight(t,rotation);

					break;
					
					
				case -1:
					CornerPiece tr = getTopRight();
					SidePiece sr = getTopSide();
					
					setTopRight(getBotRight(),rotation);
					setTopSide(getRightSide(),rotation);
					setBotRight(getBotLeft(),rotation);
					setRightSide(getBotSide(),rotation);
					setBotLeft(getTopLeft(),rotation);
					setBotSide(getLeftSide(),rotation);
					setLeftSide(sr,rotation);
					setTopLeft(tr,rotation);
					break;
					
					
				default:
					
					System.out.println("no change");
				
				
			}
		
	}

	public CornerPiece getTopRight() {
		return topRight;
	}

	public CornerPiece getTopLeft() {
		return topLeft;
	}

	public CornerPiece getBotRight() {
		return botRight;
	}

	public CornerPiece getBotLeft() {
		return botLeft;
	}

	public SidePiece getTopSide() {
		return topSide;
	}

	public SidePiece getRightSide() {
		return rightSide;
	}

	public SidePiece getBotSide() {
		return botSide;
	}

	public SidePiece getLeftSide() {
		return leftSide;
	}

	public void setTopRight(CornerPiece topRight, int r) {
		this.topRight = topRight;
	}

	public void setTopLeft(CornerPiece topLeft, int r) {
		this.topLeft = topLeft;
	}

	public void setBotRight(CornerPiece botRight, int r) {
		this.botRight = botRight;
	}

	public void setBotLeft(CornerPiece botLeft, int r) {
		this.botLeft = botLeft;
	}

	public void setTopSide(SidePiece topSide, int r) {
		this.topSide = topSide;
	}

	public void setRightSide(SidePiece rightSide, int r) {
		this.rightSide = rightSide;
	}

	public void setBotSide(SidePiece botSide, int r) {
		this.botSide = botSide;
	}

	public void setLeftSide(SidePiece leftSide, int r) {
		this.leftSide = leftSide;
	}

}
