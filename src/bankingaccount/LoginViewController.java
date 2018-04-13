/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
 * FXML Controller class
 *
 * @author Heon Lee
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
     *
     * @return
     */
    @Override
    public Pane getView() {
        return loginview;
    }

    /**
     * Event handler for Return button
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
     * Event handler for login button
     *
     * @param event
     * @throws IOException
     */
    @FXML
    private void handleLogin(ActionEvent event) throws IOException {
        this.readData();
        BankingAccount a = this.searchAccount(idtf.getText());

        if (a != null && idtf.getText().length() > 0
                && pintf.getText().length() == 4
                && a.getAccountHolder().equals(idtf.getText())
                && a.getSSN().substring(5).equals(pintf.getText())) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("Login Successful");
            alert.setContentText("Press OK to continue");
            alert.showAndWait();
            getManagingController().addScreen("firstView.fxml", this);
            getManagingController().removeScreen(this);
        } else {
            if (a != null) {
                if (idtf.getText().length() <= 0) {
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("ID Error");
                    alert.setHeaderText("Please type ID");
                    alert.setContentText("ID is your name");
                    alert.showAndWait();
                } else if (pintf.getText().length() != 4
                        || !a.getSSN().substring(5).equals(pintf.getText())) {
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
    }

}
