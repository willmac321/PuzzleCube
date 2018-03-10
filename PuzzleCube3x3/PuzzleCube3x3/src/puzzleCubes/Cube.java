package puzzleCubes;

public class Cube implements CubeInterface{

	private Face[] faces; //front, top, right, bot, left, back
	public Cube() {
		
	}
	
	public Cube(char[] front, char[] top,  char[] right, char[] bot,char[] left, char[] back) {
		super();
		setFaces(front, top,  right, bot, left, back);
	}

	private void setFaces(char[] front, char[] top,  char[] right, char[] bot,char[] left, char[] back) {
		faces=new Face[6];		
		faces[0]=(new Face(front, top, right, bot ,left)); //front
		faces[1]=(new Face(top,back, rotateArray(rotateArray(rotateArray(right))), front,rotateArray(left))); //top
		faces[2]=(new Face(right,rotateArray(top),rotateArray(rotateArray(back)), rotateArray(rotateArray(bot)), front)); //right
		faces[3]=(new Face(bot,front,rotateArray(right),back,rotateArray(rotateArray(left)))); //bot
		faces[4]=(new Face(left, rotateArray(rotateArray(top)), front,rotateArray(bot), rotateArray(rotateArray(back)))); //left		
		faces[5]=(new Face(back,bot,rotateArray(rotateArray(right)), top, rotateArray(rotateArray(left ))));  //back
		//faces[5].rotateFace(2);	
	}
	
	@Override
	public int rotateFaceCW() {
		return rotateFaceCounterClockWise(3);
	}

	@Override
	public int rotateFaceCCW() {
		return rotateFaceCounterClockWise(1);
	}
	
	private int rotateFaceCounterClockWise(int rotations) {
		rotateAndChangeRows(rotations,0,1,2,3,4);
		setAdjRows();
		return 0;
	}

	private char[] rotateArray(char[] ary) {
		//clockwise rotate
		char [] rv = new char[9];
		
		rv[0]=ary[6];
		rv[1]=ary[3];
		rv[2]=ary[0];
		rv[3]=ary[7];
		rv[4]=ary[4];
		rv[5]=ary[1];
		rv[6]=ary[8];
		rv[7]=ary[5];
		rv[8]=ary[2];
		
		return rv;
		
	}
	
	private void rotateAndChangeRows(int rotation,int front, int top, int right, int bot, int left) {
		Face temp=new Face();
		Piece[] topTemp=new Piece[3];
		CornerPiece pieceTemp = new CornerPiece();
		for(int i=0;i<rotation;i++) {
			faces[0].rotateFace(1);
			temp.setFace(faces[front]);
			topTemp=temp.getTopRow();
			pieceTemp=(CornerPiece) topTemp[0];
			topTemp[0]=topTemp[2];
			topTemp[2]=pieceTemp;
			faces[top].changeTopRow(topTemp);
			faces[right].changeRightRow(temp.getRightRow());
			faces[bot].changeBotRow(temp.getBotRow());
			faces[left].changeLeftRow(temp.getLeftRow());
		}
		setCurrentState();
	}
	
	protected void setColor(char c,int face, int index) {
			faces[face].setColor(c,index);
			setCurrentState();
	}
	
	protected boolean checkConfig() {
		boolean rv = true;
		int[] count = new int[6];
		for (Face face:faces) {
			int[] temp = face.getColorCount();
			int i=0;
			for(int c:temp) {
				count[i]+=c;
				if(count[i]>10 || count[i]<0) {
					rv=false;
					
				}
				i++;				
			}
		}		
		return rv;		
	}
	
	@Override
	public void turnRight() {
		turnHoriz(3);
		
	}

	@Override
	public void turnLeft() {
		turnHoriz(1);
		
	}
	
	private void setCurrentState() {
		char[][] t = showCurrentState();
		setFaces(t[0],t[1],t[2],t[3],t[4],t[5]);
		setAdjRows();
	}
	
