package cubeSolver;

import java.io.Serializable;

public class NeuralNetNode implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4249350793463784752L;
	private InputNode inputs;
	private OutputNode outputs;
	private Weight weights;
	private double mSE;
	private double mSEold;
	private int counter;
	
	public NeuralNetNode(int inputSize, int outputSize, char input, String[] outputArr, double learningConst) {
		inputs = new InputNode(inputSize, input );
		outputs = new OutputNode(learningConst, outputSize, outputArr);
		weights = new Weight(outputSize, inputSize);
		mSE = Double.MAX_VALUE/2;
		mSEold = Double.MAX_VALUE;
	}

	public void calcGradDescForRow(int iterations) {
		mSE = Double.MAX_VALUE/2;
		mSEold = Double.MAX_VALUE;
		counter = 0;
		while (mSE <= mSEold && counter <= iterations) {
			
			calcNewWeights();
			counter++;
			
		} 
		
	}
	
	public void calcNewWeights() {
		mSEold = mSE;
		outputs.calcActivation(weights, inputs);
		double[][] dw = outputs.calcDeltaWeight(weights, inputs);
		outputs.calcNewWeight(weights, dw);
		
		mSE = outputs.calcMSE();
	}
	
	public String toString() {
		String rv = "";
		System.out.print(outputs.findOutput());	
		System.out.print("  MSE: " + mSE);
		System.out.print("  # of iterations: " + counter + "\n");
		return rv;
	}
	
	public InputNode getInputs() {
		return inputs;
	}

	public OutputNode getOutputs() {
		return outputs;
	}

	public Weight getWeights() {
		return weights;
	}

	public void setInputs(InputNode inputs) {
		this.inputs = inputs;
	}

	public void setOutputs(OutputNode outputs) {
		this.outputs = outputs;
	}

	public void setWeights(Weight weights) {
		this.weights = weights;
	}
	
}
