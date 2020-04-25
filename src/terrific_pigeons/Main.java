package terrific_pigeons;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Game game = new Game();
        Scanner input = new Scanner(System.in);

        String command = input.nextLine();
        while(command != "exit")
        {
                String[] commandparam = command.split(" ");
                switch(commandparam[0])
                {
                    /*case "load":
                        loadTestMap(commandparam[1]);
                        if (commandparam[1] == "/maps/test_01.txt")
                        {

                        }*/
                    case "init":
                        game.startGame();
                        break;
                    case "pass":
                        game.nextPlayer();
                        break;
                    case "move":
                        Map map = game.getGameMap();
                        ArrayList <Tile> tiles = map.getTiles();
                        Tile moveParam = new Tile(999999);
                        for(int i = 0; i < tiles.size() ;i++) {
                            if(Integer.parseInt(commandparam[1]) == tiles.get(i).getTileId())
                                moveParam = tiles.get(i);
                        }

                        int current = game.getCurrentPlayer();
                        game.getPlayers().get(current).move(moveParam);
                        break;
                    //case "exit":
                    //case "load":

                    default:
                        System.out.println("There is no command like this!");

                }
            command = input.nextLine();
            commandparam = command.split(" ");
        }
        game.endGame();
        loadTestMap("src/maps/test_map_01.txt");

    }
    public static void promptEnterKey(){
        System.out.println("Press \"ENTER\" to continue...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    public static void loadTestMap(String test)
    {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(test));
            String line = reader.readLine();
            while (line != null) {
                System.out.println(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
