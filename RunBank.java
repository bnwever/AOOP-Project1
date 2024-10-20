import java.util.Scanner;

/**
 * The RunBank class handles the interaction between the user and the program.
 * It also displays the starting and login menu.
 * @author Blaine 
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
    public static void startMenuCustomer() {
        while (true) {
            System.out.println("--------------------------------------------------------------" +
                               "Please insert the number for the function you'd like to use." +
                               "--------------------------------------------------------------" +
                               "1 - Check Balance" +
                               "2 - Deposit" +
                               "3 - Withdraw" +
                               "4 - Transfer" +
                               "--------------------------------------------------------------");

            switch (promptUser()) {
                case "1":
                System.out.println("");
                    break;

                case "2":
                    break;

                case "3":
                    break;

                case "4":
                    break;

                default:
                    System.out.println("Invalid Input.");
            }
        }
    }

    /**
     * 
     */
    public static void startMenuBankManager() {

    }

    /**
     * 
     */
    public static boolean logIn() {
        return true;
    }

    /**
     * Prompts the user for an input and returns the entered string.
     * @return The string entered by the user.
     */
    public static String promptUser() {
        System.out.println("----------------------------------------");
        System.out.print("> ");
        return scanner.nextLine();
    }

    public static Boolean isExit(String inputIn) {
        if (inputIn.equals("exit")){
            return true;
        }
        return false;
    }

    /**
     * The main method for the Bank Program.
     * @param args command line (unused)
    */  
    public static void main(String[] args) {
        while (true) {
            String temp = promptUser();
            if (temp.equals("exit")) {
                break;
            }
        }
        scanner.close();
    }
}
