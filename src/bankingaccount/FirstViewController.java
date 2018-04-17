
package bankingaccount;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class for firstView.fxml
 *
 * @author Heon Lee
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
    
    /**
     * Event handler for login button
     * @param event Button Click
     * @throws IOException IOException
     */
    @FXML
    public void handleLogin(ActionEvent event) throws IOException {
        getManagingController().addScreen("LoginView.fxml", this);
        getManagingController().removeScreen(this);

    }
    /**
     * Event handler for new account button
     * @param event Button click
     * @throws IOException IOException
     */
    @FXML
    public void handleCreation(ActionEvent event) throws IOException {
        getManagingController().addScreen("accountCreationView.fxml", this);
        getManagingController().removeScreen(this);
    }
    /**
     * Getter for view
     * @return Pane object of firstView.fxml 
     */
    @Override
    public Pane getView() {
        return firstView;
    }
    
    /**
     * Event handler for exit button. Exits the program.
     * @param event ActionEvent button click
     * @throws IOException IOException
     */
    @FXML
    public void handleExit(ActionEvent event) throws IOException {
        System.exit(0);//Exits the program
    }
}
