abstract class Account {
    public String accountID;
    private double balance;

    public void deposit(double amount) {
        this.balance += amount;
    }

    public void withdraw(double amount) {
        // assumption that cash is being withdrawn (no implementation)
        if (amount >= this.balance) {
            this.balance -= amount;
            System.out.print("Process Success:");
            this.checkBalance();
        }
        else System.out.println("Process Failure: Amount Larger than Balance");
    }

    public void checkBalance() {
        System.out.printf(" Current Balance - ", this.balance);
    }

    public void transferMoney(Account a, double amount) {
        if (amount >= this.balance) {
            this.balance -= amount;
            a.balance += amount;
        }
        else System.out.println("Proess Failure: Amount Larger than Balance");
    }
}