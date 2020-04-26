package terrific_pigeons;

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
        this.setWork(this.work + 1);
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

    public void draw(){
        System.out.print(moveableid + ":" + myTile.getTileId() + ":" + life + ":" + (4-work) + ":" + (inWater ? "+" : "-"));
        ArrayList<String> thingids = new ArrayList<>();
        for (Thing t : things)
            thingids.add(t.Name);
        if (thingids.size()>0)
            System.out.println(String.join(",", thingids) + ";");
        else
            System.out.println(" ;");
    }

}