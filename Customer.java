/**
 * The Customer class represents a bank customer who can own multiple accounts.
 * It allows opening, adding, and removing accounts.
 * 
 * @author Jesus Ordaz
 */
public class Customer {
    private int customerID;
    private Account accounts[];
    private Person individual;

    // Constructors
    /** No param constructor */
    public Customer () {
        this.accounts = new Account[3];
    }

    /**
     * Constructs a Customer with a specified customer ID.
     * 
     * @param customerIDIn the unique ID of the customer
     */
    public Customer(int customerIDIn) {
        this.customerID = customerIDIn;
        this.accounts = new Account[3];
    }

    // Methods
    /** 
     * Returns the type of a class as a string.
     *  
     * @return a string of the account type
    */
    public String getAccountType(int index) {
        return accounts[index].getClass().getSimpleName();
    }

    // Getters
    /** @return the customer ID attribute. */
    public int getCustomerID() {
        return this.customerID;
    }
    
    /** 
     * Retrieves the account at the specified index from the accounts array.
     * 
     * @param accountIndex the index of the desired account in the accounts array.
     * 
     * @return the checking account attribute. 
     */
    public Account getAccount(int accountIndex) {
        return this.accounts[accountIndex];
    }

    /** @return the person attribute. */
    public Person getPerson() {
        return this.individual;
    }

    // Setters
    /**
     * Sets customerIDIn ID to customerID.
     * 
     * @param customerIDIn
     */
    public void setCustomerID(int customerIDIn) {
        this.customerID = customerIDIn;
    }

    /**
     * Sets the account at specific index in the account array.
     * 
     * @param accountIn The account object being set.
     * @param accountIndex The index the account is being set to in the array.
     */
    public void setAccount(Account accountIn, int accountIndex) {
        this.accounts[accountIndex] = accountIn;
    }

    /**
     * Sets the person attribute.
     * 
     * @param individualIn
     */
    public void setPerson(Person individualIn){
        this.individual = individualIn;
    }
}