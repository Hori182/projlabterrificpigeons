package terrific_pigeons;

public class Tile {

    protected int snow;
    protected boolean safe;
    protected Thing thing;
    protected Player[] players;
    private Tile[] neighbours;

    /*
     * Megnöveli a jégtáblán lévő hó mennyiségét.
     * @param snow Ennyivel növeli a havat
     * */
    public void addSnow(int snow) {}

    /*
     * Csökenti a jégtáblán lévő hó mennyiségét.
     * @param snow Ennyivel csökkenti a havat*/
    public void subSnow(int snow) {}

    /*
     * Fogadja a játékost.
     * @param p A mezőre lépő játékos
     * */
    public void Receive(Player p) {}

    /*
     * Eltávolítja a játékost.
     * @param p A mezőről távozó játékos
     * */
    public void Remove(Player p)
    {
        System.out.println("t1 <- t2");
    }

    /*
     * Játékos átmozgatása.
     * @param p Az átléptetendő játékos
     * */
    public void moveRequest(Player p)
    {
        System.out.println("t1 -> getTile(e) -> e");
        Tile t2 = p.getTile();
        System.out.println("t1 -> remove(e) -> t2");
        t2.Remove(p);
        System.out.println("t1 -> setMyTile(e) -> e");
        p.setMyTile(this);
        System.out.println("t1 <- e");
        System.out.println("t1 -> Receive(e) -> t1");
        System.out.println("e <- t1");
    }

    /*
     * Eltávolítja a mezőn lévő tárgyat.
     * */
    public void removeThing() {}

    /**/
    public int getSnow() {
        return snow;
    }

    /**/
    public void setSnow(int snow) {
        this.snow = snow;
    }

    /**/
    public Tile[] getNeighbours() {
        return neighbours;
    }
    /*Ez nemtomkelle*/
    public void setNeighbours(Tile[] neighbours) {
        this.neighbours = neighbours;
    }
}