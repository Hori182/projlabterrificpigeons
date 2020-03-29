package terrific_pigeons;

import java.util.ArrayList;

public class Game {
    private Map gameMap;
    //private Player[] players;
    ArrayList<Player> players = new ArrayList<>();
    private boolean win = false;
    private boolean die = false;

    public Game() {
        System.out.println("Game created");
    }

    /**/
    public void startGame(){}

    /**/
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

    /**/
    public void assemble(){}

    /**/
    public void endGame(){}

    public ArrayList<Player> getPlayers() {
        return players;
    }


    public void setPlayers(Player p) {
        players.add(p);
    }

}
