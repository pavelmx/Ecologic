package Generation;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;


public class TerImage {
/*
    BufferedImage image;
    private Square[][] squares;
    private int gridSize;
    private int seed;
    private boolean showGrid;
    private List<SityNode> listSities;

    public TerImage (Square[][] squares, int gridSize, List<SityNode> listSities) {
        this.squares = squares;
        this.gridSize = gridSize;
        this.listSities = listSities;
        seed = new Random().nextInt();
    }

    void drawImage(){
        image = new BufferedImage(squares.length*2, squares[0].length*2, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        for (Square[] sqares2dim: squares) {
            for (Square sqare: sqares2dim) {
                //g.setColor(sqare.getColor());
                g.fillRect(sqare.getX(),sqare.getY(),1,1);
            }
        }
        g.dispose();
        drawRoutes();
        drawSities();
    }

    private void drawRoutes(){
        Graphics g = image.getGraphics();
        g.setColor(Color.orange);
        List<SityNode> locListSities = new ArrayList<>();
        locListSities.addAll(listSities);
        for (SityNode sity: locListSities ) {
            for (SityNode toSity: sity.getRoadToSity()) {
                //g.drawLine(sity.getX(),sity.getY(),toSity.getX(),toSity.getY());
                //Helpers.drawLineWnoise(g, sity.getX(),sity.getY(),toSity.getX(),toSity.getY());
            }
        }
        g.dispose();
    }

    private void drawSities(){
        Graphics g = image.getGraphics();
        g.setColor(Color.black);
        for (SityNode sity: listSities) {
            int heigh = sity.getDiametrX()/2;
            int width = sity.getDiametrY()/2;
            g.fillRect(sity.getX()-width, sity.getY() - heigh, sity.getDiametrY(), sity.getDiametrX());
        }
        g.dispose();
    }



    public int getHeight(){
        return squares[0].length;
    }

    private int getWidth(){
        return squares.length;
    }

    public BufferedImage getImage(){
        if (image == null){
            drawImage();
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
            drawRoutes();
            drawSities();
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
            drawRoutes();
            drawSities();
        }else
            pickField(x,y);
    }



    public void routeMode() {
    }
    */
}
