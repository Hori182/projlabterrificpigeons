package terrific_pigeons;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/*
 * A DivingSuit osztály a búvárruha osztálya a játékban.
 */
public class DivingSuit extends Thing{
    /*
     * Vízbe esés esetén életben tartja a játékost,
     * amíg ki nem mentik.
     */
    public void useThing()
    {
        if (this.owner.getInWater())
        {
            ArrayList<Tile> temp = this.owner.getTile().getNeighbours();
            int randomNum = ThreadLocalRandom.current().nextInt(0, temp.size() );
            temp.get(randomNum).moveRequest(this.owner);
            this.owner.work();
        }
    }
    public String Name(){
        return "D";
    }
}
