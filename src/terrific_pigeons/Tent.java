package terrific_pigeons;

public class Tent extends Thing{

    /*A tárgyat valamelyik játékos ennek a függvénynek a meghívásával használja.
    *A sátor a játékos mezőjét egy körig védetté teszi, a safe és safeByTent
    *változók igazra állításával.
    */
    public void useThing() {
        owner.getTile().setSafeByTent(true);
        owner.removeThing(this);
        owner.work();
    }
    /*Visszaadja a Tent nevét.*/
    public String Name(){
        return "T";
    }
}
