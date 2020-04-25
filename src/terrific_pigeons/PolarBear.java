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
        kill();
        pass();
    }

    public void draw(){
        System.out.println("Polar bear is at " + myTile.getTileId());
    }
    /*
    A medve megöli a játékos(oka)t.
     */
    public void kill(){
        for (int i=0; i<myTile.getMovables().size()-1; i++){
            //ezt mindjárt javítom, Arnold
            //myTile.getMovables().get(i).attacked();
        }
    }
}
