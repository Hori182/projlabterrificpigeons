package terrific_pigeons;

import java.util.Random;
import java.util.Scanner;

public class  Unstable extends Tile{

    private int limit = 0;

    public Unstable()
    {
        System.out.println("Unstabile tile created"+limit);
    }

    /*
    *Ha túl sokan állnak egy tile-on akkor hívodik meg. A rajta állók beleesnek a vízbe.
    * */
    public void turnOver(MoveAble m)
    {
        //System.out.println(this.getName() + " -> turnOver() ->" + this.getName());
        for(int i = 0; i < this.movables.size(); i++)
        {
            this.getMovables().get(i).setInWater(true);
        }
        m.pass();
        //System.out.print(this.getName());
        //this.getMovables().get(0).pass();
        //System.out.println(this.getName() + " <- " + this.getName());
    }

    /*
    * Ellenőrzi, hogy nem állnak e többen a tile-on mint amennyi a limit.
    * Ha igen, akkor átfordul.
    * */
    public void check(MoveAble m)
    {
        /*System.out.println("Többen állnak rajta mint kéne (1: igen, 0: nem )");
        System.out.print("Please choose: ");
        Scanner input = new Scanner( System.in );
        int choice = input.nextInt();
        if(choice == 1) {
            this.turnOver();
        }*/

        if(this.movables.size() >limit)
            this.turnOver(m);

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
    public void moveRequest(MoveAble m)
    {
        System.out.println("e -> moveRequest(e) -> " + this.getName());
        Tile t2 = m.getTile();
        //System.out.println("lol");

        t2.remove(m);

        m.setMyTile(this);

        //System.out.println("t1 -> Receive(e) -> t1");
        this.receive(m);

        System.out.println("t1 -> check(e) -> t1");
        this.check(m);
        System.out.println("e <- t1");
    }
}
