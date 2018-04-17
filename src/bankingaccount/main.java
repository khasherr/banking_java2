
package bankingaccount;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main class
 * @author Heon Lee, Sher Khan
 */
public class main extends Application {
    /**
     * 
     * @param primaryStage Stage
     * @throws IOException IOException
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("mainView.fxml"));
        
        Scene scene = new Scene(root, 600, 400);
                
        primaryStage.setTitle("BANK");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    
}
