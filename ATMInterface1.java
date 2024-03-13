import java.util.Scanner;

public class ATMInterface1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Bank bank = new Bank();
        ATM atm = new ATM(bank);

        // Prompt user for PIN
        System.out.print("Enter your PIN: ");
        int pin = scanner.nextInt();

        // Authenticate user
        if (atm.authenticate(pin)) {
            System.out.println("Authentication successful!");
            boolean quit = false;
            while (!quit) {
                System.out.println("1. Transaction History");
                System.out.println("2. Withdraw");
                System.out.println("3. Deposit");
                System.out.println("4. Transfer");
                System.out.println("5. Quit Program");

                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        atm.showTransactionHistory();
                        break;
                    case 2:
                        System.out.print("Enter amount to withdraw: ");
                        double withdrawAmount = scanner.nextDouble();
                        atm.withdraw(withdrawAmount);
                        break;
                    case 3:
                        System.out.print("Enter amount to deposit: ");
                        double depositAmount = scanner.nextDouble();
                        atm.deposit(depositAmount);
                        break;
                    case 4:
                        System.out.print("Enter recipient account number: ");
                        int recipientAccountNumber = scanner.nextInt();
                        System.out.print("Enter amount to transfer: ");
                        double transferAmount = scanner.nextDouble();
                        atm.transfer(recipientAccountNumber, transferAmount);
                        break;
                    case 5:
                        quit = true;
                        System.out.println("Quitting the program...");
                        break;
                    default:
                        System.out.println("Invalid choice!");
                }
            }
        } else {
            System.out.println("Authentication failed. Exiting program...");
        }
        scanner.close();
    }
}

class Bank {
    private int userPin = 1234; // Example PIN
    private double balance = 1000; // Example initial balance
    private String transactionHistory = "";

    public boolean authenticate(int pin) {
        return pin == userPin;
    }

    public double getBalance() {
        return balance;
    }

    public void updateBalance(double amount) {
        balance += amount;
    }

    public void addTransaction(String transaction) {
        transactionHistory += transaction + "\n";
    }

    public String getTransactionHistory() {
        return transactionHistory;
    }
}

class ATM {
    private Bank bank;

    public ATM(Bank bank) {
        this.bank = bank;
    }

    public boolean authenticate(int pin) {
        return bank.authenticate(pin);
    }

    public void showTransactionHistory() {
        System.out.println("Transaction History:");
        System.out.println(bank.getTransactionHistory());
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= bank.getBalance()) {
            bank.updateBalance(-amount);
            bank.addTransaction("Withdrawal: $" + amount);
            System.out.println("Withdrawal successful. Current balance: $" + bank.getBalance());
        } else {
            System.out.println("Invalid withdrawal amount or insufficient balance.");
        }
    }

    public void deposit(double amount) {
        if (amount > 0) {
            bank.updateBalance(amount);
            bank.addTransaction("Deposit: $" + amount);
            System.out.println("Deposit successful. Current balance: $" + bank.getBalance());
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void transfer(int recipientAccountNumber, double amount) {
        // Logic for transferring funds to another account
        System.out.println("Transfer functionality not implemented yet.");
    }
}
