package terrific_pigeons;

public class Eskimo extends Player {

    private int life = 5;

    public Eskimo()
    {
        System.out.println("Eskimo e created");
    }

    /*
     * Iglut épít a saját jégtáblájára, ezzel biztonságossá
     * teszi a mezőt.
     * */
    public void build()
    {
        System.out.println("-> build() -> e");
        System.out.println("e -> setSafe(true) -> t1");
        myTile.setSafe(true);
        System.out.println("e <- t1");
        System.out.println("e -> work() -> e");
        work();
        System.out.println("<- e");
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
