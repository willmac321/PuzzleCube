package puzzleCubes;
import java.awt.EventQueue;
import java.util.Arrays;

public class CubeMain implements CubeInterface {
	
	private CubeDisplay cubeDisp;
	private Cube cube;
	    /*
		 * 0,1,2
		 * 3,4,5
		 * 6,7,8
		 */
	public CubeMain() {
		cube = new Cube();
		cube = cube.createSolvedCube();
		cubeDisp = new CubeDisplay(cube);
		cubeDisp.createAndShowGui();
	}
	
	public char[][] showCurrentState() {
		return cube.showCurrentState();
	}
	
	public String[] showFaceEdges(Cube test, int faceNum) {
		
		return cube.showFaceArray(test, faceNum);
	}
	
	public String toString() {
		//System.out.print(cube.showFrontRows());
		return cube.toString();
	}

	public String cubeToString() {
		return Arrays.toString(showCurrentState());
	}
	
	public void turnRight() {
		cube.turnRight();
		
	}
		
	public void turnLeft() {
		cube.turnLeft();

	}
	
	public void turnUp() {
		cube.turnUp();

	}
	
	public void turnDown() {
		cube.turnDown();

	}
	
	public void rotateFaceCCW() {
		cube.rotateFaceCCW();

	}
	
	public void rotateFaceCW() {
		cube.rotateFaceCW();
	}
	
	public void randomize(int rNum) {
		cube.randomize(rNum);
	}
	
	public String getMoveList() {
		return cube.printMoveArray();
	}
	
	public void clearMoveList() {
		cube.removeAllMoves();
	}
	
	public boolean isSolved() {
		return cube.isSolved();
	}
	
	public void translateCubeMove(int ranInt) {
		cube = cube.intMove(cube, ranInt);
		char[][] temp = cube.showCurrentState();
		cube.setFaces(temp[0], temp[1], temp[2], temp[3], temp[4], temp[5]);
		
	}
	
	public void redrawDisplay() {
		EventQueue.invokeLater(new Runnable() { public void run() {
	        cubeDisp.reDrawCube();
	    }});
	}
}
