package terrific_pigeons;

import java.util.ArrayList;

public class Tile {

    protected int snow;
    protected boolean safe = false;
    protected int limit = 100;
    protected Thing thing;
    protected ArrayList<Player> players;
    private Tile[] neighbours;
    //csak a szkeletonhot hoztuk létre ezt a változót
    private String name;

    public Tile()
    {
        players = new ArrayList<>();
        System.out.println("Tile created");
    }

    /*
     * Megnöveli a jégtáblán lévő hó mennyiségét.
     * @param snow Ennyivel növeli a havat
     * */
    public void addSnow(int sn)
    {
        System.out.println("m -> AddSnow(1) -> " + this.getName());
        setSnow(sn+snow);
        System.out.println("m <- " + this.getName());
    }

    /*
     * Csökenti a jégtáblán lévő hó mennyiségét.
     * @param snow Ennyivel csökkenti a havat*/
    public void subSnow(int snow) {
        System.out.println("e -> subSnow(" + snow + ") -> " + this.getName());
        this.setSnow(snow-1);
        System.out.println("e <- " + this.getName());
    }

    /*
     * Fogadja a játékost.
     * @param p A mezőre lépő játékos
     * */
    public void receive(Player p)
    {
        System.out.println("t1 -> Receive(e) -> t1");
        System.out.println("t1 <- t1");
        players.add(p);
    }

    /*
     * Eltávolítja a játékost.
     * @param p A mezőről távozó játékos
     * */
    public void remove(Player p)
    {
        System.out.println("t1 -> remove(e) -> t2");
        System.out.println("t1 <- t2");
    }

    /*
     * Játékos átmozgatása.
     * @param p: Az átléptetendő játékos
     * */
    public void moveRequest(Player p)
    {
        System.out.println("e -> moveRequest(e) -> " + this.getName());
        Tile t2 = p.getTile();
        t2.remove(p);
        p.setMyTile(this);
        this.receive(p);
        System.out.println("e <- " + this.getName());
    }

    /*
     * Eltávolítja a mezőn lévő tárgyat.
     */
    public void removeThing()
    {
        System.out.println("e <- t");
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
    public void setSnow(int snow) {
        System.out.println("t -> setSnow(" + snow + ") -> t");
        this.snow = snow;
        System.out.println("t <- t");
    }

    /*
    * Visszaadja az adott tile szomszédait.
    */
    public Tile[] getNeighbours() {
        return neighbours;
    }
    /*Ez nemtomkelle*/
    public void setNeighbours(Tile[] neighbours) {
        this.neighbours = neighbours;
    }

    /*
    *Visszaadja, hogy a mezőn van-e iglu
     */
    public boolean getSafe()
    {
        System.out.println("m -> getSafe() -> t");
        return safe;
    }

    /*
    *Beállítja, hogy a mezőn van-e iglu
     */
    public void setSafe(boolean s)
    {
        System.out.println("e -> setSafe(true) -> t");
        safe=s;
        System.out.println("e <- t");
    }

    /*
     *Beállítja, hogy a mezőn van-e iglu
     */
    public int getLimit()
    {

        System.out.println("r -> getLimit() -> t");

        System.out.println("r <- limit <- t");
        return limit;
    }

    /*
     *Beállítja, hogy a mezőn van-e iglu
     */
    public void setLimit(int l)
    {
        System.out.println("? <- t");
        limit= l;
    }

    public ArrayList<Player> getPlayers()
    {
        return players;
    }

    public void setThing(Thing t){this.thing = t;}

    public Thing getThing()
    {
        System.out.println("e <- shovel <- t");
        return thing;
    }

    public void setName(String s){this.name = s;}
    public String getName(){return this.name;}
}