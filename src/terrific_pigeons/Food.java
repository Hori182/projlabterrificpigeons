package terrific_pigeons;

/*
 * A Food osztály az étely osztálya a játékban.
 */
public class Food extends Thing{

    private boolean used = false;
    /*
     * Növeli a karakter életerejét eggyel.
     */
    public void useThing()
    {
        used = owner.addLife();
        if(used)
        {
            owner.removeThing(this);
            owner.work();
        }
        else System.out.println("Your health is at max!");
    }
    public String Name(){
        return "F";
    }
}

