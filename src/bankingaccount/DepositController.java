
package bankingaccount;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class for Deposit.fxml
 *
 * @author Heon Lee
 */
public class DepositController extends Controller implements Initializable {

    @FXML
    private AnchorPane depositView;
    @FXML
    private TextField amounttf;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    /**
     * Getter for view
     * @return A pane of the Controller's View
     */
    @Override
    public Pane getView() {
        return depositView;
    }
    /**
     * event handler for return button
     * @param event ActionEvent. Button click
     * @throws IOException 
     */
    @FXML
    private void handleReturn(ActionEvent event) throws IOException {
        getManagingController().addScreen("WelcomeView.fxml", this);
        getManagingController().removeScreen(this);
    }
    /**
     * event handler for submit button
     * @param event ActionEvent
     * @throws IOException 
     */
    @FXML
    private void handleSubmit(ActionEvent event) throws IOException {
        double amount = 0;
        boolean err = false;
        try {
            amount = Double.parseDouble(amounttf.getText());
        } catch (NumberFormatException ex) {
            err = true;
            Alert warning = new Alert(AlertType.WARNING);
            warning.setHeaderText("Please enter a valid number");
            warning.showAndWait();
            amounttf.clear();
        }
        if (!err) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("DEPOSIT");
            alert.setHeaderText("Deposit: $" + amounttf.getText());
            Optional<ButtonType> a = alert.showAndWait();
            if (a.get() == ButtonType.OK && amount > 0) {
                getCurrent().deposit(amount);
                getManagingController().addScreen("WelcomeView.fxml", this);
                getManagingController().removeScreen(this);
            } else if (amount <= 0) {
                Alert warning = new Alert(AlertType.WARNING);
                warning.setHeaderText("Please enter a valid number");
                warning.showAndWait();
            }
        }
    }
}
