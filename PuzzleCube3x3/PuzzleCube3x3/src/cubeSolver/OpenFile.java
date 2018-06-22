package cubeSolver;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

class OpenFile {
	
	private String[] train;
	private String fileName;
	private BufferedReader br;
	private int len;
	private String csvSplit = ",";
	private int iLength;
	private int oLength;
	
	public OpenFile(String inFileName, int inputLength, int outputLength) {
		br = null;
		iLength = inputLength;
		oLength = outputLength;
		fileName = inFileName;
		openFile(fileName);
	}
	
	private void openFile(String inFile) {
		String header = "";
		try {
			br = new BufferedReader(new FileReader(inFile));	
			if ((header = br.readLine()) != null) {
				len = header.split(csvSplit).length;	
				train = new String[len];
			}
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int countLines() {
		int rv = 0;
		try {
			while ((br.readLine()) != null) {
				rv++;
			}
		
			br.close();
			openFile(fileName);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		return rv;
	}
	
	public boolean readNextLine() {
		boolean rv = false;
		String line = "";
		try {
			if ((line = br.readLine()) != null) {
				train = line.split(",");
				rv = true;
			}
			else {
				train = null;
				br.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return rv;
	}
	
	public char[] getInputs() {
		char[] rv = new char[iLength];
		for(int i = 0; i < iLength; i++) {
			rv[i] = train[i].trim().charAt(0);
		}
		return rv;
	}
	
	public String[] getOutputs() {
		String[] rv = new String[oLength];
		for(int i = iLength; i < oLength + iLength; i++) {
			rv[i - iLength] = train[i];
		}
		return rv;
	}
	
}
