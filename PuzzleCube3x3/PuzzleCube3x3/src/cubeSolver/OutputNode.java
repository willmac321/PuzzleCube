package cubeSolver;

import java.io.Serializable;

class OutputNode implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7105856355764085532L;
	double[] out;
	double[] error;
	double net;
	double alpha;
	int size;
	
	public OutputNode(double learningConst, int size) {
		alpha = learningConst;
		this.size = size;
		net = 0;
		out = new double[size];
		error = new double[size];
	}
	
	public double[] calcActivation(Weight weight, InputNode input) {
		for(int j = 0; j < size; j++) {
			out[j] = 1 / (1 + Math.exp(-1 * calcNet(weight, input, j)));
		} 			
		return out;	
	}
	
	private double calcNet(Weight weight, InputNode input, int i) {
		net = 0;		
		for(int j = 0; j < weight.getSizeCol(0); j++) {
			net += weight.getWeight(i, j) * input.getInput(j);
		}	
		return net;		
	}
	
	public double[][] calcDeltaWeight(Weight weight, InputNode input) {
		double[][] dw = new double[weight.getSizeRow()][weight.getSizeCol(0)];
		for(int i = 0; i < dw.length; i++) {
			for(int j = 0; j < dw[0].length; j++) {
				dw[i][j] = alpha * ( (i + 1) - out[i]) * out[i] * (1 - out[i]) * input.getInput(j); 
			}
		}
		return dw;	
	}
	
	public void calcNewWeight(Weight weight, double[][] dw) {
		for(int i = 0; i < dw.length; i++) {
			for(int j = 0; j < dw[0].length; j++) {
				weight.setWeight(i, j, dw[i][j] + weight.getWeight(i, j));
			}
		}
	}
	
	public double calcMSE() {
		double rv = 0;
		for(int j = 0; j < error.length; j++) {
			error[j] = Math.pow((j - out[j]),2);
			rv += error[j];
		}
		return rv;
	}
	
	public int findIntFromFeature(String feature) {
		int rv = 0;
		switch(feature) {
		case "CW":
			rv = 1;
			break;
		case "CCW":
			rv = 2;
			break;
		case "UP":
			rv = 3;
			break;
		case "D":
			rv = 4;
			break;
		case "L":
			rv = 5;
			break;
		case "R":
			rv = 6;
			break;
		default:
			System.out.println("Error 418: I'm a little teapot");		
		}
		return rv;
	}
	
	public String getOutput(int i) {
		String str = "";
		switch(i) {
		case 1:
			str = ("CW");
			break;
		case 2:
			str = ("CCW");
			break;
		case 3:
			str = ("UP");
			break;
		case 4:
			str = ("D");
			break;
		case 5:
			str = ("L");
			break;
		case 6:
			str = ("R");
			break;
		default:
			System.out.println("Error 418: I'm a little teapot");		
		}		
		return str;
	}
	
	public void setAlpha(double a) {
		alpha = a;
	}
	
	public double getAlpha() {
		return alpha;
	}
}
