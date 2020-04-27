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
        System.out.println("lefutottam 1");

        for (int i = 0; i < neighbours.size(); i++){
            ArrayList<MoveAble> wp = neighbours.get(i).getMovables();
            System.out.println("lefutottam 2");
            for (int j = 0; j < wp.size(); j++) {
                MoveAble m1 = wp.get(j);
                System.out.println("lefutottam 3");
                 if (m1.getInWater() == true) {
                     System.out.println("lefutottam 4");
                     t.moveRequest(m1);
                     m1.setInWater(false);
                     //lololol
                   /*owner.getTile().moveRequest(owner.getTile().getNeighbours().get(i).getMovables().get(j));
                   owner.getTile().getNeighbours().get(i).getMovables().get(j).setInWater(false);*/

                }
            }
        }
        owner.work();
    }

    public String Name(){
        return "R";
    }
}

