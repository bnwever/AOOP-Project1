/**
 * The RunBank class handles the interaction between the user and the program.
 * It also displays the starting and login menu.
 * @author Blaine 
 */
public class RunBank {

    /**
     * The main method for the Bank Program.
     * @param args command line (unused)
    */  
    public static void main(String[] args) {

        // Check if BankUsers is sorted
        UserInteractions.isBankUsersSorted("SortedUsers");

        // Print Start Screen
        UserInteractions.startScreen();

        // Asks user if they are a Bank Manager
        UserInteractions.isManager();

        // If User is Bank Manager
        if (UserInteractions.isBankManager) {
            UserInteractions.findCustomer();

        // If User is not Bank Manager
        } else {
           Customer user = UserInteractions.customerLogIn();

           UserInteractions.customerFunctions(user);
        }
    }
}