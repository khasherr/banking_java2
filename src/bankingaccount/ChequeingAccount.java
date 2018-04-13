/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankingaccount;

/**
 *
 * @author Sher Khan
 */
public class ChequeingAccount extends BankingAccount{
    
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
    public ChequeingAccount(String accountNumber, String accountHolder, String openDate, String SSN, int accountHolderID, double bankFees, double balance) {
        super(accountNumber, accountHolder, openDate, SSN, accountHolderID, bankFees, balance);
    }
    
    
    
    public String appendCAData(){
        return this.appendData()+","+this.getbalance();
    }
    
    
    /**
     * 
     * @return balance
     */
  
    @Override
    public double getbalance() {
        super.calculateBalanceWithFees();
        return super.balance;
    }
    
    
}
