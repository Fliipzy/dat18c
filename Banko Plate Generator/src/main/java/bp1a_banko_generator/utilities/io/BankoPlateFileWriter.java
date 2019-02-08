package bp1a_banko_generator.utilities.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import bp1a_banko_generator.BankoPlate;

public class BankoPlateFileWriter 
{
    public static final File file = new File("src\\main\\java\\bp1a_banko_generator\\data\\bankoplates.txt");

    public void write(List<BankoPlate> plates)
    {
        try 
        {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            
            String idLine = new String();
            String rows = new String();
            for (int x = 0; x < plates.size(); x++) 
            {
                if (x < plates.size()-1) 
                {
                    idLine += plates.get(x).getId() + ",";
                } 
                else
                {
                    idLine += plates.get(x).getId() + "\n";
                }
                
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 9; j++) {
                        if (j < 8) {
                            rows += plates.get(x).getNumber(i, j) + ",";
                            continue;
                        }
                        rows += plates.get(x).getNumber(i, j);
                    }   
                    rows += "\n";
                }
            }
            writer.write(idLine);
            writer.write(rows);

            writer.close();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
}