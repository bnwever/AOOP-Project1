import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
/**
 * Concrete class representation of a savings account in a bank.
 * 
 * Provides implementation for the abstract methods such as collecting amount from user,
 * deposit function, withdraw function, and money transferring.
 * 
 * @author Blaine
 */
public class Savings extends Account {
    private int withdrawLimit = 6;

    // Use a single Scanner instance for user input throughout the class.
    private static final Scanner scanner = new Scanner(System.in);

    public Savings(int accountIDIn, double balanceIn){
        super(accountIDIn, balanceIn);
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
                scanner.nextLine();
                
                // Check if input is positive
                if (amount >= 0) {
                    break;

                } else {
                    System.out.println("Invalid input. Input was not positive or zero.");
                }
            } else {
                System.out.println("Invalid input. Input was not a double.");
                scanner.nextLine();
            }
        }
        return amount;
    }

    /** Adds amount inputed to balance. */
    @Override
    public void deposit() {
        double amount = collectAmount();
        this.setBalance(this.getBalance() + amount);
        System.out.println("Process Success: Current Balance = $" + this.getBalance());

        String transactionDetails = "Deposited" + amount + "into account - " + this.getAccountID();
        logTransaction(transactionDetails);
    }

    /** 
     * Removes amount inputed to balance.
     * The amount of withdrawals are limited per cycle.
     */
    @Override
    public void withdraw() {
        if (this.withdrawLimit <= 0){
            System.out.println("Process Denied: Cannot proceed monthly transaction limit.");
        }

        this.setBalance(this.getBalance() - amountWithinBalance());
        this.withdrawLimit -= 1;

        System.out.println("Process Success: Current Balance = " + this.getBalance());
    }

    /** Transfers money from account to recipient's account. */
    @Override
    public void transferMoney(Account recipient) {
        double amount = amountWithinBalance();

        this.setBalance(this.getBalance() - amount);
        recipient.setBalance(recipient.getBalance() + amount);
        this.withdrawLimit -= 1;

        System.out.println("Process Success: Current Balance = " + this.getBalance());
    }

    /** Collects a positive double within the account's balance. */
    private double amountWithinBalance () {
        double amount;

        // Loop to ensure that amount is not greater than balance
        while (true) {
            amount = collectAmount();

            if (amount <= this.getBalance()) {
                break;
            } else {
                System.out.println("Invalid input. Amount larger than balance.");
            }
        }

        return amount;
    }

    private void logTransaction(String transactionDetails) {
        try (FileWriter writer = new FileWriter("TransactionLog.txt", true)) {
            System.out.println("write success");
            writer.write(transactionDetails);
        } catch (IOException e) {
            System.out.println("log error");
        }
    }
}