package bp1a_banko_generator;

import java.util.Arrays;

import bp1a_banko_generator.utilities.BankoPlateGenerator;
import bp1a_banko_generator.utilities.io.BankoPlateFileWriter;

public final class Program 
{
    public static void main(String[] args) 
    {
        BankoPlate b1 = BankoPlateGenerator.generatePlate();
        
        BankoPlateFileWriter writer = new BankoPlateFileWriter();

        writer.write(Arrays.asList(b1));
    }
}
