package terrific_pigeons;

/*
 * A Shovel osztály a lapát tárgy osztálya a játékban.
 * Ennek a tárgynak a segítségével 2 egységnyi havat tud eltávolítani a játékos.
 */
public class Shovel extends Thing{

    public Shovel(){System.out.println("Shovel created");}
    /*
     * Két egységnyi havat lapátol el a játékos a saját
     * jégtáblájáról.
     */
    public void useThing() {
        System.out.println("-> useThing() -> s");
        Tile t = owner.getTile();
        t.subSnow(2);
        owner.work();
        System.out.println("s <- e");
        System.out.println("<- s");
    }
}
