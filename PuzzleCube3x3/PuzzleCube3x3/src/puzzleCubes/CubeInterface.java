package puzzleCubes;

public interface CubeInterface {

		public int rotateFaceCW();
		public int rotateFaceCCW();
		public void turnRight();
		public void turnLeft();
		public void turnUp();
		public void turnDown(); //for what?
		public char[][] showCurrentState();
		
}
