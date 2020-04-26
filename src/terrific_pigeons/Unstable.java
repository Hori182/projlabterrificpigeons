package terrific_pigeons;

import java.util.Random;
import java.util.Scanner;

public class  Unstable extends Tile{

    public Unstable(int limit, int tileId)
    {
        super(tileId);
        this.limit = limit;
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
        System.out.println("Tile " + getTileId() + "turned over");
    }

    /*
    * Ellenőrzi, hogy nem állnak e többen a tile-on mint amennyi a limit.
    * Ha igen, akkor átfordul.
    * */
    public void check(MoveAble m)
    {
        if(this.movables.size() > limit)
            this.turnOver(m);
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
        boolean isNeighbour = false;
        for(int i = 0; i < t2.getNeighbours().size(); i++) {
            if(this == t2.getNeighbours().get(i))
                isNeighbour = true;
        }
        if(isNeighbour){
            t2.remove(m);
            m.setMyTile(this);
            //this.receive(m);
            this.check(m);
        }
        else {
            System.out.println("Not neighbour!");
        }
    }
}
