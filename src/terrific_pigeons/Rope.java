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
        Tile t = owner.getTile();
        ArrayList<Tile> temp = t.getNeighbours();
        //int randomNum = ThreadLocalRandom.current().nextInt(0, temp.size() );   //A Tile szomszédjai közül véletlenszerüen választ egyet
        /*for(int i = 0; i < temp.size(); i++)
        {
            if(temp.get(i).getLimit() == 0)
            {
                ArrayList<Player> wp = temp.get(i).getPlayers();
            }
        }

        ArrayList<Player> wp = temp.get(randomNum).getPlayers();

        int randomNum2 = ThreadLocalRandom.current().nextInt(0, temp.size() );  //Az egyik szomszédon véletlenszerüen választ egy player
        temp2.get(randomNum2).getInWater();                                            //Akinek megvizsgálja hogy vízben van-e
        t.moveRequest(temp2.get(randomNum2));
        owner.work();*/
    }
}

