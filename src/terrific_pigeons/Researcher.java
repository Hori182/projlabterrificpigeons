package terrific_pigeons;

public class Researcher extends Player {

    public Researcher()
    {
        life=4;
        System.out.println("Researcher created");
    }

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
        System.out.println("-> look(" + t.getName() + ") -> r");
        t.getLimit();
        System.out.println("<- limit <- r");
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