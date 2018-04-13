/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankingaccount;


import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;

/**
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
     * @return
     */
    @Override
    public Pane getView() {
        return creationView;
    }

    /**
     * Initialize the controller
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    /**
     *
     * @param event
     * @throws IOException
     */
    @FXML
    private void handleReturn(ActionEvent event) throws IOException {
        getManagingController().addScreen("firstView.fxml", this);
        getManagingController().removeScreen(this);
    }

    /**
     *
     * @param event
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
        if (type != null && nametf.getText().length() > 0
                && ssntf.getText().length() == 9
                && isNumeric(ssntf.getText())) {
            if (type.equals("Savings")) {
                SavingAccount sa = new SavingAccount(accountnumGenerator(), name, time,
                        ssn, 0, 0.0, 0.0, 2.0);
                this.writeData(sa);
            } else {
                ChequeingAccount ca = new ChequeingAccount(accountnumGenerator(), name, time,
                        ssn, 0, 0.0, 0.0);
                this.writeData(ca);
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
        } else if (!isNumeric(ssntf.getText())){
            alert.setTitle("SSN Error");
            alert.setHeaderText("SSN must be numeric");
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
