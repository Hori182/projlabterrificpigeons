package terrific_pigeons;

public class Map {

    private Tile[] tiles;

    /*
    * ??
    * */
    public void snowStorm()
    {


    }

    /*
    * Visszaadja a tile-okat, amik a Map-ot alkotjak.
    * */
    public Tile[] getTiles() {
        return tiles;
    }
    /*
    * Beállítja a tiles tömb értékét.
    * @param tiles : Ezzel írja felül a tiles értékét.
    * */
    public void setTiles(Tile[] tiles) {
        this.tiles = tiles;
    }
}
