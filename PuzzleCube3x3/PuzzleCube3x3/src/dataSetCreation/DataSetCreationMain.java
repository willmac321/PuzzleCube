package dataSetCreation;

public class DataSetCreationMain {

	private static final int MAX_MOVES = 10;
	
	public static void main(String[] args) {
		DataSet dataSet = new DataSet();
		dataSet.createFileStream("cubeDataSet");
		
		for (int i = 1; i < 10; i++) {
			dataSet.setScramble(i);
			for( int j = 0; j < 2000; j++) {
				//max moves to solve should be 2 I think for one scramble, since all moves are only on the front face;
				dataSet.initializeRandomization();
				dataSet.logCubeState();
				dataSet.runDataSetCreation(MAX_MOVES);
				dataSet.logMoveList(MAX_MOVES);
				dataSet.logCubeState();
				dataSet.logIsSolved();
				dataSet.logNewLine();
			}
		}
		dataSet.closeFileStream();
		
		
		
	}

}
