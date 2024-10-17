import java.util.ArrayList;

/**
 * The Customer class represents a bank customer who can own multiple accounts.
 * It allows opening, adding, and removing accounts.
 * 
 * @author Jesus Ordaz
 */
public class Customer {
    private String customerID;
    private ArrayList<Account> accounts;

    /**
     * Constructs a Customer with a specified customer ID.
     * 
     * @param customerIDIn the unique ID of the customer
     */
    public Customer(String customerIDIn) {
        this.customerID = customerIDIn;
        this.accounts = new ArrayList<>();
    }

    /**
     * Adds an existing account to the customer's list of accounts.
     * 
     * @param account the account to be added
     */
    public void addAccount(Account account) {
        this.accounts.add(account);
    }

    /**
     * Removes an account based on its account number.
     * 
     * @param accountNumber the unique identifier of the account to be removed
     */
    public void removeAccount(String accountNumber) {
        accounts.removeIf(account -> account.getAccountID().equals(accountNumber));
    }

    /**
     * Returns the list of accounts associated with the customer.
     * 
     * @return the list of accounts
     */
    public ArrayList<Account> getAccounts() {
        return this.accounts;
    }

    /**
     * Gets the customer's ID.
     * 
     * @return the customer ID
     */
    public String getCustomerID() {
        return customerID;
    }

    /**
     * Sets the customer's ID.
     * 
     * @param customerID the new customer ID
     */
    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }
}
