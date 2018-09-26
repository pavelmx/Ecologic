import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Main {

public static void main(String[] args) {

	int gridSize = 50;
	Terrain terrain = new Terrain(800, 400, gridSize);
	JFrame frame = new JFrame();
	JLabel label = new JLabel();
	label.setForeground(Color.red);
	frame.getContentPane().add(terrain);
	frame.pack();
	frame.setVisible(true);


	terrain.setLayout(new FlowLayout());
	terrain.add(label);

	terrain.addMouseMotionListener(new MouseAdapter() {
		@Override
			public void mouseMoved(MouseEvent e) {
                int x = e.getX() ;
                int y = e.getY();
                terrain.pickFiled(x, y, label);
  	          }
		});
	// :(
	terrain.addMouseListener(new MouseListener() {
		@Override
		public void mouseClicked(MouseEvent e) {

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
			terrain.stopHighlight();
		}
	});




	frame.addKeyListener(new KeyListener() {
		@Override
		public void keyTyped(KeyEvent e) {
		}

		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyChar() == 'v')
				terrain.zoom(10);
		}

		@Override
		public void keyReleased(KeyEvent e) {
		}
	});
	}


}
