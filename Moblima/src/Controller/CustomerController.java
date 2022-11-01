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

public class CustomerController {
    private static final String CUSTOMER_FILE = "Data/Customer.dat";
    private static ArrayList<Customer> listOfCustomers;
    private static int currentUserId = -1;
    
    //THIS READ FUNCTION HAS TO BE USED EVERYTIME THE PROGRAMME STARTS
    public static void readUserList() throws IOException, ClassNotFoundException {
        if (serialisedRead(CUSTOMER_FILE) == null) listOfCustomers = new ArrayList<>();
        else listOfCustomers = (ArrayList<Customer>) serialisedRead(CUSTOMER_FILE);
    }

    public static void updateCustomerList() throws IOException {
        serialisedWrite(CUSTOMER_FILE, listOfCustomers);
    }

    //AFTER READING FROM FILE, THE PROGRAMME CAN THEN RETRIEVE THE HASHMAP DATA
    public static ArrayList<Customer> retrieveCustomerList() {
        return listOfCustomers;
    }
    public static void addCustomerIntoList( Customer customer) throws IOException {
        listOfCustomers.add(customer);
        updateCustomerList();

    }
    public static void setCurrentUser(Customer C){
        currentUserId =listOfCustomers.indexOf(C);
    }
    public static Customer getCurrentUser(){
        if(currentUserId==-1) return null;
        else return listOfCustomers.get(currentUserId);
    }
    public static void removeCustomerFromList(Customer customer) throws IOException{
        listOfCustomers.remove(customer);
        updateCustomerList();
    }
}
