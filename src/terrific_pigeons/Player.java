package terrific_pigeons;

public abstract class Player {
    /*???????A life kell egyáltalán ide??????*/
    protected int life;
    protected int work = 4;
    protected Tile myTile;
    protected Thing[] things;
    protected boolean inWater = false;


    /*Ez a függvény felel a Player egyik tile-ról
     * a másikra való mozgatásáért.
     * @param t A jégtábla amire a játékos lép.
     */
    public void move(Tile t)
    {
        System.out.println("-> move(t1) -> e");
        System.out.println("e -> moveRequest(e) -> t1");
        t.moveRequest(this);
        System.out.println("<- e");
    }

    /*
     * A játékos lelapátol egy egység havat a
     * saját jégtáblájáról.
     * */
    public void dig() {}

    /*
     * A játékos használja valamelyik tárgyát.
     * @param t A játékos által használni kívánt tárgy
     * */
    public void use(Thing t) {}

    /*
     * A játékos elpasszolja a körét, nem él az összes lépésével.
     * */
    public void pass() {}

    /*
     * A játékos meghal.
     * */
    public void die() {}

    /*
     * A játékos felveszi a jégtábláján lévő tárgyat.
     * */
    public void equip() {}

    /*
     * Az adott tárgy bekerül a játékos tárgyai közé.
     * @param t A berakott tárgy.
     * */
    public void addThing(Thing t) {}

    /*
     * Az adott tárgy kikerül a játékos tárgyai közül.
     * @param t A kikerülő tárgy.
     * */
    public void removeThing(Thing t) {}

    /*
     * A játékos elvégez egy munkát, ezzel munkamennyisége
     * eggyel megnő.
     * */
    public void work() {}

    /*
     * Növeli az életerőt eggyel.
     * */
    public void addLife() {}

    /*
     * Csökkenti az életerőt eggyel.
     * */
    public void subLife() {}

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
    public void setWork(int work) {
        this.work = work;
    }

    /**/
    public Tile getTile()
    {
        System.out.println("t1 <- t2 <- e");
        return myTile;
    }

    public void setMyTile(Tile t) {

        this.myTile = t;
    }

    /**/
    public Thing[] getThings() {
        return things;
    }

    /*
     * Sztem ez nem kell ide mert van egy addThingunk
     * opinions???
     * de nem ezt használná?(énsevagyokbiztos)
     * */
    public void setThings(Thing thing, int position) {
        this.things[position] = thing;
    }

    /*
     * Visszaadja, hogy a játékos vízben van-e.
     * */
    public boolean getInWater() {
        return inWater;
    }

    /**/
    public void setInWater(boolean inWater) {
        this.inWater = inWater;
    }


    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }
}