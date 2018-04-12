/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankingaccount;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author leedy
 */
public class MainViewController implements Initializable {
    @FXML
    StackPane sp;
    
    @FXML
    FirstViewController firstViewController;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        firstViewController.setManagingController(this);
    }    
    
    public void addScreen(String fxmlFile, Controller sender) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent view = loader.load();//Parent class is a base class of all nodes
        //that have children
        Controller newC = loader.getController();
        sp.getChildren().add(view);
        
        newC.setParentController(sender);
        newC.setManagingController(this);
    }
    
    public void removeScreen(Controller sender){
        sp.getChildren().remove(sender.getView());
    }
   
    

    
}
