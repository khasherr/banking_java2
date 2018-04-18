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
 * FXML Controller class for mainView.fxml. The frame of all the other screens.
 *
 * @author Heon Lee, Sher Khan
 */
public class MainViewController implements Initializable {

    @FXML
    StackPane sp;

    @FXML
    FirstViewController firstViewController;

    /**
     * Initializes the controller class. Sets the managing controller of first
     * view controller to this controller.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        firstViewController.setManagingController(this);
    }

    /**
     * Add screen
     *
     * @param fxmlFile name of fxml File
     * @param sender Controller that initialized this method
     * @throws IOException IOException
     */
    public void addScreen(String fxmlFile, Controller sender) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent view = loader.load();//Parent class is a base class of all nodes
        //that have children
        Controller newC = loader.getController();
        //Gets the controller of new fxml file
        if (newC instanceof WelcomeController || newC instanceof DepositController
                || newC instanceof WithdrawlController) {
            newC.setCurrent(sender.getCurrent());
            newC.setAccountList(sender.getAccountList());
            if (newC instanceof WelcomeController) {
                ((WelcomeController) newC).message();
                ((WelcomeController) newC).showBalance();
            } else if (newC instanceof DepositController) {
                ((DepositController) newC).showBalance();
            }else if(newC instanceof WithdrawlController){
                ((WithdrawlController) newC).showBalance();
            }
        }
        sp.getChildren().add(view);

        newC.setParentController(sender);
        newC.setManagingController(this);
    }

    /**
     * Remove screen
     *
     * @param sender Controller object that called this method
     */
    public void removeScreen(Controller sender) {
        sp.getChildren().remove(sender.getView());
    }

}
