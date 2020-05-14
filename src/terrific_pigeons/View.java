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
    public void drawTile(Graphics g, int x, int y, int l, int w){
        g.fillOval(x,y, l,w);
    }
    public void drawUnstable(){}
    public void drawWater(){}
    public void drawNieghbours(){}

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        drawTile(g, 0, 0, 50, 50);
    }

    public void drawMap(Graphics g){}

}
