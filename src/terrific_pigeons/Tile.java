package terrific_pigeons;

import java.util.ArrayList;

public class Tile {

    protected int snow;
    protected boolean safe = false;
    protected boolean safeByTent = false;
    protected int limit = -1;
    protected Thing thing;
    protected ArrayList<MoveAble> movables = new ArrayList<>();
    protected ArrayList<Tile> neighbours = new ArrayList<>();
    private int tileId;
    //csak a szkeletonhoz hoztuk létre ezt a változót
    private String name;

    public Tile(int id){tileId = id; }

    /*
     * Megnöveli a jégtáblán lévő hó mennyiségét.
     * @param snow Ennyivel növeli a havat
     * */
    public void addSnow(int sn)
    {
        setSnow(sn+snow);
    }

    /*
     * Csökenti a jégtáblán lévő hó mennyiségét.
     * @param snow Ennyivel csökkenti a havat*/
    public void subSnow(int i) {
        if(snow > 0) {
            this.setSnow(snow - i);
        }
    }

    /*
     * Fogadja a játékost.
     * @param p A mezőre lépő játékos
     * */
    public void receive(MoveAble m)
    {
        movables.add(m);
    }

    /*
     * Eltávolítja a játékost.
     * @param p A mezőről távozó játékos
     * */
    public void remove(MoveAble m)
    {
        movables.remove(m);
    }

    /*
     * Játékos átmozgatása.
     * @param p: Az átléptetendő játékos
     * */
    public void moveRequest(MoveAble m)
    {
        Tile t2 = m.getTile();
        t2.remove(m);
        m.setMyTile(this);
        this.receive(m);
    }

    /*
     * Eltávolítja a mezőn lévő tárgyat.
     */
    public void removeThing()
    {
        this.thing = null;
    }

    /*
    *Visszaadja a tile-on lévő hó mennyiségét.
    */
    public int getSnow() {
        return snow;
    }

    /*
    *A snow változó értékét megváltoztatja.
    * @param snow: A snow változót ezzel az értékkel írja felül.
    */
    public void setSnow(int snow)
    {
        this.snow = snow;
    }

    /*
    * Visszaadja az adott tile szomszédait.
    */
    public ArrayList<Tile> getNeighbours()
    {
        return neighbours;
    }
    /*
     * Beállít egy jégtáblát szomszédnak és a paraméterként beállított mezőnek is beállítja szomszédnak.
     * @param t: a szomszédnak beállított tábla
     */
    public void addNeighbour(Tile t)
    {
        this.neighbours.add(t);
        t.getNeighbours().add(this);
    }

    /*
    *Visszaadja, hogy a mezőn van-e iglu
     */
    public boolean getSafe()
    {
        /*System.out.println("m -> getSafe() -> " + this.getName());
        System.out.println("m <- "+ String.valueOf(this.safe) +" <- " + this.getName());*/
        return safe;
    }

    /*
    *Beállítja, hogy a mezőn van-e iglu
     */
    public void setSafe(boolean s)
    {
        this.safe=s;
    }

    /*
     * Visszaadja a jégtáblán álló karakterek listáját.
     */
    public ArrayList<MoveAble> getMovables()
    {
        return movables;
    }

    /*
     * Beállítja milyen tárgy legyen a mezőn..
     */
    public void setThing(Thing t)
    {
        this.thing = t;
    }

    public Thing getThing()
    {
        return thing;
    }

    public int getLimit() {
        return limit;
    }

    public void setName(String s){this.name = s;}
    public String getName(){return this.name;}
    public void addMoveAbles(MoveAble m) { this.movables.add(m); }
    public void setSafeByTent(boolean b){safeByTent = b;}
    public boolean getSafeByTent(){return this.safeByTent ;}

    public int getTileId() {
        return tileId;
    }
}