package terrific_pigeons;

import javax.swing.*;
import java.awt.*;
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
    public void drawPResearcher(){}
    public void drawTile(Graphics g, int x, int y, int r){
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
        drawUnstable(g, 0, 0, 50);
    }

    public void drawMap(Graphics g){}

}
