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
        for(int i = 0; i < this.movables.size(); i++)
        {
            this.getMovables().get(i).setInWater(true);
        }
        m.pass();
    }

    /*
    * Ellenőrzi, hogy nem állnak e többen a tile-on mint amennyi a limit.
    * Ha igen, akkor átfordul.
    * */
    public void check(MoveAble m)
    {
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
        Tile t2 = m.getTile();
        t2.remove(m);
        m.setMyTile(this);
        this.receive(m);
        this.check(m);
    }
}
