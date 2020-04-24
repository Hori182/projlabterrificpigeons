package terrific_pigeons;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Number of eskimoes: ");
        int numOfEskimoes = Integer.parseInt(input.nextLine());
        System.out.println("Number of reserchers: ");
        int numOfReserchers = Integer.parseInt(input.nextLine());
        Game game = new Game();
        for(int i = 0; i < numOfEskimoes; i++) {
            Eskimo e = new Eskimo();
            game.addPlayers(e);
        }
        for(int i = 0; i < numOfReserchers; i++) {
            Researcher r = new Researcher();
            game.addPlayers(r);
        }

        String command = input.nextLine();
        while(command != "exit")
        {
            String[] commandparam = command.split(" ");
                break;
                switch(commandparam[0])
                {
                    case "load":


                        break;

                    case "pass":

                }
            command = input.nextLine();
            commandparam = command.split(" ");
        }

    }
    public static void promptEnterKey(){
        System.out.println("Press \"ENTER\" to continue...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }
}
