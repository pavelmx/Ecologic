import java.awt.*;
import java.util.*;
import java.util.List;
import javax.swing.*;


public class Terrain extends JPanel {
    private Square[][] squares;
    private int gridSize;
    private int squareSize = 1;
    private int height, width;
    private int seed;


    public Terrain(int rows, int cols, int gridSize) {
        this.setPreferredSize(new Dimension(rows, cols));
        this.gridSize = gridSize;
        this.width = rows;
        this.height = cols;
        squares = new Square[rows][cols];
        reroll();

    }

    public int mode = 4;
    public int nMode = 4;
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //debug
        if(mode % nMode == 1)
            showHei(g);
        else if(mode % nMode == 2)
            showMoi(g);
        else if(mode % nMode == 3)
            showBiome(g, Biome.RIVER);
        else {
            draw(g);
        }

    }

    public void draw(Graphics g){
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
    }

    private int pX;
    private int pY;
    public void getField (int x, int y) {
        pX = getToSquareSize(x);
        pY = getToSquareSize(y);
        List<Square> s = new LinkedList<>();
        for (int i = pX; i < pX + gridSize; i++) {
            for (int j = pY; j < pY + gridSize; j++) {
                s.add(squares[i][j]);
            }
        }
        repaint();
        System.out.println(Square.averangeBiome(s));

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
    private void showBiome (Graphics g,  Biome b){
        System.out.println(b);
        for (Square s: getBiomeSq(b)) {
            g.setColor(s.getColor());
            g.fillRect(s.getX(),s.getY(),1,1);
        }
    }

    //debug
    private List<Square> getBiomeSq(Biome b){
        List<Square> l = new LinkedList<>();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if(squares[i][j].getBio() == b)
                    l.add(squares[i][j]);
            }
        }
        return l;
    }


    private int getTSS(int value){
        return Math.round(value/ gridSize)* gridSize;
    }
    private int getToSquareSize(int value){
        return Math.round(value/ gridSize)* gridSize;
    }

    private int hX, hY;
    boolean hightlight = false;
    public void pickFiled(int x, int y, JLabel label) {
        hX = getToSquareSize(x);
        hY = getToSquareSize(y);
        label.setText(String.format("x - %s: y - %s", getTSS(squares[x][y].getX()), getTSS(squares[x][y].getY())));
        hightlight = true;
        repaint();
    }
    public void stopHighlight(){
        hightlight = false;
    }

    public void zoom(int scale){
        squareSize = scale;
            for (int i = 0; i <= width-scale; i+=scale) {
                for (int j = 0; j <= height-scale; j+=scale) {
                    Square[][] someSq = new Square[scale][scale];
                    for (int l = 0; l < scale; l++) {
                        for (int k = 0; k < scale; k++) {
                            someSq[l][k] = squares[i+l][j+k];
                        }
                    }
                    //TODO функции считают только height и biome
                    double sqH =  Square.averangeHeight(someSq);
                    Biome sqB = Square.averangeBiome(someSq);
                    for (int l = 0; l < scale; l++) {
                        for (int k = 0; k < scale; k++) {
                            squares[i+l][j+k].setHeight(sqH);
                            squares[i+l][j+k].setBio(sqB);
                        }
                    }
                    System.out.println(String.format("Done for i = %d, j = %d", i, j));
                }
            }
        repaint();
    }

    public void addSity(int n){
        for (int k = 0; k < n; k++) {
            int sizeSity = new Random().nextInt(Math.min(width, height)/4);
            int xSity = new Random().nextInt(width- sizeSity);
            int ySity = new Random().nextInt(height - sizeSity);
            for (int i = xSity; i < xSity + sizeSity; i++) {
                for (int j = ySity; j < ySity + sizeSity; j++) {
                    squares[i][j].setBio(Biome.SITY);
                }
            }
        }


    }

    public void reroll() {
        long startTime = System.nanoTime();
        squareSize = 1;
        seed = new Random().nextInt();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                squares[i][j] = new Square(i,j,seed);
            }
        }
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println(String.format("%d ms", duration/1000000));
        addSity(3);
        repaint();
    }



}
