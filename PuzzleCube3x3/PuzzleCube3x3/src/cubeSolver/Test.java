package cubeSolver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;

public class Test {

	
	// TODO put node outputs into another neural net and weight it for final out
	
	private static char[] in;
	private static String[] out;
	private static NeuralNet nn;
	private static String[] movelist;
	private static int lines;
	private static int count;
	private static long startT;
	
	public static void main(String[] args) {
		
		
		String input = "C:\\Users\\Will MacIntyre\\Documents\\RandomPrograms\\Rubicks Cube\\cubeDataSet.csv";
		
		//createNNModel();
		
		openNN("neuralNet.ser");
		
		OpenFile dataSet = new OpenFile(input, 54, 1);
		count = 0;
//		while( count < 5001) {//dataSet.readNextLine()) {
//			dataSet.readNextLine();
//			in = dataSet.getInputs();
//			count++;
//			
//		}
		movelist = new String[1];
		
		while( count < 100) {//dataSet.readNextLine()) {
			dataSet.readNextLine();
			in = dataSet.getInputs();
			out = dataSet.getOutputs();
			count++;
			movelist[0] = (nn.suggestMove(in));
			saveResults();
		}
		
	}
	
	public static void createNNModel() {
		String input = "C:\\Users\\Will MacIntyre\\Documents\\RandomPrograms\\Rubicks Cube\\cubeDataSet.csv";
		
		startT = System.nanoTime();
		
		OpenFile dataSet = new OpenFile(input, 54, 1);
		lines = dataSet.countLines();
		count = 0;
		if (dataSet.readNextLine()) {
			in = dataSet.getInputs();
			out = dataSet.getOutputs();	
		}
		System.out.println("Progress: " + (count / lines) * 100 + "%");
		nn = new NeuralNet(6, 6, in, out, .3 );
		count++;
		
		while( count < 5001) {//dataSet.readNextLine()) {
				dataSet.readNextLine();
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
		System.out.println("file saved");
	}
	
	public static void openNN(String name) {

		try {
			FileInputStream fis = new FileInputStream("neuralNet.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			nn = (NeuralNet)ois.readObject();
			ois.close();
		} catch(IOException e){
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	
	public static void saveResults() {
		try {
			String fileName = "endResult";
			PrintWriter pw;
			Path str = Paths.get(fileName + ".csv");
			if(Files.exists(str)) {
				pw = new PrintWriter(new FileWriter(new File(fileName + ".csv"), true) );
			}
			else {
				pw = new PrintWriter(new FileWriter(new File(fileName + ".csv"), false));
				
			}
			
			StringBuilder sb = new StringBuilder();
			for (char t: in) {
				sb.append(t+",");
			}
			
			for (String s: movelist) {
				sb.append(s + ",");
			}
			
			for (String s: out) {
				sb.append(s + ",");
			}
			
			pw.write(sb.toString() + "\n");
			pw.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}
}
