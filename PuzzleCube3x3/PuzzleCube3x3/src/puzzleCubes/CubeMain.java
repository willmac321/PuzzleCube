package puzzleCubes;
import java.util.*;
public class Test {

	public static void main(String[] args) {
		
		
		
		char[][] test = new char[6][];
		

		test[0]=new char[] {'w','w','w','w','w','w','w','w','w'};
		 
		//char[][] arrTopFace = 1
		test[1]=new char[]{'r','r','r','r','r','r','r','r','r'};
		//char[][] arrRightFace = 2
		test[2]=(new char[]{'g','g','g','g','g','g','g','g','g'});
		//char[][] arrBotFace =3
		test[3]=new char[]{'o','o','o','o','o','o','o','o','o'};
		//char[][]  arrLeftFace = 4
		test[4] = new char[]{'b','b','b','b','b','b','b','b','b'};
		//char[][] arrBackFace = 5
		test[5]=new char[]{'y','y','y','y','y','y','y','y','y'};
		//Face front = new Face(arrFrontFace);

		Cube cube = new Cube(test[0],test[1],test[2],test[3],test[4],test[5]);
		System.out.print(cube.showFrontRows());
		System.out.print("BackRows\n");
		System.out.print(cube.showBackRows());
		System.out.print(cube.toString());
		//cube.rotateFaceCCW();
		//System.out.print(cube.toString());
		cube.rotateFaceCW();
		System.out.println("rotate CW");
		System.out.print(cube.showFrontRows());
		System.out.print("BackRows\n");
		System.out.print(cube.showBackRows());
		System.out.print(cube.toString());

		
		cube.turnDown();
		System.out.println("turn down");
		System.out.print(cube.showFrontRows());
		System.out.print("BackRows\n");
		System.out.print(cube.showBackRows());
		System.out.print(cube.toString());
		cube.rotateFaceCW();
		System.out.println("rotate CW");
		System.out.print(cube.showFrontRows());
		System.out.print(cube.toString());
		cube.turnRight();
		System.out.println("turn right");
		System.out.print(cube.showFrontRows());
		System.out.print("BackRows\n");
		System.out.print(cube.showBackRows());
		System.out.print(cube.toString());
		

		//CubeDisplay cubeDisp = new CubeDisplay(0,0);
		
		//Cube cube = new Cube();
		//cubeDisp.createAndShowGui();
		
		/*
		for( int i=0;i<4;i++) {
			for (int r=-3; r<4;r++) {
				int t=r;
				if (r<0) {
					
					r=4+r;
				}
				int l = i+Math.abs((r-1)%3+1);
				if(l>=4) {
					l=l-4;
				}
				System.out.println("rotation is: "+t+" index is: "+i+"; i+abs(r-1)%3+1="+l);
				r=t;
			}
		}
		*/
		
		
	}

}
