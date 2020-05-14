package terrific_pigeons;

import javax.swing.*;
import java.awt.*;
import java.io.Console;
import java.util.List;

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
    public void drawPolarBear(){}
    public void drawEskimo(){}
    public void drawResearcher(){}
    public void drawTile(Graphics g, int x, int y, int r){
        g.setColor(Color.BLACK);
        g.drawOval(x,y, r,r);
    }
    public void drawUnstable(Graphics g, int x, int y, int r){
        g.setColor(Color.RED);
        g.fillOval(x, y, r, r);
    }
    public void drawWater(Graphics g, int x, int y, int r){
        g.setColor(Color.BLUE);
        g.fillOval(x, y, r, r);
    }
    public void drawNieghbours(){}

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        for(int i = 0; i< game.getGameMap().getTiles().size(); i++)
        {
            int x = game.getGameMap().getTiles().get(i).GetX();
            int y = game.getGameMap().getTiles().get(i).GetY();
            if(i < 7)
            {
                if(y ==150 || y==450)
                {
                    game.getGameMap().getTiles().get(i).SetX(x+40);
                    x = x+40;
                }
                drawTile(g,x,y,50);
            }
            else if(i < 12)
            {
                if(y ==150 || y==450)
                {
                    game.getGameMap().getTiles().get(i).SetX(x+40);
                    x = x+40;
                }
                drawWater(g,x,y,50);
            }
            else
            {
                if(y ==150 || y==450)
                {
                    game.getGameMap().getTiles().get(i).SetX(x+40);
                    x = x+40;
                }
                drawUnstable(g,x,y,50);
            }
           //System.out.println("X: " + x + " Y: " + y);
        }

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
    }

    public void drawMap(Graphics g){

    }

}
