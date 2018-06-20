package cubeSolver;

class InputNode {
	

	int[] inputs;

	
	public InputNode(int size, char feature) {
			inputs = new int[size];
		for(int i = 0; i < size; i++) {
			inputs[i] = findIntFromFeature(feature);		
		}
	}
	
	private int findIntFromFeature(char feature) {
		int rv = 0;
		switch(feature) {
		case 'r':
			rv = 1;
			break;
		case 'o':
			rv = 2;
			break;
		case 'g':
			rv = 3;
			break;
		case 'b':
			rv = 4;
			break;
		case 'y':
			rv = 5;
			break;
		case 'w':
			rv = 6;
			break;
		}
		return rv;
	}
	
	private char findFeatureFromInt(int i) {
		char rv = 'n';
		switch(i) {
		case 1:
			rv = 'r';
			break;
		case 2:
			rv = 'o';
			break;
		case 3:
			rv = 'g';
			break;
		case 4:
			rv = 'b';
			break;
		case 5:
			rv = 'y';
			break;
		case 6:
			rv = 'w';
			break;
		}
		return rv;
	}
	
	public int getInput(int j) {
		return inputs[j];
	}
	
}
