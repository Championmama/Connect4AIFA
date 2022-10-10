package src;

import javax.swing.*;
import java.awt.event.*;

public class App extends JFrame {
    private final int width = 1000;
    private final int height = 800;

    private boolean won = false;

    public static void main(String[] args) {
        new App();
    }

    private Spielfeld Field = new Spielfeld();
    private Canvas canvas = new Canvas(Field);
    private AI ai = new AI(canvas);

    public App() {
        super();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(width, height);

        // Bildelemente
        canvas.setSize(width, height);
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
                    if (xpos < 7 && ypos < 6) {

                        if (!Field.putBall(xpos, 1)) return;
                        canvas.paint(canvas.getGraphics());
                        if (Field.testSieger(xpos)) {
                            won = true;
                        } else {
                            
                            // MinMax Algorithmus
                            int pos = ai.getNextMove(Field);
                            if (pos == -1) {
                                //KI konnte keinen Zug finden
                                return;
                            }
                            Spielfeld cFeld = Field.getNextFeld(pos, 2);
                            Field.setField(cFeld);

                            canvas.repaint();
                            if (Field.testSieger(pos)) {
                                won = true;
                            }
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
