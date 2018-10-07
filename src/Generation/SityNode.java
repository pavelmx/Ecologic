package Generation;

import java.util.Random;

public class SityNode {
    int x, y;
    int diametrX, diametrY;
    float[][] buildingsMap;
    float safety;

    public SityNode(int x, int y) {
        this.x = x;
        this.y = y;
        Random r = new Random();
        diametrX = Math.round((r.nextFloat()*6+1)/2)*2;
        diametrY = Math.round((r.nextFloat()*6+1)/2)*2;
        safety = r.nextFloat();
        buildingsMap = new float[diametrX][diametrY];
        double scale = .007;
        double pers = .3;
        for (int i = 0; i < diametrX; i++) {
            for (int j = 0; j < diametrY; j++) {
                buildingsMap[i][j] = (float)SimplexNoise.sumOctave(13,i,j, 5, pers,scale);
            }
        }
    }

    public int getDiametrX() {
        return diametrX;
    }

    public int getDiametrY() {
        return diametrY;
    }
}
