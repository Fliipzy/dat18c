package blacksquare;

import java.util.Random;

public final class Program 
{
    public static void main(String[] args) 
    {
        Random random = new Random();
        random.ints().limit(10).forEach(System.out::println);
    }
}
