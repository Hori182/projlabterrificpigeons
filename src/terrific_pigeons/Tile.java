package terrific_pigeons;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class Tile implements IViewable{

    protected int snow;
    protected boolean safe = false;
    protected boolean safeByTent = false;
    protected int limit = -1;
    protected Thing thing = null;
    protected ArrayList<MoveAble> movables = new ArrayList<>();
    protected ArrayList<Tile> neighbours = new ArrayList<>();
    private int tileId;
    //csak a szkeletonhoz hoztuk létre ezt a változót
    private String name;
    protected int X;
    protected int Y;
    public Tile(int id){ tileId = id; }

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
        boolean isNeighbour = false;
        for(int i = 0; i < t2.getNeighbours().size(); i++) {
            if(t2.getNeighbours().get(i).getTileId() == this.getTileId())
                isNeighbour = true;
        }
        if(isNeighbour) {
            t2.remove(m);
            m.setMyTile(this);
            receive(m);
        }
        else {
            System.out.println("Not neighbour!");
        }

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
        if(!neighbours.contains(t)) {
            this.neighbours.add(t);
        }
        if(!t.neighbours.contains(this)){
            t.getNeighbours().add(this);
        }
    }

    /*
    *Visszaadja, hogy a mezőn van-e iglu
     */
    public boolean getSafe()
    {
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
    Kirajzolja a Tile-t
     */
    protected void draw(){
        System.out.print(tileId + ":" + (safe ? "I" : (safeByTent ? "T" : " ")) + ":" + snow + ":");
        ArrayList<String> ids = new ArrayList<>();
        for (MoveAble m : movables )
            ids.add(m.getId());
        if (ids.size()>0)
            System.out.print(String.join(",", ids) + ":");
        else
            System.out.print(" :");

        ids = new ArrayList<>();
        for (Tile t : neighbours)
            ids.add(String.valueOf(t.getTileId()));
        if (ids.size()>0)
            System.out.print(String.join(",", ids) + ":");
        else
            System.out.print(" :");
        if (snow==0)
            System.out.println(thing==null ? " ;" : (thing.Name() + ";"));
        else
            System.out.println(" ;");

    }

    /*
     * Visszaadja a jégtáblán álló karakterek listáját.
     */
    public ArrayList<MoveAble> getMovables()
    {
        return movables;
    }

    /*Beállítja a thing változó értékét a t értékére.
    * @param: Thing t: ezzel az értékkel írja felül a thing-et.
    * */
    public void setThing(Thing t)
    {
        this.thing = t;
    }
    /*Visszaadja a thing változó értékét.*/
    public Thing getThing()
    {
        if(thing!=null){
            return thing;
        }
        return null;
    }
    /*Visszaadja a limit változó értékét.*/
    public int getLimit() {
        return limit;
    }
    /*
    * Beállítja a name változó értékét az s étékére.
     * @param: String s: ezzel az értékkel írja felül a name-et.
    * */
    public void setName(String s){this.name = s;}
    /*Visszaadja a name változó értékét.*/
    public String getName(){return this.name;}
    /*Hozzáadunk egy új elemet a moveables-hrz
    * @param MoveAble m: ezt adjuk a moveable-hez.
    * */
    public void addMoveAbles(MoveAble m) { this.movables.add(m); }
    /*
     * Beállítja a safeByTent változó értékét a b értékére.
     * @param: boolean b: ezzel az értékkel írja felül a name-et.
     * */
    public void setSafeByTent(boolean b){safeByTent = b;}
    /*Visszaadja a safeByTent értékét.*/
    public boolean getSafeByTent(){return this.safeByTent ;}
    /*Visszaadja a tileId értékét.*/
    public int getTileId() {
        return tileId;
    }
    /*
     * Beállítja milyen tárgy legyen a mezőn..
     */
    public void setThing(int a) {
        switch (a) {
            case 0: {
                Thing t = new Tent();
                this.thing = t;
                break;
            }
            case 1: {
                Thing t = new Food();
                this.thing = t;
                break;
            }
            case 2: {
                Thing t = new Shovel();
                this.thing = t;
                break;
            }
            case 3: {
                Thing t = new Rope();
                this.thing = t;
                break;
            }
            case 4: {
                Thing t = new FragileShovel();
                this.thing = t;
                break;
            }
            case 5: {
                Thing t = new DivingSuit();
                this.thing = t;
                break;
            }
        }
    }
    /*Kiírja a tile tartalmát fileba.*/
    public void save(PrintWriter writer){
        writer.print(tileId+":+:"+limit+":"+(safe?"I:":(safeByTent?"T:":"-:"))+snow+":");
        ArrayList<String> ids = new ArrayList<>();
        for (MoveAble m : movables)
            ids.add(m.getId());

        if (ids.size()>0) {
            writer.print(String.join(",", ids) + ":");
        }else
            writer.print("-:");
        writer.print(thing==null?"-:":(thing.Name()+":"));
        ids = new ArrayList<>();
        for (Tile t: neighbours)
            ids.add(String.valueOf(t.getTileId()));
        if (ids.size()>0)
            writer.println(String.join(",", ids)+":");
        else
            writer.println("-:");

    }


    @Override
    public void notify(View v) {

    }
    /*
    * Visszaadja az X értékét.
    * */
    @Override
    public int GetX() {
        return X;
    }
    /*
     * Visszaadja az Y értékét.
     * */
    @Override
    public int GetY() {
        return Y;
    }
    /*Felülírja az X értékét.
    * @param int x: X értkét ezzel írja felül.
    * */
    @Override
    public void SetX(int x) {
            this.X = x;
    }
    /*Felülírja az Y értékét.
     * @param int y: Y értkét ezzel írja felül.
     * */
    @Override
    public void SetY(int y) {
            this.Y = y;
    }
}