/**
 * @author Yulianna Torres, Judith Garcia, Isaiah Landin
 * @since 11/02/2020
 *
 * Class Description:
 * Account is an abstract class that stores basic account information.
 *
 * Assumptions:
 * 1) All accounts have an account number and a starting balance
 */

public abstract class Account {
    protected int Account_Number;
    protected float balance;

    /**
     * Default Account Constructor
     * @param account_Number account number
     * @param starting_Balance starting balance
     */
    public Account(int account_Number, float starting_Balance) {
        Account_Number = account_Number;
        balance = starting_Balance;
    }

    /**
     * Get Account_Number
     * @return Account_Number
     */
    public int getAccount_Number() {
        return Account_Number;
    }

    /**
     * Set account_Number
     * @param account_Number account number
     */
    public void setAccount_Number(int account_Number) {
        Account_Number = account_Number;
    }

    /**
     * Get Balance
     * @return balance
     */
    public float get_Balance() {
        return balance;
    }

    /**
     * Set Balance
     * @param balance balance
     */
    public void set_Balance(float balance){
        this.balance = balance;
    }

    /**
     * Set Starting Balance
     * @param balance balance
     */
    public void setStarting_Balance(float balance) {
        balance = balance;
    }

    /**
     * toString returns parameters as strings
     * @return parameters as strings
     */
    @Override
    public String toString() {
        return ("\nAccount Number:" + Account_Number +
                "\nBalance:" + balance);
    }
}
