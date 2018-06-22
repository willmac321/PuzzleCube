package cubeSolver;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class NeuralNet implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 8750071297770388789L;
	
	private NeuralNetNode[] nodes;
	private int colorNum;
	private double alpha;
	
	public NeuralNet(int colorNum, int moveNum, char[] inputArr, String[] outputArr, double alpha){
		
		this.colorNum = colorNum;
		this.alpha = alpha;
		nodes = new NeuralNetNode[inputArr.length];
		
		createNodes(colorNum, moveNum, inputArr, outputArr, alpha);
		
		calcGradDescForRow(1000);	
		
	}
	
	public void nextInput(char[] inputArr) {
		for(int i = 0; i < nodes.length; i++) {
			nodes[i].setInputs(new InputNode(colorNum, inputArr[i]));
		} 
	}

	public void nextOutput(String[] outputArr) {
		for(int i = 0; i < nodes.length; i++) {
			nodes[i].setOutputs(new OutputNode(alpha, colorNum, outputArr));
		} 
	}
	
	public  void calcGradDescForRow(int iterations) {
		for(int i = 0; i < nodes.length; i++) {
			nodes[i].calcGradDescForRow(iterations);
			//nodes[i].toString();
		}
	}
	
	private void createNodes(int colorNum, int moveNum, char[] inputArr, String[] outputArr, double alpha) {
		
		for(int i = 0; i < inputArr.length; i++) {
			nodes[i] = new NeuralNetNode(colorNum, moveNum, inputArr[i], outputArr, alpha);
		}
		
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