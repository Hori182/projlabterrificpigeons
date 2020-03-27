package terrific_pigeons;

public class Resercher extends Player {

    private int life = 4;

    /*
     * Megnézi, hogy az adott táblának mennyi a teherbírása.
     * @param t A megviszgált jégtábla.
     * */
    public void look(Tile t) {}

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

}