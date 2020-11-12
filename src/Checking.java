/**
 * @author Yulianna Torres, Isaiah Landin, Judith Garcia
 * @since 11/02/2020
 *
 * Class Description: Checking creates a checking account
 *
 * Assumptions:
 * 1) not all users must have a checking account.
 * 2) Implements Printable interphase
 */

public class Checking extends Account implements Printable {

    //Constructors
    /**
     * Default constructor
     * @param account_Number checking account number
     * @param starting_Balance checking balance
     */
    public Checking(int account_Number, float starting_Balance) {
        super(account_Number, starting_Balance);
    }

    //Methods
    /**
     * helps to print Balance
     * @return balance
     */
    public float printBalance(){
        return this.get_Balance();
    }

    /**
     * Get Account Type
     * @return "Checking"
     */
    public String accountType(){
        return "Checking";
    }

    /**
     * toString returns parameters as strings
     * @return parameters as strings
     */
    @Override
    public String toString() {
        return ("\n" + accountType() +
                super.toString());
    }
}
