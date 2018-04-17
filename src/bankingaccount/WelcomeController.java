/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankingaccount;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;



/**
 * FXML Controller class
 *
 * @author Sher Khan
 */
public class WelcomeController extends Controller implements Initializable {
    @FXML
    private VBox welcomeView;
    @FXML
    private Label welcome;
    
    /*
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    @Override
    public Pane getView() {
        return welcomeView;
    }
    
    public void message(){
        welcome.setText("Welcome, " + this.getCurrent().getAccountHolder());
    }
    
    public void handleExit() throws IOException{
        File f = new File(".\\src\\bankingaccount\\db\\accounts.txt");
        if(f.exists()){
            f.delete();
        }
        for (int i = 0; i < getAccountList().size(); i++) {
            if(getAccountList().get(i) instanceof ChequeingAccount){
                writeData((ChequeingAccount) getAccountList().get(i));
            } else {
                writeData((SavingAccount) getAccountList().get(i));
            }
        }
        System.exit(0);
    }
    
    public void handleTransfer(ActionEvent event) throws IOException{
    }
    
    public void handleDeposit() throws IOException{
        getManagingController().addScreen("Deposit.fxml", this);
        getManagingController().removeScreen(this);
    }
    public void handleWithdraw() throws IOException{
        getManagingController().addScreen("Withdrawl.fxml", this);
        getManagingController().removeScreen(this);
    }
}
