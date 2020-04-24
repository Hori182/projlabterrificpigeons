package terrific_pigeons;

public class Researcher extends Player {

    public Researcher(String id)
    {
        life=4;
        playerid = id;
    }

    /*
     * Megnézi, hogy az adott táblának mennyi a teherbírása.
     * @param t A megviszgált jégtábla.
     * */
    public int look(Tile t)
    {
        int l = t.getLimit();
        /*System.out.println("Tile limit: " + l);*/
        work();
        return l;
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