package terrific_pigeons;

import java.io.PrintWriter;
import java.util.Random;

public class PolarBear extends MoveAble {
    public PolarBear(String id)
    {
        moveableid = id;
        life=99999999;
    }

    /*Ez a függvény felel a PolarBear egyik tile-ról
     * egy random másikra való mozgatásáért.
     * @param t A jégtábla amire a medve lép.
     */
    public void moveToRandom(){
        Random r = new Random();
        int randomNeighbor = r.nextInt(myTile.getNeighbours().size());
        move(myTile.getNeighbours().get(randomNeighbor));
    }

    /*Ez a függvény felel a PolarBear egyik tile-ról
     * egy másikra való mozgatásáért.
     * @param t A jégtábla amire a medve lép.
     */
    public void move(Tile t){
        t.moveRequest(this);
        kill();
        pass();
    }

    public void draw(){
        System.out.print(moveableid + ":" + myTile.getTileId() + ";");
    }
    /*
    A medve megöli a játékos(oka)t.
     */
    public void kill(){
        for (MoveAble m :myTile.getMovables()){
            m.attacked();
        }
    }

    public void save(PrintWriter writer){
        writer.println(getId()+":"+getTile().getTileId());
    }
}
