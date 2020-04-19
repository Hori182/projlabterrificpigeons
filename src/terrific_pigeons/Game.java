package terrific_pigeons;

import java.util.ArrayList;

public class Game {
    private Map gameMap;

    ArrayList<Player> players = new ArrayList<>();
    private boolean win = false;
    private boolean die = false;
    private int usedParts;

    public Game() { System.out.println("Game created"); }

    /*
    * Játék indítása.
    * */
    public void startGame(){}

    /*
    * Követkető játékosra vált.
    * */
    public void nextPlayer(){
        System.out.println("nextPlayer() -> g");
        players.get(0).getWork();
        players.get(0).getInWater();
        System.out.print("g->");
        players.get(0).die();
        System.out.println("<- e");
        System.out.println("->e");
        System.out.println("<- g");
    }

    /*
    * Pistoly összeszerelése.
    * */
    public void assemble(PistolPart pp1,PistolPart pp2,PistolPart pp3){
        System.out.println("pp1 -> assemble(pp1, pp2, pp3) -> g");
        setUsedParts(usedParts+1);
        pp1.owner.getTile();
        pp2.owner.getTile();
        pp3.owner.getTile();
        setWin(true);
        setUsedParts(0);
        System.out.println("pp1 <- g");
    }

    /*
    * Játék vége.
    * */
    public void endGame(){}

    public ArrayList<Player> getPlayers() {
        return players;
    }


    /*
    * A players ArrayList-het új elemet ad.
    * @param p: Ezt adja az Arraylisthez.
    * */
    public void addPlayers(Player p) {
        players.add(p);
    }
    /*
    * Beállítja a win változó új értékét.
    * @param win: win változó új értéke.
    * */
    public void setWin(boolean win) {
        System.out.println("g -> setWin(true) -> g");
        System.out.println("g <- g");
        this.win = win;
    }

    /*
     *Visszaadja a usedParts értékét.
     */
    public int getUsedParts() {
        System.out.println("g -> getUsedParts() -> g");
        System.out.println("g <- g ");
        return usedParts;}

    /*
     *A usedParts változónak új értéket állít be.
     * @param i: Ez lesz a usedParts új értéke.
     */
    public void setUsedParts(int i) {
        System.out.println("g -> setUsedParts() -> g");
        System.out.println("g <- g ");
        this.usedParts = i;
    }
}
