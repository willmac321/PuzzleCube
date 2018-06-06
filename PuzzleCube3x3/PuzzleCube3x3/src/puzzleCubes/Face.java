package puzzleCubes;

public class Face {

	private CornerPiece bl, br, tr,  tl;
	private SidePiece b,r,t,l;
	private CenterPiece m;
	private Piece[] topRow, botRow, leftRow, rightRow;
	private Piece[] front;
	private int cR, cW, cG, cO, cB, cY;
	protected Face()
	{
		topRow= null;
		botRow=null;
		leftRow=null;
		rightRow=null;
		front= null;
	}
	
	
	protected Face( char[] front, char[] top, char[] right, char[] bot, char[] left) {
		tl= new CornerPiece(front[0], top[6], left[2]) ; //front top side
		tr = new CornerPiece(front[2], right[0], top[8]);
		bl = new CornerPiece(front[6],left[8],bot[0] );
		br = new CornerPiece(front[8], bot[2],right[6]);
		
		t = new SidePiece(front[1],top[7]);
		r = new SidePiece(front[5],right[3]);
		b = new SidePiece(front[7], bot[1]);
		l = new SidePiece(front[3], left[5]);
		
		m= new CenterPiece(front[4]);
		
		setRows();
	}
	
	protected Face(CornerPiece bl, CornerPiece br, CornerPiece tr, CornerPiece tl, SidePiece b, SidePiece r, SidePiece t,
			SidePiece l, CenterPiece m) {
		super();
		this.bl = bl;
		this.br = br;
		this.tr = tr;
		this.tl = tl;
		this.b = b;
		this.r = r;
		this.t = t;
		this.l = l;
		this.m = m;
		setRows();
	}

	/**
	 * Start on bottom left and go ccw
	 * @param rot int for ccw 
	 */
	protected Face rotateFace(int rot) {
		Face rv = new Face();
		for(int i =0; i<rot;i++) {
			
			CornerPiece temp = new CornerPiece(bl.getFront(),bl.getTop(),bl.getSide());
			
			SidePiece sideTemp = new SidePiece(b.getFront(),b.getSide());
			
			bl=tl;
			tl=tr;
			tr=br;
			br=temp;
			b=l;
			l=t;
			t=r;
			r=sideTemp;
			setRows();
		}
		
		
		rv.setFace(this);
		//middle.rotateMiddlePiece(rot);
		//orientation=middle.getOrientation();
		return rv;
	}
	
	protected void changeBotRow(Piece[] botRow) {
		tl=(CornerPiece) botRow[0];
		t=(SidePiece) botRow[1];
		tr=(CornerPiece) botRow[2];
		setRows();
	}
	protected void changeRightRow(Piece[] botRow) {
		tl=(CornerPiece) botRow[2];
		l=(SidePiece) botRow[1];
		bl=(CornerPiece) botRow[0];
		setRows();
	}
	protected void changeLeftRow(Piece[] botRow) {
		tr=(CornerPiece) botRow[2];
		r=(SidePiece) botRow[1];
		br=(CornerPiece) botRow[0];
		setRows();
	}
	protected void changeTopRow(Piece[] botRow) {
		bl=(CornerPiece) botRow[2];
		b=(SidePiece) botRow[1];
		br=(CornerPiece) botRow[0];
		setRows();
	}
	protected void setRows() {
		
		topRow=new Piece[] {tl.translateCornerPiece(), t.translateSide(), tr.translateCornerPiece().translateCornerPiece()};
		rightRow = new Piece[] {br.translateCornerPiece().translateCornerPiece(), r.translateSide(), tr.translateCornerPiece()};
		botRow = new Piece[] {bl.translateCornerPiece().translateCornerPiece(), b.translateSide(), br.translateCornerPiece()};
		leftRow = new Piece[] { bl.translateCornerPiece() , l.translateSide(), tl.translateCornerPiece().translateCornerPiece()};
		setPieceArray();
		setColorCount();
	}
		
	private void setPieceArray() {
		front = new Piece[] {tl,t,tr,l,m,r,bl,b,br};
	}
	
	private void setColorCount() {
		cW=0;
		cO=0;
		cR=0;
		cG=0;
		cB=0;
		cY=0;
	
		for (Piece piece:front) {
			switch(piece.getFront()) {
			case 'w':
				cW++;
				break;
			case 'o':
				cO++;
				break;
			case 'r':
				cR++;
				break;
			case 'g':
				cG++;
				break;
			case 'b':
				cB++;
				break;
			case 'y':
				cY++;
				break;
			
			}
		}
		
	}
	
	public int[] getColorCount() {
		
		int[] rv = {cW,cO,cR,cG,cB,cY};
		return rv;
	}
	protected Piece[] getTopRow() {
		return topRow;
	}

	protected Piece[] getBotRow() {
		return botRow;
	}

	protected char getMiddle() {
		return m.getFront();
	}
	
	protected Piece[] getLeftRow() {
		return leftRow;
	}

	protected Piece[] getRightRow() {
		return rightRow;
	}

	protected void setColor(char c, int index) {
		front[index].setFrontColor(c);
		setRows();
	}
	protected char[][] getFaceColors() {
		
		return new char[][] {{tl.getFront(), t.getFront(), tr.getFront()},
			{l.getFront(), m.getFront(), r.getFront()},
			{bl.getFront(), b.getFront(), br.getFront()}};	
	}
	
	public char[] colorToSingleArray(char[][] colors) {
		
		char[] t = new char[9];
		int i=0;
		for (int r =0;r<3;r++) {
			for (int c =0; c<3;c++) {
				t[i]=colors[r][c];
						i++;
			}
		}
		
		
		return t;
	}
	
	public String toString(){
		String rv ="";
		char[][] temp = getFaceColors();
		for(int r =0; r<3;r++) {
			for(int c=0; c<3; c++) {
				rv+=temp[r][c];
			}
			rv+="\n";
		}
		
		return rv;
	}
	
	public Face getFace() {
		return this;
	}
	
	protected void setFace(Face face) {
		
		this.bl = new CornerPiece(face.bl);
		this.br = new CornerPiece(face.br);
		this.tr = new CornerPiece(face.tr);
		this.tl = new CornerPiece(face.tl);
		this.b = new SidePiece(face.b);
		this.r = new SidePiece(face.r);
		this.t = new SidePiece(face.t);
		this.l = new SidePiece(face.l);
		this.m = new CenterPiece(face.m);
		setRows();	
	}
	/*
	 * 
	 * 	private void setAdjacent() {
		bl.setRight(b);
		bl.setLeft(l);
		b.setRight(br);
		b.setLeft(bl);
		br.setRight(r);
		br.setLeft(b);
		l.setRight(tr);
		l.setLeft(bl);
		tr.setRight(t);
		tr.setLeft(l);
		t.setRight(tl);
		t.setLeft(tr);
		tl.setRight(r);
		tl.setLeft(t);
		l.setRight(bl);
		l.setLeft(tl);
		
	}
	public Piece getPiece(int r, int c) {
		return face[r][c];
	}
	
	public void setPieceSide(int r, int c, char color) {
		if (r!=1 && c!=1) {
			((CornerPiece) face[r][c]).setSide(color);
		}
		else if(r==c) {
			
		}
		else {
			((SidePiece) face[r][c]).setSide(color);;
		}
	}
	
	public void setPieceTop(int r, int c, char color) {
		if (r!=1 && c!=1) {
			((CornerPiece) face[r][c]).setTop(color);
		}
	}
	

	
	*/


}
