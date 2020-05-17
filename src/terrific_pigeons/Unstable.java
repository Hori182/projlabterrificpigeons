package terrific_pigeons;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class  Unstable extends Tile{
    /*Unstable konstruktora.*/
    public Unstable(int limit, int tileId)
    {
        super(tileId);
        this.limit = limit;
    }

    /*
    *Ha túl sokan állnak egy tile-on akkor hívodik meg. A rajta állók beleesnek a vízbe.
    * */
    public void turnOver()
    {
        for(int i = 0; i < this.movables.size(); i++)
        {
            this.getMovables().get(i).setInWater(true);
        }

        System.out.println("Tile " + getTileId() + " turned over");
    }

    /*
    * Ellenőrzi, hogy nem állnak e többen a tile-on mint amennyi a limit.
    * Ha igen, akkor átfordul.
    * */
    public void check()
    {
        if(this.movables.size() > limit){
            this.turnOver();
        }
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
            if(t2.getNeighbours().get(i).getTileId() == this.getTileId())
                isNeighbour = true;
        }
        if(isNeighbour){
            t2.remove(m);
            m.setMyTile(this);
            this.receive(m);
            this.check();
        }
        else {
            System.out.println("Not neighbour!");
        }
    }
    /*Kiírja a tile tartalmát fileba.*/
    public void save(PrintWriter writer){
        writer.print(getTileId()+":-:"+limit+":"+(safe?"I:":(safeByTent?"T:":"-:"))+snow+":");
        ArrayList<String> ids = new ArrayList<>();
        for (MoveAble m : movables)
            ids.add(m.getId());
        if (ids.size()>0)
            writer.print(String.join(",", ids) + ":");
        else
            writer.print("-:");
        writer.print(thing==null?"-:":(thing.Name()+":"));
        ids = new ArrayList<>();
        for (Tile t: neighbours)
            ids.add(String.valueOf(t.getTileId()));
        if (ids.size()>0)
            writer.println(String.join(",", ids) + ":");
        else
            writer.println("-:");
    }
}
