package terrific_pigeons;

import java.util.Random;

public class PolarBear extends MoveAble {
    public PolarBear()
    {
        life=99999999;
    }

    /*Ez a függvény felel a PolarBear egyik tile-ról
     * egy random másikra való mozgatásáért.
     * @param t A jégtábla amire a medve lép.
     */
    public void move(){
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
    }

    public void draw(){
        System.out.println("Polar bear is at " + myTile.getTileId());
    }
    /*
    A medve megöli a játékos(oka)t.
     */
    public void kill(Game g){
        g.setDie(true);
    }
}
