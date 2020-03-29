package terrific_pigeons;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        int exit = 0;

        while (exit == 0) {

            System.out.println("Menu");
            /*
                Eszkimo stabil mezőről stabilra mozog
             */
            System.out.println("1.Eskimo moves to stable tile");
            /*
                Eszkimo stabil mezőről instabilra mozog
                és a döntés alapján vízbe eshet
             */
            System.out.println("2.Eskimo moves to unstable tile");
            /*
            Az eskimo megfullad
             */
            System.out.println("3.Eskimo drowns");
            /*
            Az eszkimo megfagy
             */
            System.out.println("4.Eskimo freezes");
            /*
            A buvarruha eletben tartja az eszkimot
             */
            System.out.println("5.Eskimo uses diving suit");
            /*
            Eszkimo eszik egy ételt
             */
            System.out.println("6.Eskimo eats food");
            /*
            Eszkimo osszeszereli a pisztolyt
             */
            System.out.println("7.Eskimo assembles pistol");
            /*
            Eszkimo kiment valakit egy kötéllel
             */
            System.out.println("8.Eskimo saves with rope");
            /*
            Eszkimo épít egy iglut
             */
            System.out.println("9.Eskimo builds iglu");
            /*
            Kutató megvizsgálja a jegtablat
             */
            System.out.println("10.Researcher scans tile");
            /*
            Eszkimo havat ás
             */
            System.out.println("11.Eskimo digs");
            /*
            Eszkimo elpasszolja a körét
             */
            System.out.println("12.Eskimo pass");
            /*
            Eszkimo felvesz egy lapatot
             */
            System.out.println("13.Eskimo equips shovel");
            /*
            Hovihar elkapja a jatekost
             */
            System.out.println("14.Snowstorm catches player");
            /*
            Hovihar nem bantja a jatekost az iglu miatt
             */
            System.out.println("15.Snowstorm comes, player safe in iglu");
            System.out.println("16. Exit\n");

            System.out.print("Please choose: ");
            Scanner input = new Scanner( System.in );
            int choice = input.nextInt();

            switch (choice)
            {
                //ELSO KETTOT MEG NEZZUK AT
                case 1:
                {
                    Eskimo e = new Eskimo();
                    Tile t1 = new Tile();
                    t1.setName("t1");
                    Tile t2 = new Tile();
                    t2.setName("t2");
                    e.setMyTile(t2);
                    System.out.println("\n");
                    e.move(t1);
                    break;
                }
                case 2:
                {
                    Eskimo e = new Eskimo();
                    Unstable t1 = new Unstable();
                    t1.setName("t1");
                    Tile t2 = new Tile();
                    t2.setName("t2");
                    e.setMyTile(t2);
                    System.out.println("t1 is unstable");
                    System.out.println("\n");
                    e.move(t1);
                    break;
                }
                //MEGVAN
                case 3:
                {
                    Game g = new Game();
                    Eskimo e1 = new Eskimo();
                    e1.setWork(0);
                    e1.setInWater(true);
                    g.setPlayers(e1);
                    g.nextPlayer();
                    break;
                }
                //MEGVAN
                case 5:
                {
                    Eskimo e = new Eskimo();
                    Unstable u = new Unstable();
                    Tile t = new Tile();
                    break;
                }

                case 6: {
                    Food f = new Food();
                    Eskimo e = new Eskimo();
                    e.setWork(0);
                    f.setOwner(e);
                    System.out.println("\n");
                    f.useThing();
                    break;
                }
                case 9:
                {
                    Eskimo e = new Eskimo();
                    Tile t = new Tile();
                    e.setMyTile(t);
                    e.build();
                    break;
                }
                case 10:
                {
                    Researcher r = new Researcher();
                    Tile t1 = new Tile();
                    Tile t2 = new Tile();
                    r.setMyTile(t1);
                    r.look(t2);
                    break;
                }
                case 11:
                {
                    Eskimo e = new Eskimo();
                    Tile t = new Tile();
                    t.setName("t1");
                    t.receive(e);
                    e.setMyTile(t);
                    e.dig();
                    break;
                }
                case 12:
                {
                    Eskimo e = new Eskimo();
                    e.pass();
                    break;
                }
                //MEGVAN
                case 13:
                {
                    Eskimo e = new Eskimo();
                    Tile t = new Tile();
                    t.setName("t");
                    Shovel shovel = new Shovel();
                    e.setMyTile(t);
                    t.setThing(shovel);
                    e.equip();
                    break;
                }
                case 14:
                {
                    Map m = new Map();
                    Eskimo e = new Eskimo();
                    System.out.println("Life: "+e.getLife());
                    Tile t = new Tile();
                    t.setName("t");
                    e.setMyTile(t);
                    t.receive(e);
                    m.addTile(t);

                    m.snowStorm();
                    break;
                }
                case 15:
                {
                    Map m = new Map();
                    Eskimo e = new Eskimo();
                    System.out.println("Life: "+e.getLife());
                    Tile t = new Tile();
                    t.setName("t");
                    t.setSafe(true);
                    e.setMyTile(t);
                    t.receive(e);
                    m.addTile(t);

                    m.snowStorm();
                    break;
                }
                case 16:
                {
                    exit = 1;
                }

            }
            if(exit == 0) { promptEnterKey(); }
        }

    }
    public static void promptEnterKey(){
        System.out.println("Press \"ENTER\" to continue...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }
}
