package cubeSolver;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class NeuralNet implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4249350793463784752L;
	private InputNode inputs;
	private OutputNode outputs;
	private Weight weights;
	
	public NeuralNet(int inputSize, int outputSize, char[] inputArr, String[] outputArr, double learningConst) {
		inputs = new InputNode(inputSize, inputArr );
		outputs = new OutputNode(learningConst, outputSize);
		weights = new Weight(outputSize, inputSize);
	}
	
	public boolean saveNN() {
		boolean rv = false;
		
		try {
			FileOutputStream fos = new FileOutputStream("neuralNet.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(this);
			oos.close();
			rv = true;
		}
		catch(IOException e){
			e.printStackTrace();
		}
		
		return rv;
	}
	
}
