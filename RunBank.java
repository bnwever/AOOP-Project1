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

        // If User is Bank Manager
        // Breaks loop/ends program when user enters "exit"
        while (UserInteractions.isBankManager) {
            UserInteractions.findCustomer();
        }

        // Customer logIn (not Bank Manager)
        Customer user = UserInteractions.customerLogIn();

        // Gives Menu to check accounts, deposity, withdraw, or transfer money
        UserInteractions.customerFunctions(user);
    }
}