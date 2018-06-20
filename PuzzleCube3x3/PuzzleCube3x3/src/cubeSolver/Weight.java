package cubeSolver;

import java.util.Random;

class Weight {

	double[][] weight;
	
	public Weight(int row, int col) {
		
		weight = new double[row][col];
		createWeightArray(row, col);
	}

	private void createWeightArray(int row, int col) {
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < col; j++) {
				weight[i][j] = (initWeight());
			}
		}
	}
	
	private double initWeight() {
		
		Random rand = new Random();
		double r = rand.nextDouble();
				
		return r;
	}
	
	public double getWeight(int i, int j) {
		return weight[i][j];
	}
	
	public void setWeight(int i, int j, double newWeight) {
		weight[i][j] = newWeight;
	}
	
	public int getSizeCol(int i) {
		return weight[i].length;
	}
	
	public int getSizeRow() {
		return weight.length;
	}
}
