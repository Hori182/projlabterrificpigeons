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
    public String look(Tile t)
    {
        int l = t.getLimit();
        String out;
        work();
        if(l > 0) {
            System.out.println("TileID: " + t.getTileId() + "'s limit is " + l);
            out = "limit "+l;
        }
        else if(l == 0){
            System.out.println("TileID: " + t.getTileId() + " is water!");
            out = "limit "+l;
        }
        else{
            System.out.println("TileID: " + t.getTileId() + "'s limit is unlimited");
            out = "unlimited";
        }
        return (t.getTileId()+": "+out);
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