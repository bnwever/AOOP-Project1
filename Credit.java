import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Concrete class representation of a savings account in a bank.
 * 
 * Provides implementation for the abstract methods such as collecting amount
 * from user,
 * deposit function, withdraw function, and money transferring.
 * 
 * @author Blaine
 */
public class Credit extends Account {
    private double creditLimit;

    // Use a single Scanner instance for user input throughout the class.
    private static final Scanner scanner = new Scanner(System.in);

    public Credit(int accountIDIn, double creditMaxIn, double balanceIn) {
        super(accountIDIn, balanceIn);
        this.creditLimit = -creditMaxIn;
    }

    /**
     * Collects a positive double from user.
     * 
     * @return amount: the double collected
     */
    @Override
    public double collectAmount() {
        double amount = 0;

        while (true) {
            System.out.print("Enter an amount:\n$ ");

            // Check if input is double
            if (scanner.hasNextDouble()) {
                amount = scanner.nextDouble();

                // Check if input is positive
                if (amount >= 0) {
                    break;
                } else {
                    System.out.println("Invalid input. Input was not positive or zero.");
                }
            } else {
                System.out.println("Invalid input. Input was not a double.");
                scanner.next(); // Clear invalid input
            }
        }
        return amount;
    }

    /** 
     * Adds amount inputed to balance
     * Logs the transaction in Transactions.txt
     */
    @Override
    public void deposit() {
        double amount;

        // Loop to ensure that the amount does not make balance exceed the maximum.
        while (true) {
            amount = collectAmount();
            if (amount <= Math.abs(this.getBalance())) {
                break;
            } else {
                System.out.println("Invalid input. Amount larger than balance.");
            }
        }

        this.setBalance(this.getBalance() + amount);
        System.out.println("Process Success: Current Balance = $" + this.getBalance());

        // Log the deposity transaction
        String transactionDetails = "Deposited $" + amount + " into account ID: " + this.getAccountID();
        logTransaction(transactionDetails);
    }

    /** 
     * Removes amount inputed to balance.
     * Logs the transaction in Transactions.txt
     */
    @Override
    public void withdraw() {
        double amount = amountWithinBalance();

        this.setBalance(this.getBalance() - amount);
        System.out.println("Process Success: Current Balance = " + this.getBalance());

        // Log the withdrawal transaction
        String transactionDetails = "Withdrew $" + amount + " from account ID: " + this.getAccountID();
        logTransaction(transactionDetails);
    }

    /** 
     * Transfers money from account to recipient's account.
     * Logs the transaction in Transactions.txt
     */
    @Override
    public void transferMoney(Account recipient) {
        double amount = amountWithinBalance();

        this.setBalance(this.getBalance() - amount);
        recipient.setBalance(recipient.getBalance() + amount);

        System.out.println("Process Success: Current Balance = " + this.getBalance());

        // Log the transfer transaction
        String transactionDetails = "Transferred $" + amount + " from account ID: " + this.getAccountID() +
                                    " to account ID: " + recipient.getAccountID();
        logTransaction(transactionDetails);
    }

    /** Collects a positive double within the account's balance. */
    private double amountWithinBalance() {
        double amount;

        // Loop to ensure that amount is not greater than balance
        while (true) {
            amount = collectAmount();

            if (this.getBalance() - amount >= this.creditLimit) {
                break;
            } else {
                System.out.println("Invalid input. Amount exceeds credit limit.");
            }
        }

        return amount;
    }

    /** logs the transaction details into TransactionLog.txt */
    private void logTransaction(String transactionDetails) {
        try (FileWriter writer = new FileWriter("TransactionLog.txt", true)) {
            writer.write("\n" + transactionDetails);
        } catch (IOException e) {
            System.out.println("log error");
        }
    }
}
