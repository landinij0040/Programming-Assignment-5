import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class StartController {

    @FXML private Button Start_Normal_User, Start_Bank_Manager;
    @FXML private Text Start_Message;

    @FXML protected void TransactionReader(){
        Start_Message.setVisible(true);
        Start_Message.setManaged(true);

        Start_Window.staticMenu.transactionFinder(Start_Window.accounts);

        Start_Message.setText("All operations where made successfully!");
    }

    @FXML protected void Close(){
        Platform.exit();
        System.exit(0);
    }

    @FXML protected void Normal_menu(ActionEvent e){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
            Stage stage = (Stage) Start_Normal_User.getScene().getWindow();
            Scene scene = new Scene(loader.load(), 800, 600);
            scene.getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");
            stage.setScene(scene);
        }catch (IOException io){
            io.printStackTrace();
        }
    }

    @FXML protected void BankManager_menu(ActionEvent e){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Bank_ManagerFXML.fxml"));
            Stage stage = (Stage) Start_Bank_Manager.getScene().getWindow();
            Scene scene = new Scene(loader.load(), 1330, 700);
            scene.getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");
            scene.getStylesheets().add("tableView.css");
            stage.setScene(scene);
        }catch (IOException io){
            io.printStackTrace();
        }
    }

    @FXML
    public void initialize() {
        Start_Message.setVisible(false);
        Start_Message.setManaged(false);
    }



}
