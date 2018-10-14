import java.util.Scanner;

public class Program 
{
    public static void main(String[] args) 
    {     
        Dice dice = new Dice(6);
        DiceRobot robot = new DiceRobot("Dice-O-Tron");

        GameManager gm = new GameManager(dice, robot);

        gm.startGame();
    }

}