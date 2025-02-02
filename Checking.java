import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Concrete class representation of a checking account in a bank.
 * 
 * Provides implementation for the abstract methods such as collecting amount
 * from user,
 * deposit function, withdraw function, and money transferring.
 * 
 * @author Blaine
 */
public class Checking extends Account {
    /** Fee for overdrafting balance */
    private double overDraftFee = 50.0;

    // Use a single Scanner instance for user input throughout the class.
    private static final Scanner scanner = new Scanner(System.in);

    public Checking(int accountIDIn, double balanceIn) {
        super(accountIDIn, balanceIn);
    }

    /**
     * Collects a positive double from user.
     * 
     * @return amount: the double collected
     */
    @Override
    public double collectAmount() {
        double amount = 0;

        while (true) {
            System.out.print("Enter an amount:\n$ ");

            // Check if input is double
            if (scanner.hasNextDouble()) {
                amount = scanner.nextDouble();

                // Check if input is positive
                if (amount >= 0) {
                    break;
                } else {
                    System.out.println("Invalid input. Input was not positive or zero.");
                }
            } else {
                System.out.println("Invalid input. Input was not a double.");
                scanner.next(); // Clear invalid input
            }
        }
        return amount;
    }

    /** Adds amount inputted to balance. */
    @Override
    public void deposit() {
        double amount = collectAmount();
        this.setBalance(this.getBalance() + amount);
        System.out.println("Process Success: Current Balance = " + this.getBalance());

        String transactionDetails = "Deposited $" + amount + " into account ID: " + this.getAccountID();
        logTransaction(transactionDetails);

        updateBalanceInCSV(null, null, null, this.getAccountID(), this.getBalance());
    }

    /**
     * Removes amount inputted from balance.
     * Applies an overdraft fee if balance after withdrawal is below 0.
     */
    @Override
    public void withdraw() {
        double amount = collectAmount();

        // Allow withdrawal including overdraft consideration
        if (amount <= this.getBalance() + overDraftFee) {
            this.setBalance(this.getBalance() - amount);

            if (this.getBalance() < 0) {
                System.out.println("Overdraft Fee added: " + this.overDraftFee);
                this.setBalance(this.getBalance() - this.overDraftFee);
            }

            System.out.println("Process Success: Current Balance = " + this.getBalance());

            // Log the withdrawal transaction
            String transactionDetails = "Withdrew $" + amount + " from account ID: " + this.getAccountID();
            logTransaction(transactionDetails);

            updateBalanceInCSV(null, null, null, this.getAccountID(), this.getBalance());

        } else {
            System.out.println("Insufficient funds, including overdraft consideration.");
        }
    }

    /** Transfers money from account to recipient's account. */
    @Override
    public void transferMoney(Account recipient) {
        double amount = amountWithinBalance();

        this.setBalance(this.getBalance() - amount);
        recipient.setBalance(recipient.getBalance() + amount);

        System.out.println("Process Success: Current Balance = " + this.getBalance());

        // Log the transfer transaction
        String transactionDetails = "Transferred $" + amount + " from account ID: " + this.getAccountID() +
                " to account ID: " + recipient.getAccountID();
        logTransaction(transactionDetails);

        updateBalanceInCSV(null, null, null, this.getAccountID(), this.getBalance());
        updateBalanceInCSV(null, null, null, recipient.getAccountID(), recipient.getBalance());
    }

    /** Collects a positive double within the account's balance. */
    private double amountWithinBalance() {
        double amount;

        // Loop to verify that amount is not greater than balance
        while (true) {
            amount = collectAmount();

            if (amount <= this.getBalance()) {
                break;
            } else {
                System.out.println("Invalid input. Amount larger than balance.");
            }
        }

        return amount;
    }

    /** logs the transaction details into TransactionLog.txt */
    private void logTransaction(String transactionDetails) {
        try (FileWriter writer = new FileWriter("TransactionLog.txt", true)) {
            writer.write("\n" + transactionDetails);
        } catch (IOException e) {
            System.out.println("log error");
        }
    }

    public static void updateBalanceInCSV(Integer customerIDIn, String firstNameIn, String lastNameIn,
            Integer accountIDIn, double newBalance) {
        List<String> fileContent = new ArrayList<>();
        String line;
        boolean balanceUpdated = false;

        try (BufferedReader reader = new BufferedReader(new FileReader("BankUsers.csv"))) {
            // Read the CSV file line by line
            String header = reader.readLine();
            fileContent.add(header); // Keep header row as is

            while ((line = reader.readLine()) != null) {
                String[] columns = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                boolean isMatch = false;

                // Check by ID if customerIDIn is provided
                if (customerIDIn != null) {
                    int customerID = Integer.parseInt(columns[0].trim());
                    if (customerID == customerIDIn) {
                        isMatch = true;
                    }
                }

                // Check by Name if firstNameIn and lastNameIn are provided
                if (!isMatch && firstNameIn != null && lastNameIn != null) {
                    String firstName = columns[1].trim();
                    String lastName = columns[2].trim();
                    if (firstName.equals(firstNameIn) && lastName.equals(lastNameIn)) {
                        isMatch = true;
                    }
                }

                // Check by account number if account number is provided
                if (!isMatch && accountIDIn != null) {
                    int checkingNumber = Integer.parseInt(columns[6].trim());
                    int savingsNumber = Integer.parseInt(columns[8].trim());
                    int creditNumber = Integer.parseInt(columns[10].trim());

                    if (checkingNumber == accountIDIn || savingsNumber == accountIDIn || creditNumber == accountIDIn) {
                        isMatch = true;
                    }
                }

                // If a match is found, update the balance for the appropriate account
                if (isMatch && !balanceUpdated && accountIDIn != null) {
                    int checkingNumber = Integer.parseInt(columns[6].trim());
                    int savingsNumber = Integer.parseInt(columns[8].trim());
                    int creditNumber = Integer.parseInt(columns[10].trim());

                    if (checkingNumber == accountIDIn) {
                        columns[7] = String.valueOf(newBalance); // Update Checking Balance (column 7)
                    } else if (savingsNumber == accountIDIn) {
                        columns[9] = String.valueOf(newBalance); // Update Savings Balance (column 9)
                    } else if (creditNumber == accountIDIn) {
                        columns[12] = String.valueOf(newBalance); // Update Credit Balance (column 12)
                    }
                    balanceUpdated = true; // Mark that the balance update occurred
                }

                // Join the updated columns back into a single line
                fileContent.add(String.join(",", columns));
            }
        } catch (IOException e) {
            System.out.println("Error reading customer data from file: " + e.getMessage());
            return;
        }

        // Write the updated content back to the CSV file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("BankUsers.csv"))) {
            for (String contentLine : fileContent) {
                writer.write(contentLine);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving updated customer data to file: " + e.getMessage());
        }

        if (!balanceUpdated) {
            System.out.println("No matching account found. No balance updated.");
        }
    }
}
