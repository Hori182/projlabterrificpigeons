package terrific_pigeons;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/*
 * A Rope a kötél osztály a játékban.
 * Segítségével kimenthetünk egy vízbe esett játékost.
 */
public class Rope extends Thing{

    public Rope() { System.out.println("Rope r created"); }

    /*
     * A saját jégtáblájára húzza a mellette vízben levő játékost.
     */

    public void useThing() {
        System.out.println("-> useThing() -> r");
        Tile t = owner.getTile();
        ArrayList<Tile> temp = t.getNeighbours();
        int randomNum = ThreadLocalRandom.current().nextInt(0, temp.size() );
        ArrayList<Player> temp2 = temp.get(randomNum).getPlayers();
        int randomNum2 = ThreadLocalRandom.current().nextInt(0, temp.size() );
        temp2.get(randomNum2).getInWater();
        t.moveRequest(temp2.get(randomNum2));
        owner.work();
        System.out.println("r <- e");
        System.out.println("<- r");
    }
}

