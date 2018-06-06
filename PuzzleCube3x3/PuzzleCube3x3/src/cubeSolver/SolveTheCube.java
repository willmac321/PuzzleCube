package cubeSolver;

import puzzleCubes.*;



public class SolveTheCube {
	
	private FaceNode[] fNodes;
	
	
	private CubeMain cube;
	private char[][] colors;
	private int[] errorSum;
	//private int[][] MOVE_MATRIX = {{1,0,0,0,0,0},{0,1,0,0,0,0},{0,0,1,0,0,0},{0,0,0,1,0,0},{0,0,0,0,1,0},{0,0,0,0,0,1}}; //right, left, up, down, cw, ccw
	private int error;
	private MoveWeights[] weights; //right, left, up, down, cw, ccw
	
	public SolveTheCube(CubeMain cube) {
		this.cube=cube;
		initialize();	
		error=0;
		weights = new MoveWeights[6];
		
		loopToSolve();
	}
	
	private void initialize() {		
		colors=cube.showCurrentState();	
		fNodes = new FaceNode[6];
		setFNodes(colors);
		errorSum=calculateError(colors);
	}
	
	protected void loopToSolve() {
		
		while(error>0) {
			initialize();
			Cube testCube = new Cube(colors[0],colors[1],colors[2],colors[3],colors[4],colors[5]);
//			
//				switch(suggestNextMove()) {
//				case 0:
//					testCube.turnRight();
//					break;
//				case 1:
//					testCube.turnLeft();
//					break;
//				case 2:
//					testCube.turnUp();
//					break;
//				case 3:
//					testCube.turnDown();
//					break;
//				case 4:
//					testCube.rotateFaceCW();
//					break;
//				case 5:
//					testCube.rotateFaceCCW();
//					break;
//				}
				
//				calculateWeights(calculateError(testCube.showCurrentState()));
	
						
		}
		
	}
	
	protected void setWeights(int[] t) {
		for (int i=0;i<t.length;i++) {
			weights[i].setWeight(t[i]);			
		}
	}
	
	protected void calculateWeights(int[] t) {
		//error calculation goes here....
		
	}
	
	protected int suggestNextMove() {
		double max =0;
		int rv=0;
		initialize();
			
			for (MoveWeights w : weights) {
				if(w.getWeight()>max) {
					max=w.getWeight();
					rv++;
				}
			}
		return rv;
	}
	
	private int[] calculateError(char[][] color) {
		int i =0;
		error=0;
		int[] errorSum = new int[9];
		for (FaceNode f : fNodes) {
			int[] testSum= f.calculateError(color[i]);
			i++;
			
			for (int r=0;r< errorSum.length;r++) {
				errorSum[r]+=testSum[r];
				error+=testSum[r];
			}
		}
		return errorSum;
	}
	
	private void setFNodes(char[][] c) {
		for (int r=0;r< c.length;r++) {
			fNodes[r]=new FaceNode(c[r]);
		}
		
	}
}
