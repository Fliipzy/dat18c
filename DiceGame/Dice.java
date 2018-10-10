import java.util.Random;

public class Dice
{
    private static Random random = new Random();
    private int sides;
    
    public int getSides() { return sides; }
    
    public Dice(int sides) 
    {
        this.sides = sides;
    }

    public int Roll()
    {
        //nextInt(bounds : int) randomly returns an integer from 0 (inclusive) to bounds (exclusive). (0-(bounds-1))
        //That's why we add 1
        return random.nextInt(sides)+1;
    }
    
}