package Generation;

import java.util.*;


public class Terrain  {
    //private Square[][] squares;
    private int gridSize;
    private int seed;
    private int Pseed;
    private int Sseed;
    private int width, height;
    private double[][] heightMap;
    private double[][] populationMap;
    private double[][] waterMap;
    private double[][] safetyMap;


    public Terrain(int width, int height, int gridSize) {
        this.width = width;
        this.height = height;
        this.gridSize = gridSize;
        reroll();
    }



    public void reroll(){
        seed = new Random().nextInt(9999);
        Pseed = new Random().nextInt(9999);
        Sseed = new Random().nextInt(9999);
        long startTime = System.nanoTime(); //timer
        generateHeightNwaterMap();
        generatePopul();
        long endTime = System.nanoTime(); //timer
        long duration = (endTime - startTime); //timer
        System.out.println(String.format("generation in %d ms", duration / 1000000)); //timer
        //System.out.println(String.format("Number iter - %d\nScale - %f\nPers - %f\nSeed - %d\n---", tempNumberIr,tempScale, tempPers, seed ));

    }

    private void generateHeightNwaterMap(){
        double heightMap[][] = new double[height][width];
        double waterMap[][] = new double[height][width];
        double scale = .001650;
        double pers = .240000;
        int numberIter = 5;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                double val = SimplexNoise.sumOctave(numberIter, i, j, seed, pers, scale);
                val = (val+1)/2;
                heightMap[i][j] = linearVal2(val);
                waterMap[i][j] = val > 0.8 ? val : 0;
            }
        }
        this.heightMap = heightMap;
        this.waterMap = waterMap;
    }

    /*private int tempNumberIr = 5;
    public void setTempNumberIr(int tempNumberIr) {
        if (this.tempNumberIr != tempNumberIr){
            this.tempNumberIr = tempNumberIr;
            reroll();
        }
    }

    private double tempScale = 0.0031;
    public void setTempScale(double tempScale) {
        tempScale = tempScale / 100000;
        if (this.tempScale != tempScale){
            this.tempScale = tempScale;
            reroll();
        }
    }

    private double tempPers = 0.14;
    public void setTempPers(double tempPers) {
        tempPers = tempPers / 100;
        if (this.tempPers != tempPers){
            this.tempPers = tempPers;
            reroll();
        }
    }*/

    private void generatePopul(){
        double popMap[][] = new double[height][width];
        double scale = 0.00165;
        double pers = 0.14;
        int numberIter = 5;        //5
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                double val = SimplexNoise.sumOctave(numberIter, i, j, Pseed, pers, scale);
                popMap[i][j] = (val+1)/2;
                popMap[i][j] = popMap[i][j] > 0.65 ? popMap[i][j] : 0; //0.65
            }
        }
        if ( waterMap != null){
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if(waterMap[i][j] == 0){
                        popMap[i][j] = 0;
                    }
                }
            }
        }
        this.populationMap = popMap;
    }

    private void generateSafetyMap(){
        double saveMap[][] = new double[height][width];
        double scale = 0.00265;      //0.0031
        double pers = 0.24;        //0.14
        int numberIter = 2;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                double val = SimplexNoise.sumOctave(numberIter, i, j, Sseed, pers, scale);
                saveMap[i][j] = (val+1)/2;
            }
        }
        if(populationMap != null){
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (populationMap[i][j] == 0){
                        saveMap[i][j] = 0;
                    }
                }
            }
        }
        this.safetyMap = saveMap;
    }

    double linearVal( double val){
        double f = 0;
        if(val < .14){
            f = 0;
        } else if(val < .51){
            f = .2;
        } else if(val < .85f){
            f = .51;
        } else if(val < .98f){
            f = .85;
        } else
            f = 1;
        return f;
    }
    double linearVal2(double val){
        double f = 0;
        if(val < .1){
            f = 0;
        } else if(val < .2){
            f = .1;
        } else if(val < .3){
            f = .2;
        } else if(val < .4){
            f = .3;
        } else if(val < .5){
            f = .4;
        } else if(val < .6){
            f = .5;
        } else if(val < .7){
            f = .6;
        } else if(val < .8){
            f = .7;
        } else if(val < .9){
            f = .8;
        } else {
            f = .85;
        }
        return f;
    }


    public double[][] getPopulationMap() {
        return populationMap;
    }

    public double[][] getHeightMap() {
        return heightMap;
    }

    public double[][] getWaterMap() {
        return waterMap;
    }

    public double[][] getSafetyMap() {
        return safetyMap;
    }

    public int getHeight(){
        return height;
    }

    public int getWidth(){
        return width;
    }

    public int getGridSize() {
        return gridSize;
    }


}
