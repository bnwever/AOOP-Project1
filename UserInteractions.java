import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * This class holds the implemented functions called by RunBank.java in the main function.
 * This is done to keep the design more organized.
 * 
 * The class is split into TWO main parts (not including attributes).
 * Part 1 - Functions directly called by RunBank.java.
 * Part 2 - Functions used by Functions in Part 1 to help keep the code organized and readable.
 * 
 * @author Jesus Ordaz and Blaine Wever
 */
public class UserInteractions {

    // -----------------------------------------------------------------------------------------------------------------------------------------------
    // Attributes
    /**
     * Indicates if the user is a bank manager.
     * True if user is bank manager, otherwise false.
     * 
     * Bank Managers gain access to view every account in the bank.
     */
    static Boolean isBankManager;

    /** When transfering money, this variable keeps track of the account index for the account the user wants to transfer to. */
    private static int transferAccountIndex;

    // For a shared scanner
    private static final Scanner scanner = new Scanner(System.in);

    // Register a shutdown hook to close the scanner when the program exits
    static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> scanner.close()));
    }

    // -----------------------------------------------------------------------------------------------------------------------------------------------
    // Part 1 : Directly Called Functions by RunBank.java

    /** Displays the start/welcome screen for the program */
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
     * Prompts user to choose ID or Name of a customer to find.
     * Program then prints the information of the customer.
     */
    public static void findCustomer() {
        // (outer) Loop to ensure user inputs "a"/"A" or "b"/"B"
        Boolean customerFound = false;
        while (!customerFound) {
            Customer searchedCustomer = new Customer(); // Class we're going to put our found customer in.
            System.out.println("--------------------------------------------------------------\n" +
                               "Enter in the option you'd like to choose...\n" +
                               "A. Find customer by ID\n" +
                               "B. Find customer by Name");

            // Determine if user wants to find account based on ID or Name
            switch (promptUser()) {

                // Finds customer based on ID.
                case "A":
                case "a":
                    // (inner) Loop until a customer is found.
                    while (true) {
                        System.out.println("--------------------------------------------------------------\n" +
                                       "Please enter the ID of the account.");
                        
                        // Searches for customer and creates Customer Class based on ID inputed
                        searchedCustomer = createCustomer(Integer.parseInt(promptUser()), null, null, null);
                        if (searchedCustomer == null) { // checkingUser is null if customer isn't found.
                            System.out.println("Customer ID was not found.");
                        } else {
                            customerFound = true; // Keeps outer loop from running again
                            break; // Breaks inner loop
                        }
                    }
                    break; // Breaks switch case
                
                // Finds customer based on Name.
                case "B":
                case "b":
                    // (inner) Loop until a customer is found.
                    while (true) {
                        // Asks and recieves first and last name of desired customer.
                        System.out.println("--------------------------------------------------------------\n" +
                                           "Please enter the Name of the account.");
                        System.out.println("First name:");
                        String firstName = promptUser();
                        System.out.println("Last name:");
                        String lastName = promptUser();

                        // Searches for customer and creates Customer class based on Names inputed
                        searchedCustomer = createCustomer(null, firstName, lastName, null);
                        if (searchedCustomer == null) { // checkingUser is null if customer isn't found.
                            System.out.println("Customer name was not found.");
                        } else {
                            customerFound = true; // Keeps outer loop from running again
                            break; // Breaks inner loop
                        }
                    }
                    break; // Breaks switch case

                // Invalid Input case. Outer loop keeps running
                default:
                    System.out.println("Invalid Input.");
                    break; 
            }
            
            // Prints out customer's information.
            printCustomerInfo(searchedCustomer);
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

    /**
     * This function 
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

                // Prints Customer Information
                case "1":
                    printCustomerInfo(customerIn);
                    break;

                // Prompts user to pick account. Then 
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

    // -----------------------------------------------------------------------------------------------------------------------------------------------
    // Part 2: Helper Functions Called By Functions in Part 1

     /**
     * Prompts user for an input.
     * Checks if input isExit. If true, stops the program. If false, do nothing.
     * 
     * @return input
     */
    public static String promptUser() {
        System.out.print("> ");
        String input = scanner.nextLine();
        isExit(input);  // Exits program if input is "exit" or "Exit"
        return input;
    }

    /**
     * Ends the program if the input is "exit" or "Exit"
     * 
     * @param inputIn: The input
     */
    public static void isExit(String inputIn) {
        if (inputIn.equalsIgnoreCase("exit")) {
            System.out.println("Exiting program...");
            System.exit(0);  // Ensure shutdown hook runs
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
     * Finds a row(customer) then creates and returns a Customer based on the row(customer) found.
     * There are three different ways to find a customer.
     * 1. By Customer ID -  createCustomer(customerIDIn, null, null, null)
     * 2. By Name -         createCustomer(null, firstNameIn, lastNameIn, null)
     * 3. By Account ID -   creaCustomer(null, null, null, accountIDIn)
     * 
     * @param customerIDIn
     * @param firstNameIn
     * @param lastNameIn
     * @param accountIDIn
     * 
     * @return user: A newly created Customer Class
     */
    public static Customer createCustomer(Integer customerIDIn, String firstNameIn, String lastNameIn, Integer accountIDIn) {
         // Find the row based on either ID or Name
        String[] columns = findRow(customerIDIn, firstNameIn, lastNameIn, accountIDIn);

        if (columns == null) return null; // Return null if the row (customer) wasn't found
    
    
        Customer User = new Customer(); // Create Customer object
    
        // Create and set a Person object for the user: Person(First Name, Last Name, Address)
        Person individual = new Person(columns[1].trim() + columns[2].trim(), columns[4].trim());
        User.setPerson(individual);
    
        // Create and set Checking account: Checking(CheckingID, CheckingBalance)
        Checking userChecking = new Checking(Integer.parseInt(columns[6]), Integer.parseInt(columns[7]));
        User.setAccount(userChecking, 0);
    
        // Create and set Savings account: Savings(SavingsID, SavingsBalance)
        Savings userSavings = new Savings(Integer.parseInt(columns[8]), Integer.parseInt(columns[9]));
        User.setAccount(userSavings, 1);
    
        // Create and set Credit account: Credit(CreditID, CreditBalance)
        Credit userCredit = new Credit(Integer.parseInt(columns[10]), Integer.parseInt(columns[11]));
        User.setAccount(userCredit, 2);
    
        return User;
    }

    /**
     * 
     * @param customerIDIn
     * @param firstNameIn
     * @param lastNameIn
     * @param accountIDIn
     * 
     * @return columns: An array of strings holding the contents of a single row from BankUsers.csv
     */
    public static String[] findRow(Integer customerIDIn, String firstNameIn, String lastNameIn, Integer accountIDIn) {
        try (BufferedReader reader = new BufferedReader(new FileReader("BankUsers"))) {
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
}