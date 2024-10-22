import java.util.ArrayList;
import java.util.List;

/**
 * The Customer class represents a bank customer who can own multiple accounts.
 * It allows opening, adding, and removing accounts.
 * 
 * @author Jesus Ordaz
 */
public class Customer {
    private int customerID;
    private List<Account> accounts;
    private Person individual;

    // Constructors
    /** No paramameters constructor */
    public Customer() {
        this.accounts = new ArrayList<>();
    }

    /**
     * Constructs a Customer with a specified customer ID.
     * 
     * @param customerIDIn the unique ID of the customer.
     */
    public Customer(int customerIDIn) {
        this.customerID = customerIDIn;
        this.accounts = new ArrayList<>();
    }

    // Methods
    /** 
     * Returns the type of a class as a string.
     *  
     * @param index The index of the account
     * @return a string of the account type, or "Invalid account index" if index is out of bounds.
    */
    public String getAccountType(int index) {
        if (index >= 0 && index < accounts.size() && accounts.get(index) != null) {
            return accounts.get(index).getClass().getSimpleName();
        }
        return "Invalid account index";
    }

    /**
     * Finds an account by its ID.
     * 
     * @param accountID The ID of the account to find.
     * @return The account with the specified ID, or null if not found.
     */
    public Account findAccountByID(int accountID) {
        for (Account account : accounts) {
            if (account.getAccountID() == accountID) {
                return account;
            }
        }
        return null;
    }

    // Getters
    /** @return the customer ID attribute. */
    public int getCustomerID() {
        return this.customerID;
    }
    
    /** 
     * Retrieves the account at the specified index from the accounts list.
     * 
     * @param accountIndex the index of the desired account.
     * 
     * @return the account, or null if the index is invalid.
     */
    public Account getAccount(int accountIndex) {
        if (accountIndex >= 0 && accountIndex < accounts.size()) {
            return accounts.get(accountIndex);
        }
        return null;
    }

    /** @return the person attribute. */
    public Person getPerson() {
        return this.individual;
    }

    // Setters
    /**
     * Sets customerIDIn ID to customerID.
     * 
     * @param customerIDIn the customer ID to set.
     */
    public void setCustomerID(int customerIDIn) {
        this.customerID = customerIDIn;
    }

    /**
     * Sets the person attribute.
     * 
     * @param individualIn the person to set.
     */
    public void setPerson(Person individualIn) {
        this.individual = individualIn;
    }

    // Account management
    /**
     * Adds an account to the accounts list.
     * 
     * @param accountIn The account object being added.
     */
    public void addAccount(Account accountIn) {
        accounts.add(accountIn);
    }

    /**
     * Removes an account by its ID.
     * 
     * @param accountID The ID of the account to be removed.
     * @return true if the account was removed successfully, false if not found.
     */
    public boolean removeAccountByID(int accountID) {
        Account accountToRemove = findAccountByID(accountID);
        if (accountToRemove != null) {
            accounts.remove(accountToRemove);
            return true;
        }
        return false;
    }

    /**
     * Retrieves the balance of the account at the specified index.
     * 
     * @param index The index of the account.
     * @return The balance of the account, or -1 if the index is invalid.
     */
    public double getAccountBalance(int index) {
        if (index >= 0 && index < accounts.size() && accounts.get(index) != null) {
            return accounts.get(index).getBalance();
        }
        return -1;
    }
}
