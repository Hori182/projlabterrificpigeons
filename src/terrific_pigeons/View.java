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
       setForeground(Color.BLACK);
        g.drawOval(x,y, r,r);
    }
    public void drawUnstable(Graphics g, int x, int y, int r){
        setForeground(Color.GRAY);
        g.fillOval(x, y, r, r);
    }
    public void drawWater(Graphics g, int x, int y, int r){
        setForeground(Color.BLUE);
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
                drawTile(g,x,y,50);
            }
            else if(7 < i && i < 12)
            {
                drawWater(g,x,y,50);
            }
            else drawUnstable(g,x,y,50);
            System.out.println("X: " + x + " Y: " + y);
        }
    }

    public void drawMap(Graphics g){

    }

}
