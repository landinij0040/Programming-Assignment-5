import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class LoginController{


    @FXML private Button Login_SignIn;
    @FXML private TextField Login_email;
    @FXML private PasswordField Login_password;
    @FXML private Text Login_Error;

    @FXML protected void Login(){
        Start_Window.customer = getCustomerByEmailAndPassword(Start_Window.accounts);

        if(Start_Window.customer != null){
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Normal_UserFXML.fxml"));
                Stage stage = (Stage) Login_SignIn.getScene().getWindow();
                Scene scene = new Scene(loader.load(), 900, 600);
                scene.getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");
                stage.setScene(scene);
            }catch (IOException io){
                io.printStackTrace();
            }
        }else{
            Login_Error.setText("Wrong Password!");
        }

    }



    /**
     * @authior Judith Garcia
     * @param accounts list of customer accounts in bank
     * @return customer inquired about
     */
    public Customer getCustomerByEmailAndPassword(ArrayList<Customer> accounts){
        String email = Login_email.getText();
        String password = Login_password.getText();

        for(int i = 0; i < accounts.size(); i++){
            if(email.equals(accounts.get(i).getEmail())) {
                if(password.equals(accounts.get(i).getPassword())){
                    return accounts.get(i);
                }
            }
        }
        return null;
    }
}
