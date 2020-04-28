package terrific_pigeons;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/*
 * A Rope a kötél osztály a játékban.
 * Segítségével kimenthetünk egy vízbe esett játékost.
 */
public class Rope extends Thing{

    /*
     * A saját jégtáblájára húzza a mellette vízben levő játékost.
     */
    public void useThing() {
        Tile t = owner.getTile();
        ArrayList<Tile> neighbours = t.getNeighbours();

        for (int i = 0; i < neighbours.size(); i++){
            ArrayList<MoveAble> wp = neighbours.get(i).getMovables();
            for (int j = 0; j < wp.size(); j++) {
                MoveAble m1 = wp.get(j);
                 if (m1.getInWater() == true) {
                     m1.setMyTile(t);
                     m1.setInWater(false);
                }
            }
        }
        owner.work();
    }
    /*Visszaadja a Rope nevét*/
    public String Name(){
        return "R";
    }
}

