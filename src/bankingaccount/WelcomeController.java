package bankingaccount;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
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
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class for WelcomeView.fxml
 *
 * @author Heon Lee, Sher Khan
 */
public class WelcomeController extends Controller implements Initializable {

    @FXML
    private VBox welcomeView;
    @FXML
    private Label welcome;
    @FXML
    private Label balancetf;

    /*
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    /**
     * Getter for view
     *
     * @return Pane object of welcomeview.fxml
     */
    @Override
    public Pane getView() {
        return welcomeView;
    }

    /**
     * Sets message so that the message include account holder name
     */
    public void message() {
        welcome.setText("Welcome, " + this.getCurrent().getAccountHolder());
    }

    /**
     * Sets a label so that the label shows the balance
     */
    public void showBalance() {
        balancetf.setText(String.valueOf(getCurrent().getBalance()));
    }

    /**
     * Event handler for exit button and checks validity. Also, it rewrites
     * updates account information to a file. If a user wants, it also writes
     * the user's history to a new file.
     *
     * @throws IOException IOException
     */
    public void handleExit() throws IOException {
        Alert exit = new Alert(AlertType.CONFIRMATION);
        exit.setContentText("Exit?");
        Optional<ButtonType> ex = exit.showAndWait();
        if (confirmationCatch(ex) == false && ex.get() == ButtonType.OK) {
            //If the user pressed OK
            File f = new File(".\\src\\bankingaccount\\db\\accounts.txt");
            if (f.exists()) {//If the file already exists
                f.delete();//delete it
            }
            //Writes updated account information to a new file
            for (int i = 0; i < getAccountList().size(); i++) {
                if (getAccountList().get(i) instanceof ChequeingAccount) {
                    writeData((ChequeingAccount) getAccountList().get(i));
                } else {
                    writeData((SavingAccount) getAccountList().get(i));
                }
            }
            
            //Save history
            Alert history = new Alert(AlertType.CONFIRMATION);
            history.setContentText("Save History?");
            Optional<ButtonType> c = history.showAndWait();
            if (confirmationCatch(c) == false && c.get() == ButtonType.OK) {
                File hist = new File(".\\src\\bankingaccount\\history\\"
                        + getCurrent().getaccountHolderID()
                        + ".txt");
                while (hist.exists()) {
                    hist = new File(".\\src\\bankingaccount\\history\\"
                            + getCurrent().getaccountHolderID() + 1
                            + ".txt");
                }
                FileWriter fw = new FileWriter(hist, true);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.append(getCurrent().getAccountHolder() + ":");
                bw.append(getCurrent().gethistory());
                bw.flush();
                bw.close();
            }
            System.exit(0);

        }
    }

    /**
     * Event handler for transfer button
     *
     * @param event ActionEvent
     * @throws IOException IOException
     */
    public void handleTransfer(ActionEvent event) throws IOException {
        getManagingController().addScreen("TransferView.fxml", this);
        getManagingController().removeScreen(this);
    }
    /**
     * Event handler for information button
     * @param event ActionEvent
     * @throws IOException IOException
     */
    public void handleInformation(ActionEvent event) throws IOException {
        getManagingController().addScreen("informationView.fxml", this);
        getManagingController().removeScreen(this);
    }

    /**
     * Event handler for deposit button
     *
     * @throws IOException IOException
     */
    public void handleDeposit() throws IOException {
        getManagingController().addScreen("Deposit.fxml", this);
        getManagingController().removeScreen(this);
    }

    /**
     * Event handler for withdraw button
     *
     * @throws IOException IOException
     */
    public void handleWithdraw() throws IOException {
        getManagingController().addScreen("Withdrawl.fxml", this);
        getManagingController().removeScreen(this);
    }
}
