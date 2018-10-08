package Generation;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class SityNode {
    private int x, y;
    private int diametrX, diametrY;
    private float[][] buildingsMap;
    private float safety;
    private int numberOfRoads = 0;
    private List<SityNode> roadToSity;

    public SityNode(int x, int y) {
        this.x = x;
        this.y = y;
        Random r = new Random();
        diametrX = Math.round((r.nextFloat()*6+1)/2)*2;
        diametrY = Math.round((r.nextFloat()*6+1)/2)*2;
        safety = r.nextFloat();
        roadToSity = new LinkedList<>();
        buildingsMap = new float[diametrX][diametrY];
        double scale = .007;
        double pers = .3;
        for (int i = 0; i < diametrX; i++) {
            for (int j = 0; j < diametrY; j++) {
                buildingsMap[i][j] = (float)SimplexNoise.sumOctave(13,i,j, 5, pers,scale);
            }
        }
    }

    public void addRoute(SityNode sity){
        if(!roadToSity.contains(sity)){
            roadToSity.add(sity);
            sity.addRoute(this);
            numberOfRoads++;
        }
    }

    public int getDiametrX() {
        return diametrX;
    }

    public int getDiametrY() {
        return diametrY;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getNumberOfRoads() {
        return numberOfRoads;
    }

    public List<SityNode> getRoadToSity() {
        return roadToSity;
    }

    static public int distanceLinear(SityNode s1, SityNode s2){
        int x = Math.abs(s1.getX() - s2.getX());
        int y = Math.abs(s1.getY() - s2.getY());
        int result = y > x ? (14*x + 10*(y-x)) : (14*y + 10*(x-y));
        return result;
    }
}
