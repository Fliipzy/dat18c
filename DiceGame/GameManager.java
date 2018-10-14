import java.util.Scanner;

public class GameManager 
{
    private Scanner input;
    private DiceRobot robot;
    private Dice dice;

    private int playerTotalSum;
    private int opponentTotalSum;
    private int playerRunningSum;

    public GameManager(Dice dice, DiceRobot robot) 
    {
        input = new Scanner(System.in);

        this.dice = dice;
        this.robot = robot;
        initializeNewGame();
    }

    private void initializeNewGame()
    {
        playerTotalSum = 0;
        playerRunningSum = 0;
        opponentTotalSum = 0;
    }

    public void startGame()
    {
        if (!isPlayerStarting()) 
        {
            MessageService.log("How many points did your opponent score?");
            int score = input.nextInt();

            opponentTotalSum += score;
        } 

        //Game loop
        while (true) 
        {
            //Players turn
            startPlayerTurn();

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
        
        String winnerText = playerTotalSum > opponentTotalSum ? "You" : "Your opponent";
        int winnerSum = playerTotalSum > opponentTotalSum ? playerTotalSum : opponentTotalSum;

        MessageService.log("[" + winnerText + " won with " + winnerSum + " points!]");
    }

    private boolean isPlayerStarting()
    {
        MessageService.log("Is it your turn to throw first? y/n");

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
                MessageService.log("You must enter either y or n!");
            }
        }
    }

    private void startPlayerTurn()
    {
        //reset running sum
        playerRunningSum = 0;

        while (true) 
            {
                //Calculating risk vs reward
                if (robot.shouldRoll(playerTotalSum, playerRunningSum, opponentTotalSum)) 
                {
                    int lastRoll = dice.roll();

                    if (lastRoll == 1) 
                    {
                        MessageService.log("bad luck, you rolled one!");
                        playerRunningSum = 0;
                        break;
                    } 
                    else
                    {
                        MessageService.log("You rolled a " + lastRoll + "!");
                        playerRunningSum += lastRoll;
                        MessageService.log("Your running sum is now " + playerRunningSum);
                    }
                }
                else
                {
                    MessageService.log("Dice-o-tron has decided to stop rolling the dice and pass the turn!");
                    break;
                }
            }
            playerTotalSum += playerRunningSum;
    }

    private void startOpponentTurn()
    {
        MessageService.log("How many points did your opponent score?");
        int score = input.nextInt();

        opponentTotalSum += score;
    }

    private void logTurn()
    {
        MessageService.log("");
        MessageService.log("[You have: " + playerTotalSum + " points, Your opponent have: " + opponentTotalSum + " points!]");
        MessageService.log("");
    }
}