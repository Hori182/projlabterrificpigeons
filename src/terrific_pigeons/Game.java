package terrific_pigeons;

public class Game {
    private Map gameMap;
    private Player[] players;
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
        players[1].getWork();
        players[1].getInWater();
        players[1].die();
        System.out.println("<- g");
    }

    /**/
    public void assemble(){}

    /**/
    public void endGame(){}

    public Player[] getPlayers() {
        return players;
    }

    public void setPlayers(Player p, int position) {
        players[position] = p;
    }

}
