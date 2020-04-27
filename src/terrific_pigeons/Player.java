package terrific_pigeons;

import java.io.PrintWriter;
import java.util.ArrayList;

public abstract class Player extends MoveAble {
    //protected int life;
    //protected int work = 0;
    //protected Tile myTile;
    protected ArrayList<Thing> things = new ArrayList<>();
    //protected String playerid;
    //protected boolean inWater = false;

    /*Ez a függvény felel a Player egyik tile-ról
     * a másikra való mozgatásáért.
     * @param t A jégtábla amire a játékos lép.
     */
    public void move(Tile t)
    {
        t.moveRequest( this);
        work();
    }

    /*
     * A játékos lelapátol egy egység havat a
     * saját jégtáblájáról.
     * */
    public void dig() {
        myTile.subSnow(1);
        work();
    }

    public void build() {
        System.out.println("Current player is not an eskimo!");
    }

    public void look(Tile t) {
        System.out.println("Current player is not a researcher!");
    }

    /*
     * A játékos használja valamelyik tárgyát.
     * @param t A játékos által használni kívánt tárgy
     * */
    public void use(Thing t)
    {
        t.useThing();
    }

    /*
     * A játékos felveszi a jégtábláján lévő tárgyat.
     * */
    public void equip()
    {
        Thing th = myTile.getThing();
        th.setOwner(this);
        addThing(th);
        myTile.removeThing();
        this.work();
    }

    /*
     * Az adott tárgy bekerül a játékos tárgyai közé.
     * @param t A berakott tárgy.
     * */
    public void addThing(Thing t)
    {
        this.things.add(t);
        t.setOwner(this);
    }

    /*
     * Az adott tárgy kikerül a játékos tárgyai közül.
     * @param t A kikerülő tárgy.
     * */
    public void removeThing(Thing t) {
        this.things.remove(t);
    }

    /*
     * A játékos elvégez egy munkát, ezzel munkamennyisége
     * eggyel megnő.
     * */
    public void work()
    {
        if( !(work+1 > 4)) {
            this.setWork(work + 1);
        }
    }

    /*
    * Visszaadja a Player-nél lévő Thing-eket (things).
    * */
    public ArrayList<Thing> getThings() {
        return things;
    }

    /*
    * A játékost megtámadta egy medve
    * */
    public void attacked(){
        if (!myTile.getSafe())
            setLife(0);
    }

    /*
    * Játékos kirajzolása konzolra
    * */
    public void draw(){
        System.out.print(moveableid + ":" + myTile.getTileId() + ":" + life + ":" + (4-work) + ":" + (inWater ? "+:" : "-:"));
        ArrayList<String> thingids = new ArrayList<>();
        for (Thing t : things)
            thingids.add(t.Name());
        if (thingids.size()>0)
            System.out.println(String.join(",", thingids) + ";");
        else
            System.out.println(" ;");
    }

    /*
     * Játékos kiírása fájlba
     * */
    public void save(PrintWriter writer){
        writer.print(getId()+":"+getTile().getTileId()+":"+getLife()+":"+(4-getWork())+":"+(getInWater()?"+":"-")+":");
        ArrayList<String> thingids = new ArrayList<>();
        for (Thing t : getThings())
            thingids.add(t.Name());
        if (thingids.size()>0)
            writer.println(String.join(",", thingids)+":");
        else
            writer.println("-:");
    }

}