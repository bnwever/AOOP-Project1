import java.util.Arrays;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;
import java.util.Arrays;

public class UserInteractions {
    /**
     * Indicates if the user is a bank manager.
     * True if user is bank manager, otherwise false.
     * 
     * Bank Managers gain access to view every account in the bank.
     */
    static Boolean isBankManager;

    private static int transferAccountIndex;

    private static final Scanner scanner = new Scanner(System.in); // For a shared scanner

    static {
        // Register a shutdown hook to close the scanner when the program exits
        Runtime.getRuntime().addShutdownHook(new Thread(() -> scanner.close()));
    }

    public static String promptUser() {
        System.out.print("> ");
        String input = scanner.nextLine();
        isExit(input);  // Exits program if input is "exit" or "Exit"
        return input;
    }

    public static void isExit(String inputIn) {
        if (inputIn.equalsIgnoreCase("exit")) {
            System.out.println("Exiting program...");
            System.exit(0);  // Ensure shutdown hook runs
        }
    }

    /**
     * Checks if there's a sorted version of BankUsers.
     * Does nothing if there version found.
     * Creates a sorted version if version not found.
     * 
     * @param csvName: The name of the sorted User Information
     */
    public static void isBankUsersSorted(String csvName) {
        File file = new File(csvName);
        if (!file.exists() || file.length() == 0) {
            UserInteractions.sortBankUsers();
        }
    }

    /**
     * 
     */
    public static void startScreen() {
        System.out.println("--------------------------------------------------------------\n" +
                           "Welcome to El Paso Miners Bank!" +
                           "At any time, type 'exit' to leave the program.");
    }

    /**
     * Asks user if they are a Bank Manager.
     * 
     * Yes sets isBankManager to true.
     * No sets isBankManager to false.
     */
    public static void isManager() {
        System.out.println("--------------------------------------------------------------\n" +
                           "Are you a bank manager? [Y/N]:");

        // Loop until user inputs valid answer (yes or no).
        while (true) {
            String input = UserInteractions.promptUser();

            // Sets isBankManager to true if user answers yes.
            if (input.equals("Y") || input.equals("y")){
                isBankManager = true;
                break;
            } 
            
            // Sets isBankManager to false if user answers no.
            else if (input.equals("N") || input.equals("n")) {
                isBankManager = false;
                break;
            }

            else System.out.println("Invalid Input"); // User input is invalid
        }
    }

    /**
     * Creates a filled Customer class based on inputed ID or Name.
     * 
     * @return User: A filled out Customer class.
     */
    public static Customer customerLogIn(){
        System.out.println("--------------------------------------------------------------\n" +
                           "How would you like to sign into your account?\n" +
                           "A. By ID\n" +
                           "B. By Name");

        Customer User = new Customer();
        switch (promptUser()) {
            case "A":
            case "a":
            while (true) {
                System.out.println("--------------------------------------------------------------\n" +
                               "Please enter the ID of the account.");
                User = createCustomer(Integer.parseInt(promptUser()), null, null, null);
                if (User != null) { // checkingUser is null if customer isn't found.
                    break;
                } else {
                    System.out.println("Customer ID was not found.");
                }
            }
                break;
            
            case "B":
            case "b":
            while (true) {
                System.out.println("--------------------------------------------------------------\n" +
                                   "Please enter the Name of the account.");
                System.out.println("First name:");
                String firstName = promptUser();
                System.out.println("Last name:");
                String lastName = promptUser();

                User = createCustomer(null, firstName, lastName, null);
                if (User != null) { // checkingUser is null if customer isn't found.
                    break;
                } else {
                    System.out.println("Customer name was not found.");
                }
            }
                break;
        }
        return User;
    }

