package src;

public class AI {
    private int MAXDEPTH = 6;
    int depthiterator = 2*MAXDEPTH;

    private Canvas canvas;
    private int[] clmScore = {-200,-200,-200,-200,-200,-200,-200};

    public AI(Canvas c) {
        canvas = c;
    }

    public int getNextMove(Spielfeld Field) {
        MAXDEPTH=(int)depthiterator++/2;
        System.out.println("Maxdepth: " + MAXDEPTH + "\n-------------------");
        return getBestMove(Field, 0).Move;
    }

    private Move getBestMove(Spielfeld mSpielfeld, int depth) {
        Spielfeld[] SpielFeldListe = new Spielfeld[7];
        Move bestMove = new Move(-1, -200);
        if (depth > MAXDEPTH)
            return new Move(-1, 0);
        for (int i = 0; i < 7; i++) {
            if (mSpielfeld.getClmstate()[i] < 6) {
                Spielfeld help = mSpielfeld.getNextFeld(i, 2);
                if (help.testSieger(i))
                    return new Move(i, 100);
                SpielFeldListe[i]=help;
            } else {
                SpielFeldListe[i]=null;
            }
        }
        for (int i = 0; i < 7; i++) {
            Spielfeld help = SpielFeldListe[i];
            if (help != null) {
                Move thismove = getWorstMove(help, depth + 1);
                if (depth == 0) {
                    thismove.Move = i;
                    thismove.print("Best" + i);
                    clmScore[i]=thismove.Score;
                }
                if (thismove.Score > bestMove.Score)
                    bestMove = thismove;
            }
        }
        if (depth==0) canvas.setclmScore(clmScore);
        bestMove.Score = (int) (bestMove.Score / 2);
        return bestMove;
    }

    private Move getWorstMove(Spielfeld mSpielfeld, int depth) {
        Spielfeld[] SpielFeldListe = new Spielfeld[7];
        Move worstMove = new Move(-1, 200);
        if (depth > MAXDEPTH)
            return new Move(-1, 0);
        for (int i = 0; i < 7; i++) {
            if (mSpielfeld.getClmstate()[i] < 6) {
                Spielfeld help = mSpielfeld.getNextFeld(i, 1);
                if (help.testSieger(i))
                    return new Move(i, -100);
                SpielFeldListe[i]=help;
            } else {
                SpielFeldListe[i]=null;
            }
        }

        for (int i = 0; i < 7; i++) {
            Spielfeld help = SpielFeldListe[i];
            if (help != null) {
                Move thismove = getBestMove(help, depth + 1);
                if (thismove.Score < worstMove.Score)
                    worstMove = thismove;
            }
        }
        return worstMove;
    }

    public class Move {
        public int Move, Score;

        public Move(int Move, int Score) {
            this.Move = Move;
            this.Score = Score;
        }

        public void print(String name) {
            System.out.println(this.Move + ": " + this.Score);
        }
    }
}
