package puzzleCubes;
import java.util.*;
public class CubeMain implements CubeInterface {

	private CubeDisplay cubeDisp;
	private Cube cube;
	    /*
		 * 0,1,2
		 * 3,4,5
		 * 6,7,8
		 */
		public CubeMain() {
		
		char[][] test = new char[6][];
		

		test[0]=new char[] {'w','w','w','w','w','w','w','w','w'};
		 
		//char[][] arrTopFace = 1
		test[1]=new char[]{'r','r','r','r','r','r','r','r','r'};
		//test[1]=new char[]{'g','g','g','r','r','r','b','b','b'};
		//char[][] arrRightFace = 2
		test[2]=new char[]{'g','g','g','g','g','g','g','g','g'};
		//test[2]=(new char[]{'r','g','o','r','g','o','r','g','o'});
		//char[][] arrBotFace =3
		test[3]=new char[]{'o','o','o','o','o','o','o','o','o'};
		//test[3]=new char[]{'g','g','g','o','o','o','b','b','b'};
		//char[][]  arrLeftFace = 4
		test[4]=new char[]{'b','b','b','b','b','b','b','b','b'};
		//test[4] = new char[]{'r','b','o','r','b','o','r','b','o'};
		//char[][] arrBackFace = 5
		test[5]=new char[]{'y','y','y','y','y','y','y','y','y'};
		//Face front = new Face(arrFrontFace);

		cube = new Cube(test[0],test[1],test[2],test[3],test[4],test[5]);
		/*
		System.out.print(cube.showFrontRows());
		System.out.print("BackRows\n");
		System.out.print(cube.showBackRows());
		System.out.print(cube.toString());
		
	    cube.rotateFaceCW();
		 
		cube.turnUp();

		cube.rotateFaceCW();
		System.out.println("front is back");
		System.out.print(cube.showFrontRows());
		System.out.print("BackRows\n");
		System.out.print(cube.showBackRows());
		System.out.print("RightRows\n");
		System.out.print(cube.showRightRows());
		System.out.print(cube.toString());
				
		cube.turnRight();
		cube.turnDown();
		cube.turnRight();

		cube.turnRight();

		cube.rotateFaceCW();

		cube.rotateFaceCCW();
		cube.turnLeft();
		cube.turnLeft();
		cube.turnUp();
		cube.turnLeft();
		cube.rotateFaceCCW();
		cube.turnDown();
		cube.rotateFaceCCW();		
		
		System.out.println("back to normal");
		System.out.print(cube.showFrontRows());
		System.out.print("BackRows\n");
		System.out.print(cube.showBackRows());
		System.out.print(cube.toString());
		*/
		cubeDisp = new CubeDisplay(cube);
		
		cubeDisp.createAndShowGui();
		
	}
	
	public void turnRight() {
		cube.turnRight();
		cubeDisp.reDrawCube();
	}
		
	public void turnLeft() {
		cube.turnLeft();
		cubeDisp.reDrawCube();
	}
	
	public void turnUp() {
		cube.turnUp();
		cubeDisp.reDrawCube();
	}
	
	public void turnDown() {
		cube.turnDown();
		cubeDisp.reDrawCube();
	}
	
	public void rotateFaceCCW() {
		cube.rotateFaceCCW();
		cubeDisp.reDrawCube();
	}
	
	public void rotateFaceCW() {
		cube.rotateFaceCW();
		cubeDisp.reDrawCube();
	}
	
	public char[][] showCurrentState() {
		return cube.showCurrentState();
	}
	
	public String[] showFaceEdges(Cube test, int faceNum) {
		
		return cube.showFaceArray(test, faceNum);
	}
	
	public String toString() {
		System.out.print(cube.showFrontRows());
		return cube.toString();
	}


}
