package src;

//https://www.tutorialspoint.com/how-to-make-a-canvas-in-java-swing

import javax.swing.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;

public class Canvas extends JPanel {

    private Spielfeld SFeld;

    public Canvas(Spielfeld pFeld) {
        SFeld = pFeld;
    }

    private final int XOFFSET = 20;
    private final int YOFFSET = 15;
    private final int CIRCLEWIDTH = 80;
    private final int CIRCLEHEIGHT = 80;

    private Graphics2D graphics;
    public int[] pclmScore = { -200, -200, -200, -200, -200, -200, -200 };

    public void paintScore(int[] clmScore) {
        pclmScore = clmScore;
        graphics.setColor(Color.black);
        for (int i = 0; i < clmScore.length; i++) {
            if (clmScore[i] != -200)
                graphics.drawString("" + clmScore[i], i * (CIRCLEWIDTH + XOFFSET), 600);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        int[][] Field = SFeld.getField();
        graphics = (Graphics2D) g;
        for (int x = 0; x < Field.length; x++) {
            for (int y = 0; y < Field[x].length; y++) {
                if (Field[x][y] == 1) {
                    graphics.setColor(Color.red);
                    graphics.fillArc((CIRCLEWIDTH + XOFFSET) * x, (CIRCLEHEIGHT + YOFFSET) * y, CIRCLEWIDTH,
                            CIRCLEHEIGHT, 0, 360);
                } else if (Field[x][y] == 2) {
                    graphics.setColor(Color.yellow);
                    graphics.fillArc((CIRCLEWIDTH + XOFFSET) * x, (CIRCLEHEIGHT + YOFFSET) * y, CIRCLEWIDTH,
                            CIRCLEHEIGHT, 0, 360);
                }

                graphics.setColor(Color.gray);
                graphics.drawRect(x * (XOFFSET + CIRCLEWIDTH) - XOFFSET / 2, y * (YOFFSET + CIRCLEHEIGHT) - YOFFSET / 2,
                        CIRCLEWIDTH + XOFFSET, CIRCLEHEIGHT + YOFFSET);
            }
        }
        paintScore(pclmScore);
    }
}
