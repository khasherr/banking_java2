
package bankingaccount;

/**
 * 
 * @author Sher Khan
 */
public class SavingAccount extends BankingAccount implements IInterest {

    private double interest;
    
    /**
     * Constructor
     * @param accountNumber account number
     * @param accountHolder account holder
     * @param openDate date opened
     * @param SSN ssn
     * @param accountHolderID account holder ID
     * @param bankFees bank fee
     * @param balance balance
     * @param interest interest rate
     */
    public SavingAccount(String accountNumber, String accountHolder, 
            String openDate, String SSN, int accountHolderID, double bankFees, 
            double balance, double interest) {
        super(accountNumber, accountHolder, openDate, SSN, accountHolderID,
                bankFees, balance);
        
        this.interest = interest;
    }
    
    /**
     * Calculates balance with interest
     */
    @Override
    public void calculateInterestWithBalance() {
       setBalance(getBalance() * interest);
    }
   /**
     * Acts like a toString().
     * @return A formatted string including the information of this account.
     */
    public String appendSAData(){
        return this.appendData()+","+getBalance()+","+interest;
    }

    
    
    
    
}
