package terrific_pigeons;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {


        System.out.println("Menu");
        System.out.println("1.Eskimo moves to stable tile");
        System.out.println("2.Eskimo moves to unstable tile");
        //lehet akk itt meg kene kerdezni h tobben allnak e rajta mint amennyit elbir
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
        System.out.println("13.Eskimo equips thing");
        System.out.println("14.Snowstorm catches player");
        System.out.println("15.Snowstorm comes, player safe in iglu\n");

        System.out.print("Please choose: ");
        Scanner input = new Scanner( System.in );
        int choice = input.nextInt();

        switch (choice)
        {
            case 1:
            {
                Eskimo e = new Eskimo();
                System.out.println("Eskimo e created");
                Tile t1 = new Tile();
                System.out.println("Tile created");
                Tile t2 = new Tile();
                System.out.println("Tile created");
                e.setMyTile(t2);
                e.move(t1);
                break;
            }
            case 2:
            {
                Eskimo e = new Eskimo();
                System.out.println("Eskimo e created");
                Unstable t1 = new Unstable();
                System.out.println("Unstable tile created");
                Tile t2 = new Tile();
                System.out.println("Tile created");
                e.setMyTile(t2);
                e.move(t1);
                break;
            }
        }



    }

}
