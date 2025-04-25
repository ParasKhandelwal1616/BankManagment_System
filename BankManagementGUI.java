import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BankManagementGUI extends JFrame {
    private Bank bank;
    private JPanel mainPanel, cardPanel;
    private CardLayout cardLayout;
    
    // Main menu components
    private JButton createAccountBtn, depositBtn, withdrawBtn, checkBalanceBtn;
    private JButton displayInfoBtn, addInterestBtn, listAccountsBtn, transactionHistoryBtn;
    
    // Create account panel components
    private JPanel createAccountPanel;
    private JComboBox<String> accountTypeCombo;
    private JTextField nameField, initialDepositField, rateOrLimitField;
    private JLabel rateOrLimitLabel;
    
    // Transaction panel components
    private JPanel transactionPanel;
    private JTextField accountField, amountField;
    private JLabel transactionStatusLabel;
    
    // Display panels
    private JPanel displayPanel;
    private JTextArea displayArea;
    
    public BankManagementGUI() {
        bank = new Bank();
        
        // Setup frame
        setTitle("Bank Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Initialize panels
        mainPanel = new JPanel(new BorderLayout());
        setupCardPanel();
        setupTopPanel();
        
        // Add components to frame
        add(mainPanel);
        
        // Show main menu initially
        cardLayout.show(cardPanel, "main");
    }
    
    private void setupCardPanel() {
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        
        // Setup individual panels
        setupMainMenu();
        setupCreateAccountPanel();
        setupTransactionPanel();
        setupDisplayPanel();
        
        mainPanel.add(cardPanel, BorderLayout.CENTER);
    }
    
    private void setupTopPanel() {
        JPanel topPanel = new JPanel();
        JButton homeButton = new JButton("Home");
        homeButton.addActionListener(e -> cardLayout.show(cardPanel, "main"));
        
        topPanel.add(homeButton);
        mainPanel.add(topPanel, BorderLayout.NORTH);
    }
    
    private void setupMainMenu() {
        JPanel menuPanel = new JPanel(new GridBagLayout());
        menuPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        JLabel titleLabel = new JLabel("BANK ACCOUNT MANAGEMENT SYSTEM");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        
        createAccountBtn = new JButton("Create Account");
        depositBtn = new JButton("Deposit Money");
        withdrawBtn = new JButton("Withdraw Money");
        checkBalanceBtn = new JButton("Check Balance");
        displayInfoBtn = new JButton("Display Account Info");
        addInterestBtn = new JButton("Add Interest");
        listAccountsBtn = new JButton("List All Accounts");
        transactionHistoryBtn = new JButton("Transaction History");
        
        // Add action listeners
        createAccountBtn.addActionListener(e -> cardLayout.show(cardPanel, "create"));
        depositBtn.addActionListener(e -> setupTransactionPanelFor("deposit"));
        withdrawBtn.addActionListener(e -> setupTransactionPanelFor("withdraw"));
        checkBalanceBtn.addActionListener(e -> setupTransactionPanelFor("balance"));
        displayInfoBtn.addActionListener(e -> setupTransactionPanelFor("info"));
        addInterestBtn.addActionListener(e -> setupTransactionPanelFor("interest"));
        listAccountsBtn.addActionListener(e -> displayAllAccounts());
        transactionHistoryBtn.addActionListener(e -> setupTransactionPanelFor("history"));
        
        // Create layout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        
        // Add title
        gbc.gridx = 0;
        gbc.gridy = 0;
        menuPanel.add(titleLabel, gbc);
        
        // Add buttons - first column
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        menuPanel.add(createAccountBtn, gbc);
        
        gbc.gridy = 2;
        menuPanel.add(depositBtn, gbc);
        
        gbc.gridy = 3;
        menuPanel.add(withdrawBtn, gbc);
        
        gbc.gridy = 4;
        menuPanel.add(checkBalanceBtn, gbc);
        
        // Add buttons - second column
        gbc.gridx = 1;
        gbc.gridy = 1;
        menuPanel.add(displayInfoBtn, gbc);
        
        gbc.gridy = 2;
        menuPanel.add(addInterestBtn, gbc);
        
        gbc.gridy = 3;
        menuPanel.add(listAccountsBtn, gbc);
        
        gbc.gridy = 4;
        menuPanel.add(transactionHistoryBtn, gbc);
        
        cardPanel.add(menuPanel, "main");
    }
    
    private void setupCreateAccountPanel() {
        createAccountPanel = new JPanel(new GridBagLayout());
        createAccountPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        JLabel titleLabel = new JLabel("Create New Account");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        
        JLabel typeLabel = new JLabel("Account Type:");
        JLabel nameLabel = new JLabel("Owner Name:");
        JLabel depositLabel = new JLabel("Initial Deposit ($):");
        rateOrLimitLabel = new JLabel("Interest Rate (%):");
        
        accountTypeCombo = new JComboBox<>(new String[]{"Savings Account", "Checking Account"});
        accountTypeCombo.addActionListener(e -> {
            if (accountTypeCombo.getSelectedIndex() == 0) {
                rateOrLimitLabel.setText("Interest Rate (%):");
            } else {
                rateOrLimitLabel.setText("Overdraft Limit ($):");
            }
        });
        
        nameField = new JTextField(20);
        initialDepositField = new JTextField(20);
        rateOrLimitField = new JTextField(20);
        
        JButton createButton = new JButton("Create Account");
        createButton.addActionListener(e -> createNewAccount());
        
        // Layout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        createAccountPanel.add(titleLabel, gbc);
        
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        createAccountPanel.add(typeLabel, gbc);
        gbc.gridx = 1;
        createAccountPanel.add(accountTypeCombo, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        createAccountPanel.add(nameLabel, gbc);
        gbc.gridx = 1;
        createAccountPanel.add(nameField, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        createAccountPanel.add(depositLabel, gbc);
        gbc.gridx = 1;
        createAccountPanel.add(initialDepositField, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 4;
        createAccountPanel.add(rateOrLimitLabel, gbc);
        gbc.gridx = 1;
        createAccountPanel.add(rateOrLimitField, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        createAccountPanel.add(createButton, gbc);
        
        cardPanel.add(createAccountPanel, "create");
    }
    
    private void setupTransactionPanel() {
        transactionPanel = new JPanel(new GridBagLayout());
        transactionPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        JLabel titleLabel = new JLabel("Transaction");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        
        JLabel accountLabel = new JLabel("Account Number:");
        JLabel amountLabel = new JLabel("Amount ($):");
        
        accountField = new JTextField(20);
        amountField = new JTextField(20);
        
        JButton processButton = new JButton("Process");
        transactionStatusLabel = new JLabel("");
        
        // Layout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        transactionPanel.add(titleLabel, gbc);
        
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        transactionPanel.add(accountLabel, gbc);
        gbc.gridx = 1;
        transactionPanel.add(accountField, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        transactionPanel.add(amountLabel, gbc);
        gbc.gridx = 1;
        transactionPanel.add(amountField, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        transactionPanel.add(processButton, gbc);
        
        gbc.gridy = 4;
        transactionPanel.add(transactionStatusLabel, gbc);
        
        cardPanel.add(transactionPanel, "transaction");
    }
    
    private void setupDisplayPanel() {
        displayPanel = new JPanel(new BorderLayout());
        displayPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        
        JScrollPane scrollPane = new JScrollPane(displayArea);
        
        displayPanel.add(scrollPane, BorderLayout.CENTER);
        
        cardPanel.add(displayPanel, "display");
    }
    
    private void createNewAccount() {
        try {
            String name = nameField.getText();
            double initialDeposit = Double.parseDouble(initialDepositField.getText());
            double rateOrLimit = Double.parseDouble(rateOrLimitField.getText());
            
            Account newAccount;
            if (accountTypeCombo.getSelectedIndex() == 0) {
                newAccount = bank.createSavingsAccount(name, initialDeposit, rateOrLimit);
            } else {
                newAccount = bank.createCheckingAccount(name, initialDeposit, rateOrLimit);
            }
            
            // Display success message
            JOptionPane.showMessageDialog(this,
                    "Account created successfully!\nAccount Number: " + newAccount.getAccountNumber(),
                    "Account Created", JOptionPane.INFORMATION_MESSAGE);
            
            // Reset fields
            nameField.setText("");
            initialDepositField.setText("");
            rateOrLimitField.setText("");
            
            // Return to main menu
            cardLayout.show(cardPanel, "main");
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Please enter valid numbers for deposit and rate/limit",
                    "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this,
                    e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void setupTransactionPanelFor(String transactionType) {
        // Configure transaction panel for different operations
        JButton processButton = (JButton) transactionPanel.getComponent(7);
        processButton.removeActionListener(processButton.getActionListeners()[0]);
        
        JLabel titleLabel = (JLabel) transactionPanel.getComponent(0);
        JLabel amountLabel = (JLabel) transactionPanel.getComponent(4);
        amountField.setVisible(true);
        
        switch (transactionType) {
            case "deposit":
                titleLabel.setText("Deposit Money");
                processButton.addActionListener(e -> processDeposit());
                break;
            case "withdraw":
                titleLabel.setText("Withdraw Money");
                processButton.addActionListener(e -> processWithdraw());
                break;
            case "balance":
                titleLabel.setText("Check Balance");
                amountField.setVisible(false);
                processButton.addActionListener(e -> checkBalance());
                break;
            case "info":
                titleLabel.setText("Display Account Information");
                amountField.setVisible(false);
                processButton.addActionListener(e -> displayAccountInfo());
                break;
            case "interest":
                titleLabel.setText("Add Interest");
                amountField.setVisible(false);
                processButton.addActionListener(e -> addInterest());
                break;
            case "history":
                titleLabel.setText("Transaction History");
                amountField.setVisible(false);
                processButton.addActionListener(e -> showTransactionHistory());
                break;
        }
        
        accountField.setText("");
        amountField.setText("");
        transactionStatusLabel.setText("");
        cardLayout.show(cardPanel, "transaction");
    }
    
    private void processDeposit() {
        try {
            String accountNumber = accountField.getText();
            double amount = Double.parseDouble(amountField.getText());
            
            bank.deposit(accountNumber, amount);
            transactionStatusLabel.setText("Deposited: $" + amount + " successfully");
            
        } catch (NumberFormatException e) {
            transactionStatusLabel.setText("Error: Please enter a valid amount");
        } catch (IllegalArgumentException e) {
            transactionStatusLabel.setText("Error: " + e.getMessage());
        }
    }
    
    private void processWithdraw() {
        try {
            String accountNumber = accountField.getText();
            double amount = Double.parseDouble(amountField.getText());
            
            bank.withdraw(accountNumber, amount);
            transactionStatusLabel.setText("Withdrawn: $" + amount + " successfully");
            
        } catch (NumberFormatException e) {
            transactionStatusLabel.setText("Error: Please enter a valid amount");
        } catch (IllegalArgumentException e) {
            transactionStatusLabel.setText("Error: " + e.getMessage());
        }
    }
    
    private void checkBalance() {
        try {
            String accountNumber = accountField.getText();
            double balance = bank.checkBalance(accountNumber);
            
            transactionStatusLabel.setText("Current Balance: $" + balance);
            
        } catch (IllegalArgumentException e) {
            transactionStatusLabel.setText("Error: " + e.getMessage());
        }
    }
    
    private void displayAccountInfo() {
        try {
            String accountNumber = accountField.getText();
            
            // Use a custom implementation to capture the output
            CustomOutputCapture capture = new CustomOutputCapture();
            System.setOut(capture);
            
            bank.displayAccountInfo(accountNumber);
            
            // Restore standard output
            System.setOut(System.out);
            
            // Display the captured output
            displayArea.setText(capture.getOutput());
            cardLayout.show(cardPanel, "display");
            
        } catch (IllegalArgumentException e) {
            transactionStatusLabel.setText("Error: " + e.getMessage());
        }
    }
    
    private void addInterest() {
        try {
            String accountNumber = accountField.getText();
            
            // Use a custom implementation to capture the output
            CustomOutputCapture capture = new CustomOutputCapture();
            System.setOut(capture);
            
            bank.addInterest(accountNumber);
            
            // Restore standard output
            System.setOut(System.out);
            
            // Display the captured output
            transactionStatusLabel.setText(capture.getOutput());
            
        } catch (IllegalArgumentException e) {
            transactionStatusLabel.setText("Error: " + e.getMessage());
        }
    }
    
    private void displayAllAccounts() {
        // Use a custom implementation to capture the output
        CustomOutputCapture capture = new CustomOutputCapture();
        System.setOut(capture);
        
        bank.listAllAccounts();
        
        // Restore standard output
        System.setOut(System.out);
        
        // Display the captured output
        displayArea.setText(capture.getOutput());
        cardLayout.show(cardPanel, "display");
    }
    
    private void showTransactionHistory() {
        try {
            String accountNumber = accountField.getText();
            
            // Use a custom implementation to capture the output
            CustomOutputCapture capture = new CustomOutputCapture();
            System.setOut(capture);
            
            bank.showTransactionHistory(accountNumber);
            
            // Restore standard output
            System.setOut(System.out);
            
            // Display the captured output
            displayArea.setText(capture.getOutput());
            cardLayout.show(cardPanel, "display");
            
        } catch (IllegalArgumentException e) {
            transactionStatusLabel.setText("Error: " + e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BankManagementGUI gui = new BankManagementGUI();
            gui.setVisible(true);
        });
    }
    
    // Helper class to capture System.out output
    private static class CustomOutputCapture extends java.io.PrintStream {
        private StringBuilder output = new StringBuilder();
        
        public CustomOutputCapture() {
            super(new java.io.ByteArrayOutputStream());
        }
        
        @Override
        public void println(String x) {
            output.append(x).append("\n");
        }
        
        @Override
        public void print(String x) {
            output.append(x);
        }
        
        public String getOutput() {
            return output.toString();
        }
    }
}