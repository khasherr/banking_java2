
package bankingaccount;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class for information View
 *
 * @author Heon Lee, Sher Khan
 */
public class InformationViewController extends Controller 
        implements Initializable {
    
    @FXML 
    private Label balanceInfo;  // RIGHT - balance enter
    
    @FXML 
    private Label feesInfo; // fees- enter 
    
    @FXML 
    private Label typeInfo; // type of account 
    
    @FXML 
    private Label accNumInfo; // number 
    
    @FXML 
    private Label ssnInfo;  //enter ssn info
    
    @FXML 
    private Button returnButton;  // return button 
    
    @FXML
    private AnchorPane informationView;
    
    
    
    /**
     * Initializer
     * @param url URL
     * @param rb ResourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
    
    /**
     * Getter for view
     *
     * @return Pane object of informationView.fxml
     */
    @Override
    public Pane getView() {
        return informationView;
    }
    
     /**
     * Event handler for return button
     *
     * @param event ActionEvent
     * @throws IOException IOException
     */
    @FXML
    private void handleReturn(ActionEvent event) throws IOException {
        getManagingController().addScreen("WelcomeView.fxml", this);
        getManagingController().removeScreen(this);
    }
    
    /**
     * Shows account information
     */
    public void message(){
        balanceInfo.setText(String.valueOf(getCurrent().getBalance()));
        feesInfo.setText(String.valueOf(getCurrent().getbankFees()));
        if(getCurrent() instanceof ChequeingAccount){
            typeInfo.setText("Chequeing");
        } else {
            typeInfo.setText("Saving");
        }
        accNumInfo.setText(getCurrent().getAccountNumber());
        ssnInfo.setText(getCurrent().getSSN());
    }
    
    
}
