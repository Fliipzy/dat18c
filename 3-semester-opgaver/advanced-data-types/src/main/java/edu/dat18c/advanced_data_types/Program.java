package edu.dat18c.advanced_data_types;

public class Program 
{
    public static void main(String[] args )
    {
        IntArrayList intArrayList = new IntArrayList();

        System.out.println("Size of the array: " + intArrayList.size());

        System.out.println("Adding new Integer objects..");

        intArrayList.add(1);
        intArrayList.add(2);
        intArrayList.add(3);
        intArrayList.add(4);
        intArrayList.add(5);

        System.out.println("New size of the array: " + intArrayList.size());

        System.out.println(intArrayList.toString());

        System.out.println("Removing index 2 from list..");
        intArrayList.remove(2);
        System.out.println(intArrayList.toString());
    }
}
