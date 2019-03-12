package chess_pathfinding.chess.pieces;

import chess_pathfinding.chess.Move;
import chess_pathfinding.util.Position;

public abstract class Piece 
{
    protected Position position;

    public Piece(Position position) 
    {
        this.position = position;
    }

    public abstract String getName();
    public abstract Move[] getMoves();

    public Position getPosition() 
    {
        return position;
    }

    public void setPosition(Position position) 
    {
        this.position = position;
    }
    
}