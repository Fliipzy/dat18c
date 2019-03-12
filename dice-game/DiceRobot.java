public class DiceRobot 
{
    private String name;

    public DiceRobot(String name) 
    {
        this.name = name;
    }

    public boolean shouldRoll(int playerTotalSum, int playerRunningSum, int opponentTotalSum)
    {
        if (playerTotalSum + playerRunningSum >= 100) 
        {
            return false;
        }

        if (playerRunningSum >= 40) 
        {
            return false;
        }

        return true;
    }
}