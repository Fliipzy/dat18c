package blacksquare.pieces;

import blacksquare.Move;
import blacksquare.Position;

public abstract class Piece 
{
    protected Move[] moves;
    protected Position position;
    protected boolean colorShift;

    public abstract Move[] getMoves();
    public abstract boolean getColorShift();

    public Piece(int x, int y) 
    {
        this.position = new Position(x, y);
        this.moves = getMoves();
        this.colorShift = getColorShift();
    }

    public Position getPosition()
    {
        return position;
    }

    public void setPosition(int x, int y)
    {
        position.setPosition(x, y);
    }
}