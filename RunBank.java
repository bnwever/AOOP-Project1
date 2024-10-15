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
    public Boolean isBankManager;

    /**
     * 
     */
    public static void displayMenu(){
        System.out.println("----------------------------------------");
        System.out.println("----------------------------------------");
    }

    /**
     * 
     */
    public static void logIn(){
        System.out.println("----------------------------------------");

        System.out.println("----------------------------------------");
    }

    /**
     * Prompts the user for an input and returns the entered string.
     * @return The string entered by the user.
     */
    public static String promptUser(){
        try (Scanner scanner = new Scanner(System.in)){
            System.out.print("$ ");
            return scanner.nextLine();
        }
    }

    /**
     * The main method for the Bank Program.
     * @param args command line (unused)
    */  
    public static void main(String[] args){

 }
}
