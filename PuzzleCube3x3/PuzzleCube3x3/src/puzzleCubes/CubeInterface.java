package puzzleCubes;

public interface CubeInterface {

		public void rotateFaceCW();
		public void rotateFaceCCW();
		public void turnRight();
		public void turnLeft();
		public void turnUp();
		public void turnDown(); //for what?
		public char[][] showCurrentState();
		
}
