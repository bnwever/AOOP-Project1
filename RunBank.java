import java.io.File;

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
        isBankUsersSorted("SortedUsers");

        // Print Start Screen
        startScreen();

        // Asks user if they are a Bank Manager
        UserInteractions.isManager();
    }

    public static void startScreen() {
        System.out.println("--------------------------------------------------------------\n" +
                           "Welcome to El Paso Miners Bank!" +
                           "At any time, type 'exit' to leave the program.");
    }

    public static void isBankUsersSorted(String csvName) {
        File file = new File(csvName);
        if (!file.exists() || file.length() == 0) {
            UserInteractions.sortBankUsers();
        }
    }
}
