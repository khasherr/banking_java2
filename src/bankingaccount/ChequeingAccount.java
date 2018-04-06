/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankingaccount;

import java.util.ArrayList;

/**
 *
 * @author Sher Khan
 */
public class ChequeingAccount extends BankingAccount{

    public ChequeingAccount(String accountNumber, String accountHolder, String openDate, String SSN, int accountHolderID, ArrayList<String> history, double bankFees, double balance) {
        super(accountNumber, accountHolder, openDate, SSN, accountHolderID, history, bankFees, balance);
    }

    @Override
    public double getbalance() {
        super.calculateBalanceWithFees();
        return super.balance;
    }
    
    
}
