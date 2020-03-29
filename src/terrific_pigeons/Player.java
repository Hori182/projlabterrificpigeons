package terrific_pigeons;

import java.util.ArrayList;

public abstract class Player {
    protected int life;
    protected int work = 0;
    protected Tile myTile;
    protected ArrayList<Thing> things = new ArrayList<>();
    protected boolean inWater = false;


    /*Ez a függvény felel a Player egyik tile-ról
     * a másikra való mozgatásáért.
     * @param t A jégtábla amire a játékos lép.
     */
    public void move(Tile t)
    {
        System.out.println("-> move(t1) -> e");
        t.moveRequest(this);
        System.out.println("<- e");
    }

    /*
     * A játékos lelapátol egy egység havat a
     * saját jégtáblájáról.
     * */
    public void dig() {
        //ezt jelölni kéne hogy ez a myTile????? vagy mindegy
        myTile.subSnow(1);
        work();
        System.out.println("<- e");
    }

    /*
     * A játékos használja valamelyik tárgyát.
     * @param t A játékos által használni kívánt tárgy
     * */
    public void use(Thing t) {}

    /*
     * A játékos elpasszolja a körét, nem él az összes lépésével.
     * */
    public void pass() {
        System.out.println("-> pass() -> e");
        this.setWork(4);
        //System.out.println("e -> setWork(4) -> e");
        System.out.println("e <- e");
        System.out.println("<- e");
    }

    /*
     * A játékos meghal.
     * */
    public void die() {}

    /*
     * A játékos felveszi a jégtábláján lévő tárgyat.
     * */
    public void equip()
    {
        System.out.println("-> equip() -> e");
        Thing th = myTile.getThing();
        th.setOwner(this);
        addThing(th);
        myTile.removeThing();
        this.work();
        System.out.println("<- e");
    }

    /*
     * Az adott tárgy bekerül a játékos tárgyai közé.
     * @param t A berakott tárgy.
     * */
    public void addThing(Thing t)
    {
        System.out.println("e-> addThing(shovel) -> e");
        this.things.add(t);
        System.out.println("e <- e");
    }

    /*
     * Az adott tárgy kikerül a játékos tárgyai közül.
     * @param t A kikerülő tárgy.
     * */
    public void removeThing(Thing t) {
        System.out.println("f -> removeThing(f) -> e");
    }

    /*
     * A játékos elvégez egy munkát, ezzel munkamennyisége
     * eggyel megnő.
     * */
    public void work()
    {
        System.out.println("e -> work() -> e");
        this.setWork(1);
        System.out.println("e <- e");
    }

    /*
     * Növeli az életerőt eggyel.
     * */
    public void addLife() {
        System.out.println("f -> addLife() -> e");
        System.out.println("f <- e");
    }

    /*
     * Csökkenti az életerőt eggyel.
     * */
    public void subLife()
    {
        System.out.println("m -> subLife() -> p");
        if (this.myTile.getSafe()==false)
        {
            setLife(this.getLife()-1);
        }
        System.out.println("m <- p");
    }

    /*
    * Visszaadja a work változó értékét.
    * */
    public int getWork() {
        return work;
    }
    /*
    * A work változó értékét megváltoztatja.
    * @param work: A work változót ezzel az értékkel írja felül.
    * */

    public void setWork(int work)
    {
        this.work = work;
        System.out.println("e -> setWork(" + work + ") -> e");
        System.out.println("e <- e");
    }

    /*
    * Visszaadja azt a tile-t amin a player áll.
    * */
    public Tile getTile()
    {
        System.out.println("t1 -> getTile(e) -> e");
        System.out.println("t1 <- t2 <- e");
        return myTile;
    }
    /*
    * Beáálítja a myTile változó értékét.
    * @param t: Ez a myTile változó új értéke.
    * */
    public void setMyTile(Tile t)
    {
        System.out.println(t.getName() + " -> setMyTile("+ t.getName() + ") -> e");
        System.out.println(t.getName() + " <- e");
        this.myTile = t;
    }

    /*
    * Visszaadja a Player-nél lévő Thing-eket (things).
    * */
    public ArrayList<Thing> getThings() {
        return things;
    }

    /*
     * Visszaadja, hogy a játékos vízben van-e.
     * */
    public boolean getInWater() {
        return inWater;
    }

    /*
    * Beállítja az inWater értékét.
    * @param inWater: Ezzel az értékkel írja felül az inWater értékét.
    * */
    public void setInWater(boolean inWater) {
        this.inWater = inWater;
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
        System.out.println("t1 <- e");
    }
}