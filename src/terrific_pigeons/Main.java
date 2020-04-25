package terrific_pigeons;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Game game = new Game();
        Scanner input = new Scanner(System.in);

        game.startGame();
        String command = input.nextLine();
        while(command != "exit")
        {
            String[] commandparam = command.split(" ");
                break;
                switch(commandparam[0])
                {
                    case "load":
                        loadTestMap(commandparam[1]);
                        if (commandparam[1] == "/maps/test_01.txt")
                        {

                        }
                    case "pass":
                        game.nextPlayer();
                        break;
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

    public static void loadTestMap(String test)
    {

    }
}
