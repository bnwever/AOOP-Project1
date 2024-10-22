import java.util.Scanner;

/**
 * Concrete class representation of a checking account in a bank.
 * 
 * Provides implementation for the abstract methods such as collecting amount from user,
 * deposit function, withdraw function, and money transferring.
 * 
 * @author Blaine Wever
 */
public class Checking extends Account {
    /** Fee for overdrafting balance */
    private double overDraftFee = 50.0;
    
    // Use a single Scanner instance for user input throughout the class.
    private static final Scanner scanner = new Scanner(System.in);

    public Checking(int accountIDIn, double balanceIn) {
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

    /** Adds amount inputted to balance. */
    @Override
    public void deposit() {
        double amount = collectAmount();
        this.setBalance(this.getBalance() + amount);
        System.out.printf("Process Success: Current Balance = %.2f%n", this.getBalance());
    }

    /**
     * Removes amount inputted from balance. 
     * Applies an overdraft fee if balance after withdrawal is below 0.
     */
    @Override
    public void withdraw() {
        double amount = collectAmount();

        // Allow withdrawal including overdraft consideration
        if (amount <= this.getBalance() + overDraftFee) {
            this.setBalance(this.getBalance() - amount);

            if (this.getBalance() < 0) {
                System.out.printf("Overdraft Fee added: %.2f%n", this.overDraftFee);
                this.setBalance(this.getBalance() - this.overDraftFee);
            }

            System.out.printf("Process Success: Current Balance = %.2f%n", this.getBalance());
        } else {
            System.out.println("Insufficient funds, including overdraft consideration.");
        }
    }

    /** Transfers money from account to recipient's account. */
    @Override
    public void transferMoney(Account recipient) {
        double amount = amountWithinBalance();

        this.setBalance(this.getBalance() - amount);
        recipient.setBalance(recipient.getBalance() + amount);

        System.out.printf("Process Success: Current Balance = %.2f%n", this.getBalance());
    }

    /** Collects a positive double within the account's balance. */
    private double amountWithinBalance() {
        double amount;

        // Loop to verify that amount is not greater than balance
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
