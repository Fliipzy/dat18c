package bp1a_banko_generator;

public class BankoPlate 
{
    public static final int ROWS = 3;
    public static final int COLUMNS = 9;

    private int[][] numbers;
    private String id;

    public BankoPlate(int[][] numbers) 
    {
        createUniqueID();
        this.numbers = numbers;
    }

    public int getNumber(int row, int column)
    {
        return numbers[row][column];
    }

    public int[][] getNumbers() 
    {
        return numbers;
    }

    public String getId() 
    {
        return id;
    }

    private void createUniqueID()
    {
        id = Integer.toHexString(this.hashCode());
    }

    @Override
    public String toString()
    {
        String m = new String();

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) 
            {
                int n = numbers[i][j];
                if (n != 0) 
                {
                    m += String.format("[%s]\t", n);
                } 
                else
                {
                    m += "[  ]\t";
                }
            }
            m += "\n";
        }

        return m;
    }

    @Override
    public boolean equals(Object other)
    {
        BankoPlate otherp = (BankoPlate)other;
        return otherp.getId().equals(this.getId());
    }

}