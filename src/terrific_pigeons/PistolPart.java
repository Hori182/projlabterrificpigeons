package terrific_pigeons;

/*
 * A PistolPart a pisztoly részeit reprezentáló osztály a játékban.
 * Ezek összeszerelésével lehet megnyerni a játékot.
 */
public class PistolPart extends Thing{
    private int usedParts;

    public int getUsedParts() {return usedParts;}
    public void setUsedParts(int i) {this.usedParts = i;}
    /*
     * Összeszerli a pisztolyt a pisztolydarabokból.
     */
    public void UseThing() {}
}
