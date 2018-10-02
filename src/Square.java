import java.awt.Color;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Square {
    private double height;
    private double moist;
    private int x, y;
    private Biome bio;



    public Square(){
        System.out.println("f u");
        throw new java.lang.Error("try something else");
    }
    public Square(int x, int y, int seed){
        this.x = x;
        this.y = y;
        calcHeight(seed);
        calcMoinst(seed);
        setBio();
    }



    public int getX() {
        return x;
    } public int getY(){
        return y;
    } public double getHeight (){
        return height;
    } public void setHeight (double height){
        this.height = height;
    } public Biome getBio(){
        return bio;
    } public void setBio(Biome bio) {
        this.bio = bio;
    } public double getMoist () {
        return moist;
    } public void setMoist(double moist){
        this.moist = moist;
    }





    private void calcHeight(int seed){
        double scale = .007;
        double pers = .3;
        height = SimplexNoise.sumOctave(16, x, y, seed, pers, scale);
        height = (height+1)/2;
    }
    private void calcMoinst (int seed) {
        double scale = .004;
        double pers = .3;
        seed = seed;
        moist = SimplexNoise.sumOctave(16, x, y, seed, pers, scale);
        moist = (moist+1)/2;
    }

    public void setBio(){
        //TODO биомы нормально
        if(height < .25f){
                bio = Biome.RIVER;
        }
        else if(height < .5f){
            if(moist < .3f)
                bio = Biome.DESERT;
            else
                bio = Biome.PLAIN;
        } else if(height < .7f){
            if(moist < .5f)
                bio = Biome.BRUSH;
            else
                bio = Biome.FOREST;
        } else if(height < .9f){
            if(moist < .5f)
                bio = Biome.TAIGA;
            else
                bio = Biome.MOUNT;
        } else
            bio = Biome.SNOW;

    }

    public Color getColor(){
        switch (bio) {
            case RIVER:
                //return Color.decode("#41C4EB");
                return Col.addFunkyShCol(Color.decode("#41C4EB"), getHeight(), getMoist());
            case DESERT:
                //return Color.decode("#EDC9AF");
                return Col.addFunkyShCol(Color.decode("#EDC9AF"), getHeight(), getMoist());
            case PLAIN:
                //return Color.decode("#49b484");
                return Col.addFunkyShCol(Color.decode("#49b484"), getHeight(), getMoist());
            case FOREST:
                //return Color.decode("#228B22");
                return Col.addFunkyShCol(Color.decode("#228B22"), getHeight(), getMoist());
            case BRUSH:
                //return Color.decode("#7d8e74");
                return Col.addFunkyShCol(Color.decode("#7d8e74"), getHeight(), getMoist());
            case TAIGA:
                //return Color.decode("#808000");
                return Col.addFunkyShCol(Color.decode("#808000"), getHeight(), getMoist());
            case MOUNT:
                //return Color.decode("#977c53");
                return Col.addFunkyShCol(Color.decode("#977c53"), getHeight(), getMoist());
            case SNOW:
                //return Color.WHITE;
                return Col.addFunkyShCol(Color.WHITE, getHeight(), getMoist());
            case SITY:
                return Color.decode("#7b9095");
            default:
                return Color.BLACK;
        }
    }



    static double averangeHeight (Square[][] s){
        int numberSq = s.length;
        double av = 0;
        for (int i = 0; i < numberSq; i++) {
            for (int j = 0; j < numberSq; j++) {
                av += s[i][j].getHeight();
            }
        }
        av /= (numberSq*numberSq);
        return av;
    }
    static Biome averangeBiome(Square[][] s){
        int len = s.length;
        Biome[] bio = new Biome[len * len];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                bio[i*len+j] = s[i][j].getBio();
            }
        }
        return Biome.maxBiome(bio);
    }

    static Biome averangeBiome(List<Square> s){
        List<Biome> bio = s.stream().map(Square::getBio).collect(Collectors.toList());
        return Biome.maxBiome(bio);
    }

    static public float distance(Square s1, Square s2){
        return (float)Math.sqrt(Math.pow(s2.getX() - s1.getX(),2) + Math.pow(s2.getY() - s1.getY(),2));
    }
    static public int distanceLinear(Square s1, Square s2){
        int x = Math.abs(s1.getX() - s2.getX());
        int y = Math.abs(s1.getY() - s2.getY());
        int result = y > x ? (14*x + 10*(y-x)) : (14*y + 10*(x-y));
        return result;
    }
    static public float distanceLinearWHeight(Square s1, Square s2){
        int d = distanceLinear(s1,s2);
        return  d;
    }



}
