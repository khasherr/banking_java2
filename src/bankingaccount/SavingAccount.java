/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankingaccount;

import java.util.ArrayList;

public class SavingAccount extends BankingAccount implements IInterest {

    private double interest;
    
    /**
     * 
     * @param accountNumber
     * @param accountHolder
     * @param openDate
     * @param SSN
     * @param accountHolderID
     * @param history
     * @param bankFees
     * @param balance
     * @param interest 
     */
    public SavingAccount(String accountNumber, String accountHolder, 
            String openDate, String SSN, int accountHolderID, double bankFees, 
            double balance, double interest) {
        super(accountNumber, accountHolder, openDate, SSN, accountHolderID, bankFees, balance);
        
        this.interest = interest;
    }

    /**
     * 
     * @return 
     */
    @Override
    public double getbalance() {
        super.calculateBalanceWithFees();
        calculateInterestWithBalance();
        return super.balance;
    }
    
    /**
     * 
     */
    @Override
    public void calculateInterestWithBalance() {
       this.balance += this.balance * interest;
    
    }
    
    public String appendSAData(){
        return this.appendData()+","+this.getbalance()+","+interest;
    }

    
    
    
    
}
