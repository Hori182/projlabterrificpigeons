package terrific_pigeons;

public class Researcher extends Player {

    public Researcher(String id)
    {
        life=4;
        moveableid = id;
    }

    /*
     * Megnézi, hogy az adott táblának mennyi a teherbírása.
     * @param t A megviszgált jégtábla.
     * */
    public void look(Tile t)
    {
        int l = t.getLimit();
        work();
        if(l > 0) System.out.println("TileID: " + t.getTileId() + "'s limit is " + l);
        else if(l == 0) System.out.println("TileID: " + t.getTileId() + " is water!");
        else System.out.println("TileID: " + t.getTileId() + "'s limit is unlimited");
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