import Generation.Terrain;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Main {

public static void main(String[] args) {

	int gridSize = 20;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	Terrain terrain = new Terrain((int)(screenSize.getWidth()/3), (int)(screenSize.getHeight()/3), gridSize);
	JFrame frame = new JFrame();
	JLabel label = new JLabel();
	label.setForeground(Color.red);
	frame.getContentPane().add(terrain);
	frame.pack();
	frame.setVisible(true);
	terrain.setLayout(new FlowLayout());
	terrain.add(label);
	Color c = new Color(210,20,39);

	terrain.addMouseMotionListener(new MouseAdapter() {
		@Override
			public void mouseMoved(MouseEvent e) {
                int x = e.getX() ;
                int y = e.getY();
                terrain.pickFiled(x, y);
  	          }
		});
	// :(
	terrain.addMouseListener(new MouseListener() {
		@Override
		public void mouseClicked(MouseEvent e) {
			terrain.getField(e.getX(), e.getY());


		}

		@Override
		public void mousePressed(MouseEvent e) {

		}

		@Override
		public void mouseReleased(MouseEvent e) {

		}

		@Override
		public void mouseEntered(MouseEvent e) {

		}

		@Override
		public void mouseExited(MouseEvent e) {
			//terrain.stopHighlight();
		}
	});

	frame.addKeyListener(new KeyListener() {
		@Override
		public void keyTyped(KeyEvent e) {
		}

		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyChar() == 'v')
				terrain.grid();
			/*if(e.getKeyChar() == 'c'){
                terrain.mode++;
                terrain.repaint();
            }*/
            if(e.getKeyChar() == 'r')
                terrain.regenerate();

		}

		@Override
		public void keyReleased(KeyEvent e) {
		}
	});
	}



}
