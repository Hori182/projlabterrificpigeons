package terrific_pigeons;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        int exit = 0;

        while (exit == 0) {

            System.out.println("Menu");
            System.out.println("1.Eskimo moves to stable tile");
            System.out.println("2.Eskimo moves to unstable tile");
            System.out.println("3.Eskimo drowns");
            System.out.println("4.Eskimo freezes");
            System.out.println("5.Eskimo uses diving suit");
            System.out.println("6.Eskimo eats food");
            System.out.println("7.Eskimo assembles pistol");
            System.out.println("8.Eskimo saves with rope");
            System.out.println("9.Eskimo builds iglu");
            System.out.println("10.Researcher scans tile");
            System.out.println("11.Eskimo digs");
            System.out.println("12.Eskimo pass");
            System.out.println("13.Eskimo equips shovel");
            System.out.println("14.Snowstorm catches player");
            System.out.println("15.Snowstorm comes, player safe in iglu");
            System.out.println("16. Exit\n");

            System.out.print("Please choose: ");
            Scanner input = new Scanner( System.in );
            int choice = input.nextInt();

            switch (choice)
            {
                case 1:
                {
                    Eskimo e = new Eskimo();
                    Tile t1 = new Tile();
                    Tile t2 = new Tile();
                    e.setMyTile(t2);
                    e.move(t1);
                    break;
                }
                case 2:
                {
                    Eskimo e = new Eskimo();
                    Unstable t1 = new Unstable();
                    Tile t2 = new Tile();
                    e.setMyTile(t2);
                    System.out.println("t/t1 is unstable");
                    e.move(t1);
                    break;
                }
                case 3:
                {
                    Game g = new Game();
                    g.nextPlayer();
                    break;
                }
                case 6: {
                    Food f = new Food();
                    Eskimo e = new Eskimo();
                    f.owner = e;
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
                    t.receive(e);
                    e.myTile = t;
                    e.dig();
                    break;
                }
                case 12:
                {
                    Eskimo e = new Eskimo();
                    e.pass();
                    break;
                }
                case 13:
                {
                    Eskimo e = new Eskimo();
                    Tile t = new Tile();
                    Shovel shovel = new Shovel();
                    e.setMyTile(t);
                    t.thing = shovel;
                    e.equip();
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
