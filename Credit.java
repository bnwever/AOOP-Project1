import java.util.Scanner;
/**
 * Concrete class representation of a savings account in a bank.
 * 
 * Provides implementation for the abstract methods such as collecting amount from user,
 * deposit function, withdraw function, and money transferring.
 * 
 * @author Blaine
 */
public class Credit extends Account {
    /**
     * Collects a positive double from user that does not exceed the maximumBalance.
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
        System.out.printf("Process Success: Currente Balance = %.2f\n");
    }

    /** Removes amount inputed to balance. */
    @Override
    public void withdraw() {
        this.setBalance(this.getBalance() - collectAmount());
        System.out.printf("Process Success: Current Balance = %.2f\n", this.getBalance());
    }

    /** Transfers money from account to recipient's account. */
    @Override
    public void transferMoney(Account recipient) {
        double amount = collectAmount();

        this.setBalance(this.getBalance() - amount);
        recipient.setBalance(recipient.getBalance() + amount);

        System.out.printf("Process Success: Current Balance = %.2f\n", this.getBalance());
    }
}