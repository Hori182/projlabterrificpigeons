package terrific_pigeons;

public class Eskimo extends Player {

    private int life = 5;

    public Eskimo()
    {
        super();
        System.out.println("Eskimo created");
    }

    /*
     * Iglut épít a saját jégtáblájára, ezzel biztonságossá
     * teszi a mezőt.
     * */
    public void build()
    {
        System.out.println("-> build() -> e");
        myTile.setSafe(true);
        work();
        System.out.println("<- e");
    }



    public void work()
    {
        System.out.println("e -> work() -> e");
        this.setWork(work+1);

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
