package src;

public class Spielfeld {
    public Spielfeld() {

    }

    public Spielfeld(Spielfeld copy) {
        this.Field = copy.Field;
        this.clmstate = copy.clmstate;
    }

    public Spielfeld(int[][] Feld, int[] paramclmstate) {
        for (int i = 0; i < Feld.length; i++) {
            for (int j = 0; j < Feld[i].length; j++) {
                Field[i][j] = Feld[i][j];
            }
        }
        for (int i = 0; i < paramclmstate.length; i++) {
            clmstate[i] = paramclmstate[i];
        }
    }

    /**
     * Speichert das Feld für alle Unterklassen ab:
     * 0 Leeres Feld
     * 1 Münze von Spieler 1
     * 2 Münze von KI
     */
    private int[][] Field = {
            { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }
    };
    private int[] clmstate = { 0, 0, 0, 0, 0, 0, 0 };

    public int[] getClmstate() {
        return clmstate;
    }

    public int[][] getField() {
        return Field;
    }

    public void setField(int[][] field) {
        Field = field;
    }

    public void setField(Spielfeld field) {
        Field = field.getField();
        clmstate = field.clmstate;
    }

    /**
     * Fügt bei <b>pos</b> ein Element in das lokale Feld ein und gibt das Feld zurück.
     * 
     * @param pos Position des hinzugefügten Steines
     * @return bearbeitetes Feld
     */
    public Spielfeld getNextFeld(int pos, int TEAM) {
        // Kopieren des Feldes
        int[][] pFeld = new int[Field.length][Field[0].length];
        for (int i = 0; i < pFeld.length; i++) {
            for (int j = 0; j < pFeld[i].length; j++) {
                pFeld[i][j] = Field[i][j];
            }
        }

        pFeld[pos][5 - clmstate[pos]] = TEAM;
        clmstate[pos]++;

        Spielfeld help = 
        new Spielfeld(pFeld, clmstate);
        clmstate[pos]--;
        return help;
    }

    /**
     * puts a Ball down
     * 
     * @param pos  the position to put the Disk
     * @param TEAM the Teams ball to put
     * @return true, if success; false, if no success
     */
    public boolean putBall(int pos, int TEAM) {
        if (clmstate[pos] < 6) {
            Field[pos][5 - clmstate[pos]] = TEAM;
            clmstate[pos]++;
            return true;
        } else
            return false;
    }

    public boolean testSieger(int lastPosition) {
        int savedyPos = clmstate[lastPosition] - 1;
        int TEAM = Field[lastPosition][5 - savedyPos];
        int xpos = lastPosition;
        int ypos = savedyPos;
        boolean win = false;
        int count = 1;

        // oben links
        while (!win && count < 4) {
            xpos--;
            ypos++;
            if (xpos < 0 || ypos > 5)
                break;
            if (Field[xpos][5 - ypos] == TEAM) {
                count++;
            } else
                break;
        }
        ypos = savedyPos;
        xpos = lastPosition;
        // unten rechts
        while (!win && count < 4) {
            xpos++;
            ypos--;
            if (xpos > 6 || ypos < 0)
                break;
            if (Field[xpos][5 - ypos] == TEAM) {
                count++;
            } else
                break;
        }

        if (count > 3)
            win = true;
        count = 1;

        ypos = savedyPos;
        xpos = lastPosition;
        // links
        while (!win && count < 4) {
            xpos--;
            if (xpos < 0)
                break;
            if (Field[xpos][5 - ypos] == TEAM) {
                count++;
            } else
                break;
        }
        ypos = savedyPos;
        xpos = lastPosition;
        // rechts
        while (!win && count < 4) {
            xpos++;
            if (xpos > 6)
                break;
            if (Field[xpos][5 - ypos] == TEAM) {
                count++;
            } else
                break;
        }
        if (count > 3)
            win = true;

        count = 1;
        ypos = savedyPos;
        xpos = lastPosition;
        // oben rechts
        while (!win && count < 4) {
            xpos++;
            ypos++;
            if (xpos > 6 || ypos > 5)
                break;
            if (Field[xpos][5 - ypos] == TEAM) {
                count++;
            } else
                break;
        }
        ypos = savedyPos;
        xpos = lastPosition;
        // unten links
        while (!win && count < 4) {
            xpos--;
            ypos--;
            if (xpos < 0 || ypos < 0)
                break;
            if (Field[xpos][5 - ypos] == TEAM) {
                count++;
            } else
                break;
        }
        if (count > 3)
            win = true;
        count = 1;
        ypos = savedyPos;
        xpos = lastPosition;
        // unten
        while (!win && count < 4) {
            ypos--;
            if (ypos < 0)
                break;
            if (Field[xpos][5 - ypos] == TEAM) {
                count++;
            } else
                break;
        }

        if (count > 3)
            win = true;
        return win;
    }
}
