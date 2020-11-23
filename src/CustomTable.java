import javafx.beans.property.SimpleStringProperty;

public class CustomTable {
    private final SimpleStringProperty firstName = new SimpleStringProperty("");
    private final SimpleStringProperty lastName = new SimpleStringProperty("");
    private final SimpleStringProperty phone = new SimpleStringProperty("");
    private final SimpleStringProperty dob = new SimpleStringProperty("");
    private final SimpleStringProperty identification = new SimpleStringProperty("");
    private final SimpleStringProperty address = new SimpleStringProperty("");
    private final SimpleStringProperty checkingAccount = new SimpleStringProperty("");
    private final SimpleStringProperty savingsAccount = new SimpleStringProperty("");
    private final SimpleStringProperty creditAccount = new SimpleStringProperty("");
    private final SimpleStringProperty checkingBalance = new SimpleStringProperty("");
    private final SimpleStringProperty savingsBalance = new SimpleStringProperty("");
    private final SimpleStringProperty creditBalance = new SimpleStringProperty("");


    public CustomTable() {
    }

    public CustomTable(String firstName, String lastName, String phone, String dob, String identification, String address,
                       String checkingAccount, String savingsAccount, String creditAccount, String checkingBalance, String savingsBalance, String creditBalance) {
        setFirstName(firstName);
        setLastName(lastName);
        setPhone(phone);
        setDob(dob);
        setIdentification(identification);
        setAddress(address);
        setCheckingAccount(checkingAccount);
        setSavingsAccount(savingsAccount);
        setCreditAccount(creditAccount);
        setCheckingBalance(checkingBalance);
        setSavingsBalance(savingsBalance);
        setCreditBalance(creditBalance);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public SimpleStringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public SimpleStringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public String getPhone() {
        return phone.get();
    }

    public SimpleStringProperty phoneProperty() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public String getDob() {
        return dob.get();
    }

    public SimpleStringProperty dobProperty() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob.set(dob);
    }

    public String getIdentification() {
        return identification.get();
    }

    public SimpleStringProperty identificationProperty() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification.set(identification);
    }

    public String getAddress() {
        return address.get();
    }

    public SimpleStringProperty addressProperty() {
        return address;
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public String getCheckingAccount() {
        return checkingAccount.get();
    }

    public SimpleStringProperty checkingAccountProperty() {
        return checkingAccount;
    }

    public void setCheckingAccount(String checkingAccount) {
        this.checkingAccount.set(checkingAccount);
    }

    public String getSavingsAccount() {
        return savingsAccount.get();
    }

    public SimpleStringProperty savingsAccountProperty() {
        return savingsAccount;
    }

    public void setSavingsAccount(String savingsAccount) {
        this.savingsAccount.set(savingsAccount);
    }

    public String getCreditAccount() {
        return creditAccount.get();
    }

    public SimpleStringProperty creditAccountProperty() {
        return creditAccount;
    }

    public void setCreditAccount(String creditAccount) {
        this.creditAccount.set(creditAccount);
    }

    public String getCheckingBalance() {
        return checkingBalance.get();
    }

    public SimpleStringProperty checkingBalanceProperty() {
        return checkingBalance;
    }

    public void setCheckingBalance(String checkingBalance) {
        this.checkingBalance.set(checkingBalance);
    }

    public String getSavingsBalance() {
        return savingsBalance.get();
    }

    public SimpleStringProperty savingsBalanceProperty() {
        return savingsBalance;
    }

    public void setSavingsBalance(String savingsBalance) {
        this.savingsBalance.set(savingsBalance);
    }

    public String getCreditBalance() {
        return creditBalance.get();
    }

    public SimpleStringProperty creditBalanceProperty() {
        return creditBalance;
    }

    public void setCreditBalance(String creditBalance) {
        this.creditBalance.set(creditBalance);
    }
}
