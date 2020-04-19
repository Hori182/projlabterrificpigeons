package terrific_pigeons;

public class FragileShovel extends Shovel{
    // Hányszor volt használva a lapát.
    private int usage;

    /*
    * A tárgyat valamelyik játékos ennek a függvénynek a meghívásával használja.
    * Amennyiben van még hó a játékos mezőjén és nem használtuk el a 3 lehetőségünket,
    * a függvény 2 egység havat távolít el róla és megnöveli a használatot eggyel.
     */
    public void useThing() {

    }


    /*
    *Visszaadja a usage változó értékét.
    * */
    public int getUsage() {
        return usage;
    }

    /*Beállítja a usage változó értékét.
    * @param usage: Ez a usage változó új értéke.
    */
    public void setUsage(int usage) {
        this.usage = usage;
    }
}
