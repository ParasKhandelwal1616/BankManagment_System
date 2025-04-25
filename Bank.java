import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Bank {
    private Map<String, Account> accounts;
    private List<Transaction> transactions;
    
    public Bank() {
        accounts = new HashMap<>();
        transactions = new ArrayList<>();
    }
    
    public Account createSavingsAccount(String ownerName, double initialDeposit, double interestRate) {
        SavingsAccount account = new SavingsAccount(ownerName, initialDeposit, interestRate);
        accounts.put(account.getAccountNumber(), account);
        recordTransaction(account.getAccountNumber(), "OPEN", initialDeposit);
        return account;
    }
    
    public Account createCheckingAccount(String ownerName, double initialDeposit, double overdraftLimit) {
        CheckingAccount account = new CheckingAccount(ownerName, initialDeposit, overdraftLimit);
        accounts.put(account.getAccountNumber(), account);
        recordTransaction(account.getAccountNumber(), "OPEN", initialDeposit);
        return account;
    }
    
    public void deposit(String accountNumber, double amount) {
        Account account = findAccount(accountNumber);
        account.deposit(amount);
        recordTransaction(accountNumber, "DEPOSIT", amount);
    }
    
    public void withdraw(String accountNumber, double amount) {
        Account account = findAccount(accountNumber);
        account.withdraw(amount);
        recordTransaction(accountNumber, "WITHDRAW", amount);
    }
    
    public double checkBalance(String accountNumber) {
        Account account = findAccount(accountNumber);
        return account.getBalance();
    }
    
    public void displayAccountInfo(String accountNumber) {
        Account account = findAccount(accountNumber);
        account.displayAccountInfo();
    }
    
    public void addInterest(String accountNumber) {
        Account account = findAccount(accountNumber);
        if (account instanceof SavingsAccount) {
            ((SavingsAccount) account).addInterest();
            recordTransaction(accountNumber, "INTEREST", 0);
        } else {
            throw new IllegalArgumentException("Interest can only be added to savings accounts");
        }
    }
    
    public void listAllAccounts() {
        System.out.println("\n==== ALL ACCOUNTS ====");
        for (Account account : accounts.values()) {
            System.out.println(account.getAccountNumber() + " - " + 
                              account.getOwnerName() + " - $" + 
                              account.getBalance());
        }
        System.out.println("=====================\n");
    }
    
    public void showTransactionHistory(String accountNumber) {
        findAccount(accountNumber); // Verify account exists
        
        System.out.println("\n==== TRANSACTION HISTORY FOR " + accountNumber + " ====");
        for (Transaction transaction : transactions) {
            if (transaction.toString().contains(accountNumber)) {
                System.out.println(transaction);
            }
        }
        System.out.println("===========================================\n");
    }
    
    private Account findAccount(String accountNumber) {
        Account account = accounts.get(accountNumber);
        if (account == null) {
            throw new IllegalArgumentException("Account not found: " + accountNumber);
        }
        return account;
    }
    
    private void recordTransaction(String accountNumber, String type, double amount) {
        Transaction transaction = new Transaction(accountNumber, type, amount);
        transactions.add(transaction);
    }
}
