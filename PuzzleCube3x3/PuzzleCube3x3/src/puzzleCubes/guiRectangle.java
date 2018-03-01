package puzzleCubes;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

import java.awt.*;

public class guiRectangle extends JPanel implements MouseListener{
	private Color colors[]=
			new Color[] {Color.red, Color.blue, Color.white, Color.green, Color.orange, Color.yellow};

	private Color color;
	private int intColor;
	private JPanel panel;	
	
	public guiRectangle(int height, int width, Color color) {
		this.color = color;
		
		
		panel = new JPanel();
		//panel.setSize(width,height);
		panel.addMouseListener(this);
		panel.setMinimumSize(new Dimension(width,height));
		setColor(color);
		this.add(panel);
	}
	
	public void setColor(Color c) {
		
		for (int co=0;co<colors.length;co++) {
			if(colors[co]==c) {
				color=colors[co];
				intColor=co;
			}
		}
		panel.setBackground(color);
		
		
	}
	

	private Color getNextColor() {
		if(intColor<colors.length-1) {
			intColor++;
		}
		else {
			intColor=0;
		}
	return colors[intColor];
	
	}

	public void mouseClicked(MouseEvent event) {
		
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
		setColor(getNextColor());
		
	}
	
	/*	 public void paintComponent(Graphics g) {
	 
	super.paintComponent(g);
	
	
	//for (int i=0; i<6; i++) {
		//panels[i]=new JPanel();
		//for (int r=1;r<4;r++) {
			//for(int c=1;c<4;c++) {
	
				Graphics2D g2 =  (Graphics2D)g;
				//for (Shape item:shapeList) {
					Rectangle2D rect = new Rectangle2D.Float(0,0,width,height);
					
					g2.setColor(color);
					g2.fill(rect);
					
	
					//}
					//rect.addActionListener(listen);

					//g2.draw(rect);
					
					
				//}
			//}

	}
*/
}
