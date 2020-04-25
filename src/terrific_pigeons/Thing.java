package terrific_pigeons;
/*
 * A Thing osztály a játékos által felvehető tárgyak ősosztálya.
 */
public class Thing {
    /*
     * Minden tárgynak van egy tulajdonosa.
     */
    protected Player owner;
    protected String Name;

    String getName(){ return Name; }

    String Name(){return "";}
    void setName(String s) { this.Name = s; }
    //csak a skeletonhoz hoztuk létre ezt a változót:

    /*
     * Minden tárgyat lehet használni. Attól függően, hogy melyik tárgyról van szó a
     * függvény viselkedése változik.
     */
    protected void useThing() {}

    /*
     * Beállítja a tárgy tulajdonosát.
     */
    protected void setOwner(Player p)
    {
        /*System.out.println("e-> setOwner(e) -> " + this.getName());
        System.out.println("e <- " + this.getName());*/
        this.owner = p;
        p.getThings().add(this);
    }

    protected void draw() {}
    /*
    * Visszaadja az owner változó értékét.
    * */
    protected Player getOwner(){
        return owner;
    }
}
