package dataSetCreation;

import java.text.DecimalFormat;
import java.util.Date;

public class DataSetCreationMain {

	private static final int MAX_MOVES = 50;
	private static long startT;
	public static void main(String[] args) {
		DataSet dataSet = new DataSet();
		dataSet.createFileStream("cubeDataSet", MAX_MOVES);
		System.out.println("creating dataset....");
		startT = System.nanoTime();
		for (int i = 0; i < 20; i++) {
			dataSet.setScramble(i + 1);
			for( int j = 0; j < 10000; j++) {
				//max moves to solve should be 2 I think for one scramble, since all moves are only on the front face;
				dataSet.initializeRandomization();
				if(!dataSet.isAlreadySolved()) {
					dataSet.logScrambleState(i + 1);
					dataSet.logCubeState();
					dataSet.runDataSetCreation(MAX_MOVES);
					dataSet.logMoveList(MAX_MOVES);
					dataSet.logCubeState();
					dataSet.logIsSolved();
					dataSet.logNewLine();
				}
				else {
					j--;
				}
			}
		}
		dataSet.closeFileStream();
		System.out.println("done!");
		DecimalFormat df = new DecimalFormat("###.###");
		System.out.println("Time Elapsed: " + df.format((double)(System.nanoTime() - startT)/1000000000.0) + " seconds");
		
		
		
	}

}
