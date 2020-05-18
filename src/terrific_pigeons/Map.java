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
    /*Map konstruktora*/
    public Map() {
        tiles = new ArrayList<>();
    }

    /*
     * Hóvihar, ami elkaphatja a játékosokat, ezzel csökkentve az életüket,
     * valamint növeli a hó mennyiségét a jégtáblákon.
     * */
    public void snowStorm() {
        Random rand = new Random();

        for (Tile t : tiles) {
            int r = rand.nextInt(4);
            if (r==1) {
                t.addSnow(1);
                ArrayList<MoveAble> temp;
                if (t.getMovables() != null) temp = t.getMovables();
                else temp = null;
                for (MoveAble m : temp) {
                    if (!m.myTile.getSafe() && !m.myTile.getSafeByTent())
                        m.subLife();
                }
            }
        }
    }
    /*
    Hozzáad egy tile-t a tiles arraylist-hez.
    * @param Tile t: Ezt a tile adja hozzá a tileshoz.
    * */
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

    /*
    * Visszaadja a Map_data tartalmát.
    * */
    public int[] getMap_data() {
        return map_data;
    }
    /*
    * Visszaadja a things tartalmát.
    * */
    public Thing[] getThings() {
        return things;
    }

    /*
    * Visszaadja, hogy num_of_tiles értékét.
    * */
    public int getNum_of_tiles() {
        return num_of_tiles;
    }
}
