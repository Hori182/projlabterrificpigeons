package terrific_pigeons;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        Game game = new Game();

        boolean started = false;
        while(!started) {
            System.out.println("1. Start the game with your own map - type: load example.txt");
            System.out.println("2. Start the game with generated map - type: init");
            System.out.println("3. Testing - type: test all (for all tests) / test test_10 (to run single test #10 for example) ");
            Scanner choose = new Scanner(System.in);

            String chosedStart = choose.nextLine();
            String[] load = chosedStart.split(" ");

            if(chosedStart.equals("init")) {
                game.startGame();
                started = true;
            }
            else if(load[0].equals("load")) {
                game = loadTestMap("./src/maps/" + load[1]);
                started = true;
            }
            else if(load[0].equals("test"))
            {
                started = true;
                int c = 0, nc = 0;
                if(load[1].equals("all"))
                {
                    File folder1 = new File("./src/tests");
                    File[] listOfFiles = folder1.listFiles();

                    for (int i = 0; i < listOfFiles.length; i++) {
                        if (listOfFiles[i].isFile()) {
                            System.out.println("Test " + i + ": ");
                            read_test("./src/tests/" + listOfFiles[i].getName());
                            //compareResult("./src/results/"+listOfresults[i].getName(), "./src/expected/"+listOfexpected[i].getName());
                            //ead_test("./src/tests/"+load[1]);  //ez kell az előző 5 sor helyett, ha egyesével akarsz tesztelni, pl. test test_07.txt
                        }
                    }
                    System.out.println("Results are in allTestResults.txt");
                    testAll();
                }
                else {
                        read_test("./src/tests/" + load[1] + ".txt");
                        String[] t2 = load[1].split("_");
                        boolean correct;
                        if(t2[1].charAt(0) == '0') {
                            correct = compareResult("./src/results/result_map_0" + t2[1].charAt(1) + ".txt",
                                    "./src/expected/expected_map_0" + t2[1].charAt(1) + ".txt");
                        }
                        else
                        {
                            correct = compareResult("./src/results/result_map_" + t2[1].charAt(0) + t2[1].charAt(1) + ".txt",
                                    "./src/expected/expected_map_" + t2[1].charAt(0) + t2[1].charAt(1) + ".txt");
                        }
                        if(correct)
                        {
                            System.out.println("The test was successful");
                        }
                        else System.out.println("Unsuccessful test");
                }
            }

        }

        System.out.println("Command: ");
        Scanner input = new Scanner(System.in);

        String command = input.nextLine();
        while(!command.equals("exit")) {
            int current = game.getCurrentPlayer();
            String[] commandparam = command.split(" ");
            Command(commandparam, game, current);

            System.out.println("Next command: ");
            command = input.nextLine();
            commandparam = command.split(" ");
        }
        game.endGame();
    }

    public static void Command(String[] commandparam, Game game, int current) throws FileNotFoundException, UnsupportedEncodingException {
            switch(commandparam[0])
            {
                case "pass":
                    game.getPlayers().get(current).pass();
                    //game.draw();
                    break;
                case "snowstorm":
                    game.getGameMap().snowStorm();
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
                    if(game.getPlayers().size() > 0)
                        game.getPlayers().get(current).move(moveParam);
                    game.draw();
                    break;
                case "polarbear":
                    map = game.getGameMap();
                    tiles = map.getTiles();
                    moveParam = new Tile(999999);
                    for(int i = 0; i < tiles.size() ;i++) {
                        if(Integer.parseInt(commandparam[1]) == tiles.get(i).getTileId())
                            moveParam = tiles.get(i);
                    }
                    game.polarbears.get(0).move(moveParam);
                    break;
                case "dig":
                    current = game.getCurrentPlayer();
                    if(game.getPlayers().get(current).myTile.getSnow() > 0) {
                        game.getPlayers().get(current).dig();
                        game.draw();
                    }
                    else System.out.println("Snow: 0");
                    game.draw();
                    break;

                case "equip":
                    current = game.getCurrentPlayer();
                    if(game.getPlayers().get(current).myTile.getThing() != null && game.getPlayers().get(current).myTile.getSnow() == 0)
                        game.getPlayers().get(current).equip();
                    else System.out.println("There is nothing to equip!");
                    game.draw();
                    break;
                case "draw":
                    game.draw();
                    break;
                case "shovel":
                    current = game.getCurrentPlayer();
                    things("Sh", game, current);
                    break;
                case "rope":
                    current = game.getCurrentPlayer();
                    things("R", game, current);
                    break;
                case "pistolpart":
                    current = game.getCurrentPlayer();
                    game.assemble();
                    break;
                case "fragileshovel":
                    current = game.getCurrentPlayer();
                    things("FR", game, current);
                    break;
                case "tent":
                    current = game.getCurrentPlayer();
                    things("T", game, current);
                    break;
                case "food":
                    current = game.getCurrentPlayer();
                    things("F", game, current);
                    break;
                case "divingsuit":
                    current = game.getCurrentPlayer();
                    things("D", game, current);
                    break;
                case "eskimoSpecialAbility":
                    current = game.getCurrentPlayer();
                    game.getPlayers().get(current).build();
                    game.draw();
                    break;
                case "researcherSpecialAbility":
                    map = game.getGameMap();
                    tiles = map.getTiles();
                    moveParam = new Tile(999999);
                    for(int i = 0; i < tiles.size() ;i++) {
                        if(Integer.parseInt(commandparam[1]) == tiles.get(i).getTileId())
                            moveParam = tiles.get(i);
                    }

                    current = game.getCurrentPlayer();
                    game.getPlayers().get(current).look(moveParam);
                    game.draw();
                    break;
                case "save":
                    game.save("./src/results/" + commandparam[1] + ".txt");
                    game.draw();
                    break;

                //case "exit":
                //case "load":
                default:
                    System.out.println("There is no command like this!");
            }
            if(game.getPlayers().size() > 0) {
                if (game.getPlayers().get(current).getWork() >= 4 && !game.getPlayers().get(current).getInWater()) {
                    game.nextPlayer();
                } else if (game.getPlayers().get(current).getInWater()) {
                    game.nextPlayer();
                }
            }
    }

    public static void things(String a, Game game, int current){
        boolean x = false;
        for(int i = 0; i < game.getPlayers().get(current).getThings().size(); i++){
            if (game.getPlayers().get(current).getThings().get(i).Name() == a){
                game.getPlayers().get(current).getThings().get(i).useThing();
                x = true;
            }
        }
        if(x == false) System.out.println("No item.");
    }

    public static void read_test(String test) throws IOException {
        Game game = new Game();
        int current = 0;
        BufferedReader reader;
        reader = new BufferedReader(new FileReader(test));
        String line;

        line = reader.readLine();
        String[] command = line.split(" ");
        if(command[0].equals("load")){
            System.out.println(command[1]);
            game = loadTestMap(command[1]);
            current = game.getCurrentPlayer();
        }
        while (!line.equals("exit")){
            line = reader.readLine();
            System.out.println(line);
            command = line.split(" ");
            if(!command[0].equals("exit")){
                Command(command, game, current);
                //line = reader.readLine();
            }
        }

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
                ArrayList<MoveAble> moveAbles = new ArrayList<MoveAble>();
                while (!line.equals("moveables end"))
                {
                    System.out.println(line);
                    String[] moveableParams = line.split(":");

                    //for (int i = 0; i < moveableParams.length; i++ )
                    //{
                        //Megnezzuk milyen tipusu karaktert kene felvenni
                        String characterType = Character.toString( moveableParams[0].charAt(0));
                        if(characterType.equals("R"))
                        {
                            //TILE BEALLITASANAL ALLITSUK BE A MOVEABLEK MYTILEJAT
                            //!!!!!!!!!!!!!!!!!!!!
                            Researcher moveTemp = new Researcher(moveableParams[0]);
                            moveTemp.setLife(Integer.parseInt(moveableParams[2]));
                            moveTemp.setWork(Integer.parseInt(moveableParams[3]));
                            if(moveableParams[4].equals("-")) {
                                moveTemp.setInWater(false);
                            }
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

                            System.out.println("MovableID: " + moveableParams[0] + " myTileID: " + moveableParams[1] +
                                    " life: " + moveableParams[2] + " work: " + moveableParams[3] +
                                    " inWater: " + moveableParams[4] + " tárgy: " + moveableParams[5]);
                        }
                        else if(characterType.equals("E"))
                        {
                            Eskimo moveTemp = new Eskimo(moveableParams[0]);
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

                            System.out.println("MovableID: " + moveableParams[0] + " myTileID: " + moveableParams[1] +
                                    " life: " + moveableParams[2] + " work: " + moveableParams[3] +
                                    " inWater: " + moveableParams[4] + " tárgy: " + moveableParams[5]);
                        }
                        if(characterType.equals("P"))
                        {
                            PolarBear moveTemp = new PolarBear(moveableParams[0]);
                            moveAbles.add(moveTemp);
                            g.addPolarBear(moveTemp);
                            //System.out.println("MovableID: " + moveableParams[0] + " myTileID: " + moveableParams[1]);
                        }
                    //}
                    line = reader.readLine();
                }
                line = reader.readLine();

                while(!line.equals("tiles end")) {
                    String[] tileParams = line.split(":");
                   System.out.println("TileID: " + tileParams[0] + " stabil: " + tileParams[1] +
                            " limit: " + tileParams[2] + " védettség: " + tileParams[3] + " hó: " +tileParams[4] +
                            " rajta áll: " + tileParams[5] + " tárgy: " + tileParams[6] + " szomszédok: " + tileParams[7]);

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
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        g.setGameMap(m);
        return g;
    }

    public static Tile setTileParams(Tile temp,String[] tileParams, ArrayList<MoveAble> moveAbles)
    {
        String[] tempNeighbors = tileParams[7].split(",");
        String[] tempMovables = null;
        if(!tileParams[5].equals("-")) {
            tempMovables = tileParams[5].split(",");
        }
        //vedettseg
        if(tileParams[3].equals("I")) temp.setSafe(true);
        else if(tileParams[3].equals("T")) temp.setSafeByTent(true);
        temp.setSnow(Integer.parseInt(tileParams[4]));
        //szomszedok beallitasa a beolvasas alapjan
        for (int i = 0; i < tempNeighbors.length; i++ )
        {
            Tile neighbortemp = new Tile(Integer.parseInt(tempNeighbors[i]));
            temp.addNeighbour(neighbortemp);
        }

        //Rajta allo karakterek
        if(tempMovables != null){
            for (int i = 0; i < tempMovables.length; i++ )
            {
                int j = 0;
                while(j < moveAbles.size())
                {
                    if(moveAbles.get(j).getId().equals(tempMovables[i]))
                    {
                        temp.addMoveAbles(moveAbles.get(j));
                        moveAbles.get(j).setMyTile(temp);
                    }
                    j++;
                }
            }
        }
        //Targy benne
        switch(tileParams[6])
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
        String[] tempNeighbors = tileParams[7].split(",");
        String[] tempMovables = null;

        if(!tileParams[5].equals("-")) {
            tempMovables = tileParams[5].split(",");
        }

        //vedettseg
        if(tileParams[3].equals("I")) temp.setSafe(true);
        else if(tileParams[3].equals("T")) temp.setSafeByTent(true);
        temp.setSnow(Integer.parseInt(tileParams[4]));
        //szomszedok beallitasa a beolvasas alapjan
        for (int i = 0; i < tempNeighbors.length; i++ )
        {
            Tile neighbortemp = new Tile(Integer.parseInt(tempNeighbors[i]));
            temp.addNeighbour(neighbortemp);
        }
        //Rajta allo karakterek
        if(tempMovables != null){
            for (int i = 0; i < tempMovables.length; i++ )
            {
                int j = 0;
                while(j < moveAbles.size())
                {
                    if(moveAbles.get(j).getId().equals(tempMovables[i]))
                    {
                        temp.addMoveAbles(moveAbles.get(j));
                        moveAbles.get(j).setMyTile(temp);
                    }
                    j++;
                }
            }
        }
        //Targy benne
        switch(tileParams[6])
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
            else{
                String[] ar_line1 = line1.split(":");
                String[] ar_line2 = line2.split(":");
                if(ar_line1.length == ar_line2.length){
                    for (int i = 0; i < ar_line1.length; i++){
                        if(ar_line1[i].equals(ar_line2[i])) {
                            continue;
                        }
                        else{
                            areEqual = false;
                            break;
                        }
                    }
                }
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
        for(int i = 1; i < 24; i++) {
            if (i == 11 || i == 16) {

            } else {
                String resultPath;
                String expectedPath;
                if (i < 10) {
                    resultPath = resultBase + "0" + Integer.toString(i) + ".txt";
                    expectedPath = expectedBase + "0" + Integer.toString(i) + ".txt";
                } else {
                    resultPath = resultBase + Integer.toString(i) + ".txt";
                    expectedPath = expectedBase + Integer.toString(i) + ".txt";
                }
                boolean valid = compareResult(resultPath, expectedPath);
                if (valid) {
                    good++;
                    fw.write("Test " + i + " is good.\n");
                } else {
                    bad++;
                    fw.write("Test " + i + " is bad.\n");
                }
            }
        }
            fw.write("Test results: " + Integer.toString(good) + "/21");
            fw.close();
    }
}

