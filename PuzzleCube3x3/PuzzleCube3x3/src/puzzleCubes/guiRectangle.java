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
	private int faceNumber;
	private int indexNumber;
	
	public guiRectangle(int height, int width, Color color, int f, int i) {
		this.color = color;
		
		faceNumber=f;
		indexNumber=i;
		
		addMouseListener(this);

		setColor(color);

	}
	
	public void setColor(Color c) {
	
			for (int co=0;co<colors.length;co++) {
				if(colors[co]==c) {
					color=colors[co];
					intColor=co;
				}
			}
	
		this.setBackground(color);
		
		
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
	
	protected int getIndexNum() {
		return indexNumber;
	}
	
	protected int getFaceNum() {
		return faceNumber;
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
	
}

