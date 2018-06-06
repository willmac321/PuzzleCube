package dataSetCreation;

import java.util.Random;

import puzzleCubes.*;


public class createDataSet {
	
	private static final int MAX_RAND_MOVES = 60;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CubeMain cube = new CubeMain();
		//System.out.print(cube.toString());
		
		cube.randomize(MAX_RAND_MOVES);
		cube.redrawDisplay();
		System.out.print(cube.toString());
		cube = doRandomTurns(cube, 100);
		cube.redrawDisplay();
		System.out.print(cube.getMoveList());
	}
	
	public static CubeMain doRandomTurns(CubeMain c, int turns) {
		//number of moves to do total
		Random rand = new Random();
		CubeMain temp = c;
		while(!c.isSolved()) {
			c.clearMoveList();

			for(int i = 0; i < turns; i++) {			
				//move a min of 1 times and max of var times
				int moveNum = rand.nextInt(6)+1;
				doMove(moveNum, c);
				if(c.isSolved()) {
					i = turns;
				}
			}
			if(!c.isSolved()) {
				c = temp;
			}
		}
		
		return c;
	}

	private static void doMove(int turn, CubeMain c) {
		c.translateCubeMove(turn);
	}
}
