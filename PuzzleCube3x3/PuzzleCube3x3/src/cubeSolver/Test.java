package cubeSolver;

import java.text.DecimalFormat;

public class Test {

	
	// TODO put node outputs into another neural net and weight it for final out
	
	private static char[] in;
	private static String[] out;
	private static NeuralNet nn;
	
	private static int lines;
	private static int count;
	private static long startT;
	
	public static void main(String[] args) {
		String input = "C:\\Users\\Will MacIntyre\\Documents\\RandomPrograms\\Rubicks Cube\\cubeDataSet.csv";
		
		startT = System.nanoTime();
		
		OpenFile dataSet = new OpenFile(input, 54, 1);
		lines = dataSet.countLines();
		count = 0;
		if (dataSet.readNextLine()) {
			in = dataSet.getInputs();
			out = dataSet.getOutputs();	
		}
		System.out.println("Progress: " + (double)(count / lines) * 100 + "%");
		nn = new NeuralNet(6, 6, in, out, .3 );
		count++;
		
		while(dataSet.readNextLine()) {
				in = dataSet.getInputs();
				out = dataSet.getOutputs();	
			
			nn.nextInput(in);
			nn.nextOutput(out);		
			nn.calcGradDescForRow(1000);
			count++;
			
			if(count % 1000 == 0) {
				DecimalFormat df = new DecimalFormat("###.###");
				System.out.println("Progress: " + (Math.round((double)count / (double)lines * 10000)) / 100 + "% TE: " + df.format((double)(System.nanoTime() - startT)/(1000000000.0 * 60)) + " minutes");
			}
		}
		nn.saveNN();
	}

}
