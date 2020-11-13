/**
 * @author Yulianna Torres, Judith Garcia, Isaiah Landin
 * @since 11/02/2020
 *
 * Class Description:
 * RunBank is the main class for the program that calls all other classes.
 */

import java.io.*;
import java.util.*;

public class RunBank {
    /**
     * helps locate in what index of the csv all parameters are
     * @param header header in csv
     * @param format string split by commas
     * @return index of location in csv
     */
    public static int findFormatIndex(String header, String[] format){
        for(int i = 0; i < format.length; i++){
            if(header.equals(format[i])){
                return i;
            }
        }
        return -1;
    }

    /**
     * method reads a CSV and returns an ArrayList with the contents of the CVS file.
     * @param Path: Read the name of the csv file to open
     * @return ArrayList: Containing all users accounts
     */
    public static ArrayList read_csv(String Path){
        ArrayList<Customer> accounts= new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(Path))) {
            String line;
            // Read the heading of a CSV
            line = br.readLine();
            // Making format array
            String[] format = line.split(",");
            // For person Object
            int firstName = findFormatIndex("First Name", format);
            int lastName = findFormatIndex("Last Name", format);
            int dateOfBirth = findFormatIndex("Date of Birth", format);
            int phoneNumber = findFormatIndex("Phone Number", format);
            int idNum = findFormatIndex("Identification Number", format);
            int address = findFormatIndex("Address", format);
            int email = findFormatIndex("Email", format);
            int password = findFormatIndex("Password", format);
            // For Other Account information
            int savingsAccountNumber = findFormatIndex("Savings Account Number", format);
            int savingsStartBalance = findFormatIndex("Savings Starting Balance", format);

            int checkingAccountNumber = findFormatIndex("Checking Account Number", format);
            int checkingStartingBalance = findFormatIndex("Checking Starting Balance", format);

            int creditMax = findFormatIndex("Credit Max", format);
            int creditNumber = findFormatIndex("Credit Account Number", format);
            int creditStartingBalance = findFormatIndex("Credit Starting Balance", format);


            while ((line = br.readLine()) != null) { //read all lines

                //All values located inn the same line are save in the variable values after being separated by a comma
                String[] values = line.split(",");

                String address2 = values[address + 1] + ", " + values[address + 2] + ", " + values[address + 3];

                Customer customer = new Customer(
                        values[firstName + 3],
                        values[lastName],
                        values[dateOfBirth] +"," +values[dateOfBirth + 1],
                        Integer.parseInt(values[idNum]),
                        address2,
                        values[phoneNumber + 1],
                        values[email + 3],
                        values[password + 1],
                        Float.parseFloat(values[checkingStartingBalance + 1]),
                        Integer.parseInt(values[checkingAccountNumber + 1]),
                        Float.parseFloat(values[savingsStartBalance + 1]),
                        Integer.parseInt(values[savingsAccountNumber]),
                        Float.parseFloat(values[creditStartingBalance + 1]),
                        Integer.parseInt(values[creditNumber + 1]),
                        Float.parseFloat(values[creditMax + 3]));
                BankStatement bankStatement = new BankStatement();
                bankStatement.setStartingCheckingsBalance(values[checkingStartingBalance + 1]);
                bankStatement.setStartingCreditBalance(values[creditStartingBalance + 1]);
                bankStatement.setStartingSavingsBalance(values[savingsStartBalance + 1]);
                customer.setBankStatement(bankStatement);
                accounts.add(customer);


            }

            // Catch any errors found in the code above
        } catch (FileNotFoundException e) {
            //Is the address of the error
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Collections.sort(accounts, new Customer.sort_by_id());

        return accounts;
    }

    /**
     * main method
     * @param args needed for main method
     */
    public static void main(String[] args) {
        String inCSV = "Input/Bank_Users.csv" ;
        String outCSV = "Output/Bank_Users.csv";
        // Making a copy of the input CSV file
        try{
            // Copying all the contents of old string
            BufferedReader file = new BufferedReader(new FileReader(inCSV));
            // Where to store all old information
            StringBuffer inputBuffer = new StringBuffer();
            // To help Traverse the old CSV file
            String line;
            // Copying all the stuff into the String Buffer
            while((line = file.readLine()) != null){
                inputBuffer.append(line);
                inputBuffer.append('\n');
            }
            // Rewriting Bank CSV
            FileWriter writeToFile = new FileWriter(outCSV);
            writeToFile.append(inputBuffer.toString());
            writeToFile.close();
        }catch(Exception e){
            UI.print("Could not copy the new file");
        }

        ArrayList<Customer> accounts = read_csv("Input/Bank_Users.csv");
        Menu menu = new Menu();
        menu.general_menu(accounts);
    }
}
