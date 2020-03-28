package terrific_pigeons;

public class Researcher extends Player {

    private int life = 4;

    public Researcher(){System.out.println("Researcher created");}

    /*
     * Megnézi, hogy az adott táblának mennyi a teherbírása.
     * @param t A megviszgált jégtábla.
     * */
    public void look(Tile t) {}
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