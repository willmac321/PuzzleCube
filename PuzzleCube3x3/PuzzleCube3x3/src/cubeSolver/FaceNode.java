package cubeSolver;

public class FaceNode {
	private static final int CMP_INDEX=4;
	private int[] err;
	private char[] face;

	public FaceNode() {
		err = new int[9];
	}
	
	public FaceNode(char[] face) {
		super();
		this.face=face;
		err=calculateError(this.face);
	}
	
	protected int[] calculateError(char[] f) {
		int[] rv = new int[9];
		for (int i=0;i<f.length;i++) {
			if(f[i]==f[CMP_INDEX]) {
				rv[i]=0;
			}
			else {
				rv[i]=1;
			}				
		}

		return rv;
	}
}
