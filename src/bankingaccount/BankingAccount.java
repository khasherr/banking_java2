/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankingaccount;

import java.util.ArrayList;

// Hello, it's Heon Lee
/**
 *
 * @author Sher Khan
 */
public abstract class BankingAccount {

    private String accountNumber;
    private String accountHolder;
    private String openDate;
    private String SSN; // social security number 
    private static int accountHolderID = 100; // intialized it 100\ 
    private ArrayList<String> history;
    private double bankFees;
    protected double balance; //bc we access through othher classes inherited

    //constructor 
    /**
     *
     * @param accountNumber
     * @param accountHolder
     * @param openDate
     * @param SSN
     * @param accountHolderID
     * @param bankFees
     * @param balance
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
        this.history = new ArrayList<String>();
    }

    //Withdrawl
    /**
     *
     * @param amount
     * @return
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
     *
     * @param amount
     */
    public void deposit(double amount) {
        balance = balance + amount;
        addHistory("You added " + amount + " and your balance is " + balance);
    }

    // Transfer between account 
    /**
     *
     * @param sender
     * @param reciever
     * @param amount
     */
    public void transfer(BankingAccount sender, BankingAccount reciever, double amount) {
        sender.withdrawl(amount);
        reciever.deposit(amount);

        addHistory("You transfered " + amount + " and your balance is " + balance);
    }

    //calculates fees with balance
    /**
     *
     */
    public void calculateBalanceWithFees() {
        balance -= this.bankFees;

    }

    //SETTERS
    // accountnumber = ID + RANDOM + FIRST 2 DIGIT OF SOCIAL SECURITY NUMBER
    // check if this works !
    public void setAccountNumber() {
        int random = (int) Math.random() * 100;
        accountNumber = accountHolderID + " " + random + SSN.substring(0, 2);
    }

    // GETTERS 
    public String getAccountNumber() {
        return this.accountNumber;
    }

    public String getAccountHolder() {
        return this.accountHolder;
    }

    public String getopenDate() {
        return this.openDate;
    }

    public String getSSN() {
        return this.SSN;
    }

    public int getaccountHolderID() {
        return this.accountHolderID;
    }

    /**
     *
     * @return
     */
    public String gethistory() {
        String output = "";
        for (String element : history) {
            output += element + "\n";

        }
        return output;
    }

    public void addHistory(String input) {
        history.add(input);
    }

    public double getbankFees() {
        return this.bankFees;
    }

    public String appendData() {
        return this.getAccountNumber() + "," + this.getAccountHolder() + ","
                + this.getopenDate() + "," + this.getSSN() + ","
                + this.getaccountHolderID() + "," + this.getbankFees();
    }

    public abstract double getbalance();

}
