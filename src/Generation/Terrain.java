package Generation;

import java.awt.*;
import javax.swing.*;


public class Terrain extends JPanel {
    private TerImage image;
    Square[][] squares;


    public Terrain(int rows, int cols, int gridSize) {
        this.setPreferredSize(new Dimension(rows, cols));
        squares = new Square[rows][cols];
        image = new TerImage(squares, gridSize);
    }

    public void grid () {
        image.gridMode();
        repaint();
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(image.getImage(), 0, 0, this);

    }



    public void pickFiled (int x, int y) {
        image.pickField(x,y);
        repaint();
    }

    public void getField (int x, int y) {
        image.highField(x,y);
        repaint();
    }

    public void regenerate () {
        image.regenerate();
        repaint();
    }

    /*public void draw(Graphics g){
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if(i% gridSize == 0 || j% gridSize == 0)
                    g.setColor(Color.black);
                else if (getTSS(i) == hX && getTSS(j) == hY && hightlight == true)
                    g.setColor(squares[i][j].getColor().brighter());
                else if (getTSS(i) == pX && getTSS(j) == pY)
                    g.setColor(squares[i][j].getColor().darker());
                else
                    g.setColor(squares[i][j].getColor());
                g.fillRect(i,j,1,1);
            }
        }

    }*/










}
