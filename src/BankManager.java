/**
 * Class Description:
 * BankManager has all bank manager functionality.
 *
 * Assumptions:
 * 1) Bank manager creates new users
 * 2) Bank manager generates bank statements
 * 3) there is only one bank manager for the whole bank
 * 4) There will never be an account number = 0
 *
 * @author Yulianna Torres, Isaiah Landin
 * @since 11/13/2020
 */

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BankManager {
    private static BankManager INSTANCE;
    private int biggestSavingsAccountNum = 0;
    private int biggestCheckingAccountNum = 0;
    private int biggestCreditAccountNum = 0;

    //Singleton constructor
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


    //Setters/Getters
    public void setBiggestSavingsAccountNum(int biggestSavingsAccountNum) {
        this.biggestSavingsAccountNum = biggestSavingsAccountNum;
    }

    public void setBiggestCheckingAccountNum(int biggestCheckingAccountNum) {
        this.biggestCheckingAccountNum = biggestCheckingAccountNum;
    }

    public void setBiggestCreditAccountNum(int biggestCreditAccountNum) {
        this.biggestCreditAccountNum = biggestCreditAccountNum;
    }

    public int getBiggestSavingsAccountNum() {
        return biggestSavingsAccountNum;
    }

    public int getBiggestCheckingAccountNum() {
        return biggestCheckingAccountNum;
    }

    public int getBiggestCreditAccountNum() {
        return biggestCreditAccountNum;
    }


    //Methods
    /**
     * setBiggestAccountNums finds the max account numbers for Savings, Checking, and Credit accounts and sets then to the BankManager parameters
     * @param accounts list of accounts in bank system
     */
    public void setBiggestAccountNums(List<Customer> accounts){
        //if biggest account num hasn't been 
        if ((biggestSavingsAccountNum == 0) && (biggestCreditAccountNum == 0) && (biggestCheckingAccountNum == 0)){
            //iterate through all accounts to find/set max account numbers
            for (Customer account : accounts) {
                //Savings account number
                if (account.getSavings().getAccount_Number() > biggestSavingsAccountNum) {
                    setBiggestSavingsAccountNum(account.getSavings().getAccount_Number());
                }
                //Checking account number
                if (account.getChecking().getAccount_Number() > biggestCheckingAccountNum) {
                    setBiggestCheckingAccountNum(account.getChecking().getAccount_Number());
                }
                //Credit account number
                if (account.getCredit().getAccount_Number() > biggestCreditAccountNum) {
                    setBiggestCreditAccountNum(account.getCredit().getAccount_Number());
                }
            }
        }
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
        //Check if last element in user_data is empty, if yes display error and return to bank manager menu to try again
        if (user_data.get(user_data.size() - 1).isEmpty()) {
            System.out.println("First name can't be empty");
            return null;//returns to original bank manager menu
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
        System.out.println("Identification Number: (Ex: ##)");
        user_data.add(UI.getOption());
        if (user_data.get(user_data.size() - 1).isEmpty()) {
            System.out.println("Identification Number can't be empty");
            return null;
        }
        try{
            Integer.parseInt(user_data.get(user_data.size() - 1));
        }catch (NumberFormatException e ){
            System.out.println("identification number must be inputted as a number");
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

        //-------Generating Accounts---------
        //find/set biggest account numbers
        setBiggestAccountNums(accounts);

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

        customer = new Customer(user_data.get(0),//first name
                user_data.get(1),//last name
                user_data.get(2),//dob
                Integer.parseInt(user_data.get(3)),//identification num
                user_data.get(4),//address
                user_data.get(5),//phone number
                user_data.get(6),//email
                user_data.get(7),//password
                Float.parseFloat(user_data.get(8)),//Savings balance
                biggestSavingsAccountNum + 1);//Savings account number
        System.out.println("Savings was created successfully");
        setBiggestSavingsAccountNum(customer.getSavings().getAccount_Number()); //set new biggest savings account number
        System.out.println("Savings account number is now " + customer.getSavings().getAccount_Number());
        //set a bank statement for the customer
        BankStatement bankStatement = new BankStatement(savingsStartingBalance);
        customer.setBankStatement(bankStatement);

        //Option for checking
        System.out.println("Would you like to create a Checking account?(y/n)");
        String option = UI.getOption();
        switch (option){
            case "y":
                System.out.println("What is the Checking Account starting balance?");
                user_data.add(UI.getOption());
                if (user_data.get(user_data.size() - 1).isEmpty()) {
                    System.out.println("Checking Account balance can't be empty");
                    return null;
                }
                customer.getBankStatement().setStartingCheckingsBalance(user_data.get(user_data.size() - 1));
                //Create Checking Account
                customer.createChecking(biggestCheckingAccountNum + 1, //Checking account number
                                        Float.parseFloat(user_data.get(user_data.size() - 1))); //checking account balance
                System.out.println("Checking account created");
                setBiggestCheckingAccountNum(customer.getChecking().getAccount_Number()); //set new biggest checking account number
                System.out.println("Checking account number is now " + customer.getChecking().getAccount_Number());
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
                //Create Credit account
                customer.createCredit(biggestCreditAccountNum + 1, //Credit account number
                        Float.parseFloat(user_data.get(user_data.size() - 2)),//credit account balance
                        Float.parseFloat(user_data.get(user_data.size() -1))); //credit max

                System.out.println("Credit account created");
                setBiggestCreditAccountNum(customer.getCredit().getAccount_Number()); //set new biggest checking account number
                System.out.println("Credit account number is now " + customer.getCredit().getAccount_Number());
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

