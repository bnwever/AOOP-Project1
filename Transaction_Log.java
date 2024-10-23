/**
 * The TransactionLog class is for logging and retrieving
 * transaction details from the bank using a fixed-size array.
 * 
 * <p>Provides methods to log transactions and read the transaction log.
 * 
 * @author Jesus Ordaz
 */
public class TransactionLog {

    private String[] logEntries;
    private int currentIndex;

    // Constructor
    /**
     * Constructs a TransactionLog with a specified capacity.
     * 
     * @param capacity The maximum number of transactions that can be logged.
     */
    public TransactionLog(int capacity) {
        this.logEntries = new String[capacity];
        this.currentIndex = 0;
    }

    /**
     * Logs a transaction by adding the given details to the transaction log.
     * If the log is full, the oldest entry will be overwritten.
     *
     * @param details A string containing the details of the transaction.
     */
    public void logTransaction(String details) {
        if (currentIndex >= logEntries.length) {
            // Overwrite the oldest transaction when the log is full
            currentIndex = 0;
        }
        logEntries[currentIndex] = details;
        currentIndex++;
    }

    /**
     * Reads the transaction log and returns an array of all logged transactions.
     * 
     * @return An array of strings, where each string represents a logged transaction.
     */
    public String[] readLog() {
        return logEntries.clone();
    }

    /**
     * Returns the total capacity of the transaction log.
     * 
     * @return The capacity of the transaction log.
     */
    public int getCapacity() {
        return logEntries.length;
    }
}
