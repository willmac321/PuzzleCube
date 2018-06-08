package puzzleCubes;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.FontRenderContext;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import dataSetCreation.DataSetCreationMain;

public class CubeDisplay extends JFrame {
	private static final int RECTD = 95;
	private static final int MAX_RAND_MOVES = 30;
	
	int c;
	int r;
	private Color color;
	private JPanel panelCube;
	private JPanel home;
	private JPanel btnContain, solverContain, moveContain;
	private JButton btnCW,btnCCW,btnUp,btnDown,btnRight,btnLeft, btnRand, btnCreateDataSet;
	private Dimension dim;
	private char[][] cubeArray;
	private Cube cube;
	private CubeMain cubeMain;
	private ActionClick click;
	private ColorChange colorChange;
	private JTextField listbSaveFileName, listbMaxMoves, listbMaxScrambles;
	private JTextField listbMaxIterations;
	private Label txtSaveFileName, txtMaxMoves, txtMaxScramble, txtMaxIterations;
	private Label txtMessages;
	private Label txtMoveHeader;
	private Label txtDataHeader;
	private JTextArea txtMoveList;
	private JCheckBox chkScramble;
	private boolean isChkScramble;
	
	protected CubeDisplay() {
	
		dim = new Dimension(1000,1000);
		home =new JPanel(new GridLayout());
		
		btnContain = new JPanel();
		moveContain = new JPanel();
		solverContain = new JPanel();
		isChkScramble = false;
		this.r=0;
		this.c=0;
	}
	
	protected CubeDisplay(CubeMain cubeMain, Cube cube) {
		this();
		click = new ActionClick();
		colorChange=new ColorChange();
		setCubeDisplay(cube);	
		this.cubeMain = cubeMain;
	}
	
	protected void createAndShowGui() {		
		this.setTitle("Puzzle Cube Configuration");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(dim);

		
		
		setButtons();

		JPanel rtContain = new JPanel(new FlowLayout(FlowLayout.CENTER,100,this.getHeight()/7));
		rtContain.add(btnContain);
		rtContain.add(moveContain);
		rtContain.add(solverContain);
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
		
		txtMoveList = new JTextArea("");
		txtMoveList.setLineWrap(true);
		txtMoveList.setWrapStyleWord(true);
		txtMoveList.setOpaque(false);
		txtMoveList.setRows(4);
		txtMoveList.setColumns(40);
		txtMoveList.setSize(moveContain.getWidth(), moveContain.getHeight());
		
		btnCreateDataSet = new JButton("Create DataSet");
		
		txtMoveHeader = new Label("Available Moves: ");
		
		txtDataHeader = new Label("Create a Move Data Set: ");
		
		btnCW.addActionListener(click);
		btnCCW.addActionListener(click);
		btnUp.addActionListener(click);
		btnDown.addActionListener(click);
		btnRight.addActionListener(click);
		btnLeft.addActionListener(click);
		
		btnRand.addActionListener(click);
		
		btnCreateDataSet.addActionListener(click);
		
		btnContain.setLayout(new GridLayout(5,2,5,5));
		btnContain.add(txtMoveHeader);
		btnContain.add(new Label());
		btnContain.add(btnCW);
		btnContain.add(btnCCW);
		btnContain.add(btnUp);
		btnContain.add(btnDown);
		btnContain.add(btnLeft);	
		btnContain.add(btnRight);
		btnContain.add(btnRand);

		btnContain.setSize(new Dimension(RECTD,RECTD));
		
		moveContain.add(txtMoveList);
		
		listbSaveFileName = new JTextField("cubeDataSet");
		listbMaxMoves = new JTextField("10");
		listbMaxScrambles = new JTextField("2");
		listbMaxIterations = new JTextField("10");
		txtSaveFileName = new Label("Input Save File Name: ");
		txtMaxMoves = new Label("Input Max Moves: ");
		txtMaxScramble = new Label("Input Maximum Random Turns: ");
		txtMaxIterations = new Label("Maximum Iterations (resets on each run): ");
		txtMessages = new Label(" ");
		chkScramble = new JCheckBox("<html><body>Check to scramble and<br> randomly pick moves otherwise<br>log moves to scramble from solved</body></html>",
				isChkScramble);
		
		solverContain.setLayout(new GridLayout(7,2,5,5));
		solverContain.add(txtDataHeader);
		solverContain.add(new Label());
		solverContain.add(txtSaveFileName);
		solverContain.add(listbSaveFileName);
		solverContain.add(txtMaxMoves);
		solverContain.add(listbMaxMoves);
		solverContain.add(txtMaxScramble);
		solverContain.add(listbMaxScrambles);
		solverContain.add(txtMaxIterations);
		solverContain.add(listbMaxIterations);
		solverContain.add(new Label());
		solverContain.add(chkScramble);
		
		solverContain.add(btnCreateDataSet);
		solverContain.add(txtMessages);
		
		Font font = new Font(txtMoveHeader.getFont().getName(), Font.BOLD, txtMoveHeader.getFont().getSize()+3);
		
		txtMoveHeader.setFont(font);
		txtDataHeader.setFont(font);
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
	
	protected Color getColor(char t) {
		
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
	
	protected char getCharFromColor(Color c) {
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
	
	protected void reDrawCube() {
		setCubeDisplay(cube);
		home.remove(panelCube);
		paintCube();
		panelCube.revalidate();
		
	}
	
	protected void setColorWithLoc(Color c,int face, int index) {
		
		cube.setColor(getCharFromColor(c),face,index);
	}
	
	protected void setCubeDisplay(Cube cube) {
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
					cubeMain.rotateFaceCW();
					reDrawCube();			
				}
				else if(e==btnCCW){
					cubeMain.rotateFaceCCW();
					reDrawCube();
				}
				else if(e==btnUp){
					cubeMain.turnUp();
					reDrawCube();
				}
				else if(e==btnDown){
					cubeMain.turnDown();
					reDrawCube();
				}
				else if(e==btnLeft){
					cubeMain.turnLeft();
					reDrawCube();
				}
				else if(e==btnRight){
					cubeMain.turnRight();
					reDrawCube();
				}
				else if(e==btnRand) {
					txtMoveList.setText(null);
					
					cubeMain.randomize(MAX_RAND_MOVES);
					reDrawCube();
					String str = cubeMain.getMoveList();
					txtMoveList.setText(str);
				}
				else if(e==btnCreateDataSet) {
					//max, scramble, iterations
					isChkScramble = chkScramble.isSelected();
					new DataSetCreationMain(cubeMain, Integer.valueOf(listbMaxMoves.getText()),
							 Integer.valueOf(listbMaxScrambles.getText()),  Integer.valueOf(listbMaxIterations.getText()),
							 listbSaveFileName.getText(), txtMessages, isChkScramble);
				}
			}
			else {
				System.out.println("Check inputs, incorrect amount of colors.");
			}
		}
	}	
}

