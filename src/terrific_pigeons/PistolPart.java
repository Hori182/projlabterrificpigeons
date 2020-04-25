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
    public void UseThing(Game g) {
        g.assemble();
        g.assemble();
    }

    public String Name(){
        return "P";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