	private void turnHoriz(int rotations) {
		//turns right
		// 0 front, 1 top,2 right,3 bot,4 left,5 back
		Face temp = new Face();
		for(int i=0;i<rotations;i++) {
			//Face backTemp = new Face( faces[5].getFace().ge;
			
			
			temp.setFace(faces[0]);
		
			faces[0].setFace(faces[2]);
			//faces[2].flipFace();
			Face bTemp=new Face();
			bTemp.setFace(faces[5]);
			bTemp=bTemp.rotateFace(2);
			faces[2].setFace(bTemp);
			//faces[2].rotateFace(2);
			//faces[2];
			faces[5].setFace(faces[4]);
			faces[5].rotateFace(2);
			faces[4].setFace(temp);
			faces[1].rotateFace(3);
			faces[3].rotateFace(1);
			
		}
		setCurrentState();
		
	}
	
	private void turnVert(int rotations) {
		//turns up
		//front, top, right, bot, left, back
			Face temp = new Face();
		for(int i=0;i<rotations;i++) {
			
			temp.setFace(faces[0]);
			faces[0].setFace(faces[3]);
			faces[3].setFace(faces[5]);		
			faces[5].setFace(faces[1]);
			
			faces[1].setFace(temp);			
			faces[2].rotateFace(3);
			faces[4].rotateFace(1);
			
			//setAdjRows();

		}
		//faces[5].rotateFace(2);
		setCurrentState();

	}
	@Override
	public void turnUp() {
		turnVert(1);
	}

	@Override
	public void turnDown() {
		turnVert(3);
	}

	@Override
	public char[][]showCurrentState() {
		char[][] temp = new char[6][];
		int i=0;
		for (Face face:faces) {
			char[] t = face.colorToSingleArray(face.getFaceColors());
			temp[i]=t;
			i++;
		}
		return temp;
	}
	private void setAdjRows() {
		for(int i=0;i<faces.length;i++) {
			faces[i].setRows();
		}
	}
	
	public String showFrontRows() {
		String rv=" ";
		Piece[] row = faces[0].getTopRow();
		for (int i=0;i<3;i++) {
			rv+=row[i].toString() + " ";
		}
		rv+=" \n";
		row = faces[0].getLeftRow();
		Piece[] other = faces[0].getRightRow();
		for (int i=0;i<3;i++) {
			rv+=row[i].toString()+"   "+other[i].toString() + "\n";
		}
		
		other = faces[0].getBotRow();
		for (int i=0;i<3;i++) {
			rv+=" " + other[i].toString();
		}
		rv+="\n";
		
		
		
		return rv;
	}
	public String showBackRows() {
		String rv=" ";
		Piece[] row = faces[5].getTopRow();
		for (int i=0;i<3;i++) {
			rv+=row[i].toString() + " ";
		}
		rv+=" \n";
		row = faces[5].getLeftRow();
		Piece[] other = faces[5].getRightRow();
		for (int i=0;i<3;i++) {
			rv+=row[i].toString()+"   "+other[i].toString() + "\n";
		}		
		other = faces[5].getBotRow();
		for (int i=0;i<3;i++) {
			rv+=" " + other[i].toString();
		}
		rv+="\n";
		return rv;
	}
	public String showRightRows() {
		String rv=" ";
		Piece[] row = faces[2].getTopRow();
		for (int i=0;i<3;i++) {
			rv+=row[i].toString() + " ";
		}
		rv+=" \n";
		row = faces[2].getLeftRow();
		Piece[] other = faces[2].getRightRow();
		for (int i=0;i<3;i++) {
			rv+=row[i].toString()+"   "+other[i].toString() + "\n";
		}		
		other = faces[2].getBotRow();
		for (int i=0;i<3;i++) {
			rv+=" " + other[i].toString();
		}
		rv+="\n";
		return rv;
	}
	public String toString() {	
		String rv = "";		
		rv="Front: \n" + faces[0].toString() +
				"Top: \n" + faces[1].toString() +
				"Right: \n" + faces[2].toString() +
				"Bottom: \n" + faces[3].toString() +
				"Left: \n" + faces[4].toString() + 
				"Back: \n" + faces[5].toString() +"\n";
		return rv;		
	}

}