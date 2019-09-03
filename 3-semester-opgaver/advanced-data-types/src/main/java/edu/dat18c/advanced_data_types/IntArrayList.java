package edu.dat18c.advanced_data_types;

import java.util.Arrays;

/**
 * IntArrayList
 */
public class IntArrayList 
{
    int c_index = 0;
    int[] array;

    public IntArrayList() 
    {
        array = new int[5];
    }

    public IntArrayList(int initSize)
    {
        array = new int[initSize];
    }

    public int get(int index)
    {
        if (index > 0 && index <= array.length) 
        {
            return array[index];    
        }
        throw new IndexOutOfBoundsException();
    }

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

    public void remove(int index)
    {
        int[] newArray = new int[array.length-1];
        int[] firstHalf = Arrays.copyOfRange(array, 0, index);
        int[] secondHalf = Arrays.copyOfRange(array, index+1, array.length);

        System.arraycopy(firstHalf, 0, newArray, 0, firstHalf.length);
        System.arraycopy(secondHalf, 0, newArray, firstHalf.length, secondHalf.length);

        this.array = newArray; 
    }

    public int size()
    {
        return c_index;
    }

    private boolean hasSpace()
    {
        if (c_index == array.length) 
        {
            return false;
        }
        return true;
    }

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