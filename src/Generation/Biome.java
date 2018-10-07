package Generation;

import java.util.List;

public enum Biome{
    RIVER, DESERT, PLAIN, FOREST, BRUSH, TAIGA, MOUNT, SNOW, SITY;




    static private int[] bitcount(Biome[] bio){
        int[] count = new int[Biome.values().length];
        for (Biome b: bio) {
            int index = b.ordinal();
            count[index] += 1;
        }
        return count;
    }

    static private int[] bitcount(List<Biome> bio){
        int[] count = new int[Biome.values().length];
        for (Biome b: bio) {
            int index = b.ordinal();
            count[index] += 1;
        }
        return count;
    }

    static public Biome maxBiome(Biome[] bio){
        int[] b = bitcount(bio);
        int max = b[0];
        int index = 0;
        for (int i = 0; i < b.length; i++) {
            if (max < b[i]) {
                max = b[i];
                index = i;
            }
        }
        return Biome.values()[index];
    }

    static public Biome maxBiome(List<Biome> bio){
        int[] b = bitcount(bio);
        int max = b[0];
        int index = 0;
        for (int i = 0; i < b.length; i++) {
            if (max < b[i]) {
                max = b[i];
                index = i;
            }
        }
        return Biome.values()[index];
    }
}
