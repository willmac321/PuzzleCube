package puzzleCubes;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;


public class CubeDisplay extends JPanel {
	private static final int RECTD = 100;

	int c;
	int r;
	private Color colors[]=
			new Color[] {Color.red, Color.blue, Color.white, Color.green, Color.orange, Color.yellow};
	private Color color;
	private JFrame frame;
	private JButton btnOK;
	private Dimension dim;
	public CubeDisplay(int r, int c) {

		
		dim = new Dimension(1000,1000);
		frame = new JFrame("Puzzle Cube Configuration");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		btnOK= new JButton("OK");

			//panels[i].add(new Graphics2d.draw(new Rectangle2D.Double((float)r,(float)c,(float)RECTD,(float)RECTD));
		this.r=r;
		this.c=c;

		
	}
	
	public void createAndShowGui() {
		JPanel cube = new JPanel();
		
		JPanel btnContain = new JPanel();
		btnContain.setLayout(new FlowLayout());
		
		btnContain.add(btnOK);
		
		int intColor=0;
		
		cube=drawCube();
		
		//btnOK.addActionListener(new btnClick());
		frame.add(cube);
		frame.add(btnContain);
        frame.setSize(dim);
        frame.setLocationRelativeTo(null);
		//frame.pack();
        
		frame.setVisible(true);

	}
	
	private JPanel drawCube() {
		
		JPanel cube = new JPanel();

		cube.setSize(dim);
		cube.setLayout(new GridLayout(4,3));
		
		JPanel faces[] = new JPanel[11];
		
		Dimension faceDim = new Dimension((int)dim.getHeight()/6,(int)dim.getWidth()/6);
		
		int intColor=0;
		
		for (int i=0;i<faces.length;i++) {
			faces[i]= new JPanel();
			faces[i].setSize(faceDim);
			faces[i].setLayout(new GridLayout(3,3));
			faces[i].setMinimumSize(dim);
			if(i!=0 && i!=2 && i!=6 && i!=8 && i!=9) {

				for (int r=1;r<4;r++) {
					for(int c=1;c<4;c++) {
						guiRectangle rect = new guiRectangle(RECTD, RECTD, colors[intColor]);				
						faces[i].add(rect);
					}
				}
				intColor++;			
			}
			cube.add(faces[i]);
		}
		  return cube;   
	}
	
	private void linkCube() {
		
	}
	
	
	private class MyListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			if(event.getSource()==btnOK) {
				linkCube();
			}
		}
	}
	
}

