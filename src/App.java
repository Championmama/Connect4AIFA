package src;

import javax.swing.*;
import java.awt.event.*;

public class App extends JFrame {
    private final int width = 1000;
    private final int height = 800;

    private boolean turn = false;
    private boolean won = false;
    private int TEAM = 1;

    public static void main(String[] args) {
        new App();
    }

    private Spielfeld Field = new Spielfeld();
    private Canvas canvas = new Canvas(Field);
    private AI ai = new AI();

    public App() {
        super();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(width, height);

        // Bildelemente
        canvas.setSize(width - 200, height);
        canvas.setVisible(true);

        // System.out.println(TensorFlow.version());

        canvas.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (won)
                    return;
                if (e.getButton() == 1 || e.getButton() == 2) {
                    int x = e.getLocationOnScreen().x;
                    int y = e.getLocationOnScreen().y;
                    int xpos = (int) (x / 100);
                    int ypos = (int) (y / 95);
                    if (xpos < Field.length && ypos < Field.width) {
                        if ((turn ? 2 : 1) == TEAM) {
                            Field.putBall(xpos, TEAM);
                            canvas.repaint();
                        } else
                            return;
                    }

                    if (Field.testSieger(xpos)) {
                        System.out.println("Player Win");
                        won = true;
                    } else {

                        // MinMax Algorithmus
                        int pos = ai.getNextMove(Field);
                        if (pos == -1) {
                            System.out.println("AI has no Idea what to do");
                            return;
                        }
                        Spielfeld cFeld = Field.getNextFeld(pos, TEAM % 2 + 1);
                        Field.setField(cFeld);
                        if (Field.testSieger(pos)) {
                            System.out.println("Computer Win");
                            won = true;
                        }
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }

        });

        add(canvas);

        setVisible(true);
    }

}
