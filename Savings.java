import java.util.Scanner;
/**
 * Concrete class representation of a savings account in a bank.
 * 
 * Provides implementation for the abstract methods such as collecting amount from user,
 * deposit function, withdraw function, and money transferring.
 * 
 * @author Blaine Wever
 */
public class Savings extends Account {
    private int withdrawLimit = 6;

    public Savings(int accountIDIn, double balanceIn){
        super(accountIDIn, balanceIn);
    }

    /**
     * Collects a positive double from user.
     * 
     * @return amount: the double collected.
     */
    @Override
    public double collectAmount() {
        Scanner scanner = null;
        double amount = 0; 
    
        try {
            scanner = new Scanner(System.in);
            
            while (true) {
                System.out.print("Enter an amount:\n$ ");
    
                // Check if input is double
                if (scanner.hasNextDouble()) {
                    amount = scanner.nextDouble();
                    System.out.println(amount); // TO DELETE: for debugging
                } else {
                    System.out.println("Invalid input. Input was not a double.");
                }

                // Check if input is also positive
                if (amount < 0) {
                    System.out.println("Invalid input. Input was not positive or zero.");
                } else break;
            }
        } catch (Exception e) {
            System.out.println("Error");
        } finally {
            scanner.close(); 
        }
        
        return amount; 
    } 

    /** Adds amount inputed to balance. */
    @Override
    public void deposit() {
        double amount = collectAmount();
        this.setBalance(this.getBalance() + amount);
        System.out.printf("Process Success: Currente Balance = %.2f\n");
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

        System.out.printf("Process Success: Current Balance = %.2f\n", this.getBalance());
    }

    /** Transfers money from account to recipient's account. */
    @Override
    public void transferMoney(Account recipient) {
        double amount = amountWithinBalance();

        this.setBalance(this.getBalance() - amount);
        recipient.setBalance(recipient.getBalance() + amount);
        this.withdrawLimit -= 1;

        System.out.printf("Process Success: Current Balance = %.2f\n", this.getBalance());
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
}