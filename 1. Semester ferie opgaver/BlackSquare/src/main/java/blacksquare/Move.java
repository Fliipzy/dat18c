package blacksquare;

public class Move 
{
    private boolean binaryMove;
    private int moveX;
    private int moveY;

    public Move(int moveX, int moveY, boolean binaryMove) {
        this.moveX = moveX;
        this.moveY = moveY;
        this.binaryMove = binaryMove;
    }

    public int getMoveX() {
        return moveX;
    }
    public int getMoveY() {
        return moveY;
    }
}