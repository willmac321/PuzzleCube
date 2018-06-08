package dataSetCreation;

import java.awt.Label;
import java.text.DecimalFormat;
import java.util.Date;
import puzzleCubes.*;

public class DataSetCreationMain {

	private long startT;
	
	
	public DataSetCreationMain(CubeMain cube, int maxMoves, int scramble, int iterations, String saveFileName, Label lblStatus) {
		DataSet dataSet = new DataSet(cube);
		dataSet.createFileStream(saveFileName,maxMoves);
		lblStatus.setText(setProgressIndicator());
		startT = System.nanoTime();
		for (int i = 0; i < scramble; i++) {
			lblStatus.setText(setProgressIndicator());
			dataSet.setScramble(i + 1);
			for( int j = 0; j < iterations; j++) {
				lblStatus.setText(setProgressIndicator());
				//max moves to solve should be 2 I think for one scramble, since all moves are only on the front face;
				dataSet.initializeRandomization();
				if(!dataSet.isAlreadySolved()) {
					dataSet.logScrambleState(i + 1);
					dataSet.logCubeState();
					dataSet.runDataSetCreation(maxMoves);
					dataSet.logMoveList(maxMoves);
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
		
		DecimalFormat df = new DecimalFormat("###.###");
		lblStatus.setText("Done! \nTime Elapsed: " + df.format((double)(System.nanoTime() - startT)/1000000000.0) + " seconds");
		
		
		
	}
	
	public String setProgressIndicator() {
		
		int t = ((int)(System.nanoTime()/1000000000.0)) % 10;
		String str = "";
		switch (t) {
		case 0:
		case 1:
		case 8:
			str = "|";
		
			break;
		case 2:
		case 3:
		case 9:
			str = "/";
			break;
		case 4:
		case 5:
		case 10:
			str = "-";
			break;
		case 6:
		case 7:		
			str = "\\";
			break;
		default:
			str = "*";		
		}
		
		return "creating dataset  " + str;	
	}

}
