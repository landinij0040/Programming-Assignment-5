/**
 * Class Description: Savings creates a savings account
 *
 * Assumptions:
 * 1) All users must have a savings account.
 * 2) Implements Printable interphase
 * @author Yulianna Torres, Isaiah Landin, Judith Garcia
 * @since 11/02/2020
 */
public class Savings extends Account implements Printable{

    //Constructor
    /**
     * constructor:
     * @param account_Number savings account number
     * @param starting_Balance savings balance
     */
    public Savings(int account_Number, float starting_Balance) {
        super(account_Number, starting_Balance);
    }

    //Methods
    /**
     * printBalance helps to print balance of account
     * @return balance of account
     */
    public float printBalance(){
        return this.get_Balance();
    }

    /**
     * accountType returns account type
     * @return string containing account type
     */
    public String accountType(){
        return "Savings";
    }

    /**
     * toString converts parameters to strings
     * @return parameters as strings
     */
    @Override
    public String toString() {
        return ("\n" + accountType() +
                super.toString());
    }
}
