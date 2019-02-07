package bp1a_banko_generator.utilities;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import bp1a_banko_generator.BankoPlate;

public class BankoPlateGenerator 
{
    public static Random random = new Random();

    public static BankoPlate generatePlate()
    {
        int[][] numbers = new int[BankoPlate.ROWS][BankoPlate.COLUMNS];

        List<Integer> availableRows = Arrays.asList(0,1,2);
        for (int i = 0; i < 3; i++) 
        {
            Collections.shuffle(availableRows);
            for (int j = 0; j < 3; j++) 
            {
                int index = ((j + 1) + (i * 3))-1;
                if (index == 0) {
                    numbers[availableRows.get(j)][index] = random.nextInt(9)+1;
                    continue;
                }
                numbers[availableRows.get(j)][index] = random.nextInt(9) + (index) * 10;
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) 
            {
                int rndColumn = random.nextInt(BankoPlate.COLUMNS);
                while (numbers[i][rndColumn] != 0)
                {
                    rndColumn = random.nextInt(BankoPlate.COLUMNS);
                }

                int rndNum = random.nextInt(9) + 1 + rndColumn*10;
                while (
                    numbers[0][rndColumn] == rndNum ||
                    numbers[1][rndColumn] == rndNum || 
                    numbers[2][rndColumn] == rndNum) 
                {
                    rndNum = random.nextInt(9) + 1 + rndColumn*10;
                }
                numbers[i][rndColumn] = rndNum;
            }
        }

        return new BankoPlate(numbers);
    }
}