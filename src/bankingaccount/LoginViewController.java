/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankingaccount;

import bankingaccount.BankingAccount;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author leedy
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

    @Override
    public Pane getView() {
        return loginview;
    }

    @FXML
    private void handleReturn(ActionEvent event) throws IOException {
        getManagingController().addScreen("firstView.fxml", this);
        getManagingController().removeScreen(this);
    }

    @FXML
    private void handleLogin(ActionEvent event) throws IOException {
        this.readData();
        BankingAccount a = this.searchAccount(idtf.getText());

        if (a.getAccountHolder().equals(idtf.getText())
                && a.getSSN().substring(5).equals(pintf.getText())) {
            getManagingController().addScreen("firstView.fxml", this);
            getManagingController().removeScreen(this);
        } else {
            //Error
        }
    }

}
