package src;

public class AI {
    private final int MAXDEPTH = 8;

    public AI() {}

    public int getNextMove(Spielfeld Field) {
        System.out.println("-------------");
        return getBestMove(Field, 0).Move;
    }

    private Move getBestMove(Spielfeld mSpielfeld, int depth) {
        Spielfeld current = new Spielfeld(mSpielfeld);
        Move bestMove = new Move(-1, -200);
        if (depth > MAXDEPTH)
            return new Move(-1, 0);
        for (int i = 0; i < 7; i++) {
            if (current.getClmstate()[i] < 6) {
                Spielfeld help = current.getNextFeld(i, 2);
                if (help.testSieger(i))
                    return new Move(i, 100);
                Move thismove = getWorstMove(help, depth + 1);
                thismove.Move=i;
                if(depth==0) thismove.print("Best"+i);
                
                if (thismove.Score > bestMove.Score)
                bestMove = thismove;
            }
        }
        bestMove.Score=(int)(bestMove.Score/2);
        return bestMove;
    }

    private Move getWorstMove(Spielfeld mSpielfeld, int depth) {
        Spielfeld current = new Spielfeld(mSpielfeld);
        Move bestMove = new Move(3, 200);
        if (depth > MAXDEPTH)
            return new Move(-1, 0);
        for (int i = 0; i < 7; i++) {
            if (current.getClmstate()[i] < 6) {
                Spielfeld help = current.getNextFeld(i, 1);
                if (help.testSieger(i))
                    return new Move(i, -100);
                Move thismove = getBestMove(help, depth + 1);
                thismove.Move=i;
                if (thismove.Score < bestMove.Score)
                bestMove = thismove;
            }
        }
        return bestMove;
    }

    public class Move {
        public int Move, Score;

        public Move(int Move, int Score) {
            this.Move = Move;
            this.Score = Score;
        }
        public void print(String name) {
            System.out.println(this.Move+": " +this.Score);
        }
    }
}
