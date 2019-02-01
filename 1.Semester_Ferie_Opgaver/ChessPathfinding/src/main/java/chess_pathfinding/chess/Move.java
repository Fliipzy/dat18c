package chess_pathfinding.chess;

import chess_pathfinding.util.Position;

public class Move 
{
    private Position newPosition;
    private Boolean binaryMove;

    public Move(Position newPosition, boolean binaryMove) 
    {
        this.newPosition = newPosition;
        this.binaryMove = binaryMove;
    }

    public Position getNewPosition() 
    {
        return newPosition;
    }

    public Boolean getBinaryMove() 
    {
        return binaryMove;
    }

    @Override
    public String toString()
    {
        return String.format("(Δx = %s, Δy = %s)", newPosition.getX(), newPosition.getY());
    }
}