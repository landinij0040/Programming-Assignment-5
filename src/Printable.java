/**
 * Class Description: interphase that calls methods that help print and return account type
 *
 * Assumptions:
 * 1)Checkings, Savings, and Credit methods all utilize this interphase
 * @author Yulianna Torres, Isaiah Landin, Judith Garcia
 * @since 11/07/2020
 *
 */
public interface Printable {
    float printBalance();
    String toString();
    String accountType();
}
