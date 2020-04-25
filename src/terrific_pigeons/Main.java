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
        System.out.println("Command: ");
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
                        game.draw();
                        break;
                    case "dig":
                        current = game.getCurrentPlayer();
                        if(game.getPlayers().get(current).myTile.getSnow() > 0) {
                            game.getPlayers().get(current).dig();
                            game.draw();
                        }
                        else System.out.println("Snow: 0");
                        break;

                    case "equip":
                        current = game.getCurrentPlayer();
                        if(game.getPlayers().get(current).myTile.getThing() != null && game.getPlayers().get(current).myTile.getSnow() == 0)
                            game.getPlayers().get(current).equip();
                        else System.out.println("There is nothing to equip!");
                        break;
                    case "draw":
                        game.draw();
                        break;
                    case "use":
                        current = game.getCurrentPlayer();
                        System.out.println(game.getPlayers().get(current).getThings().size());
                        if(game.getPlayers().get(current).getThings().size() > 0){
                            for(int i=0; i < game.getPlayers().get(current).getThings().size(); i++)
                                System.out.println(i + ". " + game.getPlayers().get(current).getThings().get(i).Name());
                        } else System.out.println("Inventory is empty!");
                        break;
                    //case "save":
                    //case "exit":
                    //case "load":

                    default:
                        System.out.println("There is no command like this!");
                }
                System.out.println("Next command: ");
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

    //Betölti a felhasználó által megadott testesetet
    public static void loadTestMap(String test)
    {
        System.out.println("map loaded");
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(test));
            String line;
            line = reader.readLine();
            while (line != null)
            {
                while(!line.equals("tiles end")) {
                    String[] tileParams = line.split(":");
                    System.out.println("TileID: " + tileParams[0] + " stabil: " + tileParams[1] +
                            " limit: " + tileParams[2] + " védettség: " + tileParams[3] +
                            " rajta áll: " + tileParams[4] + " tárgy: " + tileParams[5]);
                    line = reader.readLine();
                }
                line = reader.readLine();
                while (!line.equals("movaebles end"))
                {
                    String[] moveableParams = line.split(":");
                    System.out.println("MovableID: " + moveableParams[0] + " myTileID: " + moveableParams[1] +
                            " life: " + moveableParams[2] + " work: " + moveableParams[3] +
                            " inWater: " + moveableParams[4] + " tárgy: " + moveableParams[5] );
                    line = reader.readLine();
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Összehasonlítja a teszt végrahajtása után kapott txt-t egy txt-vel ami az elvárt kimenetet tartalmazza
    public static void compareResult(String result, String expectation)
    {

    }
}

