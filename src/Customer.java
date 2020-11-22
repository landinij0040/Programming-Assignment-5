/**
 * @author Yulianna Torres, Isaiah Landin, Judith Garcia
 * @since 11/09/2020
 *
 * Class Description:
 * Customer extends from Person class and stores customer information not already inside of Person class: identification number and date of birth.
 *
 * Assumptions:
 * 1) Users have everything inside of Person class.
 * 2) Not all users have a checking and a credit account.
 */


import java.util.Comparator;

/**
 * <p>
 * Class Description:
 * Customer extends from Person class and stores customer information not already inside of Person class: checking and credit accounts.
 * <p>
 * Assumptions:
 * 1) Users have everything inside of Person class.
 * 2) Not all users have a checking and/or a credit account .
 * @author Yulianna Torres
 * @version 3.0
 * @author Isaiah Landin
 * @author Judith Garcia
 * @since 11/02/2020
 * @since 11/01/2020
 * @since 10/30/2020
 */

public class Customer extends Person {
    private Checking checking;
    private Credit credit;
    private BankStatement bankStatement;

    //Constructors

    /**
     * default constructor
     */
    public Customer() {
        super();
    }

    /**
     * constructor for customer without checking and credit accounts
     * @param firstName first name
     * @param lastName last name
     * @param DOB date of birth
     * @param identificationNum identification number
     * @param address address
     * @param phoneNum phone number
     * @param email email
     * @param password password
     * @param Savings_Balance savings account balance
     * @param Savings_Account savings account number
     */
    public Customer(String firstName,
                    String lastName,
                    String DOB,
                    int identificationNum,
                    String address,
                    String phoneNum,
                    String email,
                    String password,
                    float Savings_Balance,
                    int Savings_Account) {
        super(firstName, lastName, DOB, identificationNum, address, phoneNum, email, password, Savings_Balance, Savings_Account);
    }

    /**
     * constructor for customer with checking and credit accounts
     * @param firstName first name
     * @param lastName last name
     * @param DOB date of birth
     * @param identificationNum identification number
     * @param address address
     * @param phoneNum phone number
     * @param email email
     * @param password password
     * @param Checking_Balance checking account balance
     * @param Checking_Account checking account number
     * @param Savings_Balance savings account balance
     * @param Savings_Account savings account number
     * @param Credit_Balance credit account balance
     * @param Credit_Account credit account number
     * @param credit_max credit max
     */
    public Customer(String firstName,
                    String lastName,
                    String DOB,
                    int identificationNum,
                    String address,
                    String phoneNum,
                    String email,
                    String password,
                    float Checking_Balance,
                    int Checking_Account,
                    float Savings_Balance,
                    int Savings_Account,
                    float Credit_Balance,
                    int Credit_Account,
                    float credit_max) {
        super(firstName, lastName, DOB, identificationNum, address, phoneNum, email, password, Savings_Balance, Savings_Account);
        this.credit = new Credit(Credit_Account,Credit_Balance, credit_max);
        this.checking = new Checking(Checking_Account,Checking_Balance);
    }

    // Setters and Getters

    /**
     * gets checking account
     * @return checking account
     */
    public Checking getChecking() {
        return checking;
    }

    /**
     * sets checking account
     * @param checking checking account
     */
    public void setChecking(Checking checking) {
        this.checking = checking;
    }

    /**
     * gets credit account
     * @return credit account
     */
    public Credit getCredit() {
        return credit;
    }

    /**
     * sets credit account
     * @param credit credit account
     */
    public void setCredit(Credit credit) {
        this.credit = credit;
    }

    /**
     * gets bank statement
     * @return bank statement
     */
    public BankStatement getBankStatement() {
        return bankStatement;
    }

    /**
     * sets bank statement
     * @param bankStatement bank statement
     */
    public void setBankStatement(BankStatement bankStatement) {
        this.bankStatement = bankStatement;
    }

    //Methods
    /**
     * createChecking creates a checking account
     * @param Checking_Account checking account number
     * @param Checking_Balance checking account balance
     */
    public void createChecking(int Checking_Account,float Checking_Balance){
        this.checking = new Checking(Checking_Account,Checking_Balance);
    }

    /**
     * createCredit creates a credit account
     * @param Credit_Account credit account number
     * @param Credit_Balance credit account balance
     * @param credit_max credit max allowed
     */
    public void createCredit(int Credit_Account,float Credit_Balance, float credit_max){
        this.credit = new Credit(Credit_Account,Credit_Balance, credit_max);
    }

    /**
     * Class used to order the users
     */
    public static class sort_by_id implements Comparator<Customer> {
        @Override
        public int compare(Customer o, Customer t1) {
            return Integer.valueOf(o.getIdentificationNum()).compareTo(t1.getIdentificationNum());
        }
    }

    /**
     * toString returns parameters as strings
     * @return parameters as strings
     */

    @Override
    public String toString() {
        String creditString = "no credit account";
        String checkingString = "no checking account";

        if(credit != null){
            creditString = credit.toString();
        }
        if(checking != null){
            checkingString = checking.toString();
        }

        return (super.toString() +
                "\n" + checkingString +
                "\n" + creditString +
                "\n" + bankStatement.toString());
    }

    /**
     * Print prints bank statement
     * @return String of bank statement inquired
     */
    public String print() {
        return "\nBANK STATEMENT" +
                "\n------------------------" + toString();
    }
}











