package terrific_pigeons;

import java.util.ArrayList;
import java.util.Random;

public class Game {
    private Map gameMap = new Map();

    ArrayList<Player> players = new ArrayList<>();
    private int currentPlayer = 0;
    private boolean win = false;
    private boolean die = false;
    private int usedParts;

    public Game() {
        System.out.println("Game created");

    }

    /*
    * Játék indítása.
    * */
    public void startGame(){}

    /*
    * Követkető játékosra vált.
    * */
    public void nextPlayer(){
        if(players.get(currentPlayer).getWork() == 0 && players.get(currentPlayer).getInWater())
        {
            players.get(currentPlayer).die();
        }
        if(currentPlayer < players.size())
            currentPlayer++;
        else
            currentPlayer = 0;
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
    public void endGame(){
        System.out.println("Game Over!");
    }

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

    public void setDie(boolean die) {
        this.die = die;
        endGame();
    }

    public void create_tiles(int x, int y){
        Random rand = new Random();

        gameMap.generateMap(x, y);
        int datas[] = gameMap.getMap_data();

        for(int i = 0; i < datas[0]; i++){
            Tile t = new Tile(i);
            gameMap.addTile(t);
        }
        for(int i = 0; i < datas[1]; i++){
            Tile t = new Unstable(rand.nextInt(players.size()-1),i + datas[0]);
            gameMap.addTile(t);
        }
        for(int i = 0; i < datas[2]; i++){
            Tile t = new Unstable(0, i + datas[0] + datas[1]);
            gameMap.addTile(t);
        }
        gameMap.shuffle();
    }

    public void initNeighbours(){
        int cnt = 0;

        // Set the neighbours
        for(int i = 0; i < gameMap.getL(); i++){
            for(int j = 0; j < gameMap.getW(); j++){
                if(i == 0){
                    if(j > 0){
                        gameMap.getTiles().get(cnt).addNeighbour(gameMap.getTiles().get(cnt-1));
                    }
                } else if(i > 0){
                    if(j == 0){
                        gameMap.getTiles().get(cnt).addNeighbour(gameMap.getTiles().get(cnt-gameMap.getW()));
                        gameMap.getTiles().get(cnt).addNeighbour(gameMap.getTiles().get(cnt-gameMap.getW()+1));
                    }
                    if(j != 0 && j < gameMap.getW() - 1){
                        gameMap.getTiles().get(cnt).addNeighbour(gameMap.getTiles().get(cnt-1));
                        gameMap.getTiles().get(cnt).addNeighbour(gameMap.getTiles().get(cnt-gameMap.getW()-1));
                        gameMap.getTiles().get(cnt).addNeighbour(gameMap.getTiles().get(cnt-gameMap.getW()));
                        gameMap.getTiles().get(cnt).addNeighbour(gameMap.getTiles().get(cnt-gameMap.getW()+1));
                    } else if(j == gameMap.getW() - 1){
                        gameMap.getTiles().get(cnt).addNeighbour(gameMap.getTiles().get(cnt-1));
                        gameMap.getTiles().get(cnt).addNeighbour(gameMap.getTiles().get(cnt-gameMap.getW()-1));
                        gameMap.getTiles().get(cnt).addNeighbour(gameMap.getTiles().get(cnt-gameMap.getW()));
                    }
                }
                cnt++;
            }
        }
    }

}
