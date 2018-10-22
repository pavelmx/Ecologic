package Display;

import Generation.Square;
import Generation.Terrain;
import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class CanvasGraphics {

    private Canvas terCanvas;
    private PixelWriter pw;

    public CanvasGraphics(Canvas terCanvas) {
        this.terCanvas = terCanvas;
    }

    public void drawImage(Canvas terCanvas, Terrain terrain) {
        Square[][] squares = terrain.getSqares();
        pw = terCanvas.getGraphicsContext2D().getPixelWriter();
        for (Square[] sqares2dim: squares) {
            for (Square sqare: sqares2dim) {
                Color col =  sqare.getColor();
                pw.setColor(sqare.getX(),sqare.getY(),col);
                //byte buffer?
            }
        }
    }

    public void drawGrid(int gridSize, boolean showGrid) {
        Canvas c = new Canvas(terCanvas.getWidth(),terCanvas.getHeight());
        GraphicsContext gcTER = terCanvas.getGraphicsContext2D();
        GraphicsContext gcLay = c.getGraphicsContext2D();
        gcLay.fillRect(81,50,52,20);
        //gcTER.drawImage(c,0,0);
        /*for (int i = 0; i < terCanvas.getWidth(); i+=gridSize) {
            for (int j = 0; j < terCanvas.getHeight(); j+=gridSize) {
                if(showGrid)
                    pw.setColor(i,j,Color.BLACK);
                else
                    pw.setColor(i,j,squares[i][j].getColor());
            }
        }*/
        final Rectangle rect1 = new Rectangle(10, 10, 100, 100);
        rect1.setArcHeight(20);
        rect1.setArcWidth(20);
        rect1.setFill(Color.RED);
        FadeTransition ft = new FadeTransition(Duration.millis(3000), rect1);
        ft.setFromValue(1.0);
        ft.setToValue(0.1);
        ft.setCycleCount(Timeline.INDEFINITE);
        ft.setAutoReverse(true);
        ft.play();
    }

    static public void initializeGrid(Canvas c, int gridSize){
        PixelWriter gc = c.getGraphicsContext2D().getPixelWriter();
        for (int i = gridSize; i < c.getWidth(); i+=gridSize) {
            for (int j = gridSize; j < c.getHeight(); j+=gridSize) {
                gc.setColor(i,j,Color.BLACK);
            }
        }

    }
}
