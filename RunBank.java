/**
 * 
 * @author Jesus Ordaz and Blaine
 */
public class RunBank {

    /**
     * The main method for the Bank Program.
     * 
     * @param args command line (unused)
     */
    public static void main(String[] args) {

        // Print Start Screen
        UserInteractions.startScreen();

        // Asks user if they are a Bank Manager
        UserInteractions.isManager();

        // Create a TransactionLog with a capacity of 100 (you can adjust this value)
        TransactionLog transactionLog = new TransactionLog(100);

        // If User is Bank Manager
        // Breaks loop/ends program when user enters "exit"
        while (UserInteractions.isBankManager) {
            UserInteractions.findCustomer();
        }

        // Customer logIn (not Bank Manager)
        Customer user = UserInteractions.customerLogIn();

        // Gives Menu to check accounts, deposit, withdraw, or transfer money
        // Pass the user and transactionLog to customerFunctions
        UserInteractions.customerFunctions(user, transactionLog);
    }
}
