import java.util.Scanner;

/**
 * The RunBank class handles the interaction between the user and the program.
 * It also displays the starting and login menu.
 * @author Jesus Ordaz and Blaine Wever
 */
public class RunBank {
    /**
     * Indicates if the user is a bank manager.
     * True if user is bank manager, otherwise false.
     * 
     * Bank Managers gain access to view every account in the bank.
     */
    public static Boolean isBankManager = false;

    private static final Scanner scanner = new Scanner(System.in);

    /**
     * 
     */
    public static void displayMenu(){
    }

    /**
     * 
     */
    public static boolean logIn(){
        return true;
    }

    /**
     * Prompts the user for an input and returns the entered string.
     * @return The string entered by the user.
     */
    public static String promptUser(){
        System.out.println("----------------------------------------");
        System.out.print("$ ");
        return scanner.nextLine();
    }

    /**
     * The main method for the Bank Program.
     * @param args command line (unused)
    */  
    public static void main(String[] args){
        while (1 < 2) {
            String temp = promptUser();
            if (temp.equals("exit")) {
                break;
            }
        }
        scanner.close();
    }
}
