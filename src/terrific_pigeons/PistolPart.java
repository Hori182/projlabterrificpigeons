package terrific_pigeons;

/*
 * A PistolPart a pisztoly részeit reprezentáló osztály a játékban.
 * Ezek összeszerelésével lehet megnyerni a játékot.
 */
public class PistolPart extends Thing{
    private int usedParts;
    /*
    *Visszaadja a usedParts értékét.
    */
    public int getUsedParts() {return usedParts;}

    /*
    *A usedParts változónak új értéket állít be.
    * @param i: Ez lesz a usedParts új értéke.
    */
    public void setUsedParts(int i) {this.usedParts = i;}
    /*
     * Összeszerli a pisztolyt a pisztolydarabokból.
     */
    public void UseThing() {}
}
