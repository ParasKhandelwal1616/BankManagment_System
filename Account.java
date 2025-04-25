public abstract class Account {
    private String accountNumber;
    private String ownerName;
    protected double balance;
    private static int accountCounter = 10000;
    
    public Account(String ownerName, double initialDeposit) {
        this.accountNumber = generateAccountNumber();
        this.ownerName = ownerName;
        this.balance = initialDeposit;
    }
    
    private String generateAccountNumber() {
        return "ACCT" + (++accountCounter);
    }
    
    public String getAccountNumber() {
        return accountNumber;
    }
    
    public String getOwnerName() {
        return ownerName;
    }
    
    public double getBalance() {
        return balance;
    }
    
    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        balance += amount;
        System.out.println("Deposited: $" + amount);
        System.out.println("New Balance: $" + balance);
    }
    
    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient funds");
        }
        balance -= amount;
        System.out.println("Withdrawn: $" + amount);
        System.out.println("New Balance: $" + balance);
    }
    
    public abstract void displayAccountInfo();
    
    @Override
    public String toString() {
        return "Account Number: " + accountNumber + 
               "\nOwner: " + ownerName + 
               "\nBalance: $" + balance;
    }
}
