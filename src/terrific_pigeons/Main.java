package terrific_pigeons;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {

        Game game = new Game();

        boolean started = false;
        while(!started) {
            System.out.println("1. Start the game with your own map - type: load example.txt");
            System.out.println("2. Start the game with generated map - type: init");
            Scanner choose = new Scanner(System.in);

            String chosedStart = choose.nextLine();

            if(chosedStart.equals("init")) {
                game.startGame();
                started = true;
            }
            else if(chosedStart.equals("load")) {
                //Valami
                started = true;
            }

        }

        System.out.println("Command: ");
        Scanner input = new Scanner(System.in);

        String command = input.nextLine();
        while(!command.equals("exit"))
        {

                int current = game.getCurrentPlayer();
                String[] commandparam = command.split(" ");
                switch(commandparam[0])
                {
                    /*case "load":
                        loadTestMap(commandparam[1]);
                        if (commandparam[1] == "/maps/test_01.txt")
                        {

                        }*/

                    case "pass":
                        game.getPlayers().get(current).pass();
                        //game.nextPlayer();
                        game.draw();
                        break;
                    case "move":
                        Map map = game.getGameMap();
                        ArrayList <Tile> tiles = map.getTiles();
                        Tile moveParam = new Tile(999999);
                        for(int i = 0; i < tiles.size() ;i++) {
                            if(Integer.parseInt(commandparam[1]) == tiles.get(i).getTileId())
                                moveParam = tiles.get(i);
                        }

                        current = game.getCurrentPlayer();
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
                    case "useThing":
                        current = game.getCurrentPlayer();
                        if(game.getPlayers().get(current).getThings().size() > 0){
                            for(int i=0; i < game.getPlayers().get(current).getThings().size(); i++)
                                System.out.println(i + ". " + game.getPlayers().get(current).getThings().get(i).Name());

                            System.out.println("which item would you like to use?");
                            Scanner menu = new Scanner(System.in);
                            int choose = Integer.parseInt(input.nextLine());
                            game.getPlayers().get(current).getThings().get(choose).useThing();
                        } else System.out.println("Inventory is empty!");
                        break;
                    case "eskimoSpecialAbility":
                        current = game.getCurrentPlayer();
                        game.getPlayers().get(current).build();
                        break;
                    case "reasercherSpecialAbility":
                        map = game.getGameMap();
                        tiles = map.getTiles();
                        moveParam = new Tile(999999);
                        for(int i = 0; i < tiles.size() ;i++) {
                            if(Integer.parseInt(commandparam[1]) == tiles.get(i).getTileId())
                                moveParam = tiles.get(i);
                        }

                        current = game.getCurrentPlayer();
                        game.getPlayers().get(current).look(moveParam);
                        break;
                    case "save":
                        game.save("probafajbairas.txt");
                        break;

                    //case "exit":
                    //case "load":
                    default:
                        System.out.println("There is no command like this!");
                }
                if(game.getPlayers().get(current).getWork() >= 4 && game.getPlayers().get(current).getInWater() == false){
                    game.nextPlayer();
                }
                else if(game.getPlayers().get(current).getInWater() == true) {
                    game.nextPlayer();
                }

                System.out.println("Next command: ");
                command = input.nextLine();
                commandparam = command.split(" ");
        }
        game.endGame();
        //loadTestMap("src/maps/test_map_01.txt");

    }
    public static void promptEnterKey(){
        System.out.println("Press \"ENTER\" to continue...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    //Betölti a felhasználó által megadott testesetet
    public static Game loadTestMap(String test)
    {
        Game g = new Game();
        System.out.println("map loaded");
        BufferedReader reader;
        Map m = new Map();
        try {
            reader = new BufferedReader(new FileReader(test));
            String line;
            line = reader.readLine();
            while (line != null)
            {
                ArrayList<MoveAble> moveAbles = new ArrayList<MoveAble>();
                while (!line.equals("movaebles end"))
                {
                    System.out.println(line);
                    String[] moveableParams = line.split(":");
                    System.out.println("MovableID: " + moveableParams[0] + " myTileID: " + moveableParams[1] +
                            " life: " + moveableParams[2] + " work: " + moveableParams[3] +
                            " inWater: " + moveableParams[4] + " tárgy: " + moveableParams[5] );

                    for (int i = 0; i < moveableParams.length; i++ )
                    {
                        //Megnezzuk milyen tipusu karaktert kene felvenni
                        String characterType = Character.toString( moveableParams[i].charAt(0));
                        if(characterType.equals("R"))
                        {
                            //TILE BEALLITASANAL ALLITSUK BE A MOVEABLEK MYTILEJAT
                            //!!!!!!!!!!!!!!!!!!!!
                            Researcher moveTemp = new Researcher(moveableParams[i]);
                            moveTemp.setLife(Integer.parseInt(moveableParams[2]));
                            moveTemp.setWork(Integer.parseInt(moveableParams[3]));
                            if(moveableParams[4].equals("-")) moveTemp.setInWater(false);
                            else moveTemp.setInWater(true);
                            String[] tempThings = moveableParams[5].split(",");
                            for(int j = 0; j < tempThings.length; j++)
                            {
                                switch (tempThings[j])
                                {
                                    case "PP":  moveTemp.addThing(new PistolPart());break;
                                    case "T":  moveTemp.addThing(new Tent());break;
                                    case "FS":  moveTemp.addThing(new FragileShovel());break;
                                    case "F":  moveTemp.addThing(new Food());break;
                                    case "D":  moveTemp.addThing(new DivingSuit());break;
                                    case "Ro":  moveTemp.addThing(new Rope());break;
                                    case "S":  moveTemp.addThing(new Shovel());break;
                                }
                            }
                            moveAbles.add(moveTemp);
                            g.addPlayers(moveTemp);
                        }
                        else if(characterType.equals("E"))
                        {
                            Eskimo moveTemp = new Eskimo(moveableParams[i]);
                            moveTemp.setLife(Integer.parseInt(moveableParams[2]));
                            moveTemp.setWork(Integer.parseInt(moveableParams[3]));
                            if(moveableParams[4].equals("-")) moveTemp.setInWater(false);
                            else moveTemp.setInWater(true);
                            String[] tempThings = moveableParams[5].split(",");
                            for(int j = 0; j < tempThings.length; j++)
                            {
                                switch (tempThings[j])
                                {
                                    case "PP":  moveTemp.addThing(new PistolPart());break;
                                    case "T":  moveTemp.addThing(new Tent());break;
                                    case "FS":  moveTemp.addThing(new FragileShovel());break;
                                    case "F":  moveTemp.addThing(new Food());break;
                                    case "D":  moveTemp.addThing(new DivingSuit());break;
                                    case "Ro":  moveTemp.addThing(new Rope());break;
                                    case "S":  moveTemp.addThing(new Shovel());break;
                                }
                            }
                            moveAbles.add(moveTemp);
                            g.addPlayers(moveTemp);
                        }
                        else if(characterType.equals("P"))
                        {
                            PolarBear moveTemp = new PolarBear(moveableParams[i]);
                            moveAbles.add(moveTemp);
                            g.addPolarBear(moveTemp);
                        }
                    }
                    line = reader.readLine();
                }
                line = reader.readLine();
                //ITT KENE UGRANI
                while(!line.equals("tiles end")) {
                    String[] tileParams = line.split(":");
                    System.out.println("TileID: " + tileParams[0] + " stabil: " + tileParams[1] +
                            " limit: " + tileParams[2] + " védettség: " + tileParams[3] + " hó: " +tileParams[4] +
                            " rajta áll: " + tileParams[5] + " szomszédok: " + tileParams[6] + " tárgy: " + tileParams[7]);

                    //ha stabil, stabilat, ha nem instabilat hozunk létre
                    if(tileParams[1].equals("+"))
                    {
                        Tile temp = new Tile(Integer.parseInt(tileParams[0]));
                        setTileParams(temp,tileParams,moveAbles);
                        m.addTile(temp);
                    }
                    else
                    {
                        Unstable temp = new Unstable(Integer.parseInt(tileParams[2]), Integer.parseInt(tileParams[0]));
                        setUnstableParams(temp,tileParams,moveAbles);
                        m.addTile(temp);
                    }
                    line = reader.readLine();
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        g.setGameMap(m);
        return g;
    }

    public static Tile setTileParams(Tile temp,String[] tileParams, ArrayList<MoveAble> moveAbles)
    {
        String[] tempNeighbors = tileParams[6].split(",");
        String[] tempMovables = tileParams[4].split(",");
        //vedettseg
        if(tileParams[3].equals("T")) temp.setSafe(true);
        else if(tileParams[3].equals("I")) temp.setSafeByTent(true);
        temp.setSnow(Integer.parseInt(tileParams[4]));
        //szomszedok beallitasa a beolvasas alapjan
        for (int i = 0; i < tempNeighbors.length; i++ )
        {
            Tile neighbortemp = new Tile(Integer.parseInt(tempNeighbors[i]));
            temp.addNeighbour(neighbortemp);
        }
        //Rajta allo karakterek
        for (int i = 0; i < tempMovables.length; i++ )
        {
            int j = 0;
            while(j < moveAbles.size())
            {
                if(moveAbles.get(j).getId().equals(tempMovables[i]))
                {
                    temp.addMoveAbles(moveAbles.get(j));
                    moveAbles.get(j).setMyTile(temp);
                    j++;
                }
            }
        }
        //Targy benne
        switch(tileParams[7])
        {
            case "PP":  temp.setThing(new PistolPart());break;
            case "T":  temp.setThing(new Tent());break;
            case "FS":  temp.setThing(new FragileShovel());break;
            case "F":  temp.setThing(new Food());break;
            case "DS":  temp.setThing(new DivingSuit());break;
            case "Ro":  temp.setThing(new Rope());break;
            case "S":  temp.setThing(new Shovel());break;
        }
        return temp;
    }

    public static Unstable setUnstableParams(Unstable temp,String[] tileParams, ArrayList<MoveAble> moveAbles)
    {
        String[] tempNeighbors = tileParams[6].split(",");
        String[] tempMovables = tileParams[4].split(",");
        //vedettseg
        if(tileParams[3].equals("T")) temp.setSafe(true);
        else if(tileParams[3].equals("I")) temp.setSafeByTent(true);
        temp.setSnow(Integer.parseInt(tileParams[4]));
        //szomszedok beallitasa a beolvasas alapjan
        for (int i = 0; i < tempNeighbors.length; i++ )
        {
            Tile neighbortemp = new Tile(Integer.parseInt(tempNeighbors[i]));
            temp.addNeighbour(neighbortemp);
        }
        //Rajta allo karakterek
        for (int i = 0; i < tempMovables.length; i++ )
        {
            int j = 0;
            while(j < moveAbles.size())
            {
                if(moveAbles.get(j).getId().equals(tempMovables[i]))
                {
                    temp.addMoveAbles(moveAbles.get(j));
                    moveAbles.get(j).setMyTile(temp);
                    j++;
                }
            }
        }

        //Targy benne
        switch(tileParams[7])
        {
            case "PP":  temp.setThing(new PistolPart());break;
            case "T":  temp.setThing(new Tent());break;
            case "FS":  temp.setThing(new FragileShovel());break;
            case "F":  temp.setThing(new Food());break;
            case "DS":  temp.setThing(new DivingSuit());break;
            case "Ro":  temp.setThing(new Rope());break;
            case "S":  temp.setThing(new Shovel());break;
        }
        return temp;
    }

    //Összehasonlítja a teszt végrahajtása után kapott txt-t egy txt-vel ami az elvárt kimenetet tartalmazza
    public static boolean compareResult(String result, String expectation) throws IOException {
        BufferedReader reader1 = new BufferedReader(new FileReader(result));
        BufferedReader reader2 = new BufferedReader(new FileReader(expectation));
        String line1 = reader1.readLine();
        String line2 = reader2.readLine();
        boolean areEqual = true;

        while (line1 != null || line2 != null)
        {
            if(line1 == null || line2 == null)
            {
                areEqual = false;
                break;
            }
            else if(! line1.equalsIgnoreCase(line2))
            {
                areEqual = false;
                break;
            }
            line1 = reader1.readLine();
            line2 = reader2.readLine();
        }
        reader1.close();
        reader2.close();
        return areEqual;
    }

    //Osszehasonlitja az osszes tesztesetet es kiirja az eredmenyt
    public static void testAll() throws IOException {
        FileWriter fw = new FileWriter("src/allTestResults.txt");
        String resultBase ="src/results/result_map_";
        String expectedBase ="src/expected/expected_map_";
        int good = 0;
        int bad = 0;
        for(int i = 1; i < 24; i++)
        {
            String resultPath;
            String expectedPath;
            if(i < 10)
            {
                resultPath = resultBase + "0" + Integer.toString(i) +".txt";
                expectedPath = expectedBase + "0" + Integer.toString(i) + ".txt";
            }
            else
            {
                resultPath = resultBase + Integer.toString(i)+".txt";
                expectedPath = expectedBase + Integer.toString(i)+".txt";
            }
            boolean valid = compareResult(resultPath,expectedPath);
            if(valid)
            {
                good++;
                fw.write("Test " + i + "is good.\n");
            }
            else {
                bad++;
                fw.write("Test " + i + "is bad.\n");
            }
        }
        fw.write("Test results: " + Integer.toString(good) +"/24");
        fw.close();
    }
}

