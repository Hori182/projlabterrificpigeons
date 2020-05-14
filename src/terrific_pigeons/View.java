package terrific_pigeons;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.io.Console;
import java.util.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class View extends JPanel {
    JFrame jf = new JFrame();
    Graphics g;


    public View(){
        /*jf.setSize(500,500);
        jf.setVisible(true);*/
    }

    private List<IViewable> viewables;
    private Game game;
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
    public void drawTile(Graphics g, int x, int y, int r){
        g.setColor(Color.WHITE);
        g.fillOval(x,y, r,r);
        g.setColor(Color.BLACK);
        g.drawOval(x,y,r,r);
    }
    public void drawUnstable(Graphics g, int x, int y, int r){
        g.setColor(Color.RED);
        g.fillOval(x, y, r, r);
        g.setColor(Color.BLACK);
        g.drawOval(x,y,r,r);
    }
    public void drawWater(Graphics g, int x, int y, int r){
        g.setColor(Color.BLUE);
        g.fillOval(x, y, r, r);
        g.setColor(Color.BLACK);
        g.drawOval(x,y,r,r);
    }

    public void mouse_click(){
        Player p = game.getPlayers().get(game.getCurrentPlayer());
        int tile_id = -1;


        if (p.getWork() < 4){
            System.out.println(game.getPlayers().get(game.getCurrentPlayer()).getId().toString() + " " + game.getPlayers().get(game.getCurrentPlayer()).getTile().getTileId());

            addMouseListener(new MouseAdapter() {
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
                                if(game.getGameMap().getTiles().get(i) != game.getPlayers().get(game.getCurrentPlayer()).getTile()){
                                    b = true;
                                    tile_id = i;
                                    break;
                                }
                            }
                        }
                    }

                    if(b){
                        game.getPlayers().get(game.getCurrentPlayer()).move(game.getGameMap().getTiles().get(tile_id));
                    }
                    System.out.println(game.getPlayers().get(game.getCurrentPlayer()).getTile().getMovables().size());
                }
            });
        }
    }

    public void drawNieghbours(){

    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

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
                drawTile(g,x,y,50);
            }
            else if(i < 15)
            {
                if(game.getGameMap().getTiles().get(i).getLimit() == 0) drawWater(g,x,y,50);
                else drawUnstable(g,x,y,50);
            }

            if(game.getGameMap().getTiles().get(i).getMovables().size() > 0) {
                int eltolas = 0;
                System.out.println(game.getGameMap().getTiles().get(i).getMovables().size());
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
        mouse_click();
    }

    public void drawMap(Graphics g){

    }

}
