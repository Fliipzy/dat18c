package edu.dat18c.advanced_data_types;

import java.util.Arrays;

/**
 * IntArrayList
 */
public class IntArrayList 
{
    int c_index = 0; //Current index pointer in array
    int[] array; //The array we store the data in

    /**
     * The constructor of the IntArrayList
     */
    public IntArrayList() 
    {
        array = new int[5];
    }

    /**
     * Returns the indexed integer. Function runs at O(1).
     * @param index 
     * @return The integer stored in the array at position {@code index}
     */
    public int get(int index)
    {
        if (index >= 0 && index <= array.length) 
        {
            return array[index];    
        }
        throw new IndexOutOfBoundsException();
    }

    /**
     * Adds a new integer to the list. If the {@code array} has space for one more it runs at O(1). But if
     * the array needs to grow, it runs at O(n), because we need to iterate through all objects
     * in the array and copy them into a new bigger array.
     * @param value The integer that will be stored in the {@code array}.
     */
    public void add(int value)
    {
        if (hasSpace()) 
        {
            array[c_index] = value;
            c_index++;
            return;
        }
        grow();
    }

    /**
     * Removes integer at the given {@code index} position. The function runs at O(n)
     * because we copy over arrays multiple times.
     * @param index The position in the array that will be deleted.
     */
    public void remove(int index)
    {
        int[] newArray = new int[array.length-1];
        int[] firstHalf = Arrays.copyOfRange(array, 0, index);
        int[] secondHalf = Arrays.copyOfRange(array, index+1, array.length);

        System.arraycopy(firstHalf, 0, newArray, 0, firstHalf.length);
        System.arraycopy(secondHalf, 0, newArray, firstHalf.length, secondHalf.length);

        this.array = newArray; 
    }

    /**
     * @return The size of the array.
     */
    public int size()
    {
        return c_index;
    }

    /**
     * Checks to see if array is filled up.
     * @return The {@code boolean} value, based on whether the array is full or empty.
     */
    private boolean hasSpace()
    {
        if (c_index == array.length) 
        {
            return false;
        }
        return true;
    }

    /**
     * Resizes the array to double the original size. Runs at O(n).
     */
    private void grow()
    {
        int[] newArray = new int[array.length * 2];
        
        for (int i = 0; i < array.length; i++) 
        {
            newArray[i] = array[i];    
        }
        this.array = newArray;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < array.length; i++) 
        {
            if (i == array.length-1) 
            {
                sb.append(array[i]);
                continue;
            }
            sb.append(array[i] + ", ");
        }
        sb.append("]");
        return sb.toString();
    }
}