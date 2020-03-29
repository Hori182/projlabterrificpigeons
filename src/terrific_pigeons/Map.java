package terrific_pigeons;

import java.util.ArrayList;

public class Map {

    private ArrayList<Tile> tiles;

    public Map()
    {
        tiles = new ArrayList<>();
        System.out.println("Map created");
    }
    /*
    * ??
    * */
    public void snowStorm()
    {
        System.out.println("-> snowstorm() -> m");
        for( Tile t : tiles)
        {
            t.addSnow(1);
            for( Player p : t.getPlayers())
            {
            }
        }

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
}
