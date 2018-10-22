package Display;

import Generation.Terrain;
import javafx.fxml.FXML;
import javafx.scene.canvas.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;


public class MainWindow {
    //TODO refractor class/functions names (they are misslead)

    @FXML
    public Canvas terCanvas;
    public Canvas gridCanvas;
    public Canvas pickCanvas;

    private Terrain terrain;
    private CanvasGraphics canvasG;

    @FXML
    private void initialize() {
        System.out.println("init");
        this.terrain = new Terrain((int) terCanvas.getWidth(), (int) terCanvas.getHeight(), 20);
        this.canvasG = new CanvasGraphics(terCanvas);
        canvasG.drawImage(terCanvas, terrain);
        CanvasGraphics.initializeGrid(gridCanvas, terrain.getGridSize());
        System.out.println("done");
    }

    @FXML
    public void evalButton() {
        terrain.regenerate();
        long startTime = System.nanoTime(); //timer
        canvasG.drawImage(terCanvas, terrain);
        long endTime = System.nanoTime(); //timer
        long duration = (endTime - startTime); //timer
        System.out.println(String.format("Draw in %d ms", duration / 1000000)); //timer
        System.out.println("---");
    }


    @FXML
    public void gridMode() {
        gridCanvas.setVisible(!gridCanvas.isVisible());
    }

    @FXML
    public void MouseMoved(MouseEvent e) {
        //TODO move outside controller
        int gridSize = terrain.getGridSize();
        int x = (int) (e.getX() / gridSize) * gridSize;
        int y = (int) (e.getY() / gridSize) * gridSize;
        GraphicsContext gc = pickCanvas.getGraphicsContext2D();
        gc.clearRect(0, 0, pickCanvas.getWidth(), pickCanvas.getWidth());
        if(pX == Integer.MAX_VALUE && pY == Integer.MAX_VALUE){
            gc.setFill(Color.color(1, 1, 1, 0.2));
            gc.fillRect(x, y, gridSize, gridSize);
        } else{
            gc.setFill(Color.color(0, 0, 0, 0.3));
            gc.fillRect(pX, pY, gridSize, gridSize);
        }
    }

    private int pX = Integer.MAX_VALUE;
    private int pY = Integer.MAX_VALUE;
    @FXML
    public void mouseCliked(MouseEvent e) {
        //TODO move outside controller (mby this part is actualy ok (no) )
        int gridSize = terrain.getGridSize();
        int x = (int) (e.getX() / gridSize) * gridSize;
        int y = (int) (e.getY() / gridSize) * gridSize;
        if(pX == x && pY == y){
            pX = pY = Integer.MAX_VALUE;
            return;
        }
        pX = x; pY = y;
        MouseMoved(e);
    }

    @FXML
    public void KeyPressed(KeyEvent e) {
        //System.out.println(e.getCode());
        if(e.getCode() == KeyCode.ESCAPE){
            pX = pY = Integer.MAX_VALUE;
        }
    }
}
