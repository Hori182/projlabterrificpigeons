package terrific_pigeons;

import java.util.ArrayList;

public class Tile {

    protected int snow;
    protected boolean safe = false;
    protected int limit = 100;
    protected Thing thing;
    protected ArrayList<MoveAble> movables = new ArrayList<>();
    private ArrayList<Tile> neighbours = new ArrayList<>();
    //csak a szkeletonhoz hoztuk létre ezt a változót
    private String name;

    public Tile()
    {
        System.out.println("Tile created");
    }

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
        if(i == 1){
            System.out.println("e -> subSnow(" + i + ") -> " + this.getName());
            this.setSnow(snow-i);
            System.out.println("e <- " + this.getName());
        } else if(i == 2){
            System.out.println("s -> subSnow(" + i + ") -> " + this.getName());
            this.setSnow(snow-i);
            System.out.println("e <- " + this.getName());
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
     * Beállít egy jégtáblát szomszédnak.
     * @param t: a szomszédnak beállított tábla
     */
    public void addNeighbour(Tile t)
    {
        this.neighbours.add(t);
    }

    /*
    *Visszaadja, hogy a mezőn van-e iglu
     */
    public boolean getSafe()
    {
        System.out.println("m -> getSafe() -> " + this.getName());
        System.out.println("m <- "+ String.valueOf(this.safe) +" <- " + this.getName());
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
     *Beállítja, hogy a mezőn van-e iglu
     */
    public int getLimit()
    {
        return limit;
    }

    /*
     *Beállítja, hogy a mezőn van-e iglu
     */
    public void setLimit(int l)
    {
        System.out.println("<- t");
        limit= l;
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
        System.out.println("-> setThing(shovel) ->" + this.getName());
        System.out.println("<- " + this.getName() );
        this.thing = t;
    }

    public Thing getThing()
    {
        System.out.println("e -> getThing() -> " + this.getName());
        System.out.println("e <- shovel <- " + this.getName());
        return thing;
    }

    public void setName(String s){this.name = s;}
    public String getName(){return this.name;}
    public void addMoveAbles(MoveAble m) { this.movables.add(m); }
}