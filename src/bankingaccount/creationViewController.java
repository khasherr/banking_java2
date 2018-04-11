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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;

/**
 *
 * @author leedy
 */
public class creationViewController extends Controller implements Initializable{
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
    @Override
    public Pane getView() {
        return creationView;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    
    @FXML
    private void handleReturn(ActionEvent event) throws IOException{
        getManagingController().addScreen("firstView.fxml", this);
        getManagingController().removeScreen(this);
    }
    
    
}
