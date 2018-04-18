package bankingaccount;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;

/**
 * Controller for accountCreationView.fxml
 *
 * @author Heon Lee
 */
public class creationViewController extends Controller implements Initializable {

    @FXML
    private Pane creationView;
    @FXML
    private TextField nametf;
    @FXML
    private TextField ssntf;
    @FXML
    private ToggleGroup tg;
    @FXML
    private Button btnSubmit;
    @FXML
    private Button btnReturn;

    /**
     * Return the pane object
     *
     * @return Pane object of accountcreationview.fxml
     */
    @Override
    public Pane getView() {
        return creationView;
    }

    /**
     * Initialize the controller
     *
     * @param location URL
     * @param resources ResourceBundle
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    /**
     * Event handler for return button
     *
     * @param event ActionEvent
     * @throws IOException
     */
    @FXML
    private void handleReturn(ActionEvent event) throws IOException {
        getManagingController().addScreen("firstView.fxml", this);
        getManagingController().removeScreen(this);
    }

    /**
     * Event handler for submit button
     * Checks validity
     *
     * @param event ActionEvent
     * @throws IOException
     */
    @FXML
    private void handleSubmit(ActionEvent event) throws IOException {
        String type = null;
        try {
            type = ((RadioButton) tg.getSelectedToggle()).getText();
        } catch (NullPointerException ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Account Type Error");
            alert.setHeaderText("Please select your account type");
            alert.showAndWait();
        }

        String name = nametf.getText();
        String ssn = ssntf.getText();
        String time = LocalDateTime.now().toString();
        Alert alert = new Alert(AlertType.INFORMATION);
        readData();
        BankingAccount a = this.searchAccount(name);
        if (type != null && name.length() > 0 && name.matches("[ a-zA-Z]+")
                && ssn.length() == 9
                && isNumeric(ssn)
                && a == null) {
            //No created account
            //SSN is length of 9
            //ssn is numeric
            //name length is greater than 0
            //name does not contain any digits
            if (type.equals("Savings")) {
                Alert fee = new Alert(AlertType.CONFIRMATION);
                fee.setHeaderText("Saving Account fee is $1 and the interest"
                        + "rate will be 2%");
                Optional<ButtonType> c = fee.showAndWait();
                if (confirmationCatch(c) == false && c.get() == ButtonType.OK) {
                    SavingAccount sa = new SavingAccount(accountnumGenerator(), name, time,
                            ssn, 0, 0.0, 0.0, 2.0);
                    this.writeData(sa);
                    Alert num = new Alert(AlertType.INFORMATION);
                    num.setContentText("Your account number is " + sa.getAccountNumber());
                    num.showAndWait();
                }
            } else {
                ChequeingAccount ca = new ChequeingAccount(accountnumGenerator(), name, time,
                        ssn, 0, 0.0, 0.0);
                this.writeData(ca);
                Alert num = new Alert(AlertType.INFORMATION);
                num.setContentText("Your account number is " + ca.getAccountNumber());
                num.showAndWait();
            }

            alert.setTitle("Account Creation successful");
            alert.setHeaderText("Account Creation successful");
            alert.showAndWait();
            getManagingController().addScreen("firstView.fxml", this);
            getManagingController().removeScreen(this);
        } else if (nametf.getText().length() <= 0) {
            alert.setTitle("Name Not Given");
            alert.setHeaderText("Please type your name");
            alert.showAndWait();
        } else if (ssntf.getText().length() != 9) {
            alert.setTitle("SSN length error");
            alert.setHeaderText("SSN must be 9 digits");
            alert.showAndWait();
        } else if (!isNumeric(ssntf.getText())) {
            alert.setTitle("SSN Error");
            alert.setHeaderText("SSN must be numeric");
            alert.showAndWait();
        } else if (a != null) {
            if (a instanceof ChequeingAccount) {
                alert.setHeaderText("Chequeing account Exists with the following name");
            } else {
                alert.setHeaderText("Saving account Exists with the following name");
            }
            alert.showAndWait();
        } else if (!name.matches("[ a-zA-Z]+")) {
            //If there is a digit or special characters
            alert.setHeaderText("Name Error");
            alert.setContentText("No digit allowed\nNo Special Characters allowed");
            alert.showAndWait();
        }
    }

    /**
     * Generates 8 digit account number
     *
     * @return 8 digit account number
     */
    private static String accountnumGenerator() {
        return String.valueOf((int) (Math.random() * (99999999 - 10000000) + 10000000));
    }

}
