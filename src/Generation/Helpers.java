package Generation;

import java.awt.Color;

public class Helpers {


    static Color addFunkyShCol(Color c, double h, double m){
        int r = (int)(c.getRed() + addMH(m, h));
        int g = (int)(c.getGreen() + addMH(m, h));
        int b = (int)(c.getBlue() + addMH(m, h));
        r = round(r, 0, 255);
        g = round(g, 0,255);
        b = round(b, 0, 255);
        return new Color(r,g,b);
    }

    static int round(int value ,int bottom, int top){
        value = value > top ? top : value;
        value = value < bottom ? bottom : value;
        return value;
    }

    static double addMH(double h,double m){
        return  - m * (255/3);
    }

    static int getToSquareSize(int value, int gridSize){
        return Math.round(value/ gridSize)* gridSize;
    }
}