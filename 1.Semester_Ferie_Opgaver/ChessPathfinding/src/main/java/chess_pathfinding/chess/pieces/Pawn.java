package chess_pathfinding.chess.pieces;

import chess_pathfinding.chess.Move;
import chess_pathfinding.util.Position;

public class Pawn extends Piece 
{
    public Pawn(Position position) 
    {
        super(position);
    }

    @Override
    public String getName() 
    {
        return "Pawn";
    }

    @Override
    public Move[] getMoves() 
    {
        return new Move[]{
            new Move(new Position( 1,  1), true),
            new Move(new Position( 1, -1), true),
            new Move(new Position(-1,  1), true),
            new Move(new Position(-1, -1), true)
        };
    }
}