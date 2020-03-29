package terrific_pigeons;

/*
 * A Food osztály az étely osztálya a játékban.
 */
public class Food extends Thing{

    public Food()
    {
        System.out.println("Food f created");
    }
    /*
     * Növeli a karakter életerejét eggyel.
     */
    public void useThing() {
        System.out.println("-> useThing() -> f");
        owner.addLife();
        owner.removeThing(this);
        owner.work();
        System.out.println("f <- e");
        System.out.println("<- f");
    }
}

