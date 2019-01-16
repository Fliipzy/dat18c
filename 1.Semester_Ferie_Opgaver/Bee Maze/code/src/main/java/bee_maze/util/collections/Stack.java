package bee_maze.util.collections;

import java.util.Arrays;

public class Stack<T> 
{    
    private Object[] array;
    private int newestIndex;

    public Stack() 
    {
        array = new Object[10];
        newestIndex = -1;
    }

    @SuppressWarnings("unchecked")
    public T peek()
    {
        return (T)array[newestIndex];
    }

    public void push(T item)
    {
        if (newestIndex == array.length-1) 
        {
            Object[] temp = new Object[array.length * 2];
            temp = Arrays.copyOf(array, array.length + 1);
            array = temp;   
        }
        array[newestIndex+1] = item;
        newestIndex++;        
    }

    @SuppressWarnings("unchecked")
    public T pop()
    {
        if (newestIndex >= 0) 
        {
            T item = (T)array[newestIndex];
            array[newestIndex] = null;
            newestIndex--;

            return item;
        }
        return null;
    }

    public int getLength()
    {
        return newestIndex+1;
    }
}