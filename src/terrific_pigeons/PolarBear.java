package terrific_pigeons;

public class PolarBear extends MoveAble {
    /*Ez a függvény felel a PolarBear egyik tile-ról
     * a másikra való mozgatásáért.
     * @param t A jégtábla amire a medve lép.
     */
    public PolarBear()
    {
        life=99999999;
    }
    public void move(Tile t){
        
    }

    public void draw(){
        System.out.print("P");
    }
    /*
    A medve megöli a játékos(oka)t.
     */
    public void kill(Game g){
        g.setDie(true);
    }
}
