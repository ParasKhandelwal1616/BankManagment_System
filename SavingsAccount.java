public class SavingsAccount extends Account {
    private double interestRate;
    private static final double MIN_BALANCE = 100.0;
    
    public SavingsAccount(String ownerName, double initialDeposit, double interestRate) {
        super(ownerName, initialDeposit);
        if (initialDeposit < MIN_BALANCE) {
            throw new IllegalArgumentException("Minimum initial deposit for savings account is $" + MIN_BALANCE);
        }
        this.interestRate = interestRate;
    }
    
    public void addInterest() {
        double interest = balance * interestRate / 100;
        balance += interest;
        System.out.println("Interest added: $" + interest);
        System.out.println("New Balance: $" + balance);
    }
    
    @Override
    public void withdraw(double amount) {
        if (balance - amount < MIN_BALANCE) {
            throw new IllegalArgumentException("Withdrawal would break minimum balance requirement of $" + MIN_BALANCE);
        }
        super.withdraw(amount);
    }
    
    @Override
    public void displayAccountInfo() {
        System.out.println("\n==== SAVINGS ACCOUNT INFORMATION ====");
        System.out.println(super.toString());
        System.out.println("Interest Rate: " + interestRate + "%");
        System.out.println("Minimum Balance: $" + MIN_BALANCE);
        System.out.println("=====================================\n");
    }
}
