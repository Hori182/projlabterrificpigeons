package terrific_pigeons;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main extends JFrame{

    public static void main(String[] args) throws IOException {

        Game game = new Game();
        game.startGame();
        JFrame jf = new JFrame("Deadly Ice Tiles");

        jf.setResizable(false);
        View v = new View();

        v.setGame(game);
        v.addButtons();

        JPanel pButtons = new JPanel(new GridLayout(12,1));
        JPanel pArea = new JPanel(new GridLayout(1,1));
        JPanel pPanel = new JPanel (new GridLayout(2,1));
        pPanel.add(pButtons);
        pPanel.add(pArea);

        for (JButton b : v.getButtons()){
            pButtons.add(b);
            if (b.equals(v.buttons.get(3)))
                pButtons.add(v.combo);
        }
        pButtons.add(v.bRestart);
        pArea.add(v.area);

        jf.add(v, BorderLayout.CENTER);
        jf.add(pPanel, BorderLayout.EAST);
        jf.setSize(975,600);
        jf.setVisible(true);

    }
}