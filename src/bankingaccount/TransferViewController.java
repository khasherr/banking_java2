
package bankingaccount;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * FXML controller class for transferview.fxml
 *
 * @author Sher Khan, Heon Lee
 */
public class TransferViewController extends Controller implements Initializable {

    @FXML
    private AnchorPane transferView;

    @FXML
    private TextField amounttf; // RIGHT - amount transfer 

    @FXML
    private TextField Reaccount; // recieve account

    @FXML
    private TextField name; // recieve name 

    @FXML
    private Button returnButton;  // return button 
    
    @FXML
    private Label balancetf;
    /**
     * Initialize
     *
     * @param url URL
     * @param rb ResourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    
    /**
     * Sets a label so that the label shows the balance
     */
    public void showBalance(){
        balancetf.setText("Your Balance: " + 
                String.valueOf(getCurrent().getBalance()));
    }

    /**
     * Getter for view
     *
     * @return Pane object of TransferView.fxml
     */
    @Override
    public Pane getView() {
        return transferView;
    }

    /**
     * Event handler for submit button
     *
     * @throws java.io.IOException IOException
     */
    public void handleTransfer(ActionEvent event) throws IOException {
        double amount = 0;
        boolean err = false;
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        //If user typed nothing
        try {
            amount = Double.parseDouble(amounttf.getText());
        } catch (NumberFormatException ex) {
            err = true;
            Alert warning = new Alert(Alert.AlertType.WARNING);
            warning.setHeaderText("Please enter a valid amount");
            warning.showAndWait();
            amounttf.clear();
        }
        //If account number is not numeric
        if (!isNumeric(Reaccount.getText())) {
            err = true;
            Alert warning = new Alert(Alert.AlertType.WARNING);
            warning.setHeaderText("Please enter a valid account number");
            warning.showAndWait();
            amounttf.clear();
        } else if (Reaccount.getText().length() <= 0) {//User put no account number
            err = true;
            alert.setTitle("Account number Error");
            alert.setHeaderText("Please type the receiver's account number");
            alert.showAndWait();
        } else if (searchAccount(name.getText()) == null || 
                this.searchNumber(Reaccount.getText()) == null) {
            err = true;
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Receiver account not found");
            alert.setContentText("There is no such account in our database."
                    + "Sorry for the inconvenience.");
            alert.showAndWait();
            name.clear();
            Reaccount.clear();
        } else if (name.getText().length() <= 0){
            err = true;
            alert.setTitle("Receiver name Not Given");
            alert.setHeaderText("Please type your name");
            alert.showAndWait();
        } else if (!name.getText().matches("[ a-zA-Z]+")){
            //If there is a digit or special characters
            err = true;
            alert.setHeaderText("Name Error");
            alert.setContentText("No digit allowed\nNo Special Characters allowed");
            alert.showAndWait();
        } else if (Reaccount.getText().length() != 8){
            err = true;
            alert.setHeaderText("Account Error");
            alert.setContentText("Account number must be 8 digits");
            alert.showAndWait();
        } else if (!searchAccount(name.getText()).getAccountNumber()
                .equals(Reaccount.getText())){
            err = true;
            alert.setHeaderText("Account Error");
            alert.setContentText("Please type proper accout number and name");
            alert.showAndWait();
        } else if (getCurrent().getAccountNumber().equals(Reaccount.getText()) ||
                getCurrent().getAccountHolder().equals(name.getText())){
            err = true;
            alert.setHeaderText("Account Error");
            alert.setContentText("Please type proper accout number and name");
            alert.showAndWait();
        }

        if (!err) {
            Alert alertc = new Alert(Alert.AlertType.CONFIRMATION);
            alertc.setTitle("TRANSFER");
            alertc.setHeaderText("TRANSFER: $" + amounttf.getText()
                    + " TO: " + Reaccount.getText() + " " + name.getText());
            Optional<ButtonType> a = alertc.showAndWait();
            if (!confirmationCatch(a) && a.get() == ButtonType.OK && amount > 0) {
                //If user pressed OK and the entered anmount is greater than 0
                if (getCurrent().transfer(this.searchAccount(name.getText()), amount)
                        == false) {
                    //If balance is insufficient
                    Alert balance = new Alert(Alert.AlertType.INFORMATION);
                    balance.setTitle("Insufficent");
                    balance.setHeaderText("Your balance: " + getCurrent()
                            .getBalance());
                    balance.showAndWait();
                } else {
                    Alert alertX = new Alert(Alert.AlertType.INFORMATION);
                    alertX.setTitle("Transfer");
                    alertX.setHeaderText("Transfer Complete");
                    alertX.setContentText("Your Balance: " + getCurrent()
                            .getBalance());
                    alertX.showAndWait();
                    save();
                    readData();
                    getManagingController().addScreen("WelcomeView.fxml", this);
                    getManagingController().removeScreen(this);
                }
            } else if (amount <= 0) {
                Alert warning = new Alert(Alert.AlertType.WARNING);
                warning.setHeaderText("Please enter a valid amount");
                warning.showAndWait();
            }
        }
    }
    
    /**
     * Event handler for return button
     *
     * @param event ActionEvent
     * @throws IOException
     */
    @FXML
    private void handleReturn(ActionEvent event) throws IOException {
        getManagingController().addScreen("WelcomeView.fxml", this);
        getManagingController().removeScreen(this);
    }

}
