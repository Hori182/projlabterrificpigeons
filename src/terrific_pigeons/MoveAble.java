package terrific_pigeons;

public abstract class MoveAble {
    protected boolean inWater = false;
    protected int work = 0;
    protected Tile myTile;

    protected  void move(Tile t){}
    /*
     * A játékos elpasszolja a körét, nem él az összes lépésével.
     * */
    public void pass() {
        System.out.println(" -> pass() -> e");
        this.setWork(4);
        //System.out.println("e -> setWork(4) -> e");
        System.out.println("e <- e");
        System.out.println("<- e");
    }
    /*
     * Visszaadja, hogy a játékos vízben van-e.
     * */
    public boolean getInWater() {
        System.out.println("g-> getInWater() -> e");
        System.out.println("g <- "+ inWater + " <- e");
        return inWater;
    }

    /*
     * Beállítja az inWater értékét.
     * @param inWater: Ezzel az értékkel írja felül az inWater értékét.
     * */
    public void setInWater(boolean inWater)
    {
        System.out.println("t1 -> setInWater(true) -> e");
        this.inWater = inWater;
        System.out.println("t1 <- e");
    }

    /*
     * Visszaadja a work változó értékét.
     * */
    public int getWork() {
        System.out.println("g-> getWork() -> e");
        System.out.println("g <- "+ work + " <- e");
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
        System.out.println("t1 <- "+this.myTile.getName()+" <- e");
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
}
