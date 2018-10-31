package Display;

import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class CanvasGrid {
    static public void initializeGrid(Canvas c, int gridSize){
        PixelWriter gc = c.getGraphicsContext2D().getPixelWriter();
        for (int i = gridSize; i < c.getWidth(); i+=gridSize) {
            for (int j = gridSize; j < c.getHeight(); j+=gridSize) {
                gc.setColor(i,j,Color.BLACK);
            }
        }
    }
}
