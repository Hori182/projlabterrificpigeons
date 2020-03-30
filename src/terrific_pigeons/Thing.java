package terrific_pigeons;
/*
 * A Thing osztály a játékos által felvehető tárgyak ősosztálya.
 */
public class Thing {
    /*
     * Minden tárgynak van egy tulajdonosa.
     */
    protected Player owner;
    //csak a skeletonhoz hoztuk létre ezt a változót:
    private String name;

    /*
     * Minden tárgyat lehet használni. Attól függően, hogy melyik tárgyról van szó a
     * függvény viselkedése változik.
     */
    protected void useThing() {}

    protected void setOwner(Player p)
    {
        System.out.println("e-> setOwner(e) -> shovel");
        System.out.println("e <- shovel");
        this.owner = p;
    }
}
