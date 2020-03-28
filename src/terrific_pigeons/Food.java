package terrific_pigeons;

/*
 * A Food osztály az étely osztálya a játékban.
 */
public class Food extends Thing{
    /*
     * Növeli a karakter életerejét eggyel.
     */
    public void useThing() {
        System.out.println("-> useThing() -> f");
        System.out.println("f -> addLife() -> e");
        System.out.println("f <- e");
        System.out.println("f -> removeThing(f) -> e");
        System.out.println("e -> work() -> e");
        owner.work();
        System.out.println("f <- e");
        System.out.println("<- f");
    }
}

