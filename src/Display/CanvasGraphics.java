package Display;

import Generation.Terrain;
import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
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
        double[][] map = terrain.getPopulationMap();
        pw = terCanvas.getGraphicsContext2D().getPixelWriter();
        for (int i = 0; i < terCanvas.getHeight(); i++) {
            for (int j = 0; j < terCanvas.getWidth(); j++) {
                Color col = Color.gray(map[i][j]);
                pw.setColor(i,j,col);
            }
        }
        /*Square[][] squares = terrain.getSqares();
        pw = terCanvas.getGraphicsContext2D().getPixelWriter();
        for (Square[] sqares2dim: squares) {
            for (Square sqare: sqares2dim) {
                Color col =  sqare.getColor();
                pw.setColor(sqare.getX(),sqare.getY(),col);
                //byte buffer?
            }
        }*/
    }

    public  static void drawDebugImage(Canvas canvas, Terrain t, int mode){
        double[][] map;
        switch (mode){
            case 0: map = t.getHeightMap();
            break;
            case 1: map = t.getWaterMap();
            break;
            case 2: map = t.getPopulationMap();
            break;
            case 3: map = t.getSafetyMap();
            break;
            default: map = new double[t.getHeight()][t.getWidth()];
            break;
        }
        PixelWriter pw = canvas.getGraphicsContext2D().getPixelWriter();
        for (int i = 0; i < canvas.getHeight(); i++) {
            for (int j = 0; j < canvas.getWidth(); j++) {
                Color col = Color.gray(map[i][j]);
                pw.setColor(i,j,col);
            }
        }
    }

}
