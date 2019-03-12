package blacksquare;

import java.util.List;

import blacksquare.pieces.Piece;

public class Board 
{
    private static final byte CHESSBOARD_LENGTH = 8;
    private static final byte GOAL = 2;
    private static final byte START = 1;
    private byte[][] tiles;

    public Board() 
    {
        tiles = new byte[CHESSBOARD_LENGTH][CHESSBOARD_LENGTH];
    }

    public boolean pathExists(Piece piece, Position startPos, Position endPosition, List<Position> refList)
    {
        //If startPos and endPos are different tile colors AND piece is not able to shift tile color
        //there won't be a path 
        if ((isBlackTile(startPos) && !isBlackTile(endPosition)) 
        || (!isBlackTile(startPos) && isBlackTile(endPosition))
        && !piece.getColorShift()) 
        {
            return false;
        }
        
        tiles[startPos.getX()][startPos.getY()] = START;
        tiles[endPosition.getX()][endPosition.getY()] = GOAL;
        
        return true;
    }

    private boolean isBlackTile(Position pos)
    {
        return (pos.getX() % 2) + (pos.getY() % 2) == 1 ? false : true; 
    }
    
    
}