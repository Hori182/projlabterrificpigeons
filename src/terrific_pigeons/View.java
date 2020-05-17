package terrific_pigeons;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.MouseAdapter;

public class View extends JPanel {
    public View(){

    }

    private List<IViewable> viewables;
    private Game game;
    protected ArrayList<JButton> buttons = new ArrayList<>();
    protected JComboBox combo = new JComboBox<>();
    protected JTextArea area = new JTextArea();
    protected Tile actualTile;
    protected JButton bRestart = new JButton("RESTART");
    protected int basicbuttons = 5; //azt jelöli, hány db alapvető gomb van


    /*
    * Beállítja a game változó értékét.
    * @param g: Game - erre állítja be a game változó értékét.
    * */
    public void setGame(Game g)
    {
        this.game = g;
    }
    public void init(Map map){}
    /*
    * Jegesmedvét kirajzoló függvény.
    * @param Graphics g
    * @param int x - Ebbe az x pozícióba rajzolja ki.
    * @param int y - Ebbe az y pozícióba rajzolja ki.
    * @param int eltolas - eltolás értéke.
    * */
    public void drawPolarBear(Graphics g, int x, int y, int eltolas){
        g.setColor(Color.BLACK);
        g.fillRect(x+eltolas+5,y+20,10,10);
    }
    /*
     * Eszkimót kirajzoló függvény.
     * @param Graphics g
     * @param int x - Ebbe az x pozícióba rajzolja ki.
     * @param int y - Ebbe az y pozícióba rajzolja ki.
     * @param int eltolas - eltolás értéke.
     * */
    public void drawEskimo(Graphics g, int x, int y, int eltolas, Color c){
        g.setColor(Color.GREEN);
        g.fillRect(x+eltolas+5,y+20,10,10);
        g.setColor(c);
        g.drawRect(x+eltolas+5,y+20,10,10);
    }
    /*
     * Kutatót kirajzoló függvény.
     * @param Graphics g
     * @param int x - Ebbe az x pozícióba rajzolja ki.
     * @param int y - Ebbe az y pozícióba rajzolja ki.
     * @param int eltolas - eltolás értéke.
     * */
    public void drawResearcher(Graphics g, int x, int y, int eltolas, Color c){
        g.setColor(Color.BLUE);
        g.fillRect(x+eltolas+5,y+20,10,10);
        g.setColor(c);
        g.drawRect(x+eltolas+5,y+20,10,10);
    }
    /*
     * Tileokat kirajzoló függvény.
     * @param Graphics g
     * @param int x - Ebbe az x pozícióba rajzolja ki.
     * @param int y - Ebbe az y pozícióba rajzolja ki.
     * @param int r - sugár mérete.
     * @param Color c - tile színe.
     * */
    public void drawTile(Graphics g, int x, int y, int r, Color c){
        g.setColor(Color.WHITE);
        g.fillOval(x,y, r,r);
        g.setColor(c);
        g.drawOval(x,y,r,r);
    }
    /*
     * Unstable tileokat kirajzoló függvény.
     * @param Graphics g
     * @param int x - Ebbe az x pozícióba rajzolja ki.
     * @param int y - Ebbe az y pozícióba rajzolja ki.
     * @param int r - sugár mérete.
     * @param Color c - tile színe.
     * */
    public void drawUnstable(Graphics g, int x, int y, int r, Color c){
        g.setColor(Color.RED);
        g.fillOval(x, y, r, r);
        g.setColor(c);
        //System.out.println(c.toString());
        g.drawOval(x,y,r,r);
    }
    /*
     * Vizet kirajzoló függvény.
     * @param Graphics g
     * @param int x - Ebbe az x pozícióba rajzolja ki.
     * @param int y - Ebbe az y pozícióba rajzolja ki.
     * @param int r - sugár mérete.
     * @param Color c - tile színe.
     * */
    public void drawWater(Graphics g, int x, int y, int r){
        g.setColor(Color.cyan);
        g.fillOval(x, y, r, r);
        g.setColor(Color.BLACK);
        g.drawOval(x,y,r,r);
    }
    /*
    * Információkat megjelenítő sidebart kirajzoló függvény.
    * @param Graphics g
    * */
    public void drawSideBar(Graphics g)
    {
        g.setColor(Color.BLACK);
        int fontSize = 15;
        Font f = new Font("Courier", Font.BOLD, fontSize);
        g.setFont(f);
        g.drawRect(600,50,200,350);
        g.drawString("Current player: "+game.getPlayers().get(game.getCurrentPlayer()).getId(),645,145);
        g.drawString("Life: " + game.getPlayers().get(game.getCurrentPlayer()).getLife(),645,185);
        g.drawString("Work: " + game.getPlayers().get(game.getCurrentPlayer()).getWork(),645,225);
        //g.drawString("TileID: " + game.getPlayers().get(game.getCurrentPlayer()).getTile().getName(),720,220);
        g.drawString("Snow: " + game.getPlayers().get(game.getCurrentPlayer()).getTile().getSnow(),645,265);
        if(game.getPlayers().get(game.getCurrentPlayer()).getTile().getSnow() == 0)
        {
            if (game.getPlayers().get(game.getCurrentPlayer()).getTile().getThing()!=null)
                g.drawString("Thing on Tile: " + game.getPlayers().get(game.getCurrentPlayer()).getTile().getThing().Name(),645,305);
            else
                g.drawString("Thing on Tile: -", 645, 305);
        }
        else g.drawString("Thing on Tile: snow",645,305);

        String things ="";
        if (game.getPlayers().get(game.getCurrentPlayer()).getThings().size()!=0)
        for (Thing t : game.getPlayers().get(game.getCurrentPlayer()).getThings()) {
            things = things + t.Name();
        }
        g.drawString(("Own things: " + things), 645, 345);
    }
    /*
    * Repaint-el.
    * */
    public void update(){
        this.repaint();
        for (int i = 0; i < buttons.size(); i++){
            buttons.get(i).updateUI();
        }
    }
    /*
    * Karakterek mozgatásához használt függvény.
    * */
    public void mouse_click(){
        Player p = game.getPlayers().get(game.getCurrentPlayer());
        int tile_id = -1;

        if (p.getWork() < 4){

            this.addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent me) {
                    int tile_id = -1;
                    int x = me.getX();
                    int y = me.getY();
                    boolean b = false;

                    for (int i = 0; i < game.getGameMap().getTiles().size(); i++){
                        if(x < game.getGameMap().getTiles().get(i).X + 50 &&
                                x > game.getGameMap().getTiles().get(i).X - 50){
                            if(y < game.getGameMap().getTiles().get(i).Y + 50 &&
                                    y > game.getGameMap().getTiles().get(i).Y - 50){
                                //if(game.getGameMap().getTiles().get(i).getLimit() != 0){
                                    if(game.getGameMap().getTiles().get(i) != game.getPlayers().get(game.getCurrentPlayer()).getTile() && game.getPlayers().get(game.getCurrentPlayer()).getTile().getNeighbours().contains(game.getGameMap().getTiles().get(i)) ){
                                         b = true;
                                        tile_id = i;
                                        break;
                                    }
                                //}
                            }
                        }
                    }

                    if(b && !game.getWin() && !game.getDie()) {
                        game.getPlayers().get(game.getCurrentPlayer()).move(game.getGameMap().getTiles().get(tile_id));
                        update();
                    }
                }
            });
        } else{
            game.nextPlayer();

            if(game.getCurrentPlayer() == 0){
                game.polarbears.get(0).moveToRandom();
                for (Player pl : game.getPlayers()){
                    if (pl.getLife()==0)
                        game.setDie(true);
                }
                update();
            }
        }
    }
    /*
    * Gombok hozzáadása.
    * */
    public void addButtons(){
        JButton bPass = new JButton("Pass");
        buttons.add(bPass);
        bPass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.getPlayers().get(game.getCurrentPlayer()).pass();
                update();
            }
        });

        JButton bDig = new JButton("Dig");
        buttons.add(bDig);
        bDig.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.getPlayers().get(game.getCurrentPlayer()).dig();
                update();
            }
        });

        JButton bEquip = new JButton("Equip");
        buttons.add(bEquip);
        bEquip.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.getPlayers().get(game.getCurrentPlayer()).equip();
                update();
            }
        });

        JButton bSpecial = new JButton ("Special ability");
        buttons.add(bSpecial);
        bSpecial.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.getPlayers().get(game.getCurrentPlayer()).build();
                String limit;
                for (Tile t: game.getGameMap().getTiles()){
                    if (t.getTileId()==(int)combo.getSelectedItem()) {
                        limit = game.getPlayers().get(game.getCurrentPlayer()).look(t);
                        if (limit!="")
                            area.append(limit+"\n");
                    }
                }
                update();
            }
        });

        JButton bPistol = new JButton("Assemble pistol");
        buttons.add(bPistol);
        bPistol.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.assemble();
                update();
            }
        });

        actualTile = game.getPlayers().get(game.getCurrentPlayer()).getTile();
        for (Tile t : game.getPlayers().get(game.getCurrentPlayer()).getTile().getNeighbours()){
            combo.addItem(t.getTileId());
        }

        area.setEditable(false);


        JButton bThing1 = new JButton ("elso");
        buttons.add(bThing1);

        JButton bThing2 = new JButton ("masodik");
        buttons.add(bThing2);

        JButton bThing3 = new JButton ("harmadik");
        buttons.add(bThing3);

        JButton bThing4 = new JButton("negyedik");
        buttons.add(bThing4);

        JButton bThing5 = new JButton("otodik");
        buttons.add(bThing5);

        bRestart.setOpaque(true);
        bRestart.setBackground(Color.RED);
        bRestart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game = new Game();
                game.startGame();
                for (JButton b :buttons)
                    b.setEnabled(true);
                combo.setEnabled(true);
                update();
            }
        });
    }
    /*
     * Gombok szerkesztése.
     * */
    public void editButtons(){
        for (int i=0; i<buttons.size(); i++) {
            if (i >= buttons.size() - 5 && i < buttons.size() - (5 - game.getPlayers().get(game.getCurrentPlayer()).getThings().size())) {
                buttons.get(i).setText("Use " + game.getPlayers().get(game.getCurrentPlayer()).getThings().get(i - 5).Name());
                buttons.get(i).setEnabled(true);
                for (ActionListener al : buttons.get(i).getActionListeners()) {
                    buttons.get(i).removeActionListener(al);
                }
                Thing th = game.getPlayers().get(game.getCurrentPlayer()).getThings().get(i-5);
                buttons.get(i).addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        game.getPlayers().get(game.getCurrentPlayer()).use(th);
                        update();
                    }
                });
            }
            if (i >= buttons.size() - (5 - game.getPlayers().get(game.getCurrentPlayer()).getThings().size())) {

                buttons.get(i).setText("");
                buttons.get(i).setEnabled(false);
            }
        }
        if (!actualTile.equals(game.getPlayers().get(game.getCurrentPlayer()).getTile())) {
            combo.removeAllItems();
            for (Tile t : game.getPlayers().get(game.getCurrentPlayer()).getTile().getNeighbours()) {
                combo.addItem(t.getTileId());
            }
            actualTile = game.getPlayers().get(game.getCurrentPlayer()).getTile();
        }
    }
    /*
     * Gombok tömbjét adja vissza.
     * */
    public ArrayList<JButton> getButtons(){
        return buttons;
    }

    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(2));
        super.paintComponent(g);
        drawMap(g);
        drawSideBar(g);
        editButtons();

        mouse_click();
    }
    /*
    * Térkép kirajzolása.
    * */
    public void drawMap(Graphics g){

        for(int i = 0; i < game.getGameMap().getTiles().size(); i++)
        {
            int startX = game.getGameMap().getTiles().get(i).GetX();
            int startY = game.getGameMap().getTiles().get(i).GetY();

            int neigborsize =  game.getGameMap().getTiles().get(i).getNeighbours().size();
            for(int j = 0; j < neigborsize; j++)
            {
                int endX = game.getGameMap().getTiles().get(i).getNeighbours().get(j).GetX();
                int endY = game.getGameMap().getTiles().get(i).getNeighbours().get(j).GetY();
                g.drawLine(startX+25,startY+25,endX+25,endY+25);
            }
        }
        for(int i = 0; i< game.getGameMap().getTiles().size(); i++)
        {
            int x = game.getGameMap().getTiles().get(i).GetX();
            int y = game.getGameMap().getTiles().get(i).GetY();
            if(i < 7)
            {
                if(game.getGameMap().getTiles().get(i).getSafe()){ drawTile(g,x,y,50,Color.GREEN); }
                else if(game.getGameMap().getTiles().get(i).getSafeByTent()){ drawTile(g,x,y,50,Color.BLUE); }
                else drawTile(g,x,y,50,Color.BLACK);
                g.setColor(Color.BLACK);
                g.drawString(String.valueOf(game.getGameMap().getTiles().get(i).getTileId()), x+50, y+50);
            }
            else if(i < 15)
            {
                if(game.getGameMap().getTiles().get(i).getLimit() == 0) drawWater(g,x,y,50);
                else
                {
                    if(game.getGameMap().getTiles().get(i).getSafe()){ drawUnstable(g,x,y,50,Color.GREEN); }
                    else if(game.getGameMap().getTiles().get(i).getSafeByTent()){ drawUnstable(g,x,y,50,Color.BLUE); }
                    else drawUnstable(g,x,y,50,Color.BLACK);
                    g.drawString(String.valueOf(game.getGameMap().getTiles().get(i).getTileId()), x+50, y+50);
                }
            }

            if(game.getGameMap().getTiles().get(i).getMovables().size() > 0) {
                int eltolas = 0;
                for(int j = 0; j < game.getGameMap().getTiles().get(i).getMovables().size(); j++) {
                    if(game.getGameMap().getTiles().get(i).getMovables().get(j).getId().contains("e")) {
                        if(game.getGameMap().getTiles().get(i).getMovables().get(j) == game.getPlayers().get(game.getCurrentPlayer())) {
                            drawEskimo(g, game.getGameMap().getTiles().get(i).GetX(), game.getGameMap().getTiles().get(i).GetY(), eltolas, Color.ORANGE);
                        }
                       else drawEskimo(g, game.getGameMap().getTiles().get(i).GetX(), game.getGameMap().getTiles().get(i).GetY(), eltolas, Color.GREEN);
                        eltolas += 15;
                    }
                    else if(game.getGameMap().getTiles().get(i).getMovables().get(j).getId().contains("r")) {
                        if(game.getGameMap().getTiles().get(i).getMovables().get(j) == game.getPlayers().get(game.getCurrentPlayer())) {
                            drawResearcher(g, game.getGameMap().getTiles().get(i).GetX(), game.getGameMap().getTiles().get(i).GetY(), eltolas, Color.ORANGE);
                        }
                        else drawResearcher(g,game.getGameMap().getTiles().get(i).GetX(), game.getGameMap().getTiles().get(i).GetY(), eltolas, Color.BLUE);
                        eltolas += 15;
                    }
                    else if(game.getGameMap().getTiles().get(i).getMovables().get(j).getId().contains("p")) {
                        drawPolarBear(g, game.getGameMap().getTiles().get(i).GetX(), game.getGameMap().getTiles().get(i).GetY(), eltolas);
                        eltolas += 15;
                    }
                }
            }
        }
        if (game.getDie()) {
            g.setFont(new Font("Courier", Font.BOLD, 120));
            g.setColor(Color.BLACK);
            g.drawString("GAME OVER", 0,300);
            for (JButton b :buttons)
                b.setEnabled(false);
            combo.setEnabled(false);
        }
        if (game.getWin()){
            g.setFont(new Font("Courier", Font.BOLD, 120));
            g.setColor(Color.ORANGE);
            g.drawString("YOU WON!", 0,300);
            for (JButton b :buttons)
                b.setEnabled(false);
            combo.setEnabled(false);
        }
        addButtons();
    }

}
