package terrific_pigeons;

public class FragileShovel extends Shovel{
    // Hányszor volt használva a lapát.
    private int usage;

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
