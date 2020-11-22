/**
 * Class Description: Credit creates a credit account
 *
 * Assumptions:
 * 1) not all users must have a credit account.
 * 2) Implements Printable interphase
 * @author Yulianna Torres, Isaiah Landin, Judith Garcia
 * @since 11/02/2020
 *
 *
 */

public class Credit extends Account implements Printable{

    private float credit_max;

    //Constructors
    /**
     * Constructor
     * @param account_Number credit account number
     * @param starting_Balance credit starting balance
     */
    public Credit(int account_Number, float starting_Balance) {
        super(account_Number, starting_Balance);
    }

    /**
     * Constructor
     * @param account_Number credit account number
     * @param starting_Balance credit starting balance
     * @param credit_max credit max
     */
    public Credit(int account_Number, float starting_Balance, float credit_max) {
        super(account_Number, starting_Balance);
        this.credit_max = credit_max;
    }

    //Setters/Getters
    /**
     * Get Credit Max
     * @return Credit Max
     */
    public float getCredit_max() {
        return credit_max;
    }

    /**
     * Set credit max
     * @param credit_max credit max
     */
    public void setCredit_max(float credit_max) {
        this.credit_max = credit_max;
    }

    //Methods
    /**
     * Print Balance
     * @return balance
     */
    public float printBalance(){
        return this.get_Balance();
    }

    /**
     * Get Account Type
     * @return "Credit"
     */
    public String accountType(){
        return "Credit";
    }

    /**
     * toString returns parameters as strings
     * @return parameters as strings
     */
    @Override
    public String toString() {
        return ("\n" + accountType() +
                super.toString() +
                "\nCredit Max:" + credit_max);
    }
}
