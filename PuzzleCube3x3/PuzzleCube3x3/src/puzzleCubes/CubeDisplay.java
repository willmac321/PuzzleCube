package puzzleCubes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class CubeDisplay extends JFrame {
	private static final int RECTD = 95;

	int c;
	int r;
	private Color color;
	private JPanel panelCube;
	private JPanel home;
	private JPanel btnContain;
	private JButton btnCW,btnCCW,btnUp,btnDown,btnRight,btnLeft, btnRand;
	private Dimension dim;
	private char[][] cubeArray;
	private Cube cube;
	private ActionClick click;
	private ColorChange colorChange;
	
	
	public CubeDisplay() {
	
		dim = new Dimension(1000,1000);
		home =new JPanel(new GridLayout());
		
		btnContain = new JPanel();

		this.r=0;
		this.c=0;
	}
	
	public CubeDisplay(Cube cube) {
		this();
		click = new ActionClick();
		colorChange=new ColorChange();
		setCubeDisplay(cube);	
	}
	
	public void createAndShowGui() {		
		this.setTitle("Puzzle Cube Configuration");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(dim);

		
		
		setButtons();

		JPanel rtContain = new JPanel(new FlowLayout(FlowLayout.CENTER,2,this.getHeight()/2));
		rtContain.add(btnContain);
		//sideView.add(btnContain);
		//btnContain.add(btnOK);
		panelCube= new JPanel();
		//frame.add(btnContain);
		paintCube();
		
		

		home.add(rtContain);
		home.setBackground(color.GRAY);
		//home.add(new JPanel());
		
		this.add(home);
        //frame.setSize(dim);
        this.setLocationRelativeTo(null);
		//frame.pack();
        
		this.setVisible(true);

	}
	private void setButtons() {

		btnCW= new JButton("CW");
		btnCCW=new JButton("CCW");
		btnUp=new JButton("Up");
		btnDown = new JButton("Down");
		btnRight= new JButton("Right");
		btnLeft=new JButton("Left");
		
		btnRand = new JButton("Randomize");
		
		btnCW.addActionListener(click);
		btnCCW.addActionListener(click);
		btnUp.addActionListener(click);
		btnDown.addActionListener(click);
		btnRight.addActionListener(click);
		btnLeft.addActionListener(click);
		
		btnRand.addActionListener(click);
		
		btnContain.setLayout(new GridLayout(3,2,5,5));
		btnContain.add(btnCW);
		btnContain.add(btnCCW);
		btnContain.add(btnUp);
		btnContain.add(btnDown);
		btnContain.add(btnLeft);	
		btnContain.add(btnRight);
		btnContain.add(btnRand);
		btnContain.setSize(new Dimension(RECTD,RECTD));
		
	}
	private void paintCube() {
		
		
		
		panelCube=drawCube();
		panelCube.setMinimumSize(new Dimension((int)dim.getHeight(),(int)dim.getWidth()));
		home.add(panelCube);
		home.setComponentZOrder(panelCube, 0);
		
	}
	
	private JPanel drawCube() {
		
		JPanel panelCube = new JPanel();
		//Dimension faceDim = new Dimension((int)dim.getHeight()/12,(int)dim.getWidth()/12);
		//panelCube.setSize(faceDim);
		GridLayout grid = new GridLayout(4,3);
		grid.setHgap(10);
		grid.setVgap(10);
		panelCube.setLayout(grid);
		
		
		JPanel faces[] = new JPanel[11];
		
		
		
		int r=0;
		
		for (int i=0;i<faces.length;i++) {
			faces[i]= new JPanel();
			//faces[i].setMinimumSize(faceDim);
			faces[i].setLayout(new GridLayout(3,3,5,5));
			//faces[i].setMinimumSize(dim);
			if(i!=0 && i!=2 && i!=6 && i!=8 && i!=9) {	
				
						for(int c=0;c<9;c++) {							
							guiRectangle rect = new guiRectangle(RECTD, RECTD, getColor(cubeArray[r][c]),r,c);	
							
							faces[i].add(rect);	
							rect.addMouseListener(colorChange);
						}						
					r++;			
			}
			//faces[i].setMinimumSize(faceDim);
			
		}
			JPanel temp = faces[1];
			faces[1]=faces[3];
			
			faces[3]=faces[7];
			faces[7]=faces[5];
			faces[5]=faces[4];
			faces[4]=temp;
			
			for (int i=0;i<faces.length;i++) {
				panelCube.add(faces[i]);
				
			}
		  return panelCube;   
	}
	
	public Color getColor(char t) {
		
		switch (t) {
		case 'w':
			color=Color.white;
			break;
		case 'o':
			color=Color.orange;
			break;
		case 'r':
			color=Color.red;
			break;
		case 'g':
			color=Color.green;
			break;
		case 'b':
			color=Color.blue;
			break;
		case 'y':
			color=Color.yellow;
			break;
		default:
			color=Color.black;
		}
		return color;		
	}
	
	public char getCharFromColor(Color c) {
		char rv=0;
		
		
		if(c== Color.white) {
			rv='w';
		}
		else if(c== Color.orange){
			rv='o';
		}
		else if(c== Color.red) {
			rv='r';
		}
		else if(c==  Color.green) {
			rv='g';
		}
		else if(c== Color.blue) {
			rv='b';
		}
		else if(c== Color.yellow) {
			rv='y';
		}

		return rv;		


	}
	
	public void reDrawCube() {
		setCubeDisplay(cube);
		home.remove(panelCube);
		paintCube();
		panelCube.revalidate();
		
	}
	
	protected void setColorWithLoc(Color c,int face, int index) {
		
		cube.setColor(getCharFromColor(c),face,index);
	}
	
	public void setCubeDisplay(Cube cube) {
		cubeArray=cube.showCurrentState();
		this.cube=cube;
	}
	private class ColorChange implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			Object t = e.getSource();
			Color c = null;
			if(t instanceof puzzleCubes.guiRectangle) {
				c=((Component)t).getBackground();
				setColorWithLoc(c,((puzzleCubes.guiRectangle) t).getFaceNum(),((puzzleCubes.guiRectangle) t).getIndexNum());
			}	
		}	
	}
	private class ActionClick implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			Object e = event.getSource();
			if(cube.checkConfig()) {
				if(e==btnCW) {
					cube.rotateFaceCW();
					reDrawCube();			
				}
				else if(e==btnCCW){
					cube.rotateFaceCCW();
					reDrawCube();
				}
				else if(e==btnUp){
					cube.turnUp();
					reDrawCube();
				}
				else if(e==btnDown){
					cube.turnDown();
					reDrawCube();
				}
				else if(e==btnLeft){
					cube.turnLeft();
					reDrawCube();
				}
				else if(e==btnRight){
					cube.turnRight();
					reDrawCube();
				}
				else if(e==btnRand) {
					cube.randomize();
					reDrawCube();
				}
			}
			else {
				System.out.println("Check inputs, incorrect amount of colors.");
			}
		}
	}	
}

