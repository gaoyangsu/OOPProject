package Controller;
import Entity.Movie;
import Entity.Review;
import Entity.SystemSettings;
import Entity.Theatre;
import Entity.TheatreEnums;
import Entity.Customer;
import Entity.Holiday;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

import static Controller.RWController.*;

/**
 A class of CRUD operations on the Customer.dat file
 Calls RWController to read and write data into .dat file
 @version 1.0
 @since 2022-11-01
 */
public class CustomerController implements Initialiser{
    /**
     * {@code String } denoting the location of the Customer.dat file
     */
    private static final String CUSTOMER_FILE = "Data/Customer.dat";
    /**
     * {@code ArrayList } denoting the list of {@code Customer} classes to be retrieved while the Customer.dat is being read
     */
    private static ArrayList<Customer> listOfCustomers;
    /**
     * denote which index in the ArrayList of Customers to be Picked
     */
    private static int currentUserId = -1;
    
    
    /** 
     * Thiss method calls the RWController, to read the file, and put them into {@code listOfCustomers} Arraylist.
     * THIS READ FUNCTION HAS TO BE USED EVERYTIME THE PROGRAMME STARTS
     * @throws IOException
     * @throws ClassNotFoundException
     */

    public static void readUserList() throws IOException, ClassNotFoundException {
        if (serialisedRead(CUSTOMER_FILE) == null) listOfCustomers = new ArrayList<>();
        else listOfCustomers = (ArrayList<Customer>) serialisedRead(CUSTOMER_FILE);
    }

    
    /** 
     * This method updates/overwrites the Customer.dat file with {@code listOfCustomers} Arraylist.
     * @throws IOException
     */
    public static void updateCustomerList() throws IOException {
        serialisedWrite(CUSTOMER_FILE, listOfCustomers);
    }

    
    /** 
     * This method gets the {@code  ArrayList<Customer> } previous being created in
     * the readUserList() function
     * @return ArrayList<Customer>
     */
    //AFTER READING FROM FILE, THE PROGRAMME CAN THEN RETRIEVE THE HASHMAP DATA
    public static ArrayList<Customer> retrieveCustomerList() {
        return listOfCustomers;
    }
    
    /** 
     * This method adds a {@code Customer} class into the {@code listOfCustomers} Arraylist.
     * @param customer
     * @throws IOException
     */
    public static void addCustomerIntoList( Customer customer) throws IOException {
        listOfCustomers.add(customer);
        updateCustomerList();

    }
    
    /** 
     * This method sets the CurrentUser with an index 
     * @param C
     */
    public static void setCurrentUser(Customer C){
        currentUserId =listOfCustomers.indexOf(C);
    }
    
    /** 
     * This methods allows user to get the CurrentUser in the {@code listOfCustomers} Arraylist.
     * @return Customer
     */
    public static Customer getCurrentUser(){
        if(currentUserId==-1) return null;
        else return listOfCustomers.get(currentUserId);
    }
    
    /** 
     * This method enables user to remove a {@code Customer} class from {@code listOfCustomers} Arraylist
     * @param customer
     * @throws IOException
     */
    public static void removeCustomerFromList(Customer customer) throws IOException{
        listOfCustomers.remove(customer);
        updateCustomerList();
    }
    /** 
     * This method intialises the .dat files and place all of them 
     * in runtime during execution of the java file
     * @return boolean true to denote all the .dat files are properly read
     * @return false to denote file integrity issues
     */
    @Override
    public boolean initialise(){
        try{readUserList();
        return true;
    } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        } catch (ClassNotFoundException ex) {
            return true;
        }
    }
}
