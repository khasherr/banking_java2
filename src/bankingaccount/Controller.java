/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankingaccount;

import bankingaccount.BankingAccount;
import bankingaccount.ChequeingAccount;
import bankingaccount.MainViewController;
import bankingaccount.SavingAccount;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javafx.scene.layout.Pane;

/**
 *
 * @author leedy
 */
public abstract class Controller {
    private Controller parent;
    private MainViewController manage;
    private ArrayList<BankingAccount> arr = new ArrayList<>();
    
    public MainViewController getManagingController() {
        return manage;
    }
    
    public void setManagingController(MainViewController manage){
        this.manage = manage;
    }
    public Controller getParentController(){
        return parent;
    }
    
    public void setParentController(Controller parent){
        this.parent = parent;
    }
    
    public void writeData(ChequeingAccount a) throws IOException{
        File f = new File(".\\src\\bankingaccount\\db\\accounts.txt");
        if(!f.exists()){
            f.createNewFile();
        }
        
        FileWriter fw = new FileWriter(f, true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.append(a.appendCAData());
        bw.newLine();
        bw.flush();
        bw.close();
    }
    
    public void writeData(SavingAccount a) throws IOException{
        File f = new File(".\\src\\bankingaccount\\db\\accounts.txt");
        if(!f.exists()){
            f.createNewFile();
        }
        
        FileWriter fw = new FileWriter(f, true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.append(a.appendSAData());
        bw.newLine();
        bw.flush();
        bw.close();
    }
    
    public void readData() throws FileNotFoundException, IOException{
        File f = new File(".\\src\\bankingaccount\\db\\accounts.txt");
        if(!f.exists()){
        } else {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            while(line != null){
                makeAccount(line.split(","));
                line = br.readLine();
            }
            
            br.close();
        }
    }
    
    private void makeAccount(String[] str){
        BankingAccount a;
        if(str.length == 7){
            a = new ChequeingAccount(str[0],str[1],str[2],str[3],
                    Integer.parseInt(str[4]), Double.parseDouble(str[5]),
                    Double.parseDouble(str[6]));
        } else {
            a = new SavingAccount(str[0],str[1],str[2],str[3],
                    Integer.parseInt(str[4]), Double.parseDouble(str[5]),
                    Double.parseDouble(str[6]),Double.parseDouble(str[7]));
        }
        
        arr.add(a);
        
    }
    
    public BankingAccount searchAccount(String name){
        BankingAccount a = null;
        for (int i = 0; i < arr.size(); i++) {
            if(arr.get(i).getAccountHolder().equals(name)){
                a = arr.get(i);
            }
        }
        return a;
    }
    public abstract Pane getView();
}
