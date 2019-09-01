package edu.dat18c.fitness_exercise;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * PersonFileReader
 */
public class PersonFileReader 
{
    private File file;

    public PersonFileReader(File file) 
    {
        this.file = file;
    }

    public List<Person> readFile()
    {
        List<Person> persons = new ArrayList<Person>();

        try 
        {
            try(BufferedReader reader = new BufferedReader(new FileReader(file)))
            {
                String line;
                while ((line = reader.readLine()) != null) 
                {
                    persons.add(parsePerson(line));
                }
            }
        } 
        catch (IOException e) 
        {
            System.out.println(e.toString());
        }
        return persons;
    }

    private Person parsePerson(String str)
    {
        String[] pData = str.split(",");
        
        if (pData.length == 2) 
        {
            return new Person(pData[0], pData[1]);    
        }
        return null;
    }
}