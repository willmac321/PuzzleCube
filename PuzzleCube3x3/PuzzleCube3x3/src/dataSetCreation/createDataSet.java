package dataSetCreation;

import puzzleCubes.*;


public class createDataSet {

	private static final int MAX_RAND_MOVES = 60;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CubeMain cube = new CubeMain();
		//System.out.print(cube.toString());
		
		cube.randomize(MAX_RAND_MOVES);
		cube.redrawDisplay();
		System.out.print(cube.cubeToString());
	}

}