     /** Creates a filled Customer class based on inputed ID or Name and prints the info. */
    public static void findCustomer() {
        while (true) {
            System.out.println("--------------------------------------------------------------\n" +
                               "Enter in the option you'd like to choose...\n" +
                               "A. Find customer by ID\n" +
                               "B. Find customer by Name");

            Customer searchedCustomer = new Customer();
            switch (promptUser()) {
                // Finds customer based on ID.
                case "A":
                case "a":
                    // Loop until a customer is found.
                    while (true) {
                        System.out.println("--------------------------------------------------------------\n" +
                                       "Please enter the ID of the account.");
                        searchedCustomer = createCustomer(Integer.parseInt(promptUser()), null, null, null);
                        if (searchedCustomer != null) { // checkingUser is null if customer isn't found.
                            break;
                        } else {
                            System.out.println("Customer ID was not found.");
                        }
                    }
                    break;
                
                // Finds customer based on Name.
                case "B":
                case "b":
                    // Loop until a customer is found.
                    while (true) {
                        System.out.println("--------------------------------------------------------------\n" +
                                           "Please enter the Name of the account.");
                        System.out.println("First name:");
                        String firstName = promptUser();
                        System.out.println("Last name:");
                        String lastName = promptUser();

                        searchedCustomer = createCustomer(null, firstName, lastName, null);
                        if (searchedCustomer != null) { // checkingUser is null if customer isn't found.
                            break;
                        } else {
                            System.out.println("Customer name was not found.");
                        }
                    }
                    break;

                default:
                    System.out.println("Invalid Input.");
                    break;
            }
            
            // Prints out customer's information.
            printCustomerInfo(searchedCustomer);
        }
    }

    /** Prints Customer info. Customer, Person, and it's accounts. */
    public static void printCustomerInfo(Customer searchedCustomer) {
        System.out.println("--------------------------------------------------------------\n");
        System.out.println("Customer ID: " + searchedCustomer.getCustomerID());
        System.out.println("Name: " + searchedCustomer.getPerson().getName());
        System.out.println("Address: " + searchedCustomer.getPerson().getAddress());

        System.out.println(searchedCustomer.getAccountType(0) + " Account -");
        System.out.println("\tID: " + searchedCustomer.getAccount(0).getAccountID());
        System.out.println("\tBalance: " + searchedCustomer.getAccount(0).getBalance());

        System.out.println(searchedCustomer.getAccountType(1) + " Account -");
        System.out.println("\tID: " + searchedCustomer.getAccount(1).getAccountID());
        System.out.println("\tBalance: " + searchedCustomer.getAccount(1).getBalance());

        System.out.println(searchedCustomer.getAccountType(2) + " Account -");
        System.out.println("\tID: " + searchedCustomer.getAccount(2).getAccountID());
        System.out.println("\tBalance: " + searchedCustomer.getAccount(2).getBalance());
    }

    /**
     * 
     */
    public static void customerFunctions(Customer customerIn) {
        while (true) {
            System.out.println("--------------------------------------------------------------\n" +
                               "Please insert the number for the function you'd like to use.\n" +
                               "--------------------------------------------------------------\n" +
                               "1 - Check All Accounts\n" +
                               "2 - Deposit\n" +
                               "3 - Withdraw\n" +
                               "4 - Transfer\n" +
                               "OR 'Exit' to end the program");

            switch (promptUser()) {
                case "1":
                    printCustomerInfo(customerIn);
                    break;

                case "2":
                    int checkingIndex = printChooseAccount("Deposit");
                    customerIn.getAccount(checkingIndex).deposit();
                    break;

                case "3":
                    int savingsIndex = printChooseAccount("Withdraw");
                    customerIn.getAccount(savingsIndex).withdraw();
                    break;

                case "4":
                    int creditIndex = printChooseAccount("Transfer");
                    Customer foundAccount = new Customer();
                    while (true) {
                        System.out.println("--------------------------------------------------------------\n" +
                                           "Please insert the account number you wish to transfer money to.");

                        Integer accountIDIn = Integer.parseInt(promptUser());
                        foundAccount = createCustomer(null, null, null, accountIDIn);
                        if (foundAccount != null) {
                            break;
                        }
                    }
                    
                    customerIn.getAccount(creditIndex).transferMoney(foundAccount.getAccount(transferAccountIndex));
                    break;

                default:
                    System.out.println("Invalid Input.");
                    break;
            }
        }
    }

