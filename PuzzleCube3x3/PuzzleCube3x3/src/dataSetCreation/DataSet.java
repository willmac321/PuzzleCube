package dataSetCreation;

import java.util.Random;

import puzzleCubes.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class DataSet {
	
	private int maxRandMoves;
	private CubeMain cube;
	private PrintWriter pw;
	private StringBuilder sb;
	
	public DataSet() {
				
		
		// TODO Auto-generated method stub
		cube = new CubeMain();
		//System.out.print(cube.toString());

	}
	
	public void setScramble(int maxMoves) {
		maxRandMoves=maxMoves;
	}
	
	public void initializeRandomization() {
		cube.randomize(maxRandMoves);
		cube.redrawDisplay();
	}
	
	public void createFileStream(String fileName) {
		try {
			File file = new File(fileName + ".csv");
			if(file.exists()) {
				pw = new PrintWriter(new FileWriter(file, true) );
			}
			else {
				pw = new PrintWriter(new FileWriter(file, false));
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void logNewLine() {
		pw.write("\n");
	}
	
	public void logCubeState() {
		sb = new StringBuilder();
		sb.append(cube.cubeToString());
		pw.write(sb.toString());
	}
	
	public void logIsSolved() {
		sb = new StringBuilder();
		sb.append(cube.isSolved());
		pw.write(sb.toString() + ",");
	}
	
	public void logMoveList(int spaces) {
		sb = new StringBuilder();
		String str = cube.getMoveList();
		int strL = str.split(", ").length;
		if (strL < spaces) {
			spaces = spaces - strL;
		}
		else {
			spaces = 0;
		}
		
		sb.append(cube.getMoveList());
		
		for(int i = 0; i < spaces; i++) {
			sb.append(" ,");
		}
		
		pw.write(sb.toString());
	}
	
	public void closeFileStream() {
		
		pw.close();
	}
	
	public void runDataSetCreation(int turns) {
		//System.out.print(cube.toString());
		doRandomTurns(turns);
		cube.redrawDisplay();
		//System.out.print(cube.getMoveList());
	}
	
	public void doRandomTurns(int turns) {
		//number of moves to do total
		Random rand = new Random();
		//while(!cube.isSolved()) {
			cube.clearMoveList();

			for(int i = 0; i < turns; i++) {			
				//move a min of 1 times and max of var times
				int moveNum = rand.nextInt(6)+1;
				doMove(moveNum, cube);
	//			cube.redrawDisplay();
				if(cube.isSolved()) {
					i = turns;
				}
			}
		//}
	}

	private void doMove(int turn, CubeMain c) {
		c.translateCubeMove(turn);
	}
}
