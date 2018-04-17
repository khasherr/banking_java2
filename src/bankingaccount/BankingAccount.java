package bankingaccount;

import java.util.ArrayList;

/**
 * An inheritance class for account classes
 * @author Sher Khan
 */
public abstract class BankingAccount {

    //variables
    private String accountNumber;
    private String accountHolder;
    private String openDate;
    private String SSN; // social security number 
    private static int accountHolderID = 100; // intialized it 100\ 
    private ArrayList<String> history = new ArrayList<>();
    private double bankFees;
    private double balance;

    //constructor 
    /**
     * Constructor for BankingAccount
     *
     * @param accountNumber account number
     * @param accountHolder name
     * @param openDate open date
     * @param SSN ssn
     * @param accountHolderID account holder ID. This will be used for history
     * file
     * @param bankFees Account fees
     * @param balance balance
     */
    public BankingAccount(String accountNumber, String accountHolder,
            String openDate, String SSN, int accountHolderID,
            double bankFees, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.openDate = openDate;
        this.SSN = SSN;
        this.accountHolderID += this.accountHolderID++;
        this.bankFees = bankFees;
        this.balance = balance;
    }

    //Withdrawl
    /**
     * withdraw from this account
     *
     * @param amount double
     * @return boolean. Indicates if withdrawl was successful or not.
     */
    public boolean withdrawl(double amount) {
        if (amount > 0 && balance - amount >= 0) {
            balance = balance - amount;
            addHistory("You withdrew" + amount + " and your balance is " + balance);
            return true;
        } else {
            return false;
        }
    }

    //Deposit
    /**
     * Deposit to this account
     *
     * @param amount double
     */
    public void deposit(double amount) {
        balance = balance + amount;
        addHistory("You added " + amount + " and your balance is " + balance);
    }

    // Transfer between account 
    /**
     * Transfer
     *
     * @param sender BankingAccount
     * @param reciever BankingAccount
     * @param amount double
     */
    public void transfer(BankingAccount sender, BankingAccount reciever, double amount) {
        sender.withdrawl(amount);
        reciever.deposit(amount);

        addHistory("You transfered " + amount + " and your balance is " + balance);
    }

    //calculates fees with balance
    /**
     * Subtracts fee from this account's balance
     */
    public void calculateBalanceWithFees() {
        balance -= this.bankFees;

    }

    //SETTERS
    // accountnumber = ID + RANDOM + FIRST 2 DIGIT OF SOCIAL SECURITY NUMBER
    // check if this works !
    /**
     * setter for account number
     */
    public void setAccountNumber() {
        int random = (int) Math.random() * 100;
        accountNumber = accountHolderID + " " + random + SSN.substring(0, 2);
    }

    /**
     * setter for balance
     *
     * @param balance double. New balance
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    // GETTERS 
    /**
     * Getter for account number
     *
     * @return This account's account number
     */
    public String getAccountNumber() {
        return this.accountNumber;
    }

    /**
     * Getter for account holder name
     * @return name of the owner
     */
    public String getAccountHolder() {
        return this.accountHolder;
    }
    /**
     * Getter for date opened
     * @return Date opened in string
     */
    public String getopenDate() {
        return this.openDate;
    }
    /**
     * getter for ssn
     * @return SSN
     */
    public String getSSN() {
        return this.SSN;
    }
    /**
     * getter for account holder ID
     * @return integer
     */
    public int getaccountHolderID() {
        return this.accountHolderID;
    }

    /**
     * getter for history
     * @return String containing all history logs
     */
    public String gethistory() {
        String output = "";
        for (String element : history) {
            output += element + "\n";

        }
        return output;
    }
    /**
     * Add new history
     * @param input String
     */
    public void addHistory(String input) {
        history.add(input);
    }
    /**
     * Getter for bank fees
     * @return this.bankFees
     */
    public double getbankFees() {
        return this.bankFees;
    }
    /**
     * getter for balance
     * @return this.balance
     */
    public double getBalance() {
        return this.balance;
    }
    /**
     * Acts like a toString()
     * @return A string containing all the information of this account
     */
    public String appendData() {
        return this.getAccountNumber() + "," + this.getAccountHolder() + ","
                + this.getopenDate() + "," + this.getSSN() + ","
                + this.getaccountHolderID() + "," + this.getbankFees();
    }

}
