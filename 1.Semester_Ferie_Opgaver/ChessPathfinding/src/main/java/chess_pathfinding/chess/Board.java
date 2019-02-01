package chess_pathfinding.chess;

import chess_pathfinding.util.Position;

public class Board 
{
    private static final byte BOARD_LENGTH = 8;

    public Board() 
    {
        
    }

    public static TileColor tileColorFromPos(Position pos)
    {
        return (pos.getX() + pos.getY()) % 2 == 0 ? TileColor.BLACK : TileColor.WHITE;
    }

    private static enum TileColor
    {
        BLACK,
        WHITE
    }
}