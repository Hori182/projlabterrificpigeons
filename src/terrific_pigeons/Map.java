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
    * Hóvihar, ami elkaphatja a játékosokat, ezzel csökkentve az életüket,
    * valamint növeli a hó mennyiségét a jégtáblákon.
    * */
    public void snowStorm()
    {
        System.out.println("-> snowstorm() -> m");
        for( Tile t : tiles)
        {
            t.addSnow(1);
            ArrayList<Player> temp = t.getPlayers();
            for( Player p : temp)
            {
                p.subLife();
            }
        }
        System.out.println("<- m");
    }

    public void addTile(Tile t)
    {
        System.out.println("-> addTile(" + t.getName() + ") -> m");
        this.tiles.add(t);
        System.out.println("<- m");
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
