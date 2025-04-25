public class CheckingAccount extends Account {
    private double overdraftLimit;
    
    public CheckingAccount(String ownerName, double initialDeposit, double overdraftLimit) {
        super(ownerName, initialDeposit);
        this.overdraftLimit = overdraftLimit;
    }
    
    @Override
    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }
        
        if (amount > balance + overdraftLimit) {
            throw new IllegalArgumentException("Amount exceeds overdraft limit");
        }
        
        balance -= amount;
        
        if (balance < 0) {
            System.out.println("Warning: Account overdrawn!");
        }
        
        System.out.println("Withdrawn: $" + amount);
        System.out.println("New Balance: $" + balance);
    }
    
    @Override
    public void displayAccountInfo() {
        System.out.println("\n==== CHECKING ACCOUNT INFORMATION ====");
        System.out.println(super.toString());
        System.out.println("Overdraft Limit: $" + overdraftLimit);
        System.out.println("======================================\n");
    }
}
