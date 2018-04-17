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
 * FXML Controller class for Withdrawl.fxml
 *
 * @author Heon Lee
 */
public class WithdrawlController extends Controller implements Initializable {

    @FXML
    private AnchorPane withdrawView;
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
     * Event handler for return button
     *
     * @param event ActionEvent
     * @throws IOException
     */
    @FXML
    private void handleReturn(ActionEvent event) throws IOException {
        getManagingController().addScreen("WelcomeView.fxml", this);
        getManagingController().removeScreen(this);
    }

    /**
     * Event handler for submit button
     *
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
            alert.setTitle("WITHDRAW");
            alert.setHeaderText("WITHDRAW: $" + amounttf.getText());
            Optional<ButtonType> a = alert.showAndWait();
            if (a.get() == ButtonType.OK && amount > 0) {
                if (getCurrent().withdrawl(amount) == false) {
                    Alert balance = new Alert(AlertType.INFORMATION);
                    balance.setTitle("Insufficent");
                    balance.setHeaderText("Your balance: " + getCurrent()
                            .getBalance());
                    balance.showAndWait();
                } else {
                    Alert alertX = new Alert(AlertType.INFORMATION);
                    alertX.setTitle("WITHDRAW");
                    alertX.setHeaderText("Withdraw Complete");
                    alertX.setContentText("Your Balance: " + getCurrent().getBalance());
                    alertX.showAndWait();
                    getManagingController().addScreen("WelcomeView.fxml", this);
                    getManagingController().removeScreen(this);
                }
            } else if (amount <= 0) {
                Alert warning = new Alert(AlertType.WARNING);
                warning.setHeaderText("Please enter a valid number");
                warning.showAndWait();
            }
        }
    }

    /**
     * Getter for view
     *
     * @return Pane object of withdrawl.fxml
     */
    @Override
    public Pane getView() {
        return withdrawView;
    }

}
