import Generation.Terrain;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Main {

public static void main(String[] args) {

	//100x100 km
	//

	int gridSize = 20;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int width = (int)(screenSize.getWidth()*2/3/gridSize)*gridSize;
	int height = (int)(screenSize.getHeight()*2/3/gridSize)*gridSize;

	Terrain terrain = new Terrain(width, height, gridSize);


	JPanel panTerrain = new JPanel();
	JPanel panButtons = new JPanel();
	JPanel panMain = new JPanel();

	panTerrain.add(terrain);
	//?
	//panTerrain.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
	//
	JSlider slider = new JSlider();
	slider.setMinimum(1);
	Button button = new Button("do stuff");
	JLabel label = new JLabel("value");
	panButtons.add(slider);
	panButtons.add(label);
	panButtons.add(button);
	panButtons.setSize(500,50);
	//?
	//panButtons.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
	//


	panMain.setLayout(new FlowLayout());
	panMain.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
	panMain.add(panTerrain); panMain.add(panButtons);

	JFrame frame = new JFrame();
	frame.getContentPane().add(panMain);
	frame.pack();
	frame.setVisible(true);
    //frame.setSize(width+50, height+50);


	/*JFrame frame = new JFrame();
	JLabel label = new JLabel();
	label.setForeground(Color.red);
	frame.getContentPane().add(terrain);
	frame.pack();
	frame.setVisible(true);
	terrain.setLayout(new FlowLayout());
	terrain.add(label);*/






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

	/*panTerrain.addKeyListener(new KeyListener() {
		@Override
		public void keyTyped(KeyEvent e) {
		}

		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyChar() == 'v')
				terrain.grid();
			if(e.getKeyChar() == 'c'){
                terrain.mode++;
                terrain.repaint();
            }
            if(e.getKeyChar() == 'r')
                terrain.regenerate();

		}

		@Override
		public void keyReleased(KeyEvent e) {
		}
	});*/

	slider.addChangeListener(e -> label.setText(Integer.toString(slider.getValue())));
	button.addActionListener(e -> terrain.regenerate());
	}


}
