import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class BankManagerController {

    @FXML private TableView<CustomTable> BankManager_TableView;
    @FXML private Button BankManager_Inquire;
    @FXML private VBox BankManager_ShowInquire, BankManager_ShowInquire_By, BankManager_ShowAll, BankManager_ShowGenerate, BankManager_ShowCreate_User;
    @FXML private TextField BankManager_Name;
    @FXML private GridPane BankManager_GridPane;
    @FXML private Text BankManager_Found, BankManager_Found2, BankManager_Found3, BankManager_Found4;
    @FXML private SplitMenuButton options;
    @FXML private TextField BankManager_Account_Number, BankManager_Name_Generate, BankManager_Create_Name, BankManager_Create_Last, BankManager_Address;
    @FXML private TextField BankManager_DOB, BankManager_Email, BankManager_Phone;
    @FXML private PasswordField BankManager_Password;

    @FXML
    protected void ShowInquireAllAccounts(ActionEvent event) {
        hide();
        BankManager_ShowAll.setVisible(true);
        BankManager_ShowAll.setManaged(true);
        BankManager_GridPane.setVisible(true);
        BankManager_GridPane.setManaged(true);

        BankManager_TableView.getItems().clear();

        ShowAllTable();
    }

    @FXML
    protected void CreateUser(){
        String name = BankManager_Create_Name.getText();
        String lastname = BankManager_Create_Last.getText();

        Start_Window.customer = Start_Window.staticMenu.getCustomer(Start_Window.accounts,  name + " " + lastname);

        if(Start_Window.customer == null && !name.isEmpty() && !lastname.isEmpty() && !BankManager_DOB.getText().isEmpty() && !BankManager_Address.getText().isEmpty()
        && !BankManager_Phone.getText().isEmpty() && !BankManager_Email.getText().isEmpty() && !BankManager_Password.getText().isEmpty()){
            int id = Start_Window.accounts.get(Start_Window.accounts.size() - 1).getIdentificationNum() + 1;
            Customer newCustomer = new Customer(name, lastname, BankManager_DOB.getText(), id,
                    BankManager_Address.getText(), BankManager_Phone.getText(), BankManager_Email.getText(), BankManager_Password.getText(), 0, id + 1999);

            BankManager_GridPane.setVisible(true);
            BankManager_GridPane.setManaged(true);
            BankManager_TableView.getItems().clear();
            BankManager_TableView.setPrefHeight(65);

            Start_Window.accounts.add(newCustomer);

            ObservableList<CustomTable> data = BankManager_TableView.getItems();

            data.add(new CustomTable(newCustomer.getFirstName(), newCustomer.getLastName(), newCustomer.getPhoneNum(), newCustomer.getDOB(), "" + newCustomer.getIdentificationNum(),
                    newCustomer.getAddress(), "" , "" + newCustomer.getSavings().getAccount_Number(),
                    "" , "" ,
                    "" + newCustomer.getSavings().get_Balance(), "" ));



            BankManager_Found4.getStyleClass().clear();
            BankManager_Found4.getStyleClass().addAll("p", "strong", "text-success");
            BankManager_Found4.setText("User has been created!");
        }else{
            BankManager_Found4.getStyleClass().clear();
            BankManager_Found4.getStyleClass().addAll("p", "strong", "text-danger");
            if(Start_Window.customer != null){
                BankManager_Found4.setText("The user already exists!");
            }else{
                BankManager_Found4.setText("Please fill out empty fields!");
            }

        }

    }

    @FXML
    protected void ShowCreate(){
        hide();
        BankManager_ShowCreate_User.setVisible(true);
        BankManager_ShowCreate_User.setManaged(true);
    }

    @FXML
    public void InquireAccountId(){
        String Option = options.getText();
        boolean found = false;

        if(!Option.isEmpty() && isNumeric(BankManager_Account_Number.getText())) {
            int type = Integer.parseInt(BankManager_Account_Number.getText());
            BankManager_GridPane.setVisible(true);
            BankManager_GridPane.setManaged(true);
            BankManager_TableView.getItems().clear();
            BankManager_TableView.setPrefHeight(65);

            ObservableList<CustomTable> data = BankManager_TableView.getItems();

            for (Customer account : Start_Window.accounts) {
                if (Option.equals("Credit") && type == account.getCredit().getAccount_Number() || Option.equals("Checking") && type == account.getChecking().getAccount_Number() || Option.equals("Savings") && type == account.getSavings().getAccount_Number()) {
                    String creditAccount = "";
                    String creditBalance = "";
                    String checkingAccount = "";
                    String checkingBalance = "";
                    if(account.getCredit() != null){
                        creditAccount += account.getCredit().getAccount_Number();
                        creditBalance += account.getCredit().get_Balance();
                    }
                    if(account.getChecking() != null){
                        checkingAccount += account.getChecking().getAccount_Number();
                        checkingBalance += account.getChecking().get_Balance();
                    }

                    data.add(new CustomTable(account.getFirstName(), account.getLastName(), account.getPhoneNum(), account.getDOB(), "" + account.getIdentificationNum(),
                            account.getAddress(), checkingAccount, "" + account.getSavings().getAccount_Number(),
                            creditAccount, checkingBalance,
                            "" + account.getSavings().get_Balance(), creditBalance));
                    found = true;
                    break;
                }
            }
            if(found){
                BankManager_Found2.getStyleClass().clear();
                BankManager_Found2.getStyleClass().addAll("p", "strong", "text-success");
                BankManager_Found2.setText("The user was found!");
            }else{
                BankManager_Found2.getStyleClass().clear();
                BankManager_Found2.getStyleClass().addAll("p", "strong", "text-danger");
                BankManager_Found2.setText("User not found!");
            }
        }else{
            BankManager_Found2.getStyleClass().clear();
            BankManager_Found2.getStyleClass().addAll("p", "strong", "text-danger");
            if(Option.isEmpty()) {
                BankManager_Found2.setText("Please select an account.");
            }else{
                BankManager_Found2.setText("Account number invalid.");
            }
        }

    }

    @FXML
    public void GenerateBankStatement(){
        Start_Window.customer = Start_Window.staticMenu.getCustomer(Start_Window.accounts, BankManager_Name_Generate.getText());
        if(Start_Window.customer != null){
            BankManager_GridPane.setVisible(true);
            BankManager_GridPane.setManaged(true);

            BankManager_Found3.getStyleClass().clear();
            BankManager_Found3.getStyleClass().addAll("p", "strong", "text-success");

            BankManager_TableView.setPrefHeight(65);

            BankManager_TableView.getItems().clear();
            Customer account = Start_Window.customer;

            BankManager.generateBankStatement(account,"Output/Bank Statements");

            BankManager_Found3.setText("The Bank Statement was generated successfully!");

            ObservableList<CustomTable> data = BankManager_TableView.getItems();

            data.add(new CustomTable(account.getFirstName(), account.getLastName(), account.getPhoneNum(), account.getDOB(), ""+account.getIdentificationNum(),
                    account.getAddress(), ""+account.getChecking().getAccount_Number(), ""+account.getSavings().getAccount_Number(),
                    ""+account.getCredit().getAccount_Number(), ""+account.getChecking().get_Balance(),
                    ""+account.getSavings().get_Balance(), ""+account.getCredit().get_Balance()));
        }else{
            BankManager_Found.getStyleClass().clear();
            BankManager_Found.getStyleClass().addAll("p", "strong", "text-danger");
            BankManager_Found.setText("We couldn't find anything for " + BankManager_Name.getText());
        }
    }

    @FXML protected void Back(ActionEvent e){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Start.fxml"));
            Stage stage = (Stage) BankManager_Inquire.getScene().getWindow();
            Scene scene = new Scene(loader.load(), 800, 600);
            scene.getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");
            stage.setScene(scene);
        }catch (IOException io){
            io.printStackTrace();
        }
    }


    public void ShowAllTable(){
        BankManager_TableView.getItems().clear();
        BankManager_TableView.setPrefHeight(200);

        ObservableList<CustomTable> data = BankManager_TableView.getItems();
        for (Customer account : Start_Window.accounts) {
            String creditAccount = "";
            String creditBalance = "";
            String checkingAccount = "";
            String checkingBalance = "";
            if(account.getCredit() != null){
                creditAccount += account.getCredit().getAccount_Number();
                creditBalance += account.getCredit().get_Balance();
            }
            if(account.getChecking() != null){
                checkingAccount += account.getChecking().getAccount_Number();
                checkingBalance += account.getChecking().get_Balance();
            }

            data.add(new CustomTable(account.getFirstName(), account.getLastName(), account.getPhoneNum(), account.getDOB(), ""+account.getIdentificationNum(),
                    account.getAddress(), checkingAccount, ""+account.getSavings().getAccount_Number(),
                    creditAccount, checkingBalance,
                    ""+account.getSavings().get_Balance(), creditBalance));
        }
    }

    @FXML
    public void ShowInquire(){
        hide();
        BankManager_ShowInquire.setVisible(true);
        BankManager_ShowInquire.setManaged(true);
    }

    @FXML
    public void ShowInquireBy(){
        hide();
        BankManager_ShowInquire_By.setVisible(true);
        BankManager_ShowInquire_By.setManaged(true);
    }

    @FXML
    public void ShowGenerate(){
        hide();
        BankManager_ShowGenerate.setVisible(true);
        BankManager_ShowGenerate.setManaged(true);
    }

    @FXML
    public void InquireAccount(){
        Start_Window.customer = Start_Window.staticMenu.getCustomer(Start_Window.accounts, BankManager_Name.getText());
        if(Start_Window.customer != null){
            BankManager_GridPane.setVisible(true);
            BankManager_GridPane.setManaged(true);

            BankManager_Found.getStyleClass().clear();
            BankManager_Found.getStyleClass().addAll("p", "strong", "text-success");

            BankManager_TableView.setPrefHeight(65);

            BankManager_TableView.getItems().clear();
            Customer account = Start_Window.customer;

            BankManager_Found.setText("The user was found!");

            ObservableList<CustomTable> data = BankManager_TableView.getItems();

            data.add(new CustomTable(account.getFirstName(), account.getLastName(), account.getPhoneNum(), account.getDOB(), ""+account.getIdentificationNum(),
                    account.getAddress(), ""+account.getChecking().getAccount_Number(), ""+account.getSavings().getAccount_Number(),
                    ""+account.getCredit().getAccount_Number(), ""+account.getChecking().get_Balance(),
                    ""+account.getSavings().get_Balance(), ""+account.getCredit().get_Balance()));
        }else{
            BankManager_Found.getStyleClass().clear();
            BankManager_Found.getStyleClass().addAll("p", "strong", "text-danger");
            BankManager_Found.setText("We couldn't find anything for " + BankManager_Name.getText());
        }
    }

    public boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    public void hide(){
        BankManager_ShowInquire.setVisible(false);
        BankManager_ShowInquire.setManaged(false);
        BankManager_ShowInquire_By.setVisible(false);
        BankManager_ShowInquire_By.setManaged(false);
        BankManager_ShowAll.setVisible(false);
        BankManager_ShowAll.setManaged(false);
        BankManager_ShowGenerate.setVisible(false);
        BankManager_ShowGenerate.setManaged(false);
        BankManager_ShowCreate_User.setVisible(false);
        BankManager_ShowCreate_User.setManaged(false);
    }

    @FXML
    public void initialize(){
        BankManager_ShowInquire.setVisible(false);
        BankManager_ShowInquire_By.setVisible(false);
        BankManager_GridPane.setVisible(false);
        BankManager_GridPane.setManaged(false);
        BankManager_ShowAll.setVisible(false);
        BankManager_ShowAll.setManaged(false);
        BankManager_ShowGenerate.setVisible(false);
        BankManager_ShowGenerate.setManaged(false);
        BankManager_ShowCreate_User.setVisible(false);
        BankManager_ShowCreate_User.setManaged(false);

        // Combobox options
        MenuItem savings = new MenuItem("Savings");
        MenuItem checking = new MenuItem("Checking");
        MenuItem credit = new MenuItem("Credit");

        options.getItems().addAll(savings, checking, credit);
        // Combobox options action
        savings.setOnAction((e)-> {
            options.setText("Savings");
        });
        checking.setOnAction((e)-> {
            options.setText("Checking");
        });
        credit.setOnAction((e)-> {
            options.setText("Credit");
        });
    }


}
