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
        /*
        if(mode % nMode == 1)
            showHei(g);
        else if(mode % nMode == 2)
            showMoi(g);
        else if(mode % nMode == 3)
            showBiome(g, Generation.Biome.RIVER);
        else {
            //draw(g);
        }*/
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

    /*private int pX;
    private int pY;
    public void getField (int x, int y) {
        pX = getToSquareSize(x);
        pY = getToSquareSize(y);
        List<Generation.Square> s = new LinkedList<>();
        for (int i = pX; i < pX + gridSize; i++) {
            for (int j = pY; j < pY + gridSize; j++) {
                s.add(squares[i][j]);
            }
        }
        repaint();
        System.out.println(Generation.Square.averangeBiome(s));

    }



    //debug
    private void showMoi (Graphics g) {
        System.out.println("Moist");
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                g.setColor(Color.getHSBColor((float)squares[i][j].getMoist(), .5f, .5f));
                g.fillRect(i,j,1,1);
            }
        }
    }

    //debug
    private void showHei (Graphics g) {
        System.out.println("Height");
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                g.setColor(Color.getHSBColor((float)squares[i][j].getHeight(), .5f, .5f));
                g.fillRect(i,j,1,1);
            }
        }
    }

    //debug
    private void showBiome (Graphics g,  Generation.Biome b){
        System.out.println(b);
        for (Generation.Square s: getBiomeSq(b)) {
            g.setColor(s.getColor());
            g.fillRect(s.getX(),s.getY(),1,1);
        }
    }

    //debug
    private List<Generation.Square> getBiomeSq(Generation.Biome b){
        List<Generation.Square> l = new LinkedList<>();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if(squares[i][j].getBio() == b)
                    l.add(squares[i][j]);
            }
        }
        return l;
    }




    private int hX, hY;
    boolean hightlight = false;
    public void pickFiled(int x, int y, JLabel label) {
        hX = getToSquareSize(x);
        hY = getToSquareSize(y);
        //label.setText(String.format("x - %s: y - %s", getTSS(squares[x][y].getX()), getTSS(squares[x][y].getY())));
        hightlight = true;
        repaint();
    }
    public void stopHighlight(){
        hightlight = false;
    }

*/







}
