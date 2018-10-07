package Generation;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import java.util.List;


public class TerImage {

    BufferedImage image;
    private Square[][] squares;
    private int gridSize;
    private int seed;
    private boolean showGrid;
    private List<SityNode> listSities;

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
        generateSities();
        generateRoutes();
        generateImage();
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println(String.format("Generation %d ms", duration/1000000));


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

    void generateRoutes(){
        //TODO impl

    }

    void generateSities(){
        //TODO
        //Генерирует города на сетке с шагом step в биоме равнин с определенной вероятностью и небольшим отклонением от сетки
        List<SityNode> listSites = new ArrayList<SityNode>();
        int step = 60;
        for (int i = step/2; i < getWidth()-step/2; i+=step) {
            for (int j = step/2; j < getHeight()-step/2; j+=step) {
                if(squares[i][j].getBio() == Biome.PLAIN && new Random().nextBoolean()){
                    int ki = (int)(i + Math.sin(j*50)*10);
                    int kj = (int)(j + Math.sin(i*50)*10);
                    SityNode sityNode = new SityNode(ki, kj);
                    int width = sityNode.getDiametrX()/2 ;
                    int heigh = sityNode.getDiametrY()/2;
                    for (int k = -width; k < width; k++) {
                        for (int l = -heigh; l < heigh; l++) {
                            squares[ki+k][kj+l].setBio(Biome.SITY);
                        }
                    }
                    listSites.add(sityNode);
                }
            }
        }
        this.listSities = listSites;
    }



}