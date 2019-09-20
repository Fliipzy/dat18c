package edu.dat18c.generics;

/**
 * Track
 */
abstract public class Track 
{
    private Signal right;
    private Signal left;

    public Track(Signal right, Signal left) 
    {
        this.right = right;
        this.left = left;
    }
    
    abstract Signal getRight();
    abstract Signal getLeft();
}