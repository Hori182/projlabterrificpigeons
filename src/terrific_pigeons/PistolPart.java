package terrific_pigeons;

/*
 * A PistolPart a pisztoly részeit reprezentáló osztály a játékban.
 * Ezek összeszerelésével lehet megnyerni a játékot.
 */
public class PistolPart extends Thing{
    private int id;
    /*
     * Összeszerli a pisztolyt a pisztolydarabokból.
     */

    /*
    Megpróbálja összeszerelni a pisztolyt.
     */
    public void UseThing() {
        Game g = new Game();
        PistolPart pp2 = new PistolPart();
        PistolPart pp3 = new PistolPart();

        System.out.println(" ");
        System.out.println("UseThing -> pp1");
        pp2.owner = this.owner;                   // Azt a legegyszerübb esetet nézzük, amikor egy játékosnál van az összes pisztoly darab
        pp3.owner = this.owner;
        this.owner.myTile.setName("t1");
        pp2.owner.myTile.setName("t1");
        pp3.owner.myTile.setName("t1");

        g.assemble(this, pp2, pp3);
        System.out.println("<- pp1");
    }

    public void draw(){
        System.out.print(getId()+"P");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
