package terrific_pigeons;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Game {
    private JFrame jf = new JFrame();
    private Map gameMap = new Map();
    ArrayList<Player> players = new ArrayList<>();
    ArrayList<PolarBear> polarbears = new ArrayList<>();
    private int currentPlayer = 0;
    private boolean win = false;
    private boolean die = false;
    private int usedParts;
    ArrayList<PistolPart> pistolParts = new ArrayList<>(3);


    /*
    Konstruktor
     */
    public Game() {}

    /*
    Beállítja a játék pályáját
    @param m: a beállítandó térkép/pálya
     */
    public void setGameMap(Map m)
    {
       gameMap = m;
    }
    /*
    Hozzáad a jegesmedvék listájához egy újabbat
    @param p: a hozzáadandó jegesmedve
     */
    public void addPolarBear(PolarBear p)
    {
        polarbears.add(p);
    }
    /*
    * Játék indítása.
    * */
    public void startGame(){
        this.initMap2();
    }

    /*
    * Követkető játékosra vált.
    * */
    public void nextPlayer(){
        /*for(int i = 0; i < polarbears.size();i++) {
            polarbears.get(i).moveToRandom();
        }*/
        Random rand = new Random();
        int r = rand.nextInt(10);
        if( r==1) {
            gameMap.snowStorm();
        }
        if(currentPlayer < players.size()-1) {
            currentPlayer++;
        }
        else{
            currentPlayer = 0;
            for(int i = 0; i < players.size(); i++){
                if (players.get(i).getDrown() == 1){
                    players.get(i).setDrown(2);
                }
            }
        }

        if((players.get(currentPlayer).getDrown() == 2) || players.get(currentPlayer).getLife() == 0 )
        {
            players.get(currentPlayer).setLife(0);
            this.setDie(true);
        }
        for (Player p : players){
            if (p.getLife()==0)
                setDie(true);
        }
        getPlayers().get(currentPlayer).setWork(0);
        if(getPlayers().get(currentPlayer).getInWater() && getPlayers().size() > 1)
            nextPlayer();
    }

    /*
    Kötél használata
    @param t: a Tile amire kihúzzuk a játékost
    @param p: a játékos akit kihúzunk
     */
    public void useRope(Tile t, Player p){
        Tile t_owner = p.getTile();
        boolean x = false;

        if(t.getMovables().size() > 0){
            for (int i = 0; i < t_owner.getNeighbours().size(); i++){
                if(t_owner.getNeighbours().get(i).getTileId() == t.getTileId()){
                    x = true;
                    break;
                }
            }

            if(x){
                String id = t.getMovables().get(0).getId();
                t.getMovables().get(0).setInWater(false);
                t.getMovables().get(0).move(t_owner);
                for (int i = 0; i < t_owner.getMovables().size(); i++){
                    if(t_owner.getMovables().get(i).getId().equals(id)){
                        t_owner.getMovables().get(i).setWork(0);
                    }
                }
                p.work();
            }
        }
    }

    /*
    * Pisztoly összeszerelése.
    * */
    public void assemble() {
        if (pistolParts.get(0).getOwner()!=null && pistolParts.get(1).getOwner()!=null && pistolParts.get(2).getOwner()!=null) {

            int id1 = pistolParts.get(0).getOwner().getTile().getTileId();
            int id2 = pistolParts.get(1).getOwner().getTile().getTileId();
            int id3 = pistolParts.get(2).getOwner().getTile().getTileId();
            if (id1 == id2 && id1 == id3) {

                setWin(true);
                players.get(currentPlayer).work();
                //System.out.println("Nyertél!");
            }
        }
    }

    /*
    Hozzáad a pisztolydarabok listájához egy újabbat
    @param pp: a hozzáadandó pisztolydarab
     */
    public void addPistolPart(PistolPart pp){pistolParts.add(pp);}

    /*
    * Játék vége.
    * */
    public void endGame(){
        System.out.println("Game Over!");
        //System.exit(0);
    }

    /*
    Visszadja a játékban lévő játékosok listáját
     */
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
    /*
       Visszadja a win értékét
     */
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
    /*
       Beállítja a die értékét.
    */
    public void setDie(boolean die) {
        this.die = die;
        if(die)
            endGame();
    }

    public boolean getDie(){
        return die;
    }

    /*
    * Létrehozza a térképet.
    * */
    public void initMap2() {
        for(int i = 0; i < 7; i++) {
            Tile t = new Tile(i);
            gameMap.addTile(t);
        }
        for(int i = 7; i < 12; i++) {
            Unstable u = new Unstable(0, i);
            gameMap.addTile(u);
        }
        for(int i = 12; i < 15; i++) {
            int randomNum = ThreadLocalRandom.current().nextInt(1, 5);
            Unstable u = new Unstable(randomNum, i);
            gameMap.addTile(u);
        }
        Researcher r1 = new Researcher("r0");
        Researcher r2 = new Researcher("r1");
        Eskimo e1 = new Eskimo("e0");
        PolarBear p = new PolarBear("p0");

        this.addPlayers(r1);
        this.addPlayers(r2);
        this.addPlayers(e1);
        polarbears.add(p);

        PistolPart p1 = new PistolPart();
        PistolPart p2 = new PistolPart();
        PistolPart p3 = new PistolPart();
        pistolParts.add(p1);
        pistolParts.add(p2);
        pistolParts.add(p3);
        DivingSuit ds = new DivingSuit();
        Food f = new Food();
        FragileShovel fs = new FragileShovel();
        Rope r = new Rope();
        Shovel s = new Shovel();
        Tent t = new Tent();

        gameMap.getTiles().get(0).addNeighbour(gameMap.getTiles().get(7));
        gameMap.getTiles().get(0).addNeighbour(gameMap.getTiles().get(1));
        gameMap.getTiles().get(0).addNeighbour(gameMap.getTiles().get(8));
        gameMap.getTiles().get(0).addNeighbour(gameMap.getTiles().get(12));
        gameMap.getTiles().get(0).setSnow(4);
        gameMap.getTiles().get(0).setThing(p1);
        gameMap.getTiles().get(0).SetX(150);gameMap.getTiles().get(0).SetY(0);

        gameMap.getTiles().get(1).addNeighbour(gameMap.getTiles().get(7));
        gameMap.getTiles().get(1).addNeighbour(gameMap.getTiles().get(9));
        gameMap.getTiles().get(1).addNeighbour(gameMap.getTiles().get(13));
        gameMap.getTiles().get(1).addMoveAbles(polarbears.get(0));
        polarbears.get(0).setMyTile(gameMap.getTiles().get(1));
        gameMap.getTiles().get(1).setSnow(2);
        gameMap.getTiles().get(1).setThing(p2);
        gameMap.getTiles().get(1).SetX(450);gameMap.getTiles().get(1).SetY(0);

        gameMap.getTiles().get(2).addNeighbour(gameMap.getTiles().get(0));
        gameMap.getTiles().get(2).addNeighbour(gameMap.getTiles().get(7));
        gameMap.getTiles().get(2).addNeighbour(gameMap.getTiles().get(8));
        gameMap.getTiles().get(2).addNeighbour(gameMap.getTiles().get(4));
        gameMap.getTiles().get(2).addNeighbour(gameMap.getTiles().get(9));
        gameMap.getTiles().get(2).addNeighbour(gameMap.getTiles().get(13));
        gameMap.getTiles().get(2).setSnow(3);
        gameMap.getTiles().get(2).setThing(f);
        gameMap.getTiles().get(2).SetX(190);gameMap.getTiles().get(2).SetY(150);

        gameMap.getTiles().get(3).addNeighbour(gameMap.getTiles().get(12));
        gameMap.getTiles().get(3).addNeighbour(gameMap.getTiles().get(8));
        gameMap.getTiles().get(3).addNeighbour(gameMap.getTiles().get(4));
        gameMap.getTiles().get(3).addNeighbour(gameMap.getTiles().get(11));
        gameMap.getTiles().get(3).addNeighbour(gameMap.getTiles().get(5));
        gameMap.getTiles().get(3).setSnow(3);
        gameMap.getTiles().get(3).setThing(t);
        gameMap.getTiles().get(3).SetX(0);gameMap.getTiles().get(3).SetY(300);
        //gameMap.getTiles().get(3).setSafe(true);
        //gameMap.getTiles().get(4).setSafeByTent(true);

        gameMap.getTiles().get(4).addNeighbour(gameMap.getTiles().get(3));
        gameMap.getTiles().get(4).addNeighbour(gameMap.getTiles().get(8));
        gameMap.getTiles().get(4).addNeighbour(gameMap.getTiles().get(2));
        gameMap.getTiles().get(4).addNeighbour(gameMap.getTiles().get(10));
        gameMap.getTiles().get(4).addNeighbour(gameMap.getTiles().get(14));
        gameMap.getTiles().get(4).addNeighbour(gameMap.getTiles().get(11));
        gameMap.getTiles().get(4).setSnow(4);
        gameMap.getTiles().get(4).SetX(150);gameMap.getTiles().get(4).SetY(300);

        gameMap.getTiles().get(5).addNeighbour(gameMap.getTiles().get(3));
        gameMap.getTiles().get(5).addNeighbour(gameMap.getTiles().get(11));
        gameMap.getTiles().get(5).setSnow(3);
        for(int i = 0; i < 3; i++) {
            gameMap.getTiles().get(5).addMoveAbles(players.get(i));
            players.get(i).setMyTile(gameMap.getTiles().get(5));
        }
        gameMap.getTiles().get(5).setThing(r);
        gameMap.getTiles().get(5).SetX(40);gameMap.getTiles().get(5).SetY(450);

        gameMap.getTiles().get(6).addNeighbour(gameMap.getTiles().get(14));
        gameMap.getTiles().get(6).addNeighbour(gameMap.getTiles().get(10));
        gameMap.getTiles().get(6).addNeighbour(gameMap.getTiles().get(9));
        gameMap.getTiles().get(6).addNeighbour(gameMap.getTiles().get(13));
        gameMap.getTiles().get(6).setSnow(3);
        gameMap.getTiles().get(6).setThing(s);
        gameMap.getTiles().get(6).SetX(450);gameMap.getTiles().get(6).SetY(300);

        gameMap.getTiles().get(7).addNeighbour(gameMap.getTiles().get(0));
        gameMap.getTiles().get(7).addNeighbour(gameMap.getTiles().get(1));
        gameMap.getTiles().get(7).addNeighbour(gameMap.getTiles().get(2));
        gameMap.getTiles().get(7).addNeighbour(gameMap.getTiles().get(13));
        gameMap.getTiles().get(7).setSnow(0);
        gameMap.getTiles().get(7).SetX(300);gameMap.getTiles().get(7).SetY(40);

        gameMap.getTiles().get(8).addNeighbour(gameMap.getTiles().get(0));
        gameMap.getTiles().get(8).addNeighbour(gameMap.getTiles().get(2));
        gameMap.getTiles().get(8).addNeighbour(gameMap.getTiles().get(3));
        gameMap.getTiles().get(8).addNeighbour(gameMap.getTiles().get(4));
        gameMap.getTiles().get(8).addNeighbour(gameMap.getTiles().get(12));
        gameMap.getTiles().get(8).setSnow(0);
        gameMap.getTiles().get(8).SetX(40);gameMap.getTiles().get(8).SetY(150);

        gameMap.getTiles().get(9).addNeighbour(gameMap.getTiles().get(1));
        gameMap.getTiles().get(9).addNeighbour(gameMap.getTiles().get(6));
        gameMap.getTiles().get(9).addNeighbour(gameMap.getTiles().get(13));
        gameMap.getTiles().get(9).setSnow(0);
        gameMap.getTiles().get(9).SetX(490);gameMap.getTiles().get(9).SetY(150);

        gameMap.getTiles().get(10).addNeighbour(gameMap.getTiles().get(2));
        gameMap.getTiles().get(10).addNeighbour(gameMap.getTiles().get(4));
        gameMap.getTiles().get(10).addNeighbour(gameMap.getTiles().get(6));
        gameMap.getTiles().get(10).addNeighbour(gameMap.getTiles().get(13));
        gameMap.getTiles().get(10).addNeighbour(gameMap.getTiles().get(14));
        gameMap.getTiles().get(10).setSnow(0);
        gameMap.getTiles().get(10).SetX(300);gameMap.getTiles().get(10).SetY(300);

        gameMap.getTiles().get(11).addNeighbour(gameMap.getTiles().get(3));
        gameMap.getTiles().get(11).addNeighbour(gameMap.getTiles().get(4));
        gameMap.getTiles().get(11).addNeighbour(gameMap.getTiles().get(5));
        gameMap.getTiles().get(11).addNeighbour(gameMap.getTiles().get(14));
        gameMap.getTiles().get(11).setSnow(0);
        gameMap.getTiles().get(11).SetX(190);gameMap.getTiles().get(11).SetY(450);

        gameMap.getTiles().get(12).addNeighbour(gameMap.getTiles().get(0));
        gameMap.getTiles().get(12).addNeighbour(gameMap.getTiles().get(3));
        gameMap.getTiles().get(12).addNeighbour(gameMap.getTiles().get(8));
        gameMap.getTiles().get(12).setSnow(2);
        gameMap.getTiles().get(12).setThing(ds);
        gameMap.getTiles().get(12).SetX(0);gameMap.getTiles().get(12).SetY(0);

        gameMap.getTiles().get(13).addNeighbour(gameMap.getTiles().get(1));
        gameMap.getTiles().get(13).addNeighbour(gameMap.getTiles().get(2));
        gameMap.getTiles().get(13).addNeighbour(gameMap.getTiles().get(6));
        gameMap.getTiles().get(13).addNeighbour(gameMap.getTiles().get(7));
        gameMap.getTiles().get(13).addNeighbour(gameMap.getTiles().get(9));
        gameMap.getTiles().get(13).addNeighbour(gameMap.getTiles().get(10));
        gameMap.getTiles().get(13).setSnow(3);
        gameMap.getTiles().get(13).setThing(fs);
        gameMap.getTiles().get(13).SetX(340);gameMap.getTiles().get(13).SetY(150);

        gameMap.getTiles().get(14).addNeighbour(gameMap.getTiles().get(4));
        gameMap.getTiles().get(14).addNeighbour(gameMap.getTiles().get(6));
        gameMap.getTiles().get(14).addNeighbour(gameMap.getTiles().get(10));
        gameMap.getTiles().get(14).addNeighbour(gameMap.getTiles().get(11));
        gameMap.getTiles().get(14).setSnow(3);
        //gameMap.getTiles().get(14).limit = 2;
        gameMap.getTiles().get(14).setThing(p3);
        gameMap.getTiles().get(14).SetX(340);gameMap.getTiles().get(14).SetY(450);
    }

    /*
    Kirajzolja a játék állását
     */
    public void draw() {
        /*System.out.println("Tiles\n" + "TileID:safe:snow:standing on:neighbours:visible things;\n");
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
        if(players.size() > 0)
            System.out.println("\nCurrent player: " + players.get(currentPlayer).getId());
        System.out.println("");*/
    }

    /*
    Visszaadja az aktuális játékos számát.
     */
    public int getCurrentPlayer() {
        return currentPlayer;
    }

    /*
    Visszaadja a játék mapját
     */
    public Map getGameMap() {
        return gameMap;
    }

}
