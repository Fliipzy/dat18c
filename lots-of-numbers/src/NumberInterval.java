public class NumberInterval 
{
    private double min;
    private double max;

    public NumberInterval(double min, double max) 
    {
        this.min = min;
        this.max = max;
    }

    public boolean contains(double from, double too)
    {
        //If the numbers from the parameter is inside the min max interval
        if (from >= min && too <= max)
        {
            return true;
        }
        return false;
    }

    public String toString()
    {
        return "Minimum: " + min + ", Maximum: " + max;
    }

    public boolean equals(NumberInterval other)
    {
        //If both NumberInterval objects have the same min and max values
        if (min == other.min && max == other.max) 
        {
            return true;    
        }
        return false;
    }
}