import java.util.Random;

public class Dice 
{
    private static Random random = new Random();

    private int sides;

    public Dice(int sides) 
    {
        this.sides = sides;
    }

    public int getSides()
    {
        return sides;
    }

    public int roll()
    {
        return random.nextInt(sides)+1;
    }

}