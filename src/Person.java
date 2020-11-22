/**
 *
 * Class Description:
 * Person is an abstract class that stores basic user information.
 *
 * Assumptions:
 * 1) All users must have a first name, last name, date of birth, identification number, address, phone number, email, password, and savings account.
 * @author Yulianna Torres, Judith Garcia, Isaiah Landin
 * @since 11/02/2020*
 */

public abstract class Person {
    private String firstName;
    private String lastName;
    private String DOB;
    private int identificationNum;
    private String Address;
    private String phoneNum;
    private String email;
    private String password;
    private Savings savings;


    //Constructors
    /**
     * Default constructor
     */
    public Person(){
    }

    /**
     * Constructor
     * @param firstName first name
     * @param lastName last name
     * @param DOB date of birth
     * @param identificationNum identification number
     * @param address address
     * @param phoneNum phone number
     * @param email email address
     * @param password password
     * @param Savings_Balance savings account balance
     * @param Savings_Account savings account number
     */
    public Person(String firstName,
                  String lastName,
                  String DOB,
                  int identificationNum,
                  String address,
                  String phoneNum,
                  String email,
                  String password,
                  float Savings_Balance,
                  int Savings_Account) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.DOB = DOB;
        this.identificationNum = identificationNum;
        Address = address;
        this.phoneNum = phoneNum;
        this. email = email;
        this.password = password;
        this.savings = new Savings(Savings_Account, Savings_Balance);
    }

    //Getters/Setters
    /**
     * method that returns first name
     * @return first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * method that sets first name
     * @param firstName first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * gets last name
     * @return last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * sets last name
     * @param lastName last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * gets date of birth
     * @return date of birth
     */
    public String getDOB() {
        return DOB;
    }

    /**String
     * sets date of birth
     * @param DOB date of birth
     */
    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    /**
     * gets id number
     * @return id number
     */
    public int getIdentificationNum() {
        return identificationNum;
    }

    /**
     * sets id number
     * @param identificationNum id number
     */
    public void setIdentificationNum(int identificationNum) {
        this.identificationNum = identificationNum;
    }

    /**
     * gets Address
     * @return address
     */
    public String getAddress() {
        return Address;
    }

    /**
     * sets address
     * @param address address
     */
    public void setAddress(String address) {
        Address = address;
    }

    /**
     * gets phone number
     * @return phone number
     */
    public String getPhoneNum() {
        return phoneNum;
    }

    /**
     * sets phone number
     * @param phoneNum phone number
     */
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    /**
     * gets email
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * sets email
     * @param email email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * gets password
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * sets password
     * @param password password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * gets savings account
     * @return savings account
     */
    public Savings getSavings() {
        return savings;
    }

    /**
     * sets savings account
     * @param savings savings account
     */
    public void setSavings(Savings savings) {
        this.savings = savings;
    }

    //Methods
    /**
     * toString returns parameters as strings
     * @return parameters as strings
     */
    @Override
    public String toString() {
        return ("\nFirstName:" + firstName +
                "\nLastName:" + lastName +
                "\nDOB:" + DOB +
                "\nIdentification Number:" + identificationNum +
                "\nAddress:" + Address +
                "\nPhoneNum:" + phoneNum +
                "\nEmail:" + email +
                "\nPassword:" + password +
                "\n" + savings.toString());
    }
}

