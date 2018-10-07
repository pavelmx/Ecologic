package Generation;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class TerImage {

    BufferedImage image;
    private Square[][] squares;
    private int gridSize;
    private int seed;
    private boolean showGrid;

    public TerImage (Square[][] squares, int gridSize) {
        this.squares = squares;
        this.gridSize = gridSize;
        seed = new Random().nextInt();
        regenerate();
    }

    void generateImage(){
        image = new BufferedImage(squares.length*2, squares[0].length*2, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        for (Square[] sqares2dim: squares) {
            for (Square sqare: sqares2dim) {
                g.setColor(sqare.getColor());
                g.fillRect(sqare.getX(),sqare.getY(),1,1);
            }
        }
        g.dispose();
    }

    public void regenerate () {
        long startTime = System.nanoTime();
        seed = new Random().nextInt();
        for (int i = 0; i < getWidth(); i++) {
            for (int j = 0; j < getHeight(); j++) {
                squares[i][j] = new Square(i,j,seed);
            }
        }
        generateImage();
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println(String.format("Generation %d ms", duration/1000000));
        generateRoutes(1);


    }


    public int getHeight(){
        return squares[0].length;
    }

    private int getWidth(){
        return squares.length;
    }

    public BufferedImage getImage(){
        if (image == null){
            generateImage();
        }
        return image;
    }

    public void gridMode(){
        Graphics g = image.getGraphics();
        showGrid = !showGrid;
        for (int i = 0; i < getWidth(); i+=gridSize) {
            for (int j = 0; j < getHeight(); j+=gridSize) {
                if(showGrid)
                    g.setColor(Color.black);
                else
                    g.setColor(squares[i][j].getColor());
                g.drawRect(i,j, 1,1);
            }
        }
        g.dispose();
    }

    private int prevX, prevY;
    public void pickField(int x, int y){
        int pickedX = Helpers.getToSquareSize(x, gridSize);
        int pickedY = Helpers.getToSquareSize(y, gridSize);
        if((prevX == pickedX && prevY == pickedY) || highlited)
            return;
        else {
            Graphics g = image.getGraphics();
            for (int i = pickedX+2; i < pickedX + gridSize -1; i++) {
                for (int j = pickedY+2; j < pickedY+gridSize -1; j++) {
                    g.setColor(squares[i][j].getColor().brighter());
                    g.drawRect(i,j,1,1);
                }
            }
            for (int i = prevX+2; i < prevX + gridSize-1; i++) {
                for (int j = prevY+2; j < prevY+gridSize-1; j++) {
                    g.setColor(squares[i][j].getColor());
                    g.drawRect(i,j,1,1);
                }
            }
            prevX = pickedX;
            prevY = pickedY;
            g.dispose();
        }
    }

    private int highX;
    private int highY;
    private boolean highlited;
    public void highField (int x, int y) {
        highlited = !highlited;
        if(highlited) {
            highX = Helpers.getToSquareSize(x, gridSize);
            highY = Helpers.getToSquareSize(y, gridSize);
            Graphics g = image.getGraphics();
            for (int i = highX + 2; i < highX + gridSize - 1; i++) {
                for (int j = highY + 2; j < highY + gridSize - 1; j++) {
                    g.setColor(squares[i][j].getColor().darker());
                    g.drawRect(i, j, 1, 1);
                }
            }
            g.dispose();
        }else
            pickField(x,y);
    }
    
    void generateRoutes(int number){
        Graphics g = image.getGraphics();
        g.setColor(Color.BLACK);
        for (int i = 0; i < getWidth(); i+=40) {
            for (int j = 0; j < getHeight(); j+=40) {
                if(new Random().nextBoolean())
                    g.drawOval((int)(Math.sin(i)*50+i),(int)(Math.cos(i)*50+j),10,10);
            }
        }
    }





    





    /*public void zoom(int scale){
        squareSize = scale;
            for (int i = 0; i <= width-scale; i+=scale) {
                for (int j = 0; j <= height-scale; j+=scale) {
                    Generation.Square[][] someSq = new Generation.Square[scale][scale];
                    for (int l = 0; l < scale; l++) {
                        for (int k = 0; k < scale; k++) {
                            someSq[l][k] = squares[i+l][j+k];
                        }
                    }
                    //TODO функции считают только height и biome
                    double sqH =  Generation.Square.averangeHeight(someSq);
                    Generation.Biome sqB = Generation.Square.averangeBiome(someSq);
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
    }*/

    /*public void addSity(int n){
        for (int k = 0; k < n; k++) {
            int sizeSity = new Random().nextInt(Math.min(width, height)/4);
            int xSity = new Random().nextInt(width- sizeSity);
            int ySity = new Random().nextInt(height - sizeSity);
            for (int i = xSity; i < xSity + sizeSity; i++) {
                for (int j = ySity; j < ySity + sizeSity; j++) {
                    squares[i][j].setBio(Generation.Biome.SITY);
                }
            }
        }


    }*/
}
