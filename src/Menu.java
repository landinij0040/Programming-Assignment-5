/**
 * @author Judith Garcia, Yulianna Torres, Isaiah Landin
 * @since 11/09/2020
 *
 * Class Description:
 * Menu serves as a repository for all menu switch cases presented during RunBank.
 *
 * Assumptions:
 * 1) Transaction action is a separate bank menu option
 * 2) Bank manager creates new users
 * 3) Customer finds account information using email and password
 * 4) Bank manager has option to find accounts by name or account number
 * 5) Money amounts entered will always be a number: entered correctly like in keypad
 */

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Menu{
    /**
     * general_menu displays the general menu shown at the beginning of each run of the code
     * @param accounts : Array list with all users accounts
     */
    //------------------------------- Menus -----------------------------------


    public void general_menu(ArrayList<Customer> accounts){
        String Option = "0";

        while(!Option.equals("4")) {
            UI.generalMenu();

            Option = UI.getOption();

            switch (Option){
                case "1":  // Normal User Menu
                    normal_user_menu(accounts);
                    break;
                case "2":  // Bank Manager Menu
                    bank_manager_menu(accounts);
                    break;
                case "3":  // Transaction Action
                    transactionFinder(accounts);
                    break;
                case "4":  // Exit the menu
                    System.out.println("Goodbye!");
                    break;
                default:   // Error
                    System.out.println("Option not found!");
            }
        }
    }

    /**
     * normal_user_menu displays the user's menu.
     * @param accounts : Array list with all users accounts
     *
     * Assumptions:
     * 1) Users will only enter numbers when asked to enter a number
     */

    public void normal_user_menu(ArrayList<Customer> accounts){
        String Option;
        Customer customer;

        customer = getCustomerByEmailAndPassword(accounts);

        Option = "0";

        if(customer != null) {
            while (!Option.equals("8")) {
                UI.normalUserMenu();
                Option = UI.getOption();
                switch (Option) {
                    case "1":     // Inquire From Account
                        UI.print("\nInquire from which account type? Please type in |Savings or Checking or Credit|  ");
                        Option = UI.getOption();
                        transactionReader(customer.getFirstName(), customer.getLastName(), Option, "inquires","","","","",accounts );
                        break;
                    case "2":     // Deposit into an account
                        UI.print("\nDeposit to: Checking, Credit or Savings?");
                        Option = UI.getOption();
                        UI.print("\nHow much would you like to deposit?(Enter number)");
                        String actionAmount = UI.getOption();
                        transactionReader("", "", "", "deposits", customer.getFirstName(), customer.getLastName(), Option, actionAmount, accounts);
                        break;
                    case "3":     // Withdraw From Account
                        UI.print("\nWhere would you like to withdraw money from? Please Type|Checking or Savings|");
                        Option = UI.getOption();
                        UI.print("How much would you like to withdraw?(Enter number)");
                        String amount = UI.getOption();
                        transactionReader(customer.getFirstName(), customer.getLastName(), Option, "withdraws", "","","",amount, accounts);

                        break;
                    case "4":     // Transfer
                        UI.print("Which account would you like to transfer from |Checking or Savings|");
                        Option = UI.getOption();
                        UI.print("Which account would you like to transfer to |Checking or Savings or Credit|");
                        String Option2 = UI.getOption();
                        UI.print("How much would you like to Transfer?(Enter number)");
                        String amountTrans = UI.getOption();
                        transactionReader(customer.getFirstName(),customer.getLastName(), Option, "transfers", customer.getFirstName(), customer.getLastName(), Option2, amountTrans, accounts);
                        break;
                    case "5":    //  Pay Someone
                        UI.print("Please type which account you would like to pay from. Type|Checking or Savings|");
                        Option = UI.getOption();

                        UI.print("Please type the first name of the user that you would like to pay.");
                        String payeeFirstName= UI.getOption();

                        UI.print("Please type the last name of the user that you would like to pay.");
                        String payeeLastName = UI.getOption();

                        UI.print("Please Select the amount that you would like to pay");
                        String amountPay = UI.getOption();
                        transactionReader(customer.getFirstName(), customer.getLastName(), Option, "pays", payeeFirstName, payeeLastName, "Checking", amountPay, accounts);
                        break;
                    case "6":    //  Create Checking Account
                        if(customer.getChecking() != null)
                            UI.print("Already have a Checking account!");
                        else {
                            customer.setChecking(new Checking(customer.getSavings().getAccount_Number() - 1000, 0));
                            UI.print("Checking account created, your Checking account is: " + customer.getChecking().getAccount_Number());
                        }
                        break;
                    case "7":    // Create Credit Account
                        if(customer.getCredit() != null)
                            UI.print("Already have a Credit account!");
                        else {
                            customer.setCredit(new Credit(customer.getSavings().getAccount_Number() + 1000, 0));
                            UI.print("Credit account created, your Credit account is: " + customer.getCredit().getAccount_Number());
                        }
                        break;
                    case "8":    // Exit
                        UI.print("Goodbye!");
                        break;
                    default:     // Error
                        UI.print("Option not found!");
                }
            }
        }

    }

    /**
     * @author Isaiah Landin
     * @param accounts list of customer accounts in bank
     * @return customer inquired about
     */
    public Customer getCustomerByEmailAndPassword(ArrayList<Customer> accounts){
        UI.print("\nPlease Enter Your Email");
        String Option = UI.getOption();
        for(int i = 0; i < accounts.size(); i++){
            if(Option.equals(accounts.get(i).getEmail())) {
                UI.print("\nPlease Enter Your Password");
                String userPassword = UI.getOption();
                if(userPassword.equals(accounts.get(i).getPassword())){
                    return accounts.get(i);
                }
            }
        }
        return null;
    }


    /**
     * bank_manager_menu displays the bank manager's menu.
     * @param accounts : Array list with all users account in bank
     */

    public void bank_manager_menu(ArrayList<Customer> accounts){
        String Option = "0";
        Customer customer;
        BankManager bankManager = BankManager.getInstance();//singleton object

        while(!Option.equals("6")) {
            UI.bankManagerMenu();

            Option = UI.getOption();

            switch (Option){
                case "1":            // Inquire account by name
                    UI.print("\nWho’s account would you like to inquire about?");
                    Option = UI.getOption();
                    customer = getCustomer(accounts, Option);
                    if(customer != null){
                        bankManager.print_customer_info(customer);
                    }else{
                        UI.print("Account not found");
                    }
                    break;
                case "2":            // Inquire account by type/number
                    UI.print("\nWhat account type?");
                    Option = UI.getOption();
                    customer = getCustomer_Account(accounts, Option);
                    if(customer != null){
                        bankManager.print_customer_info(customer);
                    }else{
                        UI.print("Account not found");
                    }
                    break;
                case "3":            // Inquire all accounts
                    UI.print("-------- All Accounts --------");
                    for (Customer account : accounts) {
                        bankManager.print_customer_info(account);
                        UI.print("---------------------------");
                    }
                    break;
                case "4":            // Create User
                    Customer newCustomer = bankManager.create_user(accounts);
                    if (newCustomer != null) {
                        accounts.add(newCustomer);
                        UI.print("User created!");
                    }else{
                        UI.print("User not created");
                    }
                    break;
                case "5":             // Generate Bank Statement
                    UI.print("\nWho’s account would you like to generate a bank statement?");
                    Option = UI.getOption();
                    customer = getCustomer(accounts, Option);
                    if(customer != null){
                       BankManager.generateBankStatement(customer,"Output/Bank Statements");
                    }else
                        UI.print("The user " + Option + " doesn't exists!");
                    break;
                case "6":             // Exit
                    UI.print("Goodbye!");
                    break;
                default:               // Error
                    UI.print("Option not found!");

            }
        }

    }

    //------------------------------- Users/Accounts Information -----------------------------------
    /**
     * Method that retrieves an account with a specific Account number
     *
     * @param accounts: ArrayList that contains all users accounts
     * @param Option: Type of account inquired
     * @return Customer: Personal Info
     */
    public Customer getCustomer_Account(ArrayList<Customer> accounts, String Option){
        UI.print("What is the account number?");
        int type = UI.getOptionInt();

        for (Customer account : accounts) {
            if (Option.equals("Credit") && type == account.getCredit().getAccount_Number() || Option.equals("Checking") && type == account.getChecking().getAccount_Number() || Option.equals("Savings") && type == account.getSavings().getAccount_Number())
                return account;
        }

        return null;
    }

    /**
     * Retrieves a user with a specific name
     * @param accounts: ArrayList that contains all users accounts
     * @param name: name of account username
     * @return Customer: Personal Info
     */
    public static Customer getCustomer(List<Customer> accounts, String name){
        for (Customer account : accounts) {
            if (name.equals(account.getFirstName() + " " + account.getLastName()))
                return account;
        }
        return null;
    }


    /**
     * Rewrite the CSV file based on a new row
     * @author Isaiah Landin
     * @param oldRow: the row that will be replaced
     * @param newRow: the row that will replace oldRow
     * @param bankUserCSV: what file to rewrite
     */
    public static void rewriteCSV(String oldRow, String newRow, String bankUserCSV){
        try {
            // Making new Buffered Reader to copy contents
            BufferedReader file = new BufferedReader(new FileReader(bankUserCSV));
            // Where to store all old information
            StringBuffer inputBuffer = new StringBuffer();
            // To Help Traverse the old CSV file
            String line;
            // Copying all the stuff into the String Buffer
            while ((line = file.readLine()) != null) {
                inputBuffer.append(line);
                inputBuffer.append('\n');
            }
            file.close();
            // Putting String Buffer into a String object
            String inputStr = inputBuffer.toString();
            // Replace the text
            inputStr = inputStr.replace(oldRow, newRow);
            // Make a new file
            FileOutputStream fileOut = new FileOutputStream(bankUserCSV);
            // Writing the new contents
            fileOut.write(inputStr.getBytes());
            fileOut.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    /**
     * Helper Function to make a split array into a string.
     * @author Isaiah Landin
     * @param array: array to be turned into a string
     * @return array as a string
     */
    public static String stringArrayToString(String[] array){
        // The String to return
        String stringToReturn =  "";
        int i;
        for(i = 0; i < array.length - 1; i++){
            stringToReturn = stringToReturn + array[i] + ",";
        }
        stringToReturn = stringToReturn + array[i];
        return stringToReturn;
    }

    /**
     * Returns the account of a user based on the type string value
     * @author Isaiah Landin
     * @param customer: the customer to get the account from
     * @param type: which type of account (Savings, Checking, Credit)
     * @return Account
     */
    public static Account whatTypeOfAccount(Customer customer, String type){
        if(type.equals("Savings")){           // Return Savings
            return customer.getSavings();
        }else if(type.equals("Checking")){    // Return Checking
            return customer.getChecking();
        }else if(type.equals("Credit")){      // Return Credit
            return customer.getCredit();
        }else{                                // Edge Case
            return null;
        }
    }

    /**
     * Log a string into a customer bank statement
     * @author Isaiah Landin
     * @param customer: where to log for the bank statement for the customer
     * @param stringToLog: what string to log
     * @param transactionLogDirectory location of transaction logs
     */
    public static void log(Customer customer,String stringToLog, String transactionLogDirectory){

        // Make new Date
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        // Making stringToLog with the date appended
        stringToLog = stringToLog +" " +dtf.format(now);
        // Appending new line
        stringToLog = stringToLog + "\n";
        // Appending stringToLog to Customer File
        customer.getBankStatement().appendTransaction(stringToLog);

        // Appending to output transaction
        try {
            FileWriter writeToFile = new FileWriter(transactionLogDirectory, true);
            writeToFile.append(stringToLog);
            writeToFile.flush();
            writeToFile.close();
        }catch(Exception e){
            System.out.println("Exception Message");
        }

    }

    /**
     * Inquire Balance
     * @author Isaiah Landin
     * @param customer: customer to inquire balance from
     * @param transactionLogDirectory: where to put the transaction log for the whole bank
     * @param from: what account to inquire balance from
     */
    public static void inquireBalance(Customer customer, String transactionLogDirectory, String from){

        // Finding what type of account it is
        Account type = whatTypeOfAccount(customer,from);
        // Showing To user
        if(type instanceof Checking){      // Displaying Checking Account
            UI.print("CHECKINGS: " + ((Checking) type).printBalance());
        }
        if(type instanceof Savings){       // Displaying Savings Account
            UI.print("SAVINGS:" + ((Savings) type).printBalance());
        }
        if(type instanceof Credit){        // Displaying Credit Account
            UI.print("CREDIT:" + ((Credit) type).printBalance());
        }
        if(type == null){                  // Displaying that account was not made
            UI.print("NA");
        }

        // Making String to log into User Transaction and Bank Transaction
        String stringForEverything =
                customer.getFirstName() + " " +
                        customer.getLastName()  + " " +
                        from +
                        " inquires";
        // Logging Transaction
        log(customer, stringForEverything, transactionLogDirectory );
    }

    /**
     * Get Row By Id from the Bank Users CSV
     * @author Isaiah Landin
     * @param id: Id of the customer to get
     * @param bankUserCSV: the Bank User CSVS
     * @return return string line of customer from csv
     */
    public static String[]getRowById(String id, String bankUserCSV){
        try {
            // To Read From the CSV file
            BufferedReader br = new BufferedReader(new FileReader(bankUserCSV));
            // Helper Variable
            String line;
            // Traversing the CSV to find the account with the specified id
            while ((line = br.readLine()) != null) {
                // Split the line to help find the id
                String[] accountToCheck = line.split(",");
                // Finding if id matches
                if (accountToCheck[2].equals(id)) {
                    // Found match returning the split line
                    return accountToCheck;
                }
            }
        }catch(Exception e){
            System.out.println("There was some exception");
        }
        // account was not found
        return null;
    }

    /**
     * Method for the Customer to transfer money from an account to another account
     * @author Isaiah Landin
     * @param customer: The customer that wants to transfer money from different accounts
     * @param fromWhere: the account to transfer from
     * @param toWhere: the account to transfer to
     * @param actionAmount: amount to transfer
     * @param transactionLogDirectory: directory to log this action to
     * @param bankUserCSVInput: Input CSV to change
     * @param bankUserCSVOutput: Output CSV that is changed
     */
    public static void transfer(
            Customer customer,              // Customer that is transferring
            String fromWhere,
            String toWhere,
            String actionAmount,
            String transactionLogDirectory,  // Where to log into the whole bank log
            String bankUserCSVInput,         // Input User CSV to UPDATE
            String bankUserCSVOutput         // Output User CSV that has been updated
    ) {
        if (customer == null) {                    // customer was not found
            UI.print("Customer was not found");
            return;
        }
        if (Float.parseFloat(actionAmount) < 0) { // action amount is negative
            UI.print("Can not transfer a negative amount");
            return;
        }

        // Getting accounts to transfer
        Account fromType = whatTypeOfAccount(customer, fromWhere);
        Account whereType = whatTypeOfAccount(customer, toWhere);

        if ((fromType != null) && (whereType != null)) { // Has Both accounts
            if (fromType.get_Balance() >= Double.parseDouble(actionAmount)) {  // action amount is not bigger than from accounts balance
                if ((whereType instanceof Credit) && ((-1 * whereType.get_Balance()) < Double.parseDouble(actionAmount))) { // if the where account is of type credit and the action amount exceeds the principle
                    UI.print("Action amount exceeds credit principle.");
                    return;
                }
                // The String to put into the whole bank transaction log and individual account logs
                String toLog = "";
                // The String to display the new balance from the account giving
                String newBalanceFrom = "";
                // The String to display the new balance from the account receiving
                String newBalanceWhere = "";

                // The Actual Transfer
                // Taking amount from the account transferring
                fromType.set_Balance(fromType.get_Balance() - Float.parseFloat(actionAmount));
                // Taking amount from the account receiving
                whereType.set_Balance(whereType.get_Balance() + Float.parseFloat(actionAmount));

                // Giving the to Log a string to log

                toLog = customer.getFirstName() + " " +
                        customer.getLastName() + " " +
                        fromWhere + " " +
                        "transfers" + " " +
                        customer.getFirstName() + " " +
                        customer.getLastName() + " " +
                        toWhere + " " +
                        actionAmount;
                // Printing the log to the console
                UI.print(toLog);
                // Printing the new balances
                UI.print(fromWhere + ": " + fromType.get_Balance());
                // Logging the action to the whole bank transactions and the user transaction
                log(customer, toLog, transactionLogDirectory);

                // Getting row to change
                String[] gettingRow = getRowById(String.valueOf(customer.getIdentificationNum()), bankUserCSVInput);
                // Making the old string
                String oldString = stringArrayToString(gettingRow);
                // Finding which accounts to update
                // For the from balance
                int fromCSV;
                UI.print(toWhere + ": " + whereType.get_Balance());
                // Assuming that you can to transfer from the credit account
                if (fromType instanceof Savings) {
                    fromCSV = 9;
                } else {
                    fromCSV = 8;
                }
                // Updating for the CSV
                gettingRow[fromCSV] = String.valueOf(fromType.get_Balance());


                // for the where balance
                int whereCSV;
                if (whereType instanceof Savings) {
                    whereCSV = 8;
                } else if (whereType instanceof Checking) {
                    whereCSV = 8;
                } else {  // whereType instanceof Credit
                    whereCSV = 11;
                }

                // updating the csv
                gettingRow[whereCSV] = String.valueOf(whereType.get_Balance());
                // putting the row back to a string
                String newString = stringArrayToString(gettingRow);

                // Finally rewriting the csv file
                rewriteCSV(oldString, newString, bankUserCSVOutput);

            } else { // action amount is bigger that from accounts balance
                UI.print("Amount exceeds " + fromWhere);
                return;
            }
        } else {    // One Or both accounts are missing
            UI.print("One of the accounts was not found");
            return;
        }
    }

    /**
     * Method for the customer to deposit money into an account
     * @author Isaiah Landin
     * @param customer: The customer that wants to deposit money into an account
     * @param toWhere: the account to deposit to
     * @param actionAmount: amount to deposit
     * @param transactionLogDirectory: directory to log this action to
     * @param inputBankUserCSV: Input CSV to change
     * @param outputBankUserCSV: Output CSV that is changed
     */
    public static void deposit(Customer customer,   // the customer
            String toWhere,      // Account customer wants to deposit
            String actionAmount, // Amount customer wants to deposit
            String transactionLogDirectory, // directory to where all transactions are stored
            String inputBankUserCSV,  // Where the input bank users is
            String outputBankUserCSV  // Where the output bank users are
                           ) {

        if(customer == null){ // Customer is null
            UI.print("Customer was not found");
            return;
        }

        if(Double.parseDouble(actionAmount) < 0){ // If Action amount is negative
            UI.print("Can not deposit a negative amount");
            return;
        }

        // Finding the account
        Account type = whatTypeOfAccount(customer, toWhere);

        // If account was not found
        if(type == null){
            UI.print("Account is not available");
            return;
        }

        // If the count is the credit account and the action amount exceeds the principle
        if((type instanceof Credit) && (-1 * type.get_Balance() < Float.parseFloat(actionAmount))){
            UI.print("Amount exceeds Principle");
            return;
        }
        String toLog = "";
        String newBalance = "";
        // Depositing into the account
        type.set_Balance(type.get_Balance() + Float.parseFloat(actionAmount));
        toLog = "deposits" +              " " +
                customer.getFirstName() + " " +
                customer.getLastName() +  " " +
                toWhere +                 " " +
                actionAmount;

        // Printing log to console
        UI.print(toLog);
        UI.print(toWhere + " " + type.get_Balance());
        // logging
        log(customer,toLog, transactionLogDirectory);
        // Updating CSV
        String[] gettingRow = getRowById(String.valueOf(customer.getIdentificationNum()), inputBankUserCSV);
        String oldString = stringArrayToString(gettingRow);
        // finding which column to update
        int whereCSV;
        if(type instanceof Savings){        // Savings
            whereCSV = 9;
        }else if(type instanceof Checking){ // Checking
            whereCSV = 8;
        }else{                              // Credit
            whereCSV = 11;
        }

        gettingRow[whereCSV] = String.valueOf(type.get_Balance());
        String newString = stringArrayToString(gettingRow);

        // Finally update CSV
        rewriteCSV(oldString,newString,outputBankUserCSV);

    }


    /**
     * Method for the customer to deposit money into an account
     * @author Isaiah Landin
     * @param customer: The customer that wants to withdraw money from an account
     * @param fromWhere: the account to withdraw from
     * @param actionAmount: amount to deposit withdraw from
     * @param transactionLogDirectory: directory to log this action to
     * @param inputBankUserCSV: Input CSV to change
     * @param outputBankUserCSV: Output CSV that is changed
     */
    public static void withdraw(Customer customer,   // the customer
            String fromWhere,                        // Account customer wants to deposit
            String actionAmount,                     // Amount customer wants to withdraw
            String transactionLogDirectory,          // directory to where all transactions are stored
            String inputBankUserCSV,                 // Where the input bank users is
            String outputBankUserCSV                 // Where the output bank users are
    )
    {

        if(customer == null){ // Customer is null
            UI.print("Customer was not found");
            return;
        }

        if(Double.parseDouble(actionAmount) < 0){ // If Action amount is negative
            UI.print("Can not deposit a negative amount");
            return;
        }

        // Finding the account
        Account type = whatTypeOfAccount(customer, fromWhere);

        // If account was not found
        if(type == null){
            UI.print("Account is not available");
            return;
        }

        // If Action amount is more than the account
        if(type.get_Balance() < Double.parseDouble(actionAmount)){
            UI.print("Amount exceeds account Balance");
            return;
        }

        String toLog = "";
        String newBalance = "";
        // Withdrawing from the account
        type.set_Balance(type.get_Balance() - Float.parseFloat(actionAmount));
        toLog =
                customer.getFirstName() + " " +
                        customer.getLastName() +  " " +
                        fromWhere +               " withdraws" + " " +
                        actionAmount;

        // Printing log to console
        UI.print(toLog);
        UI.print(fromWhere + " " + type.get_Balance());
        // logging
        log(customer,toLog, transactionLogDirectory);
        // Updating CSV
        String[] gettingRow = getRowById(String.valueOf(customer.getIdentificationNum()), inputBankUserCSV);
        String oldString = stringArrayToString(gettingRow);
        // finding which column to update
        int fromCSV;
        if(type instanceof Savings){       // Savings
            fromCSV = 8;

        }else{ // Checking
            fromCSV = 7;
        }

        gettingRow[fromCSV] = String.valueOf(type.get_Balance());
        String newString = stringArrayToString(gettingRow);

        // Finally update CSV
        rewriteCSV(oldString,newString,outputBankUserCSV);

    }

    /**
     * Method for the customer to pay another user
     * @author Isaiah Landin
     * @param customer1: Payer
     * @param fromWhere: the account to pay from
     * @param customer2: Payee
     * @param toWhere: the account to pay to
     * @param actionAmount: amount to pay
     * @param allBankDirectoryTrans: directory to log this action to
     * @param outputCSV: Input CSV to change
     * @param inputCSV: Output CSV that is changed
     */
    public static void pay(Customer customer1,          // The payer
                           String fromWhere,            // The Account Paying From (Savings or Checking)

                           Customer customer2,          // The payee
                           String toWhere,              // The Account receiving (Savings or Checking)

                           String actionAmount,         // The Amount to be payed

                           String allBankDirectoryTrans,// Where Transact the payment for the whole system

                           String outputCSV,            // Where the CSV to update is located

                           String inputCSV             // Where the input CSV is located
    ){
        // Either Payer or Payee is not a customer
        if((customer1 == null) || (customer2 == null)){
            UI.print("Customer not found");
            return;
        }

        // Payer cannot pay themselves
        if(customer1 == customer2){
            UI.print("Customer can not pay themselves");
            return;
        }
        // the action amount is negative
        if(Double.parseDouble(actionAmount) < 0){
            UI.print("Can not pay a negative amount");
            return;
        }
        // getting the respective accounts
        // getting payer account
        Account fromType = whatTypeOfAccount(customer1, fromWhere);
        // getting payee account
        Account whereType = whatTypeOfAccount(customer2, toWhere);

        if((fromType != null) && (whereType != null)){  // Both accounts were found
            if(fromType.get_Balance() >= Double.parseDouble(actionAmount)){ // action amount is NOT bigger than payers account
                // String to log into the customers transaction and whole banks transaction
                String toLog = "";

                // String to print the new balance for payer
                String newBalanceForm = "";

                // String to print the new balance for the payee
                String newBalanceWere = "";

                // The actual payment
                // taking action amount out or payers account
                fromType.set_Balance(fromType.get_Balance() - Float.parseFloat(actionAmount));
                // adding action amount to payee account
                whereType.set_Balance(whereType.get_Balance() + Float.parseFloat(actionAmount));

                // adding making string to log in the customers transaction and while bank transaction
                toLog = customer1.getFirstName() + " " +
                        customer1.getLastName()  + " " +
                        fromWhere                + " " +
                        "pays"                   + " " +
                        customer2.getFirstName() + " " +
                        customer2.getLastName()  + " " +
                        toWhere                  + " " +
                        actionAmount;

                // printing the log to console
                UI.print(toLog);

                // Printing new balances
                // Printing payers new  balance
                UI.print(
                        customer1.getFirstName() + " "  +
                                customer1.getLastName()  + " " +
                                fromWhere + " " + fromType.get_Balance());
                // Printing payees new balance
                UI.print(
                        customer2.getFirstName() + " " +
                                customer2.getLastName()  + " " +
                                toWhere + " " + whereType.get_Balance());

                // logging to Customers transactions and the whole bank
                // logging payers transaction
                log(customer1, toLog, allBankDirectoryTrans);
                // logging payees transactions
                log(customer2, toLog, allBankDirectoryTrans);

                // finding the row to update the csv
                // old string for payer
                String[] gotRow1 = getRowById(String.valueOf(customer1.getIdentificationNum()),inputCSV );
                String oldString1 = stringArrayToString(gotRow1);
                // old string for payee
                String[] gotRow2 = getRowById(String.valueOf(customer2.getIdentificationNum()), inputCSV);
                String oldString2 = stringArrayToString(gotRow2);

                // Which account to update for the payer
                // Assuming you cannot pay via credit
                int fromCSV;
                if (fromType instanceof Savings){
                    fromCSV = 9;
                }else{
                    fromCSV = 8;
                }
                gotRow1[fromCSV] = String.valueOf(fromType.get_Balance());
                String newString1 = stringArrayToString(gotRow1);
                rewriteCSV(oldString1, newString1, outputCSV);

                // Which account to update for the payee
                // Assuming you cannot get payed to the credit account
                int whereCSV;
                if(whereType instanceof Savings){
                    whereCSV = 9;
                }else{
                    whereCSV = 8;
                }
                gotRow2[whereCSV] = String.valueOf(whereType.get_Balance());
                String newString2 = stringArrayToString(gotRow2);
                rewriteCSV(oldString2, newString2, outputCSV);


            }else{// action amount is bigger than payers account
                UI.print("Amount exceeds " + fromWhere);
                return;
            }

        }else{ // One or both of the accounts were not found
            UI.print("One of the accounts was not found");
            return;
        }

    }

    /*--------- For the Transaction Actions -------------------------------------------------------*/
    /**
     * Reads A Transaction Action
     * @author Isaiah Landin
     * @param firstName: Sender first name
     * @param lastName: Sender last name
     * @param fromWhere: Which account from the sender
     * @param action: what action to execute
     * @param toFirstName: Receiver first name
     * @param toLastName: Receiver Last name
     * @param toWhere: which account from the receiver
     * @param actionAmount: amount for the action
     * @param customerList: the list of customers to find the Sender and the receiver
     */
    public static void transactionReader(
            String firstName,
            String lastName,
            String fromWhere,
            String action,
            String toFirstName,
            String toLastName,
            String toWhere,
            String actionAmount,
            List<Customer>customerList
    ){

        // Output BankUsers CSV
        String outputBankUsers = "Output/Bank_Users.csv";
        // Where to put the Transaction Logs
        String transActionLogs = "Output/TransactionLog.txt";
        // Finding from Customer
        Customer customer1 = getCustomer(customerList,firstName + " " + lastName);
        // Finding to Customer
        Customer customer2 = getCustomer(customerList, toFirstName + " " + toLastName);

        if(action.equals("inquires")){ //  user wants to inquire an account
            inquireBalance(customer1, transActionLogs , fromWhere);
        }
        if(action.equals("withdraws")){ // User wants to withdraw an account
            withdraw(customer1, fromWhere,actionAmount,transActionLogs, outputBankUsers, outputBankUsers);
        }
        if(action.equals("deposits")){ // User wants to deposit into an account
            deposit(customer2,toWhere,actionAmount,transActionLogs,outputBankUsers,outputBankUsers);
        }
        if(action.equals("transfers")){ // User Wants to transfer into an account
            transfer(customer1, fromWhere, toWhere, actionAmount, transActionLogs, outputBankUsers, outputBankUsers);
        }
        if(action.equals("pays")){     // User Wants to pay another customer
            pay(customer1, fromWhere, customer2, toWhere, actionAmount, transActionLogs, outputBankUsers,   outputBankUsers);
        }
    }

    /**
     * @author Isaiah Landin and Yulianna Torres
     * @param accounts list of customer accounts in the bank system
     */
    public static void transactionFinder(List<Customer> accounts){
        try{
            // parsing the transaction actions CSV into the BufferedReader
            BufferedReader br = new BufferedReader(new FileReader("Input/Actions.csv"));

            // Reading the heading of the CSV
            String line = br.readLine();

            // Reading the Transaction Action CSV and putting them into the transaction reader
            while( (line = br.readLine() ) != null){
                // Splitting the Transaction Actions
                String[] action = new String[8];
                // Dealing with the Index out of bounds exception
                String[] toPutIntoAction = line.split(",");
                for(int i = 0; i < action.length; i++){
                    try{
                        action[i] = toPutIntoAction[i];
                    }catch( Exception e){
                        action[i] = "";
                    }
                }
                transactionReader(
                        action[0],
                        action[1],
                        action[2],
                        action[3],
                        action[4],
                        action[5],
                        action[6],
                        action[7],
                        accounts
                );
            }
        }catch(IOException e){
            System.out.println("Could not find file");
        }
    }

}

