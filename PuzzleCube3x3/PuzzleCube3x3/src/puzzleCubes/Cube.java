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
		faces[1]=(new Face(top,back, right, front,left)); //top
		faces[2]=(new Face(right,back,bot, front, top)); //right
		faces[3]=(new Face(bot,back, left, front, right)); //bot
		faces[4]=(new Face(left,back, top, front,bot)); //left		
		faces[5]=(new Face(back,top,left, bot,right ));  //back
		faces[5].rotateFace(2);
		
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

	private void rotateAndChangeRows(int rotation,int front, int top, int right, int bot, int left) {
		Face temp=new Face();
		for(int i=0;i<rotation;i++) {
			faces[front].rotateFace(1);
			temp.setFace(faces[front]);
			faces[top].changeTopRow(temp.getTopRow());
			faces[right].changeRightRow(temp.getRightRow());
			faces[bot].changeBotRow(temp.getBotRow());
			faces[left].changeLeftRow(temp.getLeftRow());
		}
	}
	@Override
	public void turnRight() {
		turnHoriz(1);
		
	}

	@Override
	public void turnLeft() {
		turnHoriz(3);
		
	}

	
	private void turnHoriz(int rotations) {
		//turns right
		// 0 front, 1 top,2 right,3 bot,4 left,5 back
		for(int i=0;i<rotations;i++) {
			//Face backTemp = new Face( faces[5].getFace().ge;
			
			
			Face fTemp=faces[0];
		
			faces[0].setFace(faces[2]);
			//faces[2].flipFace();
			Face bTemp=new Face();
			bTemp.setFace(faces[5]);
			bTemp=bTemp.rotateFace(2);
			faces[2].setFace(bTemp);
			//faces[2].rotateFace(2);
			//faces[2];
			faces[5].setFace(faces[4].rotateFace(2));
			//faces[5].setRows();
			faces[4].setFace(fTemp);
			faces[1].rotateFace(3);
			faces[3].rotateFace(1);
			
		}
		setAdjRows();
	}
	
	private void turnVert(int rotations) {
		//turns up
		//front, top, right, bot, left, back
		
		for(int i=0;i<rotations;i++) {
			
			Face temp = faces[0];
			faces[0]=faces[3];
			faces[3]=faces[5];		
			faces[5]=faces[1];
			faces[1]=temp;			
			faces[2].rotateFace(3);
			faces[4].rotateFace(1);
			setAdjRows();

		}
		
		//char[][] t = showCurrentState();
		//setFaces(t[3],t[0],t[2],t[5],t[4],t[1]);
		//faces[5].rotateFace(2);
		//setAdjRows();
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
