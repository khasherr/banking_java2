/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankingaccount;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author leedy
 */
public class FirstViewController extends Controller implements Initializable {
    @FXML
    private Pane firstView;
    @FXML
    private Button btnLogin;
    @FXML
    private Button btnCreation;
    @FXML
    private Button btnExit;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    } 
    /*
    @FXML
    public void handleLogin(ActionEvent event) throws IOException{
        getManagingController().addScreen("", this);
        
    }
    */
    @FXML
    public void handleCreation(ActionEvent event) throws IOException{
        getManagingController().addScreen("accountCreationView.fxml", this);
        getManagingController().removeScreen(this);
    }

    @Override
    public Pane getView() {
        return firstView;
    }
    
    @FXML
    public void handleExit(ActionEvent event) throws IOException{
        Platform.exit();
    }
    
    
}
