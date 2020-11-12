/**
 * @author Yulianna Torres, Isaiah Landin
 * @since 11/09/2020
 *
 * Class Description:
 * BankManager has all bank manager functionality.
 *
 * Assumptions:
 * 1) Bank manager creates new users
 * 2) Bank manager generates bank statements
 * 3) there is only one bank manager for the whole bank
 */

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BankManager {
    private static BankManager INSTANCE;

    /**
     * returns instance of bank manager, singleton design pattern
     * @return instance of bank manager
     */
    public static BankManager getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new BankManager();
        }
        return INSTANCE;
    }

    /**
     * Method that print all the Customer information.
     * @param customer: Personal Info
     */
    public void print_customer_info(Customer customer){
        System.out.println("\nFull name: " + customer.getFirstName() + " " + customer.getLastName());
        System.out.println("Date of birth: " + customer.getDOB());
        System.out.println("Identification number: " + customer.getIdentificationNum());
        System.out.println("Address: " + customer.getAddress());
        System.out.println("Phone number: " + customer.getPhoneNum());
        if(customer.getChecking() != null) {
            System.out.println("Checking Account: " + customer.getChecking().getAccount_Number());
            //  System.out.println("Checking Balance: " + customer.getChecking().inquire_balance());
        }
        System.out.println("Savings Account: " + customer.getSavings().getAccount_Number());
        //System.out.println("Savings Balance: " + customer.getSavings().inquire_balance());
        if(customer.getCredit() != null) {
            System.out.println("Credit Account: " + customer.getCredit().getAccount_Number());
            //    System.out.println("Credit Balance: " + customer.getCredit().inquire_balance() + "\n");
        }
    }

    /**
     * Method that creates a new user
     * @param accounts customer accounts in bank
     * @return customer created
     */
    public Customer create_user(List<Customer> accounts){
        ArrayList<String> user_data = new ArrayList<>();
        Customer customer = null;

        System.out.println("------- Create User -------");
        System.out.println("First Name: ");
        user_data.add(UI.getOption());
        if (user_data.get(user_data.size() - 1).isEmpty()) {
            System.out.println("First name can't be empty");
            return null;
        }
        System.out.println("Last Name: ");
        user_data.add(UI.getOption());
        if (user_data.get(user_data.size() - 1).isEmpty()) {
            System.out.println("Last name can't be empty");
            return null;
        }

        //check if user is already in system
        for (Customer account:accounts) {
            if ((user_data.get(0).equals(account.getFirstName()))&&(user_data.get(1).equals(account.getLastName()))) {
                System.out.println("Duplicate found in system");
                return null;
            }
        }

        System.out.println("Date of Birth: (Ex: mm/dd/yyyy)");
        user_data.add(UI.getOption());
        if (user_data.get(user_data.size() - 1).isEmpty()) {
            System.out.println("Date of Birth can't be empty");
            return null;
        }
        System.out.println("Identification Number:");
        user_data.add(UI.getOption());
        if (user_data.get(user_data.size() - 1).isEmpty()) {
            System.out.println("Identification Number can't be empty");
            return null;
        }
        System.out.println("Address: ");
        user_data.add(UI.getOption());
        if (user_data.get(user_data.size() - 1).isEmpty()) {
            System.out.println("Address can't be empty");
            return null;
        }
        System.out.println("Phone Number: (Ex:(xxx)xxx-xxxx)");
        user_data.add(UI.getOption());
        if (user_data.get(user_data.size() - 1).isEmpty()) {
            System.out.println("Phone number can't be empty");
            return null;
        }
        System.out.println("Email: ");
        user_data.add(UI.getOption());
        if (user_data.get(user_data.size() - 1).isEmpty()) {
            System.out.println("Email can't be empty");
            return null;
        }
        System.out.println("Password: ");
        user_data.add(UI.getOption());
        if (user_data.get(user_data.size() - 1).isEmpty()) {
            System.out.println("Password can't be empty");
            return null;
        }

        //Create Savings account
        System.out.println("Creating Savings account");
        Savings savings;
        System.out.println("What is the starting Savings Balance?");
        user_data.add(UI.getOption());
        String savingsStartingBalance;
        if (user_data.get(user_data.size() - 1).isEmpty()) {
            System.out.println("Starting Balance can't be empty");
            return null;
        }
        savingsStartingBalance = user_data.get(user_data.size() - 1);
        System.out.println("What is the Savings Account number?");
        user_data.add(UI.getOption());
        if (user_data.get(user_data.size() - 1).isEmpty()) {
            System.out.println("Savings Account Number can't be empty");
            return null;
        }

        customer = new Customer(user_data.get(0),//first name
                user_data.get(1),//last name
                user_data.get(2),//dob
                Integer.parseInt(user_data.get(3)),//identification num
                user_data.get(4),//address
                user_data.get(5),//phone number
                user_data.get(6),//email
                user_data.get(7),//password
                Float.parseFloat(user_data.get(8)),//Savings balance
                Integer.parseInt(user_data.get(9)));//Savings account number
        System.out.println("Savings was created successfully");
        BankStatement bankStatement = new BankStatement(savingsStartingBalance);
        customer.setBankStatement(bankStatement);

        //Option for checking
        System.out.println("Would you like to create a Checking account?(y/n)");
        String option = UI.getOption();
        switch (option){
            case "y":
                System.out.println("What is the Checking Account number?");
                user_data.add(UI.getOption());
                if (user_data.get(user_data.size() - 1).isEmpty()) {
                    System.out.println("Checking Account number can't be empty");
                    return null;
                }
                System.out.println("What is the Checking Account starting balance?");
                user_data.add(UI.getOption());
                if (user_data.get(user_data.size() - 1).isEmpty()) {
                    System.out.println("Checking Account balance can't be empty");
                    return null;
                }
                customer.getBankStatement().setStartingCheckingsBalance(user_data.get(user_data.size() - 1));
                customer.createChecking(Integer.parseInt(user_data.get(user_data.size() - 2)), //Checking account number
                        Float.parseFloat(user_data.get(user_data.size() - 1))); //checking account balance

                System.out.println("Checking account created");
                break;
            case "n":
                System.out.println("Checking account not created");
                break;
            default:
                System.out.println("Option not detected please try again with either (y or n)");
                break;
        }

        //Option for credit
        System.out.println("Would you like to create a Credit account?(y/n)");
        option = UI.getOption();
        switch (option){
            case "y":
                System.out.println("What is the Credit Account number?");
                user_data.add(UI.getOption());
                if (user_data.get(user_data.size() - 1).isEmpty()) {
                    System.out.println("Credit Account number can't be empty");
                    return null;
                }
                System.out.println("What is the Credit Account starting balance?");
                user_data.add(UI.getOption());
                if (user_data.get(user_data.size() - 1).isEmpty()) {
                    System.out.println("Credit Account balance can't be empty");
                    return null;
                }
                customer.getBankStatement().setStartingCreditBalance(user_data.get(user_data.size() - 1));
                System.out.println("What is the max Credit allowed?");
                user_data.add(UI.getOption());
                if (user_data.get(user_data.size() - 1).isEmpty()) {
                    System.out.println("Credit max can't be empty");
                    return null;
                }
                customer.createCredit(Integer.parseInt(user_data.get(user_data.size() - 3)), //Credit account number
                        Float.parseFloat(user_data.get(user_data.size() - 2)),//credit account balance
                        Float.parseFloat(user_data.get(user_data.size() -1))); //credit max

                System.out.println("Credit account created");
                break;
            case "n":
                System.out.println("Credit account not created");
                break;
            default:
                System.out.println("Option not detected please try again with either (y or n)");
                break;
        }

        String creditAccountNumber = "";
        String creditBalance = "";
        String creditMax = "";
        String checkingBalance = "";
        String checkingAccountNum = "";


        try{
            creditAccountNumber = String.valueOf(customer.getCredit().getAccount_Number());
            creditBalance = String.valueOf(customer.getCredit().get_Balance());
            creditMax = String.valueOf(customer.getCredit().getCredit_max());
        }catch(Exception e){
            System.out.print("");
        }

        try{
            checkingBalance = String.valueOf(customer.getChecking().get_Balance());
            checkingAccountNum = String.valueOf(customer.getChecking().getAccount_Number());
        }catch(Exception e){
            System.out.print("");
        }



        // Adding the customer to the CSV file
        String toAddToCSV = customer.getSavings().Account_Number + "," +
                customer.getLastName() + "," +
                customer.getIdentificationNum() + "," +
                customer.getDOB() + "," +
                checkingAccountNum + "," +
                creditAccountNumber + "," +
                customer.getPhoneNum() + "," +
                checkingBalance + "," +
                customer.getSavings().get_Balance()  + "," +
                customer.getPassword() + "," +
                creditBalance + "," +
                customer.getAddress()+ "," +
                customer.getFirstName()+ "," +
                customer.getEmail()+ "," +
                creditMax + ",";
        try{
            // Copying all the contents of old string
            BufferedReader file = new BufferedReader(new FileReader("Output/Bank_Users.csv"));
            // Where to store all old information
            StringBuffer inputBuffer = new StringBuffer();
            // To help Traverse the old CSV file
            String line;
            // Copying all the stuff into the String Buffer
            while((line = file.readLine()) != null){
                inputBuffer.append(line);
                inputBuffer.append('\n');
            }
            // Appending the  file with the new user information
            inputBuffer.append(toAddToCSV);
            // Rewriting Bank CSV
            FileWriter writeToFile = new FileWriter("Output/Bank_Users.csv");
            writeToFile.append(inputBuffer.toString());
            writeToFile.close();
        }catch(Exception e){
            System.out.println("The bank Users was probably not made prior");
        }
        return customer;
    }

    /**
     * generateBankStatement prints the bank statement inquired and puts bank statement into directory specified
     * @param customer customer that needs bank statement generated for
     * @param directory where bank statement will be located
     */
    public static void generateBankStatement(Customer customer, String directory){
        //print bank statement inquired
        System.out.println(customer.print());
        // Putting the string into the file in the directory specified
        try{
            File newFile = new File(directory+ "/" + customer.getFirstName() + customer.getLastName() + ".txt");
            // Just made the new File
            newFile.createNewFile();

            FileOutputStream fileOut = new FileOutputStream(directory+ "/" + customer.getFirstName() + customer.getLastName() + ".txt");
            // Just wrote to file
            fileOut.write(customer.print().getBytes());
            fileOut.close();
        }catch(FileNotFoundException e){
            System.out.println("File Not Found");
        }catch(IOException e){
            System.out.println("IOException");
        }
    }
}

