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
        /*jf.setSize(500,500);
        jf.setVisible(true);*/
    }

    private List<IViewable> viewables;
    private Game game;
    protected ArrayList<JButton> buttons = new ArrayList<>();


    public void setGame(Game g)
    {
        this.game = g;
    }
    public void init(Map map){}
    public void drawPolarBear(Graphics g, int x, int y, int eltolas){
        g.setColor(Color.RED);
        g.drawRect(x+eltolas+5,y+20,10,10);
    }
    public void drawEskimo(Graphics g, int x, int y, int eltolas){
        g.setColor(Color.GREEN);
        g.drawRect(x+eltolas+5,y+20,10,10);
    }
    public void drawResearcher(Graphics g, int x, int y, int eltolas){
        g.setColor(Color.BLUE);
        g.drawRect(x+eltolas+5,y+20,10,10);
    }
    public void drawTile(Graphics g, int x, int y, int r, Color c){
        g.setColor(Color.WHITE);
        g.fillOval(x,y, r,r);
        g.setColor(c);
        g.drawOval(x,y,r,r);
    }
    public void drawUnstable(Graphics g, int x, int y, int r, Color c){
        g.setColor(Color.RED);
        g.fillOval(x, y, r, r);
        g.setColor(c);
        //System.out.println(c.toString());
        g.drawOval(x,y,r,r);
    }
    public void drawWater(Graphics g, int x, int y, int r){
        g.setColor(Color.BLUE);
        g.fillOval(x, y, r, r);
        g.setColor(Color.BLACK);
        g.drawOval(x,y,r,r);
    }
    public void drawSideBar(Graphics g)
    {
        g.setColor(Color.BLACK);
        int fontSize = 15;
        Font f = new Font("Courier", Font.BOLD, fontSize);
        g.setFont(f);
        g.drawRect(650,50,250,400);
        g.drawString("Current player: "+game.getPlayers().get(game.getCurrentPlayer()).getId(),720,170);
        g.drawString("Life: " + game.getPlayers().get(game.getCurrentPlayer()).getLife(),720,210);
        g.drawString("Work: " + game.getPlayers().get(game.getCurrentPlayer()).getWork(),720,250);
        //g.drawString("TileID: " + game.getPlayers().get(game.getCurrentPlayer()).getTile().getName(),720,220);
        g.drawString("Snow: " + game.getPlayers().get(game.getCurrentPlayer()).getTile().getSnow(),720,290);
        if(game.getPlayers().get(game.getCurrentPlayer()).getTile().getSnow() == 0)
        {
            g.drawString("Thing on Tile: " + game.getPlayers().get(game.getCurrentPlayer()).getTile().getThing().getName(),720,330);
        }
        else g.drawString("Thing on Tile: snow",720,330);

        String things ="";
        for (Thing t : game.getPlayers().get(game.getCurrentPlayer()).getThings()){
            things+=t.getName();
        }
        g.drawString(("Own things: " + things), 720, 370);
    }

    public void update(){
        this.repaint();
    }

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
                                if(game.getGameMap().getTiles().get(i).getLimit() != 0){
                                    if(game.getGameMap().getTiles().get(i) != game.getPlayers().get(game.getCurrentPlayer()).getTile() && game.getPlayers().get(game.getCurrentPlayer()).getTile().getNeighbours().contains(game.getGameMap().getTiles().get(i)) ){
                                         b = true;
                                        tile_id = i;
                                        break;
                                    }
                                }
                            }
                        }
                    }

                    if(b) {
                        game.getPlayers().get(game.getCurrentPlayer()).move(game.getGameMap().getTiles().get(tile_id));
                        update();
                    }
                }
            });
        } else{
            game.nextPlayer();

            if(game.getCurrentPlayer() == 0){
                game.polarbears.get(0).moveToRandom();
                update();
            }
        }
    }

    public void addButtons(){
        JButton bPass = new JButton("Pass");
        add(bPass);
        bPass.setBounds(650, 450, 125, 25);
        buttons.add(bPass);
        bPass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.getPlayers().get(game.getCurrentPlayer()).pass();
                update();
            }
        });

        JButton bDig = new JButton("Dig");
        add(bDig);
        bDig.setBounds(650,475,125,25);
        buttons.add(bDig);
        bDig.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.getPlayers().get(game.getCurrentPlayer()).dig();
                update();
            }
        });
    }

    public ArrayList<JButton> getButtons(){
        return buttons;
    }

    public void drawNieghbours(){

    }

    @Override
    public void paintComponent(Graphics g){

        super.paintComponent(g);
        drawMap(g);
        drawSideBar(g);

        mouse_click();
    }

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
            }
            else if(i < 15)
            {
                if(game.getGameMap().getTiles().get(i).getLimit() == 0) drawWater(g,x,y,50);
                else
                {
                    if(game.getGameMap().getTiles().get(i).getSafe()){ drawUnstable(g,x,y,50,Color.GREEN); }
                    else if(game.getGameMap().getTiles().get(i).getSafeByTent()){ drawUnstable(g,x,y,50,Color.BLUE); }
                    else drawUnstable(g,x,y,50,Color.BLACK);
                }
            }

            if(game.getGameMap().getTiles().get(i).getMovables().size() > 0) {
                int eltolas = 0;
                for(int j = 0; j < game.getGameMap().getTiles().get(i).getMovables().size(); j++) {
                    if(game.getGameMap().getTiles().get(i).getMovables().get(j).getId().contains("e")) {
                        drawEskimo(g, game.getGameMap().getTiles().get(i).GetX(), game.getGameMap().getTiles().get(i).GetY(), eltolas);
                        eltolas += 15;
                    }
                    else if(game.getGameMap().getTiles().get(i).getMovables().get(j).getId().contains("r")) {
                        drawResearcher(g, game.getGameMap().getTiles().get(i).GetX(), game.getGameMap().getTiles().get(i).GetY(), eltolas);
                        eltolas += 15;
                    }
                    else if(game.getGameMap().getTiles().get(i).getMovables().get(j).getId().contains("p")) {
                        drawPolarBear(g, game.getGameMap().getTiles().get(i).GetX(), game.getGameMap().getTiles().get(i).GetY(), eltolas);
                        eltolas += 15;
                    }
                }
            }
        }

    }

}
