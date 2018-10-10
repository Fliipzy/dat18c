import java.util.Scanner;

import javax.lang.model.util.ElementScanner6;

public class Program 
{
    static Scanner input = new Scanner(System.in);

    static int playerTotalSum = 0;
    static int opponentTotalSum = 0;
    static int playerRunningSum = 0;

    public static void main(String[] args) 
    {     
        Dice dice = new Dice(6);

        if (!isPlayerStarting()) 
        {
            log("How many points did your opponent score?");
            int score = input.nextInt();

            opponentTotalSum += score;
        } 

        //Game loop
        while (true) 
        {
            //reset
            

            //Players turn
            startPlayerTurns();

            //Opponents turn
            startOpponentTurn();

            //Exit statement
            if (playerTotalSum >= 100 || opponentTotalSum >= 100) 
            {
                break;
            }

            //Display round
            logTurn();
        }

        //Checks to see who won and then displays the winning text!
        
        String winner = playerTotalSum > opponentTotalSum ? "You" : "Your opponent";
        int winnerSum = playerTotalSum > opponentTotalSum ? playerTotalSum : opponentTotalSum;

        log("[" + winner + " won with " + winnerSum + " points!]");
    }

    public static void logTurn()
    {
        log("");
        log("[You have: " + playerTotalSum + " points, Your opponent have: " + opponentTotalSum + " points!]");
        log("");
    }

    public static void startOpponentTurn()
    {
        log("How many points did your opponent score?");
        int score = input.nextInt();

        opponentTotalSum += score;
    }

    public static startPlayersTurn()
    {
        //reset running sum
        playerRunningSum = 0;

        while (true) 
            {
                //Calculating risk vs reward
                if (shouldRoll()) 
                {
                    int lastRoll = dice.roll();

                    if (lastRoll == 1) 
                    {
                        log("bad luck, you rolled a one!");
                        playerRunningSum = 0;
                        break;
                    } 
                    else
                    {
                        log("You rolled a " + lastRoll + "!");
                        playerRunningSum += lastRoll;
                        log("Your running sum is now " + playerRunningSum);
                    }
                }
                else
                {
                    log("Dice-o-tron has decided to stop rolling the dice and pass the turn!");
                    break;
                }
            }
            playerTotalSum += playerRunningSum;
    }

    public static boolean shouldRoll()
    {
        if (playerTotalSum + playerRunningSum >= 100) 
        {
            return false;
        }

        if (playerRunningSum >= 40) 
        {
            return false;
        }

        if ((100 - opponentTotalSum) <= 30 && (100 - playerTotalSum) >= 60)
        {
            return true;
        }
        
        return true;
    }

    public static boolean isPlayerStarting()
    {
        log("Is it your turn to throw first? y/n");

        while (true) 
        {
            String answer = input.next();

            if (answer.equals("y")) 
            {
                return true;
            } 
            else if (answer.equals("n"))
            {
                return false;
            }   
            else 
            {
                log("You must enter either y or n!");
            }
        }
    }

    public static void log(String message)
    {
        System.out.println(message);
    }
}