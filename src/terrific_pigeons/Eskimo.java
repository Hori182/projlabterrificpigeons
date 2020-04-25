package terrific_pigeons;

import java.util.ArrayList;

public class Eskimo extends Player {
    public Eskimo(String id)
    {
        life=5;
        moveableid = id;
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
