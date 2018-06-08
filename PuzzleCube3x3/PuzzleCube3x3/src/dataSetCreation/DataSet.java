package dataSetCreation;

import java.util.Random;

import puzzleCubes.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class DataSet {
	
	private int maxRandMoves;
	private CubeMain cube;
	private PrintWriter pw;
	private StringBuilder sb;
	
	public DataSet(CubeMain cube) {
				
		this.cube = cube;
		//System.out.print(cube.toString());
	}
	
	public void setScramble(int maxMoves) {
		maxRandMoves=maxMoves;
	}
	
	public void initializeRandomization() {
		cube.randomize(maxRandMoves);
		//cube.redrawDisplay();
	}
	
	public void createFileStream(String fileName, int maxMoves) {
		try {
			Path str = Paths.get(fileName + ".csv");
			if(Files.exists(str)) {
				pw = new PrintWriter(new FileWriter(new File(fileName + ".csv"), true) );
				
			}
			else {
				pw = new PrintWriter(new FileWriter(new File(fileName + ".csv"), false));
				createHeader(maxMoves);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	private void createHeader(int maxMoves) {
		sb = new StringBuilder();
		sb.append("ScrambleNum,f1,f2,f3,f4,f5,f6,f7,f8,f9,t1,t2,t3,t4,t5,t6,t7,t8,t9,r1,r2,r3,r4,r5,r6,r7,r8,r9,");
		sb.append("b1,b2,b3,b4,b5,b6,b7,b8,b9,l1,l2,l3,l4,l5,l6,l7,l8,l9,ba1,ba2,ba3,ba4,ba5,ba6,ba7,ba8,ba9,");
		for (int i = 1; i < maxMoves + 1; i++) {
			sb.append("m" + i + ",");
			
		}
		sb.append("f1,f2,f3,f4,f5,f6,f7,f8,f9,t1,t2,t3,t4,t5,t6,t7,t8,t9,r1,r2,r3,r4,r5,r6,r7,r8,r9,");
		sb.append("b1,b2,b3,b4,b5,b6,b7,b8,b9,l1,l2,l3,l4,l5,l6,l7,l8,l9,ba1,ba2,ba3,ba4,ba5,ba6,ba7,ba8,ba9,solved,\n");
		pw.write(sb.toString());
	}
	
	public void logNewLine() {
		pw.write("\n");
	}
	
	public void logSolvedState() {
		sb = new StringBuilder();
		sb.append(cube.solvedCubeToString());
		pw.write(sb.toString());
	}
	
	public void logScrambleState(int scr) {
		pw.write(scr + ",");
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
	
	public boolean isAlreadySolved() {
		return cube.isSolved();
	}
	
	public void logMoveList(int spaces) {
		sb = new StringBuilder();
		String str = cube.getMoveList();
		int strL = str.split(",").length;
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
	
	public void logMoveListReverse(int spaces) {
		sb = new StringBuilder();
		String str = cube.getMoveList();
		str = str.trim();
		String[] ary = str.split(",");
		int len = ary.length;
		for(int i = 0; i < len/2 ; i++) {
			String temp = ary[i];
			ary[i] = ary[len - 1 - i];
			ary[len - i - 1] = temp;
		}
		int strL = ary.length;
		if (strL < spaces) {
			spaces = spaces - strL;
		}
		else {
			spaces = 0;
		}
		for(String s : ary) {
		    sb.append(s + ",");
		}
		
		for(int i = 0; i < spaces; i++) {
			sb.append(",");
		}
		
		pw.write(sb.toString());
		cube.clearMoveList();
	}
	
	public void closeFileStream() {
		
		pw.close();
	}
	
	public void runDataSetCreation(int turns) {
		//System.out.print(cube.toString());
		doRandomTurns(turns);
		//cube.redrawDisplay();
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
