package edu.dat18c.fitness_exercise;

/**
 * Member
 */
public class Member extends Person 
{
    private boolean isBasic;

    public Member(String name, String cpr, boolean isBasic) 
    {
        super(name, cpr);
        this.isBasic = isBasic;
    }

    public String getMembershipType()
    {
        return isBasic ? "Basic" : "Full";
    }

    public int getMonthlyFee()
    {
        return isBasic ? 199 : 299;
    }
}