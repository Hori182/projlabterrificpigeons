package terrific_pigeons;

import java.util.ArrayList;

public abstract class Player extends MoveAble {
    protected int life;
    //protected int work = 0;
    //protected Tile myTile;
    protected ArrayList<Thing> things = new ArrayList<>();
    //protected boolean inWater = false;

    /*Ez a függvény felel a Player egyik tile-ról
     * a másikra való mozgatásáért.
     * @param t A jégtábla amire a játékos lép.
     */
    public void move(Tile t)
    {
        t.moveRequest( this);
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
     * A játékos meghal.
     * */
    public void die() {
        System.out.print("die()");
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
     * Növeli az életerőt eggyel.
     * */
    protected boolean addLife() { return  true;}

    /*
     * Csökkenti az életerőt eggyel.
     * */
    public void subLife()
    {
        if (this.myTile.getSafe() == false && this.myTile.getSafeByTent() == false)
        {
            if(this.getLife()-1 > 1) {
                setLife(this.getLife() - 1);
            }
            else{
                setLife(this.getLife()-1);
                die();
            }
        }
    }

    /*
    * Visszaadja a Player-nél lévő Thing-eket (things).
    * */
    public ArrayList<Thing> getThings() {
        return things;
    }

    /*
     * Visszaadja, hogy jelenleg hány élete van.
     */
    public int getLife() {
        return life;
    }

    /*
     * Beállítja a life attribútum új értékét.
     * @param life: élet - a life új értéke
     */
    public void setLife(int life)
    {
        System.out.println("e -> setLife(" + life + ") -> e");
        this.life = life;
        System.out.println("t <- e");
    }
}