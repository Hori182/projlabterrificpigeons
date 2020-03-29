package terrific_pigeons;

import java.util.Scanner;

public class Unstable extends Tile{

    private int limit = 0;

    public Unstable()
    {
        System.out.println("Unstabile tile created"+limit);
    }

    /*
    *Ha túl sokan állnak egy tile-on akkor hívodik meg. A rajta állók beleesnek a vízbe.
    * */
    public void turnOver()
    {
        System.out.println(this.getName() + " -> trunOver() ->" + this.getName());
        //this.getPlayers().get(0).setInWater(true);
        //this.getPlayers().get(0).pass();
        System.out.println(this.getName() + " <- " + this.getName());
    }

    /*
    * Ellenőrzi, hogy nem állnak e többen a tile-on mint amennyi a limit.
    * Ha igen, akkor átfordul.
    * */
    public void check()
    {
        System.out.println("Többen állnak rajta mint kéne (1: igen, 0: nem )");
        System.out.print("Please choose: ");
        Scanner input = new Scanner( System.in );
        int choice = input.nextInt();
        if(choice == 1) {
            System.out.println("t1 -> turnOver() -> t1");
            this.turnOver();
        }
    }

    /*
    * Visszaadja a limit változó értékét.
    * */
    public int getLimit() {
        return limit;
    }
    /*
    * A limit változó értékét megváltoztatja.
    * @param limit : Felülírja a limit változó értékét.
    * */
    public void setLimit(int limit) {
        this.limit = limit;
    }
    /*
     * Játékos átmozgatása.
     * @param p: Az átléptetendő játékos
     * */
    public void moveRequest(Player p)
    {
        Tile t2 = p.getTile();

        t2.remove(p);

        p.setMyTile(this);

        System.out.println("t1 -> Receive(e) -> t1");
        System.out.println("t1 -> check(e) -> t1");
        this.check();
        System.out.println("e <- t1");
    }
}
