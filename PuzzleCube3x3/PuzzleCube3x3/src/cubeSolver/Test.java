package cubeSolver;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Test {

	private static char[] in;
	private static String[] out;
	private static NeuralNet nn;
	
	public static void main(String[] args) {
		String input = "C:\\Users\\Will MacIntyre\\Documents\\RandomPrograms\\Rubicks Cube\\cubeDataSet.csv";
		
		OpenFile dataSet = new OpenFile(input, 54, 30);
		if (dataSet.readNextLine()) {
			in = dataSet.getInputs();
			out = dataSet.getOutputs();	
		}
		
		nn = new NeuralNet(in.length, out.length,in, out, .3 );
		
		nn.saveNN();
	
	}

}
