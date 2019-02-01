package rubbduckrace;

import java.util.Random;
import java.util.ArrayList;

import rubbduckrace.utilities.Queue;

public final class Program {

    private static Random random = new Random();
    public static void main(String[] args) 
    {
        System.out.println("Preparing duck race!");
        startRace(10);
        
    }

    public static void startRace(int nQueues)
    {
        ArrayList<Queue<Duck>> contestants = new ArrayList<Queue<Duck>>();

        for (int i = 0; i < nQueues; i++) {
            contestants.add(new Queue<Duck>());
            for (int j = 0; j < 10; j++) {
                contestants.get(i).add(new Duck((i*10)+j + " duck"));
            }
        }

        System.out.println("Race has begun!");
        Duck winner = whoWillWin(contestants);

        System.out.println("The winner of the race was " + winner.getName());
    }

    private static Duck whoWillWin(ArrayList<Queue<Duck>> contestants)
    {
        int cSize = contestants.size();

        if (cSize == 1) {
            return contestants.get(0).poll();
        }

        ArrayList<Queue<Duck>> nextContestants = new ArrayList<Queue<Duck>>();

        for (int i = 0; i < cSize-1; i++) {
            nextContestants.add(new Queue<Duck>());
        }

        for (int i = 0; i < (cSize-1)*10; i++) 
        {   
            int randomQ = random.nextInt(cSize);
            int randomNextQ = random.nextInt(cSize-1);

            while (!(contestants.get(randomQ).size() > 0)) {
                randomQ = random.nextInt(cSize);
            }
            while (!(nextContestants.get(randomNextQ).size() < 10)) {
                randomNextQ = random.nextInt(cSize-1);
            }

            nextContestants.get(randomNextQ).add(contestants.get(randomQ).poll());
        }
        return whoWillWin(nextContestants);
    }

}
