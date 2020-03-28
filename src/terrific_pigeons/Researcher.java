package terrific_pigeons;

public class Researcher extends Player {

    private int life = 4;

    public Researcher(){System.out.println("Researcher created");}

    /*
     * A kutató elvégez egy munkát, ezzel munkamennyisége
     * eggyel megnő.
     * */
    public void work()
    {
        System.out.println("r -> setWork(work+1) -> r");
        this.setWork(work+1);
        System.out.println("r <- r");
    }

    /*
     * Megnézi, hogy az adott táblának mennyi a teherbírása.
     * @param t A megviszgált jégtábla.
     * */
    public void look(Tile t)
    {
        System.out.println("-> look(t) -> r");
        System.out.println("r -> getLimit() -> t");
        t.getLimit();
        System.out.println("<- limit <- r");
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
    public void setLife(int life) {
        this.life = life;
    }

}