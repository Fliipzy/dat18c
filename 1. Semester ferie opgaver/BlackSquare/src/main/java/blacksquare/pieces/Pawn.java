package blacksquare.pieces;

import blacksquare.Move;

public class Pawn extends Piece 
{
    public Pawn(int x, int y) {
        super(x, y);
    }

    @Override
    public Move[] getMoves() 
    {
        return new Move[] {
            new Move(1,  1, true),
            new Move(-1, 1, true),
            new Move(1, -1, true),
            new Move(-1, -1, true)
        };
    }

    @Override
    public boolean getColorShift() {
        return false;
    }
}