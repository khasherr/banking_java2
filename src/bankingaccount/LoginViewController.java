/*
    Group: Group 2
    Name: Heon Lee, Sher Khan
    Assignment:  Final Project
    Program: Software Development and Network Engineering(Heon Lee)

    Description: This program is a simple banking program.

 */
package bankingaccount;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class for LoginView.fxml
 *
 * @author Heon Lee, Sher Khan
 */
public class LoginViewController extends Controller implements Initializable {

    @FXML
    private Pane loginview;
    @FXML
    private Button btnReturn;
    @FXML
    private Button login;
    @FXML
    private TextField idtf;
    @FXML
    private TextField pintf;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    /**
     * Getter for view
     * @return Pane object of loginview.fxml 
     */
    @Override
    public Pane getView() {
        return loginview;
    }

    /**
     * Event handler for Return button
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
     * Event handler for login button
     * Checks validity
     *
     * @param event ActionEvent
     * @throws IOException IOException
     */
    @FXML
    private void handleLogin(ActionEvent event) throws IOException {
        if (isNumeric(idtf.getText()) && idtf.getText().length() > 0) {
            this.readData();
            BankingAccount a = this.searchNumber(idtf.getText());
            this.setCurrent(a);

            if (a != null && pintf.getText().length() == 4
                    && a.getSSN().substring(5).equals(pintf.getText())) {
                //ID length must be greater than 0 and numeric
                //PIN must be last 4 digits of SSN
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Confirmation Dialog");
                alert.setHeaderText("Login Successful");
                alert.setContentText("Press OK to continue");
                alert.showAndWait();
                getManagingController().addScreen("WelcomeView.fxml", this);
                getManagingController().removeScreen(this);
            } else {
                if (a != null) {
                    if (pintf.getText().length() != 4
                            || !a.getSSN().substring(5).equals(pintf.getText())) {
                        //Pin is not 4 digits or the last 4 digits of ssn
                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("PIN error");
                        alert.setHeaderText("Please type PIN again");
                        alert.setContentText("Last 4 digits of your SSN");
                        alert.showAndWait();
                    }

                } else {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Dialog");
                    alert.setHeaderText("Account not found");
                    alert.setContentText("There is no such account in our database."
                            + "Sorry for the inconvenience.");
                    alert.showAndWait();
                    idtf.clear();
                    pintf.clear();
                }
            }
        } else if (idtf.getText().length() <= 0) {//User put  no id
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("ID Error");
            alert.setHeaderText("Please type ID");
            alert.setContentText("ID is your account number");
            alert.showAndWait();
        } else if(!isNumeric(idtf.getText())){
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("ID Error");
            alert.setHeaderText("Invalid ID");
            alert.setContentText("ID is your account number");
            alert.showAndWait();
        }
    }
}
