package terrific_pigeons;

public class Eskimo extends Player {

    public Eskimo()
    {
        life=5;
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

        System.out.println("e <- e");

    }

}
