package terrific_pigeons;

public class Eskimo extends Player {

    private int life = 5;

    public Eskimo()
    {
        super();
        System.out.println("Eskimo created");
    }

    public void equip()
    {
        System.out.println("-> equip() -> e");
        System.out.println("e -> getThing() -> t");
        Thing shovel = myTile.getThing();
        System.out.println("e -> setOwner(e) -> shovel");
        shovel.setOwner(this);
        System.out.println("e -> addThing(shovel) -> e");
        this.addThing(shovel);
        System.out.println("e -> removeThing() -> t");
        myTile.removeThing();
        System.out.println("e -> work() -> e");
        this.work();
        System.out.println("e <- e");
        System.out.println("<- e");
    }
    /*
     * Iglut épít a saját jégtáblájára, ezzel biztonságossá
     * teszi a mezőt.
     * */
    public void build()
    {
        System.out.println("-> build() -> e");
        System.out.println("e -> setSafe(true) -> t");
        myTile.setSafe(true);
        System.out.println("e -> work() -> e");
        work();
        System.out.println("<- e");
    }



    public void work()
    {
        System.out.println("e -> setWork(work+1) -> e");
        this.setWork(work+1);
        System.out.println("e <- e");
    }
    /*
     * Visszaadja, hogy jelenleg hány élete van.
     */
    public int getLife() {
        return life;
    }

    /*
     * Beállítja a life attribútum új értékét.
     * @param life: élet - a life új értéke
     */
    public void setLife(int life) {
        this.life = life;
    }
}
