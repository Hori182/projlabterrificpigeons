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
    }
    /*Visszaadja a PistolPart nevét.*/
    public String Name(){
        return "P";
    }
    /*Visszaadja a PistolPart Id-ját.*/
    public int getId() {
        return id;
    }
    /*Beállítja a PistolPart id-ját.*/
    public void setId(int id) {
        this.id = id;
    }
}
