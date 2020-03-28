package terrific_pigeons;

/*
 * A Food osztály az étely osztálya a játékban.
 */
public class Food extends Thing{
    /*
     * Növeli a karakter életerejét eggyel.
     */
    public void UseThing() {
        System.out.println("f -> addLife() -> e");
        System.out.println("f <- e");
        System.out.println("f -> removeThing(f) -> e");
        owner.work();
    }
}

