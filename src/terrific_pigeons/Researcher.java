package terrific_pigeons;

public class Researcher extends Player {
    /*Resercher konstuktora.*/
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
    /*
     * Visszaadja, hogy tud e növelni az életén az eszkimónak, vagy már maxon van neki.
     * */
    public boolean addLife()
    {
        if(this.life+1 <= 4)
        {
            this.life++;
            return true;
        }
        return false;
    }

    @Override
    public void SetX(int x) {

    }

    @Override
    public void SetY(int y) {

    }
}