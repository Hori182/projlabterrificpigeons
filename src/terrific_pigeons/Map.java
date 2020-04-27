package terrific_pigeons;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Map {

    private ArrayList<Tile> tiles;
    private int[] map_data = new int[3];
    private Thing[] things = new Thing[6];
    private PistolPart[] ppt = new PistolPart[3];
    private int num_of_tiles;

    public Map() {
        tiles = new ArrayList<>();
    }

    /*
     * Hóvihar, ami elkaphatja a játékosokat, ezzel csökkentve az életüket,
     * valamint növeli a hó mennyiségét a jégtáblákon.
     * */
    public void snowStorm() {
        for (Tile t : tiles) {
            t.addSnow(1);

            ArrayList<MoveAble> temp;
            if(t.getMovables()!=null) temp = t.getMovables();
            else temp = null;
            for (MoveAble m : temp) {
                if (!m.myTile.getSafe() || !m.myTile.getSafeByTent())
                    m.subLife();
            }
        }
    }

    public void addTile(Tile t) {
        this.tiles.add(t);
    }

    /*
     * Visszaadja a tile-okat, amik a Map-ot alkotjak.
     * */
    public ArrayList<Tile> getTiles() {
        return tiles;
    }

    /*
     * Beállítja a tiles tömb értékét.
     * @param tiles : Ezzel írja felül a tiles értékét.
     * */
    public void setTiles(ArrayList<Tile> tiles) {
        this.tiles = tiles;
    }

    public void generateMap(int tiles) {
        num_of_tiles = tiles;

        int unstabile = (int) (tiles * 0.3);
        int water = (int) (tiles * 0.1);
        int stabile = (tiles) - (unstabile + water);

        map_data[0] = stabile;
        map_data[1] = unstabile;
        map_data[2] = water;
    }

    public void initPistolParts() {
        ppt[0] = new PistolPart();
        ppt[1] = new PistolPart();
        ppt[2] = new PistolPart();

        ppt[0].setId(0);
        ppt[1].setId(1);
        ppt[2].setId(2);
    }

    public int[] getMap_data() {
        return map_data;
    }

    public Thing[] getThings() {
        return things;
    }

    public PistolPart[] getPpt() {
        return ppt;
    }

    public void shuffle() {
        Collections.shuffle(tiles);
    }

    public int getNum_of_tiles() {
        return num_of_tiles;
    }
}
