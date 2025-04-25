import java.util.Scanner;

public class BankManagementSystem {
    private static Bank bank = new Bank();
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        boolean exit = false;
        
        System.out.println("===== BANK ACCOUNT MANAGEMENT SYSTEM =====");
        
        while (!exit) {
            System.out.println("\nPlease select an option:");
            System.out.println("1. Create a new account");
            System.out.println("2. Deposit money");
            System.out.println("3. Withdraw money");
            System.out.println("4. Check balance");
            System.out.println("5. Display account information");
            System.out.println("6. Add interest to savings account");
            System.out.println("7. List all accounts");
            System.out.println("8. Show transaction history");
            System.out.println("9. Exit");
            
            System.out.print("\nEnter your choice (1-9): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear buffer
            
            try {
                switch (choice) {
                    case 1:
                        createAccount();
                        break;
                    case 2:
                        depositMoney();
                        break;
                    case 3:
                        withdrawMoney();
                        break;
                    case 4:
                        checkBalance();
                        break;
                    case 5:
                        displayAccountInfo();
                        break;
                    case 6:
                        addInterest();
                        break;
                    case 7:
                        bank.listAllAccounts();
                        break;
                    case 8:
                        showTransactionHistory();
                        break;
                    case 9:
                        exit = true;
                        System.out.println("Thank you for using the Bank Management System!");
                        break;
                    default:
                        System.out.println("Invalid option! Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        
        scanner.close();
    }
    
    private static void createAccount() {
        System.out.println("\n----- CREATE A NEW ACCOUNT -----");
        System.out.println("1. Savings Account");
        System.out.println("2. Checking Account");
        System.out.print("Select account type (1-2): ");
        int type = scanner.nextInt();
        scanner.nextLine(); // Clear buffer
        
        System.out.print("Enter owner name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter initial deposit amount: $");
        double initialDeposit = scanner.nextDouble();
        
        Account newAccount = null;
        
        if (type == 1) {
            System.out.print("Enter interest rate (%): ");
            double interestRate = scanner.nextDouble();
            newAccount = bank.createSavingsAccount(name, initialDeposit, interestRate);
        } else if (type == 2) {
            System.out.print("Enter overdraft limit: $");
            double overdraftLimit = scanner.nextDouble();
            newAccount = bank.createCheckingAccount(name, initialDeposit, overdraftLimit);
        } else {
            System.out.println("Invalid account type!");
            return;
        }
        
        System.out.println("\nAccount created successfully!");
        System.out.println("Your account number is: " + newAccount.getAccountNumber());
    }
    
    private static void depositMoney() {
        System.out.println("\n----- DEPOSIT MONEY -----");
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        
        System.out.print("Enter deposit amount: $");
        double amount = scanner.nextDouble();
        
        bank.deposit(accountNumber, amount);
    }
    
    private static void withdrawMoney() {
        System.out.println("\n----- WITHDRAW MONEY -----");
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        
        System.out.print("Enter withdrawal amount: $");
        double amount = scanner.nextDouble();
        
        bank.withdraw(accountNumber, amount);
    }
    
    private static void checkBalance() {
        System.out.println("\n----- CHECK BALANCE -----");
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        
        double balance = bank.checkBalance(accountNumber);
        System.out.println("Current balance: $" + balance);
    }
    
    private static void displayAccountInfo() {
        System.out.println("\n----- ACCOUNT INFORMATION -----");
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        
        bank.displayAccountInfo(accountNumber);
    }
    
    private static void addInterest() {
        System.out.println("\n----- ADD INTEREST -----");
        System.out.print("Enter savings account number: ");
        String accountNumber = scanner.nextLine();
        
        bank.addInterest(accountNumber);
    }
    
    private static void showTransactionHistory() {
        System.out.println("\n----- TRANSACTION HISTORY -----");
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        
        bank.showTransactionHistory(accountNumber);
    }
}
