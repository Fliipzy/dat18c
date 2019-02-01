package blacksquare.pieces;

import blacksquare.Move;

public class Knight extends Piece {

    public Knight(int x, int y) {
        super(x,y);
    }

    @Override
    public Move[] getMoves() {
        return new Move[] {
            new Move(1, 2, true),
            new Move(1, -2, true),
            new Move(-1, 2, true),
            new Move(-1, -2, true),
            new Move(2, 1, true),
            new Move(2, -1, true),
            new Move(-2, 1, true),
            new Move(-2, -1, true)
        };
    }

    @Override
    public boolean getColorShift() {
        return true;
    }

    
}