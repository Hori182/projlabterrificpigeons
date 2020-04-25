package terrific_pigeons;

/*
 * A Shovel osztály a lapát tárgy osztálya a játékban.
 * Ennek a tárgynak a segítségével 2 egységnyi havat tud eltávolítani a játékos.
 */
public class Shovel extends Thing{

    /*
     * Két egységnyi havat lapátol el a játékos a saját
     * jégtáblájáról.
     */
    protected void useThing() {
        Tile t = owner.getTile();
        if(t.getSnow()-2 >= 0)
        {
            t.subSnow(2);
        }
        else
        {
            t.setSnow(0);
        }
        owner.work();
    }

    public void draw(){
        System.out.print("Sh");
    }
}