    /**
     * Prompts the user to chose which account they'd like to chose.
     * Returns the index the account is found in Customer's account array.
     * 
     * @param functionName: Dynamic menu based on what function is wanting to be used
     * @return index where account is found in account array.
     */
    public static int printChooseAccount(String functionName){
        System.out.println("--------------------------------------------------------------\n" +
                           "Which account would you like to " + functionName + " in?\n" +
                           "--------------------------------------------------------------\n" +
                           "1 - Checking\n" +
                           "2 - Savings\n" +
                           "3 - Credit");

        // Loops until valid input
        while (true) {
            String decision = promptUser();
            if (decision.equals("1")) {
                return 0; // Checking account index

            } else if (decision.equals("2")) {
                return 1; // Savings account index

            } else if (decision.equals("3")) {
                return 2; // Credit account index

            } else {
                System.out.println("Invalid Input");
            }
        }
    }

    public static Customer createCustomer(Integer customerIDIn, String firstNameIn, String lastNameIn, Integer accountIDIn) {
         // Find the row based on either ID or Name
        String[] columns = findRow(customerIDIn, firstNameIn, lastNameIn, accountIDIn);

        if (columns == null) return null; // Return null if the row (customer) wasn't found
    
    
        Customer user = new Customer(); // Create Customer object
    
        // Create and set a Person object for the user: Person(First Name, Last Name, Address)
        Person individual = new Person(columns[1].trim() + columns[2].trim(), columns[4].trim());
        user.setPerson(individual);
    
        // Create and set Checking account: Checking(CheckingID, CheckingBalance)
        Checking userChecking = new Checking(Integer.parseInt(columns[6]), Integer.parseInt(columns[7]));
        user.setAccount(userChecking, 0);
    
        // Create and set Savings account: Savings(SavingsID, SavingsBalance)
        Savings userSavings = new Savings(Integer.parseInt(columns[8]), Integer.parseInt(columns[9]));
        user.setAccount(userSavings, 1);
    
        // Create and set Credit account: Credit(CreditID, CreditBalance)
        Credit userCredit = new Credit(Integer.parseInt(columns[10]), Integer.parseInt(columns[11]));
        user.setAccount(userCredit, 2);
    
        return user;
    }

    public static void sortBankUsers() { 
        String inputFile = "BankUsers.csv";
        String outputFile = "SortedUsers.csv";

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            reader.readLine(); // Get rid of header

            // Store lines in an array
            String[] lines = new String[104]; // 140 - About as many customers the bank has (no implementation on adding customers)
            int lineCount = 0;
            String line;

            // Read lines into array lines[]
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

    public static String[] findRow(Integer customerIDIn, String firstNameIn, String lastNameIn, Integer accountIDIn) {
        try (BufferedReader reader = new BufferedReader(new FileReader("SortedUsers"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] columns = line.split(",");
    
                // Check by ID if idIn is provided
                if (customerIDIn != null) {
                    int number = Integer.parseInt(columns[0].trim());
                    if (columns.length > 0 && number == customerIDIn) {
                        return columns;
                    }
                }
    
                // Check by Name if firstNameIn and lastNameIn are provided
                if (firstNameIn != null && lastNameIn != null) {
                    String firstName = columns[1].trim();
                    String lastName = columns[2].trim();
                    if (columns.length > 0 && firstName.equals(firstNameIn) && lastName.equals(lastNameIn)) {
                        return columns;
                    }
                }

                // Check by account number if account number provided
                if (accountIDIn != null) {
                    int checkingNumber = Integer.parseInt(columns[6].trim());
                    int savingsNumber = Integer.parseInt(columns[8].trim());
                    int creditNumber = Integer.parseInt(columns[10].trim());
                    if (columns.length <= 0) {
                        return null;
                    } else if (checkingNumber == accountIDIn) {
                        transferAccountIndex = 0;
                        return columns;

                    } else if (savingsNumber == accountIDIn) {
                        transferAccountIndex = 1;
                        return columns;

                    } else if (creditNumber == accountIDIn){
                        transferAccountIndex = 2;
                        return columns;
                    }
                }
            }
        } catch (IOException e) {
        }
        return null; // Return null if no matching row is found
    }
}