package terrific_pigeons;

import javax.swing.*;
import java.util.List;

public class View extends JFrame {
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
    public void drawTile(){}
    public void drawUnstable(){}
    public void drawWater(){}
    public void drawNieghbours(){}
    public void drawMap(){}

}
