package Generation;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;
import javax.swing.*;


public class Terrain extends JPanel {
    private TerImage image;
    private Square[][] squares;
    private int gridSize;
    private int seed;
    private boolean showGrid;
    private List<SityNode> listSities;


    public Terrain(int rows, int cols, int gridSize) {
        this.setPreferredSize(new Dimension(rows, cols));
        squares = new Square[rows][cols];
        this.gridSize = gridSize;
        regenerate();
    }

    public void grid() {
        image.gridMode();
        repaint();
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image.getImage(), 0, 0, this);

    }


    public void pickFiled(int x, int y) {
        image.pickField(x, y);
        repaint();
    }

    public void getField(int x, int y) {
        image.highField(x, y);
        repaint();
    }


    public int getHeight(){
        return squares[0].length;
    }

    public int getWidth(){
        return squares.length;
    }


    public void regenerate() {
        long startTime = System.nanoTime();
        seed = new Random().nextInt();
        for (int i = 0; i < getWidth(); i++) {
            for (int j = 0; j < getHeight(); j++) {
                squares[i][j] = new Square(i, j, seed);
            }
        }
        generateSities();
        generateRoutes();
        image = new TerImage(squares, gridSize, listSities);
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println(String.format("Generation %d ms", duration / 1000000));
        repaint();
    }

    void generateSities() {
        //TODO better generation
        //Генерирует города на сетке с шагом step в биоме равнин с определенной вероятностью и небольшим отклонением от сетки
        List<SityNode> listSites = new ArrayList<SityNode>();
        int step = 60;
        for (int i = -step; i < getWidth() + step; i += step) {
            for (int j = -step; j < getHeight() + step; j += step) {
                if (new Random().nextBoolean()) {
                    int ki = (int) (i + Math.sin(j * 50) * 10);
                    int kj = (int) (j + Math.sin(i * 50) * 10);
                    SityNode sityNode = new SityNode(ki, kj);
                    listSites.add(sityNode);
                }
            }

            Iterator<SityNode> iter = listSites.iterator();
            while (iter.hasNext()) {
                SityNode sity = iter.next();
                try {
                    if (squares[sity.getX()][sity.getY()].getBio() != Biome.PLAIN)
                        iter.remove();
                } catch (IndexOutOfBoundsException e) {
                }
            }
            this.listSities = listSites;
        }
    }

    void generateRoutes() {
        for (SityNode sity : listSities) {
            List<SityNode> pickSityFrom = new LinkedList<>();
            pickSityFrom.addAll(listSities);
            pickSityFrom.remove(sity);
            pickSityFrom.sort((o1, o2) -> {
                int dis1 = SityNode.distanceLinear(sity, o1);
                int dis2 = SityNode.distanceLinear(sity, o2);
                return (dis1 > dis2) ? 1 : -1;
            });
            for (int i = 0; i < 3; i++) {
                sity.addRoute(pickSityFrom.get(i));
            }
        }
    }



}
