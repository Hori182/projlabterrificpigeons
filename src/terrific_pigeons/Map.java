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
           // Player p1()
            t.addSnow(1);
            System.out.println("m -> getPlayers() -> t");
            System.out.println("m <- players <- t");
            for( Player p : t.getPlayers())
            {
               // p.subLife(1);
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
