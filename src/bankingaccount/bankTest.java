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
public class bankTest {
    public static void main (String args[]) { 
        ChequeingAccount a = new ChequeingAccount("123456", "23456","01.03.2016","100202020",234567,4.0,10.0);
          System.out.println(a.getbalance());
        
                }
    
}
