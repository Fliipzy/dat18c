public class Opgave8
{
    public static void main(String[] args) 
    {
        BankAccount acc1 = new BankAccount("Fred");
        BankAccount acc2 = new BankAccount("Miracles");

        acc1.deposit(200);
        acc2.deposit(100);

        acc1.transfer(acc2, 50);

        System.out.println(acc1 + "\n" + acc2);
    }    

    public static class BankAccount 
    {
        //Constants
        private static final double TRANSFER_FEE = 5;

        //fields
        private String name;
        private double balance;
        private double transactionFee = 0.00;

        //Constructor
        public BankAccount(String name)
        {
            this.name = name;
        }

        //Getters & setters
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public double getBalance() { return balance; }

        public double getTransactionFee() { return transactionFee; }
        public void setTransactionFee(double transactionFee) { this.transactionFee = transactionFee; }

        //Methods
        public void deposit(double amount)
        {
            balance = balance + amount;
        }

        public void withdraw(double amount)
        {
            double newBalance = balance - (amount + transactionFee);

            if (newBalance >= 0) 
            {
                balance = newBalance;
            } 
            else
            {
                System.out.println("Insufficient amounts to do that!");
            }
        }

        public void transfer(BankAccount account, double amount)
        {
            //If balance is less than $5
            if (balance < 5) 
            {
                System.out.println("Insufficient account balance to transfer $" + amount);    
            }
            //If balance is less than amount + $5
            else if (balance < amount + TRANSFER_FEE) 
            {
                account.deposit(balance);
                balance = 0;
            } 
            //If balance is more than or equal to amount + $5
            else
            {
                account.deposit(amount);
                balance -= amount + TRANSFER_FEE;
            }
        }

        public String toString()
        {
            return name + ", $" + balance; 
        }
    }
}