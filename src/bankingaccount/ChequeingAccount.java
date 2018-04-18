/*
    Group: Group 2
    Name: Heon Lee, Sher Khan
    Assignment:  Final Project
    Program: Software Development and Network Engineering(Heon Lee)
    Date: 

    Description: This program is a simple banking program.

 */
package bankingaccount;

/**
 *
 * @author Sher Khan, Heon Lee
 */
public class ChequeingAccount extends BankingAccount{
    
    /**
     *  Constructor
     * @param accountNumber account number
     * @param accountHolder account holder name
     * @param openDate date opened
     * @param SSN ssn
     * @param accountHolderID account holder ID. Used for history file.
     * @param bankFees bank fees
     * @param balance balance
     */
    public ChequeingAccount(String accountNumber, String accountHolder, 
            String openDate, String SSN, int accountHolderID, double bankFees, 
            double balance) {
        super(accountNumber, accountHolder, openDate, SSN, accountHolderID, 
                bankFees, balance);
    }
    
    
    /**
     * Acts like a toString().
     * @return A formatted string including the information of this account.
     */
    public String appendCAData(){
        return this.appendData()+","+this.getBalance();
    }
    
    
}
