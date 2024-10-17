import java.util.ArrayList;
import java.util.List;

/**
 * The TransactionLog class is for logging and retrieving
 * transaction details from the bank
 * 
 * <p>Provides methods to log transactions and read the transaction log.
 * 
 * @author Jesus Ordaz
 */
public class Transaction_Log {

    private List<String> logEntries = new ArrayList<>();

    /**
     * Logs a transaction by adding the given details to the transaction log.
     *
     * @param details A string containing the details of the transaction.
     */
    public void logTransaction(String details) {
        logEntries.add(details);
    }

    /**
     * Reads the transaction log and returns a list of all logged transactions.
     *
     * @return A list of strings, where each string represents a logged transaction.
     */
    public List<String> readLog() {
        return new ArrayList<>(logEntries);
    }
}
