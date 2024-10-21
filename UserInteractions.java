import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class UserInteractions {
    /**
     * Indicates if the user is a bank manager.
     * True if user is bank manager, otherwise false.
     * 
     * Bank Managers gain access to view every account in the bank.
     */
    public static Boolean isBankManager;

    /**
     * 
     */
    public static void isManager() {
        System.out.println("--------------------------------------------------------------\n" +
                           "Are you a bank manager? [Y/N]:");
        while (true) {
            String input = promptUser();

            if (input.equals("Y") || input.equals("y")){
                isBankManager = true;
                break;
            } 
            
            else if (input.equals("N") || input.equals("n")) {
                isBankManager = false;
                break;
            }

            else System.out.println("Invalid Input");
        }
    }

    /**
     * Prompts the user for login info.
     * User is allowed 3 login attempts.
     * If the user exceeds attempts the program will end.
     */
    public static Customer customerLogIn(){
        int attempts = 0;
        int customerID;
        Customer User = new Customer();
        while (true) {
            // Prompts user for ID Number0
            System.out.println("--------------------------------------------------------------\n" +
            "Enter in your Identification Number: ");
            customerID = Integer.parseInt(promptUser());

            // Prompts user for DOB
            System.out.println("--------------------------------------------------------------\n" +
            "Enter in your Date of Birth (Day-Month-Year) [XX-ABC-XX]: ");
            String customerDOB = promptUser();

            // Attempts to find customer in BankUsers.csv file
            Boolean accountFound = false;
            try (BufferedReader reader = new BufferedReader(new FileReader("BankUsers.csv"))){
                String line;

                // Traverse through each row in file
                while ((line = reader.readLine()) != null) {
                    String[] columns = line.split(",");
                    if (columns.length > 0){
                        try {
                            int number = Integer.parseInt(columns[0].trim());
                            String DOB = columns[3].trim();

                            // Checks if ID and DOB match user's login input.
                            // Sets User CustomerID and accounts.
                            if (number == customerID && DOB.equals(customerDOB)) {
                                accountFound = true;

                                User.setCustomerID(customerID);

                                int checkingID = Integer.parseInt(columns[6].trim());
                                int checkingBalance = Integer.parseInt(columns[7].trim());
                                User.addAccount(new Checking(checkingID, checkingBalance));

                                int savingsID = Integer.parseInt(columns[6].trim());
                                int savingsBalance = Integer.parseInt(columns[7].trim());
                                User.addAccount(new Savings(savingsID, savingsBalance));

                                int creditID = Integer.parseInt(columns[6].trim());
                                int creditBalance = Integer.parseInt(columns[7].trim());
                                User.addAccount(new Credit(creditID, creditBalance));

                                break;
                            }

                        } catch (NumberFormatException e) {}
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            // If customer info found break the login loop.
            if (accountFound) {
                break;
            }
            // If account not found re-attempt.
            else if (attempts < 3) {
                attempts++;
                System.out.printf("Login Failure. Check your Identification number or password%n" +
                                  "%d attemps left.%n", (3 - attempts));
            }
            // If account not found and no more attempts end program.
            else {
                System.exit(0);
            }
        }
        // Returns Customer Class with CustomerID set (accounts not set)
        return User;
    }

    /**
     * 
     */
    public static void bankManagerFunctions() {
        while (true) {
            System.out.println("--------------------------------------------------------------\n" +
                               "Enter in the option you'd like to choose..." +
                               "A. Inquire customer by ID" +
                               "B. Inquire customer by Name");

            switch (promptUser()) {
                case "A":
                case "a":
                    break;
                
                case "B":
                case "b":
                    break;

                default:
                    System.out.println("Invalid Input.");
                    break;
            }
        }
    }

    /**
     * 
     */
    public static void customerFunctions() {
        while (true) {
            System.out.println("--------------------------------------------------------------\n" +
                               "Please insert the number for the function you'd like to use.\n" +
                               "--------------------------------------------------------------\n" +
                               "1 - Check Balance\n" +
                               "2 - Deposit\n" +
                               "3 - Withdraw\n" +
                               "4 - Transfer\n");

            switch (promptUser()) {
                case "1":
                    break;

                case "2":
                    break;

                case "3":
                    break;

                case "4":
                    break;

                default:
                    System.out.println("Invalid Input.");
                    break;
            }
        }
    }

    /**
     * Prompts the user for an input and returns the entered string.
     * @return The string entered by the user.
     */
    public static String promptUser() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("> ");
        String input = scanner.nextLine();
        scanner.close();

        isExit(input);
        return input;
    }

    public static void isExit(String inputIn) {
        if (inputIn.equals("exit") || inputIn.equals("Exit")){
            System.out.println("Exiting program..");
            System.exit(0);
        }
    }

    public static Customer createCustomerByName() {
        return new Customer();
    }

    public static Customer createCustomerByID(int customerIDIn) {
        
        return new Customer();
    }

    public static void sortBankUsers() { 
        String inputFile = "BankUsers.csv";
        String outputFile = "SortedUsers.csv";

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            // Get rid of header
            reader.readLine();

            // Store lines in an array
            String[] lines = new String[104]; // About as many customers the bank has (no implementation on adding customers)
            int lineCount = 0;

            // Read lines into array lines[]
            String line;
            while ((line = reader.readLine()) != null) {
                if (lineCount >= lines.length) {
                    String[] newLines = new String[lines.length * 2];
                    System.arraycopy(lines, 0, newLines, 0, lines.length);
                    lines = newLines;
                }
                lines[lineCount++] = line;
            }

            // Sort the file by identification num
            for (int i = 0; i < lineCount - 1; i++) {
                for (int j = 0; j < lineCount - 1 - i; j++) {
                    int firstColumn1 = Integer.parseInt(lines[j].split(",")[0].trim());
                    int firstColumn2 = Integer.parseInt(lines[j + 1].split(",")[0].trim());
                    if (firstColumn1 > firstColumn2) {
                        // Swap lines
                        String temp = lines[j];
                        lines[j] = lines[j + 1];
                        lines[j + 1] = temp;
                    }
                }
            }

            // Write the sorted lines to the output file
            try (PrintWriter writer = new PrintWriter(outputFile)) {
                for (int i = 0; i < lineCount; i++) {
                    writer.println(lines[i]);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}