package terrific_pigeons;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Number of eskimos: ");
        int numOfEskimos = Integer.parseInt(input.nextLine());
        System.out.println("Number of researchers: ");
        int numOfResearchers = Integer.parseInt(input.nextLine());
        Game game = new Game();
        for(int i = 0; i < numOfEskimos; i++) {
            Integer.toString(i);
            String id = "E"+i;
            Eskimo e = new Eskimo(id);
            game.addPlayers(e);
        }
        for(int i = 0; i < numOfResearchers; i++) {
            Integer.toString(i);
            String id = "R"+i;
            Researcher r = new Researcher(id);
            game.addPlayers(r);
        }

        game.initMap(4, 5);
        game.draw();

        String command = input.nextLine();
        /*while(command != "exit")
        {
            String[] commandparam = command.split(" ");
                break;
                switch(commandparam[0])
                {
                    case "load":
                    case "pass":

                }
            command = input.nextLine();
            commandparam = command.split(" ");
        }*/
    }
    public static void promptEnterKey(){
        System.out.println("Press \"ENTER\" to continue...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }
}
