package terrific_pigeons;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game {
    private Map gameMap = new Map();

    ArrayList<Player> players = new ArrayList<>();
    ArrayList<PolarBear> polarbears = new ArrayList<>();
    private int currentPlayer = 0;
    private boolean win = false;
    private boolean die = false;
    private int usedParts;
    ArrayList<PistolPart> pistolParts = new ArrayList<>(3);

    public Game() {}

    /*
    * Játék indítása.
    * */
    public void startGame(){
        Scanner input = new Scanner(System.in);
        System.out.println("Number of eskimos: ");
        int numOfEskimos = Integer.parseInt(input.nextLine());
        System.out.println("Number of researchers: ");
        int numOfResearchers = Integer.parseInt(input.nextLine());

        for(int i = 0; i < numOfEskimos; i++) {
            Integer.toString(i);
            String id = "E"+i;
            Eskimo e = new Eskimo(id);
            this.addPlayers(e);
        }
        for(int i = 0; i < numOfResearchers; i++) {
            Integer.toString(i);
            String id = "R"+i;
            Researcher r = new Researcher(id);
            this.addPlayers(r);
        }

        this.initMap(20);
        this.getPlayers().get(currentPlayer).setWork(0);
        this.draw();
    }

    /*
    * Követkető játékosra vált.
    * */
    public void nextPlayer(){
        Random rand = new Random();
        rand.nextInt(10);
        if( rand.equals(3))
            gameMap.snowStorm();
        if((players.get(currentPlayer).getWork() >=4 && players.get(currentPlayer).getInWater()) || players.get(currentPlayer).getLife() == 0 )
        {
            players.get(currentPlayer).die();
            this.endGame();
        }
        if(currentPlayer < players.size()-1)
            currentPlayer++;
        else
            currentPlayer = 0;
        getPlayers().get(currentPlayer).setWork(0);
    }

    /*
    * Pistoly összeszerelése.
    * */
    public void assemble() {
        setUsedParts(usedParts + 1);
        if (usedParts == 3) {
            int id1 = pistolParts.get(1).owner.getTile().getTileId();
            int id2 = pistolParts.get(2).owner.getTile().getTileId();
            int id3 = pistolParts.get(3).owner.getTile().getTileId();
            if (id1 == id2 && id1 == id3) {
                setWin(true);
            }
            setUsedParts(0);
        }
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
        this.win = win;
        System.out.println("You won!");
    }

    public boolean getWin()
    {
        return win;
    }
    /*
     *Visszaadja a usedParts értékét.
     */
    public int getUsedParts() {
        return usedParts;}

    /*
     *A usedParts változónak új értéket állít be.
     * @param i: Ez lesz a usedParts új értéke.
     */
    public void setUsedParts(int i) {
        this.usedParts = i;
    }

    public void setDie(boolean die) {
        this.die = die;
        endGame();
    }

    public void create_tiles(int tiles){
        Random rand = new Random();

        gameMap.generateMap(tiles);

        int[] datas = gameMap.getMap_data();

        for(int i = 0; i < datas[0]; i++){
            Tile t = new Tile(i);
            t.setSnow(rand.nextInt(5));
            gameMap.addTile(t);
        }
        for(int i = 0; i < datas[1]; i++){
            Tile t = new Unstable(rand.nextInt(players.size()),i + datas[0]);
            t.setSnow(rand.nextInt(5));
            gameMap.addTile(t);
        }
        for(int i = 0; i < datas[2]; i++){
            Tile t = new Unstable(0, i + datas[0] + datas[1]);
            gameMap.addTile(t);
        }
        gameMap.shuffle();
    }

    public void initNeighbours(){
        Random rand = new Random();
        int num = 0;
        int neighbours;

        // Set at least one neighbour
        for(int i = 0; i < gameMap.getNum_of_tiles(); i++){
            num = gameMap.getTiles().get(i).getTileId();
            while (num == gameMap.getTiles().get(i).getTileId()){
                num = rand.nextInt(gameMap.getTiles().size());
                if (num != gameMap.getTiles().get(i).getTileId()){
                    gameMap.getTiles().get(i).addNeighbour(gameMap.getTiles().get(num));
                }
            }
        }

        //Set plus neighbours
        for(int i = 0; i < gameMap.getNum_of_tiles(); i++){
            if(i != gameMap.getTiles().get(i).getTileId()){
                num = rand.nextInt(10);
                if(num <= 2){
                    gameMap.getTiles().get(i).addNeighbour(gameMap.getTiles().get(num));
                }
            }
        }
    }

    public void initMap(int tiles){
        Random rand = new Random();
        int num =  0, rand_thing;
        boolean ready = false;

        create_tiles(tiles);
        initNeighbours();

        // Pistolparts
        for (int i = 0; i < 3; i++){
            while (gameMap.getTiles().get(num).getLimit() != 0 && gameMap.getTiles().get(num).getThing() != null)
                num = rand.nextInt(gameMap.getTiles().size());

            PistolPart p = new PistolPart();
            pistolParts.add(p);
            pistolParts.get(i).setId(i);
            gameMap.getTiles().get(num).setThing(pistolParts.get(i));
            num = 0;
        }

        // Generate things
        int things_pct = (int)(tiles * 0.2);
        for (int i = 0; i < things_pct; i++){
            while (gameMap.getTiles().get(num).getLimit() != 0 && gameMap.getTiles().get(num).getThing() != null)
                num = rand.nextInt(gameMap.getTiles().size());

            rand_thing = rand.nextInt(6);
            gameMap.getTiles().get(num).setThing(rand_thing);
            num = 0;
        }

        // Put the characters on it
        for (int i = 0; i < players.size(); i++) {
            while (ready != true) {
                num = rand.nextInt(gameMap.getTiles().size());
                if(gameMap.getTiles().get(num).getLimit() == -1){
                    players.get(i).setMyTile(gameMap.getTiles().get(num));
                    ready = true;
                }
            }
            ready = false;
        }

        PolarBear p = new PolarBear("P0");
        polarbears.add(p);
        while (ready != true) {
            num = rand.nextInt(gameMap.getTiles().size());
            if (gameMap.getTiles().get(num).getLimit() == -1 && gameMap.getTiles().get(num).getMovables().size() == 0) {
                p.setMyTile(gameMap.getTiles().get(num));
                ready = true;
            }
        }
    }

    public void draw() {
        /*
        int cnt = 0;

        for (int i = 0; i < gameMap.getL(); i++) {
            for (int j = 0; j < gameMap.getW(); j++) {
                System.out.print("| ");
                gameMap.getTiles().get(cnt).draw();
                System.out.print(" |");
                cnt++;
            }
            System.out.print('\n');
        }
        for(int i = 0; i < gameMap.getTiles().size(); i++)
        {
           if(gameMap.getTiles().get(i).getMovables().size() != 0)
           {
               for (int j = 0; j < gameMap.getTiles().get(i).getMovables().size(); j++)
               {
                   gameMap.getTiles().get(i).getMovables().get(j).draw();
               }
           }
        }
        System.out.println("Current player: "+players.get(currentPlayer).getId());

        */
        System.out.println("Tiles\n" + "TileID:safe:snow:standing on:neighbours;\n");
        for (Tile t : gameMap.getTiles()){
            t.draw();
        }
        System.out.println("\nPlayers\n" + "PlayerID:your Tile:life:work left:in water:your Things;\n");
        for (Player p : players){
            p.draw();
        }
        System.out.println("\nPolarBears");
        for (PolarBear p : polarbears){
            p.draw();
        }
        System.out.println("\nCurrent player: " + players.get(currentPlayer).getId());
        System.out.println("");
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }
    public Map getGameMap() {
        return gameMap;
    }
}
