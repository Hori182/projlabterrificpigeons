package terrific_pigeons;

public class Eskimo extends Player {

    public Eskimo()
    {
        life=5;
        System.out.println("Eskimo created");
    }

    /*
     * Iglut épít a saját jégtáblájára, ezzel biztonságossá
     * teszi a mezőt.
     * */
    public void build()
    {
        myTile.setSafe(true);
        work();
    }

    public boolean addLife()
    {
        if(this.life+1 <= 5)
        {
            this.life++;
            return true;
        }
        return false;
    }

}
