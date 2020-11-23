import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class Normal_UserController {

    @FXML private Text Title, NormalUser_Deposit_Message, NormalUser_Withdraw_Message, NormalUser_Transfer_Message, NormalUser_Pay_Message, NormalUser_Checking_Message, NormalUser_Credit_Message;
    @FXML private VBox NormalUser_ShowBalance, NormalUser_ShowDeposit, NormalUser_ShowWithdraw, NormalUser_ShowTransfer, NormalUser_ShowPay, NormalUser_ShowChecking, NormalUser_ShowCredit;
    @FXML private Text NormalUser_Balance;
    @FXML private TextField NormalUser_Amount, NormalUser_AmountW, NormalUser_AmountT, NormalUser_AmountP;
    @FXML private TextField NormalUser_Name, NormalUser_Lastname;

    // Output BankUsers CSV
    String outputBankUsers = "Output/Bank_Users.csv";
    // Where to put the Transaction Logs
    String transActionLogs = "Output/TransactionLog.txt";

    @FXML
    private SplitMenuButton options, options_deposit, options_withdraw, options_transfer, options_transfer1, options_pay, options_pay1;

    public Normal_UserController(){
        //Title.setText("Welcome ");
        //+ Start_Window.customer.getFirstName()


    }

    @FXML
    public void MakeDeposit(){
        if(isNumeric(NormalUser_Amount.getText()) && !options_deposit.getText().equals("Choose")) {
            String amount = NormalUser_Amount.getText();

            String Message = Start_Window.staticMenu.deposit_visual(Start_Window.customer, options_deposit.getText(), amount, transActionLogs, outputBankUsers, outputBankUsers);

            NormalUser_Deposit_Message.getStyleClass().clear();

            if(Message.indexOf("Successful Deposit!") == -1){
                NormalUser_Deposit_Message.getStyleClass().addAll("p", "strong", "text-danger");
            }else{
                NormalUser_Deposit_Message.getStyleClass().addAll("p", "strong", "text-success");
            }
            NormalUser_Deposit_Message.setText(Message);
        }else{
            NormalUser_Deposit_Message.getStyleClass().clear();
            NormalUser_Deposit_Message.getStyleClass().addAll("p", "strong", "text-danger");
            if(options_deposit.getText().equals("Choose")){
                NormalUser_Deposit_Message.setText("Please select an account!");
            }else{
                NormalUser_Deposit_Message.setText("Please introduce numbers!");
            }

        }
    }

    @FXML
    public void WidrawMoney(){
        if(isNumeric(NormalUser_AmountW.getText()) && !options_withdraw.getText().equals("Choose")) {
            String amount = NormalUser_AmountW.getText();

            String Message = Start_Window.staticMenu.withdraw_visual(Start_Window.customer, options_withdraw.getText(), amount, transActionLogs, outputBankUsers, outputBankUsers);

            NormalUser_Withdraw_Message.getStyleClass().clear();

            if(Message.indexOf("Successful Withdraw!") == -1){
                NormalUser_Withdraw_Message.getStyleClass().addAll("p", "strong", "text-danger");
            }else{
                NormalUser_Withdraw_Message.getStyleClass().addAll("p", "strong", "text-success");
            }
            NormalUser_Withdraw_Message.setText(Message);
        }else{
            NormalUser_Withdraw_Message.getStyleClass().clear();
            NormalUser_Withdraw_Message.getStyleClass().addAll("p", "strong", "text-danger");
            if(options_withdraw.getText().equals("Choose")){
                NormalUser_Withdraw_Message.setText("Please select an account!");
            }else{
                NormalUser_Withdraw_Message.setText("Please introduce numbers!");
            }

        }
    }

    @FXML
    public void TransferMoney(){
        if(isNumeric(NormalUser_AmountT.getText()) && !options_transfer.getText().equals("Choose") && !options_transfer1.getText().equals("Choose") && !options_transfer.getText().equals(options_transfer1.getText())) {
            String amount = NormalUser_AmountT.getText();

            String Message = Start_Window.staticMenu.transfer_visual(Start_Window.customer, options_transfer1.getText(), options_transfer.getText(), amount, transActionLogs, outputBankUsers, outputBankUsers);

            NormalUser_Transfer_Message.getStyleClass().clear();

            if(Message.indexOf("Successful Transfer!") == -1){
                NormalUser_Transfer_Message.getStyleClass().addAll("p", "strong", "text-danger");
            }else{
                NormalUser_Transfer_Message.getStyleClass().addAll("p", "strong", "text-success");
            }
            NormalUser_Transfer_Message.setText(Message);
        }else{
            NormalUser_Transfer_Message.getStyleClass().clear();
            NormalUser_Transfer_Message.getStyleClass().addAll("p", "strong", "text-danger");
            if(options_transfer.getText().equals("Choose") || options_transfer1.getText().equals("Choose")){
                NormalUser_Transfer_Message.setText("Please select an account!");
            }else if(options_transfer.getText().equals(options_transfer1.getText())){
                NormalUser_Transfer_Message.setText("You can't transfer to the same account!");
            }
            else{
                NormalUser_Transfer_Message.setText("Please introduce numbers!");
            }

        }
    }

    @FXML public void PaySomeone(){
        Customer customer2 = Start_Window.staticMenu.getCustomer(Start_Window.accounts, NormalUser_Name.getText() + " " + NormalUser_Lastname.getText());
        if(isNumeric(NormalUser_AmountP.getText()) && !options_pay.getText().equals("Choose") && !options_pay1.getText().equals("Choose") && customer2 != null) {
            String amount = NormalUser_AmountP.getText();

            String Message = Start_Window.staticMenu.pay_visual(Start_Window.customer, options_pay1.getText(), customer2,options_pay.getText(), amount, transActionLogs, outputBankUsers, outputBankUsers);


            NormalUser_Pay_Message.getStyleClass().clear();

            if(Message.indexOf("Payment Successful!") == -1){
                NormalUser_Pay_Message.getStyleClass().addAll("p", "strong", "text-danger");
            }else{
                NormalUser_Pay_Message.getStyleClass().addAll("p", "strong", "text-success");
            }
            NormalUser_Pay_Message.setText(Message);
        }else{
            NormalUser_Pay_Message.getStyleClass().clear();
            NormalUser_Pay_Message.getStyleClass().addAll("p", "strong", "text-danger");
            if(options_pay.getText().equals("Choose") || options_pay1.getText().equals("Choose")) {
                NormalUser_Pay_Message.setText("Please select an account!");
            }else if(customer2 == null){
                NormalUser_Pay_Message.setText("The account doesn't exists!");
            }
            else{
                NormalUser_Pay_Message.setText("Please introduce numbers!");
            }

        }
    }


    // ----------------------------- Show ---------------------------------------------
    @FXML
    public void ShowInquire(){
        hide();
        NormalUser_ShowBalance.setVisible(true);
        NormalUser_ShowBalance.setManaged(true);
    }
    @FXML
    public void ShowDeposit(){
        hide();
        NormalUser_ShowDeposit.setVisible(true);
        NormalUser_ShowDeposit.setManaged(true);
    }
    @FXML
    public void ShowWithdraw(){
        hide();
        NormalUser_ShowWithdraw.setVisible(true);
        NormalUser_ShowWithdraw.setManaged(true);
    }
    @FXML
    public void ShowTransfer(){
        hide();
        NormalUser_ShowTransfer.setVisible(true);
        NormalUser_ShowTransfer.setManaged(true);
    }

    @FXML
    public void ShowPay(){
        hide();
        NormalUser_ShowPay.setVisible(true);
        NormalUser_ShowPay.setManaged(true);
    }

    @FXML
    public void ShowChecking(){
        hide();
        NormalUser_ShowChecking.setVisible(true);
        NormalUser_ShowChecking.setManaged(true);

        if(Start_Window.customer.getChecking() != null)
            NormalUser_Checking_Message.setText("Already have a Checking account!");
        else {
            Start_Window.customer.setChecking(new Checking(Start_Window.customer.getSavings().getAccount_Number() - 1000, 0));
            NormalUser_Checking_Message.setText("Checking account created, your Checking account is: " + Start_Window.customer.getChecking().getAccount_Number());
        }
    }

    @FXML
    public void ShowCredit(){
        hide();
        NormalUser_ShowCredit.setVisible(true);
        NormalUser_ShowCredit.setManaged(true);

        if(Start_Window.customer.getCredit() != null)
            NormalUser_Credit_Message.setText("Already have a Checking account!");
        else {
            Start_Window.customer.setCredit(new Credit(Start_Window.customer.getSavings().getAccount_Number() + 1000, 0));
            NormalUser_Credit_Message.setText("Checking account created, your Checking account is: " + Start_Window.customer.getChecking().getAccount_Number());
        }
    }
    public boolean isNumeric(String str) {
        try {
            Float.parseFloat(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }



    public void hide(){
        NormalUser_ShowBalance.setVisible(false);
        NormalUser_ShowBalance.setManaged(false);
        NormalUser_ShowDeposit.setManaged(false);
        NormalUser_ShowDeposit.setVisible(false);
        NormalUser_ShowWithdraw.setVisible(false);
        NormalUser_ShowWithdraw.setManaged(false);
        NormalUser_ShowTransfer.setVisible(false);
        NormalUser_ShowTransfer.setManaged(false);
        NormalUser_ShowPay.setVisible(false);
        NormalUser_ShowPay.setManaged(false);
        NormalUser_ShowChecking.setVisible(false);
        NormalUser_ShowChecking.setManaged(false);
        NormalUser_ShowCredit.setVisible(false);
        NormalUser_ShowCredit.setManaged(false);
    }



    public void showBalance(String from){
        float balance = Start_Window.staticMenu.inquireBalanceVisual(Start_Window.customer, transActionLogs , from);
        NormalUser_Balance.setText("You Balance is: " + balance);
    }

    @FXML protected void Back(ActionEvent e){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Start.fxml"));
            Stage stage = (Stage) NormalUser_Balance.getScene().getWindow();
            Scene scene = new Scene(loader.load(), 800, 600);
            scene.getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");
            stage.setScene(scene);
        }catch (IOException io){
            io.printStackTrace();
        }
    }

    @FXML
    public void initialize() {
        Title.setText(Start_Window.customer.getFirstName() + "!");

        // Hide all operations
        NormalUser_ShowBalance.setVisible(false);
        NormalUser_ShowDeposit.setVisible(false);
        NormalUser_ShowWithdraw.setVisible(false);
        NormalUser_ShowWithdraw.setManaged(false);
        NormalUser_ShowTransfer.setVisible(false);
        NormalUser_ShowTransfer.setManaged(false);
        NormalUser_ShowPay.setVisible(false);
        NormalUser_ShowPay.setManaged(false);
        NormalUser_ShowChecking.setVisible(false);
        NormalUser_ShowChecking.setManaged(false);
        NormalUser_ShowCredit.setVisible(false);
        NormalUser_ShowCredit.setManaged(false);

        // Combobox options
        MenuItem savings = new MenuItem("Savings");
        MenuItem checking = new MenuItem("Checking");
        MenuItem credit = new MenuItem("Credit");

        // Combobox deposit
        MenuItem savings_deposit = new MenuItem("Savings");
        MenuItem checking_deposit = new MenuItem("Checking");
        MenuItem credit_deposit = new MenuItem("Credit");

        // Combobox withdraw
        MenuItem savings_withdraw = new MenuItem("Savings");
        MenuItem checking_withdraw = new MenuItem("Checking");

        // Combobox transfer
        MenuItem savings_transfer = new MenuItem("Savings");
        MenuItem checking_transfer = new MenuItem("Checking");
        MenuItem credit_transfer = new MenuItem("Credit");

        // Combobox transfer1
        MenuItem savings_transfer1 = new MenuItem("Savings");
        MenuItem checking_transfer1 = new MenuItem("Checking");
        MenuItem credit_transfer1 = new MenuItem("Credit");

        // Combobox pay
        MenuItem savings_pay = new MenuItem("Savings");
        MenuItem checking_pay = new MenuItem("Checking");
        MenuItem credit_pay = new MenuItem("Credit");

        // Combobox pay1
        MenuItem savings_pay1 = new MenuItem("Savings");
        MenuItem checking_pay1 = new MenuItem("Checking");
        MenuItem credit_pay1 = new MenuItem("Credit");

        options.getItems().addAll(savings, checking, credit);
        options_deposit.getItems().addAll(savings_deposit, checking_deposit, credit_deposit);
        options_withdraw.getItems().addAll(savings_withdraw, checking_withdraw);
        options_transfer.getItems().addAll(savings_transfer, checking_transfer, credit_transfer);
        options_transfer1.getItems().addAll(savings_transfer1, checking_transfer1, credit_transfer1);
        options_pay.getItems().addAll(savings_pay, checking_pay, credit_pay);
        options_pay1.getItems().addAll(savings_pay1, checking_pay1, credit_pay1);

        // Combobox options action
        savings.setOnAction((e)-> {
            showBalance("Savings");
            options.setText("Savings");
        });
        checking.setOnAction((e)-> {
            showBalance("Checking");
            options.setText("Checking");
        });
        credit.setOnAction((e)-> {
            showBalance("Credit");
            options.setText("Credit");
        });

        // Combobox deposit action
        savings_deposit.setOnAction((e)-> {
            options_deposit.setText("Savings");
        });
        checking_deposit.setOnAction((e)-> {
            options_deposit.setText("Checking");
        });
        credit_deposit.setOnAction((e)-> {
            options_deposit.setText("Credit");
        });

        // Combobox withdraw action
        savings_withdraw.setOnAction((e)-> {
            options_withdraw.setText("Savings");
        });
        checking_withdraw.setOnAction((e)-> {
            options_withdraw.setText("Checking");
        });

        // Combobox transfer action
        savings_transfer.setOnAction((e)-> {
            options_transfer.setText("Savings");
        });
        checking_transfer.setOnAction((e)-> {
            options_transfer.setText("Checking");
        });
        credit_transfer.setOnAction((e)-> {
            options_transfer.setText("Credit");
        });

        // Combobox transfer1 action
        savings_transfer1.setOnAction((e)-> {
            options_transfer1.setText("Savings");
        });
        checking_transfer1.setOnAction((e)-> {
            options_transfer1.setText("Checking");
        });
        credit_transfer1.setOnAction((e)-> {
            options_transfer1.setText("Credit");
        });

        // Combobox pay action
        savings_pay.setOnAction((e)-> {
            options_pay.setText("Savings");
        });
        checking_pay.setOnAction((e)-> {
            options_pay.setText("Checking");
        });
        credit_pay.setOnAction((e)-> {
            options_pay.setText("Credit");
        });

        // Combobox pay1 action
        savings_pay1.setOnAction((e)-> {
            options_pay1.setText("Savings");
        });
        checking_pay1.setOnAction((e)-> {
            options_pay1.setText("Checking");
        });
        credit_pay1.setOnAction((e)-> {
            options_pay1.setText("Credit");
        });
    }
}
