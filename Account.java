/**
 * Abstract class representation of a general bank account.
 * 
 * Provides an abstract method for withdrawing money and transfering money for 
 * checking, credit, and savings classes to implement.
 * Provides getter and setter methods for accountID and balance.
 * 
 * @author Blaine
 */
abstract class Account {
    // Attributes
    /** Identifier for the account. */
    private String accountID;

    /** Current Balance of the account. */
    private double balance;

    // Methods
    /** Prompts user for amount as input */
    public abstract double collectAmount();

    /** Deposits an amount of money into the account. */
    public abstract void deposit();

    /** Withdraws an amount of money into the account. */
    public abstract void withdraw();

    /** Transfers and amount of money from the account to the recipient account. */
    public abstract void transferMoney(Account recipient);
     
    /** Gets and returns the account ID */
    public String getAccountID() {
        return this.accountID;
    }

    /** Gets and returns the current balance of the account. */
    public double getBalance() {
        return this.balance;
    }

    /** Sets balance to newBalance */
    public void setBalance(double newBalance) {
        this.balance = newBalance;
    }
}