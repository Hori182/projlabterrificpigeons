package terrific_pigeons;

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
        if (this.owner.getInWater()==true)
        {
            this.owner.getTile();
        }
    }
}

//RANDOM KOMMENT BY ARNOLD