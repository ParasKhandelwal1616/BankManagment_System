public class Transaction {
    private String accountNumber;
    private String type;
    private double amount;
    private String timestamp;
    
    public Transaction(String accountNumber, String type, double amount) {
        this.accountNumber = accountNumber;
        this.type = type;
        this.amount = amount;
        this.timestamp = java.time.LocalDateTime.now().toString();
    }
    
    @Override
    public String toString() {
        return "Transaction [Account: " + accountNumber + 
               ", Type: " + type + 
               ", Amount: $" + amount + 
               ", Time: " + timestamp + "]";
    }
}
