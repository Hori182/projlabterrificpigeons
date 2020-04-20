package terrific_pigeons;

public class Researcher extends Player {

    public Researcher()
    {
        life=4;
    }

    /*
     * Megnézi, hogy az adott táblának mennyi a teherbírása.
     * @param t A megviszgált jégtábla.
     * */
    public void look(Tile t)
    {
        int l = t.getLimit();
        System.out.println("Tile limit: " + l);
        work();
    }

    public boolean addLife()
    {
        if(this.life+1 <= 4)
        {
            this.life++;
            return true;
        }
        return false;
    }

}