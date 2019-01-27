package rubbduckrace.utilities;

import java.util.Arrays;

public class Queue<T>
{
    private Object[] array;
    private int front = 0;
    private int cIndex = -1;
    
    public Queue() {
        array = new Object[10];
    }

    public boolean add(T t)
    {
        if (cIndex == array.length-1-front) 
        {
            Object[] tempArray = new Object[array.length * 2];
            System.arraycopy(array, front, tempArray, 0, array.length-front);
            array = tempArray;
            front = 0;
        }
        array[front+cIndex+1] = t;
        cIndex++;

        return true;
    }

    @SuppressWarnings("unchecked")
    public T peek()
    {
        if (size() > 0) {
            return (T)array[front];
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public T poll()
    {
        if (size() > 0) {
            T item = (T)array[front];
            array[front++] = null;
            cIndex--;

            return item;
        }
        return null;
    }

    public int size()
    {
        return cIndex+1;
    }
}