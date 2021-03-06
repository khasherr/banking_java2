package bankingaccount;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Pane;

/**
 * An inheritance class for all controllers
 *
 * @author Heon Lee, Sher Khan
 */
public abstract class Controller {

    //variables
    private Controller parent;
    private MainViewController manage;
    //Controllers after login controller will keep track of these two 
    //variables
    private ArrayList<BankingAccount> accountList = new ArrayList<>();
    private BankingAccount current = null;

    /**
     * Get managing Controller
     *
     * @return return managing controller
     */
    public MainViewController getManagingController() {
        return manage;
    }

    /**
     * Sets the managing controller to the argument
     *
     * @param manage managing controller
     */
    public void setManagingController(MainViewController manage) {
        this.manage = manage;
    }

    /**
     * get Parent controller
     *
     * @return Parent controller
     */
    public Controller getParentController() {
        return parent;
    }

    /**
     * set parent controller
     *
     * @param parent Controller parent
     */
    public void setParentController(Controller parent) {
        this.parent = parent;
    }

    /**
     * Writes chequeing account data to a file
     *
     * @param a Chequeing Account
     * @throws IOException IOException
     */
    public void writeData(ChequeingAccount a) throws IOException {
        File f = new File(".\\src\\bankingaccount\\db\\accounts.txt");
        if (!f.exists()) { //if file exists
            f.createNewFile();
        }

        FileWriter fw = new FileWriter(f, true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.append(a.appendCAData());//Append data
        bw.newLine();
        bw.flush();
        bw.close();
    }

    /**
     * Writes saving account data to a file
     *
     * @param a Saving account
     * @throws IOException IOException
     */
    public void writeData(SavingAccount a) throws IOException {
        File f = new File(".\\src\\bankingaccount\\db\\accounts.txt");
        if (!f.exists()) {
            f.createNewFile();
        }

        FileWriter fw = new FileWriter(f, true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.append(a.appendSAData());
        bw.newLine();
        bw.flush();
        bw.close();
    }

    /**
     * Reads data from a text file and creates accounts using data from the file
     *
     * @throws FileNotFoundException FileNotFoundException
     * @throws IOException IOException IOException
     */
    public void readData() throws FileNotFoundException, IOException {
        accountList.clear();
        File f = new File(".\\src\\bankingaccount\\db\\accounts.txt");
        if (!f.exists()) {//If file does not exist
            //do nothing
        } else {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            while (line != null) {//Keep reading line by line
                makeAccount(line.split(","));//Create account according to the
                //information
                line = br.readLine();
            }

            br.close();
        }

        if (current != null) {
            for (int i = 0; i < accountList.size(); i++) {
                if (current.getAccountNumber().equals(accountList.get(i)
                        .getAccountNumber())) {
                    accountList.set(i, current);
                }
            }
        }
    }

    /**
     * Creates accounts and populates array list of accounts
     *
     * @param str array string containing account information
     */
    private void makeAccount(String[] str) {
        BankingAccount a;
        if (str.length == 7) {//If the account is chequeing account
            a = new ChequeingAccount(str[0], str[1], str[2], str[3],
                    Integer.parseInt(str[4]), Double.parseDouble(str[5]),
                    Double.parseDouble(str[6]));
        } else {//If account is saving account
            a = new SavingAccount(str[0], str[1], str[2], str[3],
                    Integer.parseInt(str[4]), Double.parseDouble(str[5]),
                    Double.parseDouble(str[6]), Double.parseDouble(str[7]));
        }
        accountList.add(a);//Add to the list

    }

    /**
     * Search account through array list of accounts
     *
     * @param name account holder name
     * @return account
     */
    public BankingAccount searchAccount(String name) {
        BankingAccount a = null;
        for (int i = 0; i < accountList.size(); i++) {
            if (accountList.get(i).getAccountHolder().equals(name)) {//If name equals
                //the account holder's name
                a = accountList.get(i);
            }
        }
        return a;
    }

    /**
     * Search account by account number
     *
     * @param num account number
     * @return searched account or null
     */
    public BankingAccount searchNumber(String num) {
        BankingAccount a = null;
        for (int i = 0; i < accountList.size(); i++) {
            if (accountList.get(i).getAccountNumber().equals(num)) {
                a = accountList.get(i);
            }
        }
        return a;
    }

    /**
     * Checks if string is numeric
     *
     * @param s takes a String
     * @return boolean
     */
    public boolean isNumeric(String s) {
        try {
            int i = Integer.parseInt(s);//This will throw exception if there
            //is a character
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }

    /**
     * Getter for account list
     *
     * @return an array list of all accounts in DB
     */
    public ArrayList<BankingAccount> getAccountList() {
        return accountList;
    }

    /**
     * Setter for account list
     *
     * @param arr an array list of accounts
     */
    public void setAccountList(ArrayList<BankingAccount> arr) {
        this.accountList = arr;
    }

    /**
     * Getter for the account logged in currently
     *
     * @return BankingAccount
     */
    public BankingAccount getCurrent() {
        return current;
    }

    /**
     * Setter for the account logged in currently
     *
     * @param current BankingAccount
     */
    public void setCurrent(BankingAccount current) {
        this.current = current;
    }

    /**
     * Checks if the given parameter is empty or not
     *
     * @param c Optional&lt;ButtonType&gt;
     * @return boolean
     */
    public boolean confirmationCatch(Optional<ButtonType> c) {
        try {
            c.get();//This will throw exception if c is empty
        } catch (NoSuchElementException e) {
            return true;
        }
        return false;
    }

    public void save() throws IOException {
        //If the user pressed OK
        File f = new File(".\\src\\bankingaccount\\db\\accounts.txt");
        if (f.exists()) {//If the file already exists
            f.delete();//delete it
        }
        //Writes updated account information to a new file
        FileWriter fw = new FileWriter(f, false);
        for (int i = 0; i < getAccountList().size(); i++) {
            if (getAccountList().get(i) instanceof ChequeingAccount) {
                ChequeingAccount ca = (ChequeingAccount)getAccountList().get(i);
                fw.write(ca.appendCAData());
                fw.write("\r\n");
                fw.flush();
            } else {
                SavingAccount sa = (SavingAccount)getAccountList().get(i);
                fw.write(sa.appendSAData());
                fw.write("\r\n");
                fw.flush();
            }
        }
        fw.close();
    }

    /**
     * Abstract class getView
     *
     * @return A pane of the Controller's View
     */
    public abstract Pane getView();
}
